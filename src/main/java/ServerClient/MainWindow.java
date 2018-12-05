package ServerClient;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sun.font.FontFamily;


public class MainWindow extends Application {



    private Parent createContent(){
        Pane root = new Pane();
        root.setPrefSize(520,390);
        Text text = new Text();
        text.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, FontPosture.REGULAR, 20));
        text.setText("CHOOSE GAME TYPE:\n (YOU CAN CHOOSE BOTS LATER)");
        text.setTranslateX(100);
        text.setTranslateY(50);

        //TODO: NAPRAWIĆ TO DLA THREADPOOLA

        Server server = new Server(8088, 6);

        Button twoPlayers = new Button("TWO PLAYERS");
        twoPlayers.setTranslateX(200);
        twoPlayers.setTranslateY(120);
        twoPlayers.setOnAction(event -> new Thread(server).start());

        Button threePlayers = new Button("THREE PLAYERS");
        threePlayers.setTranslateX(200);
        threePlayers.setTranslateY(180);
        threePlayers.setOnAction(event -> new Thread(server).start());


        Button fourPlayers = new Button("FOUR PLAYERS");
        fourPlayers.setTranslateX(200);
        fourPlayers.setTranslateY(240);
        fourPlayers.setOnAction(event -> new Thread(server).start());

        Button sixPlayers = new Button("THREE PLAYERS");
        sixPlayers.setTranslateX(200);
        sixPlayers.setTranslateY(300);
        sixPlayers.setOnAction(event -> new Thread(server).start());


        Button abort = new Button("CLEAR");
        abort.setTranslateX(400);
        abort.setTranslateY(270);
        abort.setOnAction(event -> server.stop());

        Button exit = new Button("EXIT");
        exit.setTranslateX(400);
        exit.setTranslateY(320);
        exit.setOnAction(event -> {
            server.stop();
            System.exit(0);
        });

        root.getChildren().addAll(twoPlayers, threePlayers, fourPlayers, sixPlayers,abort, exit);
        root.getChildren().add(text);

        return root;

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Checkers");
        primaryStage.setScene(new Scene(createContent()));
        primaryStage.show();
    }

    public static void main(String[] args){

        launch(args);

    }


}
