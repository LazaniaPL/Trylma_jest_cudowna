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
        Player player1 = new PlayerA(trylma, scale);
        Player player2 = new PlayerD(trylma, scale);

    }


    private void createThreePlayers(int[][] trylma, int scale) {
        Player player1 = new PlayerA(trylma, scale);
        Player player2 = new PlayerC(trylma, scale);
        Player player3 = new PlayerE(trylma, scale);


    }


    private void createFourPlayers(int[][] trylma, int scale) {
        Player player1 = new PlayerB(trylma, scale);
        Player player2 = new PlayerC(trylma, scale);
        Player player3 = new PlayerE(trylma, scale);
        Player player4 = new PlayerF(trylma, scale);


    }


    private void createSixPlayers(int[][] trylma, int scale) {
        Player player1 = new PlayerA(trylma, scale);
        Player player2 = new PlayerB(trylma, scale);
        Player player3 = new PlayerC(trylma, scale);
        Player player4 = new PlayerD(trylma, scale);
        Player player5 = new PlayerE(trylma, scale);
        Player player6 = new PlayerF(trylma, scale);

    }


}
