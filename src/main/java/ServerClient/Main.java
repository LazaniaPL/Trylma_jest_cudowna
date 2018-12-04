package ServerClient;

public class Main{

    public static void main(String[] args) throws Exception {

        Server server = new Server(8080);
        new Thread(server).start();

        try{
            Thread.sleep( 80* 1000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println("SERVER STOPPED");
        server.stop();
    }
}
