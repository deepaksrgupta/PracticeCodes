import java.util.*;

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}

public class Main {

    //https://leetcode.com/problems/odd-even-linked-list/
    public ListNode oddEvenList(ListNode head) {

        if(head == null || head.next == null || head.next.next == null){
            return head;
        }

        ListNode oddEnd = null;
        ListNode evenStart = null, evenEnd = null;
        ListNode current = head;
        boolean isEven = false;

        while(current != null) {
            if(isEven){

                if(evenStart == null) {
                    evenStart = current;
                    evenEnd = current;
                }else{
                    evenEnd.next = current;
                    evenEnd = current;
                }

            }else{

                if(oddEnd == null) {
                    oddEnd = current;
                }else{
                    oddEnd.next = current;
                    oddEnd = current;
                }

            }

            current = current.next;
        }

        evenEnd.next = null;
        oddEnd.next = evenStart;

        return head;

    }
    public static void main(String[] args) {

        ListNode head = new ListNode(1);

        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(6);
        head.next.next.next.next.next.next = new ListNode(7);
        head.next.next.next.next.next.next.next = new ListNode(8);

        System.out.println(new Main().oddEvenList(head));

    }
}