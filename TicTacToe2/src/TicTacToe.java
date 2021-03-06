import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import javax.swing.*;
import javax.swing.border.EmptyBorder;


public class TicTacToe implements ActionListener{

	ArrayList<Integer> winner = new ArrayList<Integer>();
	
	File configFile = new File("config.txt");
	Properties prop = new Properties();
	String fileName = "config.txt";
	
	JFrame frame = new JFrame();
	JPanel MainPanel = new JPanel();
	JPanel header = new JPanel();
	JPanel colors = new JPanel();
	JPanel topRight = new JPanel();
	
	//JPanel with CardLayout to switch between overlapping panels -> MainPanel, menuPanel
	JPanel gameMenuCard = new JPanel(new CardLayout());
	
	
	JLabel label = new JLabel();
	ImageIcon icon = new ImageIcon("icon.png");
	ImageIcon MenuIcon = new ImageIcon("Zahnrad.png");
	
	
	String Difficulty;
	
	//saved Configs
	//String savedDesign;
	String savedDifficulty;
	Color savedPlayerColor;
	Color savedBotColor;
	Color savedButtonColor;
	Color savedGridColor;
	
	
	
	
	
	
	String Draw = "Draw";
	String Xwon = "X won";
	String Owon = "O won";
	
	String defaultLabelPlayer = "Your Turn";
	String defaultLabelBot = "Bot's Turn";
	
	String temporaryLabel;
	
	String TurnLabel1 = defaultLabelPlayer;
	String TurnLabel2 = defaultLabelBot;
	
	String TurnLabelCoop1 = "Player 1's turn";
	String TurnLabelCoop2 = "Player 2's turn";
	
	boolean draw = false;
	boolean Xwin = false;
	boolean Owin = false;
	
	boolean lightModeOn = false;
	boolean darkModeOn = true;
	boolean customModeOn = false;
	
	boolean playersTurn;
	
	//Symbols
	String PlayerSymbol;
	String BotSymbol;
	
	//Colors
	Color PlayerColor = Color.green;;
	Color BotColor = Color.red;
	
	Color ButtonColor = Color.DARK_GRAY;
	Color GridColor = Color.LIGHT_GRAY;
	
	Color WinnerButtonColor = new Color(20, 200, 20);
	Color WinnerFontColor = Color.DARK_GRAY;
	
	static Color colorTL = Color.blue;
	static Color colorML = Color.green;
	static Color colorBL = Color.red;
	static Color colorTR = Color.cyan;
	static Color colorMR = Color.magenta;
	static Color colorBR = Color.yellow;
	
	//Font
	Font myFont = new Font("Kalam", Font.CENTER_BASELINE, 25);

	
	//Menu
	JPanel menuPanel = new JPanel(new GridLayout(6, 1, 5, 5));
	JPanel menuLine1 = new JPanel(new GridLayout(1, 4, 2, 0));
	JPanel menuLine2 = new JPanel(new GridLayout(1, 6, 2, 0));
	JPanel menuLine3 = new JPanel(new GridLayout(1, 4, 10, 10));
	JPanel menuLine4 = new JPanel(new GridLayout(1, 4, 10, 10));
	JPanel menuLine5 = new JPanel(new GridLayout(3, 8, 20, 10));
	JPanel menuLine6 = new JPanel(new GridLayout(1, 1, 10, 10));	//Wenn Main Menu button 1,2,10,10
	
	Color MenuColor = Color.darkGray;
	Color MenuFontColor = Color.lightGray;
	//Line 1
	JLabel Line1 = new JLabel("Design:", SwingConstants.CENTER);
	JRadioButton light = new JRadioButton("light");
	JRadioButton dark = new JRadioButton("dark");
	JRadioButton custom = new JRadioButton("custom");
	ButtonGroup group1 = new ButtonGroup();
	
	//Line 2
	JLabel Line2 = new JLabel("Difficulty:", SwingConstants.CENTER);
	JRadioButton easy = new JRadioButton("easy");
	JRadioButton medium = new JRadioButton("medium");
	JRadioButton hard = new JRadioButton("hard");
	JRadioButton extreme = new JRadioButton("extreme");
	JRadioButton Player = new JRadioButton("Player");
	ButtonGroup group2 = new ButtonGroup();
	
	//Line 3
	JLabel Playercolor = new JLabel("Playercolor", SwingConstants.CENTER);
	JLabel Botcolor = new JLabel("Botcolor", SwingConstants.CENTER);
	JLabel Buttoncolor = new JLabel("Buttoncolor", SwingConstants.CENTER);
	JLabel Gridcolor = new JLabel("Gridcolor", SwingConstants.CENTER);
	
	//Line 4
	JPanel PlayerColorDropTarget = new JPanel();
	JPanel BotColorDropTarget = new JPanel();
	JPanel ButtonColorDropTarget = new JPanel();
	JPanel GridColorDropTarget = new JPanel();
	
	//Line 6
	//Button MainMenu = new Button("Main Menu");
	Button QuitGame = new Button("Quit Game");
	 
	
	boolean gameOver = false;
	boolean inMenu = false;
	
	boolean EasyMode=false;
	boolean MediumMode = false;
	boolean HardMode = false;
	boolean ExtremeMode = false;
	boolean localCoop = false;
	
	boolean switchedMode = false;
	
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
	JButton MenuButton = new JButton(MenuIcon);
	
	boolean readConfigSuccess = false;
	
	public void startTicTacToe() {
				
		//createConfigFile();
		
		readConfigFile();

		System.out.println("easy: "+EasyMode);
		System.out.println("medium: "+MediumMode);
		System.out.println("hard: "+HardMode);
		System.out.println("extreme: "+ExtremeMode);
		System.out.println("localCoop: "+localCoop);
		
		if(EasyMode) {
			easy.setSelected(true);
			System.out.println("easy");
		}
		else if(MediumMode) {
			medium.setSelected(true);
			System.out.println("medium");
		}
		else if(HardMode) {
			hard.setSelected(true);
			System.out.println("hard");
		}
		else if(ExtremeMode) {
			extreme.setSelected(true);
			System.out.println("extreme");
		}
		else if(localCoop) {
			Player.setSelected(true);
			System.out.println("localCoop");
			
			TurnLabel1 = TurnLabelCoop1;
			TurnLabel2 = TurnLabelCoop2;
		}
		else {
			System.out.println("nothing saved, hard");
			hard.setSelected(true);
			Difficulty = "hard";
			HardMode = true;
		}
		
		
		if(ButtonColor.equals(Color.DARK_GRAY) && GridColor.equals(Color.LIGHT_GRAY)) {
			dark.setSelected(true);
		}
		else if(ButtonColor.equals(Color.LIGHT_GRAY) && GridColor.equals(Color.DARK_GRAY)){
			light.setSelected(true);
		}
		else {
			custom.setSelected(true);
		}
		
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
				if(!playersTurn && !localCoop) {
				//System.out.println(winner);
					if(EasyMode) {
						//System.out.println("You chose easy!");
						botEasyAI();
					}
					else if(MediumMode) {
						//System.out.println("You chose medium!");
						botMediumAI();
				}
					else if(HardMode) {
						//System.out.println("You chose hard!");
						botHardAI();
					}
					else if(ExtremeMode) {
						//System.out.println("You chose extreme!");
						botHardAI();
						BotExtreme();
						
					}
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
		//frame.setSize(600, 715);
		frame.setResizable(true);
		
		frame.setTitle("TicTacToe");
		frame.setLayout(new BorderLayout());
		frame.setIconImage(icon.getImage());
		frame.getContentPane().setBackground(GridColor);
		frame.setMinimumSize(new Dimension(700,815));
		
		frame.setSize(700,815);
		//System.out.println(frame.getSize());
		frame.setLocationRelativeTo(null); 
		
		frame.addWindowListener(new WindowAdapter() { 
			public void windowClosing(WindowEvent e) {
				
				//System.out.println("closed game");  
				
				if(EasyMode) {
					Difficulty = "easy";
				}
				else if(MediumMode) {
					Difficulty = "medium";
				}
				else if(HardMode) {
					Difficulty = "hard";
				}
				else if(ExtremeMode) {
					Difficulty = "extreme";
				}
				else if(localCoop) {
					Difficulty = "localCoop";
				}
				
				createAndWriteFile();
				frame.dispose();
				
			} });
		
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
		colors.setBorder(new EmptyBorder(10, 10, 10, 0));
		
		addColorButtons();
		
		header.setLayout(new BorderLayout());
		header.setBounds(0, 0, 600, 100);
		header.add(label);
		header.add(colors, BorderLayout.WEST);
		frame.add(header, BorderLayout.NORTH);
		
		setColorofColorButtons();
		

		
		addGameButtons();
		//MainPanel.setBounds(0, 100, 600, 615);
		MainPanel.setLayout(new GridLayout(3, 3, 8, 8));
		MainPanel.setBackground(GridColor);
		//frame.add(MainPanel);
	
		//Menu
		//menuPanel.setBounds(300, 100, 100, 115);
		menuPanel.setBackground(MenuColor);
		menuPanel.setBorder(new EmptyBorder(0, 20, 20, 20));
		fillMenuPanel();
		
		menuLine1.setBackground(MenuColor);//MenuColor);
		menuLine2.setBackground(MenuColor);//MenuColor);
		menuLine3.setBackground(MenuColor);//MenuColor);
		menuLine4.setBackground(MenuColor);//MenuColor);
		menuLine5.setBackground(MenuColor);//(MenuColor);
		menuLine6.setBackground(MenuColor);//(MenuColor);
		
		
		//CardLayout
		//gameMenuCard.setBounds(Reihe1, Diagonal2, Diagonal1, Alle);
		gameMenuCard.add(MainPanel, "nameMainPanel");
		gameMenuCard.add(menuPanel, "nameMenuPanel");
		frame.add(gameMenuCard, BorderLayout.CENTER);
		
		//Line 1 Design ?ndern, hell, dunkel
		custom.setEnabled(false);
		group1.add(light);
		group1.add(dark);
		group1.add(custom);
		Line1.setForeground(MenuFontColor);
		Line1.setFont(myFont);
		
		light.setForeground(MenuFontColor);
		light.setBackground(MenuColor);
		light.setFont(myFont);
		light.setFocusable(false);
		light.addActionListener(this);
		light.setHorizontalAlignment(SwingConstants.CENTER);
		light.setBorderPainted(false);
		
		dark.setForeground(MenuFontColor);
		dark.setBackground(MenuColor);
		dark.setFont(myFont);
		dark.setFocusable(false);
		dark.addActionListener(this);
		dark.setHorizontalAlignment(SwingConstants.CENTER);
		dark.setBorderPainted(false);
		
		custom.setForeground(MenuFontColor);
		custom.setBackground(MenuColor);
		custom.setFont(myFont);
		custom.setFocusable(false);
		custom.addActionListener(this);
		custom.setHorizontalAlignment(SwingConstants.CENTER);
		custom.setBorderPainted(false);
		
		menuLine1.add(Line1);
		menuLine1.add(light);
		menuLine1.add(dark);
		menuLine1.add(custom);
		
		//Line 2 Schwierigkeitsgrad ?ndern
		group2.add(easy);
		group2.add(medium);
		group2.add(hard);
		group2.add(extreme);
		group2.add(Player);
		Line2.setForeground(MenuFontColor);
		Line2.setFont(myFont);
		
		easy.setForeground(MenuFontColor);
		easy.setBackground(MenuColor);
		easy.setFont(myFont);
		easy.setFocusable(false);
		easy.addActionListener(this);
		easy.setHorizontalAlignment(SwingConstants.CENTER);
		easy.setBorderPainted(false);
		
		medium.setForeground(MenuFontColor);
		medium.setBackground(MenuColor);
		medium.setFont(myFont);
		medium.setFocusable(false);
		medium.addActionListener(this);
		medium.setHorizontalAlignment(SwingConstants.CENTER);
		medium.setBorderPainted(false);
		
		
		hard.setForeground(MenuFontColor);
		hard.setBackground(MenuColor);
		hard.setFont(myFont);
		hard.setFocusable(false);
		hard.addActionListener(this);
		hard.setHorizontalAlignment(SwingConstants.CENTER);
		hard.setBorderPainted(false);
		
		extreme.setForeground(MenuFontColor);
		extreme.setBackground(MenuColor);
		extreme.setFont(myFont);
		extreme.setFocusable(false);
		extreme.addActionListener(this);
		extreme.setHorizontalAlignment(SwingConstants.CENTER);
		extreme.setBorderPainted(false);
		
		Player.setForeground(MenuFontColor);
		Player.setBackground(MenuColor);
		Player.setFont(myFont);
		Player.setFocusable(false);
		Player.addActionListener(this);
		Player.setHorizontalAlignment(SwingConstants.CENTER);
		Player.setBorderPainted(false);
		
		menuLine2.add(Line2);
		menuLine2.add(easy);
		menuLine2.add(medium);
		menuLine2.add(hard);
		menuLine2.add(extreme);
		menuLine2.add(Player);

		//Line 3 Text zum Anzeigen welche Farbe wo angwewandt wird
		Playercolor.setForeground(MenuFontColor);
		Playercolor.setFont(myFont);
		
		Botcolor.setForeground(MenuFontColor);
		Botcolor.setFont(myFont);
		
		Buttoncolor.setForeground(MenuFontColor);
		Buttoncolor.setFont(myFont);
		
		Gridcolor.setForeground(MenuFontColor);
		Gridcolor.setFont(myFont);
		
		menuLine3.add(Playercolor);
		menuLine3.add(Botcolor);
		menuLine3.add(Buttoncolor);
		menuLine3.add(Gridcolor);
		
		//Line 4 Panels zum Zuordnen der Farben
		PlayerColorDropTarget.setBackground(new Color(0,0,12));
		PlayerColorDropTarget.setFocusable(false);
		
		BotColorDropTarget.setBackground(new Color(0,0,12));
		BotColorDropTarget.setFocusable(false);
		
		ButtonColorDropTarget.setBackground(new Color(0,0,12));
		ButtonColorDropTarget.setFocusable(false);
		
		GridColorDropTarget.setBackground(new Color(0,0,12));
		GridColorDropTarget.setFocusable(false);
		
		menuLine4.add(PlayerColorDropTarget);
		menuLine4.add(BotColorDropTarget);
		menuLine4.add(ButtonColorDropTarget);
		menuLine4.add(GridColorDropTarget);
		
		//Line 5 Panels? mit Farben zum Freischalten
		
		
		
		//Line 6 Buttons: Quit Game (Main Menu)
		QuitGame.setForeground(MenuFontColor);
		QuitGame.setBackground(new Color(58, 58, 60));
		QuitGame.setFont(myFont);
		QuitGame.setFocusable(false);
		QuitGame.addActionListener(this);
		//QuitGame.setHorizontalAlignment(SwingConstants.CENTER);
		//QuitGame.setBorderPainted(false);
		
		menuLine6.setBorder(new EmptyBorder(0,230,0,230));// ?ndern wenn button main menu hinzu kommt!
		menuLine6.add(QuitGame);
		
		
		topRight.setLayout(new GridLayout(2, 1, 8, 8));
		topRight.setBackground(new Color(58, 58, 60));
		topRight.setBorder(new EmptyBorder(10, 0, 10, 5));
		header.add(topRight, BorderLayout.EAST);
		
		MenuButton.addActionListener(this);
		MenuButton.setFocusable(false);
		MenuButton.setBackground(new Color(58, 58, 60));
		MenuButton.setBorder(new EmptyBorder(10, 10, 10, 10));
		MenuButton.setFont(myFont);
		MenuButton.setBorderPainted(false);
		MenuButton.setPreferredSize(new Dimension(50, 50));
		topRight.add(MenuButton, BorderLayout.EAST);
		
		restartButton.addActionListener(this);
		restartButton.setFocusable(false);
		restartButton.setBackground(new Color(58, 58, 60));
		//restartButton.setBackground(new Color(44, 44, 46));
		restartButton.setForeground(Color.LIGHT_GRAY);
		restartButton.setFont(myFont);
		 restartButton.setBorderPainted(false);
		restartButton.setPreferredSize(new Dimension(120, 50));
		topRight.add(restartButton, BorderLayout.EAST);
	
	}
	
	private void addGameButtons() {
		for(int i=0;i<9;i++) {
			AllGameButtons[i] = new JButton();
			AllGameButtons[i].setFont(new Font("Kalam", Font.CENTER_BASELINE, 100));
			AllGameButtons[i].setFocusable(false);
			AllGameButtons[i].addActionListener(this);
			AllGameButtons[i].setBackground(ButtonColor);
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
			label.setText(TurnLabel1);
		}
		else {
			playersTurn = false;
			label.setText(TurnLabel2);
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
	
	private void fillMenuPanel() {
		
		menuPanel.add(menuLine1);
		menuPanel.add(menuLine2);
		menuPanel.add(menuLine3);
		menuPanel.add(menuLine4);
		menuPanel.add(menuLine5);
		menuPanel.add(menuLine6);
		
	}
	
	private void createAndWriteFile() {
		
		//configFile = new File("config.txt");
	    if (configFile.exists()) {
	    	configFile.delete();
	    }
	    
	    try {
			configFile.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}

	    //Write:
	    writeConfigFile();
	    
	    // set to read-only, canWrite = false */
	    configFile.setWritable(false);
	  }
	
	/*
	private void createConfigFile() {
		  try {
		      File configFile = new File("config.txt");
		      if (configFile.createNewFile()) {
		        System.out.println("File created: " + configFile.getName());     
		      } else {
		        System.out.println("File already exists.");
		      }
		    } catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
	}
	*/
	private void writeConfigFile() {
		
	    try {
	        FileWriter myWriter = new FileWriter("config.txt");
	        
	       // myWriter.write("FrameSize: " + "\n"); //lieber immer 
	       // myWriter.write("Design = " + "\n");  //Speicher eh Button und GridCOlor -> Design also unn?tig
	        myWriter.write("Difficulty = " + Difficulty+"\n");
	        myWriter.write("PlayerColor = " + String.valueOf(PlayerColor.getRGB()) + "\n");
	        myWriter.write("BotColor = " + String.valueOf(BotColor.getRGB()) + "\n");
	        myWriter.write("ButtonColor = " + String.valueOf(AllGameButtons[1].getBackground().getRGB()) + "\n");
	        myWriter.write("GridColor = " + String.valueOf(MainPanel.getBackground().getRGB()) + "\n");
	        //myWriter.write("Coins: "); kann SPieler in config ?ndern! --> unendlich Coins!!
	        
	        myWriter.close();
	      //  System.out.println("Successfully wrote to the file.");
	      } catch (IOException e) {
	        System.out.println("An error occurred.");
	        e.printStackTrace();
	      }
		
	}
	
	
	private void readConfigFile() {
		
		try (FileInputStream fis = new FileInputStream(fileName)) {
		    prop.load(fis);
		    
		
		    try {
		    	
		    int intPlayerColor = Integer.parseInt(prop.getProperty("PlayerColor"));
			savedPlayerColor = new Color (intPlayerColor);
			
			int intBotColor = Integer.parseInt(prop.getProperty("BotColor"));
			savedBotColor = new Color (intBotColor);
			
		    int intButtonColor = Integer.parseInt(prop.getProperty("ButtonColor"));
			savedButtonColor = new Color (intButtonColor);
			
			int intGridColor = Integer.parseInt(prop.getProperty("GridColor"));
			savedGridColor = new Color (intGridColor);
			
			
			PlayerColor = savedPlayerColor;
			BotColor = savedBotColor;
			
			for(int i = 0; i<9; i++) {
				AllGameButtons[i].setBackground(savedButtonColor);
			}
			MainPanel.setBackground(savedGridColor);
			
			savedDifficulty = prop.getProperty("Difficulty");
			savedDifficulty.trim(); 
			System.out.println(savedDifficulty);
			
			if(savedDifficulty.equals("easy") ) {
				
				//medium.setSelected(false);
				//easy.setSelected(true);
				
				EasyMode = true;
			}
			if(savedDifficulty.equals("medium")) {
				//medium.setSelected(true);
				MediumMode = true;
			}
			if(savedDifficulty.equals("hard")) {
				//hard.setSelected(true);
				HardMode = true;
			}
			if(savedDifficulty.equals("extreme")) {
				//hardMode an statt extreme, weil extreme auf Zeit ist und nach Start soll Zeit noch nicht ablaufen?! Oder Zeit l?uft erst ab Spielers 1. Zug
				//hard.setSelected(true);
				ExtremeMode = true;
			}
			if(savedDifficulty.equals("localCoop")) {
				//Player.setSelected(true);
				localCoop = true;
				System.out.println("set LocalCoop");
			}
			
		    }
		    catch(Exception e) {
		    	System.out.println(e);
		    }
		    
		    
		    
		    
		    readConfigSuccess = true;
		} catch (FileNotFoundException ex) {
			readConfigSuccess = false;
		} catch (IOException ex) {
		    
		}

		/*
		System.out.println(prop.getProperty("Difficulty"));
		System.out.println(prop.getProperty("PlayerColor"));
		System.out.println(prop.getProperty("BotColor"));
		System.out.println(prop.getProperty("ButtonColor"));
		System.out.println(prop.getProperty("GridColor"));
		*/
		
		/* read with Scanner... ich benutz lieber FileInputStream
		try {
		      //File myObj = new File("filename.txt");
		      Scanner myReader = new Scanner(configFile);
		      while (myReader.hasNextLine()) {
		        String data = myReader.nextLine();
		        System.out.println(data);
		      }
		      myReader.close();
		    } catch (FileNotFoundException e) {
		      System.out.println("Can't read! File not existing!");
		     // e.printStackTrace();
		    }	
		    */
	}
	
	
	
	
	
	private void setWinnerButtonsColor() {
		
		if(Reihe1 == 3 || Reihe1 == 30) {
			AllGameButtons[0].setBackground(WinnerButtonColor);
			AllGameButtons[1].setBackground(WinnerButtonColor);
			AllGameButtons[2].setBackground(WinnerButtonColor);
			
			AllGameButtons[0].setForeground(WinnerFontColor);
			AllGameButtons[1].setForeground(WinnerFontColor);
			AllGameButtons[2].setForeground(WinnerFontColor);
		}
		if(Reihe2 == 3 || Reihe2 == 30) {
			AllGameButtons[3].setBackground(WinnerButtonColor);
			AllGameButtons[4].setBackground(WinnerButtonColor);
			AllGameButtons[5].setBackground(WinnerButtonColor);
			
			AllGameButtons[3].setForeground(WinnerFontColor);
			AllGameButtons[4].setForeground(WinnerFontColor);
			AllGameButtons[5].setForeground(WinnerFontColor);
		}
		if(Reihe3 == 3 || Reihe3 == 30) {
			AllGameButtons[6].setBackground(WinnerButtonColor);
			AllGameButtons[7].setBackground(WinnerButtonColor);
			AllGameButtons[8].setBackground(WinnerButtonColor);
			
			AllGameButtons[6].setForeground(WinnerFontColor);
			AllGameButtons[7].setForeground(WinnerFontColor);
			AllGameButtons[8].setForeground(WinnerFontColor);
		}
		if(Spalte1 == 3 || Spalte1 == 30) {
			AllGameButtons[0].setBackground(WinnerButtonColor);
			AllGameButtons[3].setBackground(WinnerButtonColor);
			AllGameButtons[6].setBackground(WinnerButtonColor);
			
			AllGameButtons[0].setForeground(WinnerFontColor);
			AllGameButtons[3].setForeground(WinnerFontColor);
			AllGameButtons[6].setForeground(WinnerFontColor);
		}
		if(Spalte2 == 3 || Spalte2 == 30) {
			AllGameButtons[1].setBackground(WinnerButtonColor);
			AllGameButtons[4].setBackground(WinnerButtonColor);
			AllGameButtons[7].setBackground(WinnerButtonColor);
			
			AllGameButtons[1].setForeground(WinnerFontColor);
			AllGameButtons[4].setForeground(WinnerFontColor);
			AllGameButtons[7].setForeground(WinnerFontColor);
		}
		if(Spalte3 == 3 || Spalte3 == 30) {
			AllGameButtons[2].setBackground(WinnerButtonColor);
			AllGameButtons[5].setBackground(WinnerButtonColor);
			AllGameButtons[8].setBackground(WinnerButtonColor);
			
			AllGameButtons[2].setForeground(WinnerFontColor);
			AllGameButtons[5].setForeground(WinnerFontColor);
			AllGameButtons[8].setForeground(WinnerFontColor);
		}
		if(Diagonal1 == 3 || Diagonal1 == 30) {
			AllGameButtons[0].setBackground(WinnerButtonColor);
			AllGameButtons[4].setBackground(WinnerButtonColor);
			AllGameButtons[8].setBackground(WinnerButtonColor);
			
			AllGameButtons[0].setForeground(WinnerFontColor);
			AllGameButtons[4].setForeground(WinnerFontColor);
			AllGameButtons[8].setForeground(WinnerFontColor);
		}
		if(Diagonal2 == 3 || Diagonal2 == 30) {
			AllGameButtons[2].setBackground(WinnerButtonColor);
			AllGameButtons[4].setBackground(WinnerButtonColor);
			AllGameButtons[6].setBackground(WinnerButtonColor);
			
			AllGameButtons[2].setForeground(WinnerFontColor);
			AllGameButtons[4].setForeground(WinnerFontColor);
			AllGameButtons[6].setForeground(WinnerFontColor);
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
		/*
		System.out.println(Reihe1);
		System.out.println(Reihe2);
		System.out.println(Reihe3);
		
		System.out.println(Spalte1);
		System.out.println(Spalte2);
		System.out.println(Spalte3);
		
		System.out.println(Diagonal1);
		System.out.println(Diagonal2);
		
		*/
		
		//-------Win or loose--------------
		if(Reihe1 == 3 || Reihe2 == 3 || Reihe3 == 3 || Spalte1 == 3 || Spalte2 == 3 || Spalte3 == 3 || Diagonal1 == 3 || Diagonal2 == 3) {	
			label.setText(Owon);
			setWinnerButtonsColor();
			playersTurn = true;
			stopBot = true;
			
			for(int i=0; i<9; i++) {
				if(AllGameButtons[i].getText()=="")
				AllGameButtons[i].setText(" ");
			}
			
			//greenEnd();
			//stopGame = true;
		}
		else if(Reihe1 == 30 || Reihe2 == 30 || Reihe3 == 30 || Spalte1 == 30 || Spalte2 == 30 || Spalte3 == 30 || Diagonal1 == 30 || Diagonal2 == 30) {
			label.setText(Xwon);
			setWinnerButtonsColor();
			playersTurn = true;
			stopBot = true;

			for(int i=0; i<9; i++) {
				if(AllGameButtons[i].getText()=="")
				AllGameButtons[i].setText(" ");
			}
			//greenEnd();
			//stopGame = true;
		}
		else if(Alle == 45 || Alle == 54) {
			playersTurn = true;
			stopBot = true;
			setWinnerButtonsColor();
			label.setText(Draw);

			//stopGame = true;
		}
		
	}
	
	private void botEasyAI() {
		
		int defChance =  (int)(Math.random() * 2); // 1 = 100%, 2 = 50%, 3 = 33%, 4 = 25%, ...
		
		boolean WinOrDef;
		
		int WoD = (int)(Math.random() * 3); // win/def: 2 = 50/50, 3 = 66/33, 4 = 75/25
		if(WoD == 0) {
			//Def
			WinOrDef = false;
		}
		else {
			//Win
			WinOrDef = true;
		}
		
		setReiheSpalteDiagonale();
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
if(BotSymbol == "X") {
			
			//-----------------------------GEWINNEN-------------------------------------
			if((Reihe1==20 || Reihe2==20 || Reihe3==20 || Spalte1==20 || Spalte2==20 || Spalte3==20 || Diagonal1==20 || Diagonal2 == 20 && WinOrDef)) {
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
				if(defChance == 0 || !WinOrDef) {
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
				}
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
						System.out.println("Irgendwas dr?cken");
					}
					else {
						randomNumber = (int)(Math.random() * 9);
					}
				}
			}
		}
	}
else {
	if((Reihe1==2 || Reihe2==2 || Reihe3==2 || Spalte1==2 || Spalte2==2 || Spalte3==2 || Diagonal1==2 || Diagonal2 == 2) && WinOrDef) {
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
		if(defChance == 0 || !WinOrDef) {
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
	}
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
				System.out.println("Irgendwas dr?cken");
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
	
		
	private void botMediumAI() {
		

		
		
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
							System.out.println("Irgendwas dr?cken");
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
							System.out.println("Irgendwas dr?cken");
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
	
private void botHardAI() {
			
		setReiheSpalteDiagonale();

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
							System.out.println("Irgendwas dr?cken");
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
							System.out.println("Irgendwas dr?cken");
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
	
	private void BotExtreme() {
		
		// Zeit / Timer hinzuf?gen
		
	}
	
	private void restartGame() {
		
		press1=false;
		press2=false;
		press3=false;
		press4=false;
		press5=false;
		press6=false;
		press7=false;
		press8=false;
		press9=false;
		
		getSymbol();
		firstTurn();

		if(darkModeOn) {
			setDarkMode();
		}
		else if(lightModeOn) {
			setLightMode();
		}
		else if(customModeOn) {
			
			//Use custom Colors
			//Load custom colors from config.txt at gamestart and set them, then use them here
		}
		else {
			setDarkMode();
		}
		
		
		for(int i=0; i<10; i++) {
			winner.set(i, 0);
		}
		for(int r = 0; r<9; r++) {
			//AllGameButtons[r].setBackground(ButtonColor);
			AllGameButtons[r].setText("");
			AllGameButtons[r].setEnabled(true);
		}
		//setReiheSpalteDiagonale();
		//System.out.println(Alle);
		//System.out.println(winner);
		blockReset = true;
		stopBot = false;
		gameOver = false;

		
	}
	
	private void setDarkMode() {
		
		for(int i=0; i<9; i++) {
			AllGameButtons[i].setBackground(ButtonColor);
		}
		MainPanel.setBackground(GridColor);
		
	}
	
	private void setLightMode() {
		
		for(int i=0; i<9; i++) {
			AllGameButtons[i].setBackground(new Color(245, 245, 245));		
		}
		MainPanel.setBackground(ButtonColor);
		
	}
	
	
	private void getTemporaryLabel() {
		
		temporaryLabel = label.getText();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		
		if(blockReset) {
			
			if(e.getSource()==ColorButtons[0]) {
				PlayerColor = ColorButtons[0].getBackground();
			}
			else if(e.getSource()==ColorButtons[1]) {
				PlayerColor = ColorButtons[1].getBackground();
			}
			else if(e.getSource()==ColorButtons[2]) {
				PlayerColor = ColorButtons[2].getBackground();
			}
			else if(e.getSource()==ColorButtons[3]) {
				PlayerColor = ColorButtons[3].getBackground();
			}
			else if(e.getSource()==ColorButtons[4]) {
				PlayerColor = ColorButtons[4].getBackground();
			}
			else if(e.getSource()==ColorButtons[5]) {
				PlayerColor = ColorButtons[5].getBackground();
			}
			
			
		}
		
		//Deisgn
		
		if(e.getSource()==light) {
			customModeOn = false;
			darkModeOn = false;
			lightModeOn = true;
			
			setLightMode();
			System.out.println("light mode");
		}
		
		if(e.getSource()==dark) {
			customModeOn = false;
			lightModeOn = false;
			darkModeOn = true;
			
			setDarkMode();
			System.out.println("dark mode");
		}
		
		
		if(e.getSource()==QuitGame) {
			
			frame.dispose();
			
		}
		
		
		if(e.getSource()==easy) {
			
			MediumMode = false;
			HardMode = false;
			ExtremeMode = false;
			localCoop = false;
			EasyMode = true;
			
			switchedMode = true;
			
			TurnLabel1 = defaultLabelPlayer;
			TurnLabel2 = defaultLabelBot;
			

			
			
		}
		if(e.getSource()==medium) {
			
			EasyMode = false;			
			HardMode = false;
			ExtremeMode = false;
			localCoop = false;
			MediumMode = true;
			
			switchedMode = true;
			
			TurnLabel1 = defaultLabelPlayer;
			TurnLabel2 = defaultLabelBot;
			
			
		}
		if(e.getSource()==hard) {
			
			EasyMode = false;
			MediumMode = false;
			ExtremeMode = false;
			localCoop = false;
			HardMode = true;
			
			switchedMode = true;
			
			TurnLabel1 = defaultLabelPlayer;
			TurnLabel2 = defaultLabelBot;
						
		}
		if(e.getSource()==extreme) {
			
			EasyMode = false;
			MediumMode = false;
			HardMode = false;
			localCoop = false;
			ExtremeMode = true;
			
			switchedMode = true;
			
			TurnLabel1 = defaultLabelPlayer;
			TurnLabel2 = defaultLabelBot;
			
			
		}
		if(e.getSource()==Player) {
			
			EasyMode = false;
			MediumMode = false;
			HardMode = false;
			ExtremeMode = false;
			localCoop = true;
			
			switchedMode = true;
			//System.out.println("localCoop");
			
			TurnLabel1 = TurnLabelCoop1;
			TurnLabel2 = TurnLabelCoop2;
			
		}
		
		
		
		if(e.getSource()==MenuButton  && label.getText() != defaultLabelBot) {
				
			
			
			if(inMenu){
				
				System.out.println("close Menu");
				if(draw) {
					label.setText(Draw);
					draw = false;
				}
				else if(Xwin) {
					label.setText(Xwon);
					Xwin = false;
				}
				else if(Owin) {
					label.setText(Owon);
					Owin = false;
				}
				else {
				
				label.setText(temporaryLabel);
				}
				
				if(switchedMode) {
					
					restartGame();
					
				}
				if(darkModeOn) {
					
					setDarkMode();
					
				}
				
				CardLayout CardLayout1 = (CardLayout)(gameMenuCard.getLayout());
				CardLayout1.show(gameMenuCard,"nameMainPanel");
				//frame.remove(menuPanel);
				//MainPanel.setVisible(true);
				inMenu = false;
				switchedMode = false;
			}
			else {
	
				getTemporaryLabel();
				
				System.out.println("open Menu");
				if(label.getText()==Draw) {
					draw = true;
				}
				else if(label.getText()==Xwon) {
					Xwin = true;
				}
				else if(label.getText()==Owon) {
					Owin = true;
				}
				
				label.setText("Menu");
				
				CardLayout CardLayout1 = (CardLayout)(gameMenuCard.getLayout());
				CardLayout1.show(gameMenuCard,"nameMenuPanel");
				
				//MainPanel.setVisible(false);
				//frame.add(menuPanel);
				//fillMenuPanel();
				//
				
				inMenu = true;
			}
			
		}
		
		
		if(e.getSource()==restartButton && !blockReset && !inMenu) {
			//System.out.println("restart");
			
			restartGame();
			
		}
		
		
		
		if(playersTurn) {
			
			if(e.getSource()==AllGameButtons[0] && AllGameButtons[0].getText()=="") {

				
					System.out.println("Button 1 Player");
					AllGameButtons[0].setForeground(PlayerColor);
					AllGameButtons[0].setText(PlayerSymbol);

					blockReset = false;
					
					fillArray();
					win();
					
					if(stopBot == false) {
						label.setText(TurnLabel2);
						playersTurn = false;	
						

					}
			}
			else if(e.getSource()==AllGameButtons[1] && AllGameButtons[1].getText()=="") {
				
				
					System.out.println("Button 2 Player");
					AllGameButtons[1].setForeground(PlayerColor);
					AllGameButtons[1].setText(PlayerSymbol);

					blockReset = false;
					
					fillArray();
					win();
					
					if(stopBot == false) {
						label.setText(TurnLabel2);
						playersTurn = false;	

					}
			}
			else if(e.getSource()==AllGameButtons[2] && AllGameButtons[2].getText()=="") {
				
				
					System.out.println("Button 3 Player");
					AllGameButtons[2].setForeground(PlayerColor);
					AllGameButtons[2].setText(PlayerSymbol);

					blockReset = false;
					
					fillArray();
					win();
					
					if(stopBot == false) {
						label.setText(TurnLabel2);
						playersTurn = false;	
						
	
					}
			}		
			else if(e.getSource()==AllGameButtons[3] && AllGameButtons[3].getText()=="") {
				
				
					System.out.println("Button 4 Player");
					AllGameButtons[3].setForeground(PlayerColor);
					AllGameButtons[3].setText(PlayerSymbol);

					blockReset = false;
					
					fillArray();
					win();
					
					if(stopBot == false) {
						label.setText(TurnLabel2);
						playersTurn = false;	
						
					}
				}
			else if(e.getSource()==AllGameButtons[4] && AllGameButtons[4].getText()=="") {
				
				
					System.out.println("Button 5 Player");
					AllGameButtons[4].setForeground(PlayerColor);
					AllGameButtons[4].setText(PlayerSymbol);

					blockReset = false;
					
					fillArray();
					win();
					
					if(stopBot == false) {
						label.setText(TurnLabel2);
						playersTurn = false;	

					}
			}
			else if(e.getSource()==AllGameButtons[5] && AllGameButtons[5].getText()=="") {
				
				
					System.out.println("Button 6 Player");
					AllGameButtons[5].setForeground(PlayerColor);
					AllGameButtons[5].setText(PlayerSymbol);

					blockReset = false;
					
					fillArray();
					win();
					
					if(stopBot == false) {
						label.setText(TurnLabel2);
						playersTurn = false;	

					}
			}
			else if(e.getSource()==AllGameButtons[6] && AllGameButtons[6].getText()=="") {
				
				
					System.out.println("Button 7 Player");
					AllGameButtons[6].setForeground(PlayerColor);
					AllGameButtons[6].setText(PlayerSymbol);

					blockReset = false;
					
					fillArray();
					win();
					
					if(stopBot == false) {
						label.setText(TurnLabel2);
						playersTurn = false;	

					}
			}
			else if(e.getSource()==AllGameButtons[7] && AllGameButtons[7].getText()=="") {
				
				
					System.out.println("Button 8 Player");
					AllGameButtons[7].setForeground(PlayerColor);
					AllGameButtons[7].setText(PlayerSymbol);
					
					blockReset = false;
					
					fillArray();
					win();
					
					if(stopBot == false) {
						label.setText(TurnLabel2);
						playersTurn = false;	

					}
			}
			else if(e.getSource()==AllGameButtons[8] && AllGameButtons[8].getText()=="") {
				
				
					System.out.println("Button 9 Player");
					AllGameButtons[8].setForeground(PlayerColor);
					AllGameButtons[8].setText(PlayerSymbol);

					blockReset = false;
					
					fillArray();
					win();
					
					if(stopBot == false) {
						label.setText(TurnLabel2);
						playersTurn = false;	
						

					}
			}

			
			
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
		else if(!playersTurn){
			
			//UIManager.getDefaults().put("Button.disabledText", BotColor);
			
			if((press1 && AllGameButtons[0].getText() == "") || localCoop && e.getSource()==AllGameButtons[0] && AllGameButtons[0].getText()=="") {
					System.out.println("Button 1 Bot");
					AllGameButtons[0].setForeground(BotColor);
					AllGameButtons[0].setText(BotSymbol);
					
					fillArray();
					
					label.setText(TurnLabel1);
					playersTurn = true;	
					press1 = false;
					win();

			}
			else if((press2 && AllGameButtons[1].getText() == "") || localCoop && e.getSource()==AllGameButtons[1] && AllGameButtons[1].getText()=="") {
					System.out.println("Button 2 Bot");
					AllGameButtons[1].setForeground(BotColor);
					AllGameButtons[1].setText(BotSymbol);
					fillArray();
					
					label.setText(TurnLabel1);
					playersTurn = true;
					press2 = false;
					win();

			}
			else if((press3 && AllGameButtons[2].getText() == "") || localCoop && e.getSource()==AllGameButtons[2] && AllGameButtons[2].getText()=="") {
					System.out.println("Button 3 Bot");
					AllGameButtons[2].setForeground(BotColor);
					AllGameButtons[2].setText(BotSymbol);
					fillArray();
					
					label.setText(TurnLabel1);
					playersTurn = true;
					press3 = false;
					win();

			}		
			else if((press4 && AllGameButtons[3].getText() == "") || localCoop && e.getSource()==AllGameButtons[3] && AllGameButtons[3].getText()=="") {
					System.out.println("Button 4 Bot");
					AllGameButtons[3].setForeground(BotColor);
					AllGameButtons[3].setText(BotSymbol);
					fillArray();
					
					label.setText(TurnLabel1);
					playersTurn = true;
					press4 = false;
					win();
			}
			else if((press5 && AllGameButtons[4].getText() == "") || localCoop && e.getSource()==AllGameButtons[4] && AllGameButtons[4].getText()=="") {
					System.out.println("Button 5 Bot");
					AllGameButtons[4].setForeground(BotColor);
					AllGameButtons[4].setText(BotSymbol);
					fillArray();
					
					label.setText(TurnLabel1);
					playersTurn = true;
					press5 = false;
					win();

			}
			else if((press6 && AllGameButtons[5].getText() == "") || localCoop && e.getSource()==AllGameButtons[5] && AllGameButtons[5].getText()=="") {
					System.out.println("Button 6 Bot");
					AllGameButtons[5].setForeground(BotColor);
					AllGameButtons[5].setText(BotSymbol);
					fillArray();
					
					label.setText(TurnLabel1);
					playersTurn = true;
					press6 = false;
					win();

			}
			else if((press7 && AllGameButtons[6].getText() == "") || localCoop && e.getSource()==AllGameButtons[6] && AllGameButtons[6].getText()=="") {
					System.out.println("Button 7 Bot");
					AllGameButtons[6].setForeground(BotColor);
					AllGameButtons[6].setText(BotSymbol);
					fillArray();
					
					label.setText(TurnLabel1);
					playersTurn = true;
					press7 = false;
					win();

			}
			else if((press8 && AllGameButtons[7].getText() == "") || localCoop && e.getSource()==AllGameButtons[7] && AllGameButtons[7].getText()=="") {
					System.out.println("Button 8 Bot");
					AllGameButtons[7].setForeground(BotColor);
					AllGameButtons[7].setText(BotSymbol);
					fillArray();
				
					label.setText(TurnLabel1);
					playersTurn = true;
					press8 = false;
					win();

			}
			else if((press9 && AllGameButtons[8].getText() == "") || localCoop && e.getSource()==AllGameButtons[8] && AllGameButtons[8].getText()=="") {
					System.out.println("Button 9 Bot");
					AllGameButtons[8].setForeground(BotColor);
					AllGameButtons[8].setText(BotSymbol);
					fillArray();
					
					label.setText(TurnLabel1);
					playersTurn = true;
					press9 = false;
					win();
			}
	

			
			//System.out.println(winner);
			

		}
		

		
	}
	
}

	
	
