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
1) You can have a text file documenting player win-loss records and another stat of your choice
2) You can have a 3rd or 4th player
3) You can have a 3rd dimension to the game
4) You can make a new class for a game session as well as a single game
5) You can make a new class for a turn, or a piece, or a screen painter
*/

import java.util.Scanner;
import java.io.File;

public class Main {


  static boolean keepPlaying = true;

  static Scanner input = new Scanner(System.in);
  static File f = new File("src/main/java/data.csv");

  static FileAccessor accessor = new FileAccessor(f);

  static Game game;

  public static void main(String[] args) {
    runGame();  
  }




  public static void runGame(){
    startGame();

    while(keepPlaying){
      menuOptions();
    }

    endGame();
  }

  public static void startGame(){
    accessor.storeData();

    Player p1 = getPlayer(1, true);
    Player p2 = getPlayer(2, true);
    game = new Game(p1, p2);
  }

  public static void endGame(){

    accessor.addPlayer(game.getPlayer1());
    accessor.addPlayer(game.getPlayer2());

    accessor.writeData();
  }

  public static void menuOptions(){
    int choice = -1;

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
    
    if(choice == 1){
      game.play3by3();

    }

    if(choice == 2){
      game.play4by4();
    }

    if(choice == 4){
      Player player = getPlayer(1, false);
      if(player != null) {
        accessor.addPlayer(game.getPlayer1());
        game.changePlayer1(player);
      }
    }

    if(choice == 5){
      Player player = getPlayer(2, false);
      if(player != null) {
        accessor.addPlayer(game.getPlayer2());
        game.changePlayer2(player);
      }
    }

    if(choice == 0){
      keepPlaying = false;
    }
  }

  public static Player getPlayer(int whichPlayer, boolean start){
    Player player = null;

    int choice = -1;

    System.out.print("You need to make a ");
    if(whichPlayer == 1) System.out.println("first player:");
    else System.out.println("second player:");
    System.out.println("It is a new or existing player?");
    System.out.println("1) New");
    System.out.println("2) existing");
    if(start == false) System.out.println("0) Cancel");

    choice = input.nextInt();
    if(choice == 0 && start == false) return null;

    String name;
    System.out.println("What is your name?");
    name = input.next();

    if(choice == 1){
      player = getNewPlayer(name);
    }
    else if(choice == 2){
      player = getExistingPlayer(name, start);
    }

    
    return player;

  }

  public static Player getNewPlayer(String name){
    return new Player(name);
  }

  public static Player getExistingPlayer(String name, Boolean start){
    Player player = null; 

    if(accessor.checkNameExists(name)){
      int choice = -1;
      Player[] playersWithName = accessor.getAllWithName(name);

      System.out.println("Which one is you");

      for(int i = 0; i < playersWithName.length; i ++){
        System.out.println((i + 1) + ") Name:" + playersWithName[i].getName() + " - UUID: " + playersWithName[i].getUUID());
      }  

      choice = input.nextInt();

      player = playersWithName[choice - 1];

      accessor.removePlayer(player);
    }

    else{
      int choice = -1;

      System.out.println("There are no existing players with this name, what would you like to do?");
      System.out.println("1) Enter a different name");
      System.out.println("2) Create a new Player with this name");
      if(start  == false) System.out.println("0) Cancel");

      choice = input.nextInt();


      if(choice == 0 && start) return null;


      if(choice == 1){
        System.out.println("What is your name?");
      name = input.next();
        player = getExistingPlayer(name, start);
      }
      if(choice == 2){
        player = getNewPlayer(name);
      }
    }


    return player;
  }
}