import java.util.UUID;

public class Player{

    private String name;
    private String uuid;

    private String[] wins;
    private String[] losses;
    private String[] ties;



    public Player(String[] data){
        this.name = data[0];
        this.uuid = data[1];
        this.wins = data[2].split(";");
        this.losses = data[3].split(";");
        this.ties = data[4].split(";");
    }

    public Player(String name){
        this.name = name;
        this.uuid = UUID.randomUUID().toString();

        this.wins = new String[0];
        this.losses = new String[0];
        this.ties = new String[0];
    }

    public String getName(){
        return this.name;
    }

    public String getUUID(){
        return this.uuid;
    }

    public String getPlayerStats(){
        String allWins = "";
        String allLosses = "";
        String allTies = "";

        for(int i = 0; i < this.wins.length; i ++){
            allWins += this.wins[i];

            if(i < wins.length - 1) allWins += ";";
        }
        for(int i = 0; i < this.wins.length; i ++){
            allLosses += this.wins[i];

            if(i < losses.length - 1) allLosses += ";";
        }
        for(int i = 0; i < this.wins.length; i ++){
            allTies += this.wins[i];

            if(i < ties.length - 1) allTies += ";";
        }

        return this.name + "," + this.uuid + "," + allWins + "," + allLosses + "," + allTies;
    }

}