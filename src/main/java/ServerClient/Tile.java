package ServerClient;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import static ServerClient.Client.TILE_SIZE;

class Tile extends Circle {

    private Pawn pawn;

    boolean hasPawn(){
        return pawn != null;
    }

    Pawn getPawn() {
        return pawn;
    }

    void setPawn(Pawn pawn) {
        this.pawn = pawn;
    }

    Tile(String color, int x, int y){
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
