import java.util.*;
public class CourseSchedule2 {
    /*
    used khan algorithm for finding topological sort


    Runtime: 6 ms, faster than 54.44% of Java online submissions for Course Schedule II.
    Memory Usage: 40.3 MB, less than 97.56% of Java online submissions for Course Schedule II.
     */
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int inDegree[] = new int[numCourses];
        HashMap<Integer,List<Integer>> edges = new HashMap<>();

        for(int[] edge: prerequisites){
            inDegree[edge[1]]++;

            List<Integer> list = edges.getOrDefault(edge[0],new ArrayList<>());
            list.add(edge[1]);
            edges.put(edge[0],list);
        }

        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> processed = new LinkedHashSet<>();

        //add starting node
        for(int i = 0; i < inDegree.length; i++){
            if(inDegree[i] == 0){
                queue.add(i);
            }
        }

        while(!queue.isEmpty()){

            Integer current = queue.poll();

            processed.add(current);

            if(edges.containsKey(current)){
                for(Integer toVertex: edges.get(current)){
                    inDegree[toVertex]--;
                    if(inDegree[toVertex] == 0){
                        queue.add(toVertex);
                    }
                }
            }

        }

        if(processed.size() == numCourses) {
            int []result = new int[numCourses];
            int i=numCourses-1;
            for(Integer ele: processed){
                result[i--]=ele;
            }
            return result;
        }else{
            return new int[]{};
        }
    }
}
