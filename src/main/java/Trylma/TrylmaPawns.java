package Trylma;

import Trylma.players.*;

public class TrylmaPawns {

    public TrylmaPawns(int ilosc, int[][] trylma, int scale) {
        switch (ilosc) {
            case 2:
                createTwoPlayers(trylma, scale);
                break;
            case 3:
                createThreePlayers(trylma, scale);
                break;
            case 4:
                createFourPlayers(trylma, scale);
                break;
            case 6:
                createSixPlayers(trylma, scale);
                break;
            default:
                System.out.println("error");
                break;
        }
    }

    /**
     * --A
     * B   F
     * C   E
     * --D
     *
     * <p>
     * A=2
     * B=3
     * C=4
     * D=5
     * E=6
     * F=7
     */

    private void createTwoPlayers(int[][] trylma, int scale) {
        new PlayerA(trylma, scale);
        new PlayerD(trylma, scale);

    }


    private void createThreePlayers(int[][] trylma, int scale) {
        new PlayerA(trylma, scale);
        new PlayerC(trylma, scale);
        new PlayerE(trylma, scale);


    }


    private void createFourPlayers(int[][] trylma, int scale) {
        new PlayerB(trylma, scale);
        new PlayerC(trylma, scale);
        new PlayerE(trylma, scale);
        new PlayerF(trylma, scale);


    }


    private void createSixPlayers(int[][] trylma, int scale) {
        new PlayerA(trylma, scale);
        new PlayerB(trylma, scale);
        new PlayerC(trylma, scale);
        new PlayerD(trylma, scale);
        new PlayerE(trylma, scale);
        new PlayerF(trylma, scale);

    }


}
