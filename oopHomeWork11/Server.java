package oopHomeWork11;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private int port;

    public static void main(String[] args) {
//        Написать сервер, который будет отправлять пользователю
//информацию о системе и номер запроса.
        new Server(8080).start();
    }

    public void start(){
        String answer = "<html><head><title>ОС</title> <meta charset='utf-8'></head><body><p><h1>Operation System: "
                + System.getProperty("os.name") + "</h1></p><br></body></html>";

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            for (;;) {
                Socket socket = serverSocket.accept();
                ServerResponse(answer, socket);
            }
        } catch (IOException e) {
            System.out.println(e);
            System.out.println("Error to server Socket Open!!!");
        }

    }

    private void ServerResponse(String answer, Socket socket) {
        try (InputStream is = socket.getInputStream(); OutputStream os = socket.getOutputStream();
             PrintWriter pw = new PrintWriter(os)) {
            byte[] rec1 = new byte[is.available()];
            is.read(rec1);
            String response = "HTTP/1.1 200 OK\r\n" + "Server: My_Server\r\n" + "Content-Type: text/html\r\n"
                    + "Content-Length: " + "\r\n" + "Connection: close\r\n\r\n";
            pw.print(response + answer);
            pw.flush();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public Server(int port) {
        this.port = port;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
