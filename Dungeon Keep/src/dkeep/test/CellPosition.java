package dkeep.test;

public class CellPosition {

	public int x;
	public int y;
	
	public CellPosition(int i, int j) {
		x = i;
		y = j;
	}
	
	@Override
	public boolean equals(Object obj) {
		return (this.x == ((CellPosition) obj).x) && (this.y == ((CellPosition) obj).y);
	}
	
}
