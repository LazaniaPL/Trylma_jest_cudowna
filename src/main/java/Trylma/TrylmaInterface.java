package Trylma;

import javafx.scene.layout.GridPane;

public interface TrylmaInterface {


    void scaleTrylma(int scale);

    void setTrylma(GridPane trylma);

    /**
     * Godzina 1:55, a ja zamiast spać wymyślilem ile pól w trylmie jest ech.
     * To algorytm to jest Sigma @param k od @param k = 0 do @param scale * 2 + 1
     * plus
     * 3* Sigma k od @param k = 0 do  @param scale
     */
    default int HowMuchIsANotFishButObjects(int scale) {
        int a = 0;
        for (int i = 0; i <= (2 * scale) + 1; i++) {
            a++;
        }
        for (int i = 0; i <= scale; i++) {
            a = a + 3;
        }
        return a;
    }

    void setObject(int scale);

    void whatIsObject();

    void removeKebabTrylma(GridPane trylma);

}
