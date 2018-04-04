package dkeep.logic;
/**
 * Class for rookie.
 */
public class Rookie extends Guard{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructs the object.
	 */
	public Rookie() {
		super(8,1);
	}
	
	/**
	 * { function_description }
	 */
	public void updateIndex() {
		this.index = (this.index + 1) % this.moves.length;
	}
	
	/**
	 * { function_description }
	 *
	 * @param      map   The map
	 */
	public void move(char[][] map) {
		char c = this.moves[this.index];
		updateIndex();
		super.moveGuard(c);
	}
	

}
