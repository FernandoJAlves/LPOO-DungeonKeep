package dkeep.gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class LevelEditor extends JFrame implements MouseListener{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1138281883081414944L;
	private char[][] char_map = new char[5][5];
	private GameScreen gs = new GameScreen();
	
	JLabel lblYouCanStart;
	JComboBox<String> comboBox;

	public LevelEditor() {
		super();
		this.initialize();
	}
	
	public void setEditor() {

		this.setVisible(true);
		this.displayMap();
		gs.setVisible(true);
		
	}
	
	public void initialize() {
		this.setTitle("Level Editor");
		this.setBounds(100, 100, 700, 600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(null);
		
		JLabel lblGuardPersonality = new JLabel("Keep Size");
		lblGuardPersonality.setBounds(33, 27, 146, 15);
		this.getContentPane().add(lblGuardPersonality);
		
		comboBox = new JComboBox<String>();
		comboBox.setToolTipText("");
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"5", "6", "7", "8", "9", "10"}));
		comboBox.setSelectedIndex(0);
		comboBox.setBounds(179, 22, 120, 24);
		this.getContentPane().add(comboBox);
		
		
		JButton btnCustomGame = new JButton("New Level");
		btnCustomGame.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 displayMap();
			 }
			 }
		);
		btnCustomGame.setBounds(540, 109, 140, 25);
		this.getContentPane().add(btnCustomGame);
		
		JButton btnExit = new JButton("Back to Menu");
		btnExit.setBounds(540, 485, 140, 25);
		btnExit.addActionListener( new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 setVisible(false);
				 DungeonKeep.getWindow().getFrame().setVisible(true);
				 comboBox.setSelectedIndex(0);
					
			 }
			 }
			); 
		this.getContentPane().add(btnExit);

		
		gs.setBounds(33, 54, 480, 480);
		gs.loadResources();
		this.displayMap();
		this.getContentPane().add(gs);
		
		lblYouCanStart = new JLabel("You can start a new game");
		lblYouCanStart.setBounds(33, 540, 434, 15);
		this.getContentPane().add(lblYouCanStart);
		
	}
	
	public void resize(int size) {
		this.char_map = new char[size][size];
		gs.setLayout(new GridLayout(size, size));
		gs.setBounds(33, 54, size*48, size*48);
		this.fillLevel();
	}
	
	public void fillLevel() {
		int last = this.char_map.length -1;
		for(int i = 0; i < this.char_map[0].length;i++ ) {
			this.char_map[0][i] = 'X';
			this.char_map[last][i] = 'X';
		}
		for(int i = 1; i < last;i++ ) {
			this.char_map[i][0] = 'X';
			this.char_map[i][last] = 'X';
		}
		for(int i = 1; i < last;i++) {
			for(int j = 1; j < last;j++) {
				char_map[i][j] = ' ';
			}
		}
	}
	
	public boolean allElems() {
		boolean o = false;
		boolean k = false;
		boolean h = false;
		boolean d = false;
		for(int i = 0; i < this.char_map.length;i++) {
			for(int j = 0; j < this.char_map[0].length;j++) {
				switch (char_map[i][j]) {
				case 'O':
					o = true;
					break;
				case 'k':
					k = true;
					break;
				case 'H':
					h = true;
					break;
				case 'I':
					d = true;
					break;
				}
			}
		}
		return o && k && h && d;
		
	}
	
	public void displayMap() {
		
		 resize(comboBox.getSelectedIndex() + 5);
		 gs.draw(char_map);
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
