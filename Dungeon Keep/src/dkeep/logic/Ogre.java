package dkeep.logic;
import java.util.concurrent.ThreadLocalRandom;


public class Ogre extends Character{
	private int direction = 0;
	public Point club_hit = new Point();
	private int stun_count = 0;
	private char sprite = 'O';
	
	public Ogre(int x, int y) {
		super(x,y);
	}
	
	public int get_dir() {
		return this.direction;
	}
	public void set_dir(int i) {
		this.direction = i;
	}
	
	public void move(char[][] map) {
		if(this.isStunned()) {
			stun_count--;
			return;
		}
		else if(this.sprite == '8') {
			this.sprite = 'G';
		}
		
		
		
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
	
	public void club_logic(char[][] map) {
		int direction = ThreadLocalRandom.current().nextInt(0,4);
		club_hit.x = this.get_x();
		club_hit.y = this.get_y();
		char temp;
		this.set_dir(direction);
		switch (direction) {
		case 0:
			if(this.get_y() == 0) {
				return;
			}
			temp = map[this.get_y()-1][this.get_x()];
			if(temp == 'X' || temp == 'I') {
				return;
			}
			club_hit.y = this.get_y() - 1;
			break;
			
		case 1:
			if(this.get_x() == map[0].length-1) {
				return;
			}
			temp = map[this.get_y()][this.get_x() + 1];
			if(temp == 'X' || temp == 'I') {
				return;
			}
			club_hit.x = this.get_x() + 1;
			break;
		case 2:
			if(this.get_y() == map.length-1) {
				return;
			}
			temp = map[this.get_y()+1][this.get_x()];
			if(temp == 'X' || temp == 'I') {
				return;
			}
			club_hit.y = this.get_y() + 1;
			break;
		case 3:
			if(this.get_x() == 0) {
				return;
			}
			temp = map[this.get_y()][this.get_x() - 1];
			if(temp == 'X' || temp == 'I') {
				return;
			}
			club_hit.x = this.get_x() - 1;
			break;
			
			
		default:
		}
		
		
	}
	
	public void stun() {
		stun_count = 2;
		this.sprite = '8';
	}
	
	public char getSprite() {
		return this.sprite;
	}
	
	public boolean isStunned() {
		if(stun_count > 0) {
			return true;
		}
		return false;
	}
	
}
