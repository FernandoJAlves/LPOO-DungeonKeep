package dkeep.cli;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ContainerListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JTextArea;

public class GraphicInterface {

	private JFrame frmDungeonKeep;
	private JTextField numOgres;
	private char input = 't';

	/**
	 * Create the application.
	 */
	public GraphicInterface() {
		initialize();
		this.frmDungeonKeep.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmDungeonKeep = new JFrame();
		frmDungeonKeep.setTitle("Dungeon Keep");
		frmDungeonKeep.setBounds(100, 100, 666, 357);
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
		
		JComboBox comboBox = new JComboBox();
		comboBox.setToolTipText("");
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Rookie", "Drunken", "Suspicious"}));
		comboBox.setSelectedIndex(0);
		comboBox.setBounds(179, 22, 120, 24);
		frmDungeonKeep.getContentPane().add(comboBox);
		
		JButton btnNewGame = new JButton("New Game");
		btnNewGame.setBounds(540, 60, 114, 25);
		frmDungeonKeep.getContentPane().add(btnNewGame);
		
		JButton btnExit = new JButton("Exit");
		btnExit.setBounds(526, 279, 114, 25);
		frmDungeonKeep.getContentPane().add(btnExit);
		
		JButton btnUp = new JButton("Up");
		btnUp.setBounds(524, 142, 78, 15);
		btnUp.addActionListener( new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 input = 'w';
			 }
			 }
			); 
		frmDungeonKeep.getContentPane().add(btnUp);
		
		JButton btnDown = new JButton("Down");
		btnDown.setBounds(526, 196, 78, 15);
		btnDown.addActionListener( new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 input = 's';
			 }
			 }
			); 
		frmDungeonKeep.getContentPane().add(btnDown);
		
		JButton btnLeft = new JButton("Left");
		btnLeft.setBounds(474, 169, 78, 15);
		btnLeft.addActionListener( new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 input = 'a';
			 }
			 }
			); 
		frmDungeonKeep.getContentPane().add(btnLeft);
		
		JButton btnRight = new JButton("Right");
		btnRight.setBounds(576, 169, 78, 15);
		btnRight.addActionListener( new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 input = 'd';
			 }
			 }
			); 
		frmDungeonKeep.getContentPane().add(btnRight);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(33, 54, 429, 240);
		frmDungeonKeep.getContentPane().add(textArea);
		
		JLabel lblYouCanStart = new JLabel("You can start a new game");
		lblYouCanStart.setBounds(33, 302, 434, 15);
		frmDungeonKeep.getContentPane().add(lblYouCanStart);
	}
	
	public char read() {
		return this.input;
	}
}
