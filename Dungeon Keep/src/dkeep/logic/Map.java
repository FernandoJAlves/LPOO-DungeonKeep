package dkeep.logic;

public class Map {
	private char[][] char_map = {
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
	private char[][] char_map2 = {
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
	
	
	
	public char[][] getMap(Game.Game_State state) {
		if(state == Game.Game_State.LVL1)
			return this.char_map;
		return this.char_map2;
	}
	
	public Game.Game_State leversUp(Game.Game_State state) {
		if(state == Game.Game_State.LEVER_ACT1) {
			this.char_map[5][0] = 'S';
			this.char_map[6][0] = 'S';
			return Game.Game_State.LVL1;
		}
		else if(state == Game.Game_State.KEY_PICKED) {
			this.char_map2[1][7] = ' ';
			return Game.Game_State.KEY_PICKED;
		}
		else if(state == Game.Game_State.KEY_TURNED) {
			return Game.Game_State.KEY_TURNED;
		}
		return Game.Game_State.LVL1;

	}
	
	public Game.Game_State turnKey(Game.Game_State state) {
			this.char_map2[1][0] = 'S';
			return Game.Game_State.KEY_TURNED;

	}
	
	
}
