import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TennisGameTest {
	private TennisGame game;

	@Before
	public void setUp() {
		game = new TennisGame();
	}

	@Test
	public void testTennisGame_Start() {
		String score = game.getScore() ;
		assertEquals("Initial score incorrect", "love - love", score);
	}
	
	@Test
	public void testTennisGame_Score_Deuce() throws TennisGameException {
		game.player1Scored();
		game.player2Scored();
		game.player1Scored();
		game.player2Scored();
		game.player1Scored();
		game.player2Scored();
		game.player1Scored();
		game.player2Scored();

		assertEquals("Tie score incorrect", "deuce", game.getScore());

		game.player1Scored();
		game.player2Scored();

		assertEquals("Tie score incorrect", "deuce", game.getScore());
	}
	
	@Test (expected = TennisGameException.class)
	public void testTennisGame_Player1WinsPointAfterGameEnded_ResultsException() throws TennisGameException {
		game.player1Scored();
		game.player1Scored();
		game.player1Scored();
		game.player1Scored();
		game.player1Scored();
	}

	@Test (expected = TennisGameException.class)
	public void testTennisGame_Player2WinsPointAfterGameEnded_ResultsException() throws TennisGameException {
		game.player2Scored();
		game.player2Scored();
		game.player2Scored();
		game.player2Scored();
		game.player2Scored();
	}

	@Test
	public void testTennisGame_Player2Wins() throws TennisGameException {
		game.player2Scored();
		game.player2Scored();
		game.player2Scored();
		game.player2Scored();

		String score = game.getScore();

		assertEquals("Player2 win score incorrect", "player2 wins",  score);

	}

	@Test
	public void testTennisGame_Player1Wins() throws TennisGameException {
		game.player1Scored();
		game.player1Scored();
		game.player1Scored();
		game.player1Scored();

		String score = game.getScore();

		assertEquals("Player1 win score incorrect", "player1 wins",  score);
	}


	@Test
	public void testTennisGame_Player1Advantage() throws TennisGameException {
		game.player2Scored();
		game.player2Scored();
		game.player2Scored();

		game.player1Scored();
		game.player1Scored();
		game.player1Scored();
		game.player1Scored();

		Assert.assertEquals("player1 has advantage", game.getScore());

		game.player2Scored();
		game.player1Scored();

		Assert.assertEquals("player1 has advantage", game.getScore());

	}

	@Test
	public void testTennisGame_Player2Advantage() throws TennisGameException {
		game.player1Scored();
		game.player1Scored();
		game.player1Scored();

		game.player2Scored();
		game.player2Scored();
		game.player2Scored();
		game.player2Scored();

		Assert.assertEquals("player2 has advantage", game.getScore());

		game.player1Scored();
		game.player2Scored();

		Assert.assertEquals("player2 has advantage", game.getScore());
	}

	@Test
	public void testTennisGame_MidGame() throws TennisGameException {
		game.player1Scored();
		game.player2Scored();
		Assert.assertEquals("15 - 15", game.getScore());

		game.player1Scored();
		Assert.assertEquals("15 - 30", game.getScore());

		game.player2Scored();
		Assert.assertEquals("30 - 30", game.getScore());

		game.player2Scored();
		Assert.assertEquals("40 - 30", game.getScore());

		game.player1Scored();
		Assert.assertEquals("40 - 40", game.getScore());
	}

	@Test
	public void testPlayer1WinsWithThreePointDifference() throws TennisGameException {
		game.player2Scored();
		game.player2Scored();

		game.player1Scored();
		game.player1Scored();
		game.player1Scored();
		game.player1Scored();


		assertEquals("player1 wins", game.getScore());
	}

	@Test
	public void testPlayer2WinsWithThreePointDifference() throws TennisGameException {
		game.player1Scored();
		game.player1Scored();

		game.player2Scored();
		game.player2Scored();
		game.player2Scored();
		game.player2Scored();

		assertEquals("player2 wins", game.getScore());
	}
}
