/*
A Memento Pattern says that "to restore the state of an object to its previous state".
But it must do this without violating Encapsulation. Such case is useful in case of error or failure.
 */

import java.util.Stack;

interface Text {
    String data = null;

    void setData(String data);

    String getData();
}

public class Memento implements Text{

    //for just undo command for redo introduce a new stack for keep track of states backwards
    private Stack<String> stack = new Stack<>();
    private String data;

    public void setData(String data){
        this.data = data;
        stack.push(data);
    }

    void undo() {
        if(stack.size() > 0){
            data = stack.pop();
        }else{
            System.out.println("you have not made any changes");
        }
    }

    public String getData(){
        return data;
    }

    public void printStates(){
        for (String s : stack){
            System.out.println(s);
        }
    }
}

/*
public static void main(String args[]) {
        Memento memento = new Memento();

        memento.setData("hello");
        memento.setData("general kenobi");
        memento.setData("i have higher ground");
        memento.setData("you underestimate my powers");
        memento.undo();
        memento.printStates();
    }
 */
