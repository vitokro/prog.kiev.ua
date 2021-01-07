package oopHomeWork7.port;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Dock {
    private int boxes;
    private int dockNumber;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public Dock(int dockNumber) {
        this.dockNumber = dockNumber;
    }

    public void addBox(){
        boxes++;
        System.out.printf("dock #%d loaded %d boxes\n", dockNumber, boxes);
    }

    public int getDockNumber() {
        return dockNumber;
    }

    public void setDockNumber(int dockNumber) {
        this.dockNumber = dockNumber;
    }

    @Override
    public String toString() {
        return "Dock#" + dockNumber;
    }

    public void unload(Ship ship){
        try {
            lock.lock();
            while (ship.isUnloading()) {
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            ship.setUnloading(true);
            int shipBoxes = ship.getBoxes();
            for (int i = 0; i < shipBoxes; i++) {
                System.out.printf("Unload box#%d from %s to %s\n", i + 1, ship.getName(), this);
                ship.unloadBox();
                addBox();
                try {
                    Thread.currentThread().sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            ship.setUnloading(false);
            condition.signalAll();
        }
        finally {
            lock.unlock();
        }
    }
}
