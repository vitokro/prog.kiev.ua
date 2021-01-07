package oopHomeWork7.findFile;

import java.io.File;
import java.io.IOException;

public class FindFile implements Runnable{
    private File dir;
    private File file;

    public FindFile(File dir, File file) {
        this.dir = dir;
        this.file = file;
    }

    @Override
    public void run() {
        try {
            find(dir);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void find(File dir) throws IOException {
        File[] files = dir.listFiles();
        if (files == null)
            return;
        for (int i = 0; i < files.length; i++) {
            if (files[i].isDirectory())
                find(files[i]);
            if (file.getName().equalsIgnoreCase(files[i].getName()))
                System.out.println(files[i].getCanonicalFile());
        }
    }
}
