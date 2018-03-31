package dkeep.logic;

import java.util.ArrayList;

import dkeep.logic.Game.Game_State;

public class CustomLevel extends Map {
	private ArrayList<Ogre> ogres = new ArrayList<>();
	private int hero_x;
	private int hero_y;
	private int key_x;
	private int key_y;
	
	public CustomLevel(char[][] map) {
		this.char_map = map;
		this.initialize();
	}
	
	public void initialize() {
		for(int i  = 0; i < this.char_map.length;i++) {
			for(int j = 0; j < this.char_map[0].length;j++) {
				switch(char_map[j][i]) {
				case 'H':
					hero_x = j;
					hero_y = i;
					break;
				case 'O':
					this.ogres.add(new Ogre(j,i));
					break;
				case 'k':
					key_x = j;
					key_y = i;
					break;
				}
			}
		}
	}
	
	public int getHeroX() {
		return this.hero_x;
	}
	
	public int getHeroY() {
		return this.hero_y;
	}

	@Override
	public Game_State leversUp(Game_State state) {
		this.char_map[key_x][key_y] = ' ';
		for(int i = 0; i < this.char_map.length; i++) {
			for(int j = 0; j < this.char_map[0].length;j++) {
				if(char_map[i][j] == 'I') {
					char_map[i][j] = 'S';
				}
			}
		}
		return Game.Game_State.CLVL_KEY;
	}

	@Override
	public Game_State turnKey(Game_State state) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Game_State updateState(Game_State state, char pos) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Game_State character_collision(Game_State state, Hero hero) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Game_State map_Logic(Game_State state, Hero hero, char c, BooleanHolder update) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void move_npc() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw_characters(char[][] aux) {
		// TODO Auto-generated method stub
		
	}

}
