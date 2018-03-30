package dkeep.logic;

import dkeep.test.CellPosition;

public class Game {
	private Map map;
	private Hero hero = new Hero();
	private int numOgres;
	public enum Game_State {PREPARE,LVL1, LVL2, LVL1_LEVER_ACT, LVL2_KEY_PICKED, LVL2_KEY_TURNED, WIN, LOSE, TEST};
	public Game_State state = Game.Game_State.PREPARE;
	
	public Game(){}
	
	public void initialize(int level,int numOgres, int guardPersonality){
		this.numOgres = numOgres;
		this.hero.set_pos();
		switch(level) {
		case 1:
			this.map = new Level1(guardPersonality);
			this.state = Game.Game_State.LVL1;
			break;
		case 2:
			this.map = new Level2(numOgres);
			this.state = Game.Game_State.LVL2;
			break;
		}
	}
	
	public char[][] drawScreen() {
		
		// Esta parte pode ser melhorada, n�o queria era criar mais fun�oes sen�o fica uma confus�o nos nomes, depois vemos
		
		if (this.state.equals(Game_State.LVL2_KEY_PICKED)) {
			hero.pick_key();
		}

		if (this.state.equals(Game_State.LVL1_LEVER_ACT) || this.state.equals(Game_State.LVL2_KEY_TURNED)
				|| this.state.equals(Game_State.LVL2_KEY_PICKED)) {
			this.state = map.leversUp(this.state);
		}
		
		// At� aqui ^
		
		
		char [][] aux = new char[this.map.getMap(this.state)[0].length][];
		
		for(int i = 0; i < this.map.getMap(this.state).length;i++) {
			aux[i] = this.map.getMap(this.state)[i].clone();
		}

		aux[hero.get_y()][hero.get_x()] = hero.getSprite();
		
		this.map.draw_characters(aux);
		
		return aux;
	}
	
	public Game.Game_State collision() {
		return this.map.character_collision(this.state, this.hero);
	}
	
	public Game.Game_State updateState() {
		
		char pos = map.getMap(this.state)[hero.get_y()][hero.get_x()];
		Game.Game_State original_state = this.state;
		
		//atualiza o state de acordo com a logica do level
		Game.Game_State new_state = this.map.updateState(original_state, pos);
		
		
		//comparar os 2 states, se necess�rio fazer transi��o de levels
		if(original_state != new_state) {
			
			if(new_state == Game.Game_State.LVL2) {
				hero.set_x(1);
				hero.set_y(7);
				hero.setSprite('A');
				this.map = new Level2(numOgres);
			}
			
			//se adicionarmos mais levels, a sua transi��o ser� colocada aqui
		}
		
		return new_state;
	}
		
	public void updateGame(char c) {

		BooleanHolder update = new BooleanHolder();
		update.value = false;
		
		this.state = this.map.map_Logic(this.state, this.hero, c, update);
		
		if(update.value == true) {
			update.value = false;
			hero.set_direction(c);
			hero.move(this.map.getMap(this.state));
			state = this.updateState();
		}
		
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
