import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class ProducerConsumer {

    class Producer extends Thread {
        private BlockingQueue<Integer> buffer;

        Producer(BlockingQueue<Integer> buffer){
            this.buffer = buffer;
        }

        @Override
        public void run() {

            Random r = new Random();
            int n  = r.nextInt(100);

            for(int i=1;i<=n;i++){

                System.out.println("[Producer] : put "+i);
                try {
                    buffer.put(i);
                    System.out.println("[Producer] : remaining capacity after adding "+i+ " "+buffer.remainingCapacity());
                    Thread.sleep(r.nextInt(100));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    class Consumer extends Thread{
        private BlockingQueue<Integer> buffer;

        Consumer(BlockingQueue<Integer> buffer){
            this.buffer = buffer;
        }

        @Override
        public void run() {
            Random r = new Random();

            while (true){

                try {
                    Integer i = buffer.take();
                    System.out.println("[Consumer] : got "+i);
                    Thread.sleep(r.nextInt(100));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    public void demoProducerConsumer(){

        BlockingQueue<Integer> buffer = new LinkedBlockingQueue<>();

        Thread producer = new Producer(buffer);
        Thread consumer = new Consumer(buffer);

        producer.start();
        consumer.start();
    }
}
