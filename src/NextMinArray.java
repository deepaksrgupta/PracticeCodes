import java.util.Stack;

public class NextMinArray {

    /*
    https://www.geeksforgeeks.org/next-smaller-element/

    works in O(n) time and space
     */
    public static int[] getNextMinArray(int[] ip){

        if (ip.length == 0)
            return new int[]{};

        if(ip.length == 1)
            return new int[]{-1};


        Stack<Integer> stack = new Stack<>();


        int result[] = new int[ip.length];

        for(int i = ip.length-1; i >= 0; i--) {

            if(stack.isEmpty()){
                result[i] = -1;
            }else{

                while(!stack.isEmpty() && stack.peek() > ip[i]) {
                    stack.pop();
                }

                result[i] = stack.isEmpty() ? -1 : stack.peek();

            }

            stack.push(ip[i]);
        }

        return result;

    }

    static public void main( String args[] ) {
        int []ip = {4, 8, 5, 2, 25};
        //int []ip = {4, 1, 6, 7, 3, 5, 8, 2, 6, 9};
        //1,-1 , 3, ,3 , 2 , 2 ,2,-1 , -1,-1

        int []output = getNextMinArray(ip);

        for(int i : output){
            System.out.print(i+" ");
        }


    }
}
