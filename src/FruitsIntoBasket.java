import java.util.*;

//https://leetcode.com/problems/fruit-into-baskets/
class FruitsIntoBasket {
    public int totalFruit(int[] fruits) {

        if(fruits.length == 0){
            return 0;
        }

        HashMap<Integer,Integer> previous2FruitsCount = new HashMap<>();

        int max2Fruits = 0;
        previous2FruitsCount.put(fruits[0], 1);

        for(int i = 1; i < fruits.length; i++){


            if(previous2FruitsCount.containsKey(fruits[i])){
                previous2FruitsCount.put(fruits[i], previous2FruitsCount.get(fruits[i]) + 1);
            }else {

                if(previous2FruitsCount.size() == 2){

                    //size is 2 -> sum the total count and record in max ->
                    //get previous key and remove all others

                    int fruitCount = getFruitSum(previous2FruitsCount);
                    max2Fruits = Math.max(max2Fruits, fruitCount);

////                    //delete the fruit which will not contribute to fullest
                    Iterator<Integer> iterator = previous2FruitsCount.keySet().iterator();

                    while (iterator.hasNext()){
                        int fruit = iterator.next();
                        if(fruit != fruits[i-1]){
                            iterator.remove();
                        }
                    }

                    // previous2FruitsCount.clear();
                    // previous2FruitsCount.put(fruits[i-1], 1);

                }

                previous2FruitsCount.put(fruits[i], 1);


            }

        }



        max2Fruits = Math.max(max2Fruits, getFruitSum(previous2FruitsCount));

        return max2Fruits;
    }

    private int getFruitSum(HashMap<Integer, Integer> previous2FruitsCount){
        int sum = 0;
        for(Integer fruit : previous2FruitsCount.values()){
            sum += fruit;
        }

        return sum;
    }
}
