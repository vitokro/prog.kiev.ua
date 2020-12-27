package oopHomeWork6;

public class MultiThreadShellSort {
    private int[] array;
    private int threadsNumber;

    public MultiThreadShellSort() {
    }

    public MultiThreadShellSort(int[] array, int threadsNumber) {
        this.array = array;
        this.threadsNumber = threadsNumber;
    }

    public void sort(){
        int size = array.length / threadsNumber;
        ShellSort[] sorts = new ShellSort[threadsNumber];
        Thread[] thrs = new Thread[threadsNumber];
        for (int i = 0; i < threadsNumber; i++) {
            int begin = i * size;
            int end;
            if (i == threadsNumber - 1)
                end = array.length;
            else
                end = (i + 1) * size;
            sorts[i] = new ShellSort(array.clone(), begin, end);
            thrs[i] = new Thread(sorts[i]);
            thrs[i].start();
        }
        join(thrs);
        mergeArrays(sorts);
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

    public void setArray(int[] array) {
        this.array = array;
    }

    public void setThreadsNumber(int threadsNumber) {
        this.threadsNumber = threadsNumber;
    }

    private void mergeArrays(ShellSort[] sorts) {
        int j = 0;
        while (j != array.length) {
            int minValue = Integer.MAX_VALUE;
            int minInd = -1;
            for (int i = 0; i < threadsNumber; i++) {
                int value = sorts[i].getMinValue();
                if (value != -1 && value < minValue){
                    minValue = value;
                    minInd = i;
                }
            }
            if (minInd != -1) {
                array[j++] = minValue;
                sorts[minInd].incMinIndex();
            }
        }
    }
}
