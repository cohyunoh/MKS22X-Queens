import java.util.ArrayList;
public class QueenBoard{
  private int[][] board;

  public QueenBoard(int size){
    board = new int[size][size];
  }

  private boolean addQueen(int r, int c){
    //will only run if the params given don't go off the board and the place we are adding to is
    //already empty
    if((r>=0 || r < board.length) && (c>=0 || c < board.length) && board[r][c] == 0){
      //we denote a queen with a -1 on the board
      board[r][c] = -1;
      //adds 1 to all the spots in the path of down and to the right of the queen
      for(int i = 1; r + i < board.length && c + i < board.length; i++){
        board[r + i][c + i] += 1;
      }
      //adds 1 to all the spots in the path of up and to the right of the queen
      for(int i = 1; c + i < board.length && r - i >= 0; i++){
        board[r - i][c + i] += 1;
      }
      //adds 1 to all the spots in the path of the right of the queen
      for(int i = 1; c + i < board.length; i++){
        board[r][c + i] += 1;
      }
      return true;
    }
    return false;
  }

  private boolean removeQueen(int r, int c){
    //will only run if the params given don't go off the board and the place we are removing from is
    //already taken by a queen
    if((r>=0 || r < board.length) && (c>=0 || c < board.length) && board[r][c] == -1){
      //we make it a zero to denote an empty slot
      board[r][c] = 0;
      //subtracts 1 from all the spots in the path of down and to the right of the queen
      for(int i = 1; r + i < board.length && c + i < board.length; i++){
        board[r + i][c + i] -= 1;
      }
      //subtracts 1 from all the spots in the path of up and to the right of the queen
      for(int i = 1; c + i < board.length && r - i >= 0; i++){
        board[r - i][c + i] -= 1;
      }
      //subtracts 1 from all the spots in the path of the right of the queen
      for(int i = 1; c + i < board.length; i++){
        board[r][c + i] -= 1;
      }
      return true;
    }
    return false;
  }

  public String toString(){
    //start of with an empty string (answer string)
    String ans = "";
    //loops through the rows
    for (int r = 0; r < board.length; r++){
      //makes an empty string which will be the line being added to the answer string
      String line = "";
      //loops through the columns to add the value in this column from the row to the line
      for (int c = 0; c < board.length; c++){
        //if there is a -1 then make it a Q to denote queen
        if(board[r][c] == -1){
          line += "Q ";
        }else{
          //otherwise denote it as an underscore
          //we dont want to see anything else but the queens
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
    //if there is a value in the first column then the colution cannot be found
    if(findR(0)){
      throw new IllegalStateException("This board is not in a state to be solved");
    }else{
      //otherwise perform the helper method
      return solveHelper(0);
    }
  }

  private boolean solveHelper(int c){
    //if we pass the max index for the columns, then that means that there is a queen in every column,
    //which means we got N queens on a board of N X N
    if(c >=  board.length){
      return true;
    }
    //loop through all the r values in the current column
    for(int r = 0; r < board.length; r++){
      //if we can add it to this coordinate
      //then move on to the next column to see if we get to a solution if we place
      //a queen here. If not then remove the queen and try the coordinate one down
      if(addQueen(r, c)){
        if(solveHelper(c+1)){
          return true;
        }
      removeQueen(r,c);
      }
    }
    //if we haven't gotten to any solution after trying each row in the column then that means
    //we failed and instead returns false
    return false;
  }

  private boolean findR(int c){
    //loops through the first column to see if there is a value there
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
      throw new IllegalStateException("This board is not in a state to be solved");
    }else{
      return countSolutionsHelper(0, 0);
    }
  }
  private int countSolutionsHelper(int c, int num){
    //if we pass the max column index, then that means we found a solution and we must add to the number
    //num contains the amount of solutions
    if(c >=  board.length){
      num ++;
      //return num after adding 1 to it
      return num;
    }
    //loop through all the r values to see which one of the r values work
    //each that works is one more solution found
    for(int r = 0; r < board.length; r++){
      if(addQueen(r, c)){
        //if we are able to add the queen in this coordinate and we are able to increase our solution by 1
        //then make the current into the num that will become if we get a solution
        if(countSolutionsHelper(c+1, num) > num){
          num = countSolutionsHelper(c+1, num);
        }
        //remove it each time since we want to find all solutions and not just the first solution
        removeQueen(r,c);
      }
    }
    return num;
  }
}
