import Trylma.TrylmaBuilder;
import javafx.scene.layout.GridPane;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class Trylma extends TrylmaBuilder {


    @Test
    void sizeOfTrylma() {
        //szerokość to 4 * scale + 1
        //długość to 6 * scale + 1

        TrylmaBuilder trylmaBuilderTest = new TrylmaBuilder();
        trylmaBuilderTest.scale = 6;
        GridPane test = new GridPane();
        trylmaBuilderTest.setTrylma(test);
        trylmaBuilderTest.whatIsObject();
        assertEquals(4 * trylmaBuilderTest.scale + 1, test.impl_getColumnCount()); //szerokosc
        assertEquals(6 * trylmaBuilderTest.scale + 1, test.impl_getRowCount()); //dlugosc

    }


    @Test
    void howMuchObjects() {


        TrylmaBuilder trylmaBuilderTest = new TrylmaBuilder();
        trylmaBuilderTest.scale = 77;

        GridPane test = new GridPane();
        trylmaBuilderTest.setTrylma(test);
        trylmaBuilderTest.whatIsObject();
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
