import java.util.*;
public class GetMinInConstantTimeFromStack {

    public static class MyStack{

        Stack<Integer> minStack = new Stack<>();
        Stack<Integer> originalStack = new Stack<>();


        public void pop(){

            if(!originalStack.isEmpty()){
                if(minStack.peek() == originalStack.peek())
                {
                    originalStack.pop();
                    minStack.pop();
                }
                else{
                    originalStack.pop();
                }
            }
        }

        public int getMin(){
            if(!minStack.isEmpty())
            {
                return minStack.peek();
            }
            else return Integer.MIN_VALUE;
        }

        public void push(int i)
        {
            if((!minStack.isEmpty() && minStack.peek() > i) || minStack.isEmpty())
            {
                minStack.push(i);
                originalStack.push(i);
            }
            else originalStack.push(i);
        }


        public boolean isEmpty(){
            return originalStack.isEmpty();
        }

        public void displayStack(){
            if(!originalStack.isEmpty()){
                int x = originalStack.pop();
                System.out.print(x+" ");
                displayStack();
                originalStack.push(x);
            }
        }
    }


    public static void main(String[] args) {

        MyStack stack =  new MyStack();

        Random r = new Random();


        while (r.nextInt(15)!= 10)
        {
            stack.push(r.nextInt(100));

            System.out.println("Current stack is ");
            stack.displayStack();
            System.out.println("Minimum is "+stack.getMin());

            if(r.nextBoolean())
            {
                System.out.println("Popped element is "+stack.originalStack.peek());
                stack.pop();
            }
        }
    }
}

