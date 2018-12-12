package ServerClient;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

import static ServerClient.Server.colours;
import static ServerClient.Server.writers;

public class WorkingThread implements Runnable {

    private Socket clientSocket;
    private int scale;
    private int players;



    private String move;


    WorkingThread(Socket clientSocket, int players, int scale){
        this.clientSocket = clientSocket;
        this.scale = scale;
        this.players = players;
    }

    @Override
    public void run() {
        try{
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String data = scale+""+players+" "+colours.get(0);
            colours.remove(0);
            out.println(data);
            writers.add(out);


            while(true) {
               if ((move = in.readLine()) != null) {
                   synchronized (this) {
                       System.out.println(move);
                       for (PrintWriter writer: writers) {
                           writer.println(move);
                           Thread.sleep(100);
                       }
                   }
               }
            }
        } catch (IOException | InterruptedException e){
                e.printStackTrace();

        }
    }
}
