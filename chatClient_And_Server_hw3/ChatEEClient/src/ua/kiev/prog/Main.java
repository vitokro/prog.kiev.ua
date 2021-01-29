package ua.kiev.prog;


import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            new Menu().chooseAction(scanner);
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ExitException e) {
        } finally {
            scanner.close();
        }
    }


}
