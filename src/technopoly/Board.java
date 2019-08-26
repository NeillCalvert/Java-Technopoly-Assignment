package technopoly;

// imports the ArrayList class from java util API
import java.util.ArrayList;

/**
 * Board class: contains board setup data
 * 
 * @author jamescampbell neillcalvert nialdaly tommills andywilson
 *
 */
public class Board {

	public static final int PROPERTYBOARD_SIZE = 10;

	// instantiation of Player object as bank creates default owner as bank
	public static Player bank = new Player();

	// initialises the movementBoard ArrayList
	private static ArrayList<Area> movementBoard = new ArrayList<>();

	// initialises the propertyBoard ArrayList
	private static ArrayList<PropertyArea> propertyBoard = new ArrayList<>();

	/**
	 * data used to populate the game board
	 */
	public static NeutralArea a1 = new NeutralArea(Field.Neutral, "Go", 1);

	private static PropertyArea a2 = new PropertyArea(Field.Beijing, "Drones", 2, 60, 50, 2, 10, 250, 2, 0, bank);
	private static PropertyArea a3 = new PropertyArea(Field.Beijing, "Sensors", 3, 60, 50, 4, 20, 350, 3, 0, bank);
	private static PropertyArea a4 = new PropertyArea(Field.San_Francisco, "Hardware Development", 4, 160, 100, 12, 50,
			900, 12, 0, bank);
	private static PropertyArea a5 = new PropertyArea(Field.San_Francisco, "App Development", 5, 180, 100, 14, 60, 950,
			14, 0, bank);
	private static PropertyArea a6 = new PropertyArea(Field.San_Francisco, "Web Development", 6, 200, 100, 16, 70, 950,
			16, 0, bank);
	private static NeutralArea a7 = new NeutralArea(Field.Neutral, "Vacation", 7);
	private static PropertyArea a8 = new PropertyArea(Field.New_York, "Data Cleaning", 8, 160, 100, 12, 50, 900, 12, 0,
			bank);
	private static PropertyArea a9 = new PropertyArea(Field.New_York, "Data Analytics", 9, 180, 100, 14, 60, 950, 14, 0,
			bank);
	private static PropertyArea a10 = new PropertyArea(Field.New_York, "Data Mining", 10, 200, 100, 16, 70, 950, 16, 0,
			bank);
	private static PropertyArea a11 = new PropertyArea(Field.London, "Machine Learning", 11, 300, 200, 26, 100, 1250,
			26, 0, bank);
	private static PropertyArea a12 = new PropertyArea(Field.London, "Neural Networks", 12, 320, 200, 28, 120, 1400, 28,
			0, bank);
	// end of data population

	/**
	 * movementBoard method: populates and returns the movementBoard
	 * 
	 * @return movementBoard
	 */
	public static ArrayList<Area> movementBoard() {

		// adds each area object to the movementBoard ArrayList
		movementBoard.add(a1);
		movementBoard.add(a2);
		movementBoard.add(a3);
		movementBoard.add(a4);
		movementBoard.add(a5);
		movementBoard.add(a6);
		movementBoard.add(a7);
		movementBoard.add(a8);
		movementBoard.add(a9);
		movementBoard.add(a10);
		movementBoard.add(a11);
		movementBoard.add(a12);

		// returns the movementBoard
		return movementBoard;

	} // end of movementBoard method

	/**
	 * propertyBoard method: populates and returns the propertyBoard
	 * 
	 * @return propertyBoard
	 */
	public static ArrayList<PropertyArea> propertyBoard() {

		// adds each propertyArea object to the propertyBoard ArrayList
		propertyBoard.add(a2);
		propertyBoard.add(a3);
		propertyBoard.add(a4);
		propertyBoard.add(a5);
		propertyBoard.add(a6);
		propertyBoard.add(a8);
		propertyBoard.add(a9);
		propertyBoard.add(a10);
		propertyBoard.add(a11);
		propertyBoard.add(a12);

		// returns the propertyBoard
		return propertyBoard;

	} // end of propertyBoard class

	/**
	 * checkArea method: acesses current player square. If property square check
	 * ownership status
	 * 
	 * @param currentPlayer
	 * @param bank
	 * @param board
	 */
	public static boolean checkArea(Player currentPlayer) {

		// sets the endGame boolean as tentatively false
		boolean endGame = false;

		Area currentArea = movementBoard.get(currentPlayer.getPosition() - 1);

		// check current square is Go
		if (currentArea.getField().equals(Field.Neutral)) {
			System.out.println(
					"You are on the " + currentArea.getName() + "Area. \nThis is the end of your movement turn.");

			endGame = false;

		} else {

			PropertyArea currentPropertyArea = (PropertyArea) currentArea;

			// check if current area available to buy
			if (currentPropertyArea.getOwner() == bank) {

				GameManager.buyDesk(currentPlayer, currentPropertyArea);

				endGame = false;

			} else if (currentPropertyArea.getOwner() == currentPlayer) {

				// manage resources method
				System.out.println("You own this area.");

			} else {
				// invoke payFee method
				System.out.println("Area is owned by: " + currentPropertyArea.getOwner().getName());

				endGame = GameManager.payFee(currentPlayer, currentPropertyArea);

			}

		}

		// returns the endGame boolean value
		return endGame;

	} // end of checkArea method

	/**
	 * checkFieldOwner method: checks if currentPlayer owns all the areas in the
	 * same field of the area selected for investment. If currentPlayer does not own
	 * all areas in this field
	 * 
	 * @param currentPlayer
	 * @param area
	 * @param board
	 * @return canInvest
	 */
	public static boolean checkFieldOwner(Player currentPlayer, PropertyArea area) {

		// initialises the canInvest boolean as tentatively true
		boolean canInvest = true;

		// initialises the checkFieldOwnershipList ArrayList
		ArrayList<PropertyArea> checkFieldOwnershipList = new ArrayList<>();

		// for loop that iterates through the board
		for (int loop = 0; loop < propertyBoard.size(); loop++) {

			// if statement that extracts all areas from the property board in the same
			// field as the potential investment area
			if (propertyBoard.get(loop).getField().equals(area.getField())) {

				// adds each area in the corresponding field to the checkFieldOwnershipList
				checkFieldOwnershipList.add(propertyBoard.get(loop));

			} // end of if to make FieldPropertyList

		} // end of for loop

		// for loop that iterates through the checkFieldOwnershipList
		for (int loop = 0; loop < checkFieldOwnershipList.size(); loop++) {

			// if the current player owns all the fields, set the canInvest boolean to true
			if (checkFieldOwnershipList.get(loop).getOwner().equals(currentPlayer)) {

				canInvest = true;

			} else { // otherwise set the canInvest boolean to false

				canInvest = false;

				break; // breaks the for loop
			}
		} // end of for loop

		// returns the canInvest boolean value
		return canInvest;

	}// end of checkFieldOwner method

} // end of Board class