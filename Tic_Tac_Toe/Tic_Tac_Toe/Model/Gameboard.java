package Model;
import java.util.ArrayList;

public class Gameboard
{
    private Location[][] board;

    public Gameboard()
    {
        board = new Location[3][3];
        for(int i = 0; i < board.length; i++)
        {
            for(int j = 0; j < board[i].length; j++)
            {
                board[i][j] = new Location(i, j, this);
            }
        }
    }

    public Location[][] getBoard()
    {
        return this.board;
    }

    public boolean takeTurn(int r, int c, int t)
    {
        if(!(this.board[r][c].isOccupied()) && t % 2 == 0)
        {
            this.board[r][c].changeState("O");
            return true;
        }
        else if(!(this.board[r][c].isOccupied()) && t % 2 == 1)
        {
            this.board[r][c].changeState("X");
            return true;
        }
        return false;
    }

    public boolean isWon()
    {
        if(this.board[0][0].getState().equals("X") && this.board[1][0].getState().equals("X") && this.board[2][0].getState().equals("X"))
        {
            return true;
        }
        else if(this.board[0][0].getState().equals("O") && this.board[1][0].getState().equals("O") && this.board[2][0].getState().equals("O"))
        {
            return true;
        }
        else if(this.board[0][1].getState().equals("X") && this.board[1][1].getState().equals("X") && this.board[2][1].getState().equals("X"))
        {
            return true;
        }
        else if(this.board[0][1].getState().equals("O") && this.board[1][1].getState().equals("O") && this.board[2][1].getState().equals("O"))
        {
            return true;
        }
        else if(this.board[0][2].getState().equals("X") && this.board[1][2].getState().equals("X") && this.board[2][2].getState().equals("X"))
        {
            return true;
        }
        else if(this.board[0][2].getState().equals("O") && this.board[1][2].getState().equals("O") && this.board[2][2].getState().equals("O"))
        {
            return true;
        }
        else if(this.board[0][0].getState().equals("X") && this.board[0][1].getState().equals("X") && this.board[0][2].getState().equals("X"))
        {
            return true;
        }
        else if(this.board[0][0].getState().equals("O") && this.board[0][1].getState().equals("O") && this.board[0][2].getState().equals("O"))
        {
            return true;
        }
        else if(this.board[1][0].getState().equals("X") && this.board[1][1].getState().equals("X") && this.board[1][2].getState().equals("X"))
        {
            return true;
        }
        else if(this.board[1][0].getState().equals("O") && this.board[1][1].getState().equals("O") && this.board[1][2].getState().equals("O"))
        {
            return true;
        }
        else if(this.board[2][0].getState().equals("X") && this.board[2][1].getState().equals("X") && this.board[2][2].getState().equals("X"))
        {
            return true;
        }
        else if(this.board[2][0].getState().equals("O") && this.board[2][1].getState().equals("O") && this.board[2][2].getState().equals("O"))
        {
            return true;
        }
        else if(this.board[0][0].getState().equals("X") && this.board[1][1].getState().equals("X") && this.board[2][2].getState().equals("X"))
        {
            return true;
        }
        else if(this.board[0][0].getState().equals("O") && this.board[1][1].getState().equals("O") && this.board[2][2].getState().equals("O"))
        {
            return true;
        }
        else if(this.board[0][2].getState().equals("X") && this.board[1][1].getState().equals("X") && this.board[2][0].getState().equals("X"))
        {
            return true;
        }
        else if(this.board[0][2].getState().equals("O") && this.board[1][1].getState().equals("O") && this.board[2][0].getState().equals("O"))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public ArrayList<Location> getOccupiedLocations()
    {
        ArrayList<Location> temp = new ArrayList<Location>();
        for(int i = 0; i < this.board.length; i++)
        {
            for(int j = 0; j < this.board[i].length; j++)
            {
                if(board[i][j].isOccupied())
                {
                    temp.add(board[i][j]);
                }
            }
        }
        return temp;
    }

    public ArrayList<Location> getValidLocations()
    {
        ArrayList<Location> temp = new ArrayList<Location>();
        for(int i = 0; i < this.board.length; i++)
        {
            for(int j = 0; j < this.board[i].length; j++)
            {
                if(!board[i][j].isOccupied())
                {
                    temp.add(board[i][j]);
                }
            }
        }
        return temp;
    }

    public Gameboard copy()
    {
        Gameboard copied = new Gameboard();
        ArrayList<Location> toCopy = this.getOccupiedLocations();
        for(int i = 0; i < toCopy.size(); i++)
        {
            if(toCopy.get(i).getState().equals("X"))
            {
                copied.takeTurn(toCopy.get(i).getYPos(),toCopy.get(i).getXPos(), 1);
            }
            else
            {
                copied.takeTurn(toCopy.get(i).getYPos(), toCopy.get(i).getXPos(), 0);
            }
        }
        return copied;
    }

    public String toString()
    {
        String temp = "";
        for(Location[] locArr : board)
        {
            for(Location loc : locArr)
            {
                temp += loc.getState() + "\t";
            }
            temp += "\n";
        }
        return temp;
    }
}