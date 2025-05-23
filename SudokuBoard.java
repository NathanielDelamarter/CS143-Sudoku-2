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
      for(int r = 0; r < board.length; r++) {
         if(!rowValid(r))
            return false;
      }
      for(int c = 0; c < board.length; c++) {
         if(!colValid(c))
            return false;
      }
      for(int i = 1; i < 10; i++) {
         if(!miniSquareValid(miniSquare(i)))
            return false;
      } 
      return true;
   }
   
   public Boolean isSolved() {
      // Initialized a new HashMap and loaded the keys 1 - 9 with values of 0
      Map<Integer,Integer> map = new HashMap<>();
      for(int k = 1; k < 10; k++) {
         map.put(k,0);
      }
      // Iterated through board array and counted up occurances of 1 - 9, disregarding anything else
      for(int r = 0; r < board.length; r++) {
         for(int c = 0; c < board[r].length; c++) {
            if(board[r][c] < 10 && board[r][c] > 0) {
               int count = map.get(board[r][c]);
               map.put(board[r][c],count + 1);
            }
         }
      }
      // Iterated through HashMap and returned false if any of the values were not equal to 9
      for (Map.Entry<Integer,Integer> entry : map.entrySet()) {
         if (entry.getValue() != 9)
            return false;
      }
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
      for(int r = 0; r < mini.length; r++) {
         for(int c = 0; c < mini[r].length; c++){
            if(square.contains(mini[r][c]) && mini[r][c] != 0) {
               return false;
            } else if(mini[r][c] > 9 || mini[r][c] < 0) {
               return false;
            } else {
               square.add(mini[r][c]);
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