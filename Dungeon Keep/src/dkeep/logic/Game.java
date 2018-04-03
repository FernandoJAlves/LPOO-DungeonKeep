package dkeep.logic;


public class Game {
	private Map map;
	private Hero hero = new Hero();
	private int numOgres;
	public enum Game_State {PREPARE,LVL1, LVL2, LVL1_LEVER_ACT, LVL2_KEY_PICKED, LVL2_KEY_TURNED,CLVL,CLVL_KEY, WIN, LOSE, TEST, TEST_KEYPICKED};
	public Game_State state = Game.Game_State.PREPARE;
	
	public Game(){}
	
	public void initialize(int level,int numOgres, int guardPersonality, char[][] char_map){
		this.numOgres = numOgres;
		this.hero.set_pos(1,1);
		switch(level) {
		case 1:
			this.map = new Level1(guardPersonality);
			this.state = Game.Game_State.LVL1;
			break;
		case 2:
			this.map = new Level2(numOgres);
			this.state = Game.Game_State.LVL2;
			break;
		case 0:
			this.map = new CustomLevel(char_map);
			this.state = Game.Game_State.CLVL;
			this.hero.set_pos(((CustomLevel)this.map).getHeroX(),((CustomLevel)this.map).getHeroY());
			break;
		}
	}
	
	public char[][] drawScreen() {
		
		// Esta parte pode ser melhorada, n�o queria era criar mais fun�oes sen�o fica uma confus�o nos nomes, depois vemos
		
		if (this.state.equals(Game_State.LVL2_KEY_PICKED)) {
			hero.pick_key();
		}

		if (this.state.equals(Game_State.LVL1_LEVER_ACT) || this.state.equals(Game_State.LVL2_KEY_TURNED)
				|| this.state.equals(Game_State.LVL2_KEY_PICKED) || this.state.equals(Game_State.CLVL_KEY)) {
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

	//Testing methods
	
	public Map getMap() {
		return this.map;
	}
	
	public void setStateTest() {
		this.map = new LevelTest();
		this.state = Game.Game_State.TEST;
	}
	
	public void setState(Game.Game_State test) {
		this.state = test;
	}
	
	
/*	
	public void setTestGuard(Game_State test, int x, int y) {
		if(Game.Game_State.TEST == test) {
			this.map = new LevelTest();
		}
		
		((LevelTest)this.map).guard.set_x(x);
		((LevelTest)this.map).guard.set_y(y);
		
	}
	
	public void setTestOgre(Game_State test, int x, int y) {
		if(Game.Game_State.TEST == test) {
			this.map = new LevelTest();
		}
		((LevelTest)this.map).ogres.add(new Ogre(x,y));
	}
	*/
	
	public void setPosHero(int i, int j) {
		this.hero.set_x(i);
		this.hero.set_y(j);		
	}

	public Point getHeroPosition() {
		Point c = new Point(this.hero.get_x(),this.hero.get_y());
		return c;
	}

	public void moveHero(char c) {
		this.hero.set_direction(c);
		this.hero.move(this.map.getMap(this.state));
	}

	public boolean isGameover() {
		if(this.state == Game_State.LOSE) {
			return true;
		}
		return false;
	}
	
	
}
