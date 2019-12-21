public class maxBSTinBT
{
    static class  TempValue{
        int max;
        int min;
        int count;
        boolean isBst;
        Node bstRoot;
        TempValue(int max,int min,boolean isBst,int count,Node bstRoot){
            this.max = max;
            this.min = min;
            this.isBst = isBst;
            this.count = count;
            this.bstRoot = bstRoot;
        }
    }

    static TempValue  maxSizeOfBSTInBT(Node root){

        if (root == null)
            return new TempValue(0,0,true,0,null);

        if (root.left == null && root.right == null)
            return new TempValue(root.data,root.data,true,1,root);


        TempValue left = maxSizeOfBSTInBT(root.left);
        TempValue right = maxSizeOfBSTInBT(root.right);

        if (left.isBst && right.isBst && left.max < root.data && right.min >= root.data){
            left.min = Math.min(root.data,Math.min(left.min,right.min));
            left.max = Math.max(root.data,Math.max(left.max,right.max));
            left.count = left.count+right.count+1;
            left.bstRoot = root;
            return left;
        }

        if (left.max > right.max)
            return left;
        else return right;
    }

    static void inorder(Node current){
        if(current != null){
            inorder(current.left);
            System.out.print(current.data+" ");
            inorder(current.right);
        }
    }

    public static void main(String args[]){
        Node root = new Node(50);
        root.left = new Node(30);
        root.right = new Node(60);
        root.left.left = new Node(5);
        root.left.right = new Node(20);
        root.right.left = new Node(45);
        root.right.right = new Node(70);
        root.right.right.left = new Node(65);
        root.right.right.right = new Node(80);

        TempValue result = maxSizeOfBSTInBT(root);

        System.out.println(result.count);

        inorder(result.bstRoot);
    }
}