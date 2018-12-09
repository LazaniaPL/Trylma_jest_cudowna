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

        switch(color){
            case "WHITE":
                setFill(Color.WHITE);
                setStroke(Color.BLACK);
                break;
            case "RED":
                setFill(Color.WHITE);
                setStroke(Color.RED);
                break;
            case "GREEN":
                setFill(Color.WHITE);
                setStroke(Color.GREEN);
                break;
            case "YELLOW":
                setFill(Color.WHITE);
                setStroke(Color.DARKKHAKI);
                break;
            case "PURPLE":
                setFill(Color.WHITE);
                setStroke(Color.PURPLE);
                break;
            case "BLUE":
                setFill(Color.WHITE);
                setStroke(Color.BLUE);
                break;
            case "OLIVE":
                setFill(Color.WHITE);
                setStroke(Color.DARKOLIVEGREEN);
                break;
        }


    }

}
