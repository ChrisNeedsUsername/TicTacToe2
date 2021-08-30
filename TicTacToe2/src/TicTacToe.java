import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;

public class TicTacToe implements ActionListener{

	ArrayList<Integer> winner = new ArrayList<Integer>();
	
	JFrame frame = new JFrame();
	JPanel MainPanel = new JPanel();
	JPanel header = new JPanel();
	JPanel colors = new JPanel();
	JLabel label = new JLabel();
	ImageIcon icon = new ImageIcon("src/icon.png");
	
	boolean playersTurn;
	Color PlayerColor = Color.green;
	String PlayerSymbol;
	
	Color BotColor = Color.red;
	String BotSymbol;
	
	static Color colorTL = Color.blue;
	static Color colorML = Color.green;
	static Color colorBL = Color.red;
	static Color colorTR = Color.cyan;
	static Color colorMR = Color.magenta;
	static Color colorBR = Color.yellow;
	
	
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
	
	static int Reihe1;
	static int Reihe2;
	static int Reihe3;
	static int Spalte1;
	static int Spalte2;
	static int Spalte3;
	static int Diagonal1;
	static int Diagonal2;
	static int Alle;
	
	static boolean stopBot = false;
	static boolean blockReset = true;
	
	// BUTTONS
	JButton[] AllGameButtons = new JButton[9];
	JButton[] ColorButtons = new JButton[6];
	JButton restartButton = new JButton("Restart");
	
	public void startTicTacToe() {
				
		getSymbol();
		firstTurn();
		//setReiheSpalteDiagonale();
		
		/*
		for(int i=0; i<9; i++) {
			if(AllGameButtons[i].getText()=="" && !playersTurn) {
				gameOver = false;
			}
		}	*/

		while(!gameOver) {
			if(!playersTurn) {
			//System.out.println(winner);
			botAI();
			}
			else {
				System.out.print("");
			}
		}

	}

	//------------FRAME HEADER ICON--------------
	public void myFrame() { 

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
			
		
		colors.setLayout(new GridLayout(3, 2, 8, 8));
		colors.setBackground(new Color(58, 58, 60));

		addColorButtons();
		
		header.setLayout(new BorderLayout());
		header.setBounds(0, 0, 600, 100);
		header.add(label);
		header.add(colors, BorderLayout.WEST);
		frame.add(header, BorderLayout.NORTH);
		
		setColorofColorButtons();
		
		addGameButtons();
		MainPanel.setBounds(0, 100, 600, 615);
		MainPanel.setLayout(new GridLayout(3, 3, 8, 8));
		MainPanel.setBackground(Color.lightGray);
		frame.add(MainPanel);
	
		//restartButton.setBounds(480, 20, 80, 60);
		restartButton.addActionListener(this);
		restartButton.setFocusable(false);
		restartButton.setBackground(new Color(44, 44, 46));
		restartButton.setForeground(Color.LIGHT_GRAY);
		restartButton.setFont(new Font("Kalam", Font.CENTER_BASELINE, 20));
		restartButton.setBorderPainted(false);
		header.add(restartButton, BorderLayout.EAST);
		
		
		
	}
	
	private void addGameButtons() {
		for(int i=0;i<9;i++) {
			AllGameButtons[i] = new JButton();
			AllGameButtons[i].setFont(new Font("Kalam", Font.CENTER_BASELINE, 100));
			AllGameButtons[i].setFocusable(false);
			AllGameButtons[i].addActionListener(this);
			AllGameButtons[i].setBackground(Color.DARK_GRAY);
			AllGameButtons[i].setBorderPainted(false);
			MainPanel.add(AllGameButtons[i]);
			}	
	}
	
	private void addColorButtons() {
		for(int i=0;i<6;i++) {
			ColorButtons[i] = new JButton();
			ColorButtons[i].setFocusable(false);
			ColorButtons[i].setText("  ");
			ColorButtons[i].addActionListener(this);
			ColorButtons[i].setBorderPainted(false);
			
			colors.add(ColorButtons[i]);
		}	
	}
	
	private void setColorofColorButtons() {
		ColorButtons[0].setBackground(colorTL);
		ColorButtons[2].setBackground(colorML);
		ColorButtons[4].setBackground(colorBL);
		ColorButtons[1].setBackground(colorTR);
		ColorButtons[3].setBackground(colorMR);
		ColorButtons[5].setBackground(colorBR);
	}
	
	private void firstTurn() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		int r = (int)(Math.random() * 2);
		
		if(r == 1) {
			playersTurn = true;
			label.setText("Your Turn");
		}
		else {
			playersTurn = false;
			label.setText("Bots Turn");
		}
		
	}
	
	private void getSymbol() {
		int r = (int)(Math.random() * 2);
		
		if(r == 1) {
			PlayerSymbol = "O";
			BotSymbol = "X";
		}
		else {
			PlayerSymbol = "X";
			BotSymbol = "O";
		}
	}
	
	public void setArrayList() {
		
		for(int i = 0; i < 10; i++) {
			winner.add(0);
			}
	}	

	
	private void fillArray() {
		
		if(AllGameButtons[0].getText() == "X") {
			winner.set(1, 10);
		}
		if(AllGameButtons[1].getText() == "X") {
			winner.set(2, 10);
		}
		if(AllGameButtons[2].getText() == "X") {
			winner.set(3, 10);
		}
		if(AllGameButtons[3].getText() == "X") {
			winner.set(4, 10);
		}
		if(AllGameButtons[4].getText() == "X") {
			winner.set(5, 10);
		}
		if(AllGameButtons[5].getText() == "X") {
			winner.set(6, 10);
		}
		if(AllGameButtons[6].getText() == "X") {
			winner.set(7, 10);
		}
		if(AllGameButtons[7].getText() == "X") {
			winner.set(8, 10);
		}
		if(AllGameButtons[8].getText() == "X") {
			winner.set(9, 10);
		}

		if(AllGameButtons[0].getText() == "O") {
			winner.set(1, 1);
		}
		if(AllGameButtons[1].getText() == "O") {
			winner.set(2, 1);
		}
		if(AllGameButtons[2].getText() == "O") {
			winner.set(3, 1);
		}
		if(AllGameButtons[3].getText() == "O") {
			winner.set(4, 1);
		}
		if(AllGameButtons[4].getText() == "O") {
			winner.set(5, 1);
		}
		if(AllGameButtons[5].getText() == "O") {
			winner.set(6, 1);
		}
		if(AllGameButtons[6].getText() == "O") {
			winner.set(7, 1);
		}
		if(AllGameButtons[7].getText() == "O") {
			winner.set(8, 1);
		}
		if(AllGameButtons[8].getText() == "O") {
			winner.set(9, 1);
		}
	}
	
	private void setReiheSpalteDiagonale() {
		
		Reihe1 = winner.get(1) + winner.get(2) + winner.get(3);
		Reihe2 = winner.get(4) + winner.get(5) + winner.get(6);
		Reihe3 = winner.get(7) + winner.get(8) + winner.get(9);
		
		Spalte1 = winner.get(1) + winner.get(4) + winner.get(7);
		Spalte2 = winner.get(2) + winner.get(5) + winner.get(8);
		Spalte3 = winner.get(3) + winner.get(6) + winner.get(9);
		
		Diagonal1 = winner.get(1) + winner.get(5) + winner.get(9);
		Diagonal2 = winner.get(3) + winner.get(5) + winner.get(7);
		
		Alle = Reihe1 + Reihe2 + Reihe3;
	
	}
	
	
	private void win() {
		
		
		
		//System.out.println(winner);
		setReiheSpalteDiagonale();
		//-------Win or loose--------------
		if(Reihe1 == 3 || Reihe2 == 3 || Reihe3 == 3 || Spalte1 == 3 || Spalte2 == 3 || Spalte3 == 3 || Diagonal1 == 3 || Diagonal2 == 3) {	
			label.setText("O won");
			playersTurn = true;
			stopBot = true;
			
			for(int i=0; i<9; i++) {
				AllGameButtons[i].setEnabled(false);
			}
			
			//greenEnd();
			//stopGame = true;
		}
		else if(Reihe1 == 30 || Reihe2 == 30 || Reihe3 == 30 || Spalte1 == 30 || Spalte2 == 30 || Spalte3 == 30 || Diagonal1 == 30 || Diagonal2 == 30) {
			label.setText("X won");
			stopBot = true;
			playersTurn = true;

			for(int i=0; i<9; i++) {
				AllGameButtons[i].setEnabled(false);
			}
			//greenEnd();
			//stopGame = true;
		}
		else if(Alle == 45 || Alle == 54) {
			stopBot = true;
			playersTurn = true;

			label.setText("Draw");
			//stopGame = true;
		}
		
	}
	
	
	private void botAI() {
		
		setReiheSpalteDiagonale();

		//----------Bot AI---------------
		/*
		System.out.println("Reihe1: "+Reihe1);
		System.out.println("Reihe2: "+Reihe2);
		System.out.println("Reihe3: "+Reihe3);

		System.out.println("Spalte1: "+Spalte1);
		System.out.println("Spalte2: "+Spalte2);
		System.out.println("Spalte3: "+Spalte3);
		
		System.out.println("Diagonal1: "+Diagonal1);
		System.out.println("Diagonal2: "+Diagonal2);
		*/
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		if(BotSymbol == "X") {
			
			//-----------------------------GEWINNEN-------------------------------------
			if(Reihe1==20 || Reihe2==20 || Reihe3==20 || Spalte1==20 || Spalte2==20 || Spalte3==20 || Diagonal1==20 || Diagonal2 == 20) {
				//Reihen
				if(Reihe1 == 20) {
					System.out.println("R1 20, kann gewinnen");
					if(AllGameButtons[0].getText() == "") {
						press1 = true;
					}
					else if(AllGameButtons[1].getText() == "") {
						press2 = true;
					}			
					else if(AllGameButtons[2].getText() == "") {
						press3 = true;
					}
				}
				if(Reihe2 == 20) {
					System.out.println("R2 20, kann gewinnen");
					if(AllGameButtons[3].getText() == "") {
						press4 = true;
					}
					else if(AllGameButtons[4].getText() == "") {
						press5 = true;
					}			
					else if(AllGameButtons[5].getText() == "") {
						press6 = true;
					}
				}
				if(Reihe3 == 20) {
					System.out.println("R3 20, kann gewinnen");
					if(AllGameButtons[6].getText() == "") {
						press7 = true;
					}
					else if(AllGameButtons[7].getText() == "") {
						press8 = true;
					}			
					else if(AllGameButtons[8].getText() == "") {
						press9 = true;
					}
				}
				
				//Spalten
				if(Spalte1 == 20) {
					System.out.println("S1 20, kann gewinnen");
					if(AllGameButtons[0].getText() == "") {
						press1 = true;
					}
					else if(AllGameButtons[3].getText() == "") {
						press4 = true;
					}			
					else if(AllGameButtons[6].getText() == "") {
						press7 = true;
					}
				}
				if(Spalte2 == 20) {
					System.out.println("S2 20, kann gewinnen");
					if(AllGameButtons[1].getText() == "") {
						press2 = true;
					}
					else if(AllGameButtons[4].getText() == "") {
						press5 = true;
					}			
					else if(AllGameButtons[7].getText() == "") {
						press8 = true;
					}
				}
				if(Spalte3 == 20) {
					System.out.println("S3 20, kann gewinnen");
					if(AllGameButtons[2].getText() == "") {
						press3 = true;
					}
					else if(AllGameButtons[5].getText() == "") {
						press6 = true;
					}			
					else if(AllGameButtons[8].getText() == "") {
						press9 = true;
					}
				}
				
				//Diagonalen
				if(Diagonal1 == 20) {
					System.out.println("D1 20, kann gewinnen");
					if(AllGameButtons[0].getText() == "") {
						press1 = true;
					}
					else if(AllGameButtons[4].getText() == "") {
						press5 = true;
					}			
					else if(AllGameButtons[8].getText() == "") {
						press9 = true;
					}
				}		
				if(Diagonal2 == 20) {
					System.out.println("D2 20, kann gewinnen");
					if(AllGameButtons[2].getText() == "") {
						press3 = true;
					}
					else if(AllGameButtons[4].getText() == "") {
						press5 = true;
					}			
					else if(AllGameButtons[6].getText() == "") {
						press7 = true;
					}
				}
	
					}
			
			else {
				//-----------------------------Verteidigen-------------------------------------
				//Reihen
				if(Reihe1 == 2) {
					System.out.println("R1 2, kann verlieren");
					if(AllGameButtons[0].getText() == "") {
						press1 = true;
					}
					else if(AllGameButtons[1].getText() == "") {
						press2 = true;
					}			
					else if(AllGameButtons[2].getText() == "") {
						press3 = true;
					}
				}
				if(Reihe2 == 2) {
					System.out.println("R2 2, kann verlieren");
					if(AllGameButtons[3].getText() == "") {
						press4 = true;
					}
					else if(AllGameButtons[4].getText() == "") {
						press5 = true;
					}			
					else if(AllGameButtons[5].getText() == "") {
						press6 = true;
					}
				}
				if(Reihe3 == 2) {
					System.out.println("R3 2, kann verlieren");
					if(AllGameButtons[6].getText() == "") {
						press7 = true;
					}
					else if(AllGameButtons[7].getText() == "") {
						press8 = true;
					}			
					else if(AllGameButtons[8].getText() == "") {
						press9 = true;
					}
				}
				
				//Spalten
				if(Spalte1 == 2) {
					System.out.println("S1 2, kann verlieren");
					if(AllGameButtons[0].getText() == "") {
						press1 = true;
					}
					else if(AllGameButtons[3].getText() == "") {
						press4 = true;
					}			
					else if(AllGameButtons[6].getText() == "") {
						press7 = true;
					}
				}
				if(Spalte2 == 2) {
					System.out.println("S2 2, kann verlieren");
					if(AllGameButtons[1].getText() == "") {
						press2 = true;
					}
					else if(AllGameButtons[4].getText() == "") {
						press5 = true;
					}			
					else if(AllGameButtons[7].getText() == "") {
						press8 = true;
					}
				}
				if(Spalte3 == 2) {
					System.out.println("S2 2, kann verlieren");
					if(AllGameButtons[2].getText() == "") {
						press3 = true;
					}
					else if(AllGameButtons[5].getText() == "") {
						press6 = true;
					}			
					else if(AllGameButtons[8].getText() == "") {
						press9 = true;
					}
				}
				
				//Diagonalen
				if(Diagonal1 == 2) {
					System.out.println("D1 2, kann verlieren");
					if(AllGameButtons[0].getText() == "") {
						press1 = true;
					}
					else if(AllGameButtons[4].getText() == "") {
						press5 = true;
					}			
					else if(AllGameButtons[8].getText() == "") {
						press9 = true;
					}
				}		
				if(Diagonal2 == 2) {
					System.out.println("D2 2, kann verlieren");
					if(AllGameButtons[2].getText() == "") {
						press3 = true;
					}
					else if(AllGameButtons[4].getText() == "") {
						press5 = true;
					}			
					else if(AllGameButtons[6].getText() == "") {
						press7 = true;
					}
				}
				//-----------------------------Strategie-------------------------------------
				
				
				
				
				
				
				//-----------------------------Irgendwas-------------------------------------
				if(press1==false && press2==false && press3==false && press4==false && press5==false && press6==false && press7==false && press8==false && press9==false) {
					int randomNumber = (int)(Math.random() * 9);
					boolean noPress = true;
					
					while(noPress) {
						if(AllGameButtons[randomNumber].getText()=="") {
							
							if(randomNumber == 0) {
								press1 = true;
							}
							else if(randomNumber == 1) {
								press2 = true;
							}
							else if(randomNumber == 2) {
								press3 = true;
							}
							else if(randomNumber == 3) {
								press4 = true;
							}
							else if(randomNumber == 4) {
								press5 = true;
							}
							else if(randomNumber == 5) {
								press6 = true;
							}
							else if(randomNumber == 6) {
								press7 = true;
							}
							else if(randomNumber == 7) {
								press8 = true;
							}
							else if(randomNumber == 8) {
								press9 = true;
							}
							noPress = false;
							System.out.println("Irgendwas drücken");
						}
						else {
							randomNumber = (int)(Math.random() * 9);
						}
					}
				}
			}			
		}	
				
			else {
				if(Reihe1==2 || Reihe2==2 || Reihe3==2 || Spalte1==2 || Spalte2==2 || Spalte3==2 || Diagonal1==2 || Diagonal2 == 2) {
				//-----------------------------Gewinnen-------------------------------------
				//Reihen
				if(Reihe1 == 2) {
					System.out.println("R1 2, kann gewinnen");
					if(AllGameButtons[0].getText() == "") {
						press1 = true;
					}
					else if(AllGameButtons[1].getText() == "") {
						press2 = true;
					}			
					else if(AllGameButtons[2].getText() == "") {
						press3 = true;
					}
				}
				if(Reihe2 == 2) {
					System.out.println("R2 2, kann gewinnen");
					if(AllGameButtons[3].getText() == "") {
						press4 = true;
					}
					else if(AllGameButtons[4].getText() == "") {
						press5 = true;
					}			
					else if(AllGameButtons[5].getText() == "") {
						press6 = true;
					}
				}
				if(Reihe3 == 2) {
					System.out.println("R3 2, kann gewinnen");
					if(AllGameButtons[6].getText() == "") {
						press7 = true;
					}
					else if(AllGameButtons[7].getText() == "") {
						press8 = true;
					}			
					else if(AllGameButtons[8].getText() == "") {
						press9 = true;
					}
				}
				
				//Spalten
				if(Spalte1 == 2) {
					System.out.println("S1 2, kann gewinnen");
					if(AllGameButtons[0].getText() == "") {
						press1 = true;
					}
					else if(AllGameButtons[3].getText() == "") {
						press4 = true;
					}			
					else if(AllGameButtons[6].getText() == "") {
						press7 = true;
					}
				}
				if(Spalte2 == 2) {
					System.out.println("S2 2, kann gewinnen");
					if(AllGameButtons[1].getText() == "") {
						press2 = true;
					}
					else if(AllGameButtons[4].getText() == "") {
						press5 = true;
					}			
					else if(AllGameButtons[7].getText() == "") {
						press8 = true;
					}
				}
				if(Spalte3 == 2) {
					System.out.println("S3 2, kann gewinnen");
					if(AllGameButtons[2].getText() == "") {
						press3 = true;
					}
					else if(AllGameButtons[5].getText() == "") {
						press6 = true;
					}			
					else if(AllGameButtons[8].getText() == "") {
						press9 = true;
					}
				}
				
				//Diagonalen
				if(Diagonal1 == 2) {
					System.out.println("D1 2, kann gewinnen");
					if(AllGameButtons[0].getText() == "") {
						press1 = true;
					}
					else if(AllGameButtons[4].getText() == "") {
						press5 = true;
					}			
					else if(AllGameButtons[8].getText() == "") {
						press9 = true;
					}
				}		
				if(Diagonal2 == 2) {
					System.out.println("D2 2, kann gewinnen");
					if(AllGameButtons[2].getText() == "") {
						press3 = true;
					}
					else if(AllGameButtons[4].getText() == "") {
						press5 = true;
					}			
					else if(AllGameButtons[6].getText() == "") {
						press7 = true;
					}
				}
				}	
				else {	
				//-----------------------------Verteidigen-------------------------------------	
				//Reihen
				if(Reihe1 == 20) {
					System.out.println("R1 20, kann verlieren");
					if(AllGameButtons[0].getText() == "") {
						press1 = true;
					}
					else if(AllGameButtons[1].getText() == "") {
						press2 = true;
					}			
					else if(AllGameButtons[2].getText() == "") {
						press3 = true;
					}
				}
				if(Reihe2 == 20) {
					System.out.println("R2 20, kann verlieren");
					if(AllGameButtons[3].getText() == "") {
						press4 = true;
					}
					else if(AllGameButtons[4].getText() == "") {
						press5 = true;
					}			
					else if(AllGameButtons[5].getText() == "") {
						press6 = true;
					}
				}
				if(Reihe3 == 20) {
					System.out.println("R3 20, kann verlieren");
					if(AllGameButtons[6].getText() == "") {
						press7 = true;
					}
					else if(AllGameButtons[7].getText() == "") {
						press8 = true;
					}			
					else if(AllGameButtons[8].getText() == "") {
						press9 = true;
					}
				}
				
				//Spalten
				if(Spalte1 == 20) {
					System.out.println("S1 20, kann verlieren");
					if(AllGameButtons[0].getText() == "") {
						press1 = true;
					}
					else if(AllGameButtons[3].getText() == "") {
						press4 = true;
					}			
					else if(AllGameButtons[6].getText() == "") {
						press7 = true;
					}
				}
				if(Spalte2 == 20) {
					System.out.println("S2 20, kann verlieren");
					if(AllGameButtons[1].getText() == "") {
						press2 = true;
					}
					else if(AllGameButtons[4].getText() == "") {
						press5 = true;
					}			
					else if(AllGameButtons[7].getText() == "") {
						press8 = true;
					}
				}
				if(Spalte3 == 20) {
					System.out.println("S3 20, kann verlieren");
					if(AllGameButtons[2].getText() == "") {
						press3 = true;
					}
					else if(AllGameButtons[5].getText() == "") {
						press6 = true;
					}			
					else if(AllGameButtons[8].getText() == "") {
						press9 = true;
					}
				}
				
				//Diagonalen
				if(Diagonal1 == 20) {
					System.out.println("D1 20, kann verlieren");
					if(AllGameButtons[0].getText() == "") {
						press1 = true;
					}
					else if(AllGameButtons[4].getText() == "") {
						press5 = true;
					}			
					else if(AllGameButtons[8].getText() == "") {
						press9 = true;
					}
				}		
				if(Diagonal2 == 20) {
					System.out.println("D2 20, kann verlieren");
					if(AllGameButtons[2].getText() == "") {
						press3 = true;
					}
					else if(AllGameButtons[4].getText() == "") {
						press5 = true;
					}			
					else if(AllGameButtons[6].getText() == "") {
						press7 = true;
					}
				}
				
				//-----------------------------Strategie-------------------------------------
				
				
				
				
				
				
				
				//-----------------------------Irgendwas-------------------------------------
				if(press1==false && press2==false && press3==false && press4==false && press5==false && press6==false && press7==false && press8==false && press9==false) {
					int randomNumber = (int)(Math.random() * 9);
					boolean noPress = true;
					
					while(noPress) {
						if(AllGameButtons[randomNumber].getText()=="") {
							
							if(randomNumber == 0) {
								press1 = true;
							}
							else if(randomNumber == 1) {
								press2 = true;
							}
							else if(randomNumber == 2) {
								press3 = true;
							}
							else if(randomNumber == 3) {
								press4 = true;
							}
							else if(randomNumber == 4) {
								press5 = true;
							}
							else if(randomNumber == 5) {
								press6 = true;
							}
							else if(randomNumber == 6) {
								press7 = true;
							}
							else if(randomNumber == 7) {
								press8 = true;
							}
							else if(randomNumber == 8) {
								press9 = true;
							}
							noPress = false;
							System.out.println("Irgendwas drücken");
						}
						else {
							randomNumber = (int)(Math.random() * 9);
						}
					}
				}
			}
		}
			
			
			
			if(press1==true) {
				AllGameButtons[0].doClick();
			}
			else if(press2==true) {
				AllGameButtons[1].doClick();
			}
			else if(press3==true) {
				AllGameButtons[2].doClick();
			}
			else if(press4==true) {
				AllGameButtons[3].doClick();
			}
			else if(press5==true) {
				AllGameButtons[4].doClick();
			}
			else if(press6==true) {
				AllGameButtons[5].doClick();
			}
			else if(press7==true) {
				AllGameButtons[6].doClick();
			}
			else if(press8==true) {
				AllGameButtons[7].doClick();
			}
			else if(press9==true) {
				AllGameButtons[8].doClick();
			}

	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource()==restartButton && blockReset == false) {
			//System.out.println("restart");
			
			getSymbol();
			firstTurn();
			
			for(int i=0; i<10; i++) {
				winner.set(i, 0);
			}
			for(int r = 0; r<9; r++) {
				AllGameButtons[r].setText("");
				AllGameButtons[r].setEnabled(true);
			}
			setReiheSpalteDiagonale();
			//System.out.println(Alle);
			//System.out.println(winner);

			blockReset = true;
			stopBot = false;
			gameOver = false;
		}
		
		
		
		if(playersTurn) {
			
			if(e.getSource()==AllGameButtons[0]) {

				
					System.out.println("Button 1 Player");
					UIManager.getDefaults().put("Button.disabledText", PlayerColor);
					AllGameButtons[0].setText(PlayerSymbol);
					AllGameButtons[0].setEnabled(false);

					blockReset = false;
					
					if(stopBot == false) {
						playersTurn = false;
						label.setText("Bots Turn");
					}
			}
			else if(e.getSource()==AllGameButtons[1]) {
				
				
					System.out.println("Button 2 Player");
					UIManager.getDefaults().put("Button.disabledText", PlayerColor);
					AllGameButtons[1].setText(PlayerSymbol);
					AllGameButtons[1].setEnabled(false);

					blockReset = false;
					
					if(stopBot == false) {
						playersTurn = false;
						label.setText("Bots Turn");
					}
			}
			else if(e.getSource()==AllGameButtons[2]) {
				
				
					System.out.println("Button 3 Player");
					UIManager.getDefaults().put("Button.disabledText", PlayerColor);
					AllGameButtons[2].setText(PlayerSymbol);
					AllGameButtons[2].setEnabled(false);

					blockReset = false;
					
					if(stopBot == false) {
						playersTurn = false;
						label.setText("Bots Turn");
					}
			}		
			else if(e.getSource()==AllGameButtons[3]) {
				
				
					System.out.println("Button 4 Player");
					UIManager.getDefaults().put("Button.disabledText", PlayerColor);
					AllGameButtons[3].setText(PlayerSymbol);
					AllGameButtons[3].setEnabled(false);

					blockReset = false;
					
					if(stopBot == false) {
						playersTurn = false;
						label.setText("Bots Turn");
					}
				}
			else if(e.getSource()==AllGameButtons[4]) {
				
				
					System.out.println("Button 5 Player");
					UIManager.getDefaults().put("Button.disabledText", PlayerColor);
					AllGameButtons[4].setText(PlayerSymbol);
					AllGameButtons[4].setEnabled(false);

					blockReset = false;
					
					if(stopBot == false) {
						playersTurn = false;
						label.setText("Bots Turn");
					}
			}
			else if(e.getSource()==AllGameButtons[5]) {
				
				
					System.out.println("Button 6 Player");
					UIManager.getDefaults().put("Button.disabledText", PlayerColor);
					AllGameButtons[5].setText(PlayerSymbol);
					AllGameButtons[5].setEnabled(false);

					blockReset = false;
					
					if(stopBot == false) {
						playersTurn = false;
						label.setText("Bots Turn");
					}
			}
			else if(e.getSource()==AllGameButtons[6]) {
				
				
					System.out.println("Button 7 Player");
					UIManager.getDefaults().put("Button.disabledText", PlayerColor);
					AllGameButtons[6].setText(PlayerSymbol);
					AllGameButtons[6].setEnabled(false);

					blockReset = false;
					
					if(stopBot == false) {
						playersTurn = false;
						label.setText("Bots Turn");
					}
			}
			else if(e.getSource()==AllGameButtons[7]) {
				
				
					System.out.println("Button 8 Player");
					UIManager.getDefaults().put("Button.disabledText", PlayerColor);
					AllGameButtons[7].setText(PlayerSymbol);
					AllGameButtons[7].setEnabled(false);
					
					blockReset = false;
					
					if(stopBot == false) {
						playersTurn = false;
						label.setText("Bots Turn");
					}
			}
			else if(e.getSource()==AllGameButtons[8]) {
				
				
					System.out.println("Button 9 Player");
					UIManager.getDefaults().put("Button.disabledText", PlayerColor);
					AllGameButtons[8].setText(PlayerSymbol);
					AllGameButtons[8].setEnabled(false);

					blockReset = false;
					
					if(stopBot == false) {
						playersTurn = false;
						label.setText("Bots Turn");
					}
			}
			fillArray();
			win();

			
				
			
			//botAI();
			/*
			System.out.println(press1);
			System.out.println(press2);
			System.out.println(press3);
			System.out.println(press4);
			System.out.println(press5);
			System.out.println(press6);
			System.out.println(press7);
			System.out.println(press8);
			System.out.println(press9);
			*/
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
	
			fillArray();
			win();
			//botAI();
			//System.out.println(winner);
			

		}
		
		
		
	}
	
}

	
	
