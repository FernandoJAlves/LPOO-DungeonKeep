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

public class GraphicInterface extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField numOgres;
	private JComboBox<String> comboBox;
	private JLabel lblYouCanStart;
	private GameScreen gs = new GameScreen();
	private Game dk;
	

	/**
	 * Create the application.
	 */
	public GraphicInterface(Game dk) {
		super();
		this.dk = dk;
		initialize();
		setVisible(true);

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		this.setTitle("Dungeon Keep");
		this.setBounds(100, 100, 700, 600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Number of Ogres ");
		lblNewLabel.setBounds(33, 0, 120, 15);
		this.getContentPane().add(lblNewLabel);
		
		numOgres = new JTextField();
		numOgres.setBounds(182, -2, 29, 19);
		this.getContentPane().add(numOgres);
		numOgres.setColumns(10);
		
		JLabel lblGuardPersonality = new JLabel("Guard Personality");
		lblGuardPersonality.setBounds(33, 27, 146, 15);
		this.getContentPane().add(lblGuardPersonality);
		
		comboBox = new JComboBox<String>();
		comboBox.setToolTipText("");
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"Rookie", "Drunken", "Suspicious"}));
		comboBox.setSelectedIndex(0);
		comboBox.setBounds(179, 22, 120, 24);
		this.getContentPane().add(comboBox);
		
		JButton btnNewGame = new JButton("New Game");
		btnNewGame.setBounds(540, 60, 114, 25);
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
				 dk.initialize(1, num, comboBox.getSelectedIndex());
				 update('n');
				 
				 setKeyEvent();
			 }
			 }
			); 
		this.getContentPane().add(btnNewGame);
		
		JButton btnExit = new JButton("Exit");
		btnExit.setBounds(540, 485, 114, 25);
		btnExit.addActionListener( new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 System.exit(0);
			 }
			 }
			); 
		this.getContentPane().add(btnExit);
		
		JButton btnUp = new JButton("Up");
		btnUp.setBounds(568, 220, 78, 15);
		btnUp.addActionListener( new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 update('w');
				 
			 }
			 }
			); 
		this.getContentPane().add(btnUp);
		
		JButton btnDown = new JButton("Down");
		btnDown.setBounds(568, 302, 78, 15);
		btnDown.addActionListener( new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 update('s');
			 }
			 }
			); 
		this.getContentPane().add(btnDown);
		
		JButton btnLeft = new JButton("Left");
		btnLeft.setBounds(525, 262, 78, 15);
		btnLeft.addActionListener( new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 update('a');
			 }
			 }
			); 
		this.getContentPane().add(btnLeft);
		
		JButton btnRight = new JButton("Right");
		btnRight.setBounds(615, 262, 78, 15);
		btnRight.addActionListener( new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 update('d');
			 }
			 }
			); 
		this.getContentPane().add(btnRight);
		
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
		this.getContentPane().add(gs);
		
		lblYouCanStart = new JLabel("You can start a new game");
		lblYouCanStart.setBounds(33, 540, 434, 15);
		this.getContentPane().add(lblYouCanStart);
	}
	
	
	public void update(char c) {	
		
		if(dk.state == Game.Game_State.PREPARE) {
			return;
		}
		

		
		
		
		if(c != 'n') {
			dk.updateGame(c);
			dk.state = dk.collision();
		
		
		}
		
		//this.screenText.setText(this.dk.drawScreen());
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
	
	void setKeyEvent() {
		 this.setFocusable(false);
		 gs.setFocusable(true);
		 gs.requestFocusInWindow();
		
	}
		

}
