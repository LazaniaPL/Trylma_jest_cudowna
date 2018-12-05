package ServerClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    //TODO: TWORZENIE OBEKTU PLANSZY, WRAZ Z PIONKAMI, DANY KLIENT MOŻE RUSZAĆ TYLKO JEDNYM ZESTAWEM PIONKÓW
    //TODO: KOLEJKOWANIE RUCHÓW - POMYSŁ - RUCH DOPIERO PO OTRZYMANIU ZEZWOLENIA OD SERVERA, WYŚWIETLANIE WSKAŹNIKA "TWÓJ RUCH"
    //TODO: WYSYŁANIE IDENTYFIKACJI RUCHU DO SERVERA

            //TODO: NORMALNA APLIKACJA , ZATWIERDZENIE RUCHU NADCHODZI OD SERVERA
            //NORMALNA APLIKACJA, TAK NAPRAWDĘ GRA SERVER































    public static void main(String args[]){
        try {
            Socket socket = new Socket("localhost", 8088);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream(),true);
            while (true){

                // testing
                String line = bufferedReader.readLine();
                System.out.println(line);
                Scanner input = new Scanner(System.in);
                String dziava = input.nextLine();
                printWriter.println(dziava);



            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
