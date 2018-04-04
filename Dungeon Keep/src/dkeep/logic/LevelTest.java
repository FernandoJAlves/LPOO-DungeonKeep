package dkeep.logic;

import java.util.ArrayList;

import dkeep.logic.Game.Game_State;

public class LevelTest extends Map{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7018383172333769248L;
	public Guard guard;
	public ArrayList<Ogre> ogres = new ArrayList<>();
	
	public LevelTest(){
		super();
		char[][] temp = {
				{'X','X','X','X','X'},
				{'X',' ',' ',' ','X'},
				{'X',' ',' ',' ','X'},
				{'X','k',' ',' ','X'},
				{'X','X','X','X','X'}
		};
		this.char_map = temp;
		
		guard = new Rookie();
	}
	
	public void setTestMap(char[][] newmap) {
		this.char_map = newmap;
	}
	
	
	@Override
	public Game_State leversUp(Game_State state) {
		if(state == Game.Game_State.TEST_KEYPICKED) {
			this.char_map[3][1] = ' ';
		}
		return state;

	}

	@Override
	public Game_State turnKey(Game_State state) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Game_State updateState(Game_State state, char pos) {
		if(pos == 'k') {
			return Game.Game_State.TEST_KEYPICKED;
		}
		
		else if(pos == 'S') {
			return Game.Game_State.WIN;
		}

		return state;
	}

	@Override
	public Game_State character_collision(Game_State state, Hero hero) {
		//Guard
		
		if (hero.get_y() == (guard.get_y()) && hero.get_x() == (guard.get_x())) {
			if (guard.getSprite() != 'g') {
				return Game.Game_State.LOSE;
			}
		}
		if (hero.get_y() == (guard.get_y() - 1) && hero.get_x() == (guard.get_x())) {
			if (guard.getSprite() != 'g') {
				return Game.Game_State.LOSE;
			}
		}
		if (hero.get_y() == (guard.get_y()) && hero.get_x() == (guard.get_x() - 1)) {
			if (guard.getSprite() != 'g') {
				return Game.Game_State.LOSE;
			}
		}
		if (hero.get_y() == (guard.get_y() + 1) && hero.get_x() == (guard.get_x())) {
			if (guard.getSprite() != 'g') {
				return Game.Game_State.LOSE;
			}
		}
		if (hero.get_y() == (guard.get_y()) && hero.get_x() == (guard.get_x() + 1)) {
			if (guard.getSprite() != 'g') {
				return Game.Game_State.LOSE;
			}
		}
		
		//Ogres
		
		for (int k = 0; k < this.ogres.size(); k++) {
			hero.attack(this.ogres.get(k));
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
		
		//Next
	}

	@Override
	public Game_State map_Logic(Game_State state, Hero hero, char c, BooleanHolder update) {
		update.value = true;
		return state;
	}

	@Override
	public void move_npc() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw_characters(char[][] aux) {
		// TODO Auto-generated method stub
		
	}

}
