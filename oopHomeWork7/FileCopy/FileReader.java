package oopHomeWork7.FileCopy;

import java.io.*;

public class FileReader implements Runnable{
    private File file;
    private FileCopy fileCopy;

    public FileReader(File file, FileCopy fileCopy) {
        this.file = file;
        this.fileCopy = fileCopy;
    }

    public FileReader() {
    }

    void read() throws IOException {
        try (FileInputStream fis = new FileInputStream(file)) {
            byte[] buffer = new byte[1024 * 10];
            int byteRead = 0;
            while((byteRead = fis.read(buffer)) > 0) {
                fileCopy.write(buffer, byteRead);
            }
            fileCopy.stop();
        }
    }

    @Override
    public void run() {
        try {
            read();
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
        return "FileReader{" +
                "file=" + file +
                ", fileCopy=" + fileCopy +
                '}';
    }
}
