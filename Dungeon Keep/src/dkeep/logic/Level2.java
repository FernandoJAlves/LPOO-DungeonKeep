package dkeep.logic;

public class Level2 extends Map{

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
	
	
}
