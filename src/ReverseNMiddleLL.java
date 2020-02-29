import java.util.Stack;

public class ReverseNMiddleLL
{
    static public class LNode {
        int data;
        LNode next;
        LNode(int data){
            this.data = data;
            next = null;
        }
    }

    public static void printLL(LNode node){
        if(node == null)
            return;
        System.out.print(node.data+ " ");
        printLL(node.next);
    }

    public static LNode reverseLLRecursive(LNode head,LNode reversed) {
        if(head == null){
            return  reversed;
        }

        LNode current = head;
        head = head.next;
        current.next = reversed;
        return reverseLLRecursive(head,current);
    }

    public static LNode reverseLL(LNode node){

        if(node == null)
            return null;

        LNode root,current,previous,nextNode;
        current = null;
        previous = null;
        nextNode = node;


        while (nextNode != null){
            previous = current;
            current = nextNode;
            nextNode = current.next;
            current.next = previous;
        }

        root  = current;
        return root;
    }


    public static void checkLLPalindrome(LNode head){

        if(head== null)
            return;
        LNode first = head;
        LNode second = head;

        Stack<LNode> st  = new Stack<>();

        while (second != null && second.next != null){

            st.push(first);
            first = first.next;
            second = second.next.next;
        }

        if(second != null){
            first = first.next;
        }


        while(first != null){

            if (first.data != st.peek().data){
                System.out.println("Not a palindrome");
                return;
            }

            st.pop();

            first = first.next;
        }

        //System.out.println(first.data);
        System.out.println("ll is palindrome");

    }

    public static void main(String args[]) {
//        LNode head = new LNode(1);
//        LNode last = head;
//
//        int count = 1;
//        while (count != 10){
//            count++;
//            last.next = new LNode(count);
//            last = last.next;
//        }
//
//
//        //printLL(head);
//        LNode result = reverseLL(head);
//        printLL(result);

        LNode head = new LNode(1);
        head.next = new LNode(2);
        head.next.next = new LNode(3);
        head.next.next.next = new LNode(3);
        head.next.next.next.next = new LNode(2);
        head.next.next.next.next.next = new LNode(1);
        head.next.next.next.next.next.next = new LNode(1);

        LNode result = reverseLL(head);
        printLL(result);
        System.out.println();
        result = reverseLLRecursive(result,null);
        printLL(result);

        //checkLLPalindrome(head);

    }
}
