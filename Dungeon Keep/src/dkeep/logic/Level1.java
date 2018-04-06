package dkeep.logic;

import dkeep.logic.Game.Game_State;
/**
 * Level1.java - a class for the level 1.
 * @see Map
 */
public class Level1 extends Map{


	private static final long serialVersionUID = 1L;
	private Guard guard;
	
	/**
	 * Constructs the Level1 object.
	 *
	 * @param      guard_number  The number that represents the personality of the guard
	 */
	public Level1(int guard_number){
		super();
		char[][] temp = {
				{'X','X','X','X','X','X','X','X','X','X'},
				{'X',' ',' ',' ','I',' ','X',' ',' ','X'},
				{'X','X','X',' ','X','X','X',' ',' ','X'},
				{'X',' ','I',' ','I',' ','X',' ',' ','X'},
				{'X','X','X',' ','X','X','X',' ',' ','X'},
				{'I',' ',' ',' ',' ',' ',' ',' ',' ','X'},
				{'I',' ',' ',' ',' ',' ',' ',' ',' ','X'},
				{'X','X','X',' ','X','X','X','X',' ','X'},
				{'X',' ','I',' ','I',' ','X','k',' ','X'},
				{'X','X','X','X','X','X','X','X','X','X'}
		};
		this.char_map = temp;
		//Gerar o guard
		switch(guard_number) {
		case 0:
			guard = new Rookie();
			break;
		case 1:
			guard = new Drunken();
			break;
		case 2:
			guard = new Suspicious();
			break;
		default:
			guard = new Rookie();
		}
		
	}
	
	/**
	 * Gets the guard.
	 *
	 * @return     The guard.
	 */
	public Guard getGuard() {
		return this.guard;
	}
	
	/**
	 * Turns the key in the door
	 *
	 * @param      state  The state
	 *
	 * @return     The updated Game_State
	 */
	public Game_State turnKey(Game_State state) {
		return state;
	}
	
	/**
	 * push the lever
	 *
	 * @param      state  The state
	 *
	 * @return     the updated Game_State
	 */
	public Game.Game_State leversUp(Game.Game_State state) {
		if(state == Game.Game_State.LVL1_LEVER_ACT) {
			this.char_map[5][0] = 'S';
			this.char_map[6][0] = 'S';
			return Game.Game_State.LVL1;
		}
		return state;
	}
	
	/**
	 * Updates the game state
	 *
	 * @param      state  The state
	 * @param      pos    The position
	 *
	 * @return     the updated Game_State
	 */
	public Game.Game_State updateState(Game.Game_State state, char pos){
		
		if(pos == 'k') {
			if(state == Game.Game_State.LVL1) {
				char_map[8][7] = ' ';
				return Game.Game_State.LVL1_LEVER_ACT;
			}
		}
		else if(pos == 'S') {
			return Game.Game_State.LVL2;
		}

		return state;
	}
	
	/**
	 * Detects collision between the hero and his enemies
	 *
	 * @param      state  The state
	 * @param      hero   The hero
	 *
	 * @return     the updated Game_State
	 */
	public Game.Game_State character_collision(Game.Game_State state, Hero hero){
		
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
		
		return state;
	}
	
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
	public Game.Game_State map_Logic(Game.Game_State state, Hero hero, char c, BooleanHolder update){
		update.value = true;
		return state;
	}

	/**
	 * Moves the NPC's of the level
	 */
	public void move_npc() {
		this.guard.move(this.char_map);
	}

	/**
	 * Draws the characters.
	 *
	 * @param      aux   The auxiliary
	 */
	public void draw_characters(char[][] aux) {
		aux[this.guard.get_y()][this.guard.get_x()] = this.guard.getSprite();	
	}


	

	
}
