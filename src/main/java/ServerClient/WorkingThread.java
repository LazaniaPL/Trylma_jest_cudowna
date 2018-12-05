package ServerClient;

import java.io.*;
import java.net.Socket;

public class WorkingThread implements Runnable {

    //TODO: LOGIKA RUCHÓW

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
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            out.println("Server is working " + time + "  enter text: \n");
            while (true){
                System.out.println(messageText);
                if( messageText.equals("not")){

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
