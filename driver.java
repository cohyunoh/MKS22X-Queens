public class driver{
  public static void main(String[] args) {
    try{
      QueenBoard board = new QueenBoard(7);
      System.out.println(board.solve());
      System.out.println(board);
      /*
      System.out.println(board.solveHelper(0,0));
      System.out.println(board);
      board.clear();
      System.out.println(board.solveHelper(1,0));
      System.out.println(board);
      board.clear();
      System.out.println(board.solveHelper(2,0));
      System.out.println(board);
      board.clear();
      System.out.println(board.solveHelper(3,0));
      System.out.println(board);
      board.clear();
      System.out.println(board.solveHelper(4,0));
      System.out.println(board);
      board.clear();
      System.out.println(board.solveHelper(5,0));
      System.out.println(board);
      board.clear();
      System.out.println(board.solveHelper(6,0));
      System.out.println(board);
      board.clear();
      */
      //System.out.println(board.getLength());
      /*
      System.out.println(board.solve());
      System.out.println(board);
      board.rotateBoard();
      System.out.println(board);
      board.rotateBoard();
      System.out.println(board);
      board.rotateBoard();
      System.out.println(board);
      board.rotateBoard();
      System.out.println(board);
      board.flip(0);
      System.out.println(board);
      board.flip(0);
      System.out.println(board);
      board.flip(1);
      System.out.println(board);
      board.flip(1);
      System.out.println(board);
      System.out.println(board.countSolutions());
      System.out.println(board);
      */
    }catch(IllegalStateException e){
      System.out.println("Oh well");
    }

    /*
    board.addQueen(3,4);
    board.addQueen(4,6);
    System.out.println(board);
    board.removeQueen(3,4);
    System.out.println(board);
    */
  }
}
