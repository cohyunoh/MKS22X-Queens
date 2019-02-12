import java.util.ArrayList;
public class QueenBoard{
  private int[][] board;

  public QueenBoard(int size){
    board = new int[size][size];
  }

  private boolean addQueen(int r, int c, int[][] inpt){
    if((r>=0 || r < inpt.length) && (c>=0 || c < inpt.length) && inpt[r][c] == 0){
      inpt[r][c] = -1;
      for(int i = 1; r + i < inpt.length && c + i < inpt.length; i++){
        inpt[r + i][c + i] += 1;
      }
      for(int i = 1; c + i < inpt.length && r - i >= 0; i++){
        inpt[r - i][c + i] += 1;
      }
      for(int i = 1; c + i < inpt.length; i++){
        inpt[r][c + i] += 1;
      }
      return true;
    }
    return false;
  }

  private boolean removeQueen(int r, int c, int[][] inpt){
    if((r>=0 || r < inpt.length) && (c>=0 || c < inpt.length) && inpt[r][c] == -1){
      inpt[r][c] = 0;
      for(int i = 1; r + i < inpt.length && c + i < inpt.length; i++){
        inpt[r + i][c + i] -= 1;
      }
      for(int i = 1; c + i < inpt.length && r - i >= 0; i++){
        inpt[r - i][c + i] -= 1;
      }
      for(int i = 1; c + i < inpt.length; i++){
        inpt[r][c + i] -= 1;
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
    if(findR(0)){
      throw new IllegalStateException("This Board Shall Not Be SOlved");
    }else{
      return solveHelper(0);
    }
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

  private boolean findR(int c){
    for(int i = 0; i < board.length; i++){
      if(board[i][c] == -1){
        return true;
      }
    }
    return false;
  }

  /**
  *@return the number of solutions found, and leaves the board filled with only 0's
  *@throws IllegalStateException when the board starts with any non-zero value
  */
  public int countSolutions(){
    if(findR(0)){
      throw new IllegalStateException("This Board Shall Not Be SOlved");
    }else{
      return countSolutionsHelper(0, 0);
    }
  }
  private int countSolutionsHelper(int c, int num){
    if(c >=  board.length){
      num ++;
      return num;
    }
    for(int r = 0; r < board.length; r++){
      if(addQueen(r, c, board)){
        if(countSolutionsHelper(c+1, num) > num){
          num = countSolutionsHelper(c+1, num);
        }
        removeQueen(r,c, board);
      }
    }
    return num;
  }
}
