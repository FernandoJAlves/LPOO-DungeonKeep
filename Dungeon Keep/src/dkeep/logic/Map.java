package dkeep.logic;

public abstract class Map {
	protected char[][] char_map;
	
	public char[][] getMap(Game.Game_State state) {
		return this.char_map;
	}
	
	public abstract Game.Game_State leversUp(Game.Game_State state);
	
	public abstract Game.Game_State turnKey(Game.Game_State state);
	
	public abstract Game.Game_State updateState(Game.Game_State state, char pos);
	
}
