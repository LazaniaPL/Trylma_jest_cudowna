package ServerClient;

import Trylma.TrylmaBuilder;
import Trylma.TrylmaPawns;
import javafx.util.Pair;

import java.io.*;
import java.lang.reflect.Array;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;


import static ServerClient.Server.colours;
import static ServerClient.Server.writers;
import static ServerClient.Server.i;

public class WorkingThread implements Runnable {

    //private static ArrayList<String> colours;
    private static ArrayList<String> kolorki = null;
    //private static int i = 0;

    private Socket clientSocket;
    private int scale;
    private int players;
    // private Tile[][] board = new Tile[120][160];

    WorkingThread(Socket clientSocket, int players, int scale) {
        this.clientSocket = clientSocket;
        this.scale = scale;
        this.players = players;


    }

    @Override
    public void run() {
        try {
            //ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            //ObjectInputStream ois=new ObjectInputStream(clientSocket.getInputStream());
            //ArrayList<Serializable> arrayList=new ArrayList<>();
            //arrayList=(ArrayList<Serializable>) ois.readObject();

            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            String data = scale + "" + players + " " + colours.get(0) + " " + i;
            colours.remove(0);
            out.println(data);
            writers.add(out);


            switch (players) {
                case 2:
                    kolorki = new ArrayList<String>(Arrays.asList("PURPLE", "RED"));
                    break;
                case 3:
                    kolorki = new ArrayList<String>(Arrays.asList("YELLOW", "RED", "BLUE"));

                    break;
                case 4:
                    kolorki = new ArrayList<String>(Arrays.asList("YELLOW", "GREEN", "OLIVE", "BLUE"));

                    break;
                case 6:
                    kolorki = new ArrayList<String>(Arrays.asList("PURPLE", "YELLOW", "GREEN", "RED", "OLIVE", "BLUE"));
                    break;
            }

//            TrylmaBuilder trylmaBuilder = new TrylmaBuilder(scale);

            //          make(scale, trylmaBuilder);


            while (true) {
                String move;
                if ((move = in.readLine()) != null) {
                    synchronized (this) {

                        System.out.println(move);
                        move = move + " " + i;
                        StringTokenizer tokenizer = new StringTokenizer(move);
                        String type = tokenizer.nextToken();
                        if (type.equals("false")) {
                            String colour = tokenizer.nextToken();
                            if (colour.equals(kolorki.get(i))) {
                                for (PrintWriter writer : writers) {
                                    writer.println(move);
                                    Thread.sleep(20);

                                }


                                i++;
                                //System.out.println(players);
                                if (i == players) {
                                    i = 0;
                                }
                            }
                        }
                        /*
                        Check check = new Check(x0, y0, board);
                        //System.out.println(check.returnRealMoves());

                        for (Pair<Integer, Integer> p : check.returnRealMoves()) {
                            int l = p.getKey();
                            int r = p.getValue();

                            if (l * 2 == newX && r * 2 == newY) {
                                move = type + " " + x0 + " " + y0 + " " + newX + " " + newY;
                            } else {
                                move = type + " " + x0 + " " + y0 + " " + x0 + " " + y0;
                            }
                        }
*/
                        else {
                            if (type.equals(kolorki.get(i))) {

                                for (PrintWriter writer : writers) {
                                    writer.println(move);
                                    Thread.sleep(20);

                                }


                                i++;
                                //System.out.println(players);
                                if (i == players) {
                                    i = 0;
                                }
                            }
                        }
                    }
                }
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();

        }
    }

    public static int getI() {
        return i;
    }
/*
    private void make(int scale, TrylmaBuilder trylma) {
        for (int i = 0; i < 6 * scale + 1; i++) {
            for (int j = 0; j < 4 * scale + 1; j++) {
                int temp = trylma.trylma[i][j];
                switch (temp) {
                    case 1:
                        Tile tileWhite = new Tile("WHITE", i, j);
                        board[i][j] = tileWhite;
                        break;
                    case 2:
                        Tile tileRed = new Tile("RED", i, j);
                        board[i][j] = tileRed;
                        break;
                    case 3:
                        Tile tileGreen = new Tile("GREEN", i, j);
                        board[i][j] = tileGreen;
                        break;
                    case 4:
                        Tile tileYellow = new Tile("YELLOW", i, j);
                        board[i][j] = tileYellow;
                        break;
                    case 5:
                        Tile tilePurple = new Tile("PURPLE", i, j);
                        board[i][j] = tilePurple;
                        break;
                    case 6:
                        Tile tileBlue = new Tile("BLUE", i, j);
                        board[i][j] = tileBlue;
                        break;
                    case 7:
                        Tile tileOlive = new Tile("OLIVE", i, j);
                        board[i][j] = tileOlive;
                        break;
                }
            }

        }
    }
    */

}
