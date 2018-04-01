package dkeep.gui;

import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

import dkeep.logic.Game;

public class LevelEditor extends JFrame implements MouseListener{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1138281883081414944L;
	private char[][] char_map = new char[5][5];
	private GameScreen gs = new GameScreen();
	char icon = 0;
	
	boolean o = false;
	boolean k = false;
	boolean h = false;
	boolean d = false;
	
	JLabel lblYouCanStart;
	JComboBox<String> comboBox;

	public LevelEditor() {
		super();
		addMouseListener(this);
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
				 icon = 0;
			 }
			 }
		);
		btnCustomGame.setBounds(540, 109, 140, 25);
		this.getContentPane().add(btnCustomGame);
		
		JButton btnHero = new JButton("");
		btnHero.setIcon(this.loadIcon("res/Hero.png"));
		btnHero.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 icon = 'H';
			 }
			 }
		);
		btnHero.setBounds(540, 159, 50, 50);
		this.getContentPane().add(btnHero);
		
		JButton btnFloor = new JButton("");
		btnFloor.setIcon(this.loadIcon("res/Floor_prison.png"));
		btnFloor.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 icon = ' ';
			 }
			 }
		);
		btnFloor.setBounds(600, 159, 50, 50);
		this.getContentPane().add(btnFloor);
		
		JButton btnWall = new JButton("");
		btnWall.setIcon(this.loadIcon("res/Wall_prison.png"));
		btnWall.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 icon = 'X';
			 }
			 }
		);
		btnWall.setBounds(540, 219, 50, 50);
		this.getContentPane().add(btnWall);
		
		JButton btnKey = new JButton("");
		btnKey.setIcon(this.loadIcon("res/Key.png"));
		btnKey.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 icon = 'k';
			 }
			 }
		);
		btnKey.setBounds(600, 219, 50, 50);
		this.getContentPane().add(btnKey);
		
		JButton btnOgre = new JButton("");
		btnOgre.setIcon(this.loadIcon("res/Ogre.png"));
		btnOgre.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 icon = 'G';
			 }
			 }
		);
		btnOgre.setBounds(540, 279, 50, 50);
		this.getContentPane().add(btnOgre);
		
		JButton btnDoor = new JButton("");
		btnDoor.setIcon(this.loadIcon("res/Door_closed.png"));
		btnDoor.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 icon = 'I';
			 }
			 }
		);
		btnDoor.setBounds(600, 279, 50, 50);
		this.getContentPane().add(btnDoor);
		
		JButton btnDone = new JButton("Done");
		btnDone.setBounds(540, 435, 140, 25);
		btnDone.addActionListener( new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 DungeonKeep.getWindow().getGame().initialize(0,0,0,char_map);
				 exit();
				 DungeonKeep.getWindow().begin();
					
			 }
			 }
			); 
		this.getContentPane().add(btnDone);
		
		JButton btnExit = new JButton("Back to Menu");
		btnExit.setBounds(540, 485, 140, 25);
		btnExit.addActionListener( new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 exit();
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
	
	public ImageIcon loadIcon(String path) {
		ImageIcon im  = new ImageIcon(this.getClass().getResource(path));
		Image newimg = im.getImage().getScaledInstance(this.getWidth() / 10, this.getHeight() / 10, Image.SCALE_FAST);

		return new ImageIcon(newimg);
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

		return o && k && h && d;
		
	}
	
	public void displayMap() {
		
		 resize(comboBox.getSelectedIndex() + 5);
		 gs.draw(char_map);
	}
	
	public void exit() {
		this.icon = 0;
		 setVisible(false);
		 DungeonKeep.getWindow().getFrame().setVisible(true);
		 comboBox.setSelectedIndex(0);
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
		int size = this.char_map.length;
		int x = (int) Math.floor((arg0.getX()-33)/48);
		int y = (int) Math.floor((arg0.getY()-54)/48) -1;
		
	
		
		if(x >=1 && y>=1 && x<size-1 && y <size-1){
			switch (this.icon) {
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
			this.char_map[y][x] = this.icon;
			gs.draw(this.char_map);
		}
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
