package dkeep.cli;

import java.util.concurrent.ThreadLocalRandom;

import dkeep.logic.Game;

public class DKInterface {
	Game dk = new Game();
	IO window = new IO();
	
	public void game_loop() {
		this.initizalize();
		char c = 'j';
		do {
			this.dk.state = this.dk.collision();
			window.print(this.dk.drawScreen());
			if(this.dk.state != Game.Game_State.LOSE) {
				c = window.read();
			}
			if(c == 'q') {
				this.dk.state = Game.Game_State.LOSE;
			}
			this.dk.updateGame(c);

		} while (!(this.dk.state.equals(Game.Game_State.LOSE)) && !(this.dk.state.equals(Game.Game_State.WIN)));
		window.print(this.dk.drawScreen());
		System.out.println();
		if(this.dk.state.equals(Game.Game_State.LOSE)) {
			System.out.println("Game Over. Try again");
		}
		else {
			System.out.println("Congratulations! You Win the game");
		}
	}
	
	public void initizalize() {
		int numOgres, guard;
		numOgres = ThreadLocalRandom.current().nextInt(1,4);
		guard = ThreadLocalRandom.current().nextInt(0,3);
		this.dk.initialize(1, numOgres, guard, null);
	}
	
}
