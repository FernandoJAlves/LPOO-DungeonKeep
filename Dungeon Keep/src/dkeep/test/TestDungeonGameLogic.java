package dkeep.test;

import static org.junit.Assert.*;
import org.junit.Test;
import dkeep.logic.Game;
import dkeep.logic.Point;

public class TestDungeonGameLogic{
		
	@Test
	public void testMoveHeroIntoToFreeCell() {
		
		Game game = new Game();
		game.setState(Game.Game_State.TEST);
		game.setTestGuard(Game.Game_State.TEST, 3, 1);
		game.setPosHero(1, 1);
		assertEquals(new Point(1,1), game.getHeroPosition());
		game.moveHero('s'); // move hero down.
		assertEquals(new Point(1,2), game.getHeroPosition());
	}


	@Test
	public void testHeroIsCapturedByGuard() {
		
		Game game = new Game();
		game.setState(Game.Game_State.TEST);
		game.setTestGuard(Game.Game_State.TEST, 3, 1);
		game.setPosHero(1, 1);
		assertFalse(game.isGameover());
		game.moveHero('d'); // move hero to the right.
		game.setState(game.collision());
		assertTrue(game.isGameover());
	}
	
	@Test
	public void testHeroIsCapturedByOgre() {
		
		Game game = new Game();
		game.setState(Game.Game_State.TEST);
		game.setTestOgre(Game.Game_State.TEST, 3, 1);
		game.setPosHero(1, 1);
		assertFalse(game.isGameover());
		game.moveHero('d'); // move hero to the right.
		game.setState(game.collision());
		assertTrue(game.isGameover());
	}

	
	
	
	
}

