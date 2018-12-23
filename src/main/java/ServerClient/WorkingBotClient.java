package ServerClient;

import Trylma.TrylmaBuilder;
import Trylma.TrylmaPawns;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.StringTokenizer;
import java.util.concurrent.ThreadLocalRandom;

public class WorkingBotClient extends Application {

    private BufferedReader bufferedReader;

    static final int TILE_SIZE = 10;
    private Group tileGroup = new Group();
    private Group pawnGroup = new Group();

    private Socket socket;
    private int global_players;
    private String colour;

    private Label label = new Label();
    private Tile[][] board = new Tile[120][160];

    public WorkingBotClient() {
        try {
            socket = new Socket("localhost", 8188);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    class ClientTask extends Task {

        @Override
        protected Object call() throws Exception {
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            while (true) {
                Thread.sleep(300);
                String toTokenise = bufferedReader.readLine();
                StringTokenizer tokenizer = new StringTokenizer(toTokenise);
                String type = tokenizer.nextToken();

                int oldX = Integer.parseInt(tokenizer.nextToken());
                int oldY = Integer.parseInt(tokenizer.nextToken());
                int newX = Integer.parseInt(tokenizer.nextToken());
                int newY = Integer.parseInt(tokenizer.nextToken());
                int kogoTura = Integer.parseInt(tokenizer.nextToken());


                Pawn pawn = board[oldX][oldY].getPawn();
                pawn.move(newX, newY);
                board[oldX][oldY].setPawn(null);
                board[newX][newY].setPawn(pawn);
            }
        }
    }

    private void bot() {
        while (true) {
            int randomNum = ThreadLocalRandom.current().nextInt(0, pawnGroup.getChildren().size());
            Pawn pawn = (Pawn) pawnGroup.getChildren().get(randomNum);
            int x0 = (int) pawn.getOldX();
            int y0 = (int) pawn.getOldY();
            Check check = new Check(x0, y0, board);
            if (!pawn.getType().name().equals(colour)) {
                pawn.abortMove();
            } else {

                pawn.abortMove();
                int l = 0;
                int r = 0;
                for (Pair<Integer, Integer> p : check.returnRealMoves()) {
                    l = p.getKey();
                    r = p.getValue();

                }
                try {
                    PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
                    printWriter.println(pawn.getType() + " " + x0 / 2 + " " + y0 / 2 + " " + l + " " + r);
                    Thread.sleep(300);
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }


            }
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        WorkingBotClient c = new WorkingBotClient();
        String token = getConnection();
        colour = getColour(token);
        int i = Integer.parseInt(getI(token));
        int number = c.getNumber(token);
        int scale = c.getScale(number);
        int players = c.getPlayers(number);
        global_players = players;
        ClientTask task = new ClientTask();
        new Thread(task).start();
        TrylmaBuilder trylmaBuilder = new TrylmaBuilder(scale);
        new TrylmaPawns(players, trylmaBuilder.trylma, scale);
        makePiece(scale, trylmaBuilder, colour);
        bot();
    }

    private int toBoard(double pixel) {
        return (int) ((pixel + TILE_SIZE / 2) / TILE_SIZE);
    }

    private Pawn makePawn(PawnColors type, int x, int y, String colour) {
        Pawn pawn = new Pawn(type, x, y);
        /*
        pawn.setOnMouseReleased(event -> {
            int newX = toBoard(pawn.getLayoutX()) - 1;
            int newY = toBoard(pawn.getLayoutY()) - 1;
            System.out.println(newX / 2 + "  " + newY / 2);

            //MoveResult result = tryMove(pawn, newX, newY, colour);

            int x0 = toBoard(pawn.getOldX());
            int y0 = toBoard(pawn.getOldY());
            System.out.println(x0 / 2 + " OLD " + y0 / 2);
            Check check = new Check(x0 / 2, y0 / 2, board);
            //System.out.println(check.returnRealMoves());
            if (!pawn.getType().name().equals(colour)) {
                pawn.abortMove();
            } else {

                pawn.abortMove();
                for (Pair<Integer, Integer> p : check.returnRealMoves()) {
                    int l = p.getKey();
                    int r = p.getValue();

                    if (l * 2 == newX && r * 2 == newY) {
                        try {
                            PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
                            printWriter.println(pawn.getType() + " " + x0 / 2 + " " + y0 / 2 + " " + newX / 2 + " " + newY / 2);
                            Thread.sleep(300);
                        } catch (IOException | InterruptedException e) {
                            e.printStackTrace();
                        }

                    } else
                        pawn.abortMove();
                }
            }
        });
        */
        return pawn;
    }

    private void makePiece(int scale, TrylmaBuilder trylma, String colour) {
        for (int i = 0; i < 6 * scale + 1; i++) {
            for (int j = 0; j < 4 * scale + 1; j++) {
                int temp = trylma.trylma[i][j];
                Pawn pawn;
                switch (temp) {
                    case 1:
                        Tile tileWhite = new Tile("WHITE", i, j);
                        board[i][j] = tileWhite;
                        tileGroup.getChildren().add(tileWhite);
                        break;
                    case 2:
                        Tile tileRed = new Tile("RED", i, j);
                        board[i][j] = tileRed;
                        tileGroup.getChildren().add(tileRed);
                        pawn = makePawn(PawnColors.RED, i, j, colour);
                        tileRed.setPawn(pawn);
                        pawnGroup.getChildren().add(pawn);
                        break;
                    case 3:
                        Tile tileGreen = new Tile("GREEN", i, j);
                        board[i][j] = tileGreen;
                        tileGroup.getChildren().add(tileGreen);
                        pawn = makePawn(PawnColors.GREEN, i, j, colour);
                        tileGreen.setPawn(pawn);
                        pawnGroup.getChildren().add(pawn);
                        break;
                    case 4:
                        Tile tileYellow = new Tile("YELLOW", i, j);
                        board[i][j] = tileYellow;
                        tileGroup.getChildren().add(tileYellow);
                        pawn = makePawn(PawnColors.YELLOW, i, j, colour);
                        tileYellow.setPawn(pawn);
                        pawnGroup.getChildren().add(pawn);
                        break;
                    case 5:
                        Tile tilePurple = new Tile("PURPLE", i, j);
                        board[i][j] = tilePurple;
                        tileGroup.getChildren().add(tilePurple);
                        pawn = makePawn(PawnColors.PURPLE, i, j, colour);
                        tilePurple.setPawn(pawn);
                        pawnGroup.getChildren().add(pawn);
                        break;
                    case 6:
                        Tile tileBlue = new Tile("BLUE", i, j);
                        board[i][j] = tileBlue;
                        tileGroup.getChildren().add(tileBlue);
                        pawn = makePawn(PawnColors.BLUE, i, j, colour);
                        tileBlue.setPawn(pawn);
                        pawnGroup.getChildren().add(pawn);
                        break;
                    case 7:
                        Tile tileOlive = new Tile("OLIVE", i, j);
                        board[i][j] = tileOlive;
                        tileGroup.getChildren().add(tileOlive);
                        pawn = makePawn(PawnColors.OLIVE, i, j, colour);
                        tileOlive.setPawn(pawn);
                        pawnGroup.getChildren().add(pawn);
                        break;
                }
            }
        }
    }

    private int getScale(int number) {
        return (number - (number % 10)) / 10;
    }

    private int getPlayers(int number) {
        return number % 10;
    }

    private String getConnection() {
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            return bufferedReader.readLine();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private int getNumber(String token) {
        StringTokenizer tokenizer = new StringTokenizer(token);
        return Integer.parseInt(tokenizer.nextToken());
    }

    private String getColour(String token) {
        StringTokenizer tokenizer = new StringTokenizer(token);
        tokenizer.nextToken();
        return tokenizer.nextToken();
    }

    private String getI(String token) {
        StringTokenizer tokenizer = new StringTokenizer(token);
        tokenizer.nextToken();
        tokenizer.nextToken();
        return tokenizer.nextToken();
    }
}
