import java.util.*;

public class Main {

    public static ListNode reverseKGroup(ListNode head, int k) {

        ListNode result = head;
        ListNode start = result;
        ListNode end = result;
        int n = k;

        while(end != null){

            //traverse n distance
            start  = end;
            while(end.next != null && n != 0){
                end = end.next;
                n--;
            }

            //reverse
            if(n == 0){

                while (start != end){


                }

            }

            n = k;
        }

        return result;
    }

    public static void main(String[] args) {

        ListNode first = new ListNode(1);
        first.next = new ListNode(2);
        first.next.next = new ListNode(3);
        first.next.next.next = new ListNode(4);
        first.next.next.next.next = new ListNode(5);
        //first.next.next.next.next.next = new ListNode(6);

        first = reverseKGroup(first,2);

        while (first != null)
        {
            System.out.print(first.val+" ");
            first = first.next;
        }

    }
}