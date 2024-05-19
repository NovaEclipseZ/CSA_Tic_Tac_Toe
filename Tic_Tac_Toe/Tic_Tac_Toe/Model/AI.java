package Model;
import java.util.ArrayList;

public class AI extends GamePlayer
{
    public AI()
    {
        super();
    }

    public Location getTurn(Gameboard g)
    {
        ArrayList<Location> toFormulate = this.bestMove(g, 0);
        Location turn = this.evaluate(toFormulate);
        return turn;
    }

    public ArrayList<Location> bestMove(Gameboard g, int executions)
    {
        if(g.getValidLocations().size() > 0 && executions < 3)
        {
            ArrayList<Location> possibleMoves = new ArrayList<Location>();
            for(int i = 0; i < g.getValidLocations().size(); i++)
            {
                Gameboard compCloned = g.copy();
                Gameboard playerCloned = g.copy();
                compCloned.takeTurn(g.getValidLocations().get(i).getRow(), g.getValidLocations().get(i).getCol(), 1);
                playerCloned.takeTurn(g.getValidLocations().get(i).getRow(), g.getValidLocations().get(i).getCol(), 0);
                if(compCloned.isWon())
                {
                    Location temp = new Location(g.getValidLocations().get(i).getRow(), g.getValidLocations().get(i).getCol(), g);
                    temp.setOptimization(2);
                    possibleMoves.add(temp);
                }
                else if(playerCloned.isWon())
                {
                    Location temp = new Location(g.getValidLocations().get(i).getRow(), g.getValidLocations().get(i).getCol(), g);
                    temp.setOptimization(1);
                    possibleMoves.add(temp);
                }
                else
                {
                    for(int j = 0; j < compCloned.getValidLocations().size(); j++)
                    {
                        Gameboard compCloned2 = compCloned.copy();
                        compCloned2.takeTurn(compCloned.getValidLocations().get(j).getRow(), compCloned.getValidLocations().get(j).getCol(), 2);
                        ArrayList<Location> toFormulate = this.bestMove(compCloned2, executions + 1);
                        if(toFormulate.size() >= 1)
                        {
                            Location temp = this.evaluate(toFormulate);
                            if(temp.getOptimization() == -1)
                            {
                                temp = new Location(g.getValidLocations().get(i).getRow(), g.getValidLocations().get(i).getCol(), g);
                                temp.setOptimization(0);
                            }
                            else
                            {
                                temp.setOptimization(0);
                            }
                            possibleMoves.add(temp);
                        }
                        else
                        {
                            Location temp = new Location(g.getValidLocations().get(i).getRow(), g.getValidLocations().get(i).getCol(), g);
                            temp.setOptimization(0);
                            possibleMoves.add(temp);
                        }
                    }
                    
                }
            }
            return possibleMoves;
        }
        else if(executions >= 3)
        {
            ArrayList<Location> possibleMoves = new ArrayList<Location>();
            Location temp = new Location(-1, -1, g);
            temp.setOptimization(-1);
            possibleMoves.add(temp);
            return possibleMoves;
        }
        else
        {
            ArrayList<Location> possibleMoves = new ArrayList<Location>();
            return possibleMoves;
        }
    }

    /**
     * Takes in a ArrayList of Locations and finds the most optimal Location out of passed parameter
     * @param toEvaluate
     * @return Location
     */
    public Location evaluate(ArrayList<Location> toEvaluate)
    {
        ArrayList<Location> possibleTurns = new ArrayList<Location>();
        for(Location eval : toEvaluate)
        {
            if(eval.getOptimization() == 2)
            {
                possibleTurns.add(eval);
            }
        }
        if(possibleTurns.size() >= 1)
        {
            return possibleTurns.get((int) (Math.random() * possibleTurns.size()));
        }
        for(Location eval : toEvaluate)
        {
            if(eval.getOptimization() == 1)
            {
                possibleTurns.add(eval);
            }
        }
        if(possibleTurns.size() >= 1)
        {
            return possibleTurns.get((int) (Math.random() * possibleTurns.size()));
        }
        for(Location eval : toEvaluate)
        {
            if(eval.getOptimization() == 0)
            {
                possibleTurns.add(eval);
            }
        }
        if(possibleTurns.size() >= 1)
        {
            return possibleTurns.get((int) (Math.random() * possibleTurns.size()));
        }
        return toEvaluate.get((int) (Math.random() * toEvaluate.size()));
    }
}