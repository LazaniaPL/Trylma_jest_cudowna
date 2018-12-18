package ServerClient;

import javafx.util.Pair;

import java.util.ArrayList;

public class Check {
    private Tile[][] board;

    int x;
    int y;
    ArrayList<Pair<Integer, Integer>> moves = new ArrayList<>();
    ArrayList<Pair<Integer, Integer>> realMoves = new ArrayList<>();
    int newX;
    int newY;
    boolean wanna = false;


    public Check() {
//todo: czy jest tu pole
        moves.add(new Pair<>(x, y + 2)); // case 1
        moves.add(new Pair<>(x, y - 2)); // case 2
        moves.add(new Pair<>(x + 1, y - 1)); // itp
        moves.add(new Pair<>(x + 1, y + 1));
        moves.add(new Pair<>(x - 1, y - 1));
        moves.add(new Pair<>(x - 1, y + 1));
        for (Pair p : moves) {
            int left = (int) p.getKey();
            int right = (int) p.getValue();
            if (!board[left][right].hasPawn()) {
                realMoves.add(p);
            } else if (board[left][right].hasPawn()) {
                jump1(left, right);
            }
        }

    }

    public void jump1(int x, int y) {
        //todo: dla różnych case dodanie do wyniku konkretnego przypadku
        //todo: wtedy możemy dać jump2
        int newX, newY;
        jump2(x, y);
    }

    public void jump2(int x, int y) {

        jump1(x, y);
    }
}
