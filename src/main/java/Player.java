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

    this.wins = new String[] { "null" };
    this.losses = new String[] { "null" };
    this.ties = new String[] { "null" };
  }

  public String getName() {
    return this.name;
  }

  public String getUUID() {
    return this.uuid;
  }

  public void addWin(String opponentUUID) {
    if (this.wins[0].equals("null"))
      this.wins[0] = opponentUUID;

    else {
      String[] newWins = new String[wins.length + 1];

      for (int i = 0; i < wins.length; i++) {
        newWins[i] = wins[i];
      }
      newWins[newWins.length - 1] = opponentUUID;

      this.wins = newWins;
    }
  }

  public void addLoss(String opponentUUID) {
    if (this.losses[0].equals("null"))
      this.losses[0] = opponentUUID;

    else {
      String[] newLosses = new String[losses.length + 1];

      for (int i = 0; i < losses.length; i++) {
        newLosses[i] = losses[i];
      }
      newLosses[newLosses.length - 1] = opponentUUID;

      this.losses = newLosses;
    }
  }

  public void addTie(String opponentUUID) {
    if (this.ties[0].equals("null"))
      this.ties[0] = opponentUUID;

    else {
      String[] newTies = new String[ties.length + 1];

      for (int i = 0; i < ties.length; i++) {
        newTies[i] = ties[i];
      }
      newTies[newTies.length - 1] = opponentUUID;

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

  public void displayStats() {
    int totalWins = 0;
    int totalLosses = 0;
    int totalTies = 0;
    Player p;

    if (this.wins[0].equals("null") == false) {
      totalWins = this.wins.length;
    }
    if (this.losses[0].equals("null") == false) {
      totalLosses = this.losses.length;
    }
    if (this.ties[0].equals("null") == false) {
      totalTies = this.ties.length;
    }

    System.out.println();
    System.out.println("Name: " + this.name);
    System.out.println("UUID: " + this.uuid);
    System.out.println();
    System.out.println("Total wins: " + totalWins);

    System.out.println("Wins agains:");
    if (totalWins > 0) {
      for (String i : this.wins) {
        p = Main.accessor.getWithUUID(i);

        if (p == null) {
          if (Main.game.getPlayer1() == this) {
            p = Main.game.getPlayer2();
          } else {
            p = Main.game.getPlayer1();
          }
        }

        System.out.println("  " + p.getName() + " - " + p.getUUID());
      }
    }
    System.out.println();
    System.out.println("Total losses: " + totalLosses);

    System.out.println("Losses agains:");
    if (totalLosses > 0) {
      for (String i : this.losses) {
        p = Main.accessor.getWithUUID(i);

        if (p == null) {
          if (Main.game.getPlayer1() == this) {
            p = Main.game.getPlayer2();
          } else {
            p = Main.game.getPlayer1();
          }
        }

        System.out.println("  " + p.getName() + " - " + p.getUUID());
      }
    }
    System.out.println();
    System.out.println("Tototal ties" + totalTies);

    System.out.println("Ties agains:");
    if (totalTies > 0) {
      for (String i : this.ties) {
        p = Main.accessor.getWithUUID(i);

        if (p == null) {
          if (Main.game.getPlayer1() == this) {
            p = Main.game.getPlayer2();
          } else {
            p = Main.game.getPlayer1();
          }
        }

        System.out.println("  " + p.getName() + " - " + p.getUUID());
      }
    }
    System.out.println();
    System.out.println();
  }
}