package Trylma.players;

public class PlayerF implements Player {


    public  PlayerF(int[][] trylma, int scale) {
        for (int szerokosc = 0; szerokosc < scale; szerokosc++) {

            for (int tempWysokosc = 0; tempWysokosc <= szerokosc; tempWysokosc++) {

                //prawy trójkąt
                trylma[2 * scale - 1 - szerokosc][
                        2 * scale - 1 - szerokosc + 2 * tempWysokosc] = 7;


            }
        }
    }
}
