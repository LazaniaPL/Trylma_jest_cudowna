import Trylma.*;
import javafx.scene.layout.GridPane;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


class Trylma {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Before
    void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @Test
    void howMuchObjects() {

        for (int i = 1; i < 10; i++) {
            TrylmaBuilder trylmaBuilderTest = new TrylmaBuilder(i);
            //trylmaBuilderTest.scale = 77;
            //int[][] test = new int[4 * trylmaBuilderTest.scale + 1][6 * trylmaBuilderTest.scale + 1];
            //trylmaBuilderTest.setTrylma(test);
            //trylmaBuilderTest.whatIsObject();
            assertEquals(numbersOfObjects(trylmaBuilderTest.scale), trylmaBuilderTest.numberOfObjects);

        }
    }


    @Test
    void pawnsAreGood() {

        TrylmaBuilder trylmaBuilderTest = new TrylmaBuilder(4);
        TrylmaPawns trylmaPawns = new TrylmaPawns(1, trylmaBuilderTest.trylma, 4);
        assertTrue(trylmaPawns.error);

    }

    @Test
    void createPlayersIsGood() {
        ArrayList<Integer> possiblePlayers = new ArrayList<>();
        possiblePlayers.add(2);
        possiblePlayers.add(3);
        possiblePlayers.add(4);
        possiblePlayers.add(6);
        TrylmaBuilder trylmaBuilderTest = new TrylmaBuilder(4);
        for (int a :
                possiblePlayers) {


            TrylmaPawns trylmaPawns = new TrylmaPawns(a, trylmaBuilderTest.trylma, 4);
            assertTrue(!trylmaPawns.error);
        }
    }

    int numbersOfObjects(int scale) {
        int a = 0, b = 0;
        for (int k = 0; k <= scale * 3 + 1; k++) {
            a = a + k;
        }
        for (int k = 0; k <= scale; k++) {
            b = b + k;

        }

        return a + 3 * b;
    }
}
