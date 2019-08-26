package technopoly;

// imports the Random class from java util API (for rollDice method) 
import java.util.Random;

/**
 * Player class
 * 
 * @author jamescampbell neillcalvert nialdaly tommills andywilson
 *
 */
public class Player {

	/**
	 * instance vars
	 */
	private String name;
	private int balance;
	private int position;

	/**
	 * min & max number of character for the name String
	 */
	public static final int MIN_NAME_LENGTH = 1;
	public static final int MAX_NAME_LENGTH = 20;

	/**
	 * Player default constructor
	 */
	public Player() {

	} // end of Player default constructor

	/**
	 * Player argument constructor
	 * 
	 * @param name
	 * @param balance
	 * @param position
	 */
	public Player(String name, int balance, int position) {
		super();
		this.setName(name);
		this.setBalance(balance);
		this.setPosition(position);
	} // end of Player argument constructor

	/**
	 * getName method
	 * 
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * setName method: sets the name of each player in the game
	 * 
	 * @param name the name to set
	 */
	public void setName(String name) {

		if (name != null && name.length() >= MIN_NAME_LENGTH && name.length() <= MAX_NAME_LENGTH) {
			this.name = name;
		} else {
			System.out.println("Player Name Invalid. Name set to default");
			this.name = "DefaultPlayerName";
		}
	} // end of setName method

	/**
	 * getBalance method
	 * 
	 * @return the balance
	 */
	public int getBalance() {
		return balance;
	}

	/**
	 * setBalance method: if balance is below 0 an exception is thrown
	 * 
	 * @param balance the balance to set to initial amount
	 */
	public void setBalance(int balance) throws IllegalArgumentException {
		if (balance >= 0) {
			this.balance = balance;
		} else {
			// throws exception if an invalid balance argument is passed
			throw new IllegalArgumentException("Invalid balance entered");
		}
	}

	/**
	 * getPosition method
	 * 
	 * @return the position
	 */
	public int getPosition() {
		return position;
	}

	/**
	 * setPosition method: if position is outside the min and max area boundary
	 * values an exception is thrown.
	 * 
	 * @param position the position to set
	 */
	public void setPosition(int position) throws IllegalArgumentException {
		if (position > Area.MIN_AREA_BOUNDARY && position <= Area.MAX_AREA_BOUNDARY) {
			this.position = position;
		} else {
			// throws exception if an invalid position argument is passed
			throw new IllegalArgumentException("Position out of bounds");
		}
	} // setPosition method

	/**
	 * rollDice method: generates the random dice roll value for player movement
	 * 
	 * @return move
	 */
	public int rollDice() {

		Random dice = new Random();
		int move = dice.nextInt(10) + 2;

		return move;
	} // end of rollDice method

} // end of Player class