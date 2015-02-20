package me.pennekamp.pegsolitaire;

/**
 * @author Marco Pennekamp
 * @author Christopher Riesner
 */
public class Stage {

    // These numeric constants must not be changed.
    public static final int ROWS = 7;
    public static final int COLS = 7;
    public static final int SIZE = ROWS * COLS;
    public static final int UP = 0;
    public static final int RIGHT = 1;
    public static final int DOWN = 2;
    public static final int LEFT = 3;

    private int[] board;
    private int[] save;

    public Stage() {
        board = new int[SIZE];
        save = new int[SIZE];

        for (int row = 0; row < ROWS; row++) {
            if (row >= 2 && row <= 4) {
                for (int col = 0; col < COLS; col++) {
                    setTile(row, col, 1);
                }
            }else {
                for (int col = 0; col < COLS; col++) {
                    if (col > 1 && col < 5) {
                        setTile(row, col, 1);
                    }else {
                        setTile(row, col, 2);
                    }
                }
            }
        }

        setTile(3, 3, 0);

        save();
    }

    public boolean movePossible (int row, int col, int direction) {
        return getTile(row, col) == 1 && movePossibleUncheckedRC(row, col, direction);
    }

    public boolean movePossibleUncheckedRC (int row, int col, int direction) {
        switch (direction) {
            case UP:    return (row - 2) >= 0    && getTile(row - 1, col) == 1 && getTile(row - 2, col) == 0;
            case RIGHT: return (col + 2) < COLS  && getTile(row, col + 1) == 1 && getTile(row, col + 2) == 0;
            case DOWN:  return (row + 2) < ROWS  && getTile(row + 1, col) == 1 && getTile(row + 2, col) == 0;
            case LEFT:  return (col - 2) >= 0    && getTile(row, col - 1) == 1 && getTile(row, col - 2) == 0;
        }
        return false;
    }

    public boolean move(int row, int col, int direction) {
        if (movePossible(row, col, direction)) {
            moveUnchecked(row, col, direction);
            return true;
        }else {
            return false;
        }
    }

    public void moveUnchecked (int row, int col, int direction) {
        setTile(row, col, 0);
        switch(direction) {
            case UP:
                setTile(row - 1, col, 0);
                setTile(row - 2, col, 1);
                break;
            case RIGHT:
                setTile(row, col + 1, 0);
                setTile(row, col + 2, 1);
                break;
            case DOWN:
                setTile(row + 1, col, 0);
                setTile(row + 2, col, 1);
                break;
            case LEFT:
                setTile(row, col - 1, 0);
                setTile(row, col - 2, 1);
                break;
        }
    }

    public void rollbackMove (int row, int col, int direction) {
        setTile(row, col, 1);
        switch(direction) {
            case UP:
                setTile(row - 1, col, 1);
                setTile(row - 2, col, 0);
                break;
            case RIGHT:
                setTile(row, col + 1, 1);
                setTile(row, col + 2, 0);
                break;
            case DOWN:
                setTile(row + 1, col, 1);
                setTile(row + 2, col, 0);
                break;
            case LEFT:
                setTile(row, col - 1, 1);
                setTile(row, col - 2, 0);
                break;
        }
    }

    public int getTile (int row, int col) {
        return board[getIndex(row, col)];
    }

    public void setTile (int row, int col, int value) {
        board[getIndex(row, col)] = value;
    }

    public int getIndex (int row, int col) {
        return row * ROWS + col;
    }

    public int countPegs () {
        int count = 0;
        for (int row = 0; row < ROWS; ++row) {
            for (int col = 0; col < COLS; ++col) {
                if (getTile(row, col) == 1) {
                    count += 1;
                }
            }
        }
        return count;
    }

    public void printStage() {
        System.out.println("_______________________");
        for (int row = 0; row < ROWS; row++) {
            System.out.print("|");
            for (int col = 0; col < COLS; col++) {
                if (getTile(row, col) == 2) {
                    System.out.print("   ");
                }else {
                    System.out.print(" " + getTile(row, col) + " ");
                }
            }
            System.out.println("|");
        }
        System.out.println("_______________________");
    }

    public void save () {
        copy(board, save);
    }

    public void load () {
        copy(save, board);
    }

    private static void copy (int[] source, int[] dest) {
        System.arraycopy(source, 0, dest, 0, SIZE);
    }

}
