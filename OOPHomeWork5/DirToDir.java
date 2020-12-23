package OOPHomeWork5;

import java.io.*;

public class DirToDir {

    public DirToDir() {
    }

    public static void copy(String ext, String dirFrom, String dirTo) throws IOException {
        File f = new File(dirFrom);
        File[] fileList = f.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                int pointerIndex = pathname.getName().lastIndexOf(".");
                if (pointerIndex == -1)
                    return false;
                if (ext.equals(pathname.getName().substring(pointerIndex + 1)))
                    return true;
                return false;
            }
        });
        internalCopy(fileList, dirTo);
    }

    private static void internalCopy(File[] fileList, String dirTo) throws IOException {
        for(File file: fileList){
            try (FileInputStream fis = new FileInputStream(file);
                 FileOutputStream fos = new FileOutputStream(dirTo + file.getName())) {
                System.out.printf("Copying file %s\n", file.getName());
                byte[] buffer = new byte[10_000];
                int byteRead = 0;
                while((byteRead = fis.read(buffer)) > 0) {
                    fos.write(buffer, 0, byteRead);
                }
            }
        }
        System.out.println("All files have been copied");
    }

}
