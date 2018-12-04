package ServerClient;

import java.io.*;
import java.net.Socket;

public class WorkingThread implements Runnable {

    //TODO: LOGIKA RUCHÓW

    private BufferedReader in;
    private PrintWriter out;
    private Socket clientSocket;
    private String messageText;
    private long time = System.currentTimeMillis();

    WorkingThread(Socket clientSocket, String messageText){
        this.clientSocket = clientSocket;
        this.messageText = messageText;
    }

    @Override
    public void run() {
        try{
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            out.println("Server is working " + time + "  enter text: \n");
            while (true){
                messageText = in.readLine();
                System.out.println(messageText);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
