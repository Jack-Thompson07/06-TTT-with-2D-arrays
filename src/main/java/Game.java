import java.util.Scanner;

public class Game {

  public int[][] STATE; 
  private Player p1;
  private Player p2;

  private Scanner s = new Scanner(System.in);

  private WinCheck wCheck;

  ////////////////////////////////
  // Constructor
  ////////////////////////////////
  public Game(Player p1, Player p2) {
    this.p1 = p1;
    this.p2 = p2;
  }

  ////////////////////////////////
  // Methods
  ////////////////////////////////

  // sets up a 3x3 Tic-Tac-Toe game.
  public void play3by3(int[][] state) {
    this.STATE = state;

    playGame();
  }

  // sets up a 4x4 Tic-Tac-Toe game.
  public void play4by4() {
    this.STATE = new int[][]{ { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 } };

    playGame();
  }

   // Manages the gameplay of Tic-Tac-Toe, including player turns, checking for wins, and updating the game state.
  private void playGame() {
    this.wCheck = new WinCheck();
    int turn = 1;

    while (wCheck.getWin(this.STATE) == 0) {

      this.STATE = getInput(turn, this.STATE.length);

      if (turn == 1)
        turn = 2;
      else
        turn = 1;
    }

    space();

    if (wCheck.getWin(this.STATE) == 1) {
      p1.addWin(p2.getUUID());
      p2.addLoss(p1.getUUID());

      System.out.println("Player 1 - " + p1.getName() + " wins!");
    }

    else if (wCheck.getWin(this.STATE) == 2) {
      p2.addWin(p1.getUUID());
      p1.addLoss(p2.getUUID());

      System.out.println("Player 2 - " + p2.getName() + " wins!");
    }

    else {
      p1.addTie(p2.getUUID());
      p2.addTie(p1.getUUID());

      System.out.println("Its a tie!");
    }

    System.out.println("1) Continue");

    s.next();
  }

  //displays the board to the user
  private void drawBoard() {
    System.out.println("Board:");
    System.out.print("-  ");
    for (int i = 0; i < this.STATE.length; i++) {
      System.out.print(" " + (i + 1));
    }
    System.out.println();
    System.out.println();
    for (int i = 0; i < this.STATE.length; i++) {
      System.out.print(i + 1 + "  ");
      for (int j : this.STATE[i]) {
        if (j == 0)
          System.out.print(" -");
        else
          System.out.print(" " + j);
      }
      System.out.println();
    }
  }

  // gets the input of the player on where they want to do their turn. Will repeat untul it is a valid place
  private int[][] getInput(int player, int max) {
    String choice;
    int x = 0;
    int y = 0;
    boolean good = false;

    while (good == false) {
      space();

      drawBoard();

      System.out.print("Player " + player + " - ");
      if (player == 1)
        System.out.print(this.p1.getName());
      else
        System.out.print(this.p2.getName());

      System.out.println(": Where do you want to go (write as: row-column):");

      choice = s.next();

      y = Integer.parseInt(choice.substring(0, 1));
      x = Integer.parseInt(choice.substring(2, 3));

      if (x > 0 && y > 0 && x <= max && y <= max) {
        if (this.STATE[y - 1][x - 1] == 0) {
          good = true;
        }
      }
    }

    this.STATE[y - 1][x - 1] = player;

    return this.STATE;
  }

  //returns p1
  public Player getPlayer1() {
    return this.p1;
  }

  //returns p2
  public Player getPlayer2() {
    return this.p2;
  }

  //sets the currnet p1 to a new player
  public void changePlayer1(Player p) {
    this.p1 = p;
  }

  //sets the currnet p2 to a new player
  public void changePlayer2(Player p) {
    this.p2 = p;
  }

  // Adds empty space for formatting purposes.
  public void space() {
    for (int i = 0; i < 50; i++) {
      System.out.println();
    }
  }
}
