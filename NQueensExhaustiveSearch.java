// Author: Zeus Polanco Salgado
// Assignment 1 - CS 203
// April 2021

import java.util.*;

// ExhaustiveSearch solution class.
public class NQueensExhaustiveSearch {

  // Desc.: Allows the NQueens solution to be tested from terminal and when
  //  isn't instantiated as an object from a different class.
  // Calls: ExhaustiveSearch()
  public static void main(String[] args){
    int[] solution;
    int n;
    Scanner scan = new Scanner( System.in );
    System.out.println();
    System.out.println("\t\t NQueens Exhaustive Search");
    System.out.print("Enter n : ");
    n = scan.nextInt();
    scan.close();

    solution = ExhaustiveSearch(n);

    System.out.println(Arrays.toString(solution));
  }

  // Desc.: Public allows the method to be called from other classes. Performs
  //  ExhaustiveSearch solution for input n.
  // Input: int n (dimension of board)
  // Output: int[] (a possible solution)
  // Called by: main()
  // Calls: FindMaxMobileElement(), SwapMobileElement(), isSolution(),
  //  & ReverseDirection()
  public static int[] ExhaustiveSearch(int n){
    int[][] permutation = new int[2][n];
    int maxMobileValue, maxMobileIndex;

    // Create initial permutation
    for(int i=0; i<n; i++){
      permutation[0][i] = i;
      permutation[1][i] = 1;
    }

    // Will continue until solution is found
    while(!isSolution(permutation[0])){
      maxMobileIndex = FindMaxMobileElement(permutation);
      maxMobileValue = permutation[0][maxMobileIndex];
      permutation[0] = SwapMobileElement( permutation, maxMobileIndex );
      permutation[1] = ReverseDirection( permutation, maxMobileValue );
    }

    return permutation[0];
  }

  // Desc.: Checks for any queen attack.
  // Input: Board/Permutation set up, int[] solution
  // Output: Boolean, false if attack found, true if not attack found
  // Called by: ExhaustiveSearch()
  private static boolean isSolution(int[] solution){
    for(int i=0; i<solution.length-1; i++) // n-1 iterations
      for(int j=1; j<solution.length-i; j++) // n-i iterations
        if(solution[i] == solution[j+i] - j || solution[i] == solution[j+i] + j)
          return false;

    return true;
  }

  // Desc.: Searches for index of element to swap around in permutation
  // Input: int[][] permutation with values and directions
  // Output: int index of mobile element
  // Called by: ExhaustiveSearch()
  private static int FindMaxMobileElement(int[][] permutation){
    // Init temp variables.
    int maxMobileVal = 0;
    int maxMobileIndex = -1;
    // Iterate permutation elements.
    for( int i = 0; i < permutation[0].length; i++ ) {
       // Check if current element is mobile using its direction and its value.
             // When facing forward
       if( ( ( permutation[1][i] == 1) && ( i > 0 ) &&
             ( permutation[0][i] > permutation[0][i-1] ) ) ||
             // When facing backward
           ( ( permutation[1][i] == 0) && ( i < ( permutation[0].length - 1 ) ) &&
             ( permutation[0][i] > permutation[0][i+1] ) ) ) {
          // Check if current mobile element is greater than current max mobile element.
          if( permutation[0][i] > maxMobileVal ) {
             maxMobileVal = permutation[0][i];
             maxMobileIndex = i; } } }
    // Return index of max mobile element.
    return maxMobileIndex;
  }

  // Desc.: Performs swapping of mobile element inside permutation
  // Input: int[][] permutation with values and directions, int index of
  //  mobile element
  // Output: int[] new values of permutation after mobile element swap
  // Called by: ExhaustiveSearch()
  private static int[] SwapMobileElement(int[][] permutation, int index){   // Init temp variables.
     int tempVal = permutation[0][index];
     int tempDir = permutation[1][index];
     // Swap mobile element following its direction.
     if( permutation[1][index] == 1) {
        permutation[0][index] = permutation[0][index-1];
        permutation[0][index-1] = tempVal;
        permutation[1][index] = permutation[1][index-1];
        permutation[1][index-1] = tempDir; }
     else {
        permutation[0][index] = permutation[0][index+1];
        permutation[0][index+1] = tempVal;
        permutation[1][index] = permutation[1][index+1];
        permutation[1][index+1] = tempDir; }

     return permutation[0];
  }

  // Desc.: Reversed direction of elements greater than mobile element
  // Input: int[][] permutation with values and directions, int index of
  //  mobile element
  // Output: int[] direction of permutation values after mobile element swap
  // Called by: ExhaustiveSearch()
  private static int[] ReverseDirection(int[][] permutation, int value ){
    // Iterate permutation elements.
    for( int i = 0; i < permutation[0].length; i++ ) {
       // Reverse direction if element is greater than input value (max mobile element).
       if( permutation[0][i] > value )
        permutation[1][i] = (permutation[1][i] + 1) % 2;
    }

    return permutation[1];
  }
}
