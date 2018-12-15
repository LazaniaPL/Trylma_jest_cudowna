package ServerClient;

public class MoveResult {

    private MoveType type;

    MoveType getType(){
        return type;
    }

    private Pawn pawn;

    public Pawn getPawn() {
        return pawn;
    }

    MoveResult(MoveType type){
        this(type, null);
    }

    private MoveResult(MoveType type, Pawn pawn){
        this.type=type;
        this.pawn=pawn;
    }
}
