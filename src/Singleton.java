import java.util.Random;

public class Singleton {
    private static Singleton _instance;
    private static int random = new Random().nextInt(100);

    private Singleton(){

    }

    public static Singleton getSingleton(){

        if (_instance == null) {

            //added monitor for first 2 threads when race condition is going to happen in multithreading env
            synchronized (Singleton.class){

                if(_instance == null){
                    _instance = new Singleton();
                }
            }

        }

        return _instance;
    }

    public void printRandom(){
        System.out.println(random);
    }
}

//    public static void main(String args[]) {
//        Singleton s = Singleton.getSingleton();
//        s.printRandom();
//
//        Singleton s1 = Singleton.getSingleton();
//        s1.printRandom();
//
//        Singleton s2 = Singleton.getSingleton();
//        s2.printRandom();
//
//
//        Singleton s3 = Singleton.getSingleton();
//        s3.printRandom();
//    }
