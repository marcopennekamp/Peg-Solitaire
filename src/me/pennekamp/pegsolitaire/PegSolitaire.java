package me.pennekamp.pegsolitaire;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author Marco Pennekamp
 * @author Christopher Riesner
 */
public class PegSolitaire {

    public static void main(String[] args) {
        Stage stage = new Stage();
        stage.printStage ();

        boolean running = true;
        BufferedReader input = new BufferedReader(new InputStreamReader (System.in));
        while (running) {
            try {
                String command = input.readLine();
                String[] parts = command.split(" ");
                if (parts.length >= 1) {
                    String op = parts[0].toLowerCase();
                    if (op.equals("exit")) {
                        running = false;
                    }else if (op.equals("help")) {
                        System.out.println("Commands:");
                        System.out.println("exit       Exit the game.");
                        System.out.println("save       Save the current board.");
                        System.out.println("load       Load the saved board.");
                        System.out.println("solve      Tries to solve the current board and displays the steps.");
                        System.out.println("up x y     Move up.");
                        System.out.println("down x y   Move down.");
                        System.out.println("left x y   Move left.");
                        System.out.println("right x y  Move right.");
                    }else if (op.equals("print")) {
                        stage.printStage();
                    }else if (op.equals("save")) {
                        stage.save();
                    }else if (op.equals("load")) {
                        stage.load();
                        stage.printStage();
                    }else if (op.equals("solve")) {
                        Solver solver = new Solver();
                        solver.solve(stage);
                        solver.printMoves();
                        stage.printStage();
                    }else {
                        if (parts.length >= 3) {
                            // x corresponds to col, y corresponds to row.
                            int col = Integer.parseInt(parts[1]);
                            int row = Integer.parseInt(parts[2]);
                            int direction = -1;
                            if (op.equals("up")) {
                                direction = Stage.UP;
                            }else if (op.equals("down")) {
                                direction = Stage.DOWN;
                            }else if (op.equals("left")) {
                                direction = Stage.LEFT;
                            }else if (op.equals("right")) {
                                direction = Stage.RIGHT;
                            }

                            if (direction != -1) {
                                if (stage.move(row, col, direction)) {
                                    stage.printStage();
                                }else {
                                    System.out.println("Not a valid move!");
                                }
                            }
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace ();
                running = false;
            }
        }
    }
    
}
