package Trylma;


public interface TrylmaInterface {


    void scaleTrylma(int scale);

    void setTrylma(int[][] trylma);

    /**
     * Godzina 1:55, a ja zamiast spać wymyślilem ile pól w trylmie jest ech.
     * To algorytm to jest Sigma @param k od @param k = 0 do @param scale * 3 + 1
     * plus
     * 3* Sigma k od @param k = 0 do  @param scale
     */


    void whatIsObject();

    void removeTrylma(int[][] trylma);

}
