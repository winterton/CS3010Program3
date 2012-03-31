//Jacob Smith
//CS 3010 6:00 PM Section


package connect35Game;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class connectApplet extends JApplet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final connect35Game game = new connect35Game(); //game reference
	boolean started = false; //has the game started
	
	Canvas canvas; //game board canvas
	JLabel statusLabel; //Game staus label
	
	//Variables for player chip colors
	Color p1Color;
	Color p2Color;
	
	public void init() {
		createComponents();
	}
	
	private class Canvas extends JPanel {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public void paintComponent(Graphics g){
			super.paintComponent(g);
			
			int w = canvas.getWidth();
			int h = canvas.getHeight();
			
			int sqsize =((w<h) ? w/7 : h/6);
			
			
			//Next few lines draw the board, keeping track of chips as well
			for( int i = 0; i < 7; i++ )
			{
				for( int j = 0; j < 6; j++ )
				{
					g.setColor(Color.WHITE);
					g.drawRect(sqsize*i, sqsize*j, sqsize, sqsize);
					if (game.getValueInLoc(j+1, i+1) == connect35Game.MARK_RED){
						g.setColor(p1Color);
						g.fillOval(sqsize*i,sqsize*j,sqsize,sqsize);
					}
					else if (game.getValueInLoc(j+1,i+1) == connect35Game.MARK_BLACK) {
						g.setColor(p2Color);
						g.fillOval(sqsize*i,sqsize*j,sqsize,sqsize);
						
					}
						
				}

			}

		}
	}
	
	//Handles the creation of a new game when the new game button is pressed
	private class uiHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			game.reset();
			
			//Player chip color selection
			p1Color = getPlayerColorSelection("Player 1");
			p2Color = getPlayerColorSelection("Player 2");
			while (p2Color == p1Color) {
				JOptionPane.showMessageDialog(canvas, "Choose a different color!", "Help", JOptionPane.PLAIN_MESSAGE);
				p2Color = getPlayerColorSelection("Player 2");
			}
			
			started = true;	//New game has started
			updateGameStatus();
			canvas.repaint();
		}
	}
	
	
	//This method updates the board with a players selection if it's valid
	private class PlayerClicked implements MouseListener {
		public void mousePressed(MouseEvent evt) {
			//if game has started
			if( started )
			{
				//attempt to place the chip
				if(!game.placeChecker((evt.getX() / ( canvas.getWidth() / 7 ) + 1 )))
					JOptionPane.showMessageDialog((JPanel)evt.getSource(), "Choose a different column!", "Help", JOptionPane.PLAIN_MESSAGE);
				
				//if game is done then it's done so display status stuff
				if( game.isGameDone() ) 
				{
					started = false;
					
					if( game.gameState() != connect35Game.GAME_STATE_TIE )
						if( game.gameState() == connect35Game.GAME_STATE_RED_WON )
							JOptionPane.showMessageDialog((JPanel)evt.getSource(), "Player 1 wins!", "Winner!", JOptionPane.PLAIN_MESSAGE);
						else
							JOptionPane.showMessageDialog((JPanel)evt.getSource(), "Player 2 wins!", "Winner!", JOptionPane.PLAIN_MESSAGE);
					else
						JOptionPane.showMessageDialog((JPanel)evt.getSource(), "Tie!", "Tie!", JOptionPane.PLAIN_MESSAGE);			
				}
				canvas.repaint();
			}
			else 
				JOptionPane.showMessageDialog((JPanel)evt.getSource(), "Press 'New Game' to start a game!", "Help", JOptionPane.PLAIN_MESSAGE);
			
			//update status label
			updateGameStatus();
		}
		
		//neccessary stuff
		public void mouseClicked(MouseEvent evt){}
		public void mouseReleased(MouseEvent evt){}
		public void mouseEntered(MouseEvent evt){}
		public void mouseExited(MouseEvent evt){}
	}
	
	
	//Updates the game status label with current status
	public void updateGameStatus() {
		if (game.isGameDone()) {
			statusLabel.setText("Game Over!");
		}
		else if (game.gameState() == connect35Game.GAME_STATE_RED_TURN) {
			statusLabel.setText("Player 1's Turn!");
		}
		else if (game.gameState() == connect35Game.GAME_STATE_BLACK_TURN) {
			statusLabel.setText("Player 2's Turn!");
		}
	}
	
	
	//Hanldes player color selection
	public Color getPlayerColorSelection(String player) {
	    String[] choices = { "Red", "Black", "Blue", "Green", "Pink", "Orange" };
	    String input = (String) JOptionPane.showInputDialog(null, player + ", choose your color!",
	        "Color Chooser", JOptionPane.QUESTION_MESSAGE, null,
	        choices, // Array of choices
	        choices[1]); // Initial choice
	    
	    if (input == choices[0]) return Color.RED;
	    else if (input == choices[1]) return Color.black;
	    else if (input == choices[2]) return Color.blue;
	    else if (input == choices[3]) return Color.green;
	    else if (input == choices[4]) return Color.pink;
	    else return Color.orange;
	    
	    
	}
	
	
	//Create all of the UI Components
	public void createComponents() {
		JPanel content = new JPanel();
		content.setLayout(new BorderLayout());
		
		JPanel uip = new JPanel();
		uip.setLayout(new FlowLayout());
		
		JPanel status = new JPanel();
		status.setLayout(new FlowLayout());
		
		statusLabel = new JLabel("Start a new game!");
		status.add(statusLabel);
		
		
		JButton newGameButton = new JButton("New Game");
		newGameButton.addActionListener(new uiHandler());
		
		uip.add(newGameButton);
		
		canvas = new Canvas();
		canvas.setBackground(Color.LIGHT_GRAY);
		canvas.addMouseListener(new PlayerClicked());
		
		content.add(canvas, BorderLayout.CENTER);
		content.add(uip, BorderLayout.SOUTH);
		content.add(status, BorderLayout.NORTH);
		setContentPane(content);
	}
}
