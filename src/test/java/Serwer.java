import ServerClient.MainWindow;
import ServerClient.Server;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;
public class Serwer {

    @Test
    void startSerwer(){
        MainWindow server=new MainWindow();
        server.setRunning(4,4,0);
        assertNotNull(server.server);
    }

}
