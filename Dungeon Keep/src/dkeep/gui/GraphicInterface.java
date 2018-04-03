package dkeep.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import dkeep.logic.Game;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

public class GraphicInterface {

	private JFrame frmDK;
	private JTextField numOgres;
	private JComboBox<String> comboBox;
	private JLabel lblYouCanStart;
	private GameScreen gs = new GameScreen();
	private Game dk;
	private LevelEditor editor;
	

	/**
	 * Create the application.
	 */
	public GraphicInterface(Game dk) {
		this.frmDK = new JFrame();
		this.editor = new LevelEditor();
		this.dk = dk;
		initialize();
		frmDK.setVisible(true);

	}
	
	public JFrame getFrame() {
		return this.frmDK;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmDK.setTitle("Dungeon Keep");
		frmDK.setBounds(100, 100, 700, 600);
		frmDK.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmDK.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Number of Ogres ");
		lblNewLabel.setBounds(33, 0, 120, 15);
		frmDK.getContentPane().add(lblNewLabel);
		
		numOgres = new JTextField();
		numOgres.setBounds(182, -2, 29, 19);
		frmDK.getContentPane().add(numOgres);
		numOgres.setColumns(10);
		
		JLabel lblGuardPersonality = new JLabel("Guard Personality");
		lblGuardPersonality.setBounds(33, 27, 146, 15);
		frmDK.getContentPane().add(lblGuardPersonality);
		
		comboBox = new JComboBox<String>();
		comboBox.setToolTipText("");
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"Rookie", "Drunken", "Suspicious"}));
		comboBox.setSelectedIndex(0);
		comboBox.setBounds(179, 22, 120, 24);
		frmDK.getContentPane().add(comboBox);
		
		JButton btnNewGame = new JButton("New Game");
		btnNewGame.setBounds(540, 60, 140, 25);
		btnNewGame.addActionListener( new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 int num = 0;
				 try {
					    num = Integer.parseInt(numOgres.getText());
					} catch (NumberFormatException ex) {
					    return;
					}
				 if(num < 0 || num > 5) {
					 return;
				 }
				 lblYouCanStart.setText("You can play now.");
				 dk.initialize(1, num, comboBox.getSelectedIndex(),null);
				 begin();
			 }
			 }
			); 
		frmDK.getContentPane().add(btnNewGame);
		
		JButton btnCustomGame = new JButton("Custom Game");
		btnCustomGame.setBounds(540, 109, 140, 25);
		btnCustomGame.addActionListener( new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 frmDK.setVisible(false);
				 editor.setEditor();
				 gs.setVisible(false);
				 
			 }
			 }
			); 
		frmDK.getContentPane().add(btnCustomGame);
		
		JButton btnSave = new JButton("Save");
		btnSave.setBounds(540, 158, 140, 25);
		btnSave.addActionListener( new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 if(dk.getMap() != null) {
					 DungeonKeep.getDS().save(dk);
					 update('n');
				 }
				 setKeyEvent();
				 
			 }
			 }
			); 
		frmDK.getContentPane().add(btnSave);
		
		JButton btnLoad = new JButton("Load");
		btnLoad.setBounds(540, 207, 140, 25);
		btnLoad.addActionListener( new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 Game g = DungeonKeep.getDS().load();
				 if(g != null) {
					 dk = g;
					 lblYouCanStart.setText("You can play now.");
				 }
				 setKeyEvent();
				 update('n');
			 }
			 }
			); 
		frmDK.getContentPane().add(btnLoad);
		
		JButton btnExit = new JButton("Exit");
		btnExit.setBounds(553, 485, 114, 25);
		btnExit.addActionListener( new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 System.exit(0);
			 }
			 }
			); 
		frmDK.getContentPane().add(btnExit);
		
		JButton btnUp = new JButton("Up");
		btnUp.setBounds(568, 318, 78, 15);
		btnUp.addActionListener( new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 update('w');
				 setKeyEvent();
				 
			 }
			 }
			); 
		frmDK.getContentPane().add(btnUp);
		
		JButton btnDown = new JButton("Down");
		btnDown.setBounds(568, 395, 78, 15);
		btnDown.addActionListener( new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 update('s');
				 setKeyEvent();
			 }
			 }
			); 
		frmDK.getContentPane().add(btnDown);
		
		JButton btnLeft = new JButton("Left");
		btnLeft.setBounds(525, 359, 78, 15);
		btnLeft.addActionListener( new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 update('a');
				 setKeyEvent();
			 }
			 }
			); 
		frmDK.getContentPane().add(btnLeft);
		
		JButton btnRight = new JButton("Right");
		btnRight.setBounds(615, 359, 78, 15);
		btnRight.addActionListener( new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 update('d');
				 setKeyEvent();
			 }
			 }
			); 
		frmDK.getContentPane().add(btnRight);
		
		gs.setBounds(33, 54, 480, 480);
		gs.loadResources();

		gs.addKeyListener( new KeyListener() {

			@Override
			public void keyPressed(KeyEvent e) {
				switch(e.getKeyCode()){
				case KeyEvent.VK_LEFT: 
					update('a'); 
					break;
				case KeyEvent.VK_RIGHT:
					update('d');   
					break;
				case KeyEvent.VK_UP: 
					update('w');  
					break;
				case KeyEvent.VK_DOWN: 
					update('s'); 
					break;
				}
				
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		}
		);
		frmDK.getContentPane().add(gs);
		
		lblYouCanStart = new JLabel("You can start a new game");
		lblYouCanStart.setBounds(33, 540, 434, 15);
		frmDK.getContentPane().add(lblYouCanStart);
		

	}
	
	public void begin() {
		update('n');
		gs.setVisible(true);
		setKeyEvent();
		 
	}
	
	
	public void update(char c) {	
		
		if(dk.state == Game.Game_State.PREPARE) {
			return;
		}
		

		
		
		
		if(c != 'n') {
			dk.updateGame(c);
			dk.state = dk.collision();
		
		
		}
		
		gs.draw(this.dk.drawScreen());
		if(dk.state == Game.Game_State.LOSE) {
			lblYouCanStart.setText("Game Over! Try again!");
			dk.state = Game.Game_State.PREPARE;
			return;
		}
		else if(dk.state == Game.Game_State.WIN) {
			lblYouCanStart.setText("Congratulations! You Won!");
			dk.state = Game.Game_State.PREPARE;
			return;
		}

	}	
	
	public void setKeyEvent() {
		 frmDK.setFocusable(false);
		 gs.setFocusable(true);
		 gs.requestFocusInWindow();
		
	}
	
	public Game getGame() {
		return this.dk;
	}
	
	public void setLabelText(String text) {
		this.lblYouCanStart.setText(text);
	}
}
