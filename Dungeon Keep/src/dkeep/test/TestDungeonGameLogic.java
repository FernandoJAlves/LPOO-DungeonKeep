package dkeep.test;

import static org.junit.Assert.*;
import org.junit.Test;
import dkeep.logic.*;

public class TestDungeonGameLogic{
		
	@Test
	public void testMoveHeroIntoToFreeCell() {
		
		Game game = new Game();
		game.setStateTest();
		game.setPosHero(1, 1);
		assertEquals(new Point(1,1), game.getHeroPosition());
		game.moveHero('s'); // move hero down.
		assertEquals(new Point(1,2), game.getHeroPosition());
	}
	
	
	@Test
	public void testMoveHeroUp() {
		
		Game game = new Game();
		game.setStateTest();
		game.setPosHero(2, 2);
		assertEquals(new Point(2,2), game.getHeroPosition());
		game.moveHero('w'); // move hero up.
		assertEquals(new Point(2,1), game.getHeroPosition());
	}
	
	@Test
	public void testMoveHeroDown() {
		
		Game game = new Game();
		game.setStateTest();
		game.setPosHero(2, 2);
		assertEquals(new Point(2,2), game.getHeroPosition());
		game.moveHero('s'); // move hero down.
		assertEquals(new Point(2,3), game.getHeroPosition());
	}
	
	@Test
	public void testMoveHeroRight() {
		
		Game game = new Game();
		game.setStateTest();
		game.setPosHero(2, 2);
		assertEquals(new Point(2,2), game.getHeroPosition());
		game.moveHero('d'); // move hero right.
		assertEquals(new Point(3,2), game.getHeroPosition());
	}
	
	@Test
	public void testMoveHeroLeft() {
		
		Game game = new Game();
		game.setStateTest();
		game.setPosHero(2, 2);
		assertEquals(new Point(2,2), game.getHeroPosition());
		game.moveHero('a'); // move hero left.
		assertEquals(new Point(1,2), game.getHeroPosition());
	}
	
	@Test
	public void testHeroIsCapturedByGuard() {
		
		Game game = new Game();
		game.setStateTest();
		((LevelTest)game.getMap()).guard.set_x(3);
		((LevelTest)game.getMap()).guard.set_y(1);
		game.setPosHero(1, 1);
		assertFalse(game.isGameover());
		game.moveHero('d'); // move hero to the right.
		game.setState(game.collision());
		assertTrue(game.isGameover());
	}
	
	@Test
	public void testHeroIsCapturedByOgre() {
		
		Game game = new Game();
		game.setStateTest();
		((LevelTest)game.getMap()).ogres.add(new Ogre(2,1));
		game.setPosHero(1, 1);
		assertFalse(game.isGameover());
		//game.moveHero('d'); // simulate that the ogre moved to the side of the hero without him attacking
		game.setState(game.collision());
		assertTrue(game.isGameover());
	}

	
	@Test
	public void testHeroStunsOgre() {
		
		Game game = new Game();
		game.setStateTest();
		((LevelTest)game.getMap()).ogres.add(new Ogre(3,1));
		game.setPosHero(1, 1);
		assertFalse(game.isGameover());
		game.moveHero('d'); // move hero to the right.
		game.setState(game.collision());
		assertFalse(game.isGameover());
		assertTrue(((LevelTest)game.getMap()).ogres.get(0).isStunned());
		assertEquals(2, ((LevelTest)game.getMap()).ogres.get(0).get_stun_count());
	}

	@Test
	public void testKeyPicked() {
		
		Game game = new Game();
		game.setStateTest();
		game.setPosHero(1, 1);
		assertFalse(game.isGameover());
		game.moveHero('s'); // move hero downwards.
		game.moveHero('s'); // move hero downwards.
		game.setState(game.updateState());
		game.getMap().leversUp(game.state);
		assertEquals(new Point(1,3), game.getHeroPosition());
		assertFalse(game.isGameover());
		assertEquals(Game.Game_State.TEST_KEYPICKED, game.state);
		game.moveHero('d'); // move hero right.
		assertEquals(' ', game.getMap().getMap(game.state)[3][1]);
	}
	
	@Test
	public void testMoveAgainstWall() {
		
		Game game = new Game();
		game.setStateTest();
		game.setPosHero(1, 1);
		game.moveHero('a'); // move hero left.
		assertEquals(new Point(1,1), game.getHeroPosition());
		game.moveHero('d'); // move hero right.
		assertEquals(new Point(2,1), game.getHeroPosition());
	}
	
	@Test
	public void testMoveAgainstDoors() {
		
		Game game = new Game();
		game.setStateTest();
		char[][] temp = {
				{'X','X','X','X','X'},
				{'X',' ',' ',' ','I'},
				{'X',' ',' ',' ','X'},
				{'X',' ',' ',' ','S'},
				{'X','X','X','X','X'}
		};
		
		((LevelTest)game.getMap()).setTestMap(temp);
		game.setPosHero(3, 1);
		game.moveHero('d'); // move hero right.
		assertEquals(new Point(3,1), game.getHeroPosition());
		game.setPosHero(3, 3);
		game.moveHero('d'); // move hero right.
		assertEquals(new Point(4,3), game.getHeroPosition());
		game.setState(game.updateState());
		assertEquals(Game.Game_State.WIN, game.state);
	}
	
	@Test
	public void testRookieMovement() {
		
		Game game = new Game();
		game.initialize(1, 0, 0, null);
		assertEquals(new Point(8,1), ((Level1)game.getMap()).getGuard().getPoint());
		((Level1)game.getMap()).move_npc(); //move the guard once
		assertEquals(new Point(7,1), ((Level1)game.getMap()).getGuard().getPoint());
		((Level1)game.getMap()).move_npc(); //move the guard once
		assertEquals(new Point(7,2), ((Level1)game.getMap()).getGuard().getPoint());
	}
	
	@Test(timeout=1000)
	public void testDrunkenSleepMovement() {
		
		Game game = new Game();
		game.initialize(1, 0, 1, null);
		assertEquals(new Point(8,1), ((Level1)game.getMap()).getGuard().getPoint());
		boolean asleep = false;
		while(!asleep) {
			((Level1)game.getMap()).move_npc();
			if(((Drunken)(((Level1)game.getMap()).getGuard())).getSleepy()) {
				asleep = true;
			}
		}
	}

	@Test(timeout=1000)
	public void testDrunkenInvertedMovement() {
		
		Game game = new Game();
		game.initialize(1, 0, 1, null);
		assertEquals(new Point(8,1), ((Level1)game.getMap()).getGuard().getPoint());
		boolean inverted = false;
		while(!inverted) {
			((Level1)game.getMap()).move_npc();
			if(((Drunken)(((Level1)game.getMap()).getGuard())).getInverted()) {
				inverted = true;
			}
		}
	}
	
	
	
}

