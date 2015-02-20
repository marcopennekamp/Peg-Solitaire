package me.pennekamp.pegsolitaire;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Marco Pennekamp
 */
public class Solver {

    private static final String[] directionCommands = new String[] {
        "up", "right", "down", "left"
    };

    List<String> moves = new ArrayList<String>();

    public void solve (Stage stage) {
        long start = System.nanoTime();
        solveRecursive(stage, stage.countPegs());
        System.out.println("Solved in " + ((double) (System.nanoTime() - start) / 1.0e9) + "s.");
    }

    private boolean solveRecursive (Stage stage, int pegs) {
        if (pegs > 1) {
            for (int col = 2; col <= 4; ++col) {
                if (solveRecursiveAt(stage, pegs, 0, col)) return true;
                if (solveRecursiveAt(stage, pegs, 1, col)) return true;
                if (solveRecursiveAt(stage, pegs, 5, col)) return true;
                if (solveRecursiveAt(stage, pegs, 6, col)) return true;
            }

            for (int col = 0; col <= 6; ++col) {
                if (solveRecursiveAt(stage, pegs, 2, col)) return true;
                if (solveRecursiveAt(stage, pegs, 3, col)) return true;
                if (solveRecursiveAt(stage, pegs, 4, col)) return true;
            }

            return false;
        }else {
            return pegs == 1 && stage.getTile(3, 3) == 1;
        }
    }

    private boolean solveRecursiveAt (Stage stage, int pegs, int row, int col) {
        if (stage.getTile(row, col) != 1) return false;

        for (int direction = 0; direction < 4; ++direction) {
            if (stage.movePossibleUncheckedRC(row, col, direction)) {
                stage.moveUnchecked(row, col, direction);
                if (solveRecursive(stage, pegs - 1)) {
                    moves.add(directionCommands[direction] + " " + col + " " + row);
                    return true;
                }
                stage.rollbackMove(row, col, direction);
            }
        }
        return false;
    }

    public void printMoves () {
        System.out.println("Moves:");

        // Print the list in reverse order, since it was created in reverse order.
        for (int i = moves.size() - 1; i >= 0; --i) {
            System.out.println(moves.get(i));
        }
    }

}
