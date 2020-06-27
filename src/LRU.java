import java.util.*;
class LRU {

    LinkedHashMap<Integer,Integer> cache;
    int cacheSize;


    public LRU(int capacity) {
        cache = new LinkedHashMap<>();
        cacheSize = capacity;
    }

    public int get(int key) {

        if(!cache.containsKey(key)){
            return -1;
        }

        int value = cache.get(key);
        cache.remove(key);
        cache.put(key,value);

        return value;
    }

    public void put(int key, int value) {

        //update the key if key exists 
        if(cache.containsKey(key)){
            cache.remove(key);
        }


        //if size is full then remove the first element
        if(cache.size() == this.cacheSize){
            for(Map.Entry<Integer,Integer> entry : cache.entrySet()){
                cache.remove(entry.getKey());
                break;
            }
        }


        //add the new item
        cache.put(key,value);
    }
}

/*
1. requirement func and non func
2. capacity and constraints
3. API design
4. Data model design
5. High level design
6. detail component design
7. address bottleneck or performance issues or SPOF


1. requirement func and non func

func
1. Sending a notification; inapp or mail
2. For added products in cart we should send notify the user for product changes

non func
1. Consistent
2. Available


2. capacity and constraints

total user 100M
daily active users 10M


3. API design

getNotification(apiKey: string, userId: string, skip: int, limit: int): Array<Notification> | empty
markNotificationAsRead(apiKey: string, userId: string, notificationIds: Array<Id>) : bool
changeProductPrice() :

4. Data model design

Cart
UserId : 4B
//Products: Array<ProductId> 20B
ProductId: 4B

total size = 25B * 10M => 250MB => 400MB

Notification
Id : 4B
UserId : 4B
On: 4B (DT) 4B
ProductDiffId: 4B

ProductDiff
ProductID:  4B
PriceDiff: 4B
On:
//ProductIds: Array<Id>   20B
//PricesDiff: Array<Int>  20B

total size = 50B * 10M => 500MB


5. High level design
                  ->admin changes ->>>>>>>>>>>  API() ->           -> message broker[N] -> notificationService[N]
client          -> LB[N] -> Web server[N] ->LB[N] -> Application Server[N] -> Sql database[M/S]
                                            -> WS                   -> M
                                            -> RS                   -> S
App or desktop app

6. detail component design
 done


 7. address bottleneck or performance issues or SPOF



1,1
2,2
3,3
4,4
5,5


1,1
2,2
3,3
4,4
5,5
6,6
7,7
8,8
10,2.5
9,2.25


index data structure
1,
2,
2.2222222221
2.25
2.5


 */