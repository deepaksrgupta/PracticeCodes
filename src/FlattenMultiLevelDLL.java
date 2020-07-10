import java.util.*;
/*
https://leetcode.com/submissions/detail/364735406/?from=/explore/challenge/card/july-leetcoding-challenge/545/week-2-july-8th-july-14th/3386/

works in O(n)
with space O(number of levels)
 */
public class FlattenMultiLevelDLL {
    static class Node {
        public int val;
        public Node prev;
        public Node next;
        public Node child;
        Node(int v){
            val = v;
        }
    }

    public Node flatten(Node head) {
        if(head == null || (head.child == null && head.next == null)){
            return head;
        }


        Stack<Node> stack = new Stack<>();

        Node current = head;
        Node previousNode = null;

        while(current != null || !stack.isEmpty()){

            if(current == null){
                Node previousLevel = stack.pop();

                previousLevel.prev = previousNode;
                previousNode.next = previousLevel;

                current = previousLevel;
            }

            if(current.child != null){
                current.child.prev = current;

                if(current.next != null){
                    stack.push(current.next);
                }

                current.next = current.child;
                current.child = null;
            }

            previousNode = current;
            current = current.next;

        }


        return head;
    }

    public static void main(String args[]){
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.prev = head;

        head.next.next = new Node(3);
        head.next.next.prev = head.next;

        head.next.child = new Node(7);

        Node res = new FlattenMultiLevelDLL().flatten(head);
    }
}
