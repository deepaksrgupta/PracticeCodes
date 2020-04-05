import java.util.List;
import java.util.PriorityQueue;

public class MergeKSortedList {
    class Node implements Comparable<Node>{
        int val;
        Node next;

        Node(int val) {
            this.val = val;
        }

        @Override
        public int compareTo(Node that){
            return this.val - that.val;
        }

    }

    Node MergeKSortedLists(List<Node> list) {
        // your code goes here
        PriorityQueue<Node> pq = new PriorityQueue<>();


        for(Node currentNode : list) pq.add(currentNode);


        Node result = new Node(0);

        while(!pq.isEmpty()){

            Node minNode = pq.poll();

            result.next = minNode;


            if(minNode.next != null)  pq.add(minNode.next);

        }


        return result.next;
    }
}
