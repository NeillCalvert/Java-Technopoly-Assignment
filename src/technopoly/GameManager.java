package technopoly;

// imports required classes from java util API 
import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * GameManager class: controls the flow of the game
 * 
 * @author jamescampbell neillcalvert nialdaly tommills andywilson
 *
 */
public class GameManager {

	/**
	 * final ints for min & max number of players default starting resources and
	 * location values
	 */
	public static final int MIN_NUMBER_PLAYERS = 2;
	public static final int MAX_NUMBER_PLAYERS = 4;
	public static final int DEFAULT_STARTING_RESOURCES = 500;
	public static final int DEFAULT_START_LOCATION = 1;
	public static Player currentPlayer = new Player();

	// initialises the gameList ArrayList
	public static ArrayList<Player> gameList;

	/**
	 * playGame method: method that initiates game set up and game play
	 */
	public void playGame() {

		// sets the endGame boolean as tentatively false
		boolean endGame = false;

		// initialises the list ArrayList
		ArrayList<Player> list = createPlayers();

		gameList = displayPlayerList(list);

		// do while the endGame boolean is false
		do {

			// instantiates Player object as currentPlayer
			currentPlayer = setCurrentPlayer(gameList);

			//
			endGame = quitGameStartTurn(currentPlayer);

			// if the endGame boolean is false
			if (!endGame) {

				// invokes the movePlayer method
				movePlayer(currentPlayer, Board.movementBoard());

				endGame = Board.checkArea(currentPlayer);

				// if the endGame boolean is false
				if (!endGame) {

					// invokes the manageResources method
					manageResources(currentPlayer, Board.propertyBoard());

					endGame = quitGameEndTurn(currentPlayer);

					// nested if statement checking if the endGame boolean is false
					if (!endGame) {

						// invokes the rotatePlayer method
						gameList = rotatePlayer(gameList);

						currentPlayer = setCurrentPlayer(gameList);

						System.out.println("\n*************\nNext player " + currentPlayer.getName());

					} else {

						// invokes the endGameSummary method
						endGameSummary(currentPlayer, gameList);

					}

				} else {

					// invokes the endGameSummary method
					endGameSummary(currentPlayer, gameList);

				}
			} else {

				// invokes the endGameSummary method
				endGameSummary(currentPlayer, gameList);

			}

		} while (!endGame);

	} // end of main method

	/**
	 * movePlayer method: changes the current player's position, if they pass
	 * through Go the player's account is updated
	 * 
	 * @param currentPlayer
	 * @param board
	 */
	public static void movePlayer(Player currentPlayer, ArrayList<Area> movementBoard) {

		// roll var stores current player rollDice value
		int roll = currentPlayer.rollDice();

		// result var stores currentPlayerPosition + roll value
		int result = currentPlayer.getPosition() + roll;

		// display current position to currentPlayer (before move)
		System.out.println(
				"\nYour current area position is: " + movementBoard.get(currentPlayer.getPosition() - 1).getName());

		// displays the roll value to currentPlayer
		System.out.println("You rolled a " + roll);

		// checks if result value is above 12. If true currentPlayer balance increased
		// by £200
		if (result > 12) {

			// adjusts result value to reflect currentPlayers new position
			result -= 12;

			// increases currentPlayer balance by £200
			currentPlayer.setBalance(currentPlayer.getBalance() + 200);

			System.out.println("You have passed Go: £200 has been awarded to your account");
			System.out.println("Your new balance is: £" + currentPlayer.getBalance());

		} // end of if statement

		// set currentPlayer position to result value
		currentPlayer.setPosition(result);

		// displays currentPlayer new position details
		System.out
				.println("\nYour new area position is: " + movementBoard.get(currentPlayer.getPosition() - 1).getName()
						+ "\nIn the field: " + movementBoard.get(currentPlayer.getPosition() - 1).getField());

	} // end of movePlayer method

	/**
	 * createPlayers method
	 * 
	 * @return players
	 */
	public static ArrayList<Player> createPlayers() {

		// initialises the Scanner class
		Scanner scanner = new Scanner(System.in);

		// Get number of players
		System.out.printf("How many players would like to play? (Please enter a number between %d and %d inclusive)\n",
				MIN_NUMBER_PLAYERS, MAX_NUMBER_PLAYERS);

		// initialises the numberOfPlayers int var
		int numberOfPlayers;

		// for loop
		for (;;) {

			// try statement
			try {

				// stores the number of players who want to play
				numberOfPlayers = scanner.nextInt();

				// if the numberOfPlayers is less than the min number of players & greater than
				// the max number of player
				if (numberOfPlayers < MIN_NUMBER_PLAYERS || numberOfPlayers > MAX_NUMBER_PLAYERS) {

					System.out.println(
							"Please enter a number between " + MIN_NUMBER_PLAYERS + " and " + MAX_NUMBER_PLAYERS);
					continue;
				}

				break;

			} catch (InputMismatchException exception) {

				System.out
						.println("Please enter a number between " + MIN_NUMBER_PLAYERS + " and " + MAX_NUMBER_PLAYERS);

				scanner.nextLine();

			} // end of catch statement

		} // end of infinite for loop

		// initialises the playersArrayList ArrayList
		ArrayList<Player> playersArrayList = new ArrayList<Player>();

		// checks the capacity of the playersArrayList
		playersArrayList.ensureCapacity(numberOfPlayers);

		// initialises the tentativeArr String array
		// used to store player names and check if any duplicate names have been entered
		String[] tentativeArr = new String[numberOfPlayers];

		// initialises the tentativePlayerList ArrayList
		ArrayList<String> tentativePlayerList = new ArrayList<String>();

		// for loop
		for (int loop = 0; loop < numberOfPlayers; loop++) {

			// asks the user to enter their name
			System.out.println("Please enter the details of player " + (loop + 1));

			// adds each scanner input to the tentativeArr array
			tentativeArr[loop] = scanner.next().toLowerCase();

		} // end of for loop

		// for loop that iterates through the tentativeArr array
		for (int i = 0; i < tentativeArr.length; i++) {

			// if the tentativeArr value is not contained with the tentativePlayerList
			if (!tentativePlayerList.contains(tentativeArr[i].toLowerCase())) {

				// add the value in the tentativeArr to the tentativePlayerList ArrayList
				tentativePlayerList.add(tentativeArr[i]);

			} else {

				// do while loop
				do {
					System.out.println("Duplicate name for player " + (i + 1) + ":  " + tentativeArr[i]
							+ " entered, please enter a new name");

					//
					tentativeArr[i] = scanner.next();

				} while (tentativePlayerList.contains(tentativeArr[i]));

			} // end of else statement

		} // end of for loop

		//
		for (int i = 0; i < tentativeArr.length; i++) {
			playersArrayList.add(new Player(tentativeArr[i], DEFAULT_STARTING_RESOURCES, DEFAULT_START_LOCATION));
		}

		// Put into standard array
		ArrayList<Player> players = playersArrayList;

		// returns the players ArrayList
		return players;

	} // end of createPlayers method

	/**
	 * displayPlayerList method: displays player details before and after being
	 * randomly ordered
	 * 
	 * @param playerList
	 * @return random ordered playerList
	 */
	public static ArrayList<Player> displayPlayerList(ArrayList<Player> playerList) {

		// shuffles the playerList ArrayList
		Collections.shuffle(playerList);

		System.out.println("\nPlayer order has been randomised: \n");

		// for loop that iterates through the randomised playerList ArrayList
		for (int loop = 0; loop < playerList.size(); loop++) {

			// prints each name in the playerList ArrayList
			System.out.println(playerList.get(loop).getName());

		} // end of for loop

		// for styling
		System.out.println("\n*************");

		// returns the playerList ArrayList
		return playerList;

	} // end of displayPlayerList method

	/**
	 * method to rotate order of players in arraylist
	 * 
	 * @param playerList
	 * @return
	 */
	public static ArrayList<Player> rotatePlayer(ArrayList<Player> playerList) {
		Collections.rotate(playerList, -1);
		return playerList;
	}// end of rotate player class=

	/**
	 * setCurrentPlayer method: sets current player to the first player in the
	 * playerList
	 * 
	 * @param playerList
	 * @return currentPlayer
	 */
	public static Player setCurrentPlayer(ArrayList<Player> playerList) {

		//
		Player currentPlayer = playerList.get(0);

		// returns the currentPlayer
		return currentPlayer;

	} // end of setCurrentPlayer method

	// we know the property is own

	// 1. check if the currentPlayerBalance > currentSquareFee
	// 2. if true, currentPlayerBalance - currentFee , currentSquareOwner +
	// currentSquareFee
	// 3. if false, invoke endGame method

	/**
	 * payFee method: checks if the current area is owned by a player who is not the
	 * current player
	 * 
	 * @param currentPlayer
	 * @param currentPropertyArea
	 * @return endGame
	 */
	public static boolean payFee(Player currentPlayer, PropertyArea currentPropertyArea) {
		boolean endGame = false;

		System.out.println(currentPlayer.getName() + " your balance is: £" + currentPlayer.getBalance());

		System.out.println("You have to pay £" + currentPropertyArea.getCurrentFee() + " to "
				+ currentPropertyArea.getOwner().getName());

		//
		if (currentPlayer.getBalance() >= currentPropertyArea.getCurrentFee()) {

			currentPlayer.setBalance(currentPlayer.getBalance() - currentPropertyArea.getCurrentFee());

			currentPropertyArea.getOwner()
					.setBalance(currentPropertyArea.getOwner().getBalance() + currentPropertyArea.getCurrentFee());

			System.out.println(currentPlayer.getName() + " your new balance is: £" + currentPlayer.getBalance());

			endGame = false;

		} else {

			System.out.println("You do not have sufficient funds.\nThe game is now over.");

			endGame = true;

		}

		// returns the endGame boolean value
		return endGame;

	} // end of payFee method

	/**
	 * buyDesk method: allows current player to purchase current area
	 * 
	 * @param currentPlayer
	 * @param movementBoard
	 * @param currentAreaPosition
	 */
	public static void buyDesk(Player currentPlayer, PropertyArea currentPropertyArea) {
		// int currentAreaPosition = currentPlayer.getPosition();

		System.out.println("The price of a desk in this area is: £" + currentPropertyArea.getDeskPrice());

		// sout area price and currentplayer balance

		// initialises the scanner
		Scanner scanner = new Scanner(System.in);

		// infinite for loop
		for (;;) {

			System.out.println("Do you want to buy a desk in this area?(y/n)");

			// input var
			String input = scanner.next();

			// if input value = n
			if (input.equalsIgnoreCase("n")) {

				// invoke manage resources method
				System.out.println("You have chosen not to buy a desk this area.");
				break;
			} // end if statement

			// else if input = y
			else if (input.equalsIgnoreCase("y")) {

				if (currentPlayer.getBalance() >= currentPropertyArea.getDeskPrice()) {

					//
					currentPlayer.setBalance(currentPlayer.getBalance() - currentPropertyArea.getDeskPrice());

					//
					currentPropertyArea.setOwner(currentPlayer);
					System.out.println("\n*************\nCongratulations! Your purchase was successful!");
					System.out.println("\nYour new balance is: £" + currentPlayer.getBalance());
					System.out.println("Your new area details are:\n");
					currentPropertyArea.displayAreaDetails();

					// breaks
					break;
				} else if (currentPlayer.getBalance() < currentPropertyArea.getDeskPrice()) {

					System.out.println("You don't have enough money to buy this area");

					break;
				}
			} else {

				System.out.println("Please select y/n");

				continue;
			}

		} // end of infinite for loop

	} // end of buyDesk method

	/**
	 * manageResources method
	 * 
	 * @param currentPlayer
	 * @param propertyBoard
	 */
	public static void manageResources(Player currentPlayer, ArrayList<PropertyArea> propertyBoard) {

		// initialises the scanner
		Scanner scanner = new Scanner(System.in);

		System.out.println("Press any button to continue");

		//
		scanner.next();

		System.out.println("\n*****MANAGE RESOURCES*****");

		System.out.println("\n\nYou can now choose an Area to invest in." + "\nYour current balance is: £"
				+ currentPlayer.getBalance());

		// create propertyArea array
		ArrayList<PropertyArea> playerPropertyAreaList = new ArrayList<>();

		for (int loop = 0; loop < Board.PROPERTYBOARD_SIZE; loop++) {

			if (Board.propertyBoard().get(loop).getOwner().equals(currentPlayer)) {

				playerPropertyAreaList.add(Board.propertyBoard().get(loop));
			}

		} // end of for loop to create propertyAreaList

		// if player does not own area, escape to nextPlayermethod
		if (playerPropertyAreaList.isEmpty()) {
			System.out.println("You don't own any areas yet. ");

		} else {

//			// initialises the scanner
//			Scanner scanner = new Scanner(System.in);

			System.out.println("\nYou own the following areas: \n");
			for (int loop = 0; loop < playerPropertyAreaList.size(); loop++) {

				int menuOption = loop + 1;

				playerPropertyAreaList.get(loop).displayManageResourcesDetails();
				System.out.printf("\nTo select the above area to invest in, please enter %d\n", menuOption);

				System.out.println("______________________________________\n");

			}
			System.out.println("To invest in an area you need to own all areas in the field "
					+ "\nand have enough money in your account to pay the investment price"
					+ "\n\nTo choose an area to invest in, ENTER THE RELEVANT NUMBER"
					+ "\nTo move on without investing, ENTER 0.");

			int input = -1;

			// infinite for loop
			for (;;) {

				try {
					input = scanner.nextInt();
					if (input < 0 || input > playerPropertyAreaList.size()) {
						System.out.println("Please enter a number from the options stated above");
						continue;

					}
					break;

				} catch (InputMismatchException exception) {
					System.out.println("Please enter a number from the options stated above");
					scanner.nextLine();
				} // end of catch

			} // end of infinite for loop

			int option = input;

			if (option <= 0 || option > playerPropertyAreaList.size()) {
				System.out.println("You have chosen to not invest in any areas.");

			} else {

				PropertyArea investArea = playerPropertyAreaList.get(option - 1);
				System.out.println("\n*************\nYou have chosen " + investArea.getName());

				if (!(Board.checkFieldOwner(currentPlayer, investArea))) {

					System.out.println(
							"You cannot invest in this Area as you do not own all areas.\nThis is the end of your turn");

				} else if (currentPlayer.getBalance() < investArea.getInvestmentPrice()) {
					System.out.println(
							"You cannot invest in this Area as you do not have enough money. This is the end of your turn");

				} else if (investArea.getDevelopmentLevel() == 4) {
					System.out.println("You have an office on this area, no more investment allowed");

				} else {
					currentPlayer.setBalance(currentPlayer.getBalance() - investArea.getInvestmentPrice());
					investArea.setDevelopmentLevel(investArea.getDevelopmentLevel() + 1);
					investArea.updateCurrentFee();
					System.out.println("Your new balance is: £" + currentPlayer.getBalance());
					System.out.println("New development level of area: " + investArea.getName() + " is "
							+ investArea.displayDevelopmentLevel() + " and the new Fee is £ "
							+ investArea.getCurrentFee());
					System.out.println();

				}

			} // end of if empty and else
		}

	} // end of manageResources method

	/**
	 * quitGameStartTurn method: checks if a player wants to continue or quit
	 * 
	 * @param currentPlayer
	 */
	public static boolean quitGameStartTurn(Player currentPlayer) {

		// sets the endGame boolean as tentatively false
		boolean endGame = false;

		// initialises the playerCheckerStart scanner
		Scanner playerCheckerStart = new Scanner(System.in);

		System.out.println(currentPlayer.getName() + ", your current balance is: £"
				+ currentPlayer.getBalance() + "\nWould you like to continue? (y/n)");

		// initialises the input String var
		String input;

		// infinite for loop
		for (;;) {

			// sets the next value from the playerCheckerStart scanner
			input = playerCheckerStart.next();

			// if the input value is y
			if (input.equalsIgnoreCase("y")) {

				System.out.println("\n*************\nGood! Your movement turn starts now!");

				endGame = false;

			} // end if statement

			// else if the input value is n
			else if (input.equalsIgnoreCase("n")) {

				// check if they are sure
				System.out.println(currentPlayer.getName() + ", are you sure you want to quit? (y/n)");

				String check = playerCheckerStart.next();

				// if the check value is y
				if (check.equalsIgnoreCase("y")) {

					System.out.println("Endgame");

					// sets the endGame boolean as true
					endGame = true;

				} else if (check.equalsIgnoreCase("n")) { // else if the input value is n

					// if not sure recursive call of the quitGameStartTurn method
					quitGameStartTurn(currentPlayer);

				} else {

					System.out.println("Invalid input!");

					// invokes the quitGameStartTurn method
					quitGameStartTurn(currentPlayer);

				}

			}

			else {

				System.out.println("Would you like to continue? (Please choose y/n only)");
				continue;

			}

			// returns the endGame boolean value
			return endGame;

		} // end of infinite for loop

	} // end of quitGameStartTurn method

	/**
	 * quitGameEndTurn method: checks if a player wants to continue or quit at end
	 * of their turn
	 * 
	 * @param currentPlayer
	 */
	public static boolean quitGameEndTurn(Player currentPlayer) {

		// sets the endGame boolean as tentatively false
		boolean endGame = false;

		// initialises the playerCheckerEnd scanner
		Scanner playerCheckerEnd = new Scanner(System.in);

		System.out
				.println("\n" + currentPlayer.getName() + " it is the end of your turn, would you like to continue playing the game? (y/n)");

		// initialises the input String var
		String input;

		// infinite for loop
		for (;;) {

			// sets the next value from the playerCheckerEnd scanner
			input = playerCheckerEnd.next();

			// if the input value is y
			if (input.equalsIgnoreCase("y")) {

				// sets the endGame boolean as false
				endGame = false;

			} // end if statement

			// else if the input value is n
			else if (input.equalsIgnoreCase("n")) {

				// check if they are sure
				System.out.println(currentPlayer.getName() + " are you sure you want to quit? (y/n)");

				// sets the check value as the next value from the playerCheckerEnd scanner
				String check = playerCheckerEnd.next();

				// if the check value is y, end the game
				if (check.equalsIgnoreCase("y")) {

					System.out.println("End of Game");

					// sets the endGame boolean to true
					endGame = true;

				} else if (check.equalsIgnoreCase("n")) {

					// if not sure recursive call of the quitGameStartTurn method
					quitGameStartTurn(currentPlayer);

				} else {

					System.out.println("Invalid input!");

					// invokes the quitGameStartTurn method
					quitGameStartTurn(currentPlayer);
				}

			} // end of else statement

			else {

				System.out.println("Would you like to continue? (Please choose y/n only)");
				continue;

			}

			// returns the endGame boolean value
			return endGame;

		} // end of infinite for loop

	}// end of quitGameEndTurn method

	/**
	 * endGameSummary method: removes current player from list and prints remaining
	 * players balance to screen
	 *
	 * @param currentPlayer
	 * @param gamelist
	 */
	public static void endGameSummary(Player currentPlayer, ArrayList<Player> gamelist) {

		//
		gamelist.remove(0);
		// if statement to identify if only one player remaining or if players need to
		// be sorted
		if (gamelist.size() == 1) {
			System.out.println("Congratulations " + gameList.get(0).getName()
					+ " you have won with a remaining balance of £" + gameList.get(0).getBalance());
			// more than one player remaining
		} else {

			// sort remaining players into descending order by balance
			Collections.sort(gamelist, new SortPlayers());

			System.out.println("Remaining players sorted by balance:\n");

			// enhanced for loop that iterates through each player in the gamelist
			for (Player p : gamelist) {

				// prints out each player’s balance
				System.out.println(p.getName() + " has £" + p.getBalance());
			} // end of enhanced for loop

			if (gamelist.get(0).getBalance() > gamelist.get(1).getBalance()) {
				System.out.println("\nCongratulations " + gameList.get(0).getName()
						+ " you have won with a remaining balance of £" + gameList.get(0).getBalance());
				// else there are more than one players has the highest remaining balance then
				// no overall winner is declared
			} else {
				System.out.println("No overall winner as more than one player has the highest remaining balance.");
			}
		} // end of else statement

	} // end of endGameSummary method

} // end of GameManager class