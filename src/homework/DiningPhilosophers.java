package homework;

import java.util.concurrent.locks.ReentrantLock;

public class DiningPhilosophers {
    private Thread[] philosopherThreads;

    public void startPhilosophers() {
        int numOfPhilosophers = 5;
        ReentrantLock[] forks = new ReentrantLock[numOfPhilosophers];

        for (int i = 0; i < numOfPhilosophers; i++) {
            forks[i] = new ReentrantLock();
        }

        Philosopher[] philosophers = new Philosopher[numOfPhilosophers];

        for (int i = 0; i < numOfPhilosophers; i++) {
            philosophers[i] = new Philosopher(i, forks[i], forks[(i + 1) % numOfPhilosophers]);
        }

        philosopherThreads = new Thread[numOfPhilosophers];

        for (int i = 0; i < numOfPhilosophers; i++) {
            philosopherThreads[i] = new Thread(philosophers[i]);
            philosopherThreads[i].start();
        }
    }

    public Thread[] getPhilosopherThreads() {
        return philosopherThreads;
    }
}
