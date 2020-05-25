//https://leetcode.com/problems/redundant-connection/
/*
time complexity is O(n) i.e number of edges

Runtime: 1 ms, faster than 68.34% of Java online submissions for Redundant Connection.
Memory Usage: 39.7 MB, less than 63.64% of Java online submissions for Redundant Connection.
 */
public class findEdgeWhichMakesCycle {

    int maxValue = 10000;
    public int[] findRedundantConnection(int[][] edges) {

        DisjointSetConstantTime dsu = new DisjointSetConstantTime(maxValue);

        for(int[] e : edges){
            if(!dsu.union(e[0],e[1])){
                return new int[]{e[0],e[1]};
            }
        }

        return null;
    }

}
