package dkeep.logic;

import java.util.concurrent.ThreadLocalRandom;
/**
 * Class for suspicious.
 */
public class Suspicious extends Guard{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5731692041940503404L;
	private boolean inverted = false;
	/**
	 * Constructs the object.
	 */
	public Suspicious() {
		super(8,1);
	}
	
	/**
	 * Gets the inverted.
	 *
	 * @return     The inverted.
	 */
	public boolean getInverted() {
		return this.inverted;
	}
	
	/**
	 * { function_description }
	 *
	 * @param      map   The map
	 */
	public void move(char[][] map) {
		char c = this.getDirection();
		this.updateIndex();
		this.moveGuard(c);
	}


	/**
	 * { function_description }
	 */
	public void updateIndex() {
		int temp = ThreadLocalRandom.current().nextInt(0,3);
		int inverse;
		boolean previous_inverted = this.inverted;
		if(temp == 2) {		
			inverse = ThreadLocalRandom.current().nextInt(0,2);
			
			switch(inverse) {
			case 0:
				this.inverted = false;
				break;
			case 1:
				this.inverted = true;
				break;
			default:
				this.inverted = false;
				break;
			}
			if(previous_inverted != this.inverted) {
				return;
			}
		
		
			
		}
		
		if(inverted) {
			if(this.index == 0) {
				this.index = this.moves.length - 1;
			}
			else {
				this.index = (this.index - 1);
			}
		}
		else {
			this.index = (this.index + 1) % this.moves.length;
		}
		
	}
	
	/**
	 * Gets the direction.
	 *
	 * @return     The direction.
	 */
	public char getDirection() {
		if(this.inverted) {
			return this.moves_inv[this.index];
		}
		else {
			return this.moves[this.index];
		}
	}
}
