import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class MyMap<K,V> {


    MyMap(int capacity) {
        this.capacity = capacity;
        this.bucket = new ArrayList<>(capacity);
        for(int i = 0; i < capacity; i++){
            this.bucket.add(new ArrayList<>());
        }
    }
    private int capacity;
    private List<ArrayList<Pair<K,V>>> bucket;


    private int getHashCode(K key){
        int hashKey = Objects.hash(key) % capacity;
        return hashKey;
    }

    public boolean containsKey(K key){
        return getIndex(key) == -1 ? false : true;
    }

    private int getIndex(K key){
        int hashKey = getHashCode(key);
        ArrayList<Pair<K,V>> chain = bucket.get(hashKey);

        if (chain == null || chain.isEmpty()){
            return -1;
        }

        int i = 0;
        for(Pair<K,V> pair: chain){
            if (pair.getKey() == key){
                return i;
            }
            i++;
        }

        return -1;
    }

    public V get(K key){

        if(!containsKey(key)){
            return null;
        }

        int hashKey = getHashCode(key);
        ArrayList<Pair<K,V>> chain = bucket.get(hashKey);

        int index = getIndex(key);

        return chain.get(index).getValue();
    }

    public void put(K key, V value){

        int hashKey = getHashCode(key);
        ArrayList<Pair<K,V>> chain = bucket.get(hashKey);

        if(chain == null){
            chain = new ArrayList<>();
            bucket.add(hashKey, chain);
        }

        int index = getIndex(key);
        if(index == -1){
            chain.add(new Pair<K,V>(key, value));
        }else {
            chain.add(index, new Pair<K,V>(key, value));
        }
    }

}

/*
OLD RMS Service   New RMS Service  Pentagon
|
|
|
SEA
|
|
BATMAN   ->  BTS
|         -> Service for payouts
|
MYP     -> for Seller payments reports
|
Automatica -> creating CBE events
|
FABS
|
FLASH -> CBE to 7 segments which is used by Business to track
|
|
USL ->



 */
