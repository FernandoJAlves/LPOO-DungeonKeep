package dkeep.logic;

import java.util.concurrent.ThreadLocalRandom;

public class Drunken extends Guard{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4977216385053088310L;
	private int sleepCounter = 0;
	private int reRollSleep = 5;
	private boolean sleepy = false;
	private boolean inverted = false;
	private boolean turning = false;
	
	public Drunken() {
		super(8,1);
	}
	
	public boolean getSleepy() {
		return this.sleepy;
	}
	
	public boolean getInverted() {
		return this.inverted;
	}
	
	public void drunkenLogic() {
		
		if(this.sleepController()) return;
		if(reRollController()) return;

		this.updateIndex();
	}
	
	public boolean reRollController() {
		boolean previous_inverted = this.inverted;
		if(reRollSleep > 0)this.reRollSleep--;
		if(reRollSleep == 0) {
			reRollSleep = 5;
			if(ThreadLocalRandom.current().nextInt(0,3) == 2) {
				this.calculateSleep();
				if(this.inverted != previous_inverted) this.turning = true;
				return true;
			}
		}
		return false;
	}
	
	public boolean sleepController() {
		
		if(sleepCounter > 0) {
			this.sleepCounter--;
			return true;
		}
		if(sleepCounter == 0) {
			this.sleepy = false;
			this.setSprite('G');
		}
		return false;
	}
	
	public void calculateSleep() {
		int inverse = ThreadLocalRandom.current().nextInt(0,2);
		sleepCounter = ThreadLocalRandom.current().nextInt(3,6);
		this.sleepy = true;
		this.setSprite('g');
		switch(inverse) {
		case 1:
			this.inverted = true;
			break;
		default:
			this.inverted = false;
			break;
		}
	}
	
	public void updateIndex() {
		if(this.turning) {
			this.turning = false;
			return;
		}
		if(inverted) {
			this.index = Math.floorMod(this.index-1, this.moves.length);
		}
		else {
			this.index = (this.index + 1) % this.moves.length;
		}
	}
	
	public void move(char[][] map) {
		char c = this.getDirection();
		this.drunkenLogic();
		this.moveGuard(c);
		}


	public char getDirection() {
	if(!this.sleepy) {
		if(this.inverted) {
			return this.moves_inv[this.index];
		}
		else {
			return this.moves[this.index];
		}
	}
	return 0;
	}
}
