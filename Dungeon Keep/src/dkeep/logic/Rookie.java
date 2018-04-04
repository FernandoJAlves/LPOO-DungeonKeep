package dkeep.logic;

public class Rookie extends Guard{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Rookie() {
		super(8,1);
	}
	
	public void updateIndex() {
		this.index = (this.index + 1) % this.moves.length;
	}
	
	public void move(char[][] map) {
		char c = this.moves[this.index];
		updateIndex();
		super.moveGuard(c);
	}
	

}
