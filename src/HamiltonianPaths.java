import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HamiltonianPaths {

    /*
       just  recursive solution and complexity is O(n-1!)


       we can throw in hashMap to cache the subproblems computations
       (start,mask) =  for all i $not in mask do min(i,mask|i)
       O(n*n*2^n)


     */
    public static void findHamiltonianPaths(Graph g,int start, int verticesCount){

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

    public static void main(String[] args) {
        List<Edge> edges = Arrays.asList(
                new Edge(0, 1), new Edge(0, 2), new Edge(0, 3),
                new Edge(1, 2), new Edge(1, 3), new Edge(2, 3)
        );

        int vertices = 4;

        Graph g = new Graph(edges,4);

        findHamiltonianPaths(g,0,4);
    }
}
