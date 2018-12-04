package ServerClient;

public class Main{

    //TODO: PEŁNA PĘTLA AŻ DO WPROWADZENIA SYGNAŁU DO ZAMKNIĘCIA

    public static void main(String[] args) throws Exception {

        Server server = new Server(8080);
        new Thread(server).start();
        //JAKIEŚ RANDOMOWE TESTOWANIE
        try{
            Thread.sleep( 80* 1000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println("SERVER STOPPED");
        server.stop();
    }
}
