package dkeep.test;

import static org.junit.Assert.*;
import org.junit.Test;
import dkeep.logic.*;

public class TestDungeonGameLogic{
		
	@Test
	public void testMoveHeroIntoToFreeCell() {
		
		Game game = new Game();
		game.initialize(1, 0, 0, null);
		assertEquals(new Point(1,1), game.getHeroPosition());
		game.moveHero('d'); // move hero down
		assertEquals(new Point(2,1), game.getHeroPosition());
	}
	
	@Test
	public void testMoveHeroUp() {
		
		Game game = new Game();
		game.initialize(1, 0, 0, null);
		game.setPosHero(3, 2);
		assertEquals(new Point(3,2), game.getHeroPosition());
		game.moveHero('w'); // move hero up.
		assertEquals(new Point(3,1), game.getHeroPosition());
	}
	
	@Test
	public void testMoveHeroDown() {
		
		Game game = new Game();
		game.initialize(1, 0, 0, null);
		game.setPosHero(3, 2);
		assertEquals(new Point(3,2), game.getHeroPosition());
		game.moveHero('s'); // move hero up.
		assertEquals(new Point(3,3), game.getHeroPosition());
	}
	
	@Test
	public void testMoveHeroRight() {
		
		Game game = new Game();
		game.initialize(1, 0, 0, null);
		game.setPosHero(1, 1);
		assertEquals(new Point(1,1), game.getHeroPosition());
		game.moveHero('d'); // move hero right.
		assertEquals(new Point(2,1), game.getHeroPosition());
	}
	
	@Test
	public void testMoveHeroLeft() {
		
		Game game = new Game();
		game.initialize(1, 0, 0, null);
		game.setPosHero(3, 1);
		assertEquals(new Point(3,1), game.getHeroPosition());
		game.moveHero('a'); // move hero left.
		assertEquals(new Point(2,1), game.getHeroPosition());
	}
	
	@Test
	public void testMoveAgainstDoor_Wall() {
		
		Game game = new Game();
		game.initialize(1, 0, 0, null);
		game.setPosHero(3, 1);
		assertEquals(new Point(3,1), game.getHeroPosition());
		game.moveHero('d'); // move hero right.
		assertEquals(new Point(3,1), game.getHeroPosition());
		game.moveHero('s'); // move hero right.
		assertEquals(new Point(3,2), game.getHeroPosition());
		game.moveHero('d'); // move hero right.
		assertEquals(new Point(3,2), game.getHeroPosition());
	}
	
	/*

	
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

	*/
	
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

		while(!inverted) {
			((Level1)game.getMap()).move_npc();
			if(((Suspicious)(((Level1)game.getMap()).getGuard())).getInverted()) {
				inverted = true;
			}
		}
	}
	
	@Test
	public void testOgreRandom() {
		
		Game game = new Game();
		game.initialize(2, 1, 0, null);
		
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
	}
	
	
	@Test
	public void testOgreClubRandom() {
		
		Game game = new Game();
		game.initialize(2, 1, 0, null);
		
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
		
	}
	
	
	@Test
	public void testMoveNPCLevel2() {
		
		Game game = new Game();
		game.initialize(2, 1, 0, null);

		boolean expected = true;
		int counter = 0;
		int ogre_x, ogre_y, club_x, club_y;
		
		while(counter < 200) {
			counter++;
			((Level2)game.getMap()).getOgres().get(0).set_x(4);
			((Level2)game.getMap()).getOgres().get(0).set_y(4);
			((Level2)game.getMap()).move_npc();
			ogre_x = ((Level2)game.getMap()).getOgres().get(0).get_x();
			ogre_y = ((Level2)game.getMap()).getOgres().get(0).get_y();
			club_x = ((Level2)game.getMap()).getOgres().get(0).club_hit.x;
			club_y = ((Level2)game.getMap()).getOgres().get(0).club_hit.y;
			
			if(ogre_x == 4 && ogre_y == 3) {
				if(club_x == 4 && club_y == 2 || club_x == 4 && club_y == 4 || club_x == 3 && club_y == 3 || club_x == 5 && club_y == 3) {
					continue;
				}
			}
			if(ogre_x == 4 && ogre_y == 5) {
				if(club_x == 4 && club_y == 6 || club_x == 4 && club_y == 4 || club_x == 3 && club_y == 5 || club_x == 5 && club_y == 5) {
					continue;
				}
			}
			if(ogre_x == 3 && ogre_y == 4) {
				if(club_x == 3 && club_y == 5 || club_x == 3 && club_y == 3 || club_x == 2 && club_y == 4 || club_x == 4 && club_y == 4) {
					continue;
				}
			}
			if(ogre_x == 5 && ogre_y == 4) {
				if(club_x == 5 && club_y == 5 || club_x == 5 && club_y == 3 || club_x == 4 && club_y == 4 || club_x == 6 && club_y == 4) {
					continue;
				}
			}
			expected = false;
		}
		assertTrue(expected);
	}
	
	
	@Test
	public void testHeroIsCapturedByGuardLvl1_Left() {
		
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
	public void testHeroIsCapturedByGuardLvl1_Down() {
		
		Game game = new Game();
		game.initialize(1, 0, 0, null);
		((Level1)game.getMap()).getGuard().set_x(3);
		((Level1)game.getMap()).getGuard().set_y(1);
		game.setPosHero(3, 3);
		assertFalse(game.isGameover());
		game.moveHero('w'); // move hero to the right.
		game.setState(game.collision());
		assertTrue(game.isGameover());
	}
	
	@Test
	public void testHeroIsCapturedByGuardLvl1_Up() {
		
		Game game = new Game();
		game.initialize(1, 0, 0, null);
		((Level1)game.getMap()).getGuard().set_x(3);
		((Level1)game.getMap()).getGuard().set_y(3);
		game.setPosHero(3, 1);
		assertFalse(game.isGameover());
		game.moveHero('s'); // move hero to the right.
		game.setState(game.collision());
		assertTrue(game.isGameover());
	}
	
	@Test
	public void testHeroIsCapturedByGuardLvl1_Right() {
		
		Game game = new Game();
		game.initialize(1, 0, 0, null);
		game.setPosHero(3, 1);
		((Level1)game.getMap()).getGuard().set_x(1);
		((Level1)game.getMap()).getGuard().set_y(1);
		assertFalse(game.isGameover());
		game.moveHero('a'); // move hero to the right.
		game.setState(game.collision());
		assertTrue(game.isGameover());
	}
	
	
	@Test
	public void testHeroStunsOgreLvl2_Left() {
		
		Game game = new Game();
		game.initialize(2, 1, 0, null);
		game.setPosHero(1, 1);
		assertFalse(game.isGameover());
		game.updateGame('d');
		((Level2)game.getMap()).getOgres().get(0).set_x(3);
		((Level2)game.getMap()).getOgres().get(0).set_y(1);
		game.setState(game.collision());
		assertFalse(game.isGameover());
		assertTrue(((Level2)game.getMap()).getOgres().get(0).isStunned());
		assertEquals(2, ((Level2)game.getMap()).getOgres().get(0).get_stun_count());
	}
	
	@Test
	public void testHeroStunsOgreLvl2_Right() {
		
		Game game = new Game();
		game.initialize(2, 1, 0, null);
		game.setPosHero(3, 1);
		assertFalse(game.isGameover());
		game.updateGame('a');
		((Level2)game.getMap()).getOgres().get(0).set_x(1);
		((Level2)game.getMap()).getOgres().get(0).set_y(1);
		game.setState(game.collision());
		assertFalse(game.isGameover());
		assertTrue(((Level2)game.getMap()).getOgres().get(0).isStunned());
		assertEquals(2, ((Level2)game.getMap()).getOgres().get(0).get_stun_count());
	}
	
	@Test
	public void testHeroStunsOgreLvl2_Up() {
		
		Game game = new Game();
		game.initialize(2, 1, 0, null);
		game.setPosHero(1, 1);
		assertFalse(game.isGameover());
		game.updateGame('s');
		((Level2)game.getMap()).getOgres().get(0).set_x(1);
		((Level2)game.getMap()).getOgres().get(0).set_y(3);
		game.setState(game.collision());
		assertFalse(game.isGameover());
		assertTrue(((Level2)game.getMap()).getOgres().get(0).isStunned());
		assertEquals(2, ((Level2)game.getMap()).getOgres().get(0).get_stun_count());
	}
	
	@Test
	public void testHeroStunsOgreLvl2_Down() {
		
		Game game = new Game();
		game.initialize(2, 1, 0, null);
		game.setPosHero(1, 3);
		assertFalse(game.isGameover());
		game.updateGame('w');
		((Level2)game.getMap()).getOgres().get(0).set_x(1);
		((Level2)game.getMap()).getOgres().get(0).set_y(1);
		game.setState(game.collision());
		assertFalse(game.isGameover());
		assertTrue(((Level2)game.getMap()).getOgres().get(0).isStunned());
		assertEquals(2, ((Level2)game.getMap()).getOgres().get(0).get_stun_count());
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
		assertEquals(new Point(4,2), ((CustomLevel)game.getMap()).getOgres().get(0).getPoint());
		game.updateGame('d');
		assertNotEquals(new Point(4,2), ((CustomLevel)game.getMap()).getOgres().get(0).getPoint());
	}
	
	@Test
	public void testDrawCustomLvl() {
		
		Game game = new Game();
		char[][] temp = {
				{'X','X','X','X','X','X','X'},
				{'X','H','k',' ',' ',' ','I'},
				{'X',' ',' ',' ','O',' ','X'},
				{'X',' ',' ',' ',' ',' ','X'},
				{'X','X','X','X','X','X','X'}
		};
		game.initialize(0, 0, 0, temp);
		char[][] temp2 = game.drawScreen();
		
		assertEquals('H', temp2[1][1]);
		assertEquals('O', temp2[2][4]);
		assertEquals(1, ((CustomLevel)game.getMap()).getHeroX());
		assertEquals(1, ((CustomLevel)game.getMap()).getHeroY());
		game.updateGame('d');
		temp2 = game.drawScreen();
		assertEquals(Game.Game_State.CLVL_KEY, game.state);
		assertEquals('S', temp2[1][6]);
		game.moveHero('d'); // move hero to the right.
		game.moveHero('d'); // move hero to the right.
		game.moveHero('d'); // move hero to the right.
		game.updateGame('d');
		temp2 = game.drawScreen();
		assertEquals(Game.Game_State.WIN, game.state);
	}
	
	
	@Test
	public void testDrawLevel1() {
		
		Game game = new Game();
		game.initialize(1, 0, 0, null);
		char[][] temp = game.drawScreen();
		assertEquals('H', temp[1][1]);
		assertEquals('G', temp[1][8]);
		game.setPosHero(8, 8);
		game.updateGame('a');
		temp = game.drawScreen();
		assertEquals('S', ((Level1)game.getMap()).getMap(game.state)[5][0]);
		assertEquals('S', ((Level1)game.getMap()).getMap(game.state)[6][0]);
		assertEquals(Game.Game_State.LVL1, game.state);
		game.setPosHero(1, 6);
		game.updateGame('a');
		temp = game.drawScreen();
		assertEquals(Game.Game_State.LVL2, game.state);	
	}
	
	@Test
	public void testDrawLevel2() {
		
		Game game = new Game();
		game.initialize(2, 1, 0, null);
		char[][] temp = game.drawScreen();
		assertEquals(Game.Game_State.LVL2, game.state);
		assertTrue(((Level2)game.getMap()).getOgres().get(0).get_x() >= 3);
		assertTrue(((Level2)game.getMap()).getOgres().get(0).get_x() <= 8);
		assertTrue(((Level2)game.getMap()).getOgres().get(0).get_y() >= 3);
		assertTrue(((Level2)game.getMap()).getOgres().get(0).get_y() <= 8);
		assertEquals('O', temp[((Level2)game.getMap()).getOgres().get(0).get_y()][((Level2)game.getMap()).getOgres().get(0).get_x()]);
		((Level2)game.getMap()).getOgres().get(0).set_x(7);
		((Level2)game.getMap()).getOgres().get(0).set_y(1);
		temp = game.drawScreen();
		assertEquals('O',temp[1][7]);
		((Level2)game.getMap()).getOgres().get(0).set_x(6);
		((Level2)game.getMap()).getOgres().get(0).set_y(1);
		((Level2)game.getMap()).getOgres().get(0).club_hit.x = 7;
		((Level2)game.getMap()).getOgres().get(0).club_hit.y = 1;
		temp = game.drawScreen();
		assertEquals('$',temp[1][7]);
	}
	
	
	@Test
	public void testNumberOgresLevel2() {
		
		Game game = new Game();
		game.initialize(2, 1, 0, null);
		assertEquals(1, ((Level2)game.getMap()).getOgres().size());
	}
	
	@Test
	public void testHeroStunsOgre() {
		
		Game game = new Game();
		game.initialize(2, 1, 0, null);
		
		((Level2)game.getMap()).getOgres().get(0).set_x(4);
		((Level2)game.getMap()).getOgres().get(0).set_y(3);
		game.setPosHero(3, 3);
		assertFalse(((Level2)game.getMap()).getOgres().get(0).isStunned());
		((Level2)game.getMap()).ogreCollision(game.state, game.getHero(), ((Level2)game.getMap()).getOgres());
		assertTrue(((Level2)game.getMap()).getOgres().get(0).isStunned());
		assertEquals(Game.Game_State.LVL2, game.state);
	}
	
	
	@Test(timeout = 1000)
	public void testClubHitsHero_Left() {
		
		Game game = new Game();
		game.initialize(2, 1, 0, null);
		
		boolean kills_hero = false;

		//Left
		while (!kills_hero) {
			game.state = Game.Game_State.LVL2;
			((Level2)game.getMap()).getOgres().get(0).set_x(4);
			((Level2)game.getMap()).getOgres().get(0).set_y(3);
			game.setPosHero(2, 3);
			(((Level2)game.getMap()).getOgres().get(0)).club_logic(((Level2)game.getMap()).getMap(game.state));
			
			game.state = ((Level2)game.getMap()).ogreCollision(game.state, game.getHero(), ((Level2)game.getMap()).getOgres());
			if(game.state == Game.Game_State.LOSE) {
				kills_hero = true;
			}
		}
	}
	
	@Test(timeout = 1000)
	public void testClubHitsHero_Right() {
		
		Game game = new Game();
		game.initialize(2, 1, 0, null);
		
		boolean kills_hero = false;

		//Right
		while (!kills_hero) {
			game.state = Game.Game_State.LVL2;
			((Level2)game.getMap()).getOgres().get(0).set_x(4);
			((Level2)game.getMap()).getOgres().get(0).set_y(3);
			game.setPosHero(6, 3);
			(((Level2)game.getMap()).getOgres().get(0)).club_logic(((Level2)game.getMap()).getMap(game.state));
			
			game.state = ((Level2)game.getMap()).ogreCollision(game.state, game.getHero(), ((Level2)game.getMap()).getOgres());
			if(game.state == Game.Game_State.LOSE) {
				kills_hero = true;
			}
		}
	}
	
	@Test(timeout = 1000)
	public void testClubHitsHero_Up() {
		
		Game game = new Game();
		game.initialize(2, 1, 0, null);
		
		boolean kills_hero = false;

		//Up
		while (!kills_hero) {
			game.state = Game.Game_State.LVL2;
			((Level2)game.getMap()).getOgres().get(0).set_x(4);
			((Level2)game.getMap()).getOgres().get(0).set_y(3);
			game.setPosHero(4, 1);
			(((Level2)game.getMap()).getOgres().get(0)).club_logic(((Level2)game.getMap()).getMap(game.state));
			
			game.state = ((Level2)game.getMap()).ogreCollision(game.state, game.getHero(), ((Level2)game.getMap()).getOgres());
			if(game.state == Game.Game_State.LOSE) {
				kills_hero = true;
			}
		}
	}
	
	@Test(timeout = 1000)
	public void testClubHitsHero_Down() {
		
		Game game = new Game();
		game.initialize(2, 1, 0, null);
		
		boolean kills_hero = false;

		//Down
		while (!kills_hero) {
			game.state = Game.Game_State.LVL2;
			((Level2)game.getMap()).getOgres().get(0).set_x(4);
			((Level2)game.getMap()).getOgres().get(0).set_y(3);
			game.setPosHero(4, 5);
			(((Level2)game.getMap()).getOgres().get(0)).club_logic(((Level2)game.getMap()).getMap(game.state));
			
			game.state = ((Level2)game.getMap()).ogreCollision(game.state, game.getHero(), ((Level2)game.getMap()).getOgres());
			if(game.state == Game.Game_State.LOSE) {
				kills_hero = true;
			}
		}
	}
	
	@Test
	public void testHeroKeyInteractionLevel2() {
		
		Game game = new Game();
		game.initialize(2, 0, 0, null);
		assertEquals(Game.Game_State.LVL2, game.state);
		assertEquals('I', ((Level2)game.getMap()).getMap(game.state)[1][0]);
		game.setPosHero(5, 1);
		game.updateGame('d'); // move hero to the right.
		assertEquals(Game.Game_State.LVL2, game.state);
		game.updateGame('d'); // move hero to the right.
		game.state = ((Level2)game.getMap()).leversUp(game.state);
		assertEquals(Game.Game_State.LVL2_KEY_PICKED, game.state);
		assertEquals(' ', ((Level2)game.getMap()).getMap(game.state)[1][7]);
		BooleanHolder b = new BooleanHolder();
		b.value = false;
		((Level2)game.getMap()).map_Logic(game.state, game.getHero(), 'w', b);
		assertEquals(Game.Game_State.LVL2_KEY_PICKED, game.state);
		game.setPosHero(1, 1);
		game.updateGame('a');
		assertEquals(Game.Game_State.LVL2_KEY_TURNED, game.state);
		assertEquals('S', ((Level2)game.getMap()).getMap(game.state)[1][0]);
		game.updateGame('a');
		assertEquals(Game.Game_State.WIN, game.state);
	}
	

	@Test(timeout=1000)
	public void testDrunken() {
		
		Game game = new Game();
		game.initialize(1, 0, 1, null);
		assertEquals(new Point(8,1), ((Level1)game.getMap()).getGuard().getPoint());
		assertFalse(((Drunken)(((Level1)game.getMap()).getGuard())).getSleepy());
		assertFalse(((Drunken)(((Level1)game.getMap()).getGuard())).getInverted());
		assertEquals(0, ((Drunken)(((Level1)game.getMap()).getGuard())).getSleepCounter());
		assertEquals(5, ((Drunken)(((Level1)game.getMap()).getGuard())).getReRollSleep());
		((Level1)game.getMap()).move_npc();
		assertEquals(0, ((Drunken)(((Level1)game.getMap()).getGuard())).getSleepCounter());
		assertEquals(4, ((Drunken)(((Level1)game.getMap()).getGuard())).getReRollSleep());
		assertEquals(new Point(7,1), ((Level1)game.getMap()).getGuard().getPoint());
		((Level1)game.getMap()).move_npc();
		((Level1)game.getMap()).move_npc();
		((Level1)game.getMap()).move_npc();
		((Level1)game.getMap()).move_npc();
		assertEquals(5, ((Drunken)(((Level1)game.getMap()).getGuard())).getReRollSleep());
		
		while(!((Drunken)(((Level1)game.getMap()).getGuard())).getSleepy()) {
			((Level1)game.getMap()).move_npc();
		}
		
		while(!((Drunken)(((Level1)game.getMap()).getGuard())).getInverted()) {
			((Level1)game.getMap()).move_npc();
		}
	
	}
	
	
	
	
	
	
	

	
	
}

