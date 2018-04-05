package dkeep.logic;

/**
 * Class for guard.
 */
public abstract class Guard extends Character{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6431670539463691776L;
	protected char sprite = 'G';
	protected int index = 0;
	protected char moves[] =     {'a', 's', 's', 's', 's', 'a', 'a', 'a', 'a', 'a', 'a', 's', 'd', 'd', 'd', 'd', 'd', 'd', 'd', 'w', 'w', 'w', 'w', 'w'};
	protected char moves_inv[] = {'d', 'w', 'w', 'w', 'w', 'd', 'd', 'd', 'd', 'd', 'd', 'w', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 's', 's', 's', 's', 's'};
	
	/**
	 * Constructs the object.
	 *
	 * @param      x     the x coord 
	 * @param      y     the y coord
	 */
	public Guard(int x, int y) {
		super(x,y);
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
	 * Updates the index of the movement of the Guard.
	 */
	public abstract void updateIndex();
	
	/**
	 * Moves a character in the given map.
	 *
	 * @param      map   The map
	 */
	public abstract void move(char[][] map);
	
	/**
	 * moves the Guard in the given map
	 *
	 * @param      c     the direction
	 */
	public void moveGuard(char c) {
		if(c == 'w'){
			this.set_y(this.get_y() - 1);
		}
		else if(c == 'd') {
			this.set_x(this.get_x() + 1);
		}
		else if(c == 's') {
			this.set_y(this.get_y() + 1);
		}
		else if(c == 'a') {
			this.set_x(this.get_x() - 1);
			}
	}



	
	
	
}
