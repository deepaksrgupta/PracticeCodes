import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class HamiltonianPaths {

    /*
       just  recursive solution and complexity is O(n-1!)
       we can throw in hash map of dimension from start and visited array states leading up to O(n*n*2^n) solution
     */
    public static void findHamiltonianPathsRecursive(Graph g,int start, int verticesCount){

        List<Integer> path = new ArrayList<>();
        boolean visited[]  = new boolean[verticesCount];


        path.add(start);
        visited[start] = true;


        hamiltonianHelper(g,start,path,visited,verticesCount);
    }

    public static void hamiltonianHelper(Graph g, int start, List<Integer> path, boolean visited[], int verticesCount){

        if(path.size() == verticesCount){

            path.forEach(x->System.out.print(x+" "));
            System.out.println();
            return;
        }

        for(Integer current : g.adjList.get(start)){

            if(!visited[current]){

                visited[current] = true;
                path.add(current);

                hamiltonianHelper(g,current,path,visited,verticesCount);

                visited[current] = false;
                path.remove(path.size()-1);
            }

        }

    }


    /*

        Travelling salesman solution O(n*n*2^n)
        n*2^n total number of sub problems and we do computation of n for each sub problem

       we can throw in hashMap to cache the subproblems computations
       (start,mask) =  for all i $not in mask do min(i,mask|i)
       O(n*n*2^n)
     */
    public static int findMinHamiltonianPathTD(Graph g, int start){
        HashMap<String,Integer> cache = new HashMap<>();
        return helperTD(g,start,start,1<<start, cache);
    }


    public static int max = 100000;
    public static int helperTD(Graph g, int start,int returnTo,int visited,HashMap<String,Integer> cache){

        if(cache.containsKey(g.getCostKey(start,visited))){
            return cache.get(g.getCostKey(start,visited));
        }

        int result = max;

        //base case to return to starting node if all nodes are visited
        if(visited == ((1<<g.vertices) - 1)){

            if(g.costs.containsKey(g.getCostKey(start,returnTo))){
                return g.costs.get(g.getCostKey(start,returnTo));
            }

            return max;
        }



        for(Integer currentVertex : g.adjList.get(start)){

                int mask = 1 << currentVertex;
                if ((visited & mask) != mask){
                    result = Math.min(result,helperTD(g,currentVertex,returnTo,visited | mask,cache) + g.costs.get(g.getCostKey(start,currentVertex)));
                }
        }

        cache.put(g.getCostKey(start,visited),result);

        return result;
    }


    public static void main(String[] args) {
        List<Edge> edges = Arrays.asList(
                new Edge(0, 1,3), new Edge(0, 2,2), new Edge(0, 3,3),
                new Edge(1, 2,4), new Edge(1, 3,2), new Edge(2, 3,4)
        );

        int vertices = 4;

        Graph g = new Graph(edges,vertices,false);

        findHamiltonianPathsRecursive(g,0,vertices);


        System.out.println(findMinHamiltonianPathTD(g,0));
    }
}
