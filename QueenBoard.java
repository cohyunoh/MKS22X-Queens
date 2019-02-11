public class QueenBoard{
  private int[][] board;

  public QueenBoard(int size){
    board = new int[size][size];
  }

  private boolean addQueen(int r, int c, int[][] inptBoard){
    if((r>=0 || r < inptBoard.length) && (c>=0 || c < inptBoard.length) && inptBoard[r][c] == 0){
      inptBoard[r][c] = -1;
      for(int i = 1; r + i < inptBoard.length && c + i < inptBoard.length; i++){
        inptBoard[r + i][c + i] += 1;
      }
      for(int i = 1; c + i < inptBoard.length && r - i >= 0; i++){
        inptBoard[r - i][c + i] += 1;
      }
      for(int i = 1; c + i < inptBoard.length; i++){
        inptBoard[r][c + i] += 1;
      }
      return true;
    }
    return false;
  }

  private boolean removeQueen(int r, int c, int[][] inptBoard){
    if((r>=0 || r < inptBoard.length) && (c>=0 || c < inptBoard.length) && inptBoard[r][c] == -1){
      inptBoard[r][c] = 0;
      for(int i = 1; r + i < inptBoard.length && c + i < inptBoard.length; i++){
        inptBoard[r + i][c + i] -= 1;
      }
      for(int i = 1; c + i < inptBoard.length && r - i >= 0; i++){
        inptBoard[r - i][c + i] -= 1;
      }
      for(int i = 1; c + i < inptBoard.length; i++){
        inptBoard[r][c + i] -= 1;
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

  /**
  *@return false when the board is not solveable and leaves the board filled with zeros;
  *        true when the board is solveable, and leaves the board in a solved state
  *@throws IllegalStateException when the board starts with any non-zero value
  */
  public boolean solve(){
    if(board[0][0] != 0){
      throw new IllegalStateException("This Board Shall Not Be SOlved");
    }else{
      return solveHelper(0);
    }
  }

  private int getLength(){
    return board.length;
  }

  private boolean solveHelper(int c){
    if(c >=  board.length){
      return true;
    }
    for(int r = 0; r < board.length; r++){
      if(addQueen(r, c, board)){
        if(solveHelper(c+1)){
          return true;
        }
      removeQueen(r,c, board);
      }
    }
    return false;
  }
    /*
    //version4 - realized that it was not implementing backtracking recursion
    if(r >= board.length){
      //System.out.println("Checking r value");
      //System.out.println("r: " + r);
      //System.out.println("c: " + c);
      //System.out.println("length: " + board.length);
      if(c - 1 >= 0){
        //System.out.println("oldR value");
        //System.out.println("oldR: " + findR(c - 1));
        int oldR = findR(c - 1);
        removeQueen(findR(c-1), c - 1);
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
  */
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
          int oldR = findR(c - 1);
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
        if(c - 1 == 0 && findR(c - 1) == board.length - 1){
          return false;
        }else{
          int oldR = findR(c - 1);
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
        int oldR = findR(c-1);
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
  /*
  private int findR(int c){
    for(int i = 0; i < board.length; i++){
      if(board[i][c] == -1){
        return i;
      }
    }
    return -1;
  }
  */
  public boolean rotateBoard(){
    int[][] newBoard = new int[board.length][board.length];
    boolean ans = true;
    for(int c = 0; c < board.length; c ++){
      for(int r = 0; r < board.length; r ++){
        if(addQueen(r,c,newBoard)){
          ans = true;
        }else{
          return false;
        }
      }
    }
    board = newBoard;
    return ans;
  }
  public boolean flip(int direction){
    int[][] newBoard = new int[board.length][board.length];
    //horizontal flip\
    boolean ans = true;
    if(direction == 0){
      for(int c = 0; c < board.length; c ++){
        for(int r = 0; r < board.length; r ++){
          if(addQueen(r,c,newBoard)){
            ans = true;
          }else{
            return false;
          }
        }
      }
    }else{
      for(int c = 0; c < board.length; c ++){
        for(int r = 0; r < board.length; r ++){
          if(addQueen(r,c,newBoard)){
            ans = true;
          }else{
            return false;
          }
        }
      }
    }
    board = newBoard;
    return ans;
  }

  public void clear(){
    int[][] newBoard = new int[board.length][board.length];
    board = newBoard;
  }

  /*
  private int nextR(int r, int c){
    for(int i = 1; r + i < board.length; r++){
      if(board[r + i][c] == 0){
        return r + i;
      }
    }
    return -1;
  }
  */
  /**
  *@return the number of solutions found, and leaves the board filled with only 0's
  *@throws IllegalStateException when the board starts with any non-zero value
  */
  public int countSolutions(){
    clear();
    return countSolutionsHelper(0, 0);
  }
  private int countSolutionsHelper(int r, int num){
    return 0;
  }
}
