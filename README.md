# Peg Solitaire

This program simulates and solves the english [peg solitaire](http://en.wikipedia.org/wiki/Peg_solitaire) game.


## Build

You can build and run the program with IntelliJ Idea. If you don't have the IDE, you can also easily compile the three source files or throw them into your favorite IDE.


## Board

The initial board looks like this:

    _______________________
    |       1  1  1       |
    |       1  1  1       |
    | 1  1  1  1  1  1  1 |
    | 1  1  1  0  1  1  1 |
    | 1  1  1  1  1  1  1 |
    |       1  1  1       |
    |       1  1  1       |
    _______________________

The coordinates start at (0, 0) in the top, left corner and go to (6, 6) in the bottom, right corner. For example, the tile in the middle has the coordinates (3, 3), while the tile in the 2nd row and 4th column has the coordinates (3, 1).

## Commands

The program listens for the following commands:

<table>
<tr><th>Command</th><th>Description</th></tr>
<tr><td>exit</td><td>Exits the game.</td></tr>
<tr><td>save</td><td>Saves the current board to a variable.</td></tr>
<tr><td>load</td><td>Loads the saved board.</td></tr>
<tr><td>solve</td><td>Tries to solve the current board and displays the steps taken to solve it.</td></tr>
<tr><td>up &lt;x&gt; &lt;y&gt;</td><td>Moves the peg at (x, y) up.</td></tr>
<tr><td>down &lt;x&gt; &lt;y&gt;</td><td>Moves the peg at (x, y) down.</td></tr>
<tr><td>left &lt;x&gt; &lt;y&gt;</td><td>Moves the peg at (x, y) left.</td></tr>
<tr><td>right &lt;x&gt; &lt;y&gt;</td><td>Moves the peg at (x, y) right.</td></tr>
</table>

You can also type `help` into the console to see these descriptions.
