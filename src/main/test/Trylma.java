import Trylma.TrylmaBuilder;
import javafx.scene.layout.GridPane;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class Trylma extends TrylmaBuilder {


    @Test
    void CountOfTrylmaObjects() {
        //szerokość to 4 * scale + 1
        //długość to 6 * scale + 1

        TrylmaBuilder trylmaBuilder = new TrylmaBuilder();
        trylmaBuilder.scale = 6;
        GridPane test = new GridPane();
        trylmaBuilder.setTrylma(test);
        trylmaBuilder.whatIsObject();
        assertEquals(4 * trylmaBuilder.scale + 1, test.impl_getColumnCount()); //szerokosc
        assertEquals(6 * trylmaBuilder.scale + 1, test.impl_getRowCount()); //dlugosc

    }

}
