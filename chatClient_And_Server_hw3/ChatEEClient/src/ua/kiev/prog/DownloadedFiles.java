package ua.kiev.prog;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.concurrent.ConcurrentLinkedQueue;

public enum DownloadedFiles {
    INSTANCE;

    private ConcurrentLinkedQueue<Integer> fileIds = new ConcurrentLinkedQueue<>();
    private ConcurrentLinkedQueue<String> fileNames = new ConcurrentLinkedQueue<>();


    public void downloadFile(Scanner scanner) throws IOException {
        String fileName = null;
        int fileId = 0;
        try {
            fileName = fileNames.poll();
            fileId = fileIds.poll();
        } catch (Exception e) {
            Utils.printErr("No files for download!");
            return;
        }
        Utils.print("Type full path for downloading file");
        String fullFilePath = scanner.nextLine() + fileName;
        int respCode = 0;
        Path file = null;
        try {
            file = Paths.get(fullFilePath);
            respCode = Utils.downloadFile("/files?fileId=" + fileId, file);
        } catch (IOException e) {
            Utils.printErr("Download failed: \n\r" + e.getMessage());
            Utils.printErr("Server's response code: " + respCode);
        }
        if ((respCode == 200)) {
            Utils.print("Success! Check out downloaded file: \n" + file.toString());
            Utils.delFileFromServer("/files?fileId=" + fileId);
        }
    }

    public void sendFile(Scanner scanner, String login) throws IOException {
        Utils.print("Type @nickname + full path to file you want to send");
        String str = "";
        while (str.isEmpty()) {
            str = scanner.nextLine();
        }

        String to;
        int iAt = str.indexOf("@"); // index of @
        if (iAt != -1) {
            int iWS = str.indexOf(" "); // index of first whitespace
            to = str.substring(iAt + 1, iWS);
            str = str.substring(iWS + 1);
        } else{
            Utils.printErr("No nickname found");
            return;
        }

        Path file;
        try {
            file = Paths.get(str);
        } catch (InvalidPathException e) {
            Utils.printErr("Your path to file is not valid! File not sent");
            return;
        }
        byte[] bytes = Files.readAllBytes(file);
        int respCode = Utils.sendFile("/files?login=" + login + "&to=" + to + "&fileName=" + file.getFileName(), bytes);
        if (respCode == 200) {
            Utils.print("Your file have been sent successfully");
        } else {
            Utils.printErr("Problems with sending your file! File not sent");
            Utils.printErr("Server's response code: " + respCode);
        }
    }

    public void setFile(int fileId, String fileName) {
        this.fileNames.add(fileName);
        this.fileIds.add(fileId);
    }
}
