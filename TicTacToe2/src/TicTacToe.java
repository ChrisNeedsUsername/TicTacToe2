import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;

public class TicTacToe implements ActionListener{

	JFrame frame = new JFrame();
	JPanel MainPanel = new JPanel();
	JPanel header = new JPanel();
	JLabel label = new JLabel();
	ImageIcon icon = new ImageIcon("src/icon.png");
	
	boolean playersTurn;
	Color PlayerColor = Color.green;
	String PlayerSymbol;
	
	Color BotColor = Color.red;
	String BotSymbol;
	
	boolean gameOver = false;
	
	boolean press1 = false;
	boolean press2 = false;
	boolean press3 = false;
	boolean press4 = false;
	boolean press5 = false;
	boolean press6 = false;
	boolean press7 = false;
	boolean press8 = false;
	boolean press9 = false;
	
	// BUTTONS
	JButton[] AllGameButtons = new JButton[9];
	
	public void startTicTacToe() {
		
		myFrame();
		getSymbol();
		firstTurn();
	
		while(!gameOver) {
			if(!playersTurn) {
			botPress();
			}
			else {
				System.out.print("");
			}
		}

	}

	//------------FRAME HEADER ICON--------------
	private void myFrame() { 

		// Frame
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(600, 715);
		frame.setResizable(true);
		frame.setLocation(840, 400);
		frame.setTitle("TicTacToe");
		frame.setLayout(new BorderLayout());
		frame.setIconImage(icon.getImage());
		frame.getContentPane().setBackground(Color.lightGray);
		frame.setVisible(true);
		
		// Text and icon in header
		label.setText("TicTacToe");
		label.setIcon(icon);	
		label.setForeground(Color.WHITE);
		label.setFont(new Font("Kalam", Font.CENTER_BASELINE, 50));
		label.setIconTextGap(-1);
		label.setBounds(10, 10, 600, 100);
		label.setHorizontalTextPosition(JLabel.RIGHT);
		label.setVerticalTextPosition(JLabel.CENTER);
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setBackground(new Color(58, 58, 60));
		label.setOpaque(true);
			
		header.setLayout(new BorderLayout());
		header.setBounds(0, 0, 600, 100);
		header.add(label);
		frame.add(header, BorderLayout.NORTH);
		
		addGameButtons();
		MainPanel.setBounds(0, 100, 600, 615);
		MainPanel.setLayout(new GridLayout(3, 3, 8, 8));
		MainPanel.setBackground(Color.lightGray);
		frame.add(MainPanel);

	
	}
	
	private void addGameButtons() {
		for(int i=0;i<9;i++) {
			AllGameButtons[i] = new JButton();
			MainPanel.add(AllGameButtons[i]);
			AllGameButtons[i].setFont(new Font("Kalam", Font.CENTER_BASELINE, 100));
			AllGameButtons[i].setFocusable(false);
			AllGameButtons[i].addActionListener(this);
			AllGameButtons[i].setBackground(Color.DARK_GRAY);
			AllGameButtons[i].setBorderPainted(false);
			}	
	}
	
	private void firstTurn() {
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if((int)(Math.random() * 2) == 1) {
			playersTurn = true;
			label.setText("Your Turn");
		}
		else {
			playersTurn = false;
			label.setText("Bots Turn");
		}
		
	}
	
	private void getSymbol() {
		if((int)(Math.random() * 2) == 1) {
			PlayerSymbol = "O";
			BotSymbol = "X";
		}
		else {
			PlayerSymbol = "X";
			BotSymbol = "O";
		}
	}
	
	private void botPress() {
		int randomButton = (int)(Math.random() * 9);
		boolean notPressed = true;
		
		try {
			Thread.sleep(1300);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		while(notPressed) {
			
			if(AllGameButtons[randomButton].getText()=="") {
				
				if(randomButton == 0) {
					press1 = true;
				}
				else if(randomButton == 1) {
					press2 = true;
				}
				else if(randomButton == 2) {
					press3 = true;
				}
				else if(randomButton == 3) {
					press4 = true;
				}
				else if(randomButton == 4) {
					press5 = true;
				}
				else if(randomButton == 5) {
					press6 = true;
				}
				else if(randomButton == 6) {
					press7 = true;
				}
				else if(randomButton == 7) {
					press8 = true;
				}
				else if(randomButton == 8) {
					press9 = true;
				}
				
				
				AllGameButtons[randomButton].doClick();
				
				notPressed = false;
			}
			else {
				randomButton = (int)(Math.random() * 9);
			}
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
				
		if(playersTurn) {
			
			if(e.getSource()==AllGameButtons[0]) {

				
					System.out.println("Button 1 Player");
					UIManager.getDefaults().put("Button.disabledText", PlayerColor);
					AllGameButtons[0].setText(PlayerSymbol);
					AllGameButtons[0].setEnabled(false);
					label.setText("Bots Turn");
					playersTurn = false;
				
			}
			else if(e.getSource()==AllGameButtons[1]) {
				
				
					System.out.println("Button 2 Player");
					UIManager.getDefaults().put("Button.disabledText", PlayerColor);
					AllGameButtons[1].setText(PlayerSymbol);
					AllGameButtons[1].setEnabled(false);
					label.setText("Bots Turn");
					playersTurn = false;
				
			}
			else if(e.getSource()==AllGameButtons[2]) {
				
				
					System.out.println("Button 3 Player");
					UIManager.getDefaults().put("Button.disabledText", PlayerColor);
					AllGameButtons[2].setText(PlayerSymbol);
					AllGameButtons[2].setEnabled(false);
					label.setText("Bots Turn");
					playersTurn = false;
				
			}		
			else if(e.getSource()==AllGameButtons[3]) {
				
				
					System.out.println("Button 4 Player");
					UIManager.getDefaults().put("Button.disabledText", PlayerColor);
					AllGameButtons[3].setText(PlayerSymbol);
					AllGameButtons[3].setEnabled(false);
					label.setText("Bots Turn");
					playersTurn = false;
				
				}
			else if(e.getSource()==AllGameButtons[4]) {
				
				
					System.out.println("Button 5 Player");
					UIManager.getDefaults().put("Button.disabledText", PlayerColor);
					AllGameButtons[4].setText(PlayerSymbol);
					AllGameButtons[4].setEnabled(false);
					label.setText("Bots Turn");
					playersTurn = false;
			}
			else if(e.getSource()==AllGameButtons[5]) {
				
				
					System.out.println("Button 6 Player");
					UIManager.getDefaults().put("Button.disabledText", PlayerColor);
					AllGameButtons[5].setText(PlayerSymbol);
					AllGameButtons[5].setEnabled(false);
					label.setText("Bots Turn");
					playersTurn = false;
				
			}
			else if(e.getSource()==AllGameButtons[6]) {
				
				
					System.out.println("Button 7 Player");
					UIManager.getDefaults().put("Button.disabledText", PlayerColor);
					AllGameButtons[6].setText(PlayerSymbol);
					AllGameButtons[6].setEnabled(false);
					label.setText("Bots Turn");
					playersTurn = false;
				
			}
			else if(e.getSource()==AllGameButtons[7]) {
				
				
					System.out.println("Button 8 Player");
					UIManager.getDefaults().put("Button.disabledText", PlayerColor);
					AllGameButtons[7].setText(PlayerSymbol);
					AllGameButtons[7].setEnabled(false);
					label.setText("Bots Turn");
					playersTurn = false;
				
			}
			else if(e.getSource()==AllGameButtons[8]) {
				
				
					System.out.println("Button 9 Player");
					UIManager.getDefaults().put("Button.disabledText", PlayerColor);
					AllGameButtons[8].setText(PlayerSymbol);
					AllGameButtons[8].setEnabled(false);
					label.setText("Bots Turn");
					playersTurn = false;	
			}
		}
		else {

			//UIManager.getDefaults().put("Button.disabledText", BotColor);
			
			if(press1) {
					System.out.println("Button 1 Bot");
					UIManager.getDefaults().put("Button.disabledText", BotColor);
					AllGameButtons[0].setText(BotSymbol);
					AllGameButtons[0].setEnabled(false);
					label.setText("Your Turn");
					playersTurn = true;	
					press1 = false;
			}
			else if(press2) {
					System.out.println("Button 2 Bot");
					UIManager.getDefaults().put("Button.disabledText", BotColor);
					AllGameButtons[1].setText(BotSymbol);
					AllGameButtons[1].setEnabled(false);
					label.setText("Your Turn");
					playersTurn = true;
					press2 = false;
			}
			else if(press3) {
					System.out.println("Button 3 Bot");
					UIManager.getDefaults().put("Button.disabledText", BotColor);
					AllGameButtons[2].setText(BotSymbol);
					AllGameButtons[2].setEnabled(false);
					label.setText("Your Turn");
					playersTurn = true;
					press3 = false;
			}		
			else if(press4) {
					System.out.println("Button 4 Bot");
					UIManager.getDefaults().put("Button.disabledText", BotColor);
					AllGameButtons[3].setText(BotSymbol);
					AllGameButtons[3].setEnabled(false);
					label.setText("Your Turn");
					playersTurn = true;
					press4 = false;
			}
			else if(press5) {
					System.out.println("Button 5 Bot");
					UIManager.getDefaults().put("Button.disabledText", BotColor);
					AllGameButtons[4].setText(BotSymbol);
					AllGameButtons[4].setEnabled(false);
					label.setText("Your Turn");
					playersTurn = true;
					press5 = false;
			}
			else if(press6) {
					System.out.println("Button 6 Bot");
					UIManager.getDefaults().put("Button.disabledText", BotColor);
					AllGameButtons[5].setText(BotSymbol);
					AllGameButtons[5].setEnabled(false);
					label.setText("Your Turn");
					playersTurn = true;
					press6 = false;
			}
			else if(press7) {
					System.out.println("Button 7 Bot");
					UIManager.getDefaults().put("Button.disabledText", BotColor);
					AllGameButtons[6].setText(BotSymbol);
					AllGameButtons[6].setEnabled(false);
					label.setText("Your Turn");
					playersTurn = true;
					press7 = false;
			}
			else if(press8) {
					System.out.println("Button 8 Bot");
					UIManager.getDefaults().put("Button.disabledText", BotColor);
					AllGameButtons[7].setText(BotSymbol);
					AllGameButtons[7].setEnabled(false);
					label.setText("Your Turn");
					playersTurn = true;
					press8 = false;
			}
			else if(press9) {
					System.out.println("Button 9 Bot");
					UIManager.getDefaults().put("Button.disabledText", BotColor);
					AllGameButtons[8].setText(BotSymbol);
					AllGameButtons[8].setEnabled(false);
					label.setText("Your Turn");
					playersTurn = true;
					press9 = false;
			}
	
		}
	}
}

	
	
