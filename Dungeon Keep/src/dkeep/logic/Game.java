package dkeep.logic;


import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import dkeep.test.CellPosition;

public class Game {
	private Map map = new Level1();
	private Hero hero = new Hero();
	//private Guard guard;
	//private ArrayList<Ogre> ogres;
	public enum Game_State {LVL1, LVL2, LVL1_LEVER_ACT, LVL2_KEY_PICKED, LVL2_KEY_TURNED, WIN, LOSE, TEST};
	public Game_State state = Game.Game_State.LVL1;
	
	public Game(){}
	
	public String drawScreen() {
		
		if (this.state.equals(Game_State.LVL2_KEY_PICKED)) {
			hero.pick_key();
		}

		if (this.state.equals(Game_State.LVL1_LEVER_ACT) || this.state.equals(Game_State.LVL2_KEY_TURNED)
				|| this.state.equals(Game_State.LVL2_KEY_PICKED)) {
			this.state = map.leversUp(this.state);
		}
		
		char [][] aux = new char[this.map.getMap(this.state)[0].length][];
		
		for(int i = 0; i < this.map.getMap(this.state).length;i++) {
			aux[i] = this.map.getMap(this.state)[i].clone();
		}

		
		aux[hero.get_y()][hero.get_x()] = hero.getSprite();
		
		
		if (this.state == Game.Game_State.LVL1) {
			aux[((Level1)this.map).getGuard().get_y()][((Level1)this.map).getGuard().get_x()] = ((Level1)this.map).getGuard().getSprite();
		}
/*
		else if (this.state == Game.Game_State.LVL2 || this.state == Game.Game_State.LVL2_KEY_PICKED
				|| this.state == Game.Game_State.LVL2_KEY_TURNED) {
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
		*/
		return printMap(aux);
	}
	
	public Game.Game_State collision() {
		return this.map.character_collision(this.state, this.hero);
	}
	
	
	
	public Game.Game_State updateState() {
		
		char pos = map.getMap(this.state)[hero.get_y()][hero.get_x()];
		Game.Game_State original_state = this.state;

		
		//atualiza o state de acordo com a logica do level
		Game.Game_State new_state = this.map.updateState(original_state, pos);
		
		
		//comparar os 2 states, se necessário fazer transição de levels
		if(original_state != new_state) {
			
			if(new_state == Game.Game_State.LVL2) {
				hero.set_x(1);
				hero.set_y(7);
				hero.setSprite('A');
				this.map = new Level2();
			}
			
			//se adicionarmos mais levels, a sua transição será colocada aqui
			
		}
		
		return new_state;
		
	}
		
	public void updateGame(char c) {

		this.state = this.map.map_Logic(this.state, this.hero, c);
		
		this.hero.set_direction(c);
		this.hero.move(this.map.getMap(this.state));
		this.state = this.updateState();
		
		/*
		if(this.state == Game.Game_State.LVL2_KEY_PICKED && hero.get_x() == 1 && hero.get_y() == 1) {
			this.state = map.turnKey(this.state);
		}
		else {

		}
		*/
		
		
		//moving the npc's
		this.map.move_npc();

	}
	

	
	public String printMap(char[][] m) {
		
		String res = "";
		
		for(int i = 0; i < m.length;i++) {
			for(int j = 0; j < m[0].length;j++) {
				res += m[i][j];
			}
			res += "\n";
		}
		return res;
	}

	public void setState(Game_State test) {
		this.state = test;
	}

	public void setPosHero(int i, int j) {
		this.hero.set_x(i);
		this.hero.set_y(j);		
	}

	public CellPosition getHeroPosition() {
		CellPosition c = new CellPosition(this.hero.get_x(),this.hero.get_y());
		return c;
	}

	public void moveHero(char c) {
		this.hero.set_direction(c);
		this.hero.move(this.map.getMap(this.state));
	}

	public void setPosGuard(int i, int j) { 
		// TODO
		//this.map.guard.set_x(i);
		//this.map.guard.set_y(j);		
	}

	public boolean isGameover() {
		if(this.state == Game_State.LOSE) {
			return true;
		}
		return false;
	}
	
	
	
}
