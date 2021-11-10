package com.company;
import java.util.Random;
import java.util.Scanner;

// player = P
// ~ = mist
// _ = land.
// # = water
// row = vertical
// column = horizontal

public class Main {

    static String[][] board = new String[15][40];
    static String [][] displayBoard = new String[15][40];
    static int playerCurrentRow = 0;
    static int playerCurrentColumn = 0;
    static int coinCount = 0;
    static String playerMovement = "";
    static boolean canWin = true;
    static int []monsterRows = new int[10];
    static int []monsterColumns = new int[10];
    static boolean dead = false;

    public static void displayGrid(){

        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 40; j++) {
                displayBoard[i][j] = " ~ ";
            }
        }

        displayBoard[playerCurrentRow][playerCurrentColumn] = " P ";
        String up = board[playerCurrentRow + 1][playerCurrentColumn];
        String down = board[playerCurrentRow - 1][playerCurrentColumn];
        String left = board[playerCurrentRow][playerCurrentColumn - 1];
        String right = board[playerCurrentRow][playerCurrentColumn + 1];
        displayBoard[playerCurrentRow + 1][playerCurrentColumn] = up;
        displayBoard[playerCurrentRow - 1][playerCurrentColumn] = down;
        displayBoard[playerCurrentRow][playerCurrentColumn - 1] = left;
        displayBoard[playerCurrentRow][playerCurrentColumn + 1] = right;

        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 40; j++) {
                System.out.println(displayBoard[i][j]);
            }
            System.out.println("");
        }

    }



    public static void playerTurn(){

        Scanner input = new Scanner(System.in);
        board[playerCurrentRow][playerCurrentColumn] = " _ ";
        displayGrid();
        boolean validTurn = false;
        while (validTurn == false) {
            System.out.println("would you like to go up, down , left or right?");
            playerMovement = input.next();
            if (playerMovement.equals("up") || playerMovement.equals("down") || playerMovement.equals("left") || playerMovement.equals("right")) {
                validTurn = true;
            } else {
                System.out.println("invalid input please try again");
            }
        }

        if (playerMovement.equals("up")) {
            if (board[playerCurrentRow + 1][playerCurrentColumn].equals(" M ")) {
                System.out.println("there is a monster there, pick again");
                playerTurn();
            }
            if (board[playerCurrentRow + 1][playerCurrentColumn].equals(" C ")) {
                System.out.println("well done you have collected a coin");
                coinCount = coinCount + 1;
                System.out.println("your coin total is now " + coinCount);
            }else {
                board[playerCurrentRow + 1][playerCurrentColumn] = " P ";
                playerCurrentRow = playerCurrentRow + 1;
            }
        }

        if (playerMovement.equals("down")) {
            if (board[playerCurrentRow - 1][playerCurrentColumn].equals(" M ")) {
                System.out.println("there is a monster there, pick again");
                playerTurn();
            }
            if (board[playerCurrentRow - 1][playerCurrentColumn].equals(" C ")) {
                System.out.println("well done you have collected a coin");
                coinCount = coinCount + 1;
                System.out.println("your coin total is now " + coinCount);
            }else {
                board[playerCurrentRow - 1][playerCurrentColumn] = " P ";
                playerCurrentRow = playerCurrentRow - 1;
            }
        }

        if (playerMovement.equals("left")) {
            if (board[playerCurrentRow][playerCurrentColumn - 1].equals(" M ")) {
                System.out.println("there is a monster there, pick again");
                playerTurn();
            }
            if (board[playerCurrentRow][playerCurrentColumn - 1].equals(" C ")) {
                System.out.println("well done you have collected a coin");
                coinCount = coinCount + 1;
                System.out.println("your coin total is now " + coinCount);
            }else {
                board[playerCurrentRow][playerCurrentColumn - 1] = " P ";
                playerCurrentRow = playerCurrentColumn - 1;
            }
        }

        if (playerMovement.equals("right")) {
            if (board[playerCurrentRow][playerCurrentColumn + 1].equals(" M ")) {
                System.out.println("there is a monster there, pick again");
                playerTurn();
            }
            if (board[playerCurrentRow][playerCurrentColumn + 1].equals(" C ")) {
                System.out.println("well done you have collected a coin");
                coinCount = coinCount + 1;
                System.out.println("your coin total is now " + coinCount);
            }else {
                board[playerCurrentRow][playerCurrentColumn + 1] = " P ";
                playerCurrentRow = playerCurrentColumn + 1;
            }
        }
        canWinCheck();
        if (canWin == false) {
            System.out.println("not enough coins left, you lose");
        }else {
            if (coinCount > 9) {
                System.out.println("congratulations you got to 10 coins, you win!");
            } else {
                monsterMovement();
                if (dead = true) {
                    System.out.println("you lose, you were killed by a monster");
                } else {
                    playerTurn();
                }
            }
        }
    }



    public static void monsterMovement(){

        Random random = new Random();

        for (int i = 0; i < 10; i++) {
            boolean empty = false;
            while (empty == false) {
                int currentMonsterRow = monsterRows[i];
                int currentMonsterColumn = monsterColumns[i];
                board[currentMonsterRow][currentMonsterColumn] = " _ ";
                int nextMonsterRow = currentMonsterRow;
                int nextMonsterColumn = currentMonsterColumn;
                int monsterMove = random.nextInt(4);
                if (monsterMove == 0) {
                    nextMonsterRow = currentMonsterRow + 1;
                }
                if (monsterMove == 1) {
                    nextMonsterRow = currentMonsterRow - 1;
                }
                if (monsterMove == 2) {
                    nextMonsterColumn = currentMonsterColumn + 1;
                }
                if (monsterMove == 3) {
                    nextMonsterColumn = currentMonsterColumn - 1;
                }
                if (board[nextMonsterRow][nextMonsterColumn].equals(" _ ")) {
                    board[nextMonsterRow][nextMonsterColumn] = "M";
                    empty = true;
                }
                if (board[nextMonsterRow][nextMonsterColumn].equals(" P ")) {
                    dead = true;
                }
            }
        }
    }



    public static void canWinCheck(){

        //need to make can win

    }



    public static void main(String[] args) {
        Random random = new Random();
        Scanner input = new Scanner(System.in);

        //making playing grid (hidden from player)
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 40; j++) {
                board[i][j] = " _ ";
                System.out.print(board[i][j]);
            }
            System.out.println("");
        }
        board[0][0] = "P";
        for (int i = 0; i < 21; i++) {
            int row = random.nextInt(15);
            int column = random.nextInt(40);
            boolean empty = false;
            while (empty == false) {
                if (board[row][column].equals(" _ ")) {
                    board[row][column] = "C";
                    empty = true;
                }
            }
        }
        for (int i = 0; i < 10; i++) {
            int row = random.nextInt(15);
            monsterRows[i] = row;
            int column = random.nextInt(40);
            monsterColumns[i] = column;
            boolean empty = false;
            while (empty == false) {
                if (board[row][column].equals(" _ ")) {
                    board[row][column] = "M";
                    empty = true;
                }
            }
        }
        //end of making playing grid

        //instructions
        //end of instructions
        playerTurn();





    }


}