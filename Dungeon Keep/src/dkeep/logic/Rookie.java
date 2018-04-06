package dkeep.logic;
/**
 * Rookie.java - a class for the rookie guard.
 * @see Guard
 */
public class Rookie extends Guard{
	
	
	private static final long serialVersionUID = 1L;

	/**
	 * Constructs the object.
	 */
	public Rookie() {
		super(8,1);
	}
	
	/**
	 * Updates the movement of the guard.
	 */
	public void updateIndex() {
		this.index = (this.index + 1) % this.moves.length;
	}
	
	/**
	 * Moves the character in the given map
	 *
	 * @param      map   The map
	 */
	public void move(char[][] map) {
		char c = this.moves[this.index];
		updateIndex();
		super.moveGuard(c);
	}
	

}
