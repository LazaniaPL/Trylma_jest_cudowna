package ServerClient;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Server implements Runnable {

    protected int serverPort;
    protected ServerSocket serverSocket = null;
    protected volatile boolean  isClosed = false;
    protected Thread runningThread = null;
    protected ExecutorService threadPool;
    protected int playerNumber;

    Server(int port, int playerNumber) {
        this.serverPort = port;
        this.playerNumber = playerNumber;
        this.threadPool = Executors.newFixedThreadPool(playerNumber);
    }

    @Override
    public void run() {
        synchronized (this){
             this.runningThread = Thread.currentThread();
        }
        openServerSocket();
        while(! isClosed()){
         Socket clientSocket = null;
         try{
             clientSocket = this.serverSocket.accept();
         } catch (IOException e) {
             if(isClosed()){
                 System.out.println("SERVER STOPPED");
                 return;
             }throw new RuntimeException("ERROR ACCEPTING CLIENT", e);
         }
         //TODO: USTAWIĆ ITERATOR NA messegeText ZEBY ROZRÓZNIĆ MIĘDZY THREADAMI
         this.threadPool.execute(new WorkingThread(clientSocket, "Server"));
        }
        this.threadPool.shutdown();
        System.out.println("SERVER STOPPED");
    }

    public synchronized void stop(){
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