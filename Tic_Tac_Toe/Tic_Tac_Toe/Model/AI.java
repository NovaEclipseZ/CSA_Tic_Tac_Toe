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
        ArrayList<Location> possibleMoves = new ArrayList<Location>();
        int i = 0;
        while(i < toFormulate.size())
        {
            if(toFormulate.get(i).getOptimization() == 2);
            {
                Location temp = new Location(toFormulate.get(i).getXPos(), toFormulate.get(i).getXPos(), g);
                possibleMoves.add(temp);
            }
            i++;
        }
        if(possibleMoves.size() == 0)
        {
            i = 0;
            while(i < toFormulate.size())
            {
                if(toFormulate.get(i).getOptimization() == 1);
                {
                    Location temp = new Location(toFormulate.get(i).getXPos(), toFormulate.get(i).getXPos(), g);
                    possibleMoves.add(temp);
                }
                i++;
            }
        }
        if(possibleMoves.size() == 0)
        {
            i = 0;
            while(i < toFormulate.size())
            {
                if(toFormulate.get(i).getOptimization() == -1);
                {
                    Location temp = new Location(toFormulate.get(i).getXPos(), toFormulate.get(i).getXPos(), g);
                    possibleMoves.add(temp);
                }
                i++;
            }
        }
        if(possibleMoves.size() == 0)
        {
            i = 0;
            while(i < toFormulate.size())
            {
                if(toFormulate.get(i).getOptimization() == 0);
                {
                    Location temp = new Location(toFormulate.get(i).getXPos(), toFormulate.get(i).getXPos(), g);
                    possibleMoves.add(temp);
                }
                i++;
            }
        }
        Location turn;
        if(possibleMoves.size() > 1)
        {
            turn = possibleMoves.get((int) Math.random() * possibleMoves.size());
        }
        else
        {
            turn = possibleMoves.get(0);
        }
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
                        compCloned2.takeTurn(compCloned2.getValidLocations().get(j).getYPos(), compCloned2.getValidLocations().get(j).getXPos(), 2);
                        ArrayList<Location> toFormulate = this.bestMove(compCloned2, executions + 1);
                        int count = 0;
                        int k = 0;
                        while(k < toFormulate.size() && count == 0)
                        {
                            if(toFormulate.get(k).getOptimization() == 2);
                            {
                                Location temp = new Location(toFormulate.get(k).getYPos(), toFormulate.get(k).getXPos(), g);
                                temp.setOptimization(0);
                                count++;
                                possibleMoves.add(temp);
                            }
                            k++;
                        }
                        if(count == 0)
                        {
                            k = 0;
                            while(k < toFormulate.size() && count == 0)
                            {
                                if(toFormulate.get(k).getOptimization() == 1);
                                {
                                    Location temp = new Location(compCloned.getValidLocations().get(i).getYPos(), compCloned.getValidLocations().get(i).getXPos(), g);
                                    temp.setOptimization(0);
                                    count++;
                                    possibleMoves.add(temp);
                                }
                                k++;
                            }
                        }
                        if(count == 0)
                        {
                            k = 0;
                            while(k < toFormulate.size() && count == 0)
                            {
                                if(toFormulate.get(k).getOptimization() == -1);
                                {
                                    Location temp = new Location(compCloned.getValidLocations().get(i).getYPos(), compCloned.getValidLocations().get(i).getXPos(), g);
                                    temp.setOptimization(0);
                                    count++;
                                    possibleMoves.add(temp);
                                }
                                k++;
                            }
                        }
                        if(count == 0)
                        {
                            k = 0;
                            while(j < toFormulate.size() && count == 0)
                            {
                                if(toFormulate.get(k).getOptimization() == 0);
                                {
                                    Location temp = new Location(compCloned.getValidLocations().get(i).getYPos(), compCloned.getValidLocations().get(i).getXPos(), g);
                                    temp.setOptimization(0);
                                    count++;
                                    possibleMoves.add(temp);
                                }
                                k++;
                            }
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
            temp.setOptimization(0);
            possibleMoves.add(temp);
            return possibleMoves;
        }
        else
        {
            ArrayList<Location> possibleMoves = new ArrayList<Location>();
            return possibleMoves;
        }
    }
}