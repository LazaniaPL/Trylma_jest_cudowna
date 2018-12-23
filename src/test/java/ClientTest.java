import ServerClient.*;
import javafx.scene.Group;
import javafx.util.Pair;
import org.junit.Before;
import org.junit.Test;
import Trylma.*;

import java.io.IOException;
import java.io.PrintWriter;

import static org.junit.jupiter.api.Assertions.*;

public class ClientTest {
    private Tile[][] board= new Tile[120][160];
    ;
    private Group tileGroup = new Group();
    private Group pawnGroup = new Group();


    private Pawn makePawn(PawnColors type, int x, int y) {
        return new Pawn(type, x, y);
    }

    @Before
    public void setTrylma() {
        TrylmaBuilder trylmaBuilder = new TrylmaBuilder(4);
        new TrylmaPawns(6, trylmaBuilder.trylma, 4);

        for (int i = 0; i < 6 * 4 + 1; i++) {
            for (int j = 0; j < 4 * 4 + 1; j++) {
                int temp = trylmaBuilder.trylma[i][j];
                Pawn pawn;
                switch (temp) {
                    case 1:
                        Tile tileWhite = new Tile("WHITE", i, j);
                        board[i][j] = tileWhite;
                        tileGroup.getChildren().add(tileWhite);
                        break;
                    case 2:
                        Tile tileRed = new Tile("RED", i, j);
                        board[i][j] = tileRed;
                        tileGroup.getChildren().add(tileRed);
                        pawn = makePawn(PawnColors.RED, i, j);
                        tileRed.setPawn(pawn);
                        pawnGroup.getChildren().add(pawn);
                        break;
                    case 3:
                        Tile tileGreen = new Tile("GREEN", i, j);
                        board[i][j] = tileGreen;
                        tileGroup.getChildren().add(tileGreen);
                        pawn = makePawn(PawnColors.GREEN, i, j);
                        tileGreen.setPawn(pawn);
                        pawnGroup.getChildren().add(pawn);
                        break;
                    case 4:
                        Tile tileYellow = new Tile("YELLOW", i, j);
                        board[i][j] = tileYellow;
                        tileGroup.getChildren().add(tileYellow);
                        pawn = makePawn(PawnColors.YELLOW, i, j);
                        tileYellow.setPawn(pawn);
                        pawnGroup.getChildren().add(pawn);
                        break;
                    case 5:
                        Tile tilePurple = new Tile("PURPLE", i, j);
                        board[i][j] = tilePurple;
                        tileGroup.getChildren().add(tilePurple);
                        pawn = makePawn(PawnColors.PURPLE, i, j);
                        tilePurple.setPawn(pawn);
                        pawnGroup.getChildren().add(pawn);
                        break;
                    case 6:
                        Tile tileBlue = new Tile("BLUE", i, j);
                        board[i][j] = tileBlue;
                        tileGroup.getChildren().add(tileBlue);
                        pawn = makePawn(PawnColors.BLUE, i, j);
                        tileBlue.setPawn(pawn);
                        pawnGroup.getChildren().add(pawn);
                        break;
                    case 7:
                        Tile tileOlive = new Tile("OLIVE", i, j);
                        board[i][j] = tileOlive;
                        tileGroup.getChildren().add(tileOlive);
                        pawn = makePawn(PawnColors.OLIVE, i, j);
                        tileOlive.setPawn(pawn);
                        pawnGroup.getChildren().add(pawn);
                        break;
                }
            }
        }
    }

    @Test
    public void Check() {
        Check check = new Check(11, 13, board);
        for (Pair<Integer, Integer> p : check.returnRealMoves()) {
            int l = p.getKey();
            int r = p.getValue();
            System.out.println(l+" "+ r);
        }

    }
}
