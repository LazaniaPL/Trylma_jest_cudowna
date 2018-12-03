package ServerClient;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class Server implements Runnable {

    protected int serverPort = 6666;
    protected ServerSocket serverSocket = null;
    protected Thread runningThread = null;
    protected boolean isClosed = false;

    public Server(int port){
        this.serverPort = port;
    }

    @Override
    public void run() {
        synchronized (this){
            this.runningThread =  Thread.currentThread();
        }
        openServerSocket();
        while(!isClosed()){
         Socket clientSocket = null;
         try{
             clientSocket = this.serverSocket.accept();
         } catch (IOException e) {
             if(isClosed()){
                 System.out.println("SERVER STOPPED");
                 return;
             }
             throw new RuntimeException("ERROR ACCEPTING CLIENT", e);
         }
         new Thread(new Client(clientSocket, "MULTICULTISERVER")).start();
        }
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