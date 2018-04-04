package dkeep.logic;

public class Hero extends Character{


	/**
	 * 
	 */
	private static final long serialVersionUID = 88889832444331961L;
	private char direction = 0;
	char sprite = 'H';
	
	public Hero() {
		super(1,1);
	}
	
	public void set_pos(int x, int y) {
		this.set_x(x);
		this.set_y(y);
	}

	public void set_direction(char c) {
		this.direction = c;
	}
	
	public char get_direction() {
		return this.direction;
	}
	
	public char getSprite() {
		return this.sprite;
	}
	
	public void setSprite(char c) {
		this.sprite = c;
	}
	
	public void pick_key() {
		this.sprite = 'K';
	}
	
	public void move(char[][] map) {
		char c = this.direction;
		
		if(c == 'w'){
			if(this.get_y() == 0) {
				return;
			}
			char temp = map[this.get_y()-1][this.get_x()];
			if(temp == 'X' || temp == 'I') {
				return;
			}
			this.set_y(this.get_y() - 1);
			
		}
		else if(c == 'd') {
			
			if(this.get_x() == map[0].length-1) {
				return;
			}
			char temp = map[this.get_y()][this.get_x() + 1];
			if(temp == 'X' || temp == 'I') {
				return;
			}
			this.set_x(this.get_x() + 1);
			
		}
		else if(c == 's') {
			if(this.get_y() == map.length-1) {
				return;
			}
			char temp = map[this.get_y()+1][this.get_x()];
			if(temp == 'X' || temp == 'I') {
				return;
			}
			this.set_y(this.get_y() + 1);
			
		}
		else if(c == 'a') {
			
			if(this.get_x() == 0) {
				return;
			}
			char temp = map[this.get_y()][this.get_x() - 1];
			if(temp == 'X' || temp == 'I') {
				return;
			}
			this.set_x(this.get_x() - 1);
			
		}
		
	}
	
	public void attack(Ogre o) {
		if(this.attack_up(o)) return;
		if(this.attack_right(o)) return;
		if(this.attack_down(o)) return;
		if(this.attack_left(o)) return;
	}
	
	public boolean attack_up(Ogre o) {
		if((o.get_x() == this.get_x()) && (o.get_y() == this.get_y()-1)) {
			o.stun();
			return true;
		}
		else return false;
	}
	
	public boolean attack_right(Ogre o) {
		if(o.get_x() == this.get_x() + 1 && o.get_y() == this.get_y()) {
			o.stun();
			return true;
		}
		else return false;
	}
	
	public boolean attack_down(Ogre o) {
		if(o.get_x() == this.get_x()  && o.get_y() == this.get_y()+1) {
			o.stun();
			return true;
		}
		else return false;
	}
	
	public boolean attack_left(Ogre o) {
		if(o.get_x() == this.get_x() - 1 && o.get_y() == this.get_y()) {
			o.stun();
			return true;
		}
		else return false;
	}
	
	
}
