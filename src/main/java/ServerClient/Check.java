package ServerClient;

import javafx.util.Pair;

import java.util.ArrayList;

public class Check {
    private Tile[][] board;

    int x;
    int y;
    ArrayList<Pair<Integer, Integer>> moves = new ArrayList<>();
    ArrayList<Pair<Integer, Integer>> realMoves = new ArrayList<>();


    public Check() {

        moves.add(new Pair<>(x, y + 2));
        moves.add(new Pair<>(x, y - 2));
        moves.add(new Pair<>(x + 1, y - 1));
        moves.add(new Pair<>(x + 1, y + 1));
        moves.add(new Pair<>(x - 1, y - 1));
        moves.add(new Pair<>(x - 1, y + 1));
        for (Pair p : moves) {
            int left = (int) p.getKey();
            int right = (int) p.getValue();
            if (!board[left][right].hasPawn()) {
                realMoves.add(p);
            }
        }

    }

}
