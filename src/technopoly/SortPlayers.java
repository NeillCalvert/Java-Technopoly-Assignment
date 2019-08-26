package technopoly;

// imports the Comparator class from java util API
import java.util.Comparator;

/**
 * SortPlayers class: at the end of the game, the players are sorted in
 * descending order based on their final balance
 * 
 * @author jamescampbell neillcalvert nialdaly tommills andywilson
 *
 */
public class SortPlayers implements Comparator<Player> {

	/**
	 * compare method
	 */
	@Override
	public int compare(Player o1, Player o2) {

		return o2.getBalance() - o1.getBalance();

	} // end of compare method

} // end of SortPlayers class