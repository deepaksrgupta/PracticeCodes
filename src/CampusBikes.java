
/*
https://leetcode.com/problems/campus-bikes-ii/submissions/
https://leetcode.com/submissions/detail/573706872/
 */

import java.util.HashMap;

public class CampusBikes {
    public int assignBikes(int[][] workers, int[][] bikes) {

        return minimumDistance(workers, bikes, 0, 0, new HashMap<>());

    }

    public int minimumDistance(int[][] workers, int[][] bikes, int wi, int bikesTaken, HashMap<String, Integer> cache){

        if(wi >= workers.length){
            return 0;
        }


        String cacheKey = wi + " " + bikesTaken;

        if(cache.containsKey(cacheKey)){
            return cache.get(cacheKey);
        }


        int min = 1_000_000_000;

        for(int i = 0; i < bikes.length; i++){

            int pos = 1 << i;

            if((bikesTaken & pos) != pos){

                int subResult = minimumDistance(workers, bikes, wi+ 1, bikesTaken | pos, cache) +

                        Math.abs(bikes[i][0] - workers[wi][0]) + Math.abs(bikes[i][1] - workers[wi][1]);

                min = Math.min(min, subResult );

            }

        }

        cache.put(cacheKey, min);

        return min;

    }
}


