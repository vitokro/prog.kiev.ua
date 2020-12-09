package OOPHomeWork1;

import java.math.BigDecimal;
import java.util.Objects;

public class Phone {
    private String number;
    private Network network;
    private String name;

    public Phone() {
    }

    public Phone(String number, String name) {
        this.number = number;
        this.name = name;
    }

    void register(Network network){
        network.registerNumber(number);
        this.network = network;
    }

    boolean Call(String n){
        System.out.printf("Number %s is calling to %s\n", number, n);
        if (n.equals(number)) {
            System.out.println("You can not call to yourself!");
            return false;
        }
        if (network == null){
            System.out.printf("Number %s is not registered to any network!\n", number);
            return false;
        } else if (network.isNumberRegistered(n)){
            System.out.printf("Call to %s is successful\n", n);
            return true;
        } else {
            System.out.println("You dialed the wrong number");
            return false;
        }

    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Network getNetwork() {
        return network;
    }

    public void setNetwork(Network network) {
        this.network = network;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
