package Model;

public class Gameboard
{
    private String[][] board;

    public Gameboard()
    {
        board = new String[3][3];
        for(int i = 0; i < board.length; i++)
        {
            for(int j = 0; j < board[i].length; j++)
            {
                board[i][j] = " ";
            }
        }
    }

    public void takeTurn(int r, int c, int t)
    {
        if(this.validInput(r, c) && t % 2 == 0)
        {
            board[r][c] = "0";
        }
        else if(this.validInput(r, c) && t % 2 == 1)
        {
            board[r][c] = "X";
        }
    }

    public boolean validInput(int r, int c)
    {
        if(board[r][c].equals(" "))
        {
            return true;
        }
        return false;
    }

    public String toString()
    {
        String temp = "";
        for(String[] strArr : board)
        {
            for(String str : strArr)
            {
                temp += str + "\t";
            }
            temp += "\n";
        }
        return temp;
    }
}