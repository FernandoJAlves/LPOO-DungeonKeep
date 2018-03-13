package dkeep.logic;

public class Hero extends Character{


	private char direction = 0;
	char sprite = 'H';
	
	public Hero() {
		super(1,1);
	}

	public void set_direction(char c) {
		this.direction = c;
	}
	
	public char get_direction() {
		return this.direction;
	}
	
	public char getSprite() {
		return this.sprite;
	}
	
	public void setSprite(char c) {
		this.sprite = c;
	}
	
	public void pick_key() {
		this.sprite = 'K';
	}
	
	public void move(char[][] map) {
		char c = this.direction;
		
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
	
	public Point attack() {
		int x_temp = this.get_x();
		int y_temp = this.get_y();
		switch(this.direction) {
		case 'a':
			x_temp += -1;
			break;
		case 's':
			y_temp += 1;
			break;
		case 'w':
			y_temp += -1;
			break;
		case 'd':
			x_temp += 1;
			break;
		}
		return new Point(x_temp,y_temp);

	}
	
}
