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
            
            for(int j = 0; i < numOfRout; j++){
                
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
    }

}