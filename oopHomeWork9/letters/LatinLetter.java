package oopHomeWork9.letters;

import java.util.Objects;

public class LatinLetter {
    private char letter;
    private int quantity;

    public LatinLetter(char letter) {
        this.letter = letter;
    }

    public LatinLetter() {
    }

    public char getLetter() {
        return letter;
    }

    public void setLetter(char letter) {
        this.letter = letter;
    }

    public void incQuant(){
        quantity++;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LatinLetter latinLetter1 = (LatinLetter) o;
        return letter == latinLetter1.letter;
    }

    @Override
    public int hashCode() {
        return Objects.hash(letter);
    }

    @Override
    public String toString() {
        return  "letter=" + letter +
                ", quantity=" + quantity +
                "}\n";
    }

}
