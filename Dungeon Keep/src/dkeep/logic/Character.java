package dkeep.logic;

import java.io.Serializable;
/**
 * Class for character.
 */
public abstract class Character implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7636713237774526526L;
	private int x;
	private int y;
	/**
	 * Constructs the Character object.
	 *
	 * @param      x     the x coord
	 * @param      y     the y coord
	 */
	public Character(int x, int y) {
		this.x = x;
		this.y = y;
	}
	/**
	 * Gets the x coord.
	 *
	 * @return     The x coord.
	 */
	public int get_x() {
		return this.x;
	}
	/**
	 * Gets the y coord.
	 *
	 * @return     The y coord.
	 */
	public int get_y() {
		return this.y;
	}
	/**
	 * Sets the x coord.
	 *
	 * @param      x_t   The new x coord
	 */
	public void set_x(int x_t) {
		this.x = x_t;
	}
	/**
	 * Sets the y coord.
	 *
	 * @param      y_t   The new y coord
	 */
	public void set_y(int y_t) {
		this.y = y_t;
	}
	/**
	 * Gets the point, an object that boxes the coordinates pair.
	 *
	 * @return     The point.
	 */
	public Point getPoint() {
		return new Point(this.x, this.y);
	}
	
	/**
	 * Moves a character in the given map
	 *
	 * @param      map   The map
	 */
	public abstract void move(char[][] map);
	
}
