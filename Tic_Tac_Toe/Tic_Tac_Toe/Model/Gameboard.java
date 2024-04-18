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

    public void takeTurn(int r, int c, GamePlayer p)
    {
        if(this.validInput(r, c))
        {
            board[r][c] = "x";
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