package ServerClient;

import java.net.SocketException;
import java.util.Scanner;

public class Main{

    //TODO: PEŁNA PĘTLA AŻ DO WPROWADZENIA SYGNAŁU DO ZAMKNIĘCIA

    public static void main(String[] args) throws Exception, SocketException {

        Server server = new Server(8088);
        //JAKIEŚ RANDOMOWE TESTOWANIE
        server.run();

    }
}
