package Model;

public class GamePlayer
{
    private String name;
    private int wins;

    public GamePlayer()
    {
        this.name = "Computer";
        this.wins = 0;
    }

    public GamePlayer(String name)
    {
        this.name = name;
        this.wins = 0;
    }

    public String getName()
    {
        return this.name;
    }

    public int getWins()
    {
        return this.wins;
    }

    public void addWin()
    {
        this.wins++;
    }
}