package Model;
import java.util.ArrayList;

public class AI extends GamePlayer
{
    private ArrayList<Location> possibleMoves;

    public AI()
    {
        super();
        possibleMoves = new ArrayList<Location>();
    }

    public Location getTurn(Gameboard g)
    {
        possibleMoves = new ArrayList<Location>();
        int temp = this.bestMove(g, 0);
        int index = 0;
        for(int i = 0; i < possibleMoves.size(); i++)
        {
            if(possibleMoves.get(i).getTurns() != -1 && possibleMoves.get(i).getTurns() < temp)
            {
                temp = possibleMoves.get(i).getTurns();
                index = 0;
            }
        }
        Location turn = new Location(possibleMoves.get(index).getXPos(), possibleMoves.get(index).getXPos(), g);
        return turn;
    }

    public int bestMove(Gameboard g, int count)
    {
        Gameboard cloned = g.copy();
        ArrayList<Location> validLoc = cloned.getValidLocations();
        if(validLoc.size() < 1)
        {
            return -1;
        }
        for(int i = 0; i < validLoc.size(); i++)
        {
            Location temp = validLoc.get(i);
            cloned.takeTurn(temp.getXPos(), temp.getYPos(), 1);
            count++;
            if(cloned.isWon())
            {
                temp.changeState("Won");
                temp.setTurns(count);
                possibleMoves.add(temp);
                return count;
            }
            Gameboard cloned2 = cloned.copy();
            ArrayList<Location> validLoc2 = cloned2.getValidLocations();
            if(validLoc2.size() < 1)
            {
                return -1;
            }
            for(int j = 0; j < validLoc2.size(); j++)
            {
                Location temp2 = validLoc2.get(j);
                cloned2.takeTurn(temp2.getXPos(), temp2.getYPos(), 0);
                if(cloned.isWon())
                {
                    temp.changeState("Lost");
                    possibleMoves.add(temp);
                    temp.setTurns(count);
                    return count;
                }
                int possibility = bestMove(cloned2, count);
                if(possibility == -1)
                {
                    temp.changeState("Tied");
                    temp.setTurns(count);
                    possibleMoves.add(temp);
                }
            }
        }
        return count;
    }
}