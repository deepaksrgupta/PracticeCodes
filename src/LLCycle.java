public class LLCycle {
    public static Node findCycleStart(Node head){
        Node slow = head;
        Node fast = head;

        while(fast.right != null && fast.right.right != null) {

            slow = slow.right;

            fast = fast.right.right;


            if(fast == slow) {
                break;
            }
        }


        slow = head;

        while (slow.data != fast.data) {
            slow = slow.right;
            fast = fast.right;
        }

        return slow;
    }

    public static void main(String args[]) {
        Node head = new Node(1);
        head.right = new Node(2);
        Node cirle = new Node(3);
        head.right.right = cirle;
        head.right.right.right = new Node(4);
        head.right.right.right.right = new Node(5);
        head.right.right.right.right.right = new Node(6);
        head.right.right.right.right.right.right = new Node(7);
        head.right.right.right.right.right.right.right = cirle;



        Node CycleStart = findCycleStart(head);

        System.out.println(CycleStart.data);
    }
}
