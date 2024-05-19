package Model;

public class Location
{
    private Gameboard attached;
    private int row;
    private int col;
    private String state;
    private int optimization;

    public Location(int rowPos, int colPos, Gameboard g)
    {
        this.row = rowPos;
        this.col = colPos;
        this.attached = g;
        this.state = "_";
        this.optimization = 0;
    }

    public int getOptimization()
    {
        return this.optimization;
    }

    public void setOptimization(int o)
    {
        this.optimization = o;
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

    public int getRow()
    {
        return this.row;
    }

    public int getCol()
    {
        return this.col;
    }
}
