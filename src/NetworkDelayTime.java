import java.util.*;
class NetworkDelayTime {
    /*
    Runtime: 18 ms, faster than 69.29% of Java online submissions for Network Delay Time.
    Memory Usage: 44.7 MB, less than 88.09% of Java online submissions for Network Delay Time.

    https://leetcode.com/problems/network-delay-time/submissions/

    typical Dijkstra's algorithm runs in O(V+E*Log(V))
     */
    public int networkDelayTime(int[][] times, int N, int K) {

        HashMap<Integer,List<Pair>> graph = new HashMap<>();

        for(int[] edge : times){
            List<Pair> edges = graph.getOrDefault(edge[0],new ArrayList<Pair>());
            edges.add(new Pair(edge[1],edge[2]));
            graph.put(edge[0],edges);
        }

        PriorityQueue<Pair> pq = new PriorityQueue<>();

        pq.offer(new Pair(K,0));
        HashMap<Integer,Integer> distance = new HashMap<>();

        while(!pq.isEmpty()){

            Pair currentNode = pq.poll();

            if(distance.containsKey(currentNode.label)) continue;



            distance.put(currentNode.label,currentNode.distance);

            if(graph.containsKey(currentNode.label)){

                for(Pair next : graph.get(currentNode.label)){

                    if(!distance.containsKey(next.label)){

                        pq.offer( new Pair(next.label,currentNode.distance + next.distance));

                    }

                }

            }
        }

        if(distance.size() != N) return -1;

        int ans = 0;
        for (int cand: distance.values())
            ans = Math.max(ans, cand);
        return ans;
    }

    class Pair implements Comparable<Pair>{
        int label;
        int distance;

        Pair(int l, int d){
            label = l;
            distance = d;
        }

        @Override
        public int compareTo(Pair that){
            return this.distance - that.distance;
        }
    }
}