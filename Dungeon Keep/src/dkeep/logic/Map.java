package dkeep.logic;

import java.io.Serializable;
import java.util.ArrayList;
/**
 * Class for map.
 */
public abstract class Map implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3133907343984778555L;
	protected char[][] char_map;
	/**
	 * Gets the map.
	 *
	 * @param      state  The state
	 *
	 * @return     The map.
	 */
	public char[][] getMap(Game.Game_State state) {
		return this.char_map;
	}
	/**
	 * { function_description }
	 *
	 * @param      state  The state
	 *
	 * @return     { description_of_the_return_value }
	 */
	public abstract Game.Game_State leversUp(Game.Game_State state);
	/**
	 * { function_description }
	 *
	 * @param      state  The state
	 *
	 * @return     { description_of_the_return_value }
	 */
	public abstract Game.Game_State turnKey(Game.Game_State state);
	/**
	 * { function_description }
	 *
	 * @param      state  The state
	 * @param      pos    The position
	 *
	 * @return     { description_of_the_return_value }
	 */
	public abstract Game.Game_State updateState(Game.Game_State state, char pos);
	/**
	 * { function_description }
	 *
	 * @param      state  The state
	 * @param      hero   The hero
	 *
	 * @return     { description_of_the_return_value }
	 */
	public abstract Game.Game_State character_collision(Game.Game_State state, Hero hero);
	/**
	 * { function_description }
	 *
	 * @param      state   The state
	 * @param      hero    The hero
	 * @param      c       { parameter_description }
	 * @param      update  The update
	 *
	 * @return     { description_of_the_return_value }
	 */
	public abstract Game.Game_State map_Logic(Game.Game_State state, Hero hero, char c, BooleanHolder update);
	/**
	 * { function_description }
	 */
	public abstract void move_npc();
	/**
	 * Draws characters.
	 *
	 * @param      aux   The auxiliary
	 */
	public abstract void draw_characters(char[][] aux);
	/**
	 * { function_description }
	 *
	 * @param      state  The state
	 * @param      hero   The hero
	 * @param      ogres  The ogres
	 *
	 * @return     { description_of_the_return_value }
	 */
	public Game.Game_State ogreCollision(Game.Game_State state, Hero hero, ArrayList<Ogre> ogres){
		for (int k = 0; k < ogres.size(); k++) {
			hero.attack(ogres.get(k));
		}
		
		int x_t;
		int y_t;

		for (int k = 0; k < ogres.size(); k++) {

			x_t = ogres.get(k).club_hit.x;
			y_t = ogres.get(k).club_hit.y;

			if (ogres.get(k).isStunned()) {
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

	
}
