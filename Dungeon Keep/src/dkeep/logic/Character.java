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
	 * Constructs the object.
	 *
	 * @param      x     the x coord
	 * @param      y     the y coord
	 */
	public Character(int x, int y) {
		this.x = x;
		this.y = y;
	}
	/**
	 * Gets the x.
	 *
	 * @return     The x.
	 */
	public int get_x() {
		return this.x;
	}
	/**
	 * Gets the y.
	 *
	 * @return     The y.
	 */
	public int get_y() {
		return this.y;
	}
	/**
	 * Sets the x.
	 *
	 * @param      x_t   The x t
	 */
	public void set_x(int x_t) {
		this.x = x_t;
	}
	/**
	 * Sets the y.
	 *
	 * @param      y_t   The y t
	 */
	public void set_y(int y_t) {
		this.y = y_t;
	}
	/**
	 * Gets the point.
	 *
	 * @return     The point.
	 */
	public Point getPoint() {
		return new Point(this.x, this.y);
	}
	
	/**
	 * { function_description }
	 *
	 * @param      map   The map
	 */
	public abstract void move(char[][] map);
	
}
