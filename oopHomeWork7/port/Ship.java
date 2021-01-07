package oopHomeWork7.port;

public class Ship {
    private String name;
    private int boxes;
    private boolean isUnloading;

    public Ship() {
    }

    public Ship(String name, int boxes) {
        this.name = name;
        this.boxes = boxes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isUnloading() {
        return isUnloading;
    }

    public void setUnloading(boolean unloading) {
        isUnloading = unloading;
    }

    public int getBoxes() {
        return boxes;
    }

    public void setBoxes(int boxes) {
        this.boxes = boxes;
    }

    @Override
    public String toString() {
        return "Ship " + name;
    }

    public void unloadBox() {
        boxes--;
        if (boxes == 0)
            System.err.printf("%s is fully unloaded\n", this);
        if (boxes < 0)
            throw new IllegalStateException(String.format("You cannot unload more boxes from %s than %d\n", this, boxes));
    }
}
