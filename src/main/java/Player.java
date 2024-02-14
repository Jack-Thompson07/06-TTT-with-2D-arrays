import java.util.UUID;

public class Player {

  private String name;
  private String uuid;

  private String[] wins;
  private String[] losses;
  private String[] ties;

  public Player(String[] data) {
    this.name = data[0];
    this.uuid = data[1];
    if (data[2].equals("null"))
      wins = new String[] { "null" };
    else
      this.wins = data[2].split(";");
    if (data[3].equals("null"))
      losses = new String[] { "null" };
    else
      this.losses = data[3].split(";");
    if (data[4].equals("null"))
      ties = new String[] { "null" };
    else
      this.ties = data[4].split(";");
  }

  public Player(String name) {
    this.name = name;
    this.uuid = UUID.randomUUID().toString();

    this.wins = new String[]{"null"};
    this.losses = new String[]{"null"};
    this.ties = new String[]{"null"};
  }

  public String getName() {
    return this.name;
  }

  public String getUUID() {
    return this.uuid;
  }

  public void addWin(String opponentUUID){
    if(this.wins[0].equals("null")) this.wins[0] = opponentUUID;

    else{
      String[] newWins = new String[wins.length + 1];

      for(int i = 0; i < wins.length; i ++){
        newWins[i] = wins[i];
      }
      newWins[newWins.length -1 ] = opponentUUID;

      this.wins = newWins;
    }
  }

  public void addLoss(String opponentUUID){
    if(this.losses[0].equals("null")) this.losses[0] = opponentUUID;

    else{
      String[] newLosses = new String[losses.length + 1];

      for(int i = 0; i < losses.length; i ++){
        newLosses[i] = losses[i];
      }
      newLosses[newLosses.length -1 ] = opponentUUID;

      this.losses = newLosses;
    }
  }

  public void addTie(String opponentUUID){
    if(this.ties[0].equals("null")) this.ties[0] = opponentUUID;

    else{
      String[] newTies = new String[ties.length + 1];

      for(int i = 0; i < ties.length; i ++){
        newTies[i] = ties[i];
      }
      newTies[newTies.length -1 ] = opponentUUID;

      this.ties = newTies;
    }
  }

  public String getPlayerStats() {
    String allWins = "";
    String allLosses = "";
    String allTies = "";

    for (int i = 0; i < this.wins.length; i++) {
      allWins += this.wins[i];

      if (i < wins.length - 1)
        allWins += ";";
    }
    for (int i = 0; i < this.losses.length; i++) {
      allLosses += this.losses[i];

      if (i < losses.length - 1)
        allLosses += ";";
    }
    for (int i = 0; i < this.ties.length; i++) {
      allTies += this.ties[i];

      if (i < ties.length - 1)
        allTies += ";";
    }

    return this.name + "," + this.uuid + "," + allWins + "," + allLosses + "," + allTies;
  }

}