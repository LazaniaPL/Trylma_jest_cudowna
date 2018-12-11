package ServerClient;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import static ServerClient.Client.TILE_SIZE;

public class Tile extends Circle {

    private Pawn pawn;

    public boolean hasPawn(){
        return pawn != null;
    }

    public Pawn getPawn() {
        return pawn;
    }

    public void setPawn(Pawn pawn) {
        this.pawn = pawn;
    }

    public Tile(String color, int x, int y){
        setRadius(TILE_SIZE);
        relocate(x*(2*TILE_SIZE), y*(2*TILE_SIZE));
        setStrokeWidth(0.2*TILE_SIZE);
        setFill(Color.WHITE);
        switch(color){
            case "WHITE":
                setStroke(Color.BLACK);
                break;
            case "RED":
                setStroke(Color.RED);
                break;
            case "GREEN":
                setStroke(Color.GREEN);
                break;
            case "YELLOW":
                setStroke(Color.DARKKHAKI);
                break;
            case "PURPLE":
                setStroke(Color.PURPLE);
                break;
            case "BLUE":
                setStroke(Color.BLUE);
                break;
            case "OLIVE":
                setStroke(Color.DARKOLIVEGREEN);
                break;
        }


    }

}
