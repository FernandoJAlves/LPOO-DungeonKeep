package dkeep.logic;

public abstract class Character {
	private int x;
	private int y;
	
	public Character(int x, int y) {
		this.x = x;
		this.y = y;
	}
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
	
	public abstract void move(char[][] map);
	
}
