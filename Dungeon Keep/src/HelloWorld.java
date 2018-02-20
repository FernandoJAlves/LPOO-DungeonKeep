
public class HelloWorld {

	public static void main(String[] args) {
		Game game = new Game();
		char c = 'j';
		do {

			game.drawScreen();
			c = game.input();
			if(c == 'q') {
				game.state = Game.Game_State.LOSE;
			}
			game.move_hero(c);
		} while (!(game.state.equals(Game.Game_State.LOSE)) && !(game.state.equals(Game.Game_State.WIN)));
		System.out.println();
		if(game.state.equals(Game.Game_State.LOSE)) {
			System.out.println("Game Over. Try again");
		}
		else {
			System.out.println("Congratulations! You Win the game");
		}
		game.close();
	}

}
