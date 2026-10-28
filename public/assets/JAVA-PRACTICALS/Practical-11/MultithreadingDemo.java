class NumberThread extends Thread {
    @Override
    public void run() {
        for (int i = 1; i <= 5; i++) {
            System.out.println("Thread Class Counter: " + i);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                System.out.println("Thread interrupted.");
            }
        }
    }
}

class LetterRunnable implements Runnable {
    @Override
    public void run() {
        for (char ch = 'A'; ch <= 'E'; ch++) {
            System.out.println("Runnable Character: " + ch);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                System.out.println("Runnable interrupted.");
            }
        }
    }
}

public class MultithreadingDemo {
    public static void main(String[] args) {
        NumberThread t1 = new NumberThread();
        Thread t2 = new Thread(new LetterRunnable());

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted.");
        }

        System.out.println("Both threads have completed execution.");
    }
}
