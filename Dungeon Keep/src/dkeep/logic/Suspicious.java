package dkeep.logic;

import java.util.concurrent.ThreadLocalRandom;

public class Suspicious extends Guard{
	private boolean inverted = false;
	
	public Suspicious() {
		super(8,1);
	}
	
	
	public void move(char[][] map) {
		char c = 0;
			
		
		if(this.inverted) {
			c = this.moves_inv[this.index];
		}
		else {
			c = this.moves[this.index];
		}
			
		this.updateIndex();
		
		
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
}
