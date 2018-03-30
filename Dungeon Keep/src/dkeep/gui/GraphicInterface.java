package dkeep.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import dkeep.logic.Game;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

public class GraphicInterface {

	private JFrame frmDungeonKeep;
	private JTextField numOgres;
	private JComboBox<String> comboBox;
	private JLabel lblYouCanStart;
	private GameScreen gs = new GameScreen();
	private Game dk;
	

	/**
	 * Create the application.
	 */
	public GraphicInterface(Game dk) {
		this.dk = dk;
		initialize();
		this.frmDungeonKeep.setVisible(true);
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmDungeonKeep = new JFrame();
		frmDungeonKeep.setTitle("Dungeon Keep");
		frmDungeonKeep.setBounds(100, 100, 700, 600);
		frmDungeonKeep.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmDungeonKeep.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Number of Ogres ");
		lblNewLabel.setBounds(33, 0, 120, 15);
		frmDungeonKeep.getContentPane().add(lblNewLabel);
		
		numOgres = new JTextField();
		numOgres.setBounds(182, -2, 29, 19);
		frmDungeonKeep.getContentPane().add(numOgres);
		numOgres.setColumns(10);
		
		JLabel lblGuardPersonality = new JLabel("Guard Personality");
		lblGuardPersonality.setBounds(33, 27, 146, 15);
		frmDungeonKeep.getContentPane().add(lblGuardPersonality);
		
		comboBox = new JComboBox<String>();
		comboBox.setToolTipText("");
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"Rookie", "Drunken", "Suspicious"}));
		comboBox.setSelectedIndex(0);
		comboBox.setBounds(179, 22, 120, 24);
		frmDungeonKeep.getContentPane().add(comboBox);
		
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
			 }
			 }
			); 
		frmDungeonKeep.getContentPane().add(btnNewGame);
		
		JButton btnExit = new JButton("Exit");
		btnExit.setBounds(540, 485, 114, 25);
		btnExit.addActionListener( new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 System.exit(0);
			 }
			 }
			); 
		frmDungeonKeep.getContentPane().add(btnExit);
		
		JButton btnUp = new JButton("Up");
		btnUp.setBounds(568, 220, 78, 15);
		btnUp.addActionListener( new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 update('w');
				 
			 }
			 }
			); 
		frmDungeonKeep.getContentPane().add(btnUp);
		
		JButton btnDown = new JButton("Down");
		btnDown.setBounds(568, 302, 78, 15);
		btnDown.addActionListener( new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 update('s');
			 }
			 }
			); 
		frmDungeonKeep.getContentPane().add(btnDown);
		
		JButton btnLeft = new JButton("Left");
		btnLeft.setBounds(525, 262, 78, 15);
		btnLeft.addActionListener( new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 update('a');
			 }
			 }
			); 
		frmDungeonKeep.getContentPane().add(btnLeft);
		
		JButton btnRight = new JButton("Right");
		btnRight.setBounds(615, 262, 78, 15);
		btnRight.addActionListener( new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 update('d');
			 }
			 }
			); 
		frmDungeonKeep.getContentPane().add(btnRight);
		
		gs.setBounds(33, 54, 480, 480);
		gs.loadResources();
		frmDungeonKeep.getContentPane().add(gs);
		
		lblYouCanStart = new JLabel("You can start a new game");
		lblYouCanStart.setBounds(33, 540, 434, 15);
		frmDungeonKeep.getContentPane().add(lblYouCanStart);
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
}
