package dkeep.logic;

public class Rookie extends Guard{
	
	
	public Rookie() {
		super(8,1);
	}
	
	public void updateIndex() {
		this.index = (this.index + 1) % this.moves.length;
	}
	
	public void move(char[][] map) {
		char c = 0;
		updateIndex();
		c = this.moves[this.index];
		
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
