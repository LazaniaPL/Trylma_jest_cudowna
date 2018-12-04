package Trylma;

import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

import java.util.ArrayList;

public class TrylmaBuilder implements TrylmaInterface {

    public int scale;
    public GridPane trylma;
    public int numberOfObjects = 0;

    @Override
    public void scaleTrylma(int scale) {
        this.scale = scale;
    }

    @Override
    public void setTrylma(GridPane trylma) {
        this.trylma = trylma;
    }


    /**
     * Godzina: 2:27
     * Czym jest object?
     * Może naszym sensem istnienia?
     * Ale tutaj to na pewno figurą XD (co pewnie da rade sie obalić)
     * Jednak mi bardziej tu chodzi, gdzie te obiekty mogą być na trylmie.
     * Wielkość naszej trylmy to ( 4 * scala + 1 ) * ( 6 * scala + 1 )
     * <p>
     * Godzina: 2:35
     * Może Dawid Podsiadło miał racje
     * Może trójkąty i kwadraty mają jakiś głębszy sens.
     * Bo w tej trylmie to na pewno
     * Kwadrat składa sie z pasków które mają scala obiektów i scala + 1 obiektów
     * Jest tych pasków 4 * scala + 1
     * Gorzej będzie z trójkątami bo trójkąt trójkątowi nie jest równy
     * <p>
     * Godzina 2:57
     * Kwadrat zrobiony, zrobie chyba sobie przerwe
     */
    @Override
    public void whatIsObject() {
        //kwadrat
        int a = 1;
        for (int i = 0; i <= (4 * scale); i += 2) { //wysokosc
            for (int i1 = 0; i1 < scale + (scale + 1) % 2; i1++) { //nieparzyste
                trylma.add(new Rectangle(20, 20), scale + 1 + 2 * i1, scale + i);
                numberOfObjects = numberOfObjects + 1;

            }


        }

        for (int i = 1; i < (4 * scale); i += 2) {
            for (int i2 = 0; i2 < scale + scale % 2; i2++) { // parzyste
                trylma.add(new Rectangle(20, 20), scale + 2 * i2, scale + i);
                numberOfObjects = numberOfObjects + 1;

            }


        }
        //trójkąty boczne równoramienne


        for (int szerokosc = 0; szerokosc < scale; szerokosc++) {

            for (int tempWysokosc = 0; tempWysokosc <= szerokosc; tempWysokosc++) {
                //lewy trójkąt
                trylma.add(new Rectangle(20, 20), szerokosc,
                        3 * scale + 1 - szerokosc + 2 * tempWysokosc);
                //prawy trójkąt
                trylma.add(new Rectangle(20, 20), 4 * scale - szerokosc,
                        3 * scale + 1 - szerokosc + 2 * tempWysokosc);
                numberOfObjects = numberOfObjects + 2;

            }

        }

        //trójkąty boczne prostokątne
        for (int i = 0; i < scale; i += 2) {
            for (int i1 = 0; i1 <= i; i1 += 2) { // parzyste kwadraty ( w tej samej linii są)
                trylma.add(new Rectangle(20, 20), scale + i1, i);//lewy dolny róg
                trylma.add(new Rectangle(20, 20), scale + i1, 6 * scale - i); //lewy górny róg
                trylma.add(new Rectangle(20, 20), 3 * scale - i1, i);//prawy dolny róg
                trylma.add(new Rectangle(20, 20), 3 * scale - i1, 6 * scale - i); //prawy górny róg
                numberOfObjects = numberOfObjects + 4;


            }

        }
        for (int i = 1; i < scale; i += 2) {
            for (int i2 = 0; i2 < i ; i2+=2) { // nieparzyste kwadraty
                trylma.add(new Rectangle(20, 20), scale + 1 + i2, i);//lewy dolny róg
                trylma.add(new Rectangle(20, 20), scale + 1 +  i2, 6 * scale - i); //lewy górny róg
                trylma.add(new Rectangle(20, 20), 3 * scale + 1 -  i2, i);//prawy dolny róg
                trylma.add(new Rectangle(20, 20), 3 * scale + 1 -  i2, 6 * scale - i); //prawy górny róg
                numberOfObjects = numberOfObjects + 4;

                System.out.println(a);
                a++;
            }

        }


    }

    @Override
    public void removeKebabTrylma(GridPane trylma) {
        trylma = null;
    }
}
