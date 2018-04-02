package dkeep.logic;

import java.util.ArrayList;

import dkeep.logic.Game.Game_State;

public class CustomLevel extends Map {
	private ArrayList<Ogre> ogres = new ArrayList<>();
	private int hero_x;
	private int hero_y;
	private int key_x;
	private int key_y;
	
	public CustomLevel(char[][] map) {
		this.char_map = map;
		this.initialize();
	}
	
	public void initialize() {
		for(int i  = 0; i < this.char_map.length;i++) {
			for(int j = 0; j < this.char_map[0].length;j++) {
				switch(char_map[i][j]) {
				case 'H':
					hero_x = j;
					hero_y = i;
					char_map[i][j] = ' ';
					break;
				case 'O':
					this.ogres.add(new Ogre(j,i));
					char_map[i][j] = ' ';
					break;
				case 'k':
					key_x = j;
					key_y = i;
					break;
				}
			}
		}
		
	}
	
	public int getHeroX() {
		return this.hero_x;
	}
	
	public int getHeroY() {
		return this.hero_y;
	}

	@Override
	public Game_State leversUp(Game_State state) {
		this.char_map[key_x][key_y] = ' ';
		for(int i = 0; i < this.char_map.length; i++) {
			for(int j = 0; j < this.char_map[0].length;j++) {
				if(char_map[i][j] == 'I') {
					char_map[i][j] = 'S';
				}
			}
		}
		return Game.Game_State.CLVL_KEY;
	}

	@Override
	public Game_State turnKey(Game_State state) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Game_State updateState(Game_State state, char pos) {
		if(pos == 'k') {
			return Game.Game_State.CLVL_KEY;
		}
		
		else if(pos == 'S') {
			return Game.Game_State.WIN;
		}

		return state;
	}

	@Override
	public Game_State character_collision(Game_State state, Hero hero) {
		for (int k = 0; k < this.ogres.size(); k++) {

			if (hero.attack().x == ogres.get(k).get_x() && hero.attack().y == ogres.get(k).get_y()) {
				ogres.get(k).stun();
				continue;
			}

			else if (!ogres.get(k).isStunned()) {

				if (hero.get_y() == (ogres.get(k).get_y()) && hero.get_x() == (ogres.get(k).get_x())) {
					return Game.Game_State.LOSE;
				}

				if (hero.get_y() == (ogres.get(k).get_y() - 1) && hero.get_x() == (ogres.get(k).get_x())) {
					return Game.Game_State.LOSE;
				}
				if (hero.get_y() == (ogres.get(k).get_y()) && hero.get_x() == (ogres.get(k).get_x() - 1)) {
					return Game.Game_State.LOSE;
				}
				if (hero.get_y() == (ogres.get(k).get_y() + 1) && hero.get_x() == (ogres.get(k).get_x())) {
					return Game.Game_State.LOSE;
				}
				if (hero.get_y() == (ogres.get(k).get_y()) && hero.get_x() == (ogres.get(k).get_x() + 1)) {
					return Game.Game_State.LOSE;
				}
			}
		}
		
		int x_t;
		int y_t;

		for (int k = 0; k < this.ogres.size(); k++) {

			x_t = this.ogres.get(k).club_hit.x;
			y_t = this.ogres.get(k).club_hit.y;

			if (this.ogres.get(k).isStunned()) {
				return state;
			}

			if (hero.get_y() == (y_t) && hero.get_x() == (x_t)) {
				return Game.Game_State.LOSE;
			}
			if (hero.get_y() == (y_t - 1) && hero.get_x() == (x_t)) {
				return Game.Game_State.LOSE;
			}
			if (hero.get_y() == (y_t) && hero.get_x() == (x_t - 1)) {
				return Game.Game_State.LOSE;
			}
			if (hero.get_y() == (y_t + 1) && hero.get_x() == (x_t)) {
				return Game.Game_State.LOSE;
			}
			if (hero.get_y() == (y_t) && hero.get_x() == (x_t + 1)) {
				return Game.Game_State.LOSE;
			}
		}
		
		return state;
		
	}

	@Override
	public Game_State map_Logic(Game_State state, Hero hero, char c, BooleanHolder update) {
		update.value = true;
		return state;
	}

	@Override
	public void move_npc() {
		for (int k = 0; k < this.ogres.size(); k++) {
			this.ogres.get(k).move(this.char_map);
			ogres.get(k).club_logic(this.char_map);
		}
	}

	@Override
	public void draw_characters(char[][] aux) {
		for (int i = 0; i < this.ogres.size(); i++) {

			if (aux[this.ogres.get(i).club_hit.y][this.ogres.get(i).club_hit.x] == 'k') {
				aux[this.ogres.get(i).club_hit.y][this.ogres.get(i).club_hit.x] = '$';
			} else {
				aux[this.ogres.get(i).club_hit.y][this.ogres.get(i).club_hit.x] = '*';
			}
			
			if (aux[this.ogres.get(i).get_y()][this.ogres.get(i).get_x()] == 'k') {
				aux[this.ogres.get(i).get_y()][this.ogres.get(i).get_x()] = '$';
			} else {
				aux[this.ogres.get(i).get_y()][this.ogres.get(i).get_x()] = this.ogres.get(i).getSprite();
			}
			
		}	
	}

}
