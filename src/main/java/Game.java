public class Game {

  private Player p1;
  private Player p2;

  public Game(Player p1, Player p2) {
    this.p1 = p1;
    this.p2 = p2;
  }

  public void playGame() {

  }

  public Player getPlayer1(){
    return this.p1;
  }

  public Player getPlayer2(){
    return this.p2;
  }
}