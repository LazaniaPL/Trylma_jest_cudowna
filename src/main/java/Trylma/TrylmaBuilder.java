package Trylma;

import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

import java.util.ArrayList;

public class TrylmaBuilder implements TrylmaInterface {

    public int scale;
    public int[][] trylma;
    /**
     * Używam tego do testów
     */
    public int numberOfObjects = 0;

    @Override
    public void scaleTrylma(int scale) {
        this.scale = scale;
    }

    @Override
    public void setTrylma(int[][] trylma) {
        this.trylma = trylma;
    }

    @Override
    public void createPawn(int[][] trylma, int a, int b, int pawn) {
        if (2 <= pawn && pawn <= 7) {
            trylma[a][b] = pawn;
        }
    }

    @Override
    public void createPlayerA(int[][] trylma, int scale) {


    }

    @Override
    public void createPlayerB(int[][] trylma, int scale) {

    }

    @Override
    public void createPlayerC(int[][] trylma, int scale) {

    }

    @Override
    public void createPlayerD(int[][] trylma, int scale) {

    }

    @Override
    public void createPlayerE(int[][] trylma, int scale) {

    }

    @Override
    public void createPlayerF(int[][] trylma, int scale) {

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

                trylma[scale + 1 + 2 * i1][scale + i] = 1;

                numberOfObjects = numberOfObjects + 1;

            }


        }

        for (int i = 1; i < (4 * scale); i += 2) {
            for (int i2 = 0; i2 < scale + scale % 2; i2++) { // parzyste
                trylma[scale + 2 * i2][scale + i] = 1;
                numberOfObjects = numberOfObjects + 1;

            }


        }
        //trójkąty boczne równoramienne


        for (int szerokosc = 0; szerokosc < scale; szerokosc++) {

            for (int tempWysokosc = 0; tempWysokosc <= szerokosc; tempWysokosc++) {
                //lewy trójkąt
                trylma[szerokosc][3 * scale + 1 - szerokosc + 2 * tempWysokosc] = 1;
                //prawy trójkąt
                trylma[4 * scale - szerokosc][
                        3 * scale + 1 - szerokosc + 2 * tempWysokosc] = 1;
                numberOfObjects = numberOfObjects + 2;

            }

        }

        //trójkąty boczne prostokątne
        for (int i = 0; i < scale; i += 2) {
            for (int i1 = 0; i1 <= i; i1 += 2) { // parzyste kwadraty ( w tej samej linii są)
                trylma[scale + i1][i] = 1;//lewy dolny róg
                trylma[scale + i1][6 * scale - i] = 1; //lewy górny róg
                trylma[3 * scale - i1][i] = 1;//prawy dolny róg
                trylma[3 * scale - i1][6 * scale - i] = 1; //prawy górny róg
                numberOfObjects = numberOfObjects + 4;


            }

        }
        for (int i = 1; i < scale; i += 2) {
            for (int i2 = 0; i2 < i; i2 += 2) { // nieparzyste kwadraty
                trylma[scale + 1 + i2][i] = 1;//lewy dolny róg
                trylma[scale + 1 + i2][6 * scale - i] = 1; //lewy górny róg
                trylma[3 * scale + 1 - i2][i] = 1;//prawy dolny róg
                trylma[3 * scale + 1 - i2][6 * scale - i] = 1; //prawy górny róg
                numberOfObjects = numberOfObjects + 4;


            }

        }


    }

    @Override
    public void removeKebabTrylma(int[][] trylma) {
        trylma = null;
    }
}
