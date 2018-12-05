package Trylma.players;

public class PlayerA implements Player {

    public  PlayerA(int[][] trylma, int scale) {
        for (int szerokosc = 0; szerokosc < scale; szerokosc++) {

            for (int tempWysokosc = 0; tempWysokosc <= szerokosc; tempWysokosc++) {
                //lewy trójkąt
                trylma[szerokosc][3 * scale - szerokosc + 2 * tempWysokosc] = 2;


            }
        }

    }
}
