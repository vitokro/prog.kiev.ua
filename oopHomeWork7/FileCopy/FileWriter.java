package oopHomeWork7.FileCopy;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileWriter implements Runnable{
    private File file;
    private FileCopy fileCopy;

    public FileWriter(File file, FileCopy fileCopy) {
        this.file = file;
        this.fileCopy = fileCopy;
    }

    public FileWriter() {
    }

    void write() throws IOException {
        try (FileOutputStream fos = new FileOutputStream(file)) {
            while(!fileCopy.isStopped()) {
                byte[] buffer = fileCopy.read();
                fos.write(buffer, 0, buffer.length);
            }

        }
    }

    @Override
    public void run() {
        try {
            write();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public FileCopy getFileCopy() {
        return fileCopy;
    }

    public void setFileCopy(FileCopy fileCopy) {
        this.fileCopy = fileCopy;
    }

    @Override
    public String toString() {
        return "FileWriter{" +
                "file=" + file +
                ", fileCopy=" + fileCopy +
                '}';
    }
}
