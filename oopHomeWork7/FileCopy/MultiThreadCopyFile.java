package oopHomeWork7.FileCopy;

import oopHomeWork7.FileCopy.FileCopy;
import oopHomeWork7.FileCopy.FileReader;
import oopHomeWork7.FileCopy.FileWriter;

import java.io.File;

public class MultiThreadCopyFile {
    private File fileSource;
    private File dirDest;

    public MultiThreadCopyFile(String fileSource, String dirDest) {
        this.fileSource = new File(fileSource);
        this.dirDest = new File(dirDest);
    }

    public MultiThreadCopyFile() {
    }

    public File getFileSource() {
        return fileSource;
    }

    public void setFileSource(File fileSource) {
        this.fileSource = fileSource;
    }

    public File getDirDest() {
        return dirDest;
    }

    public void setDirDest(File dirDest) {
        this.dirDest = dirDest;
    }

    public void copy(){
        FileCopy fc = new FileCopy(fileSource);
        Thread t1 = new Thread(new FileReader(fileSource, fc));
        t1.start();
        File fileDest = new File(dirDest.getAbsolutePath() +"\\" + fileSource.getName());
        Thread t2 = new Thread(new FileWriter(fileDest, fc));
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("\nCopied successfully!");
    }

    @Override
    public String toString() {
        return "MultiThreadCopyFile{" +
                "fileSource=" + fileSource +
                ", dirDest=" + dirDest +
                '}';
    }
}
