package Trylma.players;

public class PlayerB implements Player {

    public PlayerB(int[][] trylma, int scale) {
        for (int szerokosc = 0; szerokosc < scale; szerokosc++) {

            for (int tempWysokosc = 0; tempWysokosc <= szerokosc; tempWysokosc++) {

                //prawy trójkąt
                trylma[2 * scale - 1 - szerokosc][6 * scale - 1 - szerokosc + 2 * tempWysokosc] = 3;


            }
        }
    }
}
