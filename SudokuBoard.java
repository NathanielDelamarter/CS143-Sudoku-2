import java.io.*;
import java.util.*;

public class SudokuBoard { 
   private int [][] board;
   
   public SudokuBoard (String fileName) throws FileNotFoundException {
      board = new int [9][9];
      File f = new File(fileName);
      Scanner s = new Scanner (f);
      
      for (int r= 0; r<9; r++ ) {
         if (s.hasNextLine()) {
            String line = s.nextLine();
            for (int c=0; c< 9; c++){
               char num = line.charAt(c);
               
               if(num=='.') {
                  board[r][c]=0;
               } else {
                  board[r][c]= num-'0';
               }
            }
         }
         
      }
   }
   
   public Boolean isValid() {
      return true;  
   }
   
   private Boolean rowValid(int r) {
      Set<Integer> row = new HashSet<>();
      for(int c = 0; c < board[r].length; c++) {
         if(row.contains(board[r][c]) && board[r][c] != 0) {
            return false;
         } else if(board[r][c] > 9 || board[r][c] < 0) {
            return false;
         } else {
            row.add(board[r][c]);
         }
         
      }
      return true;
   }
   
   private Boolean colValid(int c) {
      Set<Integer> col = new HashSet<>();
      for(int r = 0; r < board.length; r++) {
         if(col.contains(board[r][c]) && board[r][c] != 0) {
            return false;
         } else if(board[r][c] > 9 || board[r][c] < 0) {
            return false;
         } else {
            col.add(board[r][c]);
         }
         
      }
      return true;
   }
    
    private int[][] miniSquare(int spot) {
      int[][] mini = new int[3][3];
      for(int r = 0; r < 3; r++) {
         for(int c = 0; c < 3; c++) {
            // whoa - wild! This took me a solid hour to figure out (at least)
            // This translates between the "spot" in the 9x9 Sudoku board
            // and a new mini square of 3x3
            mini[r][c] = board[(spot - 1) / 3 * 3 + r][(spot - 1) % 3 * 3 + c];
         }
      }
      return mini;
   }
   
   private Boolean miniSquareValid(int[][] mini){
      Set<Integer> square = new HashSet<>();
      for(int r = 0; r < board.length; r++) {
         for(int c = 0; c < board[r].length; c++){
            if(square.contains(board[r][c]) && board[r][c] != 0) {
            return false;
         } else if(board[r][c] > 9 || board[r][c] < 0) {
            return false;
         } else {
            square.add(board[r][c]);
         }

         }
      }
      return true;
  }
   

    
   public String toString () {
      String result = "";
      for (int r=0;r<9;r++) {
         if (r%3 ==0) {
            result += "| - - - + - - - + - - - |\n";
         }
         for (int c=0; c<9; c++) {
            if(c%3==0) {
               result+="| ";
            }
            if (board[r][c]==0) {
               result += ". ";
            } else {
               result += board[r][c] + " ";
            }
         }
         result += "| \n";
         
      }
      result += "| - - - + - - - + - - - |\n";
      return result;
   }
}

/*
# PROGRAM OUTPUT
 | - - - + - - - + - - - |
 | 2 . . | 1 . 5 | . . 3 | 
 | . 5 4 | . . . | 7 1 . | 
 | . 1 . | 2 . 3 | . 8 . | 
 | - - - + - - - + - - - |
 | 6 . 2 | 8 . 7 | 3 . 4 | 
 | . . . | . . . | . . . | 
 | 1 . 5 | 3 . 9 | 8 . 6 | 
 | - - - + - - - + - - - |
 | . 2 . | 7 . 1 | . 6 . | 
 | . 8 1 | . . . | 2 4 . | 
 | 7 . . | 4 . 2 | . . 1 | 
 | - - - + - - - + - - - |
*/