package dkeep.cli;

import java.awt.EventQueue;

import dkeep.logic.Game;

public class DKInterface {
	Game dk = new Game();
	GraphicInterface window;
	
	public DKInterface() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window = new GraphicInterface();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void game_loop() {
		char c = 'j';
		do {
			this.dk.state = this.dk.collision();
			this.dk.drawScreen();
			if(this.dk.state != Game.Game_State.LOSE) {
				c = window.read();
			}
			if(c == 'q') {
				this.dk.state = Game.Game_State.LOSE;
			}
			this.dk.updateGame(c);

		} while (!(this.dk.state.equals(Game.Game_State.LOSE)) && !(this.dk.state.equals(Game.Game_State.WIN)));
		System.out.println();
		if(this.dk.state.equals(Game.Game_State.LOSE)) {
			System.out.println("Game Over. Try again");
		}
		else {
			System.out.println("Congratulations! You Win the game");
		}
	}
}
