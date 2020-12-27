package oopHomeWork6;

public class CountArraySum implements Runnable {
    int[] array;
    int begin;
    int end;
    long sum;

    public CountArraySum() {
    }

    public CountArraySum(int[] array, int begin, int end) {
        this.array = array;
        this.begin = begin;
        this.end = end;
    }

    @Override
    public void run() {
        count();
        System.out.println(Thread.currentThread() + " sum = " + sum);
    }

    public long getSum() {
        return sum;
    }

    public void setArray(int[] array) {
        this.array = array;
    }

    public void setBegin(int begin) {
        this.begin = begin;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    private void count() {
        for (int i = begin; i < end; i++) {
            sum = sum + array[i];
        }
    }
}
