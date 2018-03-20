package dkeep.cli;

import java.awt.EventQueue;

import dkeep.logic.Game;

public class DKInterface {
	private Game dk = new Game();
	private Input input = new Input();
	private GraphicInterface window;
	private boolean ready = false;
	
	public DKInterface() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window = new GraphicInterface();
					ready = true;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}
	public void game_loop() {
		//while(!ready) {};
		char c = 'j';
		do {
			this.dk.state = this.dk.collision();
			
			if(this.dk.state != Game.Game_State.LOSE) {
				this.dk.drawScreen();
				c = input.read();
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
