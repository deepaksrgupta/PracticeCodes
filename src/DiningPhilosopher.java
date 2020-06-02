//dining philosopher implementation w
public class DiningPhilosopher {

    //just object which will be functioning as monitors
    private Object[] forks;
    private Philosopher[] philosophers;

    DiningPhilosopher(int totalPhilosophers){

        forks = new Object[totalPhilosophers];
        philosophers = new Philosopher[totalPhilosophers];


        //at each turn only ceil(n/2) philosophers will be able to eat because of each n philosophers will be needing 2 forks each from n forks
        //so at even turn let the philosophers go for second fork then left and opposite on odd turn
        for(int i=0;i< totalPhilosophers;i++){
            forks[i] = new Object();

            int fork1 = i;
            int fork2 = (i+1)%totalPhilosophers;
            if(i%2 == 0){
                philosophers[i] = new Philosopher(i,fork2,fork1);
            }else{
                philosophers[i] = new Philosopher(i,fork1,fork2);
            }

        }
    }

    public void startThinkingNEating() throws InterruptedException{

        for(int i=0;i< philosophers.length;i++){
            philosophers[i].start();
        }

        //wait for first thread to end which will never happen because it runs for infinite time
        philosophers[0].join();
    }


    private class Philosopher extends Thread{
        private int id;
        private int fork1;
        private int fork2;


        Philosopher(int id, int fork1, int fork2){

            this.id = id;
            this.fork1 = fork1;
            this.fork2 = fork2;
        }


        @Override
        public void run() {
            status(String.format("Reading for eating using %d and %d",fork1,fork2));

            while (true) {
                synchronized (forks[fork1]) {
                    status("got fork " + fork1);
                    synchronized (forks[fork2]) {
                        status("got fork " + fork2);
                        status("eating now");
                    }
                }
            }
        }

        private void status(String msg){
            System.out.println("Philosopher: "+id+" "+msg);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        DiningPhilosopher diningPhilosopher = new DiningPhilosopher(5);
        diningPhilosopher.startThinkingNEating();
    }
}
