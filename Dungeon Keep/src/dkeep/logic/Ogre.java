package dkeep.logic;
import java.util.concurrent.ThreadLocalRandom;


public class Ogre extends Character{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6517053137579825828L;
	private int direction = 0;
	public Point club_hit = new Point(this.get_x(),this.get_y());
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
	public int get_stun_count() {
		return this.stun_count;
	}
	
	public void move(char[][] map) {
		if(!this.stunLogic()) {
			this.move_aux(map);
		}
	
	}
	
	public void move_aux(char[][] map) {
		switch (ThreadLocalRandom.current().nextInt(0,4)) {
		case 0:
			this.move_up(map);
			break;
			
		case 1:
			this.move_right(map);
			break;
		case 2:
			this.move_down(map);
			break;
		case 3:
			this.move_left(map);
			break;
			
			
		}
	}
	
	public boolean stunLogic() {
		if(this.isStunned()) {
			stun_count--;
			return true;
		}
		else if(this.sprite == '8') {
			this.sprite = 'O';
		}
		return false;
	}
	
	public void move_up(char[][] map) {
		if(this.get_y() == 0) {
			return;
		}
		char temp = map[this.get_y()-1][this.get_x()];
		if(temp == 'X' || temp == 'I') {
			return;
		}
		this.set_y(this.get_y() - 1);
	}
	
	public void move_down(char[][] map) {
		if(this.get_y() == map.length-1) {
			return;
		}
		char temp = map[this.get_y()+1][this.get_x()];
		if(temp == 'X' || temp == 'I') {
			return;
		}
		this.set_y(this.get_y() + 1);
	}
	public void move_left(char[][] map) {
		if(this.get_x() == 0) {
			return;
		}
		char temp = map[this.get_y()][this.get_x() - 1];
		if(temp == 'X' || temp == 'I') {
			return;
		}
		this.set_x(this.get_x() - 1);
	}
	public void move_right(char[][] map) {
		if(this.get_x() == map[0].length-1) {
			return;
		}
		char temp = map[this.get_y()][this.get_x() + 1];
		if(temp == 'X' || temp == 'I') {
			return;
		}
		this.set_x(this.get_x() + 1);
	}
	
	public void club_logic(char[][] map) {
		club_hit.x = this.get_x();
		club_hit.y = this.get_y();
		if(this.stun_count > 0) return;
		this.set_dir(direction);
		this.attack(map);

		
	}
	
	public void attack(char[][] map) {
		switch (ThreadLocalRandom.current().nextInt(0,4)) {
		case 0:
			this.attack_up(map);
			break;
		case 1:
			this.attack_right(map);
			break;
		case 2:
			this.attack_down(map);
			break;
		case 3:
			this.attack_left(map);
			break;
		}
		
	}
	
	void attack_up(char[][] map) {
		if(this.get_y() == 0) {
			return;
		}
		char temp = map[this.get_y()-1][this.get_x()];
		if(temp == 'X' || temp == 'I') {
			return;
		}
		club_hit.y = this.get_y() - 1;
		
	}
	
	void attack_right(char[][] map){
		if(this.get_x() == map[0].length-1) {
			return;
		}
		char temp = map[this.get_y()][this.get_x() + 1];
		if(temp == 'X' || temp == 'I') {
			return;
		}
		club_hit.x = this.get_x() + 1;
	}
	
	void attack_down(char[][] map){
		if(this.get_y() == map.length-1) {
			return;
		}
		char temp = map[this.get_y()+1][this.get_x()];
		if(temp == 'X' || temp == 'I') {
			return;
		}
		club_hit.y = this.get_y() + 1;
	}
	
	void attack_left(char[][] map){
		if(this.get_x() == 0) {
			return;
		}
		char temp = map[this.get_y()][this.get_x() - 1];
		if(temp == 'X' || temp == 'I') {
			return;
		}
		club_hit.x = this.get_x() - 1;
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
