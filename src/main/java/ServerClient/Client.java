package ServerClient;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


import static ServerClient.TrylmaGridPane.makeMeBoard;

public class Client extends Application  {

    //TODO: TWORZENIE OBEKTU PLANSZY, WRAZ Z PIONKAMI, DANY KLIENT MOŻE RUSZAĆ TYLKO JEDNYM ZESTAWEM PIONKÓW
    //TODO: KOLEJKOWANIE RUCHÓW - POMYSŁ - RUCH DOPIERO PO OTRZYMANIU ZEZWOLENIA OD SERVERA, WYŚWIETLANIE WSKAŹNIKA "TWÓJ RUCH"
    //TODO: WYSYŁANIE IDENTYFIKACJI RUCHU DO SERVERA

    //TODO: NORMALNA APLIKACJA , ZATWIERDZENIE RUCHU NADCHODZI OD SERVERA
    //NORMALNA APLIKACJA, TAK NAPRAWDĘ GRA SERVER




    public static void main(String args[]) {
       launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            Socket socket = new Socket("localhost", 8188);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream(),true);

            String line = bufferedReader.readLine();
            int number = Integer.parseInt(line);
            int players = number%10;
            int scale = (number-players)/10;


            primaryStage.setTitle("Checkers");
            primaryStage.setScene(new Scene(makeMeBoard(scale,players)));
            primaryStage.show();




        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}








