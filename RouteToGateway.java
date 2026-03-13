import java.util.*;

public class RouteToGateway {


    //https://www.geeksforgeeks.org/dsa/dijkstras-shortest-path-algorithm-greedy-algo-7/
    //we create a large value to serve as a "infinite" value as in dijkstras unknown paths are lebeled infinite. Dont use actual infinity because I dont want any funny overflow errors or risk of that possability
    static int infinity = 12345678;
    
    public static void main(String[] args){
        

        //NOTE WHEN IT COMES TO IT: -1 represent infinite weight

        Scanner myScanner = new Scanner(System.in);

        int numOfRout = myScanner.nextInt();

        //adj matrix for the graph were gonna be working w
    
        int[][] adjMatrix = new int[numOfRout][numOfRout];

        //iterate through graph to read matrix
        for(int i = 0; i < numOfRout; i++){
            
            for(int j = 0; j < numOfRout; j++){
                
                adjMatrix[i][j] = myScanner.nextInt();
            }
        }

        //eat the blankspace
        myScanner.nextLine();
                                                                        //abolish all whitespace!!!!!
        String[] gatewayRoutRead = myScanner.nextLine().trim().split("\\s+");
    

        int[] gatewayRouters = new int[gatewayRoutRead.length];
        boolean[] isGatewayRouters = new boolean[numOfRout];

        for(int i = 0; i < gatewayRoutRead.length; i++){
            gatewayRouters[i] = Integer.parseInt(gatewayRoutRead[i]) - 1;
            
            isGatewayRouters[gatewayRouters[i]] = true;
        }

        int securityAgent = myScanner.nextInt() - 1;

        //transpose the current graph to prepare dijkstras alg based on the hint provided in the assignment description
        int[][] tempTransposing = new int[numOfRout][numOfRout];

        for(int i = 0; i < numOfRout; i++){
            for(int j = 0; j < numOfRout; j++){
                tempTransposing[j][i] = adjMatrix[i][j];
            }
        }

        int[] shortestPathBank = new int[numOfRout];
        int[] nextInLineBank = new int[numOfRout];
        boolean[] bigBeautifulPath = new boolean[numOfRout];

        Arrays.fill(shortestPathBank, infinity);
        Arrays.fill(nextInLineBank, -1);
        shortestPathBank[securityAgent] = 0;

        for(int i = 0; i < numOfRout; i++){
            int temp = -1;

            for(int j = 0; j < numOfRout; j++){
                if(!bigBeautifulPath[j] && (temp == -1 || shortestPathBank[j] < shortestPathBank[temp])) temp = j;
            }


            if(shortestPathBank[temp] == infinity){
                break;
            }

            bigBeautifulPath[temp] = true;

            if(isGatewayRouters[temp] && temp != securityAgent){
                continue;
            }

            for (int temp2 = 0; temp2 < numOfRout; temp2++){
                if(adjMatrix[temp][temp2] != -1 && shortestPathBank[temp] + adjMatrix[temp][temp2] < shortestPathBank[temp2]){
                    shortestPathBank[temp2] = shortestPathBank[temp] + adjMatrix[temp][temp2];
                    nextInLineBank[temp2] = temp;
                }
            }

        }

        int[] toSABank = new int[numOfRout];
        int[] hopToSABank = new int[numOfRout];
        boolean[] bigBeautifulToSA = new boolean[numOfRout];

        Arrays.fill(toSABank, infinity);
        Arrays.fill(hopToSABank, -1);
        toSABank[securityAgent] = 0;

        for(int i = 0; i < numOfRout; i++){
            
            int temp = -1;
            
            for(int j = 0; j < numOfRout; j++){
                
                if(!bigBeautifulToSA[j] && (temp == -1 || toSABank[j] < toSABank[temp])){
                    temp = j;
                }
            }
            
            if(temp == -1 || toSABank[temp] == infinity){
                break;
            }
            
            bigBeautifulToSA[temp] = true;

            if(isGatewayRouters[temp] && temp != securityAgent){
                continue;
            }

            for(int temp2 = 0; temp2 < numOfRout; temp2++){
                
                if(tempTransposing[temp][temp2] != -1 && toSABank[temp] + tempTransposing[temp][temp2] < toSABank[temp2]){
                    
                    toSABank[temp2] = toSABank[temp] + tempTransposing[temp][temp2];
                    hopToSABank[temp2] = temp;
                }
            }
        }
        
        for(int i = 0; i < numOfRout; i++){
            if(isGatewayRouters[i]){
                continue;
            }

            System.out.println("Forwarding Table for " + (i + 1));
            System.out.println("To\tCost\tNext Hop");

            for(int j = 0; j < gatewayRouters.length; j++){
                int myDest = gatewayRouters[j];

                int total = infinity;

                int nextH = -1;

                if(toSABank[i] != infinity && shortestPathBank[myDest] != infinity){
                    total = toSABank[i] + shortestPathBank[myDest];

                    if(i == securityAgent){

                        int current = myDest;

                        while(nextInLineBank[current] != -1 && nextInLineBank[current] != securityAgent){

                            current = nextInLineBank[current];
                        }

                        nextH = current;
                    }
                    else{
                        nextH = hopToSABank[i];
                    }
                }

                if(total >= infinity || nextH == -1){
                    System.out.println((myDest + 1) + "\t-1\t-1");
                }
                else{
                    System.out.println((myDest + 1) + "\t" + total + "\t" + (nextH + 1));
                }
            }

            System.out.println();

        }

    }
}