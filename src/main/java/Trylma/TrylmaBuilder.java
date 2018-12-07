package Trylma;

import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

import java.util.ArrayList;

public class TrylmaBuilder implements TrylmaInterface {

    public TrylmaBuilder(int scale) {
        scaleTrylma(scale);
        setTrylma(createTrylma(scale));
        whatIsObject();
    }

    public int scale;
    public int[][] trylma;

    public int numberOfObjects = 0;

    public int[][] createTrylma(int scale) {
        return new int[6 * scale + 1][4 * scale + 1];
    }

    @Override
    public void scaleTrylma(int scale) {
        this.scale = scale;
    }

    @Override
    public void setTrylma(int[][] trylma) {
        this.trylma = trylma;
    }


    @Override
    public void whatIsObject() {
        //kwadrat
        int a = 1;
        for (int i = 0; i <= (4 * scale); i += 2) { //wysokosc
            for (int i1 = 0; i1 < scale + (scale + 1) % 2; i1++) { //nieparzyste
                trylma[scale + i][scale + scale % 2 + 2 * i1] = 1;
                numberOfObjects = numberOfObjects + 1;
            }
        }
        for (int i = 1; i < (4 * scale); i += 2) {
            for (int i2 = 0; i2 < scale + scale % 2; i2++) { // parzyste
                trylma[scale + i][scale + (scale + 1) % 2 + 2 * i2] = 1;
                numberOfObjects = numberOfObjects + 1;
            }
        }
        //trójkąty boczne równoramienne
        for (int szerokosc = 0; szerokosc < scale; szerokosc++) {
            for (int tempWysokosc = 0; tempWysokosc <= szerokosc; tempWysokosc++) {
                //lewy trójkąt
                trylma[3 * scale - szerokosc + 2 * tempWysokosc][szerokosc] = 1;
                //prawy trójkąt
                trylma[3 * scale - szerokosc + 2 * tempWysokosc][4 * scale - szerokosc] = 1;
                numberOfObjects = numberOfObjects + 2;
            }
        }
        //trójkąty boczne prostokątne
        for (int i = 0; i < scale; i += 2) {
            for (int i1 = 0; i1 <= i; i1 += 2) { // parzyste kwadraty ( w tej samej linii są)
                trylma[i][scale + i1] = 1;//lewy górny róg
                trylma[6 * scale - i][scale + i1] = 1; //lewy dolny róg
                trylma[i][3 * scale - i1] = 1;//prawy górny róg
                trylma[6 * scale - i][3 * scale - i1] = 1; //prawy dolny róg
                numberOfObjects = numberOfObjects + 4;
            }
        }
        for (int i = 1; i < scale; i += 2) {
            for (int i2 = 0; i2 < i; i2 += 2) { // nieparzyste kwadraty
                trylma[i][scale + 1 + i2] = 1;//lewy górny róg
                trylma[6 * scale - i][scale + 1 + i2] = 1; //lewy dolny róg
                trylma[i][3 * scale - 1 - i2] = 1;//prawy gorny róg
                trylma[6 * scale - i][3 * scale - 1 - i2] = 1; //prawy dolny róg
                numberOfObjects = numberOfObjects + 4;

            }

        }


    }

    @Override
    public void removeTrylma(int[][] trylma) {
        trylma = null;
    }
}


