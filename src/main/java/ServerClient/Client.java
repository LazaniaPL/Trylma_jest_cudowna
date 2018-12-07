package ServerClient;

import Trylma.TrylmaBuilder;
import Trylma.TrylmaPawns;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class Client extends Application  {

    //TODO: TWORZENIE OBEKTU PLANSZY, WRAZ Z PIONKAMI, DANY KLIENT MOŻE RUSZAĆ TYLKO JEDNYM ZESTAWEM PIONKÓW
    //TODO: KOLEJKOWANIE RUCHÓW - POMYSŁ - RUCH DOPIERO PO OTRZYMANIU ZEZWOLENIA OD SERVERA, WYŚWIETLANIE WSKAŹNIKA "TWÓJ RUCH"
    //TODO: WYSYŁANIE IDENTYFIKACJI RUCHU DO SERVERA

    //TODO: NORMALNA APLIKACJA , ZATWIERDZENIE RUCHU NADCHODZI OD SERVERA
    //NORMALNA APLIKACJA, TAK NAPRAWDĘ GRA SERVER

    public static final int TILE_SIZE = 20;
    private Group tileGroup = new Group();
    private Group pawnGroup = new Group();

    private Socket socket;

    public Client() {
        try {
            socket = new Socket("localhost", 8188);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private int getConnection() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String line = bufferedReader.readLine();
            return Integer.parseInt(line);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    private int getScale(int number){
        return (number - (number%10))/10;
    }

    private int getPlayers(int number){
        return number%10;
    }

    protected Pane makeMeBoard(int scale, int players) {

        Pane pane = new Pane();
        pane.setPrefSize((6 * scale + 1)*(1.5*TILE_SIZE),(1.5*TILE_SIZE)*(8 * scale + 1));
        TrylmaBuilder trylmaBuilder = new TrylmaBuilder(scale);
        TrylmaPawns trylmaPawns = new TrylmaPawns(players, trylmaBuilder.trylma, scale);
        makePiece(pane, scale, trylmaBuilder);


        return pane;
    }

    public Tile[][] makeTile(int scale){
        return new Tile[6 * scale + 1][8 * scale + 1];
    }

    public void makePiece(Pane pane, int scale, TrylmaBuilder trylma) {



        pane.setPrefSize((6 * scale + 1)*(1.5*TILE_SIZE),(1.5*TILE_SIZE)*(8 * scale + 1));
        pane.getChildren().addAll(tileGroup, pawnGroup);

        for (int i = 0; i < 6 * scale + 1; i++) {
            for (int j = 0; j < 4 * scale + 1; j++) {
                int temp = trylma.trylma[i][j];
                Tile[][] board = makeTile(scale);
                Pawn pawn = null;
                switch (temp) {
                    case 1:
                        Tile tileWhite = new Tile("WHITE",i,j);
                        board[i][j] = tileWhite;
                        tileGroup.getChildren().add(tileWhite);
                        break;
                    case 2:
                        Tile tileRed = new Tile("RED",i,j);
                        board[i][j] = tileRed;
                        tileGroup.getChildren().add(tileRed);
                        pawn = makePawn(PawnColors.RED,i,j,scale);
                        pawnGroup.getChildren().add(pawn);
                        break;
                    case 3:
                        Tile tileGreen = new Tile("GREEN",i,j);
                        board[i][j] = tileGreen;
                        tileGroup.getChildren().add(tileGreen);
                        pawn = makePawn(PawnColors.GREEN,i,j,scale);
                        pawnGroup.getChildren().add(pawn);
                        break;
                    case 4:
                        Tile tileYellow = new Tile("YELLOW",i,j);
                        board[i][j] = tileYellow;
                        tileGroup.getChildren().add(tileYellow);
                        pawn = makePawn(PawnColors.YELLOW,i,j,scale);
                        pawnGroup.getChildren().add(pawn);
                        break;
                    case 5:
                        Tile tilePurple = new Tile("PURPLE",i,j);
                        board[i][j] = tilePurple;
                        tileGroup.getChildren().add(tilePurple);
                        pawn = makePawn(PawnColors.PURPLE,i,j,scale);
                        pawnGroup.getChildren().add(pawn);
                        break;
                    case 6:
                        Tile tileBlue = new Tile("BLUE",i,j);
                        board[i][j] = tileBlue;
                        tileGroup.getChildren().add(tileBlue);
                        pawn = makePawn(PawnColors.BLUE,i,j,scale);
                        pawnGroup.getChildren().add(pawn);
                        break;
                    case 7:
                        Tile tileOlive = new Tile("OLIVE",i,j);
                        board[i][j] = tileOlive;
                        tileGroup.getChildren().add(tileOlive);
                        pawn = makePawn(PawnColors.OLIVE,i,j,scale);
                        pawnGroup.getChildren().add(pawn);
                        break;
                }
            }
        }
    }
        //TODO: ZABIĆ BUGI
    private int toBoard(double pixel){
        return (int)((pixel+TILE_SIZE/2)/TILE_SIZE);
    }

    private MoveResult tryMove(Pawn pawn, int newX, int newY, int scale){
        Tile[][] board = makeTile(scale);

        int x0 = toBoard(pawn.getOldX());
        int y0 = toBoard(pawn.getOldY());
        System.out.println(x0+"   k   "+y0);

        if (Math.abs(newX - x0)== 3 && newY - y0 == 0 ) {
            return new MoveResult(MoveType.NORMAL);
        }
        return new MoveResult(MoveType.NONE);
    }




    private  Pawn makePawn(PawnColors type , int x , int y, int scale){
        Tile[][] board = makeTile(scale);
        Pawn pawn = new Pawn(type,x,y);
        pawn.setOnMouseReleased(event -> {
                    int newX = toBoard(pawn.getLayoutX())-1;
                    int newY = toBoard(pawn.getLayoutY())-1;
                    System.out.println(newX+ "  "+ newY);


            MoveResult result = tryMove(pawn,newX,newY,scale);

            int x0 = toBoard(pawn.getOldX());
            int y0 = toBoard(pawn.getOldY());

            switch (result.getType()){

                case NONE:
                    pawn.abortMove();
                    break;
                case NORMAL:
                    pawn.move(newX,newY);
                    board[x0][y0].setPawn(null);
                    board[newX][newY].setPawn(pawn);
                    break;
                case JUMP:
                    break;
            }
        });
        return pawn;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        /*primaryStage.setTitle("Checkers");
        Client c = new Client();
        int number =  c.getConnection();
        int scale = c.getScale(number);
        int players = c.getPlayers(number);*/

        primaryStage.setScene(new Scene(makeMeBoard(6,6)));
        primaryStage.show();

    }

        public static void main(String args[]) {

        launch(args);

    }





}








