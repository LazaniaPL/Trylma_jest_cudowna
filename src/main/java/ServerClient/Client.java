package ServerClient;

import Trylma.TrylmaBuilder;
import Trylma.TrylmaPawns;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class Client extends Application  {


    private BufferedReader bufferedReader;

    static final int TILE_SIZE = 20;
    private Group tileGroup = new Group();
    private Group pawnGroup = new Group();

    private Socket socket;

    //TODO: NETWORKING PART
    public Client() {
        try {
            socket = new Socket("localhost", 8188);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private int getConnection() {
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String data = bufferedReader.readLine();
            return Integer.parseInt(data);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }
//TODO: NETWORKING PART

    private int getScale(int number){
        return (number - (number%10))/10;
    }

    private int getPlayers(int number){
        return number%10;
    }

    private Pane makeMeBoard(int scale, int players) {

        Pane pane = new Pane();
        pane.setPrefSize((6 * scale + 1)*(1.5*TILE_SIZE),(1.5*TILE_SIZE)*(8 * scale + 1));
        TrylmaBuilder trylmaBuilder = new TrylmaBuilder(scale);
        TrylmaPawns trylmaPawns = new TrylmaPawns(players, trylmaBuilder.trylma, scale);
        makePiece(pane, scale, trylmaBuilder);


        return pane;
    }

    private Tile[][] board = new Tile[120][160];

    private void makePiece(Pane pane, int scale, TrylmaBuilder trylma) {



        pane.setPrefSize((6 * scale + 1)*(1.5*TILE_SIZE),(1.5*TILE_SIZE)*(8 * scale + 1));
        pane.getChildren().addAll(tileGroup, pawnGroup);

        for (int i = 0; i < 6 * scale + 1; i++) {
            for (int j = 0; j < 4 * scale + 1; j++) {
                int temp = trylma.trylma[i][j];
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
                        pawn = makePawn(PawnColors.RED,i,j);
                        tileRed.setPawn(pawn);
                        pawnGroup.getChildren().add(pawn);
                        break;
                    case 3:
                        Tile tileGreen = new Tile("GREEN",i,j);
                        board[i][j] = tileGreen;
                        tileGroup.getChildren().add(tileGreen);
                        pawn = makePawn(PawnColors.GREEN,i,j);
                        tileGreen.setPawn(pawn);
                        pawnGroup.getChildren().add(pawn);
                        break;
                    case 4:
                        Tile tileYellow = new Tile("YELLOW",i,j);
                        board[i][j] = tileYellow;
                        tileGroup.getChildren().add(tileYellow);
                        pawn = makePawn(PawnColors.YELLOW,i,j);
                        tileYellow.setPawn(pawn);
                        pawnGroup.getChildren().add(pawn);
                        break;
                    case 5:
                        Tile tilePurple = new Tile("PURPLE",i,j);
                        board[i][j] = tilePurple;
                        tileGroup.getChildren().add(tilePurple);
                        pawn = makePawn(PawnColors.PURPLE,i,j);
                        tilePurple.setPawn(pawn);
                        pawnGroup.getChildren().add(pawn);
                        break;
                    case 6:
                        Tile tileBlue = new Tile("BLUE",i,j);
                        board[i][j] = tileBlue;
                        tileGroup.getChildren().add(tileBlue);
                        pawn = makePawn(PawnColors.BLUE,i,j);
                        tileBlue.setPawn(pawn);
                        pawnGroup.getChildren().add(pawn);
                        break;
                    case 7:
                        Tile tileOlive = new Tile("OLIVE",i,j);
                        board[i][j] = tileOlive;
                        tileGroup.getChildren().add(tileOlive);
                        pawn = makePawn(PawnColors.OLIVE,i,j);
                        tileOlive.setPawn(pawn);
                        pawnGroup.getChildren().add(pawn);
                        break;
                }
            }
        }
    }

    private int toBoard(double pixel){
        return (int)((pixel+TILE_SIZE/2)/TILE_SIZE);
    }

    private MoveResult tryMove(Pawn pawn, int newX, int newY){

        try {
            if (board[newX / 2][newY / 2].hasPawn()) {
                return new MoveResult(MoveType.NONE);
            }

            int x0 = toBoard(pawn.getOldX());
            int y0 = toBoard(pawn.getOldY());

            if ((Math.abs(newX - x0) == 4 && newY - y0 == 0) || ((Math.abs(newX - x0)) == 2 && (Math.abs(newX - x0) == 2))) {
                return new MoveResult(MoveType.NORMAL);
            } else if ((Math.abs(newX - x0) == 8 && newY - y0 == 0) || ((Math.abs(newX - x0)) == 4 && (Math.abs(newX - x0) == 4))) {

                int x1 = x0 + (newX - x0) / 2;
                int y1 = y0 + (newY - y0) / 2;

                if (board[x1 / 2][y1 / 2].hasPawn() && board[x1 / 2][y1 / 2].getPawn().getType() != pawn.getType()) {
                    return new MoveResult(MoveType.JUMP);
                }
            }
        }catch (NullPointerException e) {
            return new MoveResult(MoveType.NONE);
        }
        return new MoveResult(MoveType.NONE);
    }

    private  Pawn makePawn(PawnColors type , int x , int y){
        Pawn pawn = new Pawn(type,x,y);
        pawn.setOnMouseReleased(event -> {
                    int newX = toBoard(pawn.getLayoutX())-1;
                    int newY = toBoard(pawn.getLayoutY())-1;
                    System.out.println(newX/2+ "  "+ newY/2);


            MoveResult result = tryMove(pawn,newX,newY);

            int x0 = toBoard(pawn.getOldX());
            int y0 = toBoard(pawn.getOldY());
            System.out.println(x0/2+" OLD "+y0/2);

            try {
                PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
                printWriter.println(x0/2+"  "+y0/2+" new: "+newX/2+"   "+newY/2+"\n");
                bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String move = bufferedReader.readLine();
                System.out.println(move);
            } catch (IOException e) {
                e.printStackTrace();
            }

            switch (result.getType()){

                case NONE:
                    pawn.abortMove();
                    break;
                case NORMAL:
                    pawn.move(newX / 2, newY / 2);
                    board[x0 / 2][y0 / 2].setPawn(null);
                    board[newX / 2][newY / 2].setPawn(pawn);
                    break;
                case JUMP:
                    pawn.move(newX/2,newY/2);
                    board[x0/2][y0/2].setPawn(null);
                    board[newX/2][newY/2].setPawn(pawn);
                    break;
            }
        });
        return pawn;
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Checkers");
        Client c = new Client();
        int number =  c.getConnection();
        int scale = c.getScale(number);
        int players = c.getPlayers(number);
        primaryStage.setScene(new Scene(makeMeBoard(scale,players)));
        primaryStage.show();

    }

        public static void main(String args[]) {

        launch(args);

    }





}








