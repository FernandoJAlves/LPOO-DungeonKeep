package dkeep.logic;

import java.io.Serializable;

public abstract class Map implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3133907343984778555L;
	protected char[][] char_map;
	
	public char[][] getMap(Game.Game_State state) {
		return this.char_map;
	}
	
	public abstract Game.Game_State leversUp(Game.Game_State state);
	
	public abstract Game.Game_State turnKey(Game.Game_State state);
	
	public abstract Game.Game_State updateState(Game.Game_State state, char pos);
	
	public abstract Game.Game_State character_collision(Game.Game_State state, Hero hero);
	
	public abstract Game.Game_State map_Logic(Game.Game_State state, Hero hero, char c, BooleanHolder update);
	
	public abstract void move_npc();
	
	public abstract void draw_characters(char[][] aux);

	
}
