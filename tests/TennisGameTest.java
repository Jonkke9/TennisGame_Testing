import static org.junit.Assert.*;

import org.junit.Test;

public class TennisGameTest {


	@Test
	public void testTennisGame_Start() {
		TennisGame game = new TennisGame();
		String score = game.getScore() ;

		assertEquals("Initial score incorrect", "love - love", score);
	}
	
	@Test
	public void testTennisGame_EahcPlayerWin4Points_Score_Deuce() throws TennisGameException {
		TennisGame game = new TennisGame();
		
		game.player1Scored();
		game.player1Scored();
		game.player1Scored();
		
		game.player2Scored();
		game.player2Scored();
		game.player2Scored();
		
		game.player1Scored();
		game.player2Scored();

		String score = game.getScore() ;

		assertEquals("Tie score incorrect", "deuce", score);		
	}
	
	@Test (expected = TennisGameException.class)
	public void testTennisGame_Player1WinsPointAfterGameEnded_ResultsException() throws TennisGameException {
		//Arrange
		TennisGame game = new TennisGame();
		//Act
		game.player1Scored();
		game.player1Scored();
		game.player1Scored();
		game.player1Scored();
		//Act
		// This statement should cause an exception
		game.player1Scored();			
	}

	@Test (expected = TennisGameException.class)
	public void testTennisGame_Player2WinsPointAfterGameEnded_ResultsException() throws TennisGameException {
		//Arrange
		TennisGame game = new TennisGame();
		//Act
		game.player2Scored();
		game.player2Scored();
		game.player2Scored();
		game.player2Scored();
		//Act
		// This statement should cause an exception
		game.player2Scored();
	}

	@Test
	public void testTennisGame_Player2Wins() throws TennisGameException {
		//Arrange
		TennisGame game = new TennisGame();
		//Act
		game.player2Scored();
		game.player2Scored();
		game.player2Scored();
		game.player2Scored();

		String score = game.getScore();

		assertEquals("Player2 win score incorrect", "player2 wins",  score);

	}

	@Test
	public void testTennisGame_Player1Wins() throws TennisGameException {
		//Arrange
		TennisGame game = new TennisGame();
		//Act
		game.player1Scored();
		game.player1Scored();
		game.player1Scored();
		game.player1Scored();

		String score = game.getScore();

		assertEquals("Player1 win score incorrect", "player1 wins",  score);
	}


	@Test
	public void testTennisGame_Player1Advantage() throws TennisGameException {
		TennisGame game = new TennisGame();

		game.player2Scored();
		game.player2Scored();
		game.player2Scored();

		game.player1Scored();
		game.player1Scored();
		game.player1Scored();
		game.player1Scored();

		assertEquals("player1 has advantage", game.getScore());

		game.player2Scored();
		game.player1Scored();

		assertEquals("player1 has advantage", game.getScore());

	}

	@Test
	public void testTennisGame_Player2Advantage() throws TennisGameException {
		TennisGame game = new TennisGame();

		game.player1Scored();
		game.player1Scored();
		game.player1Scored();

		game.player2Scored();
		game.player2Scored();
		game.player2Scored();
		game.player2Scored();

		assertEquals("player2 has advantage", game.getScore());

		game.player1Scored();
		game.player2Scored();

		assertEquals("player2 has advantage", game.getScore());
	}

	@Test
	public void testTennisGame_deuceAfterAdvantage() throws TennisGameException {
		TennisGame game = new TennisGame();

		game.player2Scored();
		game.player2Scored();
		game.player2Scored();

		game.player1Scored();
		game.player1Scored();
		game.player1Scored();
		game.player1Scored();

		game.player2Scored();
		game.player2Scored();
		game.player1Scored();

		assertEquals("deuce", game.getScore());

	}

	@Test
	public void testTennisGame_MidGame() throws TennisGameException {
		TennisGame game = new TennisGame();
		game.player1Scored();
		game.player2Scored();
		assertEquals("15 - 15", game.getScore());

		game.player1Scored();
		assertEquals("15 - 30", game.getScore());

		game.player2Scored();
		assertEquals("30 - 30", game.getScore());
	}
}
