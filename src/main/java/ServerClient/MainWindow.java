package ServerClient;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.*;
import javafx.stage.Stage;
import sun.font.FontFamily;

public class MainWindow extends Application {

    private Server server;


    private void setStop(){
        server.stop();
    }

    private void  setRunning(int playerNumbers, int scale){
        server = new Server(8188, playerNumbers, scale);
        new Thread(server).start();
    }


    private Parent createContent(){
        Pane root = new Pane();
        root.setPrefSize(520,390);

        String text =   "WELCOME IN OUR PROJECT -- CHINESE CHECKERS.\n\n YOU CAN PLAY EITHER WITH YOUR FRIENDS OR BOT,\n" +
                        "WHICH YOU CAN ADD LATER...\n\n TO START THE GAME FIRST SELECT BOARD SIZE AND NUMBER\n OF PLAYERS " +
                        "THEN CLICK \"ACCEPT\", IN CASE OF WRONG DECISION\n MAKE SURE TU RESET SERVER " +
                        "BY CLICKING \"ABORT\" BUTTON ";


        Text initialText = new Text(10, 30, text);
        initialText.setTextAlignment(TextAlignment.JUSTIFY);

        Label labelScale = new Label("SELECT TABLE SIZE:");
        labelScale.setTranslateX(290);
        labelScale.setTranslateY(230);

        Spinner<Integer> numberScale  = new Spinner<>(3,10,4);
        numberScale.setTranslateX(290);
        numberScale.setTranslateY(260);

        Label labelPlayers = new Label("NUMBER OF PLAYERS:");
        labelPlayers.setTranslateX(40);
        labelPlayers.setTranslateY(230);

        Spinner<Integer> numberPlayers  = new Spinner<>(1,6,2);
        numberPlayers.setTranslateX(40);
        numberPlayers.setTranslateY(260);


        Button createServer = new Button("ACCEPT");
        createServer.setTranslateX(125);
        createServer.setTranslateY(320);
        createServer.setOnAction(event -> {
            setRunning(numberPlayers.getValue(),numberScale.getValue());
           System.out.println("SERVER STARTED");
        });

        Button exit = new Button("EXIT");
        exit.setTranslateX(330);
        exit.setTranslateY(320);
        exit.setOnAction(event -> {
            if(server != null) {
                setStop();
                System.exit(0);
            }
        });

        root.getChildren().addAll(labelScale, labelPlayers, initialText);
        root.getChildren().addAll(numberScale, numberPlayers, createServer, exit);

        return root;

    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Checkers");
        primaryStage.setScene(new Scene(createContent()));
        primaryStage.show();
    }

    public static void main(String[] args){

        launch(args);

    }


}
