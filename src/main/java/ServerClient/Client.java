package ServerClient;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {

//testing testing 123

    public static void main(String args[]){
        try {
            Socket socket = new Socket("localhost", 8080);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream(),true);
            while (true){
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
