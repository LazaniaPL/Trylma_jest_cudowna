package Trylma.gui;

import Trylma.TrylmaBuilder;
import Trylma.TrylmaPawns;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
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
        btn.setOnAction(event -> {
            GridPane gridPane = new GridPane();
            gridPane.setVgap(10);
            try {

                int players = Integer.parseInt(textFieldPlayers.getText());


                int scale = Integer.parseInt(textFieldScale.getText());
                TrylmaBuilder trylmaBuilder = new TrylmaBuilder(scale);
                TrylmaPawns trylmaPawns = new TrylmaPawns(players,trylmaBuilder.trylma,scale);
                Button[][] btn1 = new Button[6 * scale + 1][4 * scale + 1];
                //TrylmaGridPane.makePiece(gridPane, scale, trylmaBuilder, btn1);

            } catch (Exception ignored) {
            }

            Scene scene = new Scene(gridPane, 1230, 940);

            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
            System.out.println("Hello World!");
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
