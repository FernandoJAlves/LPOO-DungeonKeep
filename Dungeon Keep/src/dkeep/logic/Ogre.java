package dkeep.logic;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Ogre.java - a class for the ogre.
 * @see Character
 */
public class Ogre extends Character{

	private static final long serialVersionUID = -6517053137579825828L;
	private int direction = 0;
	public Point club_hit = new Point(this.get_x(),this.get_y());
	private int stun_count = 0;
	private char sprite = 'O';
	/**
	 * Constructs the object.
	 *
	 * @param      x     the x coordinate
	 * @param      y     the y coordinate
	 */
	public Ogre(int x, int y) {
		super(x,y);
	}
	/**
	 * Gets the direction.
	 *
	 * @return     The direction.
	 */
	public int get_dir() {
		return this.direction;
	}

	/**
	 * Sets the direction.
	 *
	 * @param      i     the direction
	 */
	public void set_dir(int i) {
		this.direction = i;
	}

	/**
	 * Gets the stun count.
	 *
	 * @return     The stun count.
	 */
	public int get_stun_count() {
		return this.stun_count;
	}
	
	/**
	 * Moves the character in the given map
	 *
	 * @param      map   The map
	 */
	public void move(char[][] map) {
		if(!this.stunLogic()) {
			this.move_aux(map);
		}
	
	}
	
	/**
	 * Sets the position of the Ogre in the given map
	 *
	 * @param      map   The map
	 */
	public void move_aux(char[][] map) {
		switch (ThreadLocalRandom.current().nextInt(0,4)) {
		case 0:
			this.move_up(map);
			break;
		case 1:
			this.move_right(map);
			break;
		case 2:
			this.move_down(map);
			break;
		case 3:
			this.move_left(map);
			break;
		}
	}
	
	/**
	 * Keeps the ogre stunned
	 *
	 * @return     True if stunned, otherwise false.
	 */
	public boolean stunLogic() {
		if(this.isStunned()) {
			stun_count--;
			return true;
		}
		else if(this.sprite == '8') {
			this.sprite = 'O';
		}
		return false;
	}
	
	/**
	 * Moves the ogre up
	 *
	 * @param      map   The map
	 */
	public void move_up(char[][] map) {
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
	 * Moves the ogre down
	 *
	 * @param      map   The map
	 */
	public void move_down(char[][] map) {
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
	 * Moves the ogre left
	 *
	 * @param      map   The map
	 */
	public void move_left(char[][] map) {
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
	 * Moves the ogre right
	 *
	 * @param      map   The map
	 */
	public void move_right(char[][] map) {
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
	 * Calculates the position of the next attack of the ogre
	 *
	 * @param      map   The map
	 */
	public void club_logic(char[][] map) {
		club_hit.x = this.get_x();
		club_hit.y = this.get_y();
		if(this.stun_count > 0) return;
		this.set_dir(direction);
		this.attack(map);

		
	}
	
	/**
	 * Choose the direction of the attack
	 *
	 * @param      map   The map
	 */
	public void attack(char[][] map) {
		switch (ThreadLocalRandom.current().nextInt(0,4)) {
		case 0:
			this.attack_up(map);
			break;
		case 1:
			this.attack_right(map);
			break;
		case 2:
			this.attack_down(map);
			break;
		case 3:
			this.attack_left(map);
			break;
		}
		
	}
	
	/**
	 * Attack up
	 *
	 * @param      map   The map
	 */
	void attack_up(char[][] map) {
		if(this.get_y() == 0) {
			return;
		}
		char temp = map[this.get_y()-1][this.get_x()];
		if(temp == 'X' || temp == 'I') {
			return;
		}
		club_hit.y = this.get_y() - 1;
		
	}
	
	/**
	 * Attack right
	 *
	 * @param      map   The map
	 */
	void attack_right(char[][] map){
		if(this.get_x() == map[0].length-1) {
			return;
		}
		char temp = map[this.get_y()][this.get_x() + 1];
		if(temp == 'X' || temp == 'I') {
			return;
		}
		club_hit.x = this.get_x() + 1;
	}
	
	/**
	 * Attack down
	 *
	 * @param      map   The map
	 */
	void attack_down(char[][] map){
		if(this.get_y() == map.length-1) {
			return;
		}
		char temp = map[this.get_y()+1][this.get_x()];
		if(temp == 'X' || temp == 'I') {
			return;
		}
		club_hit.y = this.get_y() + 1;
	}
	
	/**
	 * Attack left
	 *
	 * @param      map   The map
	 */
	void attack_left(char[][] map){
		if(this.get_x() == 0) {
			return;
		}
		char temp = map[this.get_y()][this.get_x() - 1];
		if(temp == 'X' || temp == 'I') {
			return;
		}
		club_hit.x = this.get_x() - 1;
	}
	
	/**
	 * stun the ogre
	 */
	public void stun() {
		stun_count = 2;
		this.sprite = '8';
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
	 * Determines if stunned.
	 *
	 * @return     True if stunned, False otherwise.
	 */
	public boolean isStunned() {
		if(stun_count > 0) {
			return true;
		}
		return false;
	}
	
}
