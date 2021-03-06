package dkeep.logic;

import java.io.Serializable;

/**
 * Game.java - a class for game logic.
 */
public class Game implements Serializable{

	private static final long serialVersionUID = 9058252653605357285L;
	private Map map;
	private Hero hero = new Hero();
	private int numOgres;
	public enum Game_State {PREPARE,LVL1, LVL2, LVL1_LEVER_ACT, LVL2_KEY_PICKED, LVL2_KEY_TURNED,CLVL,CLVL_KEY, WIN, LOSE, TEST, TEST_KEYPICKED};
	public Game_State state = Game.Game_State.PREPARE;
	/**
	 * Constructs the object.
	 */
	public Game(){}
	/**
	 * initializes the attributes of the Game Object.
	 *
	 * @param      level             The number of the level
	 * @param      numOgres          The number of ogres
	 * @param      guardPersonality  The personality of the guard
	 * @param      char_map          The map
	 */
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
	/**
	 * Draws the game state in a matrix of chars.
	 *
	 * @return     { description_of_the_return_value }
	 */
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
	/**
	 * Checks if there is collision in the actual game state and updates the same one
	 *
	 * @return     The updated game state.
	 */
	public Game.Game_State collision() {
		return this.map.character_collision(this.state, this.hero);
	}
	/**
	 * Updates the game state
	 *
	 * @return     the updated game state.
	 */
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
	/**
	 * updates the game
	 *
	 * @param      c     the user input char
	 */
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
	/**
	 * Prints the char matrix that represents the game state in a String
	 *
	 * @param      m     { parameter_description }
	 *
	 * @return     { description_of_the_return_value }
	 */
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
	
	/**
	 * Gets the map.
	 *
	 * @return     The map.
	 */
	public Map getMap() {
		return this.map;
	}
	
	/**
	 * Sets the state.
	 *
	 * @param      test  The test state
	 */
	public void setState(Game.Game_State test) {
		this.state = test;
	}
	/**
	 * Sets the position of the hero.
	 *
	 * @param      i     the new x coord
	 * @param      j     the new y coord
	 */
	public void setPosHero(int i, int j) {
		this.hero.set_x(i);
		this.hero.set_y(j);		
	}

	/**
	 * Gets the position of the hero.
	 *
	 * @return     The coordinates of the position of the Hero.
	 */
	public Point getHeroPosition() {
		Point c = new Point(this.hero.get_x(),this.hero.get_y());
		return c;
	}

	/**
	 * Moves the hero
	 *
	 * @param      c     the movement input char
	 */
	public void moveHero(char c) {
		this.hero.set_direction(c);
		this.hero.move(this.map.getMap(this.state));
	}
	
	/**
	 * Returns the hero.
	 *
	 * @return     the Hero.
	 */
	public Hero getHero() {
		return this.hero;
	}

	/**
	 * Determines if gameover.
	 *
	 * @return     True if gameover, False otherwise.
	 */
	public boolean isGameover() {
		if(this.state == Game_State.LOSE) {
			return true;
		}
		return false;
	}
	
}
