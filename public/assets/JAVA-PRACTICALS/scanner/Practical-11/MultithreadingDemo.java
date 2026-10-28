import java.util.Scanner;

class NumberThread extends Thread {
    private int count;

    public NumberThread(int count) {
        this.count = count;
    }

    @Override
    public void run() {
        for (int i = 1; i <= count; i++) {
            System.out.println("[Thread Class] Number: " + i);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                System.out.println("Thread interrupted.");
            }
        }
    }
}

class LetterRunnable implements Runnable {
    private int count;

    public LetterRunnable(int count) {
        this.count = count;
    }

    @Override
    public void run() {
        for (int i = 0; i < count; i++) {
            char ch = (char) ('A' + (i % 26));
            System.out.println("  [Runnable Interface] Character: " + ch);
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
        Scanner sc = new Scanner(System.in);

        System.out.println("=== Multithreading with Thread & Runnable (User Input) ===");
        System.out.print("Enter number of iterations for both threads (e.g., 5): ");
        int count = sc.nextInt();

        NumberThread t1 = new NumberThread(count);
        Thread t2 = new Thread(new LetterRunnable(count));

        System.out.println("\nStarting concurrent thread execution...");
        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted.");
        }

        System.out.println("\nBoth threads have completed execution.");
        sc.close();
    }
}
