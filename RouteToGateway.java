import java.util.*;

public class RouteToGateway {

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
    
    }

}