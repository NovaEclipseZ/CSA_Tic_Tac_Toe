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
        System.out.println(toFormulate.size());
        Location turn = this.evaluate(toFormulate);
        System.out.println(turn.getYPos() + " " + turn.getXPos());
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
                compCloned.takeTurn(g.getValidLocations().get(i).getXPos(), g.getValidLocations().get(i).getYPos(), 1);
                playerCloned.takeTurn(g.getValidLocations().get(i).getXPos(), g.getValidLocations().get(i).getYPos(), 0);
                if(compCloned.isWon())
                {
                    Location temp = new Location(g.getValidLocations().get(i).getYPos(), g.getValidLocations().get(i).getXPos(), g);
                    temp.setOptimization(2);
                    possibleMoves.add(temp);
                }
                else if(playerCloned.isWon())
                {
                    Location temp = new Location(g.getValidLocations().get(i).getYPos(), g.getValidLocations().get(i).getXPos(), g);
                    temp.setOptimization(1);
                    possibleMoves.add(temp);
                }
                else
                {
                    for(int j = 0; j < compCloned.getValidLocations().size(); j++)
                    {
                        Gameboard compCloned2 = compCloned.copy();
                        compCloned2.takeTurn(compCloned.getValidLocations().get(j).getYPos(), compCloned.getValidLocations().get(j).getXPos(), 2);
                        ArrayList<Location> toFormulate = this.bestMove(compCloned2, executions + 1);
                        if(toFormulate.size() >= 1)
                        {
                            Location temp = this.evaluate(toFormulate);
                            if(temp.getOptimization() == 2)
                            {
                                temp = new Location(g.getValidLocations().get(i).getYPos(), g.getValidLocations().get(i).getXPos(), g);
                                temp.setOptimization(4);
                            }
                            else if(temp.getOptimization() == 1)
                            {
                                temp = new Location(g.getValidLocations().get(i).getYPos(), g.getValidLocations().get(i).getXPos(), g);
                                temp.setOptimization(3);
                            }
                            else if(temp.getOptimization() == -1)
                            {
                                temp = new Location(g.getValidLocations().get(i).getYPos(), g.getValidLocations().get(i).getXPos(), g);
                                temp.setOptimization(-1);
                            }
                            possibleMoves.add(temp);
                        }
                        else
                        {
                            Location temp = new Location(g.getValidLocations().get(i).getYPos(), g.getValidLocations().get(i).getXPos(), g);
                            temp.setOptimization(0);
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

    public Location evaluate(ArrayList<Location> toEvaluate)
    {
        int i = 0;
        int indexOfBest = 0;
        int count = 0;
        while(i < toEvaluate.size() && count == 0)
        {
            System.out.println(toEvaluate.get(i).getOptimization());
            if(toEvaluate.get(i).getOptimization() > 1);
            {
                indexOfBest = i;
                count++;
                System.out.println(count);
            }
            i++;
        }
        if(count == 0)
        {
            i = 0;
            System.out.println("optimization 1");
            while(i < toEvaluate.size() && count == 0)
            {
                if(toEvaluate.get(i).getOptimization() > 0);
                {
                    indexOfBest = i;
                    count++;
                }
                i++;
            }
        }
        if(count == 0)
        {
            i = 0;
            System.out.println("optimization 4");
            while(i < toEvaluate.size() && count == 0)
            {
                if(toEvaluate.get(i).getOptimization() > 3);
                {
                    indexOfBest = i;
                    count++;
                }
                i++;
            }
        }
        if(count == 0)
        {
            i = 0;
            System.out.println("optimization 3");
            while(i < toEvaluate.size() && count == 0)
            {
                if(toEvaluate.get(i).getOptimization() > 2);
                {
                    indexOfBest = i;
                    count++;
                }
                i++;
            }
        }
        if(count == 0)
        {
            i = 0;
            while(i < toEvaluate.size() && count == 0)
            {
                if(toEvaluate.get(i).getOptimization() > -1);
                {
                    indexOfBest = i;
                    count++;
                }
                i++;
            }
        }
        return toEvaluate.get(indexOfBest);
    }
}