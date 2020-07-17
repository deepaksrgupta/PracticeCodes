import java.util.*;
public class TopKElements {
    public int[] topKFrequent(int[] nums, int k) {

        if(nums == null || nums.length == 0 || k == 0){
            return new int[]{};
        }


        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>(){

            @Override
            public int compare(int[] first, int[] second){
                return second[1]-first[1];
            }
        });


        Map<Integer,Integer> counts = new HashMap<>();


        for(int e : nums){
            int count = counts.getOrDefault(e,0);
            counts.put(e,count+1);
        }

        for(Map.Entry<Integer,Integer> entry : counts.entrySet()){

            pq.add(new int[]{entry.getKey(),entry.getValue()});
        }

        int []result = new int[k];
        for(int i=0; i< k; i++){
            result[i] = pq.poll()[0];
        }

        return result;
    }
}
