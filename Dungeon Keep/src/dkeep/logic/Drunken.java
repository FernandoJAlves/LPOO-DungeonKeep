package dkeep.logic;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Class for drunken.
 */
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
	/**
	 * Constructs the object.
	 */
	public Drunken() {
		super(8,1);
	}
	/**
	 * Gets the value of the boolean attribute sleepy.
	 *
	 * @return     The boolean sleepy.
	 */
	public boolean getSleepy() {
		return this.sleepy;
	}
	/**
	 * Gets the value of the boolean attribute inverted.
	 *
	 * @return     The inverted.
	 */
	public boolean getInverted() {
		return this.inverted;
	}
	/**
	 * Calculate the next action of the Drunken Guard.
	 */
	public void drunkenLogic() {
		
		if(this.sleepController()) return;
		if(reRollController()) return;

		this.updateIndex();
	}
	/**
	 * Calculates the number of turns until the Drunken fall asleep.
	 *
	 * @return     true if the drunken fell asleepS, otherwise false.
	 */
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
	/**
	 * Keeps the Drunken sleeping.
	 *
	 * @return     true if the drunken is still sleeping, otherwise false
	 */
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
	/**
	 * Calculates the possibility of the drunken sleeping or not.
	 */
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
	/**
	 * Updates the index of the movement of the guard depending on the direction of the actual movement
	 */
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
	/**
	 * Moves the drunken in the given map.
	 *
	 * @param      map   The map
	 */
	public void move(char[][] map) {
		char c = this.getDirection();
		this.drunkenLogic();
		this.moveGuard(c);
		}

	/**
	 * Gets the next step of the movement of the guard.
	 *
	 * @return     The direction.
	 */
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
	
	/**
	 * Gets the sleepCounter.
	 *
	 * @return     The inverted.
	 */
	public int getSleepCounter() {
		return this.sleepCounter;
	}
	
	/**
	 * Gets the reRollSleep.
	 *
	 * @return     The inverted.
	 */
	public int getReRollSleep() {
		return this.reRollSleep;
	}
	
}
