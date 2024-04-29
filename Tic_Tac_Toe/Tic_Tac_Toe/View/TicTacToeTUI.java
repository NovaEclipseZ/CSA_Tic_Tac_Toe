package View;
import java.util.Scanner;

import Model.AI;
import Model.Gameboard;
import Model.Location;
import Model.Player;

public class TicTacToeTUI
{
    public static void run()
    {
        Scanner input = new Scanner(System.in);
        Gameboard game = new Gameboard();
        System.out.println("Please enter your name: ");
        String name = input.nextLine();
        Player user = new Player(name);
        AI computer = new AI();
        int counter = 0;
        boolean won = false;
        while(!won)
        {
            System.out.println(game);
            if(counter % 2 == 0)
            {
                System.out.println(user.getName() + "'s turn");
                System.out.println("Enter a row:");
                int row = Integer.parseInt(input.nextLine());
                System.out.println("Enter a column:");
                int col = Integer.parseInt(input.nextLine());
                while(!game.takeTurn(row, col, counter))
                {
                    System.out.println("Not a valid input, try again");
                    System.out.println("Enter a row:");
                    row = Integer.parseInt(input.nextLine());
                    System.out.println("Enter a column:");
                    col = Integer.parseInt(input.nextLine());
                }
            }
            else
            {
                System.out.println(computer.getName() + "'s turn");
                Location compTurn = computer.getTurn(game);
                game.takeTurn(compTurn.getYPos(), compTurn.getXPos(), counter);
            }
            if(game.isWon())
            {
                System.out.println(game);
                won = true;
                if(counter % 2 == 0)
                {
                    System.out.println(user.getName() + " won!");
                    user.addWin();
                }
                else
                {
                    System.out.println(computer.getName() + " won!");
                    computer.addWin();
                }
                System.out.println("Would you like to play again? (y)es or (n)o");
                String playAgain = input.nextLine();
                if(playAgain.equals("y"))
                {
                    won = false;
                    game = new Gameboard();
                    counter = -1;
                }
            }
            else if(game.getValidLocations().size() == 0)
            {
                won = true;
                System.out.println("Tied!");
                System.out.println("Would you like to play again? (y)es or (n)o");
                String playAgain = input.nextLine();
                if(playAgain.equals("y"))
                {
                    won = false;
                    game = new Gameboard();
                    counter = -1;
                }
            }
            counter++;
        }
    }
}