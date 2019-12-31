import java.util.Random;
import java.util.Stack;

public class ZigZapBT
{

    static void insertNode(int data){
        if (root == null)
        {
            root = new Node(data);
            return;
        }

        Node parent = null;
        Node current = root;

        while (current != null){
            if(data < current.data){
                parent = current;
                current = current.left;
            }else{
                parent = current;
                current = current.right;
            }
        }

        if (parent.data > data)
            parent.left = new Node(data);
        else{
            parent.right = new Node(data);
        }
        return;
    }

    static void inorder(Node current){
        if(current != null){
            inorder(current.left);
            System.out.print(current.data+" ");
            inorder(current.right);
        }
    }

    public static Node root = null;

    static void zigzagPrint(Node current) {
        Stack<Node> st1 = new Stack<>();
        Stack<Node> st2 = new Stack<>();

        st1.push(current);
        boolean currentStack = true;

        while (!st1.isEmpty() || !st2.isEmpty()){
            if(currentStack) {
                while (!st1.isEmpty()){
                    Node temp = st1.pop();
                    System.out.print(temp.data+" ");
                    if(temp.left != null)
                        st2.push(temp.left);
                    if(temp.right != null)
                        st2.push(temp.right);
                }
            }else{
                while (!st2.isEmpty()){
                    Node temp = st2.pop();
                    System.out.print(temp.data+" ");
                    if(temp.right != null)
                        st1.push(temp.right);
                    if(temp.left != null)
                        st1.push(temp.left);
                }
            }
            System.out.println("");
            currentStack = !currentStack;
        }
    }

    public static void inorderIterative(Node root){
        if(root == null){
            return;
        }

        Stack<Node> st = new Stack<>();

        Node current  = root;
        boolean isStack = false;
        while (current != null){

            if(isStack){
                System.out.print(current.data+ " ");

                if(current.right != null){
                    current = current.right;
                    isStack = false;
                }else{
                    if(st.size() > 0){
                        current = st.pop();
                        isStack = true;
                    }else{
                        current = null;
                    }
                }
                continue;
            }

            if(current.left != null && current.right != null){
                st.push(current);
                current = current.left;
                isStack = false;
                continue;
            }

            if(current.left == null && current.right == null){
                System.out.print(current.data+ " ");
                if(st.size()>0){
                    current = st.pop();
                    isStack = true;
                }else{
                    current = null;
                }
                continue;
            }

            if(current.left != null){
                st.push(current);
                current = current.left;
                isStack = false;
                continue;
            }

            if(current.right != null){
                System.out.print(current.data+ " ");
                current = current.right;
                isStack = false;
            }
        }
    }

    public static void preOrderItr(Node root) {
        if(root == null){
            return;
        }

        Node current = root;
        Stack<Node> stack = new Stack<>();

        while (current != null || stack.size() != 0){
            if(current != null){
                System.out.print(current.data+ " ");
                if(current.left != null){
                    stack.push(current);
                    current = current.left;
                }else{
                    current = null;
                }
            }else{
                current = stack.pop();
                if(current.right != null){
                    current = current.right;
                }else{
                    current = null;
                }
            }
        }
    }

    void print2D(Node root, int space,int indent)
    {
        // Base case
        if (root == null)
            return;

        // Increase distance between levels
        space += indent;

        // Process right child first
        print2D(root.right, space,indent);

        // Print current node after space
        // count
        System.out.println();
        for (int i = indent; i < space; i++)
            System.out.print(" ");
        System.out.println(root.data+"\n");

        // Process left child
        print2D(root.left, space,indent);
    }

    public static void main(String args[]){
        ZigZapBT btZigZag = new ZigZapBT();

        btZigZag.insertNode(5);
        btZigZag.insertNode(3);
        btZigZag.insertNode(8);
        btZigZag.insertNode(1);
        btZigZag.insertNode(2);
        btZigZag.insertNode(9);
        btZigZag.insertNode(6);
        btZigZag.insertNode(7);
        btZigZag.insertNode(4);
        btZigZag.insertNode(-1);
        btZigZag.insertNode(10);
        btZigZag.insertNode(11);
        btZigZag.insertNode(-2);

        btZigZag.zigzagPrint(btZigZag.root);

        btZigZag.print2D(btZigZag.root,0,4);
    }

}