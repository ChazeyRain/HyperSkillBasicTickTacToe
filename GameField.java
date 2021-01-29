package tictactoe;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class GameField {
    final Scanner scanner = new Scanner(System.in);

    public boolean isRunning = true;

    private char[][] cellInput = new char[3][3];

    /*
    Cell based Constructor
     */
    public GameField(String input) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                cellInput[i][j] = input.charAt(i*3 + j);
            }
        }
    }
    /*
    Default constructor
     */
    public GameField() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                cellInput[i][j] = '_';
            }
        }
    }

    public void draw() {
        System.out.println("---------");

        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(cellInput[i][j] + " ");
            }
            System.out.println("|");
        }
        System.out.println("---------");
    }

    /*
    LOGIC
     */
    public void check() {
        if (!checkCount()) {
            isRunning = false;
            System.out.println("Impossible");
        };
        switch (somebodyWins()) {
            case 'X':
                isRunning = false;
                System.out .println("X wins");
                return;
            case 'O':
                isRunning = false;
                System.out.println("O wins");
                return;
            case 'e':
                isRunning = false;
                System.out.println("Impossible");
                return;
            default:
                break;
        }
        if (isDraw()) {
            isRunning = false;
            System.out.println("Draw");
        }
    }

    private boolean checkCount() {
        int xCount = 0;
        int oCount = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (cellInput[i][j] == 'X') {
                    xCount++;
                } else if (cellInput[i][j] == 'O') {
                    oCount++;
                }
            }

        }
        return xCount - oCount <= 1 && xCount - oCount >= -1;
    }

    private char somebodyWins() {
        int wins = 0;
        char winCell = 'e';
        for (int i = 0; i < 3; i++) {
            if (cellInput[i][0] != '_' && cellInput[i][0] == cellInput[i][1] && cellInput[i][0] == cellInput[i][2]) {
                wins++;
                winCell = cellInput[0][i];
            }
            if (cellInput[0][i] != '_' && cellInput[0][i] == cellInput[1][i] && cellInput[0][i] == cellInput[2][i]) {
                wins++;
                winCell = cellInput[0][i];
            }
        }
        if (cellInput[0][0] != '_' && cellInput[0][0] == cellInput[1][1] && cellInput[0][0] == cellInput[2][2]) {
            wins++;
            winCell = cellInput[0][0];
        }
        if (cellInput[0][2] != '_' && cellInput[0][2] == cellInput[1][1] && cellInput[0][2] == cellInput[2][0]) {
            wins++;
            winCell = cellInput[0][2];
        }
        if (wins == 1) {
            return winCell;
        } else if (wins > 1) {
            return 'e';
        } else {
            return ' ';
        }
    }

    private boolean isDraw() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (cellInput[i][j] == '_') {
                    return false;
                }
            }
        }
        return true;
    }

    /*
    Gameplay
     */
    public void nextTurn(char mark) {
        System.out.println("Enter the coordinates: ");
        try {
            int r = scanner.nextInt() - 1;
            int c = scanner.nextInt() - 1;
            if (cellInput[r][c] == '_') {
                cellInput[r][c] = mark;
            } else {
                System.out.println("This cell is occupied! Choose another one!");
                nextTurn(mark);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Coordinates should be from 1 to 3!");
            nextTurn(mark);
        } catch (InputMismatchException e) {
            System.out.println("You should enter numbers!");
            nextTurn(mark);
        }
    }
}
