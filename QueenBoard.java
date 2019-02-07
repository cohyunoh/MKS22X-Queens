public class QueenBoard{
  private int[][] board;

  public QueenBoard(int size){
    board = new int[size][size];
  }

  private boolean addQueen(int r, int c){
    if((r>=0 || r < board.length) && (c>=0 || c < board.length)){
      board[r][c] = -1;
      for(int i = 0; r + i < board.length && c + i < board.length; i++){
        board[r + i][c + i] += 1;
      }
      for(int i = 0; c + i < board.length && r - i >= 0; i++){
        board[r - i][c + i] += 1;
      }
      for(int i = 0; c + i < board.length; i++){
        board[r][c + i] += 1;
      }
      return true;
    }
    return false;
  }
}
