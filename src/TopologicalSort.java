import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class TopologicalSort {

    /*
    uses dfs with O(v+e) and O(v) extra space

    we can also use khans algorithm to maintain in degree array and output vertices with no indegree
     */
    public List<Integer> topologicalSortUsingDFS(Graph g) {

        Stack<Integer> stack = new Stack<>();


        boolean visited[] =  new boolean[g.vertices];


        for(int i = 0;i < g.vertices; i++){
            if(!visited[i]){
                topologicalSortUsingDFSUtil(g,visited,i,stack);
            }
        }


        List<Integer> result = new ArrayList<>();

        while (!stack.isEmpty()){
            result.add(stack.pop());
        }

        return result;
    }


    private void topologicalSortUsingDFSUtil(Graph g,boolean []visited, int current, Stack<Integer> output) {

        visited[current] = true;


        for (Integer i : g.adjList.get(current)) {

            if(!visited[i]){
                topologicalSortUsingDFSUtil(g,visited,i,output);
            }
        }

        output.push(current);
    }


    static public void main( String args[] ) {

        List<Edge> edges = Arrays.asList(
                new Edge(0, 1,3), new Edge(0, 2,2), new Edge(0, 3,3),
                new Edge(1, 2,4), new Edge(1, 3,2), new Edge(2, 3,4)
        );

        int vertices = 4;

        Graph g = new Graph(edges,vertices,false);

        for(Integer i: new TopologicalSort().topologicalSortUsingDFS(g)){
            System.out.print(i+" ");
        }

    }
}
