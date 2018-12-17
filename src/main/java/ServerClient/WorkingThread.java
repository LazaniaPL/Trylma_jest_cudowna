package ServerClient;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;


import static ServerClient.Server.colours;
import static ServerClient.Server.writers;

public class WorkingThread implements Runnable {


    private static ArrayList<String> kolorki2 = new ArrayList<String>(Arrays.asList("PURPLE", "RED"));
    private static ArrayList<String> kolorki3 = new ArrayList<String>(Arrays.asList("YELLOW", "RED", "BLUE"));
    private static ArrayList<String> kolorki4 = new ArrayList<String>(Arrays.asList("YELLOW", "GREEN", "OLIVE", "BLUE"));

    private static ArrayList<String> kolorki6 = new ArrayList<String>(Arrays.asList("PURPLE", "YELLOW", "GREEN", "RED", "OLIVE", "BLUE"));
    private static ArrayList<String> kolorki = null;
    private static int i = 0;

    private Socket clientSocket;
    private int scale;
    private int players;


    WorkingThread(Socket clientSocket, int players, int scale) {
        this.clientSocket = clientSocket;
        this.scale = scale;
        this.players = players;
    }

    @Override
    public void run() {
        try {
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String data = scale + "" + players + " " + colours.get(0);
            colours.remove(0);
            out.println(data);
            writers.add(out);

            switch (players) {
                case 2:
                    kolorki = kolorki2;
                    break;
                case 3:
                    kolorki = kolorki3;
                    break;
                case 4:
                    kolorki = kolorki4;
                    break;
                case 6:
                    kolorki = kolorki6;
                    break;
            }

            while (true) {
                String move;
                if ((move = in.readLine()) != null) {
                    synchronized (this) {
                        System.out.println(move);
                        StringTokenizer tokenizer = new StringTokenizer(move);
                        String type = tokenizer.nextToken();


                        if (type.equals(kolorki.get(i))) {
                            for (PrintWriter writer : writers) {
                                writer.println(move);
                                Thread.sleep(20);
                            }
                            i++;
                            if (i == players) {
                                i = 0;
                            }
                        }

                    }
                }
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();

        }
    }
}
