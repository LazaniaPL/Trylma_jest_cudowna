package ServerClient;

import Trylma.TrylmaBuilder;
import Trylma.TrylmaPawns;
import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.StringTokenizer;


public class Client extends Application {

    private BufferedReader bufferedReader;

    static final int TILE_SIZE = 10;
    private Group tileGroup = new Group();
    private Group pawnGroup = new Group();

    private Socket socket;


    public Client() {
        try {
            socket = new Socket("localhost", 8188);
        } catch (IOException e) {
            e.printStackTrace();
        }
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


    private int getScale(int number) {
        return (number - (number % 10)) / 10;
    }

    private int getPlayers(int number) {
        return number % 10;
    }

    private Pane makeMeBoard(int scale, int players, String colour, VBox vBox) {

        Pane pane = new Pane();
        pane.setPrefSize((6 * scale + 1) * (1.5 * TILE_SIZE), (1.5 * TILE_SIZE) * (8 * scale + 1));
        TrylmaBuilder trylmaBuilder = new TrylmaBuilder(scale);
        new TrylmaPawns(players, trylmaBuilder.trylma, scale);
        makePiece(pane, scale, trylmaBuilder, colour);
        pane.getChildren().add(vBox);

        return pane;
    }

    private Tile[][] board = new Tile[120][160];

    private void makePiece(Pane pane, int scale, TrylmaBuilder trylma, String colour) {


        pane.setPrefSize((8 * scale + 1) * (1.5 * TILE_SIZE), (1.5 * TILE_SIZE) * (6 * scale + 1));
        pane.getChildren().addAll(tileGroup, pawnGroup);

        for (int i = 0; i < 6 * scale + 1; i++) {
            for (int j = 0; j < 4 * scale + 1; j++) {
                int temp = trylma.trylma[i][j];
                Pawn pawn = null;
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

    private int toBoard(double pixel) {
        return (int) ((pixel + TILE_SIZE / 2) / TILE_SIZE);
    }


    //todo: przenieść do serwera i board też
    private MoveResult tryMove(Pawn pawn, int newX, int newY, String colour) {

        try {

            if (board[newX / 2][newY / 2].hasPawn()) {
                return new MoveResult(MoveType.NONE);
            }

            int x0 = toBoard(pawn.getOldX());
            int y0 = toBoard(pawn.getOldY());


            if ((((Math.abs(newX - x0) == 4 && newY - y0 == 0) || ((Math.abs(newX - x0)) == 2 && (Math.abs(newX - x0) == 2))) && pawn.getType().name().equals(colour))) {


                return new MoveResult(MoveType.NORMAL);

            } else if ((Math.abs(newX - x0) == 8 && newY - y0 == 0) || ((Math.abs(newX - x0)) == 4 && (Math.abs(newX - x0) == 4))) {

                int x1 = x0 + (newX - x0) / 2;
                int y1 = y0 + (newY - y0) / 2;

                if ((board[x1 / 2][y1 / 2].hasPawn()) && (pawn.getType().name().equals(colour))) {

                    return new MoveResult(MoveType.NORMAL);
                }
            }

        } catch (NullPointerException e) {
            return new MoveResult(MoveType.NONE);
        }
        return new MoveResult(MoveType.NONE);
    }

    /*
        private Pawn makePawn(PawnColors type, int x, int y, String colour) {
            Pawn pawn = new Pawn(type, x, y);
            pawn.setOnMouseReleased(event -> {
                int newX = toBoard(pawn.getLayoutX()) - 1;
                int newY = toBoard(pawn.getLayoutY()) - 1;
                System.out.println(newX / 2 + "  " + newY / 2);

                MoveResult result = tryMove(pawn, newX, newY, colour);

                int x0 = toBoard(pawn.getOldX());
                int y0 = toBoard(pawn.getOldY());
                System.out.println(x0 / 2 + " OLD " + y0 / 2);

                switch (result.getType()) {

                    case NONE:
                        pawn.abortMove();
                        break;
                    case NORMAL:
                        pawn.abortMove();
                        try {
                            PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
                            printWriter.println(pawn.getType() + " " + colour + " " + x0 / 2 + " " + y0 / 2 + " " + newX / 2 + " " + newY / 2);
                            Thread.sleep(300);
                        } catch (IOException | InterruptedException e) {
                            e.printStackTrace();
                        }
                        break;
                }
            });
            return pawn;
        }
    */
    private Pawn makePawn(PawnColors type, int x, int y, String colour) {
        Pawn pawn = new Pawn(type, x, y);
        pawn.setOnMouseReleased(event -> {
            int newX = toBoard(pawn.getLayoutX()) - 1;
            int newY = toBoard(pawn.getLayoutY()) - 1;
            System.out.println(newX / 2 + "  " + newY / 2);

            //MoveResult result = tryMove(pawn, newX, newY, colour);

            int x0 = toBoard(pawn.getOldX());
            int y0 = toBoard(pawn.getOldY());
            System.out.println(x0 / 2 + " OLD " + y0 / 2);
            Check check = new Check(x0/2, y0/2, board);
            //System.out.println(check.returnRealMoves());
            pawn.abortMove();
            for (Pair<Integer, Integer> p : check.returnRealMoves()) {
                int l = p.getKey();
                int r = p.getValue();
                System.out.println(l + " + " + r);
                if (l*2 == newX && r*2 == newY) {
                    try {
                        System.out.println("poszło");
                        PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
                        printWriter.println(pawn.getType() + " " + colour + " " + x0 / 2 + " " + y0 / 2 + " " + newX / 2 + " " + newY / 2);
                        Thread.sleep(300);
                    } catch (IOException | InterruptedException e) {
                        e.printStackTrace();
                    }

                } else
                    pawn.abortMove();
            }

        });
        return pawn;
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
                String colour = tokenizer.nextToken();
                int oldX = Integer.parseInt(tokenizer.nextToken());
                int oldY = Integer.parseInt(tokenizer.nextToken());
                int newX = Integer.parseInt(tokenizer.nextToken());
                int newY = Integer.parseInt(tokenizer.nextToken());
                Pawn pawn = board[oldX][oldY].getPawn();
                pawn.move(newX, newY);
                board[oldX][oldY].setPawn(null);
                board[newX][newY].setPawn(pawn);
            }
        }
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        Client c = new Client();
        String token = getConnection();
        String colour = getColour(token);
        System.out.println(colour);
        primaryStage.setTitle("CHECKERS " + colour);
        int number = c.getNumber(token);
        int scale = c.getScale(number);
        int players = c.getPlayers(number);
        ClientTask task = new ClientTask();
        new Thread(task).start();
        //todo bo widać to w innym kolorze XD

        ToolBar toolBar = new ToolBar();
        Button button1 = new Button("Koniec tury");
        button1.setOnAction(event -> {

            WorkingThread.addI(WorkingThread.getI() + 1);
            System.out.println(WorkingThread.getI() + 1);

        });

        toolBar.getItems().addAll(button1);
        VBox vbox = new VBox(toolBar);
        primaryStage.setScene(new Scene(makeMeBoard(scale, players, colour, vbox)));
        primaryStage.show();

    }

    public static void main(String args[]) {

        launch(args);

    }


}
