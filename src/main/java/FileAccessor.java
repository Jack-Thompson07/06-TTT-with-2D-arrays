import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.File;

public class FileAccessor{

  private File f;
  private Scanner s;
  private Player[] players;

  private String placeHolderData = "name,uuid,wins,losses,ties";
  
  public FileAccessor(File f){
    this.f = f;

    resetScanner();
  }

  public void resetScanner() {
    try {
      this.s = new Scanner(this.f);
    } catch (FileNotFoundException e) {
      System.out.println(e);
    }
  }

  public int getNumLines() {
    resetScanner();

    int count = 0;
    while (this.s.hasNextLine()) {
      count++;
      this.s.nextLine();
    }

    return count;
  }

  public void storeData() {
    this.players = new Player[getNumLines() - 1];

    resetScanner();
    s.nextLine();
    for (int i = 0; i < this.players.length; i++) {
      players[i] = new Player(s.nextLine().split(","));
    }
  }

  public boolean checkNameExists(String name){
    
    for(Player p: players){
      if(p.getName().equals(name)) return true;
    }
    return false;
  }

  public Player[] getAllWithName(String name){
    Player[] allWithName = new Player[getHowManyWithName(name)];
    int count = 0;

    for(Player p : players){
      if(p.getName().equals(name)){
        allWithName[count] = p;
        count ++;
      } 
    }

    return allWithName;
  }

  public int getHowManyWithName(String name){
    int count = 0;

    for(Player p: players){
      if(p.getName().equals(name)) count ++;
    }

    return count;
  }

  public void addPlayer(Player p){
    Player[] newPlayers = new Player[players.length + 1];

    for(int i = 0; i < players.length; i ++){
      newPlayers[i] = players[i];
    }
    newPlayers[newPlayers.length - 1] = p;

    this.players = newPlayers;
  }

  public void removePlayer(Player p){
    Player[] newPlayers = new Player[players.length - 1];
    int count = 0;

    for(int i = 0; i < newPlayers.length; i++){
      if(players[i - count] == p) count ++;
      else{
        newPlayers[i] = players[i-count];
      }
    }

    this.players = newPlayers;
  }

  public void writeData(){
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