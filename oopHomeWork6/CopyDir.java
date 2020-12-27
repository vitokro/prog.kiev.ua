package oopHomeWork6;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class CopyDir {

    public static void copyM(String source, String target){
        File dirCopy = new File(source);
        if (!areDirsCorrect(dirCopy, new File(target))){
            System.err.println("Copy failed");
            return;
        }
        for (File f: dirCopy.listFiles()) {
           new Thread(() -> {
               try {
                   Files.copy(f.toPath(), Path.of(target, f.getName()));
               } catch (IOException e) {
                   System.out.println("Cannot copy " + f.getName());
                   e.printStackTrace();
               }
           }).start();
        }
    }

    private static boolean areDirsCorrect(File dirCopy, File copyTo) {
        if (!dirCopy.exists()) {
            System.out.println(dirCopy + " is not exists");
            return false;
        }
        if (!copyTo.exists()) {
            System.out.println(copyTo + " is not exists");
            return false;
        }
        if (!copyTo.isDirectory()){
            System.out.println(dirCopy + " is not a directory");
            return false;
        }
        if (!copyTo.isDirectory()){
            System.out.println(copyTo + " is not a directory");
            return false;
        }
        return true;
    }
}
