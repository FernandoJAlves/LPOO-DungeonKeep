package dkeep.test;

import static org.junit.Assert.*;
import org.junit.Test;

import dkeep.cli.DKInterface;
import dkeep.logic.Game;
/*
public class TestDungeonGameLogic{
		
	@Test
	public void testMoveHeroIntoToFreeCell() {
		
		Game game = new Game();
		game.setState(Game.Game_State.TEST);
		game.setPosHero(1, 1);
		assertEquals(new CellPosition(1,1), game.getHeroPosition());
		game.moveHero('s'); // move hero down.
		assertEquals(new CellPosition(1,2), game.getHeroPosition());
	}


	@Test
	public void testHeroIsCapturedByGuard() {
		
		Game game = new Game();
		game.setState(Game.Game_State.TEST);
		game.setPosHero(1, 1);
		game.setPosGuard(3, 1);
		assertFalse(game.isGameover());
		game.moveHero('d'); // move hero to the right.
		game.setState(game.collision());
		assertTrue(game.isGameover());
	}

	
	
	
	
}

*/