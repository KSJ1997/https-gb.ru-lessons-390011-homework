package homework;

public class Main {
    public static void main(String[] args) {
        DiningPhilosophers diningPhilosophers = new DiningPhilosophers();
        diningPhilosophers.startPhilosophers();

        for (Thread philosopherThread : diningPhilosophers.getPhilosopherThreads()) {
            try {
                philosopherThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Конец работы");
    }
}
