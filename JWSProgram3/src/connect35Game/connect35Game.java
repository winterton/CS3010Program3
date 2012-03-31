//Connect 3.5 Model, Jacob Smith
//CS 3010 6:00 Section


package connect35Game;

public class connect35Game {
	
	// Game states
	public static  final int GAME_STATE_RED_TURN = 3;
	public static  final int GAME_STATE_BLACK_TURN = 4;
	public static  final int GAME_STATE_RED_WON =  5;
	public static  final int GAME_STATE_BLACK_WON =  6;
	public static  final int GAME_STATE_TIE =    7;

	//Checker Constants
	public static  final int MARK_ERROR = -1;
	public static  final int MARK_NONE = 0;
	public static  final int MARK_RED =  1;
	public static  final int MARK_BLACK =  2;

	private int [][] game = new int [6][7];  // the board
	
	//Default constructor for our model
	public connect35Game() {
		this.reset();
	}
	
	//Places the checker
	boolean placeChecker(int col) { 
		if( col < 1 || col > 7) return false;
		if( game[0][col-1] != MARK_NONE ) return false;
		if( isGameDone() ) return false;
		int i = 5;
		while( game[i][col-1] != MARK_NONE ) --i;
		game[i][col-1] = gameState() == GAME_STATE_RED_TURN ? MARK_RED : MARK_BLACK;
		return true;
	}
	
	int gameState(){
		//Game over
		if( check35ShapeForColor(MARK_RED) ) return GAME_STATE_RED_WON;
		if( check35ShapeForColor(MARK_BLACK) ) return GAME_STATE_BLACK_WON;
		if(isTie()) return GAME_STATE_TIE;
		
		//Game in progress
		if( countCheckersOfColor(MARK_RED) > countCheckersOfColor(MARK_BLACK) ) return GAME_STATE_BLACK_TURN;
		else return GAME_STATE_RED_TURN;
	}
	
	//Resets the game to initial values
	public void reset()
	{
		// Clear the game model
		for( int i = 0; i < game.length; i++ )
			for( int j = 0; j < game[i].length; j++ )
				game[i][j] = MARK_NONE;
	}
	
	
	//Simply returns the current checker (if any) of a location
	public int getValueInLoc(int row, int col)
	{
		return game[row-1][col-1];
	}
	
	
	//Checks through the checkers of the provided color for the correct shape to win.
	private boolean check35ShapeForColor(int PLAYER_COLOR) {
		for( int i = 0; i < game.length; i++ )
		{
			for( int j = 0; j < game[i].length; j++ )
			{
				if( game[i][j] == PLAYER_COLOR )
				{
						// i-1
						if( i-1 < 6 && i-1 > -1 && game[i-1][j] == PLAYER_COLOR )
						{
							if( j-2 > -1 )
								if( game[i-1][j-2] == PLAYER_COLOR && game[i-1][j-1] == PLAYER_COLOR ) return true;
							
							if( j+2 < 7 )
								if( game[i-1][j+2] == PLAYER_COLOR && game[i-1][j+1] == PLAYER_COLOR ) return true;
						}
						
						//i+1
						if( i+1 < 6 && i+1 > -1 && game[i+1][j] == PLAYER_COLOR )
						{
							if( j-2 > -1 )
								if( game[i+1][j-2] == PLAYER_COLOR && game[i+1][j-1] == PLAYER_COLOR ) return true;
							
							if( j+2 < 7 )
								if( game[i+1][j+2] == PLAYER_COLOR && game[i+1][j+1] == PLAYER_COLOR ) return true;
						}
						
						//j-1
						if( j-1 < 7 && j-1 > -1 && game[i][j-1] == PLAYER_COLOR )
						{
							if( i-2 > -1)
								if( game[i-2][j-1] == PLAYER_COLOR && game[i-1][j-1] == PLAYER_COLOR ) return true;

							if( i+2 < 6)
								if( game[i+2][j-1] == PLAYER_COLOR && game[i+1][j-1] == PLAYER_COLOR ) return true;
						}
						
						//j+1
						if( j+1 < 7 && j+1 > -1 && game[i][j+1] == PLAYER_COLOR )
						{
							if( i-2 > -1)
								if( game[i-2][j+1] == PLAYER_COLOR && game[i-1][j+1] == PLAYER_COLOR ) return true;

							if( i+2 < 6)
								if( game[i+2][j+1] == PLAYER_COLOR && game[i+1][j+1] == PLAYER_COLOR ) return true;
						}
					}
				}
			}
		return false; //Does not conform to proper shape to win
	}
	
	//Checks if theres a winner or a tie
	public boolean isGameDone()
	{
		return check35ShapeForColor(MARK_RED) || check35ShapeForColor(MARK_BLACK) || isTie();
	}
	
	//Checks for the tie
	public boolean isTie() {
		return countCheckersOfColor(MARK_NONE) == 0 && !check35ShapeForColor(MARK_BLACK) && !check35ShapeForColor(MARK_RED);
	}
	
	
	//Counts the number of positions filled by the parameter
	private int countCheckersOfColor(int COLOR){
		int count = 0;
		for( int i = 0; i < game.length; i++ )
			for( int j = 0; j < game[i].length; j++ )
				count = game[i][j] == COLOR ? ++count : count;
		return count;
	}
}
