public class Main
{

    public static void main(String args[]) {
        Singleton s = Singleton.getSingleton();
        s.printRandom();

        Singleton s1 = Singleton.getSingleton();
        s1.printRandom();

        Singleton s2 = Singleton.getSingleton();
        s2.printRandom();


        Singleton s3 = Singleton.getSingleton();
        s3.printRandom();
    }
}