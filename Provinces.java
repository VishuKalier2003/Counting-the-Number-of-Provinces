/* There are n cities... Some of them are connected, while some are not... If city a is connected directly with city b, and city b is connected directly with city c, then city a is connected indirectly with city c... A province is a group of directly or indirectly connected cities and no other cities outside of the group... You are given an n x n matrix isConnected where isConnected[i][j] = 1 if the ith city and the jth city are directly connected, and isConnected[i][j] = 0 otherwise... Return the total number of provinces...
 * Eg 1: graph = [[1,1,0],[1,1,0],[0,0,1]]         Output = 2
 * Eg 2: graph = [[1,0,0],[0,1,0],[0,0,1]]         Output = 3
 * Eg 3: graph = [[1,1,0],[0,1,1],[1,0,1]]         Output = 1
 */
import java.util.*;
public class Provinces
{
    public int NumberOfProvinces(int[][] isConnected)
    {
        List<List<Integer>> vertices = new ArrayList<List<Integer>>();   // Creating the Adjacency List...
        for(int i = 0; i < isConnected.length; i++)
            vertices.add(new ArrayList<Integer>());
        for(int i = 0; i < isConnected.length; i++)
        {
            for(int j = 0; j < isConnected[0].length; j++)
            {   // If the two vertices are connected and it is not a self edge...
                if((isConnected[i][j] == 1) && (i != j))
                    vertices.get(i).add(j);
            }
        }
        int visited[] = new int[isConnected.length];    // Visited array...
        int states = 0;
        for(int i = 0; i < isConnected.length; i++)
        {
            if(visited[i] == 0)   // If the current vertex is not visited...
            {
                states++;     // Incrementing the number of Provinces by one...
                DepthFirstSearch(i, visited, vertices);    // Using DFS on the province to mark its boundary...
            }
        }
        return states;
    }
    public void DepthFirstSearch(int x, int[] visited, List<List<Integer>> vertices)
    {
        visited[x] = 1;    // Making the node visited...
        for(int i: vertices.get(x))
        {
            if(visited[i] == 0)    // If the current node is not visited...
                DepthFirstSearch(i, visited, vertices);   // Calling the next connected node to the previous node...
        }
    }
    public static void main(String args[])
    {
        Scanner sc = new Scanner(System.in);
        int n;
        System.out.print("Enter the number of rows (n) : ");
        n = sc.nextInt();
        int matrix[][] = new int[n][n];
        for(int i = 0; i < matrix.length; i++)
        {
            for(int j = 0; j < matrix[0].length; j++)
            {
                System.out.print("Enter state of "+(i+1)+" row and "+(j+1)+" column : ");
                matrix[i][j] = sc.nextInt();
            }
        }
        Provinces provinces = new Provinces();     // Object creation...
        System.out.println("The Number of Provinces are : "+provinces.NumberOfProvinces(matrix));
        sc.close();
    }
}

// Time Complexity  - O(N^2) time...
// Space Complexity - O(k) space...        k = number of edges...

/* DEDUCTIONS :- 
 * 1. Since we are creating an Adjacency list, we can simply iterate over one list at a time via Depth-First-Search to ascertain that those nodes are visited...
 * 2. When the DFS Algorithm has no vertex left to explore further, the Depth First Search ends and it marks one Province...
*/