package Threading;

public class Threads {
    public static void main(String[] args) throws InterruptedException {
        Object obj1 = new Object();
        Object obj2 = new Object();
        Thread t1 = new Thread(()->{
            synchronized (obj1){
                try {
                    Thread.sleep(5000);
                    System.out.println("Got the first obj, waiting for second");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                synchronized (obj2){
                    try {
                        System.out.println("Got the second obj");
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
        Thread t2 = new Thread(()->{
            synchronized (obj2){
                try {
                    Thread.sleep(5000);
                    System.out.println("Got the second obj, waiting for first");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                synchronized (obj1){
                    try {
                        Thread.sleep(2000);
                        System.out.println("Got the second obj");
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
        t1.start();
        t2.start();

        t1.join();
        t2.join();
        System.out.println("Finished both threads");
    }
}
