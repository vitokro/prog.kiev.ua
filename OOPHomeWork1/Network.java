package OOPHomeWork1;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

public class Network {
    private Set<String> registeredNumbers = new HashSet<>();
    private String name;

    public Network() {
    }

    public Network(String name) {
        this.name = name;
    }

    public void registerNumber(String number){
        registeredNumbers.add(number);
        System.out.printf("Number %s has been registered to network %s\n", number, name);
    }

    public boolean isNumberRegistered(String number){
        return registeredNumbers.contains(number);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
