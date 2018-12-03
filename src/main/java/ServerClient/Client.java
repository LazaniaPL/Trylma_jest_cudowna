package ServerClient;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Client implements Runnable {

    protected Socket clientSocket = null;
    protected String messageText = null;

    public Client(Socket clientSocket, String messageText){
        this.clientSocket = clientSocket;
        this.messageText = messageText;
    }

    @Override
    public void run() {
        try{
            InputStream inputStream = clientSocket.getInputStream();
            OutputStream outputStream = clientSocket.getOutputStream();
            long time = System.currentTimeMillis();
            outputStream.write(("\n\nHTTP/1.1 200 OK\n\nCLIENT: " + this.messageText + " - " + time).getBytes());
            outputStream.close();
            inputStream.close();
            System.out.println("Request processed: " + time);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
