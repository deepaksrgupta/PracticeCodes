import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class ComparatoExample
{
    static class PQNode{
        String val;
        int count;
        PQNode(String s,int c){
            this.val = s;
            this.count = c;
        }
    }

    public static void main(String args[]){

        PriorityQueue<PQNode> pq  = new PriorityQueue<PQNode>(new Comparator<PQNode>() {
            @Override
            public int compare(PQNode o1, PQNode o2) {

                if(o1.count > o2.count)
                    return 1;
                if(o1.count == o2.count)
                    return 0;
                else return -1;
            }
        });

        ArrayList<PQNode> ll = new ArrayList<>();



        pq.add(new PQNode("g",7000));
        pq.add(new PQNode("h",8000));
        pq.add(new PQNode("i",9000));
        pq.add(new PQNode("e",5000));
        pq.add(new PQNode("f",6000));
        pq.add(new PQNode("c",3000));
        pq.add(new PQNode("d",4000));
        pq.add(new PQNode("a",1000));
        pq.add(new PQNode("b",2000));


        while(pq.size() != 0){
            PQNode t = pq.poll();
            System.out.println(t.count + " "+t.val);
        }

        System.out.println("\nSorted arraylist is given below");

        ll.add(new PQNode("g",7000));
        ll.add(new PQNode("h",8000));
        ll.add(new PQNode("i",9000));
        ll.add(new PQNode("e",5000));
        ll.add(new PQNode("f",6000));
        ll.add(new PQNode("c",3000));
        ll.add(new PQNode("d",4000));
        ll.add(new PQNode("a",1000));
        ll.add(new PQNode("b",2000));

        ll.sort(new Comparator<PQNode>() {
            @Override
            public int compare(PQNode o1, PQNode o2) {
                return o1.count-o2.count;
            }
        });


        for(PQNode t:ll){
            System.out.println(t.count + " "+t.val);
        }
    }

}
