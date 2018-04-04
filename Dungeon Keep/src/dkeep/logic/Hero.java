package dkeep.logic;

/**
 * Class for hero.
 */
public class Hero extends Character{


	/**
	 * 
	 */
	private static final long serialVersionUID = 88889832444331961L;
	private char direction = 0;
	char sprite = 'H';
	
	/**
	 * Constructs the object.
	 */
	public Hero() {
		super(1,1);
	}
	
	/**
	 * Sets the position.
	 *
	 * @param      x     { parameter_description }
	 * @param      y     { parameter_description }
	 */
	public void set_pos(int x, int y) {
		this.set_x(x);
		this.set_y(y);
	}

	/**
	 * Sets the direction.
	 *
	 * @param      c     { parameter_description }
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
	 * @param      c     { parameter_description }
	 */
	public void setSprite(char c) {
		this.sprite = c;
	}
	
	/**
	 * { function_description }
	 */
	public void pick_key() {
		this.sprite = 'K';
	}
	
	/**
	 * { function_description }
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
	
	public void attack(Ogre o) {
		if(this.attack_up(o)) return;
		if(this.attack_right(o)) return;
		if(this.attack_down(o)) return;
		if(this.attack_left(o)) return;
	}
	
	public boolean attack_up(Ogre o) {
		if((o.get_x() == this.get_x()) && (o.get_y() == this.get_y()-1)) {
			o.stun();
			return true;
		}
		else return false;
	}
	
	public boolean attack_right(Ogre o) {
		if(o.get_x() == this.get_x() + 1 && o.get_y() == this.get_y()) {
			o.stun();
			return true;
		}
		else return false;
	}
	
	public boolean attack_down(Ogre o) {
		if(o.get_x() == this.get_x()  && o.get_y() == this.get_y()+1) {
			o.stun();
			return true;
		}
		else return false;
	}
	
	public boolean attack_left(Ogre o) {
		if(o.get_x() == this.get_x() - 1 && o.get_y() == this.get_y()) {
			o.stun();
			return true;
		}
		else return false;
	}
	
	
}
