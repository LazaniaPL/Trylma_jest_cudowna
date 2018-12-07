package ServerClient;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class WorkingThread implements Runnable {

    //TODO: LOGIKA RUCHÓW

    private Socket clientSocket;
    private int scale;
    private int players;

    WorkingThread(Socket clientSocket, int players, int scale){
        this.clientSocket = clientSocket;
        this.scale = scale;
        this.players = players;
    }

    @Override
    public void run() {
        try{
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            out.println(scale+""+players);
            while (true){

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
