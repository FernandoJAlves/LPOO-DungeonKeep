package dkeep.gui;


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

public class LevelEditor extends JFrame implements MouseListener{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1138281883081414944L;
	private char[][] char_map = new char[5][5];
	
	private GameScreen gs = new GameScreen();
	char icon = 0;
	
	boolean k = false;
	boolean h = false;
	
	JLabel lblGuardPersonality;
	JLabel editorLbl;
	JComboBox<String> comboBox;
	JButton btnCustomGame;
	JButton btnHero;
	JButton btnFloor;
	JButton btnWall;
	JButton btnKey;
	JButton btnOgre;
	JButton btnDoor;
	JButton btnDone;
	JButton btnExit;

	public LevelEditor() {
		super();
		addMouseListener(this);
		this.initialize();
	}
	
	public void setEditor() {
		this.initGraph();
		this.setVisible(true);		
	}
	
	public void initialize() {
		this.setEditorFrame();
		this.setGuardPersonalityLabel();
		this.setComboBox();
		this.setCustomGame();
		this.setElementsButtons();
		this.setDoneButton();
		this.setExitButton();
		this.setGameScreen();
		this.setEditorLabel();
		this.initGraph();
	}
	
	public void setEditorFrame() {
		this.setResizable(false);
		this.setTitle("Level Editor");
		this.setBounds(100, 100, 700, 600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(null);
	}
	
	public void setGuardPersonalityLabel() {
		lblGuardPersonality = new JLabel("Keep Size");
		lblGuardPersonality.setBounds(33, 27, 146, 15);
		this.getContentPane().add(lblGuardPersonality);
	}
	
	public void setComboBox() {
		comboBox = new JComboBox<String>();
		comboBox.setToolTipText("");
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"5", "6", "7", "8", "9", "10"}));
		comboBox.setSelectedIndex(0);
		comboBox.setBounds(179, 22, 120, 24);
		this.getContentPane().add(comboBox);
	}
	
	public void setCustomGame() {
		btnCustomGame = new JButton("New Level");
		btnCustomGame.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 displayMap();
				 icon = 0;
			 }
			 }
		);
		btnCustomGame.setBounds(540, 109, 140, 25);
		this.getContentPane().add(btnCustomGame);
	}
	
	public void setElementsButtons() {
		this.setHeroButton();
		this.setFloorButton();
		this.setWallButton();
		this.setKeyButton();
		this.setOgreButton();
		this.setDoorButton();
	}
	
	public void setHeroButton() {
		btnHero = new JButton("");
		btnHero.setIcon(this.loadIcon("res/Hero.png"));
		btnHero.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 icon = 'H';
			 }
			 }
		);
		btnHero.setBounds(540, 159, 50, 50);
		this.getContentPane().add(btnHero);
	}
	
	public void setFloorButton() {
		btnFloor = new JButton("");
		btnFloor.setIcon(this.loadIcon("res/Floor_prison.png"));
		btnFloor.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 icon = ' ';
			 }
			 }
		);
		btnFloor.setBounds(600, 159, 50, 50);
		this.getContentPane().add(btnFloor);
	}
	
	public void setWallButton() {
		btnWall = new JButton("");
		btnWall.setIcon(this.loadIcon("res/Wall_prison.png"));
		btnWall.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 icon = 'X';
			 }
			 }
		);
		btnWall.setBounds(540, 219, 50, 50);
		this.getContentPane().add(btnWall);
	}
	
	public void setKeyButton() {
		btnKey = new JButton("");
		btnKey.setIcon(this.loadIcon("res/Key.png"));
		btnKey.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 icon = 'k';
			 }
			 }
		);
		btnKey.setBounds(600, 219, 50, 50);
		this.getContentPane().add(btnKey);
	}
	
	public void setOgreButton() {
		btnOgre = new JButton("");
		btnOgre.setIcon(this.loadIcon("res/Ogre.png"));
		btnOgre.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 icon = 'O';
			 }
			 }
		);
		btnOgre.setBounds(540, 279, 50, 50);
		this.getContentPane().add(btnOgre);	
	}
	
	public void setDoorButton() {
		btnDoor = new JButton("");
		btnDoor.setIcon(this.loadIcon("res/Door_closed.png"));
		btnDoor.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 icon = 'I';
			 }
			 }
		);
		btnDoor.setBounds(600, 279, 50, 50);
		this.getContentPane().add(btnDoor);
	}
	
	public void setDoneButton() {
		btnDone = new JButton("Done");
		btnDone.setBounds(540, 435, 140, 25);
		this.setDoneListener();
		this.getContentPane().add(btnDone);
	}
	
	public void setDoneListener() {
		btnDone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (allElems()) {
					DungeonKeep.getWindow().getGame().initialize(0, 0, 0, char_map);
					exit();
					DungeonKeep.getWindow().begin();
					DungeonKeep.getWindow().setLabelText("You can play now.");
				}
				else {
					editorLbl.setText("You can't start until you have the required elements");
					gs.draw(char_map);
				}

			}
		});
	}
	
	public void setExitButton() {
		btnExit = new JButton("Back to Menu");
		btnExit.setBounds(540, 485, 140, 25);
		btnExit.addActionListener( new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 exit();
			 }
			 }
			); 
		this.getContentPane().add(btnExit);
	}
	
	public void setGameScreen() {
		gs.setBounds(33, 54, 480, 480);
		gs.loadResources();
		this.getContentPane().add(gs);
	}
	
	public void setEditorLabel() {
		editorLbl = new JLabel("Choose a size for your keep and fill it with at least one of each");
		editorLbl.setBounds(33, 540, 434, 15);
		this.getContentPane().add(editorLbl);
	}
	
	public ImageIcon loadIcon(String path) {
		ImageIcon im  = new ImageIcon(this.getClass().getResource(path));
		Image newimg = im.getImage().getScaledInstance(this.getWidth() / 10, this.getHeight() / 10, Image.SCALE_FAST);

		return new ImageIcon(newimg);
	}
	
	public void resize(int size) {
		this.char_map = new char[size][size];
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
		boolean w = false;
		boolean f = false;
		boolean o = false;
		boolean d = false;
		for(int i = 0;i < this.char_map.length;i++) {
			for(int j = 0; j < this.char_map[0].length;j++) {
				switch(this.char_map[i][j]) {
				case 'X':
					w = true;
					break;
				case ' ':
					f = true;
					break;
				case 'O':
					o = true;
					break;
				case 'I':
					d = true;
				}
			}
		}
		return this.h && this.k && w && f && o && d;
	}
	
	public void displayMap() {
		
		 resize(comboBox.getSelectedIndex() + 5);
		 gs.draw(char_map);
	}
	
	public void cleanPreviousIcon(char c) {
		for(int i = 0;i < this.char_map.length;i++) {
			for(int j = 0; j < this.char_map[0].length;j++) {
				if(c == this.char_map[i][j]) {
					this.char_map[i][j] = ' ';
				}
			}
		}
	}
	
	public void initGraph() {
		gs.setVisible(true);
		this.fillLevel();
		gs.draw(this.char_map);
	}
	
	public void exit() {
		this.icon = 0;
		 setVisible(false);
		 gs.setVisible(false);
		 DungeonKeep.getWindow().getFrame().setVisible(true);
		 comboBox.setSelectedIndex(0);
	}
	
	public void updateScreen(int x, int y) {
		this.char_map[y][x] = this.icon;
		gs.draw(this.char_map);
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
		int x = (int) Math.floor((arg0.getX() - 33) / 48);
		int y = (int) Math.floor((arg0.getY() - 54) / 48) - 1;

		if (x >= 0 && y >= 0 && x < size && y < size) {
			if (this.icon == 'I' || this.icon == 'X') {
				this.updateScreen(x, y);
			}
		}

		if (x >= 1 && y >= 1 && x < size - 1 && y < size - 1) {
			switch (this.icon) {
			case 'k':
				if (k) {
					this.cleanPreviousIcon(this.icon);
				} else {
					k = true;
				}
				break;
			case 'H':
				if (h) {
					this.cleanPreviousIcon(this.icon);
				} else {
					h = true;
				}
				break;
			}
			this.updateScreen(x, y);
		}

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
