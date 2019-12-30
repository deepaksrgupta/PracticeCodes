import java.util.Random;
import java.util.Stack;

public class Main
{
    public static void inorder(Node current){
        if(current!= null){
            inorder(current.left);
            System.out.print(current.data+ " ");
            inorder(current.right);
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

    public static void main(String args[]) {
        ZigZapBT btZigZag = new ZigZapBT();

        Random r = new Random();
        btZigZag.insertNode(50);

        while (r.nextInt(100) != 80){
            btZigZag.insertNode(r.nextInt(100));
        }

        inorder(btZigZag.root);
        System.out.println();
        inorderIterative(btZigZag.root);
    }
}