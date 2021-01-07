package oopHomeWork7.FileCopy;

import java.io.File;

public class FileCopy{
    private byte[] buffer;
    private long len;
    private long progress;
    private CopyFlag readOrWrite = CopyFlag.READ;
    private boolean stop = false;
    private enum CopyFlag{
        READ,
        WRITE
    }

    public FileCopy() {
    }

    public FileCopy(File file) {
        this.len = file.length();
        this.progress = this.len;
        System.out.printf("Copying file %s\n", file.getName());
    }

    public synchronized byte[] read() {
        while (readOrWrite == CopyFlag.READ) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        byte[] temp = this.buffer;
        readOrWrite = CopyFlag.READ;
        notifyAll();
        return temp;
    }

    public synchronized void write(byte[] buffer, int len) {
        while (readOrWrite == CopyFlag.WRITE) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        this.buffer = buffer;
        progressBar(buffer.length);
        readOrWrite = CopyFlag.WRITE;
        notifyAll();
    }

    public boolean isStopped() {
        return stop;
    }

    public synchronized void stop() {
        this.stop = true;
    }

    private void progressBar(long bytes){
        progress = progress - bytes;
        long perc = 100 - progress * 100 / len;
        drawPB(perc);
    }

    private void drawPB(long perc) {
        System.out.print("\rCopied " + perc + "% [");
        for (int i = 0; i < perc / 2 - 1; i++) {
            System.out.print("=");
        }
        System.out.print(">");
        for (int i = (int)perc / 2; i < 50; i++) {
            System.out.print(".");
        }
        System.out.print("]");
    }
}
