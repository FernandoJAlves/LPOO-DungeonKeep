package dkeep.logic;

public class Rookie extends Guard{
	
	
	public Rookie() {
		super(8,1);
	}
	
	public void updateIndex() {
		this.index = (this.index + 1) % this.moves.length;
	}
	

}
