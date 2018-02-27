import java.util.Scanner;

public class Game {
	private Map map = new Map();
	private Hero hero = new Hero();
	private Guard guard = new Guard();
	private Ogre ogre = new Ogre();
	private Scanner s = new Scanner(System.in);
	public enum Game_State {LVL1, LVL2, LEVER_ACT1, KEY_PICKED, KEY_TURNED, WIN, LOSE};
	public Game_State state = Game.Game_State.LVL1;
	
	public void drawScreen() {
		for(int i = 0; i < 17; i++) {
			System.out.println();
		}
		
		if(this.state.equals(Game_State.KEY_PICKED)) {
			hero.pick_key();
		}
		
		if(this.state.equals(Game_State.LEVER_ACT1) || this.state.equals(Game_State.KEY_TURNED)) {
			this.state = map.leversUp(this.state);
		}
		
		if(this.state == Game.Game_State.LVL1) {
			for(int i = 0; i < map.getMap(this.state).length;i++) {
				for(int j = 0; j < map.getMap(this.state)[0].length; j++) {

					if(i == guard.get_y() && j == guard.get_x()) {
						System.out.print('G');
					}
					else if(i == hero.get_y() && j == hero.get_x()) {
						System.out.print('H');
					}
					else {
						System.out.print(this.map.getMap(this.state)[i][j]);
					}
				}
				System.out.println();
			}
		}
		
		else if(this.state == Game.Game_State.LVL2 || this.state == Game.Game_State.KEY_PICKED || this.state == Game.Game_State.KEY_TURNED) {
			for(int i = 0; i < map.getMap(this.state).length;i++) {
				for(int j = 0; j < map.getMap(this.state)[0].length; j++) {
					
					if(i == ogre.get_y() && j == ogre.get_x()) {
						if(ogre.get_x() == 7 && ogre.get_y() == 1) {
						System.out.print('$');
						}
						else {
						System.out.print('O');
						}
					}
					else if(i == hero.get_y() && j == hero.get_x()) {
						System.out.print(this.hero.getSprite());
					}
					else {
						System.out.print(this.map.getMap(this.state)[i][j]);
					}
				}
				System.out.println();
			}
		}
		

	}
	public char input() {
		System.out.println();
		System.out.print("Move: ");
		return s.next().charAt(0);
	}
	
	public Game.Game_State collision(){
		if(this.state == Game.Game_State.LVL1) {
		if(hero.get_y() == (guard.get_y()) && hero.get_x() == (guard.get_x())) {
			return Game.Game_State.LOSE;
		}
		if(hero.get_y() == (guard.get_y()-1) && hero.get_x() == (guard.get_x())) {
			return Game.Game_State.LOSE;
		}
		if(hero.get_y() == (guard.get_y()) && hero.get_x() == (guard.get_x()-1)) {
			return Game.Game_State.LOSE;
		}
		if(hero.get_y() == (guard.get_y()+1) && hero.get_x() == (guard.get_x())) {
			return Game.Game_State.LOSE;
		}
		if(hero.get_y() == (guard.get_y()) && hero.get_x() == (guard.get_x()+1)) {
			return Game.Game_State.LOSE;
		}
		}
		
		if(this.state == Game.Game_State.LVL2) {
		if(hero.get_y() == (ogre.get_y()) && hero.get_x() == (ogre.get_x())) {
			return Game.Game_State.LOSE;
		}
		if(hero.get_y() == (ogre.get_y()-1) && hero.get_x() == (ogre.get_x())) {
			return Game.Game_State.LOSE;
		}
		if(hero.get_y() == (ogre.get_y()) && hero.get_x() == (ogre.get_x()-1)) {
			return Game.Game_State.LOSE;
		}
		if(hero.get_y() == (ogre.get_y()+1) && hero.get_x() == (ogre.get_x())) {
			return Game.Game_State.LOSE;
		}
		if(hero.get_y() == (ogre.get_y()) && hero.get_x() == (ogre.get_x()+1)) {
			return Game.Game_State.LOSE;
		}
		}

		
		return this.state;
	}
	
	public void game_loop() {
		char c = 'j';
		do {
			
			this.state = this.collision();
			this.drawScreen();
			if(this.state != Game.Game_State.LOSE) {
				c = this.input();
			}
			if(c == 'q') {
				this.state = Game.Game_State.LOSE;
			}

			this.state = this.hero.move(c, this.state, this.map.getMap(this.state));
			if(this.state == Game.Game_State.LVL1) {
				this.guard.move(this.map.getMap(this.state));
			}
			else if(this.state == Game.Game_State.LVL2) {
				this.ogre.move(this.map.getMap(this.state));
			}

		} while (!(this.state.equals(Game.Game_State.LOSE)) && !(this.state.equals(Game.Game_State.WIN)));
		System.out.println();
		if(this.state.equals(Game.Game_State.LOSE)) {
			System.out.println("Game Over. Try again");
		}
		else {
			System.out.println("Congratulations! You Win the game");
		}
		
		
		
	}

	

	
	
	
	public void close() {
		s.close();
	}
}
