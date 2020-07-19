import java.util.*;
class CheapestFlightsWithKStops {
    /*
    Runtime: 13 ms, faster than 57.09% of Java online submissions for Cheapest Flights Within K Stops.
Memory Usage: 43 MB, less than 11.14% of Java online submissions for Cheapest Flights Within K Stops.
     */
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {


        HashMap<Integer,List<Pair>> graph = new HashMap<>();

        for(int i = 0; i < flights.length; i++){

            graph.putIfAbsent(flights[i][0],new ArrayList<Pair>());

            graph.get(flights[i][0]).add( new Pair(flights[i][1],flights[i][2]));

        }

        Queue<int[]> queue = new LinkedList<>();

        int minDistance = 100000;

        queue.add(new int[]{src,0,0});

        int steps = 0;

        while(!queue.isEmpty()){

            int qSize = queue.size();


            for(int i=0; i< qSize; i++){
                int[] vertex = queue.poll();

                if(graph.containsKey(vertex[0]) && vertex[2] <= K){

                    for(Pair neighbour: graph.get(vertex[0])){

                        if(neighbour.to == dst){
                            minDistance = Math.min(minDistance,vertex[1]+neighbour.cost);
                        }else{

                            //pruning search
                            if(neighbour.cost+ vertex[1] > minDistance){
                                continue;
                            }

                            queue.add(new int[]{neighbour.to,neighbour.cost+ vertex[1], vertex[2] + 1});
                        }


                    }

                }
            }

            //break out of bfs if for level k is not found
            if(steps++ > K) break;
        }


        return minDistance == 100000 ? -1 : minDistance;

    }

    class Pair {
        int to;
        int cost;

        Pair(int to, int cost){
            this.to = to;
            this.cost = cost;
        }

        public void setCost(int cost){
            this.cost = cost;
        }

    }
}
