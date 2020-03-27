
class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}

public class makeListOddEven {

    //https://leetcode.com/problems/odd-even-linked-list/
    // O(n) with no space
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
    public ListNode getInitLL(){
        ListNode head = new ListNode(1);

        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(6);
        head.next.next.next.next.next.next = new ListNode(7);
        head.next.next.next.next.next.next.next = new ListNode(8);

        return head;
    }

    public static void main(String[] args) {

        makeListOddEven oddEven = new makeListOddEven();
        ListNode head = oddEven.getInitLL();

        System.out.println(oddEven.oddEvenList(head));

    }
}
