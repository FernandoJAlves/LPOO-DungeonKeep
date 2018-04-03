package dkeep.logic;


public class Point {
	public int x = 0;
	public int y = 0;
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Point() {};
	
	@Override
	public boolean equals(Object obj) {
		return (this.x == ((Point) obj).x) && (this.y == ((Point) obj).y);
	}
}
