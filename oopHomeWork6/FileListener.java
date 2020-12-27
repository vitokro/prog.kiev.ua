package oopHomeWork6;

import java.io.File;
import java.util.*;

public class FileListener implements Runnable{
    private File dir;
    private File[] files;

    public FileListener() {
    }

    public FileListener(String dir) {
        this.dir = new File(dir);
        files = this.dir.listFiles();
    }

    @Override
    public void run() {
        File[] updateFiles = dir.listFiles();
        if (updateFiles.length != files.length){
            Set<File> oldFilesSet = new HashSet<>();
            Set<File> newFilesSet = new HashSet<>();
            Collections.addAll(oldFilesSet, files);
            Collections.addAll(newFilesSet, updateFiles);

            System.out.println(new Date() + ":");
            if (updateFiles.length > files.length){
                newFilesSet.removeAll(oldFilesSet);
                newFilesSet.forEach(x -> System.out.println("Added new file: " + x.getName()));
            } else {
                oldFilesSet.removeAll(newFilesSet);
                oldFilesSet.forEach(x -> System.out.println("File have been removed: " + x.getName()));
            }

            files = updateFiles;
        }
    }

    public void setDir(File dir) {
        this.dir = dir;
    }

    public void setFiles(File[] files) {
        this.files = files;
    }

}
