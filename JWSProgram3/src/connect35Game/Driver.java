//Connect 3.5 Controller, Jacob Smith
//CS 3010 6:00 Section

package connect35Game;

import java.util.Scanner;

public class Driver 
{
	private final connect35Game game;
	
	//Controller Constructor
	public Driver()
	{
		game = new connect35Game();
	}
	
	//Main Method
	public static void main(String[] args)
	{
		new Driver().playGame();
	}
	
	//Start the game
	private void playGame()
	{
		//Scanner to read in the users move
		Scanner in = new Scanner(System.in);
		
		//While game is not over, play game
		while( game.gameState() == connect35Game.GAME_STATE_RED_TURN || game.gameState() == connect35Game.GAME_STATE_BLACK_TURN )
		{
			drawGameBoard();
			String player = game.gameState() == connect35Game.GAME_STATE_RED_TURN ? "Red" : "Black";
			System.out.print(player+"'s turn - enter a column: ");
			int column = in.nextInt();
			
			//While no valid input, ask for input
			while( !game.placeChecker(column) )
			{
				System.out.print("Column " + column + " is invalid.  Enter a valid column: ");
				column = in.nextInt();
			}	
		}
		//Draw the view
		drawGameBoard();
		
		String winner;
		if( game.gameState() != connect35Game.GAME_STATE_TIE ) {
			winner = game.gameState() == connect35Game.GAME_STATE_RED_WON ? "Red wins!" : "Black wins!";
		}
		else {
			winner = "Tie!";
		}
		System.out.println(winner);
		in.close();
	}
	
	//Draw the view (console based)
	private void drawGameBoard()
	{
		String space_symbol;
		
		for( int i = 1; i <= 6; i++ )
		{
			for( int j = 1; j <= 7; j++ )
			{
				if( game.getValueInLoc(i, j) == connect35Game.MARK_NONE ) space_symbol = "_"; //Empty
				else
					space_symbol = game.getValueInLoc(i, j) == connect35Game.MARK_RED ? "R" : "B"; //Red or Black
				System.out.print(" " + space_symbol + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
}