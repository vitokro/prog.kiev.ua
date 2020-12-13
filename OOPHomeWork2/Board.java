package OOPHomeWork2;

import java.util.Arrays;

public class Board {
    private Shape[] figures = new Shape[4];

    public Board() {
    }

    public void put(Shape f, int index){
        if (figures[index] != null)
            System.out.println("This part is busy");
        else{
            figures[index] = f;
        }
    }

    public void del(int index){
        figures[index] = null;
    }

    public double getTotalArea(){
        double total = 0;
        for (Shape f: figures){
            if (f != null)
                total += f.getArea();
        }
        return total;
    }

    @Override
    public String toString() {
        return "Board{" +
                "figures=" + Arrays.toString(figures) +
                '}';
    }
}
