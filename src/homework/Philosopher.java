package homework;

import java.util.concurrent.locks.ReentrantLock;

public class Philosopher implements Runnable {
    private final int id;
    private final ReentrantLock leftFork;
    private final ReentrantLock rightFork;
    private int mealsCount;

    public Philosopher(int id, ReentrantLock leftFork, ReentrantLock rightFork) {
        this.id = id;
        this.leftFork = leftFork;
        this.rightFork = rightFork;
        this.mealsCount = 0;
    }

    private void think() throws InterruptedException {
        System.out.println("Философ " + id + " размышляет");
        Thread.sleep(1000);
    }

    private void eat() throws InterruptedException {
        System.out.println("Философ " + id + " ест");
        Thread.sleep(1000);
        mealsCount++;
    }

    @Override
    public void run() {
        try {
            while (mealsCount < 3) {
                think();

                leftFork.lock();
                System.out.println("Философ " + id + " взял левую вилку");

                if (rightFork.tryLock()) {
                    try {
                        System.out.println("Философ " + id + " взял правую вилку");
                        eat();
                    } finally {
                        if (rightFork.isHeldByCurrentThread()) {
                            rightFork.unlock();
                            System.out.println("Философ " + id + " положил правую вилку");
                        }
                    }
                } else {
                    leftFork.unlock();
                    Thread.sleep(100);
                }

                if (leftFork.isHeldByCurrentThread()) {
                    leftFork.unlock();
                    System.out.println("Философ " + id + " положил левую вилку");
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
