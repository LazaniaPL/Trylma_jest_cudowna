package ServerClient;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
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


        TextField textFieldScale = new TextField();
        textFieldScale.setPromptText("HOW BIG IS SCALE?");
        textFieldScale.setTranslateX(290);
        textFieldScale.setTranslateY(260);

        TextField textFieldPlayers = new TextField();
        textFieldPlayers.setPromptText("HOW MANY PLAYERS?");
        textFieldPlayers.setTranslateX(40);
        textFieldPlayers.setTranslateY(260);
        try {
            int playersNumber = Integer.parseInt(textFieldScale.getText());
        }catch (NumberFormatException e){ }
        Server server = new Server(8088, 6);

        Button createServer = new Button("ACCEPT");
        createServer.setTranslateX(125);
        createServer.setTranslateY(320);
        createServer.setOnAction(event -> {
            new Thread(server).start();
        });


        root.getChildren().addAll(textFieldScale, textFieldPlayers, createServer);

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
