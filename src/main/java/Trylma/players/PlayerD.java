package Trylma.players;

public class PlayerD implements Player {


    public PlayerD(int[][] trylma, int scale) {
        for (int szerokosc = 0; szerokosc < scale; szerokosc++) {

            for (int tempWysokosc = 0; tempWysokosc <= szerokosc; tempWysokosc++) {

                //prawy trójkąt
                trylma[3 * scale - szerokosc + 2 * tempWysokosc][4 * scale - szerokosc] = 5;


            }
        }
    }
}
