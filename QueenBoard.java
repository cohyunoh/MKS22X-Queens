public class QueenBoard{
  private int[][] board;

  public QueenBoard(int size){
    board = new int[size][size];
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
}
