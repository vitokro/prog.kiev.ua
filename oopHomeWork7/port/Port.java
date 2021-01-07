package oopHomeWork7.port;

import java.util.Arrays;

public class Port {
    private Ship[] ships;
    private Dock[] docks;

    public static void main(String[] args) {
        new Port().unloadShips();
    }

    public Port() {
    }

    public Port(Ship[] ships, Dock[] docks) {
        this.ships = ships;
        this.docks = docks;
    }

    public void unloadShips() {
        // create all available scenarios of unloading ships:
        Thread[][] thrs = new Thread[docks.length][ships.length];
        for (int i = 0; i < docks.length; i++) {
            for (int j = 0; j < ships.length; j++) {
                thrs[i][j] = new Thread(new UnloadShip(docks[i], ships[j]));
                thrs[i][j].start();
            }
        }

        for (int i = 0; i < docks.length; i++) {
            for (int j = 0; j < ships.length; j++) {
                try {
                    thrs[i][j].join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public Ship[] getShips() {
        return ships;
    }

    public void setShips(Ship[] ships) {
        this.ships = ships;
    }

    public Dock[] getDocks() {
        return docks;
    }

    public void setDocks(Dock[] docks) {
        this.docks = docks;
    }

    @Override
    public String toString() {
        return "Port{" +
                "ships=" + Arrays.toString(ships) +
                ", docks=" + Arrays.toString(docks) +
                '}';
    }
}
