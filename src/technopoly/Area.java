package technopoly;

/**
 * abstract Area class: parent class for the neutral & property areas
 * 
 * @author jamescampbell neillcalvert nialdaly tommills andywilson
 *
 */
public abstract class Area {

	/**
	 * instance vars
	 */
	private Field field;
	private String name;
	private int location;

	/**
	 * minimum & maximum number of areas on the board
	 */
	public static final int MIN_AREA_BOUNDARY = 0;
	public static final int MAX_AREA_BOUNDARY = 12;

	/**
	 * Area default constructor
	 */
	public Area() {

	} // end of Area default constructor

	/**
	 * Area argument constructor
	 * 
	 * @param area
	 * @param name
	 * @param location
	 */
	public Area(Field field, String name, int location) {

		this.field = field;
		this.setName(name);
		this.setLocation(location);
	} // end of Area argument constructor

	/**
	 * getField method
	 * 
	 * @return the area
	 */
	public Field getField() {
		return field;
	}

	/**
	 * setArea method
	 * 
	 * @param area the area to set
	 */
	public void setField(Field field) {
		this.field = field;
	}

	/**
	 * getName method
	 * 
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * setName method
	 * 
	 * @param name the name to set
	 */
	public void setName(String name) throws IllegalArgumentException {
		if (name != null && !name.isEmpty()) {
			this.name = name;
		} else {
			throw new IllegalArgumentException("Invalid name");
		}
	}

	/**
	 * getLocation method
	 * 
	 * @return location
	 */
	public int getLocation() {
		return location;
	}

	/**
	 * setLocation method: sets the area location of the board between the min and
	 * max area boundaries
	 * 
	 * otherwise an IllegalArgument exception is thrown
	 * 
	 * @param location the location to set
	 */
	public void setLocation(int location) throws IllegalArgumentException {

		//
		if (location > MIN_AREA_BOUNDARY && location <= MAX_AREA_BOUNDARY) {
			this.location = location;
		} else {
			// throws exception if an invalid location argument is passed
			throw new IllegalArgumentException("Location out of bounds");
		}
	} // end of setLocation method

} // end of Area abstract class