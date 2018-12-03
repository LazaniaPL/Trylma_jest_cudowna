package Trylma;

import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

import java.util.ArrayList;

public class TrylmaBuilder implements TrylmaInterface {

    public int scale;
    public GridPane trylma;
    public ArrayList<Shape> objects;

    @Override
    public void scaleTrylma(int scale) {
        this.scale = scale;
    }

    @Override
    public void setTrylma(GridPane trylma) {
        this.trylma = trylma;
    }


    @Override
    public void setObject(int scale) {
        objects = new ArrayList<>();
        for (int i = 0; i <= HowMuchIsANotFishButObjects(scale); i++) {
            Shape shape = new Rectangle();
            objects.add(shape);
        }

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

        for (int i = 0; i < (4 * scale) + 1; i++) { //wysokosc
            for (int i1 = 0; i1 < scale + 1; i1++) {
                trylma.add(new Rectangle(20, 20), scale + 2 * i1, scale + i);

            }
            i++;
        }

        for (int i = 1; i < (4 * scale) + 1; i++) {
            for (int i2 = 0; i2 < scale; i2++) {
                trylma.add(new Rectangle(20, 20), scale + 1 + 2 * i2, scale + i);

            }
            i++;
        }
        //trójkąty boczne równoramienne


        for (int szerokosc = 0; szerokosc < scale; szerokosc++) {

            for (int tempWysokosc = 0; tempWysokosc < szerokosc + 1; tempWysokosc++) {
                //lewy trójkąt
               trylma.add(new Rectangle(20, 20), szerokosc,
                       3 * scale + 1 - szerokosc + 2 * tempWysokosc);
                //prawy trójkąt
                trylma.add(new Rectangle(20, 20), 4 * scale - szerokosc,
                       3 * scale + 1 - szerokosc + 2 * tempWysokosc);
            }

        }

        //trójkąty boczne prostokątne
        for (int i = 0; i < scale; i++) {
            for (int i1 = 0; i1 < i + 1; i1++) {
                trylma.add(new Rectangle(20, 20), scale + 2 * i1, i);//lewy dolny róg
                trylma.add(new Rectangle(20, 20), scale + 2 * i1, 6 * scale - i); //lewy górny róg
                trylma.add(new Rectangle(20, 20), 3 * scale - 2 * i1, i);//prawy dolny róg
                trylma.add(new Rectangle(20, 20), 3 * scale - 2 * i1, 6 * scale - i); //prawy górny róg
            }
            i++;
        }
        for (int i = 1; i < scale; i++) {
            for (int i2 = 0; i2 < i; i2++) {
                trylma.add(new Rectangle(20, 20), scale + 1 + 2 * i2, i);//lewy dolny róg
                trylma.add(new Rectangle(20, 20), scale + 1 + 2 * i2, 6 * scale - i); //lewy górny róg
                trylma.add(new Rectangle(20, 20), 3 * scale + 1 - 2 * i2, i);//prawy dolny róg
                trylma.add(new Rectangle(20, 20), 3 * scale + 1 - 2 * i2, 6 * scale - i); //prawy górny róg

            }
            i++;
        }


    }

    @Override
    public void removeKebabTrylma(GridPane trylma) {
        trylma = null;
    }
}
