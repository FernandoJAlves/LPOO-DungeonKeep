package dkeep.logic;

import dkeep.logic.Game.Game_State;
/**
 * Class for level 1.
 */
public class Level1 extends Map{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Guard guard;
	
	/**
	 * Constructs the object.
	 *
	 * @param      guard_number  The guard number
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
	 * { function_description }
	 *
	 * @param      state  The state
	 *
	 * @return     { description_of_the_return_value }
	 */
	public Game_State turnKey(Game_State state) {
		return state;
	}
	
	/**
	 * { function_description }
	 *
	 * @param      state  The state
	 *
	 * @return     { description_of_the_return_value }
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
	 * { function_description }
	 *
	 * @param      state  The state
	 * @param      pos    The position
	 *
	 * @return     { description_of_the_return_value }
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
	 * { function_description }
	 *
	 * @param      state  The state
	 * @param      hero   The hero
	 *
	 * @return     { description_of_the_return_value }
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
	 * { function_description }
	 *
	 * @param      state   The state
	 * @param      hero    The hero
	 * @param      c       { parameter_description }
	 * @param      update  The update
	 *
	 * @return     { description_of_the_return_value }
	 */
	public Game.Game_State map_Logic(Game.Game_State state, Hero hero, char c, BooleanHolder update){
		update.value = true;
		return state;
	}

	/**
	 * { function_description }
	 */
	public void move_npc() {
		this.guard.move(this.char_map);
	}

	/**
	 * Draws characters.
	 *
	 * @param      aux   The auxiliary
	 */
	public void draw_characters(char[][] aux) {
		aux[this.guard.get_y()][this.guard.get_x()] = this.guard.getSprite();	
	}


	

	
}
