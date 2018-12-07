package ServerClient;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import static ServerClient.Client.TILE_SIZE;

public class Pawn extends Circle {

    public double mouseX, mouseY;
    public double OldX, OldY;

    public PawnColors type;

    public double getOldX() {
        return OldX;
    }

    public double getOldY() {
        return OldY;
    }

    public PawnColors getType() {
        return type;
    }

    public Pawn(PawnColors type, int x, int y){
        this.type = type;
        setRadius(TILE_SIZE);
        //TODO: Możliwe że trzeba wycentrować
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

    public void move(int x, int y){
        OldX = x*(1.5*TILE_SIZE);
        OldY = y*(2*TILE_SIZE);
        relocate(OldX, OldY);
    }

    public void  abortMove(){
        relocate(OldX,OldY);
    }
}
