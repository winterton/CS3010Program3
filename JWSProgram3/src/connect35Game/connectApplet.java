package connect35Game;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class connectApplet extends JApplet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final connect35Game game = new connect35Game();
	
	Canvas canvas;
	
	public void init() {

	}
	
	public void playGame () {
		
	}
	
	private class Canvas extends JPanel {
		public void paintComponent(Graphics g){
			
		}
	}
	
	private class uiHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String sender = e.getActionCommand();
			
			playGame();
		}
	}
	
	public void createComponents() {
		JPanel content = new JPanel();
		content.setLayout(new BorderLayout());
		
		JPanel uip = new JPanel();
		uip.setLayout(new FlowLayout());
		
		JButton newGameButton = new JButton("New Game");
		
		uip.add(newGameButton);
	}
}
