import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.File;

public class FileAccessor {

  private File f;
  private Scanner s;
  private Player[] players;

  private String placeHolderData = "name,uuid,wins,losses,ties";

  ////////////////////////////////
  // Constructor
  ////////////////////////////////
  public FileAccessor(File f) {
    this.f = f;

    resetScanner();
  }

  ////////////////////////////////
  // Methods
  ////////////////////////////////

  // Resets the scanner to read from the beginning of the file.
  public void resetScanner() {
    try {
      this.s = new Scanner(this.f);
    } catch (FileNotFoundException e) {
      System.out.println(e);
    }
  }

  // Calculates the number of lines in the file. return The number of lines in the
  // file.
  public int getNumLines() {
    resetScanner();

    int count = 0;
    while (this.s.hasNextLine()) {
      count++;
      this.s.nextLine();
    }

    return count;
  }

  // Reads data from the file and stores it in Player objects array.
  public void storeData() {
    this.players = new Player[getNumLines() - 1];

    resetScanner();
    s.nextLine();
    for (int i = 0; i < this.players.length; i++) {
      players[i] = new Player(s.nextLine().split(","));
    }
  }

  // Checks if a player with the given name exists in the stored data. param name
  // The name of the player to check. return True if the player exists, false
  // otherwise.
  public boolean checkNameExists(String name) {

    for (Player p : players) {
      if (p.getName().equals(name))
        return true;
    }
    return false;
  }

  // Retrieves all players with the given name from the stored data. param name
  // The name of the players to retrieve. return An array of Player objects with
  // the given name.
  public Player[] getAllWithName(String name) {
    Player[] allWithName = new Player[getHowManyWithName(name)];
    int count = 0;

    for (Player p : players) {
      if (p.getName().equals(name)) {
        allWithName[count] = p;
        count++;
      }
    }

    return allWithName;
  }

  // Retrieves a player with the given UUID from the stored data. Param uuid The
  // UUID of the player to retrieve. Return The Player object with the given UUID.
  public Player getWithUUID(String uuid) {
    Player player = null;
    int count = 0;

    for (Player p : players) {
      if (p.getName().equals(uuid)) {
        player = p;
        count++;
      }
    }

    return player;
  }

  // returns how many players there are with the name
  public int getHowManyWithName(String name) {
    int count = 0;

    for (Player p : players) {
      if (p.getName().equals(name))
        count++;
    }

    return count;
  }

  // adds the Player in the parameter to the list of players
  public void addPlayer(Player p) {
    Player[] newPlayers = new Player[players.length + 1];

    for (int i = 0; i < players.length; i++) {
      newPlayers[i] = players[i];
    }
    newPlayers[newPlayers.length - 1] = p;

    this.players = newPlayers;
  }

  // removes the player in the parameter from the list of players
  public void removePlayer(Player p) {
    Player[] newPlayers = new Player[this.players.length - 1];
    int count = 0;
    for (int i = 0; i < this.players.length; i++) {
      if (this.players[i] == p) {
        count++;
      } else {
        newPlayers[i - count] = this.players[i];
      }
    }
    this.players = newPlayers;
  }

  // writes all of the players in the player string into the csv file
  public void writeData() {
    try {

      FileWriter fw = new FileWriter(this.f);
      fw.write("name,uuid,wins,losses,ties" + "\n");

      for (int i = 0; i < this.players.length; i++) {
        fw.write(this.players[i].getPlayerStats() + "\n");
      }

      fw.close();

    } catch (Exception e) {
      System.out.println(e);
    }
  }
}