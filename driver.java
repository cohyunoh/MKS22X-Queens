public class driver{
  public static void main(String[] args) {
    QueenBoard board = new QueenBoard(8);
    System.out.println(board.solve());
    System.out.println(board);
    /*
    board.addQueen(3,4);
    board.addQueen(4,6);
    System.out.println(board);
    board.removeQueen(3,4);
    System.out.println(board);
    */
  }
}
