
public class Guard {
	
	private int x = 8;
	private int y = 1;
	public int index = 0;
	public char moves[] = {'a', 's', 's', 's', 's', 'a', 'a', 'a', 'a', 'a', 'a', 's', 'd', 'd', 'd', 'd', 'd', 'd', 'd', 'w', 'w', 'w', 'w', 'w'};
	
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
	
}
