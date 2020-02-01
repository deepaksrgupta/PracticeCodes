import java.util.LinkedList;
import java.util.Queue;

public class GetMaxInConstantTimeQueue {
    Queue<Integer> queue = new LinkedList<>();
    LinkedList<Integer> maxList = new LinkedList<>();

    public void enqueue(int x){
        queue.add(x);

        if(maxList.size() > 0) {
            Integer first = maxList.getFirst();
            if (x > first) {
                maxList = new LinkedList<>();
                maxList.addLast(x);
            }else{
                Integer last = maxList.getLast();

                if(last.equals(x) || x < last){
                    maxList.addLast(x);
                }else if(x > last){
                    maxList.removeLast();
                    maxList.addLast(x);
                }
            }
        }else{
            maxList.addLast(x);
        }
    }

    public Integer dequeue(){
        Integer first  = queue.poll();

        if(first != null && first.equals(maxList.getFirst())){
            maxList.removeFirst();
        }

        return first;
    }


    public Integer getMax(){
        if(maxList.isEmpty()){
            return -1;
        }

        return maxList.getFirst();
    }
}

/*
    public static void main(String args[]) {

        GetMaxInConstantTimeQueue queue = new GetMaxInConstantTimeQueue();

        queue.enqueue(4);
        queue.enqueue(3);
        queue.enqueue(4);
        queue.enqueue(3);
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        queue.enqueue(1);
        queue.enqueue(3);
        System.out.println(queue.getMax());
        queue.enqueue(9);
        queue.enqueue(20);
        queue.dequeue();
        queue.dequeue();
        System.out.println(queue.getMax());
        queue.enqueue(8);
        System.out.println(queue.getMax());
        queue.enqueue(4);
        System.out.println(queue.getMax());

    }
 */
