// Author: Zeus Polanco Salgado
// Assignment 1 - CS 203
// April 2021
//
// Implementation from:
//    A Polynomial Time Algorithm for The N-Queens Problem
//    Rok Sosic and Jun Gu

import java.util.*;
import java.lang.Math;

// IterativeRepair solution class.
public class NQueensIterativeRepair{

  // Desc.: Allows the NQueens solution to be tested from terminal and when
  //  isn't instantiated as an object from a different class.
  // Calls: IterativeRepair()
  public static void main(String[] args){
    int[] solution;
    int n;
    Scanner scan = new Scanner( System.in );
    System.out.println();
    System.out.println("\t\t NQueens Iterative Repair");
    System.out.print("Enter n : ");
    n = scan.nextInt();
    scan.close();

    solution = IterativeRepair(n);

    System.out.println(Arrays.toString(solution));
  }

  // Desc.: Public allows the method to be called from other classes. Performs
  //  IterativeRepair solution for input n.
  // Input: int n (dimension of board)
  // Output: int[] (a possible solution)
  // Called by: main()
  // Calls: generateRandomPermutation(), isSolution(), isAttacked(),
  //  & numberOfAttacks
  public static int[] IterativeRepair(int n){
    int[] temp, board = {0};
    int val, swaps = -1;

    while(swaps != 0){
      swaps = 0;
      board = generateRandomPermutation(n);

      for(int i=0; i<n-1; i++)
        for(int j=i+1; j<n; j++){
          if(isSolution(board)) return board;

          temp = Arrays.copyOf(board, n);
          temp[i] = board[j];
          temp[j] = board[i];

          if( (isAttacked(board, i) || isAttacked(board, j))
          && ( numberOfAttacks(temp) < numberOfAttacks(board) )){
            board = temp = Arrays.copyOf(temp, n);
            swaps++;
          }
        }
    }

    return board;
  }

  // Desc.: Checks for any queen attack.
  // Input: Board/Permutation set up, int[] solution
  // Output: Boolean, false if attack found, true if not attack found
  // Called by: IterativeRepair()
  private static boolean isSolution(int[] solution){
    for(int i=0; i<solution.length-1; i++) // n-1 iterations
      for(int j=1; j<solution.length-i; j++) // n-i iterations
        if(solution[i] == solution[j+i] - j || solution[i] == solution[j+i] + j)
          return false;

    return true;
  }

  // Desc.: Creates ordered array permutation and shuffles pseudoreandomly.
  // Input: int size of array
  // Output: int[] shuffled array permutation
  // Called by: IterativeRepair()
  private static int[] generateRandomPermutation(int n){
      int[] randomPermutation = new int[n];
      for(int i = 0; i<n; i++) randomPermutation[i] = i; // Ordered array

      // Shuffle
      for(int i = 0; i<n; i++){
        int randomIndex = (int) (Math.random() * n);
        int tempValue = randomPermutation[i];
        randomPermutation[i] = randomPermutation[randomIndex];
        randomPermutation[randomIndex] = tempValue;
      }
      return randomPermutation;
  }

  // Desc.: Determines if queen is being attacked. Diagonal attack definition:
  //  delta indecies == delta values
  // Input: int[] permutation values, int index of queen
  // Ouput: Boolean true when attack found and false if nothing found
  // Called by: IterativeRepair()
  private static boolean isAttacked(int[] board, int index){
    for(int i=0; i<board.length; i++)
      if( Math.abs(index-i) == Math.abs(board[index]-board[i]) )
        return true;
    return false;
  }

  // Desc.: Counts the number of individual attacks between queens in board.
  // Input: int[] board values
  // Output: int number of attacks
  // Called by: IterativeRepair()
  private static int numberOfAttacks(int[] board){
    int numOfAttacks = 0;
    for(int i = 0; i<board.length-1; i++)
      for(int j=0; j<board.length; j++)
        if( j-i == Math.abs(board[i]-board[j]) )
          numOfAttacks++;
    return numOfAttacks;
  }
}
