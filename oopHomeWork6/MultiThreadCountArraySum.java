package oopHomeWork6;

public class MultiThreadCountArraySum {
    private int threadsNumber;
    private int[] array;
    private long sum;

    public MultiThreadCountArraySum() {
    }

    public MultiThreadCountArraySum(int threadsNumber, int[] array) {
        this.threadsNumber = threadsNumber;
        this.array = array;
    }

    public void count(){
        int size = array.length / threadsNumber;
        CountArraySum[] sums = new CountArraySum[threadsNumber];
        Thread[] thrs = new Thread[threadsNumber];
        for (int i = 0; i < threadsNumber; i++) {
            int begin = i * size;
            int end;
            if (i == threadsNumber - 1)
                end = array.length;
            else
                end = (i + 1) * size;
            sums[i] = new CountArraySum(array, begin, end);
            thrs[i] = new Thread(sums[i]);
            thrs[i].start();
        }

        join(thrs);
        mergeArrays(sums);
    }

    public long getSum() {
        return sum;
    }

    public void setThreadsNumber(int threadsNumber) {
        this.threadsNumber = threadsNumber;
    }

    public void setArray(int[] array) {
        this.array = array;
    }

    private void join(Thread[] thrs) {
        for (int i = 0; i < threadsNumber; i++) {
            try {
                thrs[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    private void mergeArrays(CountArraySum[] sums) {
        sum = 0;
        for (int i = 0; i < threadsNumber; i++) {
            sum += sums[i].getSum();
        }
    }
}
