public class driver{
  public static void main(String[] args) {
    try{
      QueenBoard board = new QueenBoard(4);
      //System.out.println(board.getLength());
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
