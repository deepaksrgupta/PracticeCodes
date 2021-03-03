import java.util.Scanner;

public class HuffmanDecode {
    static class Node {
        char val;
        Node left;
        Node right;

        Node(char val){
            this.val = val;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = "";
        while (sc.hasNext()) {
            input += sc.nextLine() + "\n";
        }
        String[] lines = input.split("\n");
        String preorder = "";
        for (int i = 0; i < lines.length - 1; i++) {
            preorder += lines[i] + "\n";
        }
        String encodedMessage = lines[lines.length - 1];
        Node root = constructTree(preorder);
        String decodedMessage = decodeMessage(root, encodedMessage);
        printPreorder(root);
        System.out.println();
        System.out.println(decodedMessage);
        stats(encodedMessage, decodedMessage);
    }

    public static Node constructTree(String preorder) {
        // part 1
        return constructTreeUtil(preorder,0);

    }

    public static Node constructTreeUtil(String preorder, int i){

        Node current = new Node(preorder.charAt(i));
        i++;

        if(current.val == '*'){
            current.left = constructTreeUtil(preorder, i);
            current.right = constructTreeUtil(preorder, i);
        }
        return current;
    }

    public static String decodeMessage(Node root, String encodedMessage) {
        // part 2
        StringBuilder sb = new StringBuilder();

        Node current = root;


        for(int i = 0; i <= encodedMessage.length(); i++){

            if(current.left == null && current.right == null){
                sb.append(current.val);
                current = root;

                if(i == encodedMessage.length()){
                    break;
                }
            }

            if(encodedMessage.charAt(i) == '0'){
                current = current.left;
            }else {
                current = current.right;
            }

        }

        return sb.toString();
    }

    public static void stats(String encodedMessage, String decodedMessage) {
        // part 3
        int decodedBits = decodedMessage.length() * 8;
        int encodedBits = encodedMessage.length();

        System.out.println(decodedMessage);
        System.out.println("Number of bits = " + encodedBits);
        System.out.println("Number of characters = " + decodedMessage.length());
        System.out.println(String.format("Compression ratio = %.2f", encodedBits / decodedBits) + "%");
    }

    public static void printPreorder(Node root) {
        // part 4
        printPreorderUtil(root, "");
    }

    public static void printPreorderUtil(Node root, String encode){
        if(root.left == null && root.right == null){
            System.out.println(root.val + " " + encode.length() + " "+ encode);
            return;
        }
        printPreorderUtil(root.left, encode + "0");
        printPreorderUtil(root.right, encode + "1");
    }
}
