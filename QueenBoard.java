public class QueenBoard{
  private int[][] board;

  public QueenBoard(int size){
    board = new int[size][size];
    fill();
  }

  private void fill(){
    for(int r = 0; r  < board.length; r++){
      for( int c = 0; c < board.length; c++){
        board[r][c] = 0;
      }
    }
  }

  private boolean addQueen(int r, int c){
    if((r>=0 || r < board.length) && (c>=0 || c < board.length)){
      board[r][c] = -1;
      for(int i = 1; r + i < board.length && c + i < board.length; i++){
        board[r + i][c + i] += 1;
      }
      for(int i = 1; c + i < board.length && r - i >= 0; i++){
        board[r - i][c + i] += 1;
      }
      for(int i = 1; c + i < board.length; i++){
        board[r][c + i] += 1;
      }
      return true;
    }
    return false;
  }

  private boolean removeQueen(int r, int c){
    if((r>=0 || r < board.length) && (c>=0 || c < board.length)){
      board[r][c] = 0;
      for(int i = 1; r + i < board.length && c + i < board.length; i++){
        board[r + i][c + i] -= 1;
      }
      for(int i = 1; c + i < board.length && r - i >= 0; i++){
        board[r - i][c + i] -= 1;
      }
      for(int i = 1; c + i < board.length; i++){
        board[r][c + i] -= 1;
      }
      return true;
    }
    return false;
  }

  public String toString(){
    String ans = "";
    for (int r = 0; r < board.length; r++){
      String line = "";
      for (int c = 0; c < board.length; c++){
        if(board[r][c] == -1){
          line += "Q ";
        }else{
          line += "_ ";
        }
      }
      line += "\n";
      ans += line;
    }
    return ans;
  }

  /*
  *@return false when the board is not solveable and leaves the board filled with zeros;
  *        true when the board is solveable, and leaves the board in a solved state
  *@throws IllegalStateException when the board starts with any non-zero value
  */
  public boolean solve(){
    if(board[0][0] != 0){
      throw new IllegalStateException("This Board Shall Not Be SOlved");
    }else{
      return solveHelper(0,0);
    }
  }

  private int getLength(){
    return board.length;
  }

  private boolean solveHelper(int r, int c){
    if(r >= board.length){
      //System.out.println("Checking r value");
      //System.out.println("r: " + r);
      //System.out.println("c: " + c);
      //System.out.println("length: " + board.length);
      if(c - 1 >= 0){
        //System.out.println("oldR value");
        //System.out.println("oldR: " + findOldR(c - 1));
        int oldR = findOldR(c - 1);
        removeQueen(findOldR(c-1), c - 1);
        return solveHelper(oldR + 1, c - 1);
      }else{
        clear();
        return false;
      }
    }else if(c >=  board.length){
      //System.out.println("Checking c value");
      //System.out.println("r: " + r);
      //System.out.println("c: " + c);
      //System.out.println("length: " + board.length);
      return true;
    }else{
      //System.out.println("Checking else");
      //System.out.println("r: " + r);
      //System.out.println("c: " + c);
      //System.out.println("length: " + board.length);
      if(board[r][c] == 0){
        addQueen(r,c);
        return solveHelper(0, c + 1);
      }else{
        return solveHelper(r + 1, c);
      }
    }
  }
    /*
    //version3
      //if( -1 < c && c < board.length){
        //if(-1 < r && r < board.length){
    if(board[r][c] == 0){
      addQueen(r,c);
      if((c + 1) < board.length){
        return solveHelper(0, c + 1);
      }else{
        return true;
      }
    }else{
      if((r + 1) < board.length){
        return solveHelper(r + 1, c);
      }else{
        if((c - 1)  > -1){
          int oldR = findOldR(c - 1);
          removeQueen(oldR, c - 1);
          return solveHelper(oldR + 1, c - 1);
        }else{
          return false;
        }
      }
    }
  }
  */
    /*
    //version2
    if(board[r][c] != 0){
      if(r == board.length - 1){
        if(c - 1 == 0 && findOldR(c - 1) == board.length - 1){
          return false;
        }else{
          int oldR = findOldR(c - 1);
          removeQueen(oldR, c - 1);
          return solveHelper(oldR + 1, c - 1);
        }
      }else{
        return solveHelper(r + 1, c);
      }
    }
    addQueen(r,c);
    if(c == board.length - 1){
      return true;
    }
    return solveHelper(0, c + 1);
    }
    */
    /*
    //version1
    if(board[0][0] != 0){
      throw new IllegalStateException("First value is not zero");
    }
    if(board[r][c] == 0){
      addQueen(r,c);
    }else if(r == board.length - 1 && board[r][c] != 0){
      if(c - 1 >= 0){
        int oldR = findOldR(c-1);
        removeQueen(oldR, c-1);
        return solveHelper(oldR + 1, c - 1);
      }else{
        return false;
      }
    }else if(board[board.length - 1][0] == -1 && c == board.length - 1 && r == board.length - 1 && board[r][c] != 0){
        return false;
    }else if(c == board.length - 1 && board[r][c] == 0){
      return true;
    }
    return solveHelper(r + 1, c);
    */
  private int findOldR(int c){
    for(int i = 0; i < board.length; i++){
      if(board[i][c] == -1){
        return i;
      }
    }
    return -1;
  }
  public void rotateBoard(){
    int[][] newBoard = new int[board.length][board.length];
    for(int r = 0; r < board.length; r ++){
      for(int c = 0; c < board.length; c ++){
        newBoard[r][c] = board[c][(board.length - 1) - r];
      }
    }
    board = newBoard;
  }
  public void flip(int direction){
    int[][] newBoard = new int[board.length][board.length];
    //horizontal flip
    if(direction == 0){
      for(int r = 0; r < board.length; r ++){
        for(int c = 0; c < board.length; c ++){
          newBoard[r][c] = board[r][(board.length - 1) - c];
        }
      }
    }else{
      for(int r = 0; r < board.length; r ++){
        for(int c = 0; c < board.length; c ++){
          newBoard[r][c] = board[(board.length - 1) - r][c];
        }
      }
    }
    board = newBoard;
  }

  private void clear(){
    int[][] newBoard = new int[board.length][board.length];
    board = newBoard;
  }
  /**
  *@return the number of solutions found, and leaves the board filled with only 0's
  *@throws IllegalStateException when the board starts with any non-zero value
  */
  public int countSolutions(){
    return countSolutionsHelper(0,0,0);
  }
  private int countSolutionsHelper(int r, int c, int num){
    
    return num;
  }
}
