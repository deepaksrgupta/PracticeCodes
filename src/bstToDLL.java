public class bstToDLL
{

    public static void bstToDLL(Node root){
        //return bstToDLL(root,true);
        bstToDllV2(root);

        //below lines make will make it circular dll
        //newRoot.left = prevNode;
        //prevNode.right = newRoot;
    }

    static Node newRoot = null;
    static Node prevNode = null;

    //will work for all cases same as inorder traversal
    public static void bstToDllV2(Node root){
        if(root == null)
            return;

        bstToDllV2(root.left);

        if(prevNode == null) {
            newRoot = root;
        }else{
            prevNode.right = root;
            root.left = prevNode;
        }
        prevNode = root;
        bstToDllV2(root.right);
    }

    //will work for complete bt but not for bt
    public static Node bstToDLL(Node root,boolean isLeft){
        if(root == null){
            return null;
        }

        if(root.left == null && root.right == null){
            return root;
        }

        Node left = bstToDLL(root.left,true&isLeft);
        Node right = bstToDLL(root.right,false&isLeft);

        if(left != null && right != null){
            left.right = root;
            root.left = left;

            right.left = root;
            root.right = right;

            return isLeft? right : left;
        }

        if(left == null){
            if (isLeft) {
                root.right = right;
                right.left = root;
                return right;
            }else{
                right.left = root;
                return right;
            }
        }

        if(right == null){
            if (isLeft) {
                left.right = root;
                return root;
            }else{
                left.left = root;
                root.right = left;
                return left;
            }
        }

        return null;
    }


    public static void main(String args[]){

        Node root = new Node(50);

        root.left = new Node(30);
        root.left.left = new Node(10);
        root.left.left.left = new Node(5);
        root.left.right = new Node(40);

        root.right = new Node(60);
        root.right.left = new Node(55);
        root.right.right = new Node(80);
        root.right.right.left = new Node(75);
        root.right.right.left.left = new Node(72);
        root.right.right.left.right = new Node(78);
        root.right.right.right = new Node(90);

        bstToDLL(root);

        Node temp = newRoot;

        while (temp.right != null){
            System.out.print(temp.data+ " ");
            temp = temp.right;
        }
    }
}
