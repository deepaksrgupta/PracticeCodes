import java.util.*;
public class SortTheUnsortedStack {


    public static void sortStackUsingRecursion(Stack<Integer> st,int element) {

        //base case for going in depth
        if(st.size() !=1 && !st.isEmpty()) {
            sortStackUsingRecursion(st,st.pop());
        }

        //base case for no elements
        if(st.isEmpty()) {
            st.push(element);
            return;
        }
        else if(st.peek() > element){ //compare top and current ele

            //sort stack for next elements
            int nextElement = st.pop();
            sortStackUsingRecursion(st,element);
            st.push(nextElement);
        }
        else {  //push if element is not greater
            st.push(element);
        }
    }

    public static void printStack(Stack<Integer> st)
    {
        if(!st.isEmpty())
        {
            int z = st.pop();
            System.out.print(z+" ");
            printStack(st);
            st.push(z);
        }
    }

    public static Stack<Integer> initStack(Stack<Integer> stack)
    {
        stack = new Stack<>();

        stack.push(10);
        stack.push(9);
        stack.push(8);
        stack.push(7);
        stack.push(6);
        stack.push(5);
        stack.push(4);
        stack.push(3);
        stack.push(2);
        stack.push(1);
        stack.push(15);
        stack.push(83);
        stack.push(18);
        stack.push(15);
        stack.push(89);
        stack.push(91);
        stack.push(17);
        stack.push(18);
        stack.push(42);
        stack.push(50);
        stack.push(59);

        return stack;
    }

    public static Stack<Integer> sortStackUsingIterative(Stack<Integer> stack)
    {
        //stores element in sorted order
        Stack<Integer> anotherStack = new Stack<>();

        //repeat the process for the each element in the stack
        while (!stack.isEmpty()){

            //base case
            if(anotherStack.isEmpty()){
                anotherStack.push(stack.pop());
            }
            else {

                //keep track each element popped from the stack
                int greaterElementCount = 0;

                //select current element to track
                int element = stack.pop();

                //keep popping all the greater elements and keep track of count
                while (!anotherStack.isEmpty() && anotherStack.peek() > element)
                {
                    stack.push(anotherStack.pop());
                    greaterElementCount++;
                }

                //push correct element at proper sorted location
                anotherStack.push(element);

                //retain back all the previous elements using count
                while (greaterElementCount!=0)
                {
                    anotherStack.push(stack.pop());
                    greaterElementCount--;
                }

            }
        }
        return anotherStack;
    }

    public static void main(String[] args) {

        Stack<Integer> stack = null;

        stack = initStack(stack);

        System.out.println("Unsorted Stack is given below");

        printStack(stack);

        //sortStackUsingRecursion(stack,stack.pop());
        stack = sortStackUsingIterative(stack);

        System.out.println("\nSorted Stack is given below");

        printStack(stack);

    }
}

