package dkeep.logic;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Level2 extends Map{

	/**
	 * 
	 */
	private static final long serialVersionUID = 696215245238900987L;
	private ArrayList<Ogre> ogres;
	
	public Level2(int numOgres){
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
		
		//gerar os ogres
		ogres = ogre_generator(numOgres);
		
	}
	
	public ArrayList<Ogre> getOgres(){
		return this.ogres;
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
	
	public Game.Game_State updateState(Game.Game_State state, char pos){
		
		if(pos == 'k') {
			if(state == Game.Game_State.LVL2)
				return Game.Game_State.LVL2_KEY_PICKED;
		}
		
		else if(pos == 'S') {
			return Game.Game_State.WIN;
		}

		return state;
	}
	
	public Game.Game_State character_collision(Game.Game_State state, Hero hero){
		return super.ogreCollision(state, hero, this.ogres);
	}
	
	
	public Game.Game_State map_Logic(Game.Game_State state, Hero hero, char c, BooleanHolder update){
		
		if(state == Game.Game_State.LVL2_KEY_PICKED && hero.get_x() == 1 && hero.get_y() == 1) {
			return this.turnKey(state);
		}
		
		update.value = true;
		return state;
	}
	
	
	public void move_npc() {
		for (int k = 0; k < this.ogres.size(); k++) {
			this.ogres.get(k).move(this.char_map);
			ogres.get(k).club_logic(this.char_map);
		}
	}
	
	public void draw_characters(char[][] aux) {
		for (int i = 0; i < this.ogres.size(); i++) {

			if (this.ogres.get(i).club_hit.x == 7 && this.ogres.get(i).club_hit.y == 1) {
				aux[1][7] = '$';
			} else {
				aux[this.ogres.get(i).club_hit.y][this.ogres.get(i).club_hit.x] = '*';
			}
			
			if (this.ogres.get(i).get_x() == 7 && this.ogres.get(i).get_y() == 1) {
				aux[1][7] = '$';
			} else {
				aux[this.ogres.get(i).get_y()][this.ogres.get(i).get_x()] = this.ogres.get(i).getSprite();
			}
		}	
	}
	
	
	
	
	
	public ArrayList<Ogre> ogre_generator(int numOgres){
		int x,y;
		ArrayList<Ogre> ogres = new ArrayList<>();
		for(int i = 0;i < numOgres;i++) {
			x = ThreadLocalRandom.current().nextInt(3,8);
			y = ThreadLocalRandom.current().nextInt(3,8);
			ogres.add(new Ogre(x,y));
		}
		return ogres;
	}
	
	
}
