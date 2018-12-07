package ServerClient;

import Trylma.TrylmaBuilder;
import Trylma.TrylmaPawns;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

import java.net.Socket;


public class TrylmaGridPane {

    private static String data;

    protected TrylmaGridPane(){
        dataToMain();
    }

    protected String dataToMain(){
        return data;
    }



    protected static GridPane makeMeBoard(int scale, int players) {


        GridPane gridPane = new GridPane();
        gridPane.setVgap(10);
        TrylmaBuilder trylmaBuilder = new TrylmaBuilder(scale);
        TrylmaPawns trylmaPawns = new TrylmaPawns(players, trylmaBuilder.trylma, scale);
        Button[][] btn1 = new Button[6 * scale + 1][4 * scale + 1];
        makePiece(gridPane, scale, trylmaBuilder, btn1);

        return gridPane;
    }

    public static void makePiece(GridPane gridPane, int scale, Trylma.TrylmaBuilder trylmaBuilder, Button[][] btn1) {
        for (int i = 0; i < 6 * scale + 1; i++) {
            for (int j = 0; j < 4 * scale + 1; j++) {
                final int varX = i;
                final int varY = j;
                int temp = trylmaBuilder.trylma[i][j];
                final int colorPawn = temp;
                switch (temp) {
                    case 1:
                        btn1[i][j] = new Button();
                        btn1[i][j].setPrefSize(30, 30);
                        gridPane.add(btn1[i][j], i, j);
                        btn1[i][j].setOnMouseClicked((MouseEvent e) -> {
                            data = colorPawn+""+varX+""+varY;
                            System.out.println(data);
                        });
                        break;
                    case 2:
                        btn1[i][j] = new Button();
                        btn1[i][j].setStyle("-fx-background-color: #7C0A02; ");
                        btn1[i][j].setPrefSize(30, 30);
                        gridPane.add(btn1[i][j], i, j);
                        btn1[i][j].setOnMouseClicked((MouseEvent e) -> {
                            data = colorPawn+""+varX+""+varY;
                            System.out.println(data);
                        });
                        break;
                    case 3:
                        btn1[i][j] = new Button();
                        btn1[i][j].setStyle("-fx-background-color: #131313; ");
                        btn1[i][j].setPrefSize(30, 30);
                        gridPane.add(btn1[i][j], i, j);
                        btn1[i][j].setOnMouseClicked((MouseEvent e) -> {
                            data = colorPawn+""+varX+""+varY;
                            System.out.println(data);
                        });
                        break;
                    case 4:
                        btn1[i][j] = new Button();
                        btn1[i][j].setStyle("-fx-background-color: #008b8b; ");
                        btn1[i][j].setPrefSize(30, 30);
                        gridPane.add(btn1[i][j], i, j);
                        btn1[i][j].setOnMouseClicked((MouseEvent e) -> {
                            data = colorPawn+""+varX+""+varY;
                            System.out.println(data);
                        });
                        break;
                    case 5:
                        btn1[i][j] = new Button();
                        btn1[i][j].setStyle("-fx-background-color: #0f8b10; ");
                        btn1[i][j].setPrefSize(30, 30);
                        gridPane.add(btn1[i][j], i, j);
                        btn1[i][j].setOnMouseClicked((MouseEvent e) -> {
                            data = colorPawn+""+varX+""+varY;
                            System.out.println(data);
                        });
                        break;
                    case 6:
                        btn1[i][j] = new Button();
                        btn1[i][j].setStyle("-fx-background-color: #80208b; ");
                        btn1[i][j].setPrefSize(30, 30);
                        gridPane.add(btn1[i][j], i, j);
                        btn1[i][j].setOnMouseClicked((MouseEvent e) -> {
                            data = colorPawn+""+varX+""+varY;
                            System.out.println(data);
                        });
                        break;
                    case 7:
                        btn1[i][j] = new Button();
                        btn1[i][j].setStyle("-fx-background-color: #bbab14; ");
                        btn1[i][j].setPrefSize(30, 30);
                        gridPane.add(btn1[i][j], i, j);
                        btn1[i][j].setOnMouseClicked((MouseEvent e) -> {
                            data = colorPawn+""+varX+""+varY;
                            System.out.println(data);
                        });
                        break;


                }


            }
        }
    }
}