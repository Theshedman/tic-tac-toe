package tictactoe;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        char[][] grid = getGrid();


        drawBoard(grid);
        calculateMove(grid);
    }

    private static char[][] getGrid() {
        char[][] grid = {{' ', ' ', ' '}, {' ', ' ', ' '}, {' ', ' ', ' '}};

        return grid;
    }

    private static void drawBoardLines() {
        final int BOARD_WIDTH = 9;
        for (int i = 0; i < BOARD_WIDTH; i++) {
            System.out.print("-");
        }

        System.out.println();
    }

    private static void drawBoardCells(char[][] grid) {

        for (char[] rows : grid) {

            System.out.print("| ");

            for (char cell : rows) {
                System.out.print(cell + " ");
            }

            System.out.println("|");
        }
    }

    private static void drawBoard(char[][] grid) {
        drawBoardLines();

        drawBoardCells(grid);

        drawBoardLines();
    }

    private static String printResult(String input) {
        boolean isEmptyCells = input.contains("_") || input.contains(" ");
        boolean xRow = false;
        boolean oRow = false;

        String lowerCasedInput = input.toLowerCase();

        int xCount = 0;
        int oCount = 0;

        for (int i = 0; i < lowerCasedInput.length(); i++) {
            if (lowerCasedInput.charAt(i) == 'x') {
                xCount++;
            }
            if (lowerCasedInput.charAt(i) == 'o') {
                oCount++;
            }
        }

        if (xCount < 3 || oCount < 3) {
            return " ";
        }

        if (
                lowerCasedInput.charAt(0) == lowerCasedInput.charAt(4) &&
                        lowerCasedInput.charAt(4) == lowerCasedInput.charAt(8)
        ) {
            if (lowerCasedInput.charAt(0) == 'x') {
                xRow = true;
            } else if (lowerCasedInput.charAt(0) == 'o') {
                oRow = true;
            }
        }

        if (
                lowerCasedInput.charAt(2) == lowerCasedInput.charAt(4) &&
                        lowerCasedInput.charAt(4) == lowerCasedInput.charAt(6)
        ) {
            if (lowerCasedInput.charAt(2) == 'x') {
                xRow = true;
            } else if (lowerCasedInput.charAt(2) == 'o') {
                oRow = true;
            }
        }

        if (
                lowerCasedInput.charAt(0) == lowerCasedInput.charAt(3) &&
                        lowerCasedInput.charAt(3) == lowerCasedInput.charAt(6)
        ) {
            if (lowerCasedInput.charAt(0) == 'x') {
                xRow = true;
            } else if (lowerCasedInput.charAt(0) == 'o') {
                oRow = true;
            }
        }

        if (
                lowerCasedInput.charAt(2) == lowerCasedInput.charAt(5) &&
                        lowerCasedInput.charAt(5) == lowerCasedInput.charAt(8)
        ) {
            if (lowerCasedInput.charAt(2) == 'x') {
                xRow = true;
            } else if (lowerCasedInput.charAt(2) == 'o') {
                oRow = true;
            }
        }

        if (
                lowerCasedInput.charAt(1) == lowerCasedInput.charAt(4) &&
                        lowerCasedInput.charAt(4) == lowerCasedInput.charAt(7)
        ) {
            if (lowerCasedInput.charAt(1) == 'x') {
                xRow = true;
            } else if (lowerCasedInput.charAt(1) == 'o') {
                oRow = true;
            }
        }

        if (
                lowerCasedInput.charAt(0) == lowerCasedInput.charAt(1) &&
                        lowerCasedInput.charAt(1) == lowerCasedInput.charAt(2)
        ) {
            if (lowerCasedInput.charAt(0) == 'x') {
                xRow = true;
            } else if (lowerCasedInput.charAt(0) == 'o') {
                oRow = true;
            }
        }

        if (
                lowerCasedInput.charAt(3) == lowerCasedInput.charAt(4) &&
                        lowerCasedInput.charAt(4) == lowerCasedInput.charAt(5)
        ) {
            if (lowerCasedInput.charAt(3) == 'x') {
                xRow = true;
            } else if (lowerCasedInput.charAt(3) == 'o') {
                oRow = true;
            }
        }

        if (
                lowerCasedInput.charAt(6) == lowerCasedInput.charAt(7) &&
                        lowerCasedInput.charAt(7) == lowerCasedInput.charAt(8)
        ) {
            if (lowerCasedInput.charAt(6) == 'x') {
                xRow = true;
            } else if (lowerCasedInput.charAt(6) == 'o') {
                oRow = true;
            }
        }

        if (!xRow && !oRow && !isEmptyCells) {
            System.out.print("Draw");

            return "draw";
        }

        if (xRow && !oRow) {
            System.out.print("X wins");

            return "win";
        }

        if (!xRow && oRow) {
            System.out.print("O wins");

            return "win";
        }

        System.out.println();

        return "";
    }

    private static void calculateMove(char[][] grid) {
        int[] coordinates = new int[2];
        boolean xPlayer = false;

        do {
            xPlayer = !xPlayer;
            fillCoordinates(coordinates);
            checkRange(coordinates);
            updateBoard(coordinates, grid, xPlayer);
            drawBoard(grid);
            String result = printResult(stringBuilder(grid));

            if (result == "draw" || result == "win") {
                break;
            }
        } while (true);
    }

    private static void checkRange(int[] coordinates) {
        while (coordinates[0] < 0 || coordinates[0] > 3 || coordinates[1] < 0 || coordinates[1] > 3) {
            System.out.println("Coordinates should be from 1 to 3!");

            fillCoordinates(coordinates);
        }
    }

    private static void updateBoard(int[] coordinates, char[][] grid, boolean xPlayer) {
        do {
            int cell = grid[coordinates[0] - 1][coordinates[1] - 1];

            try {
                if (cell == '_' || cell == ' ') {
                    grid[coordinates[0] - 1][coordinates[1] - 1] = xPlayer ? 'X' : 'O';

                    break;
                } else {
                    throw new Exception("This cell is occupied! Choose another one!");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

            fillCoordinates(coordinates);
            checkRange(coordinates);

        } while (true);
    }

    private static void fillCoordinates(int[] coordinates) {
        do {
            System.out.print("Enter the coordinates: ");

            Scanner scanner = new Scanner(System.in);

            try {
                coordinates[0] = scanner.nextInt();
                coordinates[1] = scanner.nextInt();

                break;
            } catch (InputMismatchException e) {
                System.out.println("You should enter numbers!");
            }
        } while (true);
    }

    private static String stringBuilder(char[][] grid) {
        String string = "";

        for (char[] chars : grid) {
            for (char letter : chars) {
                string += Character.toString(letter);
            }
        }

        return string;
    }
}
