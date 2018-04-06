package dkeep.logic;

/**
 * Hero.java - a class for the hero.
 * @see Character
 */
public class Hero extends Character{

	private static final long serialVersionUID = 88889832444331961L;
	private char direction = 0;
	char sprite = 'H';
	
	/**
	 * Constructs the Hero object.
	 */
	public Hero() {
		super(1,1);
	}
	
	/**
	 * Sets the position.
	 *
	 * @param      x     the x coord
	 * @param      y     the y coord
	 */
	public void set_pos(int x, int y) {
		this.set_x(x);
		this.set_y(y);
	}

	/**
	 * Sets the direction.
	 *
	 * @param      c     the direction
	 */
	public void set_direction(char c) {
		this.direction = c;
	}
	
	/**
	 * Gets the direction.
	 *
	 * @return     The direction.
	 */
	public char get_direction() {
		return this.direction;
	}
	
	/**
	 * Gets the sprite.
	 *
	 * @return     The sprite.
	 */
	public char getSprite() {
		return this.sprite;
	}
	
	/**
	 * Sets the sprite.
	 *
	 * @param      c     the sprite
	 */
	public void setSprite(char c) {
		this.sprite = c;
	}
	
	/**
	 * Makes the hero pick the key.
	 */
	public void pick_key() {
		this.sprite = 'K';
	}
	
	/**
	 * Moves the character in the given map
	 *
	 * @param      map   The map
	 */
	public void move(char[][] map) {
		switch(this.direction) {
		case 'w':
			this.move_up(map);
			break;
		case 'd':
			this.move_right(map);
			break;
		case 's':
			this.move_down(map);
			break;
		case 'a':
			this.move_left(map);
			break;
		}

		
	}

	/**
	 * Moves the hero up
	 *
	 * @param      map   The map
	 */
	public void move_up(char[][]map) {
		if(this.get_y() == 0) {
			return;
		}
		char temp = map[this.get_y()-1][this.get_x()];
		if(temp == 'X' || temp == 'I') {
			return;
		}
		this.set_y(this.get_y() - 1);
	}
	
	/**
	 * Moves the hero right
	 *
	 * @param      map   The map
	 */
	public void move_right(char[][]map) {
		if(this.get_x() == map[0].length-1) {
			return;
		}
		char temp = map[this.get_y()][this.get_x() + 1];
		if(temp == 'X' || temp == 'I') {
			return;
		}
		this.set_x(this.get_x() + 1);
	}
	
	/**
	 * Moves the hero down
	 *
	 * @param      map   The map
	 */
	public void move_down(char[][]map) {
		if(this.get_y() == map.length-1) {
			return;
		}
		char temp = map[this.get_y()+1][this.get_x()];
		if(temp == 'X' || temp == 'I') {
			return;
		}
		this.set_y(this.get_y() + 1);
	}
	
	/**
	 * Moves the hero left
	 *
	 * @param      map   The map
	 */
	public void move_left(char[][]map) {
		if(this.get_x() == 0) {
			return;
		}
		char temp = map[this.get_y()][this.get_x() - 1];
		if(temp == 'X' || temp == 'I') {
			return;
		}
		this.set_x(this.get_x() - 1);
	}
	
	/**
	 * Attack the ogres in the adjacent positions
	 *
	 * @param      o     { parameter_description }
	 */
	public void attack(Ogre o) {
		this.attack_up(o);
		this.attack_right(o);
		this.attack_down(o);
		this.attack_left(o);
	}
	
	/**
	 * Attack the ogre above the hero
	 *
	 * @param      o    the ogre
	 *
	 * @return     True if stunned an ogre, otherwise false
	 */
	public boolean attack_up(Ogre o) {
		if((o.get_x() == this.get_x()) && (o.get_y() == this.get_y()-1)) {
			o.stun();
			return true;
		}
		else return false;
	}
	
	/**
	 * Attack the ogre on the right of the hero
	 *
	 * @param      o    the ogre
	 *
	 * @return     True if stunned an ogre, otherwise false
	 */
	public boolean attack_right(Ogre o) {
		if(o.get_x() == this.get_x() + 1 && o.get_y() == this.get_y()) {
			o.stun();
			return true;
		}
		else return false;
	}
	
	/**
	 * Attack the ogre below the hero
	 *
	 * @param      o    the ogre
	 *
	 * @return     True if stunned an ogre, otherwise false
	 */
	public boolean attack_down(Ogre o) {
		if(o.get_x() == this.get_x()  && o.get_y() == this.get_y()+1) {
			o.stun();
			return true;
		}
		else return false;
	}
	
	/**
	 * Attack the ogre on the left of the hero
	 *
	 * @param      o    the ogre
	 *
	 * @return     True if stunned an ogre, otherwise false
	 */
	public boolean attack_left(Ogre o) {
		if(o.get_x() == this.get_x() - 1 && o.get_y() == this.get_y()) {
			o.stun();
			return true;
		}
		else return false;
	}
	
	
}
