package tictactoe;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // write your code here
        GameField field = new GameField();
        Scanner scanner = new Scanner(System.in);

        System.out.println();
        int turn = 0;
        field.draw();
        while (field.isRunning) {
            field.nextTurn(++turn % 2 == 1 ? 'X' : 'O');
            field.draw();
            field.check();
        }
    }
}
