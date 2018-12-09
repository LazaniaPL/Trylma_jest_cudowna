package Trylma.players;

public class PlayerE implements Player {


    public  PlayerE(int[][] trylma, int scale) {
        for (int szerokosc = 0; szerokosc < scale; szerokosc++) {

            for (int tempWysokosc = 0; tempWysokosc <= szerokosc; tempWysokosc++) {
                //lewy trójkąt
                trylma[5 * scale +1 - szerokosc + 2 * tempWysokosc][2*scale+1+ szerokosc] = 6;


            }
        }
    }
}
