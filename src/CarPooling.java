import java.util.*;
class CarPooling {

    /*
    Runtime: 5 ms, faster than 59.81% of Java online submissions for Car Pooling.
Memory Usage: 39.2 MB, less than 100.00% of Java online submissions for Car Pooling.


       link: https://leetcode.com/problems/car-pooling/submissions/
       time complexity = O(n*logn)
       space complexity = O(n)
     */
    class Triplets implements Comparable<Triplets>{

        int passengers;
        int time;
        boolean isDrop;

        Triplets(int passengers, int time, boolean isDrop){
            this.passengers = passengers;
            this.time = time;
            this.isDrop = isDrop;
        }

        @Override
        public int compareTo(Triplets that){

            if(this.time == that.time){
                return that.isDrop ? 1 : -1;
            }else{
                return this.time - that.time;
            }

        }

    }

    public boolean carPooling(int[][] trips, int capacity) {

        if(trips.length == 0 || capacity == 0){
            return false;
        }

        List<Triplets> list = new ArrayList<>();

        for(int i=0;i<trips.length;i++){

            list.add(new Triplets(trips[i][0],trips[i][1],false));
            list.add(new Triplets(trips[i][0],trips[i][2],true));

        }


        Collections.sort(list);


        for(Triplets currentStop: list){

            if(currentStop.isDrop){
                capacity += currentStop.passengers;
            }else{

                if (currentStop.passengers > capacity) {
                    return false;
                }

                capacity -= currentStop.passengers;

            }

        }

        return true;
    }

    public static void main(String ars[]){
        int c = 3;
        int[][] a= {
                {2,1,5},
                {3,5,7}
        };

        CarPooling s = new CarPooling();
        System.out.println(s.carPooling(a,c));

    }
}