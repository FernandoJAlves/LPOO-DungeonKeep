
public class Hero {

	private int x = 1;
	private int y = 1;
	char sprite = 'H';
	
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
	public char getSprite() {
		return this.sprite;
	}
	public void pick_key() {
		this.sprite = 'K';
	}
	public Game.Game_State move(char c, Game.Game_State g, char[][] map) {
		if(c == 'w'){
			if(this.get_y() == 0) {
				return g;
			}
			char temp = map[this.get_y()-1][this.get_x()];
			if(temp == 'X' || temp == 'I') {
				return g;
			}
			this.set_y(this.get_y() - 1);
			
		}
		else if(c == 'd') {
			if(this.get_x() == map[0].length-1) {
				return g;
			}
			char temp = map[this.get_y()][this.get_x() + 1];
			if(temp == 'X' || temp == 'I') {
				return g;
			}
			this.set_x(this.get_x() + 1);
			
		}
		else if(c == 's') {
			if(this.get_y() == map.length-1) {
				return g;
			}
			char temp = map[this.get_y()+1][this.get_x()];
			if(temp == 'X' || temp == 'I') {
				return g;
			}
			this.set_y(this.get_y() + 1);
			
		}
		else if(c == 'a') {
			if(this.get_x() == 0) {
				return g;
			}
			char temp = map[this.get_y()][this.get_x() - 1];
			if(temp == 'X' || temp == 'I') {
				return g;
			}
			this.set_x(this.get_x() - 1);
			
		}
		
		char pos = map[this.get_y()][this.get_x()];
		
		if(pos == 'k') {
			if(g == Game.Game_State.LVL1)
				return Game.Game_State.LEVER_ACT1;
			else if(g == Game.Game_State.LVL2)
				return Game.Game_State.KEY_PICKED;
		}
		if(pos == 'S') {
			if(g == Game.Game_State.LVL1)
				return Game.Game_State.LVL2;
			else if(g == Game.Game_State.LVL2)
				return Game.Game_State.WIN;
			return Game.Game_State.WIN;
		}
		return g;
	}
	
}
