package dkeep.logic;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Level2 extends Map{

	private ArrayList<Ogre> ogres;
	
	public Level2(){
		super();
		char[][] temp = {
				{'X','X','X','X','X','X','X','X','X'},
				{'I',' ',' ',' ',' ',' ',' ','k','X'},
				{'X',' ',' ',' ',' ',' ',' ',' ','X'},
				{'X',' ',' ',' ',' ',' ',' ',' ','X'},
				{'X',' ',' ',' ',' ',' ',' ',' ','X'},
				{'X',' ',' ',' ',' ',' ',' ',' ','X'},
				{'X',' ',' ',' ',' ',' ',' ',' ','X'},
				{'X',' ',' ',' ',' ',' ',' ',' ','X'},
				{'X','X','X','X','X','X','X','X','X'}
		};
		this.char_map = temp;
		
		//gerar os ogres
		ogres = ogre_generator();
		
	}
	
	public Game.Game_State leversUp(Game.Game_State state) {

		if(state == Game.Game_State.LVL2_KEY_PICKED) {
			this.char_map[1][7] = ' ';
			return Game.Game_State.LVL2_KEY_PICKED;
		}
		else if(state == Game.Game_State.LVL2_KEY_TURNED) {
			return Game.Game_State.LVL2_KEY_TURNED;
		}
		return state;

	}
	
	public Game.Game_State turnKey(Game.Game_State state) {
		this.char_map[1][0] = 'S';
		return Game.Game_State.LVL2_KEY_TURNED;
	}
	
	public Game.Game_State updateState(Game.Game_State state, char pos){
		
		if(pos == 'k') {
			if(state == Game.Game_State.LVL2)
				return Game.Game_State.LVL2_KEY_PICKED;
		}
		
		else if(pos == 'S') {
			return Game.Game_State.WIN;
		}

		return state;
	}
	
	public Game.Game_State character_collision(Game.Game_State state, Hero hero){
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
	
	
	public Game.Game_State map_Logic(Game.Game_State state, Hero hero, char c, BooleanHolder update){
		
		if(state == Game.Game_State.LVL2_KEY_PICKED && hero.get_x() == 1 && hero.get_y() == 1) {
			return this.turnKey(state);
		}
		
		update.value = true;
		return state;
	}
	
	
	public void move_npc() {
		for (int k = 0; k < this.ogres.size(); k++) {
			this.ogres.get(k).move(this.char_map);
			ogres.get(k).club_logic(this.char_map);
		}
	}
	
	public void draw_characters(char[][] aux) {
		for (int i = 0; i < this.ogres.size(); i++) {

			if (this.ogres.get(i).club_hit.x == 7 && this.ogres.get(i).club_hit.y == 1) {
				aux[1][7] = '$';
			} else {
				aux[this.ogres.get(i).club_hit.y][this.ogres.get(i).club_hit.x] = '*';
			}
			
			if (this.ogres.get(i).get_x() == 7 && this.ogres.get(i).get_y() == 1) {
				aux[1][7] = '$';
			} else {
				aux[this.ogres.get(i).get_y()][this.ogres.get(i).get_x()] = this.ogres.get(i).getSprite();
			}
		}	
	}
	
	
	
	
	
	public ArrayList<Ogre> ogre_generator(){
		int numOgres = ThreadLocalRandom.current().nextInt(2,5);
		int x,y;
		ArrayList<Ogre> ogres = new ArrayList<>();
		for(int i = 0;i < numOgres;i++) {
			x = ThreadLocalRandom.current().nextInt(3,8);
			y = ThreadLocalRandom.current().nextInt(3,8);
			ogres.add(new Ogre(x,y));
		}
		return ogres;
	}
	
	
}
