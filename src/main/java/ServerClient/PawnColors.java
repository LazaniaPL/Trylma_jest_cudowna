package ServerClient;

public enum PawnColors {
    RED(0,1),WHITE(0,-1),YELLOW(-1,-1),GREEN(1,1),BLUE(1,-1),BLACK(-1,1);

    protected final int dirX;
    protected final int dirY;

    PawnColors(int dirX, int dirY) {
        this.dirX = dirX;
        this.dirY = dirY;
    }

    protected int getDirX(){
        return dirX;
    }

    protected int getDirY(){
        return dirY;
    }
}
