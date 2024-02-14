// Jack Thompson
// TTT with 2D arrays
// 2/14/24
// p6


/*

This project must play Tic-Tac-Toe.


For the first in-class demonstration, you must have the following:
1) a static constand 2-D Array the represnets the board and holds the STATE data for the game.
2) You must correctly resolve:
  horizontal, 
  diagonal, 
  vertical winner conditions
  And a draw
3) You must have 1 player.

For the future and more points above an A-...
0) You must correctly resolve all win and draw states
1) You should have a seond player
2) You should be able to play on a 4 x 4 board
3) You should correctly resolve turns
4) The player and the game should be in seperate classes from your static Main


For more in the future and more awesomer...
1) You can have a text file documenting player win-loss records and another stat of your choice2

2) You can have a 3rd or 4th player
3) You can have a 3rd dimension to the game
4) You can make a new class for a game session as well as a single game
5) You can make a new class for a turn, or a piece, or a screen painter


What I did:
I made it so it can acces a csv file with players storing wins losses and ties. 
You can chose between playing a 3by3 or 4by4 game
You can change players anytime.
You can See the players stats at anytime
*/

import java.util.Scanner;
import java.io.File;

public class Main {

  public static final int[][] STATE = new int[3][3];

  static boolean keepPlaying = true;

  static Scanner input = new Scanner(System.in);
  static File f = new File("src/main/java/data.csv");

  static FileAccessor accessor = new FileAccessor(f);

  static Game game;

  public static void main(String[] args) {
    runGame();
  }

  // Runs the game by starting it, displaying menu options, and ending the game.
  public static void runGame() {
    startGame();

    while (keepPlaying) {
      menuOptions();
    }

    endGame();
  }

  // Initializes the game by storing data, getting players, and creating a new
  // game instance.
  public static void startGame() {
    accessor.storeData();

    Player p1 = getPlayer(1, true);
    Player p2 = getPlayer(2, true);
    game = new Game(p1, p2);
  }

  // Ends the game by adding players to the data file and writing data.
  public static void endGame() {

    accessor.addPlayer(game.getPlayer1());
    accessor.addPlayer(game.getPlayer2());

    accessor.writeData();
  }

  // Displays menu options for the players and performs corresponding actions
  // based on player choice.
  public static void menuOptions() {
    int choice = -1;

    space();

    System.out.println("Player 1: " + game.getPlayer1().getName());
    System.out.println("Player 2: " + game.getPlayer2().getName());
    System.out.println();
    System.out.println("What would you like to do:");
    System.out.println("1) Play a 3 by 3 game");
    System.out.println("2) Play a 4 by 4 game");
    System.out.println("3) View current players stats");
    System.out.println("4) Change Player 1");
    System.out.println("5) Change Player 2");
    System.out.println("0) Quit");

    choice = input.nextInt();

    if (choice == 1) {
      game.play3by3(STATE);

    }

    if (choice == 2) {
      game.play4by4();
    }

    if (choice == 3) {
      space();
      System.out.println("Player 1:");
      game.getPlayer1().displayStats();
      System.out.println();
      System.out.println("Player 2:");
      game.getPlayer2().displayStats();
      System.out.println();
      System.out.println("1) Continue");
      input.next();
    }

    if (choice == 4) {
      Player player = getPlayer(1, false);
      if (player != null) {
        accessor.addPlayer(game.getPlayer1());
        game.changePlayer1(player);
      }
    }

    if (choice == 5) {
      Player player = getPlayer(2, false);
      if (player != null) {
        accessor.addPlayer(game.getPlayer2());
        game.changePlayer2(player);
      }
    }

    if (choice == 0) {
      keepPlaying = false;
    }
  }

  // Gets the player information based on whether it is a new or existing player
  // for the given whichPlayer.
  public static Player getPlayer(int whichPlayer, boolean start) {
    Player player = null;

    int choice = -1;

    while ((start == false && choice < 0) || (start && choice < 1) || choice > 2) {
      space();

      System.out.print("You need to make a ");
      if (whichPlayer == 1)
        System.out.println("first player:");
      else
        System.out.println("second player:");
      System.out.println("It is a new or existing player?");
      System.out.println("1) New");
      System.out.println("2) existing");
      if (start == false)
        System.out.println("0) Cancel");

      choice = input.nextInt();
    }

    if (choice == 0 && start == false)
      return null;

    space();

    System.out.println("What is your name?");
    String name = input.next();

    if (choice == 1) {
      player = getNewPlayer(name);
    } else if (choice == 2) {
      player = getExistingPlayer(name, start);
    }

    return player;

  }

  // Creates a new player with the given name and displays the UUID.
  public static Player getNewPlayer(String name) {
    space();
    Player player = new Player(name);
    System.out.println("Here is your UUID (REMEBER IT): " + player.getUUID());
    System.out.println("1) Continue");
    input.next();
    return player;
  }

  // Gets an existing player based on the given name and existing players data, or
  // prompts to create a new player.
  public static Player getExistingPlayer(String name, Boolean start) {
    Player player = null;

    if (accessor.checkNameExists(name)) {
      int choice = -1;
      Player[] playersWithName = accessor.getAllWithName(name);

      while ((start == false && choice < 0) || (start && choice < 1) || choice > accessor.getHowManyWithName(name)) {
        space();

        System.out.println("Which one is you");

        for (int i = 0; i < playersWithName.length; i++) {
          System.out
              .println((i + 1) + ") Name:" + playersWithName[i].getName() + " - UUID: " + playersWithName[i].getUUID());
        }
        if (start == false)
          System.out.println("0) Cancel");

        choice = input.nextInt();
      }

      if (choice == 0 && start)
        return null;

      player = playersWithName[choice - 1];
      accessor.removePlayer(player);

      space();

      System.out.println("Here is your UUID (REMEBER IT): " + player.getUUID());

      System.out.println("1) Continue");
      input.next();
    }

    else {
      int choice = -1;

      while ((start == false && choice < 0) || (start && choice < 1) || choice > 2) {
        space();

        System.out.println("There are no existing players with this name, what would you like to do?");
        System.out.println("1) Enter a different name");
        System.out.println("2) Create a new Player with this name");
        if (start == false)
          System.out.println("0) Cancel");

        choice = input.nextInt();
      }

      if (choice == 0 && start)
        return null;

      if (choice == 1) {
        space();

        System.out.println("What is your name?");
        name = input.next();
        player = getExistingPlayer(name, start);
      }
      if (choice == 2) {
        player = getNewPlayer(name);
      }
    }

    return player;
  }

  // Adds empty space for formatting purposes.
  public static void space() {
    for (int i = 0; i < 50; i++) {
      System.out.println();
    }
  }
}