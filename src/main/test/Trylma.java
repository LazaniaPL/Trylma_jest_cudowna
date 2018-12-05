import Trylma.TrylmaBuilder;
import javafx.scene.layout.GridPane;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class Trylma  {





    @Test
    void howMuchObjects() {


        TrylmaBuilder trylmaBuilderTest = new TrylmaBuilder(77);
        //trylmaBuilderTest.scale = 77;
        //int[][] test = new int[4 * trylmaBuilderTest.scale + 1][6 * trylmaBuilderTest.scale + 1];
        //trylmaBuilderTest.setTrylma(test);
        //trylmaBuilderTest.whatIsObject();
        assertEquals(numbersOfObjects(trylmaBuilderTest.scale), trylmaBuilderTest.numberOfObjects);


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
