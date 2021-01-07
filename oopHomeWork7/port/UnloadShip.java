package oopHomeWork7.port;

import oopHomeWork7.port.Dock;
import oopHomeWork7.port.Ship;

public class UnloadShip implements Runnable{
    private Dock dock;
    private Ship ship;

    public UnloadShip() {
    }

    public UnloadShip(Dock dock, Ship ship) {
        this.dock = dock;
        this.ship = ship;
    }

    public Dock getDock() {
        return dock;
    }

    public void setDock(Dock dock) {
        this.dock = dock;
    }

    public Ship getShip() {
        return ship;
    }

    public void setShip(Ship ship) {
        this.ship = ship;
    }

    void unload(){
        dock.unload(ship);
    }

    @Override
    public void run() {
        unload();
    }
}
