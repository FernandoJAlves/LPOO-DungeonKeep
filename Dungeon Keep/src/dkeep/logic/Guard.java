package dkeep.logic;

public abstract class Guard extends Character{
	
	protected char sprite = 'G';
	protected int index = 0;
	protected char moves[] =     {'a', 's', 's', 's', 's', 'a', 'a', 'a', 'a', 'a', 'a', 's', 'd', 'd', 'd', 'd', 'd', 'd', 'd', 'w', 'w', 'w', 'w', 'w'};
	protected char moves_inv[] = {'d', 'w', 'w', 'w', 'w', 'd', 'd', 'd', 'd', 'd', 'd', 'w', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 's', 's', 's', 's', 's'};
	
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
