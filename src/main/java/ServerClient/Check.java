package ServerClient;


import javafx.util.Pair;

import java.util.ArrayList;

class Check {
    private Tile[][] board;

    private int x;
    private int y;
    private ArrayList<Pair<Integer, Integer>> realMoves = new ArrayList<>();

    Check(int x, int y, Tile[][] board) {
        this.x = x;
        this.y = y;
        this.board = board;
        start();
    }

    ArrayList<Pair<Integer, Integer>> returnRealMoves() {
        return realMoves;
    }

    private void start() {

        ArrayList<Pair<Integer, Integer>> moves = doMove(x, y);
        int index = 0;
        for (Pair<Integer, Integer> p : moves) {
            int l = p.getKey();
            int r = p.getValue();
            if (!board[l][r].hasPawn()) {
                realMoves.add(p);
            } else if (board[l][r].hasPawn()) {

                jump1(x, y, index, moves);
            }
            index++;
        }


    }

    private void jump1(int x, int y, int index, ArrayList<Pair<Integer, Integer>> moves) {
        Pair<Integer, Integer> pair = new Pair<>(2 * moves.get(index).getKey() - x, 2 * moves.get(index).getValue() - y);
        int l = pair.getKey();
        int r = pair.getValue();
        if (board[l][r] != null && !board[l][r].hasPawn()) {
            boolean czyJest = false;
            for (Pair<Integer, Integer> p : realMoves) {
                if (p.equals(pair)) {
                    czyJest = true;
                    break;
                }
            }
            if (!czyJest) {
                realMoves.add(pair);
                jump2(l, r, index);
            }
        }
    }

    private void jump2(int x, int y, int index) {
        ArrayList<Pair<Integer, Integer>> moves = doMove(x, y);
        int index2 = 0;
        for (Pair<Integer, Integer> p : moves) {

            int l = p.getKey();
            int r = p.getValue();
            if (board[l][r].hasPawn()) {
                jump1(x, y, index2, moves);
            }
            index2++;
        }
    }

    private void isBoardExist(int x, int y, ArrayList<Pair<Integer, Integer>> moves) {
        if (board[x][y] != null) {
            moves.add(new Pair<>(x, y));
        }
    }

    private ArrayList<Pair<Integer, Integer>> doMove(int x, int y) {
        ArrayList<Pair<Integer, Integer>> moves = new ArrayList<>();
        isBoardExist(x + 2, y, moves); // case 1
        isBoardExist(x - 2, y, moves); // case 2
        isBoardExist(x + 1, y - 1, moves); // itp
        isBoardExist(x + 1, y + 1, moves);
        isBoardExist(x - 1, y - 1, moves);
        isBoardExist(x - 1, y + 1, moves);
        return moves;
    }
}
