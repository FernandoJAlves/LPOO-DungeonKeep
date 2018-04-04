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
	
	@Test(timeout=1000)
	public void testSuspiciousInvertedMovement() {
		
		Game game = new Game();
		game.initialize(1, 0, 2, null);
		assertEquals(new Point(8,1), ((Level1)game.getMap()).getGuard().getPoint());
		boolean inverted = false;
		
		//TODO
		/*
		while(!inverted) {
			((Level1)game.getMap()).move_npc();
			if(((Suspicious)(((Level1)game.getMap()).getGuard())).getInverted()) {
				inverted = true;
			}
		}
		
		*/
	}
	
	@Test
	public void testOgreRandom() {
		
		Game game = new Game();
		game.initialize(2, 1, 0, null);
		
		//TODO
		/*
		((Level2)game.getMap()).getOgres().get(0).set_x(4);
		((Level2)game.getMap()).getOgres().get(0).set_y(3);
		boolean expected_pos = true;
		int counter = 0;

		while (counter < 100) {
			((Level2) game.getMap()).move_npc();

			expected_pos = (((Level2) game.getMap()).getOgres().get(0).getPoint().equals(new Point(4, 2))
					|| ((Level2) game.getMap()).getOgres().get(0).getPoint().equals(new Point(4, 4))
					|| ((Level2) game.getMap()).getOgres().get(0).getPoint().equals(new Point(5, 3))
					|| ((Level2) game.getMap()).getOgres().get(0).getPoint().equals(new Point(3, 3)));
	
			((Level2)game.getMap()).getOgres().get(0).set_x(4);
			((Level2)game.getMap()).getOgres().get(0).set_y(3);
			counter++;
		}
		
		assertTrue(expected_pos);
		*/
	}
	
	
	@Test
	public void testOgreClubRandom() {
		
		Game game = new Game();
		game.initialize(2, 1, 0, null);
		
		//TODO
		/*
		((Level2)game.getMap()).getOgres().get(0).set_x(4);
		((Level2)game.getMap()).getOgres().get(0).set_y(3);
		boolean expected_pos = true;
		int counter = 0;

		while (counter < 100) {
			((Level2)game.getMap()).getOgres().get(0).club_logic(((Level2)game.getMap()).getMap(null));

			expected_pos = (((Level2) game.getMap()).getOgres().get(0).club_hit.equals(new Point(4, 2))
					|| ((Level2) game.getMap()).getOgres().get(0).club_hit.equals(new Point(4, 4))
					|| ((Level2) game.getMap()).getOgres().get(0).club_hit.equals(new Point(5, 3))
					|| ((Level2) game.getMap()).getOgres().get(0).club_hit.equals(new Point(3, 3)));

			counter++;
		}
		
		assertTrue(expected_pos);
		*/
	}
	
	@Test
	public void testHeroIsCapturedByGuardLvl1() {
		
		Game game = new Game();
		game.initialize(1, 0, 0, null);
		((Level1)game.getMap()).getGuard().set_x(3);
		((Level1)game.getMap()).getGuard().set_y(1);
		game.setPosHero(1, 1);
		assertFalse(game.isGameover());
		game.moveHero('d'); // move hero to the right.
		game.setState(game.collision());
		assertTrue(game.isGameover());
	}
	
	@Test
	public void testHeroStunsOgreLvl2() {
		
		Game game = new Game();
		game.initialize(2, 1, 0, null);
		
		//TODO
		/*
		((Level1)game.getMap()).getOgres().add(new Ogre(3,1));
		game.setPosHero(1, 1);
		assertFalse(game.isGameover());
		game.moveHero('d'); // move hero to the right.
		game.setState(game.collision());
		assertFalse(game.isGameover());
		assertTrue(((Level1)game.getMap()).ogres.get(0).isStunned());
		assertEquals(2, ((Level1)game.getMap()).ogres.get(0).get_stun_count());
		*/
	}
	
	
	
	@Test
	public void testHeroKeyInteractionCustomLvl() {
		
		Game game = new Game();
		char[][] temp = {
				{'X','X','X','X','X'},
				{'X','H','k',' ','I'},
				{'X',' ',' ',' ','X'},
				{'X',' ',' ',' ','X'},
				{'X','X','X','X','X'}
		};
		game.initialize(0, 0, 0, temp);
		assertEquals(Game.Game_State.CLVL, game.state);
		assertEquals('I', ((CustomLevel)game.getMap()).getMap(game.state)[1][4]);
		game.moveHero('d'); // move hero to the right.
		game.state = ((CustomLevel)game.getMap()).leversUp(game.state);
		assertEquals(Game.Game_State.CLVL_KEY, game.state);
		assertEquals('S', ((CustomLevel)game.getMap()).getMap(game.state)[1][4]);
		game.moveHero('d'); // move hero to the right.
		//game.updateGame('d');
		game.moveHero('d'); // move hero to the right.
		game.state = game.updateState();
		assertEquals(Game.Game_State.WIN, game.state);
	}
	
	@Test
	public void testOgreMovesCustomLvl() {
		
		Game game = new Game();
		char[][] temp = {
				{'X','X','X','X','X','X','X'},
				{'X','H',' ',' ',' ',' ','I'},
				{'X',' ',' ',' ','O',' ','X'},
				{'X',' ',' ',' ',' ',' ','X'},
				{'X','X','X','X','X','X','X'}
		};
		game.initialize(0, 0, 0, temp);
		assertEquals(Game.Game_State.CLVL, game.state);
		//TODO
		
		//assertEquals(new Point(4,2), ((CustomLevel)game.getMap()).getOgres().get(0).getPoint());
		game.updateGame('d');
		//assertNotEquals(new Point(4,2), ((CustomLevel)game.getMap()).getOgres().get(0).getPoint());
	}
	
	@Test
	public void testDrawCustomLvl() {
		
		Game game = new Game();
		char[][] temp = {
				{'X','X','X','X','X','X','X'},
				{'X','H',' ',' ',' ',' ','I'},
				{'X',' ',' ',' ','O',' ','X'},
				{'X',' ',' ',' ',' ',' ','X'},
				{'X','X','X','X','X','X','X'}
		};
		game.initialize(0, 0, 0, temp);
		//char[][] temp = game.drawScreen();
	}
	
	
	
	
}

