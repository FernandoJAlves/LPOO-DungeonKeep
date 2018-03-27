package dkeep.logic;

public abstract class Map {
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
