package technopoly;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * PlayerTest class
 * 
 * @author jamescampbell neillcalvert nialdaly tommills andywilson
 *
 */
public class PlayerTest {
	Player testPlayer;
	int validBalance, invalidBalance, validPosition, invalidPositionLower, invalidPositionUpper;
	String validName, validNameLower, validNameUpper, invalidNameEmpty, invalidNameLength, invalidNameNull;

	@Before
	public void setUp() throws Exception {
		validBalance = 100;
		invalidBalance = -1;
		validPosition = 1;
		invalidPositionLower = 0;
		invalidPositionUpper = 13;
		validName = "validName";
		invalidNameEmpty = "";
		invalidNameLength = "111111111111111111111";
		invalidNameNull = null;
		validNameLower = "q";
		validNameUpper = "11111111111111111111";
	}

	/**
	 * assert default constructor works also testing all setters and getters with
	 * valid input
	 */
	@Test
	public void testPlayerDefaultCons() {
		
		testPlayer = new Player();
		assertNotNull(testPlayer);
		testPlayer.setName(validName);
		assertEquals(validName, testPlayer.getName());
		testPlayer.setBalance(validBalance);
		assertEquals(validBalance, testPlayer.getBalance());
		testPlayer.setPosition(validPosition);
		assertEquals(validPosition, testPlayer.getPosition());
		
	}

	/**
	 * testing constructor with arguments with valid input
	 */
	@Test
	public void testPlayerConsWithArgsValid() {
		
		testPlayer = new Player(validName, validBalance, validPosition);
		assertEquals(validName, testPlayer.getName());
		assertEquals(validBalance, testPlayer.getBalance());
		assertEquals(validPosition, testPlayer.getPosition());
		
	}

	/**
	 * testing constructor with arguments with invalid name input
	 */
	@Test
	public void testPlayerConsWithArgsInvalidName() {
		
		testPlayer = new Player(invalidNameLength, validBalance, validPosition);
		assertEquals("DefaultPlayerName", testPlayer.getName());
		assertEquals(validBalance, testPlayer.getBalance());
		assertEquals(validPosition, testPlayer.getPosition());
		
	}

	/**
	 * testing constructor with arguments with invalid balance input.
	 * IllegalArgumentException thrown.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testPlayerConsWithArgsInvalidBalance() {
		
		testPlayer = new Player(validName, invalidBalance, validPosition);
		assertEquals(validName, testPlayer.getName());
		assertEquals(invalidBalance, testPlayer.getBalance());
		assertEquals(validPosition, testPlayer.getPosition());
		
	}

	/**
	 * testing constructor with arguments with invalid position input.
	 * IllegalArgumentException thrown.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testPlayerConsWithArgsInvalidPosition() {
		
		testPlayer = new Player(validName, validBalance, invalidPositionLower);
		assertEquals(validName, testPlayer.getName());
		assertEquals(validBalance, testPlayer.getBalance());
		assertEquals(invalidPositionLower, testPlayer.getPosition());
		
	}

	/**
	 * asserts name set to value length if at lower boundary value
	 */
	@Test
	public void testSetNamevalidLengthLower() {
		
		testPlayer = new Player();
		testPlayer.setName(validNameLower);
		assertEquals(validNameLower, testPlayer.getName());
		
	}

	/**
	 * asserts name set to value length if at upper boundary value
	 */
	@Test
	public void testSetNamevalidLengthUpper() {
		
		testPlayer = new Player();
		testPlayer.setName(validNameUpper);
		assertEquals(validNameUpper, testPlayer.getName());
		
	}

	/**
	 * assert if name field null player name set to default value
	 */
	@Test
	public void testSetNameNull() {
		
		testPlayer = new Player();
		testPlayer.setName(invalidNameNull);
		assertEquals("DefaultPlayerName", testPlayer.getName());
		
	}

	/**
	 * assert if name field inappropriate length player name set to default value
	 */
	@Test
	public void testSetNameInvalidLength() {
		
		testPlayer = new Player();
		testPlayer.setName(invalidNameLength);
		assertEquals("DefaultPlayerName", testPlayer.getName());
		
	}

	/**
	 * assert if balance set to value below 0, IllegalArgumentException thrown
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testSetBalanceInvalid() {
		
		testPlayer = new Player();
		testPlayer.setBalance(invalidBalance);
		
	}

	/**
	 * assert if position set to value below min value, IllegalArgumentException
	 * thrown
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testSetPositionLower() {
		
		testPlayer = new Player();
		testPlayer.setPosition(invalidPositionLower);
		
	}

	/**
	 * assert if position set to value above max value, IllegalArgumentException
	 * thrown
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testSetPositionUpper() {
		
		testPlayer = new Player();
		testPlayer.setPosition(invalidPositionUpper);
		
	}
} // end of PlayerTest class
