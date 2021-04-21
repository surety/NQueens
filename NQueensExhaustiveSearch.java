// Author: Zeus Polanco Salgado
// Assignment 1 - CS 203
// April 2021

import java.util.*;
import Board.*;

public class NQueensExhaustiveSearch {

  static Board board;

  public static void main(String[] args){
    int[][] solution2D;
    int n;
    Scanner scan = new Scanner( System.in );
    System.out.println();
    System.out.println("\t\t NQueens Exhaustive Search");
    System.out.print("Enter n : ");
    n = scan.nextInt();
    scan.close();

    board = new Board( n );
    while(!board.isSolution()) board.nextPermutation();

    System.out.println(Arrays.toString( board.getBoardArray() ));
    System.out.println();
    solution2D = board.get2D();
    for( int[] row : solution2D )
      System.out.println(Arrays.toString( row ));
  }
}
