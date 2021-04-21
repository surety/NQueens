// 04-07-21
// Desc. Stores and generates permutations.

package Board;

public class Board {
  private int[] board; // Permutations array
  private boolean[] directions; // Directions array
  private int n;

  public Board(int n){
    this.n = n;
    this.board = new int[n];
    this.directions = new boolean[n];
    for(int i=0; i<this.n; i++){
      this.board[i] = i;
      this.directions[i] = true;
    }
  }

  public boolean isSolution(){
    for(int i=0; i<this.n-1; i++) // n-1 iterations
      for(int j=1; j<this.n-i; j++) // n-i iterations
        if(board[i] == board[j+i] - j || board[i] == board[j+i] + j)
          return false;

    return true;
  }

  public void nextPermutation(){
    int maxMobileIndex = FindMaxMobileElement(this.board, this.directions);
    int maxMobileValue = this.board[maxMobileIndex];
    SwapMobileElement( maxMobileIndex );
    ReverseDirection( maxMobileValue );
  }

  private int FindMaxMobileElement( int[] P, boolean[] D ) {
   // Init temp variables.
   int maxMobileVal = 0;
   int maxMobileIndex = -1;
   // Iterate permutation elements.
   for( int i = 0; i < P.length; i++ ) {
      // Check if current element is mobile using its direction and its value.
      if( ( ( this.directions[i] ) && ( i > 0 ) && ( this.board[i] > this.board[i-1] ) ) ||
          ( ( !this.directions[i] ) && ( i < ( this.board.length - 1 ) ) && ( this.board[i] > this.board[i+1] ) ) ) {
         // Check if current mobile element is greater than current max mobile element.
         if( this.board[i] > maxMobileVal ) {
            maxMobileVal = this.board[i];
            maxMobileIndex = i; } } }
   // Return index of max mobile element.
   return maxMobileIndex;
 }
  private void SwapMobileElement( int k ) {
   // Init temp variables.
   int tempVal = this.board[k];
   boolean tempDir = this.directions[k];
   // Swap mobile element following its direction.
   if( this.directions[k] ) {
      this.board[k] = this.board[k-1];
      this.board[k-1] = tempVal;
      this.directions[k] = this.directions[k-1];
      this.directions[k-1] = tempDir; }
   else {
      this.board[k] = this.board[k+1];
      this.board[k+1] = tempVal;
      this.directions[k] = this.directions[k+1];
      this.directions[k+1] = tempDir; }
  }
  private void ReverseDirection(int v ) {
     // Iterate permutation elements.
     for( int i = 0; i < this.board.length; i++ ) {
        // Reverse direction if element is greater than input value (max mobile element).
        if( this.board[i] > v ) { this.directions[i] = !this.directions[i]; } }
  }


  public int[][] get2D(){
    int[][] representation2D = new int[n][n];
    for(int i=0; i<this.n; i++)
      for(int j=0; j<this.n; j++){
        if(this.board[i] == j) representation2D[j][i] = 7;
        else representation2D[j][i] = 0;
      }
    return representation2D;
  }

  public int[] getBoardArray(){
    return this.board;
  }

  public void setBoardArray(int[] board){
    this.board = board;
  }

}
