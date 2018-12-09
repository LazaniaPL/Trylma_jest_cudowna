package ServerClient;

public enum PawnColors {
    PURPLE(0,1),RED(0,-1),OLIVE(-1,-1),YELLOW(1,1),GREEN(1,-1),BLUE(-1,1);

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
