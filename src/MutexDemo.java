import java.util.*;
import java.util.concurrent.Semaphore;

public class MutexDemo {

    private Semaphore mutex = new Semaphore(1);//semaphore with 1 lock is known as binary semaphore also mutex else counting semaphore
    private Random random = new Random();

    public class ThreadDemo extends Thread {

        String threadName;

        ThreadDemo(String name){
            threadName = name;
        }

        @Override
        public void run() {
            int x = random.nextInt(10);

            try {

                System.out.println(threadName+" is preparing to go inside the critical section");

                mutex.acquire();

                System.out.println(threadName+" is inside the critical section");

                //critical section code
                Thread.sleep(x * 100);

                System.out.println(threadName+" is leaving the critical section");
                mutex.release();
            }
            catch (Exception e) {
                System.out.println("got exception in "+ threadName+ " "+e.getMessage());
            }
            finally {
                try {
                    Thread.sleep(x * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(threadName+" did computation");
            }



        }
    }

    public void showDemo(){

        String []names = new String[]{"ross","monica","chanendler bong","racheal","joey","pheobe"};

        for(String name : names){
            Thread current  = new ThreadDemo(name);
            current.start();
        }
    }

}
