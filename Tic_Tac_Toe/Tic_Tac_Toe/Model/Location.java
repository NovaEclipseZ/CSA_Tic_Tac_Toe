package Model;

public class Location
{
    private Gameboard attached;
    private int y;
    private int x;
    private String state;
    private int turns;

    public Location(int yPos, int xPos, Gameboard g)
    {
        this.y = yPos;
        this.x = xPos;
        this.attached = g;
        this.state = "_";
        this.turns = 0;
    }

    public int getTurns()
    {
        return this.turns;
    }

    public void setTurns(int t)
    {
        this.turns = t;
    }

    public boolean isOccupied()
    {
        if(state.equals("_"))
        {
            return false;
        }
        return true;
    }

    public String getState()
    {
        return this.state;
    }

    public void changeState(String newState)
    {
        this.state = newState;
    }

    public Gameboard getAttachedGameboard()
    {
        return this.attached;
    }

    public int getXPos()
    {
        return this.x;
    }

    public int getYPos()
    {
        return this.y;
    }
}
