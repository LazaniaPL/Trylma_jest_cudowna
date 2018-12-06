package Trylma.gui;

import Trylma.TrylmaBuilder;
import Trylma.TrylmaPawns;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws IOException {

        TextField textFieldScale = new TextField();
        textFieldScale.setPromptText("Wpisz wartość skali");

        TextField textFieldPlayers = new TextField();
        textFieldPlayers.setPromptText("Ile ma być graczy");

        StackPane root = new StackPane();
        VBox vBox = new VBox();
        root.getChildren().add(vBox);

        Button btn = new Button();
        btn.setText("Uruchom program");
        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                GridPane gridPane = new GridPane();
                try {

                    int players = Integer.parseInt(textFieldPlayers.getText());


                    int scale = Integer.parseInt(textFieldScale.getText());
                    TrylmaBuilder trylmaBuilder = new TrylmaBuilder(scale);
                    TrylmaPawns trylmaPawns = new TrylmaPawns(players,trylmaBuilder.trylma,scale);
                    Button[][] btn = new Button[6 * scale + 1][4 * scale + 1];
                    for (int i = 0; i < 6 * scale + 1; i++) {
                        for (int j = 0; j < 4 * scale + 1; j++) {
                            int temp = trylmaBuilder.trylma[i][j];
                            switch (temp) {
                                case 1:
                                    btn[i][j] = new Button("");
                                    btn[i][j].setPrefSize(65, 65);
                                    gridPane.add(btn[i][j], i, j);
                                    btn[i][j].setOnMouseClicked((MouseEvent e) -> {
                                    });
                                    break;
                                case 2:
                                    btn[i][j] = new Button("A");
                                    btn[i][j].setTextFill(Color.RED);
                                    btn[i][j].setPrefSize(65, 65);
                                    gridPane.add(btn[i][j], i, j);
                                    btn[i][j].setOnMouseClicked((MouseEvent e) -> {
                                    });
                                    break;
                                case 3:
                                    btn[i][j] = new Button("B");
                                    btn[i][j].setTextFill(Color.BLACK);
                                    btn[i][j].setPrefSize(65, 65);
                                    gridPane.add(btn[i][j], i, j);
                                    btn[i][j].setOnMouseClicked((MouseEvent e) -> {
                                    });
                                    break;
                                case 4:
                                    btn[i][j] = new Button("C");
                                    btn[i][j].setTextFill(Color.DARKCYAN);
                                    btn[i][j].setPrefSize(65, 65);
                                    gridPane.add(btn[i][j], i, j);
                                    btn[i][j].setOnMouseClicked((MouseEvent e) -> {
                                    });
                                    break;
                                case 5:
                                    btn[i][j] = new Button("D");
                                    btn[i][j].setTextFill(Color.CADETBLUE);
                                    btn[i][j].setPrefSize(65, 65);
                                    gridPane.add(btn[i][j], i, j);
                                    btn[i][j].setOnMouseClicked((MouseEvent e) -> {
                                    });
                                    break;
                                case 6:
                                    btn[i][j] = new Button("E");
                                    btn[i][j].setTextFill(Color.DARKVIOLET);
                                    btn[i][j].setPrefSize(65, 65);
                                    gridPane.add(btn[i][j], i, j);
                                    btn[i][j].setOnMouseClicked((MouseEvent e) -> {
                                    });
                                    break;
                                case 7:
                                    btn[i][j] = new Button("F");
                                    btn[i][j].setTextFill(Color.GREEN);
                                    btn[i][j].setPrefSize(65, 65);
                                    gridPane.add(btn[i][j], i, j);
                                    btn[i][j].setOnMouseClicked((MouseEvent e) -> {
                                    });
                                    break;


                            }


                        }
                    }

                } catch (Exception ignored) {
                }

                Scene scene = new Scene(gridPane, 1000, 1000);

                Stage stage = new Stage();
                stage.setScene(scene);
                stage.show();
                System.out.println("Hello World!");
            }
        });


        vBox.getChildren().addAll(btn, textFieldScale, textFieldPlayers);


        Scene scene = new Scene(root, 300, 250);

        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
