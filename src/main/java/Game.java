import java.util.Scanner;

public class Game {

  private Player p1;
  private Player p2;

  private Scanner s = new Scanner(System.in);

  private WinCheck wCheck;


    public Game(Player p1, Player p2) {
    this.p1 = p1;
    this.p2 = p2;
  }

  public void play3by3() {
    int[][] board = {{0,0,0},{0,0,0},{0,0,0}};
    
    playGame(board);
  }

  public void play4by4(){
    int[][] board = {{0,0,0,0},{0,0,0,0},{0,0,0,0},{0,0,0,0}};

    playGame(board);
  }

  private void playGame(int[][] board){
    this.wCheck = new WinCheck();
    int turn = 1;

    while(wCheck.getWin(board) == 0){
      drawBoard(board);

      board = getInput(board,turn,board.length);

      if(turn == 1) turn = 2;
      else turn = 1;
    }

    if(wCheck.getWin(board) == 1){
      p1.addWin(p2.getUUID());
      p2.addLoss(p1.getUUID());

      System.out.println("Player 1 - " + p1.getName() + " wins!");
      System.out.println(p1.getPlayerStats());
    }
      

    else if(wCheck.getWin(board) == 2){
      p2.addWin(p1.getUUID());
      p1.addLoss(p2.getUUID());

      System.out.println("Player 2 - " + p2.getName() + " wins!");
    }
      

    else{
      p1.addTie(p2.getUUID());
      p2.addTie(p1.getUUID());

      System.out.println("Its a tie!");
    }

    System.out.println("1) Continue");

    s.next();
  }

  private void drawBoard(int[][] board){
    System.out.println("Board:");
    System.out.print("-  ");
    for(int i = 0; i < board.length; i ++){
      System.out.print(" " + (i + 1));
    }
    System.out.println();
    System.out.println();
    for(int i = 0; i < board.length; i ++){
      System.out.print(i + 1 + "  ");
      for(int j : board[i]){
        if(j == 0) System.out.print(" -");
        else System.out.print(" " + j);
      }
      System.out.println();
    }
  }

  private int[][] getInput(int[][] board, int player, int max){
    String choice;
    int x = 0;
    int y = 0;
    boolean good = false;

    while(good == false){
      System.out.print("Player " + player + " - ");
      if(player == 1) System.out.print(this.p1.getName());
      else System.out.print(this.p2.getName());

      System.out.println(": Where do you want to go (ex: 1-1):");

      choice = s.next();

      y = Integer.parseInt(choice.substring(0,1));
      x = Integer.parseInt(choice.substring(2, 3));

      if(x > 0 && y > 0 && x <= max && y <= max){
        if(board[y-1][x-1] == 0){
          good = true;
        }
      }
    }

    board[y-1][x-1] = player;

    return board;
  }

  public Player getPlayer1(){
    return this.p1;
  }

  public Player getPlayer2(){
    return this.p2;
  }

  public void changePlayer1(Player p){
    this.p1 = p;
  }

  public void changePlayer2(Player p){
    this.p2 = p;
  }
}