package ServerClient;

import Trylma.TrylmaBuilder;
import Trylma.TrylmaPawns;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;


public class TrylmaGridPane {

    protected static String data;
        private Socket socket;

    TrylmaGridPane(Socket socket){
        this.socket=socket;
    }


}