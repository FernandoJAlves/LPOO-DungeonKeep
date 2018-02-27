import java.util.concurrent.ThreadLocalRandom;


public class Ogre {
	private int x = 4;
	private int y = 1;
	private int direction = 0;
	
	public int get_x() {
		return this.x;
	}
	public int get_y() {
		return this.y;
	}
	public void set_x(int x_t) {
		this.x = x_t;
	}
	public void set_y(int y_t) {
		this.y = y_t;
	}
	public int get_dir() {
		return this.direction;
	}
	public void set_dir(int i) {
		this.direction = i;
	}
	
	public void move(char[][] map) {
		int direction = ThreadLocalRandom.current().nextInt(0,4);
		char temp;
		switch (direction) {
		case 0:
			if(this.get_y() == 0) {
				return;
			}
			temp = map[this.get_y()-1][this.get_x()];
			if(temp == 'X' || temp == 'I') {
				return;
			}
			this.set_y(this.get_y() - 1);
			break;
			
		case 1:
			if(this.get_x() == map[0].length-1) {
				return;
			}
			temp = map[this.get_y()][this.get_x() + 1];
			if(temp == 'X' || temp == 'I') {
				return;
			}
			this.set_x(this.get_x() + 1);
			break;
		case 2:
			if(this.get_y() == map.length-1) {
				return;
			}
			temp = map[this.get_y()+1][this.get_x()];
			if(temp == 'X' || temp == 'I') {
				return;
			}
			this.set_y(this.get_y() + 1);
			break;
		case 3:
			if(this.get_x() == 0) {
				return;
			}
			temp = map[this.get_y()][this.get_x() - 1];
			if(temp == 'X' || temp == 'I') {
				return;
			}
			this.set_x(this.get_x() - 1);
			break;
			
			
		default:
		}
	
	}
}
