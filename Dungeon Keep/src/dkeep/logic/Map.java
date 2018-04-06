package dkeep.logic;

import java.io.Serializable;
import java.util.ArrayList;
/**
 * Map.java - a class for the map.
 */
public abstract class Map implements Serializable{

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
	 * Push the lever
	 *
	 * @param      state  The state
	 *
	 * @return     the updated Game_State
	 */
	public abstract Game.Game_State leversUp(Game.Game_State state);
	/**
	 * turns the key in the door
	 *
	 * @param      state  The state
	 *
	 * @return     the updated Game_State
	 */
	public abstract Game.Game_State turnKey(Game.Game_State state);
	/**
	 * Updates the game state
	 *
	 * @param      state  The state
	 * @param      pos    The position
	 *
	 * @return     the updated Game_State
	 */
	public abstract Game.Game_State updateState(Game.Game_State state, char pos);
	/**
	 * Checks if thete's collision between the Hero and his enemies
	 *
	 * @param      state  The state
	 * @param      hero   The hero
	 *
	 * @return     the updated Game_State
	 */
	public abstract Game.Game_State character_collision(Game.Game_State state, Hero hero);
	/**
	 * Applies the logic of the map to the game
	 *
	 * @param      state   The state
	 * @param      hero    The hero
	 * @param      c       the input
	 * @param      update  The update
	 *
	 * @return     the updated Game_State
	 */
	public abstract Game.Game_State map_Logic(Game.Game_State state, Hero hero, char c, BooleanHolder update);
	/**
	 * Moves the NPCs 
	 */
	public abstract void move_npc();
	/**
	 * Draws the characters.
	 *
	 * @param      aux   The auxiliary
	 */
	public abstract void draw_characters(char[][] aux);
	/**
	 * Detects collision between the hero and the ogres
	 *
	 * @param      state  The state
	 * @param      hero   The hero
	 * @param      ogres  The ogres
	 *
	 * @return     the updated Game_State
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
