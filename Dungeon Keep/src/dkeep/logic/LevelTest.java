package dkeep.logic;

import java.util.ArrayList;

import dkeep.logic.Game.Game_State;

public class LevelTest extends Map{

	public Guard guard;
	public ArrayList<Ogre> ogres;
	
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
	
	@Override
	public Game_State leversUp(Game_State state) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Game_State turnKey(Game_State state) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Game_State updateState(Game_State state, char pos) {
		// TODO Auto-generated method stub
		return null;
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
		
		//Next
	}

	@Override
	public Game_State map_Logic(Game_State state, Hero hero, char c, BooleanHolder update) {
		// TODO Auto-generated method stub
		return null;
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