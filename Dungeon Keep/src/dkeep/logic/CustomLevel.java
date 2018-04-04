package dkeep.logic;

import java.util.ArrayList;

import dkeep.logic.Game.Game_State;
/**
 * Class for custom level.
 */
public class CustomLevel extends Map {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1962158929520915113L;
	private ArrayList<Ogre> ogres = new ArrayList<>();
	private int hero_x;
	private int hero_y;
	private int key_x;
	private int key_y;
	/**
	 * Constructs the object.
	 *
	 * @param      map   The map
	 */
	public CustomLevel(char[][] map) {
		this.char_map = map;
		this.initialize();
	}
	/**
	 * { function_description }
	 */
	public void initialize() {
		for(int i  = 0; i < this.char_map.length;i++) {
			for(int j = 0; j < this.char_map[0].length;j++) {
				switch(char_map[i][j]) {
				case 'H':
					hero_x = j;
					hero_y = i;
					char_map[i][j] = ' ';
					break;
				case 'O':
					this.ogres.add(new Ogre(j,i));
					char_map[i][j] = ' ';
					break;
				case 'k':
					key_x = j;
					key_y = i;
					break;
				}
			}
		}
		
	}
	/**
	 * Gets the hero x.
	 *
	 * @return     The hero x.
	 */
	public int getHeroX() {
		return this.hero_x;
	}
	/**
	 * Gets the hero y.
	 *
	 * @return     The hero y.
	 */
	public int getHeroY() {
		return this.hero_y;
	}
	
	public ArrayList<Ogre> getOgres(){
		return this.ogres;
	}

	@Override
	public Game_State leversUp(Game_State state) {
		this.char_map[key_y][key_x] = ' ';
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
		if(pos == 'k') {
			return Game.Game_State.CLVL_KEY;
		}
		
		else if(pos == 'S') {
			return Game.Game_State.WIN;
		}

		return state;
	}

	@Override
	public Game_State character_collision(Game_State state, Hero hero) {
		return super.ogreCollision(state, hero, this.ogres);
		
	}

	@Override
	public Game_State map_Logic(Game_State state, Hero hero, char c, BooleanHolder update) {
		update.value = true;
		return state;
	}

	@Override
	public void move_npc() {
		for (int k = 0; k < this.ogres.size(); k++) {
			this.ogres.get(k).move(this.char_map);
			ogres.get(k).club_logic(this.char_map);
		}
	}

	@Override
	public void draw_characters(char[][] aux) {
		for (int i = 0; i < this.ogres.size(); i++) {

			if (aux[this.ogres.get(i).club_hit.y][this.ogres.get(i).club_hit.x] == 'k') {
				aux[this.ogres.get(i).club_hit.y][this.ogres.get(i).club_hit.x] = '$';
			} else {
				aux[this.ogres.get(i).club_hit.y][this.ogres.get(i).club_hit.x] = '*';
			}
			
			if (aux[this.ogres.get(i).get_y()][this.ogres.get(i).get_x()] == 'k') {
				aux[this.ogres.get(i).get_y()][this.ogres.get(i).get_x()] = '$';
			} else {
				aux[this.ogres.get(i).get_y()][this.ogres.get(i).get_x()] = this.ogres.get(i).getSprite();
			}
			
		}	
	}

}
