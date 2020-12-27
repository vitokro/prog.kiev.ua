package oopHomeWork6;

import java.math.BigInteger;

public class OneHundredThreads implements Runnable{
    private int id;

    public OneHundredThreads() {
    }

    public OneHundredThreads(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        calcFact(id);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private void calcFact(int n){
        BigInteger res = new BigInteger("1");
        for (int j = 1; j <= n; j++) {
            res = res.multiply(BigInteger.valueOf(j));
        }
        System.out.printf("Thread N%d fact = %s\n", n, res);
    }
}
