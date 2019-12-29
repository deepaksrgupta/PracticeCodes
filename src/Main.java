public class Main
{
    public static void main(String args[]) {
        Memento memento = new Memento();

        memento.setData("hello");
        memento.setData("general kenobi");
        memento.setData("i have higher ground");
        memento.setData("you underestimate my powers");
        memento.undo();
        memento.printStates();
    }
}