import java.util.HashMap;
import java.util.LinkedList;
public class LRU {


    class Pair {
        int key;
        int value;
        Pair(int key, int value){
            this.key = key;
            this.value = value;
        }
    }

    private LinkedList<Integer> dll = new LinkedList<>();
    private HashMap<Integer, Pair>  cache = new HashMap<>();
    private int cacheSize;

    LRU(int cacheSize){
        this.cacheSize = cacheSize;
    }


    //O(1)
    public int get(int key){

        if(cache.containsKey(key)){


            dll.remove(Integer.valueOf(key));

            dll.addLast(key);

            return cache.get(key).value;
        }

        return -1;
    }

    //O(1)
    public void  put(int key, int value){

        if(cache.containsKey(key)){
            //get the node from dll and insert in the back || get ref from cache Pair then call deleteNaddLast()
            Pair currentPair = cache.get(key);

            dll.remove(currentPair.key);
            dll.addLast(currentPair.key);

        }else{

            if(cache.size() == cacheSize){

                //get the first node which is lru || deleteFirst()
                int nodeToRemove = dll.removeFirst();

                cache.remove(nodeToRemove);



            }

            //add in hm
            Pair newEntry = new Pair(key,value);
            cache.put(key,newEntry);


            //add new pair in dll at first location || addFirst();
            dll.addLast(key);

        }
    }



    /*


    1,2,3,4, 5


    cs=3


    3,V
    4,Pair(V,ref)
    5,V

    4,3,5,


     */


    public static void main(String args[]){

        LRU cache = new LRU(4);

        cache.put(1,10);
        cache.put(2,20);
        cache.put(3,30);
        cache.put(4,40);


        System.out.println(cache.get(1));
        System.out.println(cache.get(4));
        //System.out.println(cache.get(1));
        //3,2,1,4


        cache.put(5,50);
        cache.put(6,60);

        System.out.println(cache.get(3));
        System.out.println(cache.get(2));

    }

}
