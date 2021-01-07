package oopHomeWork7.findFile;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MultiThreadFindFile {
    private File dir;
    private File file;
    private ExecutorService exSer;

    public MultiThreadFindFile() {
    }

    public MultiThreadFindFile(File dir, File file, int threadNumber) {
        this.dir = dir;
        this.file = file;
        this.exSer = Executors.newFixedThreadPool(threadNumber);
    }

    public void find() throws IOException {
        internalFind(dir);
        exSer.shutdown();
    }

    private void internalFind(File dir) throws IOException {
        File[] files = dir.listFiles();
        if (files == null)
            return;
        for (int i = 0; i < files.length; i++) {
            if (files[i].isDirectory())
                exSer.submit(new FindFile(files[i], file));
            if (file.getName().equalsIgnoreCase(files[i].getName()))
                System.out.println(files[i].getCanonicalFile());
        }
    }

    public File getDir() {
        return dir;
    }

    public void setDir(File dir) {
        this.dir = dir;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }
}
