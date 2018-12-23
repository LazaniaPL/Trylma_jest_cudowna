package ServerClient;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import static ServerClient.Client.TILE_SIZE;

public class Pawn extends Circle {

    private double mouseX, mouseY;
    private double OldX, OldY;

    private PawnColors type;

    double getOldX() {
        return OldX;
    }

    double getOldY() {
        return OldY;
    }

    PawnColors getType() {
        return type;
    }

    public Pawn(PawnColors type, int x, int y){
        this.type = type;
        setRadius(TILE_SIZE);
        move(x,y);

        switch (type){

            case PURPLE:
                setFill(Color.PURPLE);
                break;
            case RED:
                setFill(Color.RED);
                break;
            case OLIVE:
                setFill(Color.DARKOLIVEGREEN);
                break;
            case YELLOW:
                setFill(Color.DARKKHAKI);
                break;
            case GREEN:
                setFill(Color.GREEN);
                break;
            case BLUE:
                setFill(Color.BLUE);
                break;
        }

        setOnMousePressed(event -> {

            mouseX = event.getSceneX();
            mouseY = event.getSceneY();

        });

        setOnMouseDragged(event -> {
            relocate(event.getSceneX() - mouseX + OldX, event.getSceneY() - mouseY + OldY);
        } );


    }

    void move(int x, int y){
        OldX = x*(2*TILE_SIZE);
        OldY = y*(2*TILE_SIZE);
        relocate(OldX, OldY);
    }

    void  abortMove(){
        relocate(OldX,OldY);
    }
}
