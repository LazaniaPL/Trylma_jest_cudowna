package ServerClient;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Server implements Runnable {


    static ArrayList<PrintWriter> writers = new ArrayList<>();
    static ArrayList<String> colours = new ArrayList<String>(Arrays.asList("PURPLE","", "RED","", "OLIVE","", "YELLOW","", "BLUE","", "GREEN"));

    private int serverPort;
    private ServerSocket serverSocket = null;
    private boolean  isClosed = false;
    private ExecutorService threadPool;
    private int playerNumber;
    protected int scale;

    Server(int port, int playerNumber, int scale) {
        this.serverPort = port;
        this.playerNumber = playerNumber;
        this.scale = scale;
        this.threadPool = Executors.newFixedThreadPool(playerNumber*2);
    }

    @Override
    public void run() {
        synchronized (this){
            Thread runningThread = Thread.currentThread();
        }
        openServerSocket();
        while(! isClosed()){
         Socket clientSocket = null;
         try{
             clientSocket = this.serverSocket.accept();
                Thread.sleep(50);
         } catch (IOException e) {
             if(isClosed()){
                 System.out.println("SERVER STOPPED");
                 return;
             }throw new RuntimeException("ERROR ACCEPTING CLIENT", e);
         } catch (InterruptedException e) {
             e.printStackTrace();
         }
         this.threadPool.execute(new WorkingThread(clientSocket,playerNumber, scale));
        }
        this.threadPool.shutdown();
        System.out.println("SERVER STOPPED");
    }

    synchronized void stop(){
        this.isClosed = true;
        try {
            this.serverSocket.close();
        } catch (IOException e) {
            throw new RuntimeException("ERROR CLOSING SERVER", e);
        }

    }

    private void openServerSocket() {
        try {
            this.serverSocket = new ServerSocket(this.serverPort);
        } catch (IOException e) {
            throw new RuntimeException("cannot open port: " + serverPort, e);

        }
    }
    private synchronized boolean isClosed(){
        return this.isClosed;
    }

}