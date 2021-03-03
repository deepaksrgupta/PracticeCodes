import java.io.*;
        import java.util.*;
        import java.text.*;
        import java.math.*;
        import java.util.regex.*;
        import java.time.*;
        import java.util.concurrent.*;

public class ThreadSafeLRUCache {

    //12 : 45
    public static void main(String args[] ) throws Exception {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT */
        //static inputs ,capacity use run code

        ThreadSafeLRUCache lruCache = new ThreadSafeLRUCache(2);

        lruCache.set(1,1);
        //lruCache.printDll();

        lruCache.set(2,2);
        //lruCache.printDll();

        System.out.println(lruCache.get(1));
        //lruCache.printDll();

        lruCache.set(3, 3);

        //lruCache.set(2, 9);
        //lruCache.printDll();

        System.out.println(lruCache.get(2));
        //lruCache.printDll();

        //lruCache.set(4, 4);
        //lruCache.printDll();
        System.out.println(lruCache.get(1));
        //lruCache.printDll();

        System.out.println(lruCache.get(3));
        //lruCache.printDll();

        System.out.println(lruCache.get(4));
        //lruCache.printDll();

    }

    private HashMap<Integer, DLLNode> cache;
    private Integer capacity;
    private DLLNode head;
    private Integer dllSize;
    private Semaphore getLock, deleteLock,setLock;


    ThreadSafeLRUCache(int size){
        this.cache = new HashMap<>(size);
        this.capacity = size;

        this.getLock = new Semaphore(1);
        this.setLock = new Semaphore(1);
        this.deleteLock = new Semaphore(1);
    }


    //lru + thread safe

    //check in hashmap whether the key exists or not
    //if the key exists then get ref of DLL node and return the value at that point
    //have a lock for dll modify
    public Integer get(Integer key) throws InterruptedException{

        if(cache.containsKey(key)){

            DLLNode node = cache.get(key);

            //check if entry is expired  or not
            if(node.expiry != null && node.expiry.before(Date.from(Instant.now()))){

                deleteLock.acquire();

                if(cache.containsKey(key)){

                    cache.remove(key);
                    deleteDllNode(node);
                }

                deleteLock.release();

                return null;

            }else {


                getLock.acquire();

                deleteDllNode(node);
                addFirstDllNode(node);

                getLock.release();

                return node.value;

            }

        }

        return null;
    }

    /*
    dll node
    */
    public void set(Integer key,Integer value) throws InterruptedException{
        set(key, value, -1);
    }

    //expiry - seconds
    //lazily evicts keys from our cache

    //todo : if time implem deamon thread
    public void set(Integer key,Integer value,Integer expiry) throws InterruptedException {



        setLock.acquire();

        //if cache size is full then remove a entry
        if(this.cache.size() >= this.capacity){

            DLLNode lastNode = this.head.prev;
            deleteDllNode(lastNode);
            this.cache.remove(lastNode.key);
        }


        Date expiryTime = expiry == -1 ? null : Date.from(Instant.now().plusSeconds(expiry));

        if(cache.containsKey(key)){

            DLLNode node = cache.get(key);
            node.value = value;
            node.expiry = expiryTime;

        }else {

            DLLNode node = new DLLNode(key, value, expiryTime);
            cache.put(key, node);
            addFirstDllNode(node);
        }

        //check if key exist then modify the dll node value like expiry,value

        //if key does not exists then insert

        setLock.release();

    }

    public class DLLNode {
        Integer value;
        Integer key;
        Date expiry;

        DLLNode prev;
        DLLNode next;


        DLLNode(Integer key, Integer value, Date expiry){
            this.key = key;
            this.value = value;
            this.expiry = expiry;
        }
    }

    //later implement dll functions to delete Node and insert in begining
    private void deleteDllNode(DLLNode node){


        if(node.next == node && node.prev == node && node == this.head){
            this.head = null;

            node.prev = null;
            node.next = null;
        }else {

            //System.out.println(node.value);

            DLLNode prev = node.prev;
            DLLNode next = node.next;

            //System.out.println(prev);
            //System.out.println(next);


            prev.next = next;
            next.prev = prev;

            if(head.prev == node){
                head.prev = prev;
            }

            node.prev = null;
            node.next = null;

            if(node == this.head){
                this.head = next;
            }
        }

    }

    private void addFirstDllNode(DLLNode node){

        if(this.head == null){
            this.head = node;
            this.head.prev = this.head;
            this.head.next = this.head;
        }else {

            DLLNode last = this.head.prev;

            node.next = this.head;

            this.head = node;

            this.head.prev = last;

            last.next = head;
            //if only one node is present
            if(last.prev == last){
                last.prev = head;
            }
        }

    }

    private void printDll() {

        DLLNode itr = head;

        System.out.println("\nBelow is DLL . cache size is " + this.cache.size() + " \n");
        for(Integer i : this.cache.keySet()){
            System.out.print(i + " ");
        }

        System.out.println();
        do {

            System.out.print(itr.value + " -> ");

        }while(itr != head);


    }
}

