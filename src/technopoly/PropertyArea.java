package technopoly;

/**
 * PropertyArea subclass that extends the Area superclass
 * 
 * @author jamescampbell neillcalvert nialdaly tommills andywilson
 *
 */
public class PropertyArea extends Area {

	/**
	 * instance vars
	 */
	private int deskPrice;
	private int investmentPrice;
	private int deskFee;
	private int floorFee;
	private int officeFee;
	private int currentFee;
	private int developmentLevel;
	private Player owner;

	/**
	 * minimum & maximum property area development level
	 */
	public static final int MIN_PROPERTY_DEV_LEVEL = 0;
	public static final int MAX_PROPERTY_DEV_LEVEL = 4;

	/**
	 * PropertyArea default constructor
	 */
	public PropertyArea() {

	} // end of PropertyArea default constructor

	/**
	 * PropertySquare argument constructor
	 * 
	 * @param area
	 * @param name
	 * @param location
	 * @param deskPrice
	 * @param floorPrice
	 * @param officePrice
	 * @param fee
	 * @param owner
	 */
	public PropertyArea(Field field, String name, int location, int deskPrice, int investmentPrice, int deskFee,
			int floorFee, int officeFee, int currentFee, int developmentLevel, Player owner) {

		// inherited attributes from Area superclass
		super(field, name, location);

		this.setDeskPrice(deskPrice);
		this.setInvestmentPrice(investmentPrice);
		this.setDeskFee(deskFee);
		this.setFloorFee(floorFee);
		this.setOfficeFee(officeFee);
		this.currentFee = currentFee;
		this.setDevelopmentLevel(developmentLevel);
		this.owner = owner;

	} // end of PropertySquare argument constructor

	/**
	 * getDeskPrice method
	 * 
	 * @return the deskPrice
	 */
	public int getDeskPrice() {
		return deskPrice;
	}

	/**
	 * setDeskPrice method - throws IllegalArgumentException if price below zero has
	 * been entered
	 * 
	 * @param deskPrice the deskPrice to set
	 */
	public void setDeskPrice(int deskPrice) throws IllegalArgumentException {
		if (deskPrice > 0) {
			this.deskPrice = deskPrice;
		} else {
			throw new IllegalArgumentException("Invalid price value");
		}
	}

	/**
	 * getInvestmentPrice method
	 * 
	 * @return the investmentPrice
	 */
	public int getInvestmentPrice() {
		return investmentPrice;
	}

	/**
	 * setInvestmentPrice method - throws IllegalArgumentException if price below
	 * zero has been entered
	 * 
	 * @param investmentPrice the investmentPrice to set
	 */
	public void setInvestmentPrice(int investmentPrice) throws IllegalArgumentException {
		if (investmentPrice > 0) {
			this.investmentPrice = investmentPrice;
		} else {
			throw new IllegalArgumentException("Invalid price value");
		}

	}

	/**
	 * getDeskFee method
	 * 
	 * @return the deskFee
	 */
	public int getDeskFee() {
		return deskFee;
	}

	/**
	 * setDeskFee method - throws IllegalArgumentException if price below zero has
	 * been entered
	 * 
	 * @param deskFee the deskFee to set
	 */
	public void setDeskFee(int deskFee) throws IllegalArgumentException {
		if (deskFee > 0) {
			this.deskFee = deskFee;
		} else {
			throw new IllegalArgumentException("Invalid price value");
		}
	}

	/**
	 * getFloorFee method
	 * 
	 * @return the floorFee
	 */
	public int getFloorFee() {
		return floorFee;
	}

	/**
	 * setFloorFee method - throws IllegalArgumentException if price below zero has
	 * been entered
	 * 
	 * @param floorFee the floorFee to set
	 */
	public void setFloorFee(int floorFee) throws IllegalArgumentException {
		if (floorFee > 0) {
			this.floorFee = floorFee;
		} else {
			throw new IllegalArgumentException("Invalid price value");
		}
	}

	/**
	 * getOfficeFee method
	 * 
	 * @return the officeFee
	 */
	public int getOfficeFee() {
		return officeFee;
	}

	/**
	 * setOfficeFee method - throws IllegalArgumentException if price below zero has
	 * been entered
	 * 
	 * @param officeFee the officeFee to set
	 */
	public void setOfficeFee(int officeFee) throws IllegalArgumentException {
		if (officeFee > 0) {
			this.officeFee = officeFee;
		} else {
			throw new IllegalArgumentException("Invalid price value");
		}
	}

	/**
	 * getCurrentFee method
	 * 
	 * @return the currentFee
	 */
	public int getCurrentFee() {
		return currentFee;
	}

	/**
	 * setCurrentFee method
	 * 
	 * @param currentFee the currentFee to set
	 */
	public void setCurrentFee(int currentFee) {
		this.currentFee = currentFee;
	}

	/**
	 * getDevelopmentLevel method
	 * 
	 * @return the developmentLevel
	 */
	public int getDevelopmentLevel() {
		return developmentLevel;
	}

	/**
	 * setDevelopmentLevel method: sets the development level if the development
	 * level is within the appropriate range, otherwise an IllegalArgumentException
	 * is thrown
	 * 
	 * @param developmentLevel the developmentLevel to set
	 */
	public void setDevelopmentLevel(int developmentLevel) {

		if (developmentLevel >= MIN_PROPERTY_DEV_LEVEL && developmentLevel <= MAX_PROPERTY_DEV_LEVEL) {
			this.developmentLevel = developmentLevel;
		} else {
			throw new IllegalArgumentException("Invalid development level");
		}

	} // end of setDevelopmentLevel method

	/**
	 * getOwner method
	 * 
	 * @return the owner
	 */
	public Player getOwner() {
		return owner;
	}

	/**
	 * setOwner method: throws illegal argument if a null owner argument is passed
	 * 
	 * @param owner
	 */
	public void setOwner(Player owner) throws IllegalArgumentException {
		
		if (owner != null) {
			
			this.owner = owner;
			
		} else {
			
			throw new IllegalArgumentException("No player assigned to PropertyArea");
		}
	} // end of setOwner method

	/**
	 * displayDevelopmentLevel method: takes the developmentLevel int value of the
	 * propertyArea and returns the relevant description for that developmentLevel
	 * description String
	 * 
	 * @return developmentLevel
	 */
	public String displayDevelopmentLevel() {
		int developmentLevelNumber = this.developmentLevel;
		String developmentLevel = "default";

		// switch statement
		switch (developmentLevelNumber) {

		case 0:
			developmentLevel = "desk";
			break;
		case 1:
			developmentLevel = "1 floor";
			break;
		case 2:
			developmentLevel = "2 floors";
			break;
		case 3:
			developmentLevel = "3 floors";
			break;
		case 4:
			developmentLevel = "office";
			break;

		default:
			developmentLevel = "default";
		} // end of switch statement

		// returns developmentLevel
		return developmentLevel;

	} // end of displayDevelopmentLevel method

	/**
	 * updateCurrentFee method: changes the currentFee of a propertyArea to floor or
	 * office fee depending on the developmentLevel int value
	 * 
	 * @param investArea
	 */
	public void updateCurrentFee() {

		// if the property developmentLevel will be between 1 and 4 when it reaches the
		// updateCurrentFee method
		if (this.developmentLevel > MIN_PROPERTY_DEV_LEVEL && this.developmentLevel < MAX_PROPERTY_DEV_LEVEL) {

			// fee calculation for 1 - 3 floors
			this.setCurrentFee(this.getFloorFee() * this.developmentLevel);

		} else {
			// property developmentLevel = 4, hence office fee is set
			this.setCurrentFee(this.getOfficeFee());

		}

	} // end of updateCurrentFee method

	/**
	 * displayAreaDetails method: displays the details of a specific property area
	 * when a player lands on it
	 * 
	 */
	public void displayAreaDetails() {

		System.out.println("Name: " + this.getName());
		System.out.println("Field : " + this.getField());
		System.out.println("Current development level: " + this.displayDevelopmentLevel());
		System.out.println("Current Fee: £" + this.getCurrentFee());
		System.out.println("Owner: " + this.getOwner().getName());

	} // end of displayAreaDetails method

	/**
	 * displayManageResourcesDetails method: displays the details of a specific
	 * property area during the manageResources method
	 */
	public void displayManageResourcesDetails() {

		System.out.println("Name: " + this.getName());
		System.out.println("Field : " + this.getField());
		System.out.println("Current Fee: £" + this.getCurrentFee());
		System.out.println("Current development level: " + this.displayDevelopmentLevel());
		System.out.println("Investment Price : £" + this.investmentPrice);

	} // end of displayManageResourcesDetails method

} // end of PropertyArea subclass