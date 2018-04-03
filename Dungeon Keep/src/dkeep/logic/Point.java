package dkeep.logic;

import java.io.Serializable;

public class Point implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1286240019727748897L;
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
