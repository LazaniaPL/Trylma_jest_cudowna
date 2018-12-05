package Trylma.players;

public class PlayerC implements Player {



    public  PlayerC(int[][] trylma, int scale) {
        for (int szerokosc = 0; szerokosc < scale; szerokosc++) {

            for (int tempWysokosc = 0; tempWysokosc <= szerokosc; tempWysokosc++) {
                //lewy trójkąt
                trylma[scale-1 -szerokosc + 2 * tempWysokosc][2*scale+1+ szerokosc] = 4;


            }
        }
    }
}
