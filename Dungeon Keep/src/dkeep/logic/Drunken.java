package dkeep.logic;

import java.util.concurrent.ThreadLocalRandom;

public class Drunken extends Guard{
	private int sleepCounter = 0;
	private int reRollSleep = 0;
	private boolean inverted = false;
	
	public Drunken() {
		super(8,1);
	}
	
	public void updateIndex() {
		int temp;
		if(sleepCounter > 0) {
			this.sleepCounter--;
			return;
		}
		if(reRollSleep > 0) {
			this.reRollSleep--;
			return;
		}
		if(reRollSleep == 0) {
			reRollSleep = 5;
			temp = ThreadLocalRandom.current().nextInt(0,3);
			if(temp == 0) {
				sleepCounter = ThreadLocalRandom.current().nextInt(3,6);
			}
		}
		int inverse = ThreadLocalRandom.current().nextInt(0,2);
		
		switch(inverse) {
		case 0:
			this.inverted = false;
		case 1:
			this.inverted = true;
		default:
			this.inverted = false;
		}
		
		if(inverted) {
			if(this.index == 0) {
				this.index = this.moves.length;
			}
			else {
				this.index = (this.index - 1);
			}
		}
		else {
			this.index = (this.index + 1) % this.moves.length;
		}
		
		
		
	}
	
	public void move(char[][] map) {
		if(this.sleepCounter > 0) {
			return;
		}
		char c = this.moves[this.index];
		updateIndex();
		
		if(c == 'w'){
			if(this.get_y() == 0) {
				return;
			}
			char temp = map[this.get_y()-1][this.get_x()];
			if(temp == 'X' || temp == 'I') {
				return;
			}
			this.set_y(this.get_y() - 1);
			
		}
		else if(c == 'd') {
			if(this.get_x() == map[0].length-1) {
				return;
			}
			char temp = map[this.get_y()][this.get_x() + 1];
			if(temp == 'X' || temp == 'I') {
				return;
			}
			this.set_x(this.get_x() + 1);
			
		}
		else if(c == 's') {
			if(this.get_y() == map.length-1) {
				return;
			}
			char temp = map[this.get_y()+1][this.get_x()];
			if(temp == 'X' || temp == 'I') {
				return;
			}
			this.set_y(this.get_y() + 1);
			
		}
		else if(c == 'a') {
			if(this.get_x() == 0) {
				return;
			}
			char temp = map[this.get_y()][this.get_x() - 1];
			if(temp == 'X' || temp == 'I') {
				return;
			}
			this.set_x(this.get_x() - 1);
			
		}
	}

	
}