package dkeep.logic;

import dkeep.logic.Game.Game_State;

public class Level1 extends Map{

	public Level1(){
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
	}
	
	public Game_State turnKey(Game_State state) {
		return state;
	}
	
	public Game.Game_State leversUp(Game.Game_State state) {
		if(state == Game.Game_State.LVL1_LEVER_ACT) {
			this.char_map[5][0] = 'S';
			this.char_map[6][0] = 'S';
			return Game.Game_State.LVL1;
		}
		return state;
	}
	
	public Game.Game_State updateState(Game.Game_State state, char pos){
		
		if(pos == 'k') {
			if(state == Game.Game_State.LVL1)
				return Game.Game_State.LVL1_LEVER_ACT;
		}
		else if(pos == 'S') {
			return Game.Game_State.LVL2;
		}

		return state;
	}



	

	
}
