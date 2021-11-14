import java.util.*;
class BusRoutes {
    public int numBusesToDestination(int[][] routes, int source, int target) {

        HashMap<Integer,HashSet<Integer>> busesWithStops = new HashMap<>();

        for (int i = 0; i < routes.length; ++i) {
            for (int j : routes[i]) {
                if (!busesWithStops.containsKey(j))
                    busesWithStops.put(j, new HashSet<Integer>());
                busesWithStops.get(j).add(i);
            }
        }

        return minBusesToDestination(busesWithStops, source, target);
    }


    public int minBusesToDestination(HashMap<Integer,HashSet<Integer>> busesWithStops,
                                     int sourceStop,
                                     int targetStop
    ) {

        Queue<int[]> queue = new LinkedList<>();


        Set<Integer> visitedStops = new HashSet<>();

        queue.add(new int[] {sourceStop,0});

        while(!queue.isEmpty()){

            int[] currentStop = queue.poll();

            if(currentStop[0] == targetStop){
                return currentStop[1];
            }

            if(visitedStops.contains(currentStop[0])){
                continue;
            }

            visitedStops.add(currentStop[0]);

            HashSet<Integer> adjacentNodes = busesWithStops.get(currentStop[0]);

            if(adjacentNodes == null || adjacentNodes.isEmpty()){
                continue;
            }

            for(Integer stops : adjacentNodes) {

                queue.add(new int[]{stops, currentStop[1]+1});
            }

        }


        return -1;
    }

    public static void main(String[] args) {
        System.out.println(new BusRoutes().numBusesToDestination(

                new int[][]{{1,2,7},{3,6,7}},
                1,
                6
        ));
    }
}