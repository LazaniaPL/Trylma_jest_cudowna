package Trylma;

import Trylma.players.*;

public class TrylmaPawns  {



    /**
     * -F E
     * A   D
     * -B C
     * <p>
     * A=2
     * B=3
     * C=4
     * D=5
     * E=6
     * F=7
     */

    void createTwoPlayers(int[][] trylma, int scale) {
        Player player1 = new PlayerA(trylma,scale);
        Player player2 = new PlayerD(trylma, scale);

    }


    void createThreePlayers(int[][] trylma, int scale) {
        Player player1 = new PlayerA(trylma, scale);
        Player player2 = new PlayerC(trylma, scale);
        Player player3 = new PlayerE(trylma,scale);



    }


    void createFourPlayers(int[][] trylma, int scale) {
        Player player1 = new PlayerB(trylma, scale);
        Player player2 = new PlayerC(trylma, scale);
        Player player3 = new PlayerE(trylma, scale);
        Player player4 = new PlayerF(trylma, scale);



    }


    void createSixPlayers(int[][] trylma, int scale) {
        Player player1 = new PlayerA(trylma, scale);
        Player player2 = new PlayerB(trylma,scale);
        Player player3 = new PlayerC(trylma, scale);
        Player player4 = new PlayerD(trylma, scale);
        Player player5 = new PlayerE(trylma, scale);
        Player player6 = new PlayerF(trylma, scale);

    }


}
