import java.util.*;
class Edge
{
    int source,cost, dest;


    public Edge(int source, int dest) {
        this.source = source;
        this.dest = dest;
    }

    public Edge(int source, int dest,int cost) {
        this.source = source;
        this.dest = dest;
        this.cost = cost;
    }
};

public class Graph {

    List<List<Integer>> adjList = null;

    int vertices = 0;

    HashMap<String,Integer> costs = new HashMap<>();

    Graph(List<Edge> edges, int N, boolean isDirected)
    {
        vertices = N;
        adjList = new ArrayList<>(vertices);

        for (int i = 0; i < vertices; i++) {
            adjList.add(i, new ArrayList<>());
        }

        for (int i = 0; i < edges.size(); i++)
        {
            int src = edges.get(i).source;
            int dest = edges.get(i).dest;

            adjList.get(src).add(dest);
            costs.put(getCostKey(src,dest),edges.get(i).cost);

            if (!isDirected) {
                adjList.get(dest).add(src);
                costs.put(getCostKey(dest,src),edges.get(i).cost);
            }
        }
    }

    public String getCostKey(int start, int dest){
        return start+" "+dest;
    }

}
