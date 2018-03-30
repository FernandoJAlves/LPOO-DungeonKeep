package dkeep.gui;

import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class GameScreen extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int size = 0;
	private char[][] map = new char[0][0];
	private ImageIcon wall;
	private ImageIcon hero;
	private ImageIcon closedDoor;
	private ImageIcon guard;
	private ImageIcon key;
	private ImageIcon ogre;
	private ImageIcon door;
	private ImageIcon club;
	//private ImageIcon ogreStunned;
	private ImageIcon guardSleeping;
	private ImageIcon floor;

	
	public GameScreen() {
		super();

	}
	
	public void loadResources() {
		wall = new ImageIcon(this.getClass().getResource("res/Wall_prison.png"));
		hero = new ImageIcon(this.getClass().getResource("res/Hero.png"));
		club = new ImageIcon(this.getClass().getResource("res/Club_hit.png"));
		closedDoor = new ImageIcon(this.getClass().getResource("res/Door_closed.png"));
		door = new ImageIcon(this.getClass().getResource("res/Door_open.png"));
		floor = new ImageIcon(this.getClass().getResource("res/Floor_prison.png"));
		guard = new ImageIcon(this.getClass().getResource("res/Guard_awake.png"));
		guardSleeping = new ImageIcon(this.getClass().getResource("res/Guard_sleep.png"));
		key = new ImageIcon(this.getClass().getResource("res/Key.png"));
		ogre = new ImageIcon(this.getClass().getResource("res/Ogre.png"));
		
		wall = this.scaleImage(wall);
		hero = this.scaleImage(hero);
		club = this.scaleImage(club);
		closedDoor = this.scaleImage(closedDoor);
		door = this.scaleImage(door);
		floor = this.scaleImage(floor);
		guard = this.scaleImage(guard);
		guardSleeping = this.scaleImage(guardSleeping);
		key = this.scaleImage(key);
		ogre = this.scaleImage(ogre);
	}
	
	public ImageIcon scaleImage(ImageIcon im) {

		Image img = im.getImage();
		Image newimg = img.getScaledInstance(this.getWidth() / 10, this.getHeight() / 10, Image.SCALE_FAST);

		return new ImageIcon(newimg);
	}
	
	public void setMap(char[][] map) {
		this.map = map;
	}
	
	public void draw(char[][] game_map) {
		removeAll();
		this.setMap(game_map);
		this.resizeGrid();
		repaint();
		this.paintComponent(null);
		revalidate();
	}
	
	public void resizeGrid() {
		if(size !=this.map.length) {
			size = this.map.length;
			this.setLayout(new GridLayout(size, size));
			this.setBounds(33, 54, size*48, size*48);
		}
	}
	
	@Override
	public void paintComponent(Graphics g) {

		for (int i = 0; i < map.length; i++)
			for (int j = 0; j < map[0].length; j++) {

				switch (map[i][j]) {
				case 'X':
					this.add(new JLabel(wall));
					break;
				case 'I':
					this.add(new JLabel(closedDoor));
					break;
				case 'G':
					this.add(new JLabel(guard));
					break;
				case 'H':
					this.add(new JLabel(hero));
					break;
				case 'k':
					this.add(new JLabel(key));
					break;
				case 'S':
					this.add(new JLabel(door));
					break;
				case 'O':
					this.add(new JLabel(ogre));
					break;
				case '*':
					this.add(new JLabel(club));
					break;
				case '$':
					this.add(new JLabel(club));
					break;
				case 'A':
					this.add(new JLabel(hero));
					break;
				case 'K':
					this.add(new JLabel(hero));
					break;
					/*
				case '8':
					this.add(new JLabel(ogreStunned));
					break;
					*/
				case 'g':
					this.add(new JLabel(guardSleeping));
					break;
				default:
					this.add(new JLabel(floor));
					break;
				}

			}
		

	}
}
