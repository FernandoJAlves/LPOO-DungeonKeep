package dkeep.logic;

public abstract class Guard extends Character{
	
	public char sprite = 'G';
	public int index = 0;
	public char moves[] = {'a', 's', 's', 's', 's', 'a', 'a', 'a', 'a', 'a', 'a', 's', 'd', 'd', 'd', 'd', 'd', 'd', 'd', 'w', 'w', 'w', 'w', 'w'};
	public char inverted_moves[];
	
	public Guard(int x, int y) {
		super(x,y);
	}
	
	public char getSprite() {
		return this.sprite;
	}
	
	public void setSprite(char c) {
		this.sprite = c;
	}
	
	public abstract void updateIndex();
	
	public abstract void move(char[][] map);



	
	
	
}
