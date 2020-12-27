package oopHomeWork6;

public class ShellSort implements Runnable{
    private int[] a;
    private int minInd; // use it for merge arrays after sorting
    private int end;
    private int begin;
    private int length;

    public ShellSort() {
    }

    public ShellSort(int[] a, int begin, int end) {
        this.a = a;
        this.minInd = begin;
        this.begin = begin;
        this.end = end;
        this.length = end - begin;
    }

    public void sort(){
        if (length < 2)
            return;
        KnuthStep knuthStep = new KnuthStep();
        int step = knuthStep.nextStep();
        do{
            for (int i = step + begin; i < end; i++) {
                if (a[i] <= a[i - step]){
                    swap(a, i, i - step);
                    for (int j = i - step; j - step >= begin ; j = j - step) {
                        if (a[j] <= a[j - step])
                            swap(a, j, j - step);
                    }
                }
            }
            step = knuthStep.nextStep();
        } while(step > 0);
    }

    // method for merging arrays
    public int getMinValue(){
        if (minInd >= end)
            return -1;
        return a[minInd];
    }

    @Override
    public void run() {
        sort();
    }

    public void incMinIndex() {
        minInd++;
    }

    public void setArray(int[] a) {
        this.a = a;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public void setBegin(int begin) {
        this.begin = begin;
    }

    private void swap(int[] array, int i , int j){
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
    private class KnuthStep{
        private int i;

        public KnuthStep(){
            while((Math.pow(3, i) - 1)/2 < length / 3){
                i++;
            }
        }

        public int nextStep(){
            int step = (int) (Math.pow(3, i) / 2);
            i--;
            return step;
        }
    }
}
