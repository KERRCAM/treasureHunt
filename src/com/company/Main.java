package com.company;
import java.util.Random;
import java.util.Scanner;

// player - only see around them - monsters random move once per turn - monsters one shot player
// ~ = mist
// _ = land
// # = water
// row = down
// column = horizontal

public class Main {

    static String[][] board = new String[15][40];
    static String [][] displayBoard = new String[15][40];

    public static void coOrds(){
        System.out.println();
    }




    public static void main(String[] args) {
    Random random = new Random();
    Scanner input = new Scanner(System.in);

    for (int i = 0; i < 15; i++) {
        for (int j = 0; j < 40; j++) {
            displayBoard[i][j] = " ~ ";
            System.out.print(displayBoard[i][j]);
        }
        System.out.println("");
    }


    for (int i = 0; i < 15; i++) {
        for (int j = 0; j < 40; j++) {
            board[i][j] = " _ ";
            System.out.print(board[i][j]);
        }
        System.out.println("");
    }

    int coinCount = random.nextInt(21);
    for (int i = 0; i < coinCount; i++) {
        int row = random.nextInt(15);
        int column = random.nextInt(40);
        board[row][column] = "C";
    }


   boolean game = false;
   while (game == false) {

   }






    }


}
