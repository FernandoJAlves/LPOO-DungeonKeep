package dkeep.logic;

import dkeep.logic.Game.Game_State;

public abstract class Map {
	protected char[][] char_map;
	
	public char[][] getMap(Game.Game_State state) {
		return this.char_map;
	}
	
	public abstract Game.Game_State leversUp(Game.Game_State state);
	
	public abstract Game.Game_State turnKey(Game.Game_State state);
	
	public abstract Game.Game_State updateState(Game.Game_State state, char pos);
	
	public abstract Game.Game_State character_collision(Game.Game_State state, Hero hero);
	
	public abstract Game.Game_State map_Logic(Game.Game_State state, Hero hero, char c);
	
	public abstract void move_npc();

	
}
