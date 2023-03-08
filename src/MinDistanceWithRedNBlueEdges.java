import java.util.*;

public class MinDistanceWithRedNBlueEdges {
    /*
    Given a directed graph with n nodes (0 to n-1)Each edge has color red or blue.
    There can be self-edges or parallel edges
    Return an array answer of length n,
    where each answer[x] is the length of the shortest path from node 0 to node x such
    that the edge colors alternate along the path, or -1 if such a path does not exist.

     Input: n = 3, redEdges = [[0,1],[1,2]], blueEdges = []
    Output: [0,1,-1]Input: 


    n = 5, redEdges = [[0,1],[1,2],[2,3],[3,4]], blueEdges = [[1,2],[2,3],[3,1]]

    Output: [0,1,2,3,7]
     */

    private  int max = 99999;

    public int[] getMinimumDistance(int n, int[][] redEdges, int[][] blueEdges) {

        int[] minDistance  = new int[n];
        Arrays.fill(minDistance, max);
        minDistance[0] = 0;


        //true is blue & false is red
        Set<Pair<Integer, Boolean>> visitedSet = new HashSet<>();
        Queue<Pair<Integer,Pair<Integer,Boolean>>> queue = new LinkedList<>();

        HashMap<Integer, Pair<List<Integer>, List<Integer>>> graph  = buildEdges(redEdges, blueEdges);


        Pair<Integer,Boolean> startingBlueColor = new Pair<>(0, true);
        Pair<Integer,Boolean> startingRedColor = new Pair<>(0, false);

        queue.add(new Pair<>(0, startingBlueColor));
        queue.add(new Pair<>(0, startingRedColor));


        while (!queue.isEmpty()){

            Pair<Integer,Pair<Integer,Boolean>> node  = queue.poll();

            int vertex = node.getKey();
            int minDistanceForNode = node.getValue().getKey();
            boolean currentColor = node.getValue().getValue();


            if(visitedSet.contains(new Pair<>(vertex,currentColor))){
                continue;
            }

            minDistance[vertex] = Math.min(minDistanceForNode, minDistance[vertex]);


            visitedSet.add(new Pair<>(vertex,currentColor));


            //check for other color edges & publish it

            List<Integer> nextNodes = new ArrayList<>();
            if(currentColor){
                //this is blue; publish red
                if(graph.containsKey(vertex)) {
                    nextNodes = graph.get(vertex).getValue();
                }
            }else{
                if(graph.containsKey(vertex)) {
                    nextNodes = graph.get(vertex).getKey();
                }
            }

            for(int nextNode : nextNodes){
                queue.add( new Pair<>(nextNode, new Pair<>(minDistanceForNode + 1, !currentColor)));
            }


        }

        for(int i=0;i < n;i ++){
            if(minDistance[i] == max){
                minDistance[i] = -1;
            }
        }


        return minDistance;
    }

    private HashMap<Integer, Pair<List<Integer>, List<Integer>>> buildEdges(int[][] redEdges, int[][] blueEdges){

        HashMap<Integer, Pair<List<Integer>, List<Integer>>> graph = new HashMap<>();

        buildBlueNRedEdges(true,blueEdges, graph);
        buildBlueNRedEdges(false,redEdges, graph);

        return graph;
    }

    private static void buildBlueNRedEdges(boolean color, int[][] edges, HashMap<Integer, Pair<List<Integer>, List<Integer>>> graph) {
        for(int[] edge: edges){
            if(!graph.containsKey(edges[0])){

                Pair<List<Integer>, List<Integer>> blueNRedEdges = new Pair<>(new ArrayList<>(), new ArrayList<>());
                graph.put(edge[0], blueNRedEdges);
            }

            Pair<List<Integer>, List<Integer>> blueNRedEdges = graph.get(edges[0]);

            List<Integer>  blueEdgesForNode = color ?  blueNRedEdges.getKey() : blueNRedEdges.getValue();
            blueEdgesForNode.add(edge[1]);
        }
    }
}
