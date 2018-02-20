import java.util.Scanner;

public class Game {
	private Map map = new Map();
	private Hero hero = new Hero();
	private Scanner s = new Scanner(System.in);
	public enum Game_State {START, LEVER_ACT, WIN, LOSE};
	public Game_State state = Game.Game_State.START;
	
	public void drawScreen() {
		for(int i = 0; i < 17; i++) {
			System.out.println();
		}
		
		if(this.state.equals(Game_State.LEVER_ACT)) {
			map.leversUp();
		}
		
		for(int i = 0; i < 10;i++) {
			for(int j = 0; j < 10; j++) {

				if(i == hero.get_y() && j == hero.get_x()) {
					System.out.print('H');
				}
				else {
					System.out.print(this.map.getMap()[i][j]);
				}
			}
			System.out.println();
		}
	}
	public char input() {
		System.out.println();
		System.out.print("Move: ");
		return s.next().charAt(0);
	}
	
	public void move_hero(char c) {
		if(c == 'w'){
			if(hero.get_y() == 0) {
				return;
			}
			char temp = this.map.getMap()[hero.get_y()-1][hero.get_x()];
			if(temp == 'X' || temp == 'I') {
				return;
			}
			hero.set_y(hero.get_y() - 1);
			
		}
		else if(c == 'd') {
			if(hero.get_x() == 9) {
				return;
			}
			char temp = this.map.getMap()[hero.get_y()][hero.get_x() + 1];
			if(temp == 'X' || temp == 'I') {
				return;
			}
			hero.set_x(hero.get_x() + 1);
			
		}
		else if(c == 's') {
			if(hero.get_y() == 9) {
				return;
			}
			char temp = this.map.getMap()[hero.get_y()+1][hero.get_x()];
			if(temp == 'X' || temp == 'I') {
				return;
			}
			hero.set_y(hero.get_y() + 1);
			
		}
		else if(c == 'a') {
			if(hero.get_x() == 0) {
				return;
			}
			char temp = this.map.getMap()[hero.get_y()][hero.get_x() - 1];
			if(temp == 'X' || temp == 'I') {
				return;
			}
			hero.set_x(hero.get_x() - 1);
			
		}
		
		char pos = this.map.getMap()[hero.get_y()][hero.get_x()];
		if(pos == 'k') {
			this.state = Game.Game_State.LEVER_ACT;
		}
		if(pos == 'S') {
			this.state = Game.Game_State.WIN;
		}
	}
	
	public void close() {
		s.close();
	}
}
