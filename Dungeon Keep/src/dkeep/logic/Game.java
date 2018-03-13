package dkeep.logic;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Game {
	private Map map = new Map();
	private Hero hero = new Hero();
	private Guard guard;
	private ArrayList<Ogre> ogres;
	public enum Game_State {LVL1, LVL2, LEVER_ACT1, KEY_PICKED, KEY_TURNED, WIN, LOSE};
	public Game_State state = Game.Game_State.LVL1;
	
	public Game(){
		int guard_number = ThreadLocalRandom.current().nextInt(0,3);
		guard_number = 1;
		switch(guard_number) {
		case 0:
			guard = new Rookie();
			break;
		case 1:
			guard = new Drunken();
			break;
		case 2:
			guard = new Suspicious();
			break;
		default:
			guard = new Rookie();
		}
		
		ogres = ogre_generator();
		
	}
	
	public void drawScreen() {
		for (int i = 0; i < 17; i++) {
			System.out.println();
		}

		if (this.state.equals(Game_State.KEY_PICKED)) {
			hero.pick_key();
		}

		if (this.state.equals(Game_State.LEVER_ACT1) || this.state.equals(Game_State.KEY_TURNED)
				|| this.state.equals(Game_State.KEY_PICKED)) {
			this.state = map.leversUp(this.state);
		}

		if (this.state == Game.Game_State.LVL1) {
			for (int i = 0; i < map.getMap(this.state).length; i++) {
				for (int j = 0; j < map.getMap(this.state)[0].length; j++) {

					if (i == guard.get_y() && j == guard.get_x()) {
						System.out.print(guard.getSprite());
					} else if (i == hero.get_y() && j == hero.get_x()) {
						System.out.print('H');
					} else {
						System.out.print(this.map.getMap(this.state)[i][j]);
					}
				}
				System.out.println();
			}
		}

		else if (this.state == Game.Game_State.LVL2 || this.state == Game.Game_State.KEY_PICKED
				|| this.state == Game.Game_State.KEY_TURNED) {

			for (int i = 0; i < map.getMap(this.state).length; i++) {
				for (int j = 0; j < map.getMap(this.state)[0].length; j++) {
						if (this.getOgre(j, i) != null) {
							if (this.getOgre(7, 1) != null && j == 7 && i == 1) {
								System.out.print('$');
							} else {
								System.out.print(this.getOgre(7, 1).getSprite());
							}
						} else if (i == hero.get_y() && j == hero.get_x()) {
							System.out.print(this.hero.getSprite());
						} else if (this.isHit(j, i)) {
							if (this.isHit(7, 1)) {
								System.out.print('$');
							} else {
								System.out.print('*');
							}
						} else {
							System.out.print(this.map.getMap(this.state)[i][j]);
						}
					}
				System.out.println();
			}
		}
	}
	
	public Game.Game_State collision() {
		if (this.state == Game.Game_State.LVL1 || this.state == Game.Game_State.LEVER_ACT1) {
			if (hero.get_y() == (guard.get_y()) && hero.get_x() == (guard.get_x())) {
				if (guard.getSprite() != 'g') {
					return Game.Game_State.LOSE;
				}
			}
			if (hero.get_y() == (guard.get_y() - 1) && hero.get_x() == (guard.get_x())) {
				if (guard.getSprite() != 'g') {
					return Game.Game_State.LOSE;
				}
			}
			if (hero.get_y() == (guard.get_y()) && hero.get_x() == (guard.get_x() - 1)) {
				if (guard.getSprite() != 'g') {
					return Game.Game_State.LOSE;
				}
			}
			if (hero.get_y() == (guard.get_y() + 1) && hero.get_x() == (guard.get_x())) {
				if (guard.getSprite() != 'g') {
					return Game.Game_State.LOSE;
				}
			}
			if (hero.get_y() == (guard.get_y()) && hero.get_x() == (guard.get_x() + 1)) {
				if (guard.getSprite() != 'g') {
					return Game.Game_State.LOSE;
				}
			}
		}
		if (this.state == Game.Game_State.LVL2 || this.state == Game.Game_State.KEY_TURNED
				|| this.state == Game.Game_State.KEY_PICKED) {

			for (int k = 0; k < this.ogres.size(); k++) {
				
				if(hero.attack().x == ogres.get(k).get_x() && hero.attack().y == ogres.get(k).get_y()) {
					ogres.get(k).stun();
				}
				
				else if(ogres.get(k).isStunned()){

				if (hero.get_y() == (ogres.get(k).get_y()) && hero.get_x() == (ogres.get(k).get_x())) {
					return Game.Game_State.LOSE;
				}

				if (hero.get_y() == (ogres.get(k).get_y() - 1) && hero.get_x() == (ogres.get(k).get_x())) {
					return Game.Game_State.LOSE;
				}
				if (hero.get_y() == (ogres.get(k).get_y()) && hero.get_x() == (ogres.get(k).get_x() - 1)) {
					return Game.Game_State.LOSE;
				}
				if (hero.get_y() == (ogres.get(k).get_y() + 1) && hero.get_x() == (ogres.get(k).get_x())) {
					return Game.Game_State.LOSE;
				}
				if (hero.get_y() == (ogres.get(k).get_y()) && hero.get_x() == (ogres.get(k).get_x() + 1)) {
					return Game.Game_State.LOSE;
				}
				}
			}
		}

		if (this.state == Game.Game_State.LVL2 || this.state == Game.Game_State.KEY_TURNED
				|| this.state == Game.Game_State.KEY_PICKED) {

			int x_t;
			int y_t;

			for (int k = 0; k < this.ogres.size(); k++) {
				
			x_t = this.ogres.get(k).club_hit.x;
			y_t = this.ogres.get(k).club_hit.y;

			if (hero.get_y() == (y_t) && hero.get_x() == (x_t)) {
				return Game.Game_State.LOSE;
			}
			if (hero.get_y() == (y_t - 1) && hero.get_x() == (x_t)) {
				return Game.Game_State.LOSE;
			}
			if (hero.get_y() == (y_t) && hero.get_x() == (x_t - 1)) {
				return Game.Game_State.LOSE;
			}
			if (hero.get_y() == (y_t + 1) && hero.get_x() == (x_t)) {
				return Game.Game_State.LOSE;
			}
			if (hero.get_y() == (y_t) && hero.get_x() == (x_t + 1)) {
				return Game.Game_State.LOSE;
			}
			}
		}

		return this.state;
	}
	
	
	
	public Game.Game_State updateState() {
		
		char pos = map.getMap(this.state)[hero.get_y()][hero.get_x()];
		Game.Game_State g = this.state;
		
		if(pos == 'k') {
			if(g == Game.Game_State.LVL1)
				return Game.Game_State.LEVER_ACT1;
			else if(g == Game.Game_State.LVL2)
				return Game.Game_State.KEY_PICKED;
		}
		if(pos == 'S') {
			if(g == Game.Game_State.LVL1) {
				hero.set_x(1);
				hero.set_y(7);
				hero.setSprite('A');
				return Game.Game_State.LVL2;
			}

			else if(g == Game.Game_State.LVL2)
				return Game.Game_State.WIN;
			return Game.Game_State.WIN;
		}

		
		return g;
		
	}
		
	public void updateGame(char c) {

		if(this.state == Game.Game_State.KEY_PICKED && hero.get_x() == 1 && hero.get_y() == 1) {
			this.state = map.turnKey(this.state);
		}
		else {
			this.hero.set_direction(c);
			this.hero.move(this.map.getMap(this.state));
			this.state = this.updateState();
		}
		

		
		if (this.state == Game.Game_State.LVL1) {
			this.guard.move(this.map.getMap(this.state));
		} else if (this.state == Game.Game_State.LVL2 || this.state == Game.Game_State.KEY_PICKED
				|| this.state == Game.Game_State.KEY_TURNED) {
			for(int k = 0;k < this.ogres.size();k++) {
			this.ogres.get(k).move(this.map.getMap(this.state));
			ogres.get(k).club_logic(map.getMap(this.state));
			}
		}
	}

		
	
	public void close() {
		
	}
	
	public ArrayList<Ogre> ogre_generator(){
		int numOgres = ThreadLocalRandom.current().nextInt(2,5);
		int x,y;
		ArrayList<Ogre> ogres = new ArrayList<>();
		for(int i = 0;i < numOgres;i++) {
			x = ThreadLocalRandom.current().nextInt(3,8);
			y = ThreadLocalRandom.current().nextInt(3,8);
			ogres.add(new Ogre(x,y));
		}
		return ogres;
	}
	
	
	public Ogre getOgre(int x, int y) {
		for(int k = 0;k < this.ogres.size();k++) {
			if(this.ogres.get(k).get_x() == x && this.ogres.get(k).get_y() == y) {
				return this.ogres.get(k);
			}
		}
		return null;
	}
	
	public boolean isHit(int x, int y) {
		for(int k = 0;k < this.ogres.size();k++) {
			if(this.ogres.get(k).club_hit.x == x && this.ogres.get(k).club_hit.y == y) {
				return true;
			}
		}
		return false;
		
	}
}
