package technopoly;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * PropertyAreaTest class
 * 
 * @author jamescampbell neillcalvert nialdaly tommills andywilson
 *
 */
public class PropertyAreaTest {

	private void AssertNotNull(PropertyArea testPropertyArea1) {

	}

	public PropertyArea testPropertyArea;
	int validPrice, invalidPrice, validLocationUpper, invalidLocationLower, invalidLocationUpper, validDevelopmentLevel,
			invalidDevelopmentLevel;
	String validName, invalidNameEmpty, invalidNameNull;
	Field defaultField;
	Player testPlayer, nullTestPlayer;

	@Before
	public void setUp() throws Exception {
		
		validPrice = 100;
		invalidPrice = -100;
		validLocationUpper = 12;
		invalidLocationLower = 0;
		invalidLocationUpper = 13;
		validDevelopmentLevel = 1;
		invalidDevelopmentLevel = 5;
		validName = "validName";
		invalidNameEmpty = "";
		invalidNameNull = null;
		testPlayer = new Player(validName, validPrice, validLocationUpper);
	}

	@Test
	public void testPropertyAreaDefaultCons() {
		
		testPropertyArea = new PropertyArea();
		AssertNotNull(testPropertyArea);
	}

	@Test
	public void testPropertyAreaConsWithArgs() {
		
		testPropertyArea = new PropertyArea(defaultField, validName, validLocationUpper, validPrice, validPrice,
				validPrice, validPrice, validPrice, validPrice, validDevelopmentLevel, testPlayer);
		assertEquals(defaultField, testPropertyArea.getField());
		assertEquals(validName, testPropertyArea.getName());
		assertEquals(validLocationUpper, testPropertyArea.getLocation());
		assertEquals(validPrice, testPropertyArea.getDeskFee());
		assertEquals(validPrice, testPropertyArea.getDeskPrice());
		assertEquals(validPrice, testPropertyArea.getFloorFee());
		assertEquals(validPrice, testPropertyArea.getInvestmentPrice());
		assertEquals(validPrice, testPropertyArea.getOfficeFee());
		assertEquals(validPrice, testPropertyArea.getCurrentFee());
		assertEquals(validDevelopmentLevel, testPropertyArea.getDevelopmentLevel());
		assertEquals(testPlayer, testPropertyArea.getOwner());
		
	}

	@Test(expected = IllegalArgumentException.class)
	
	public void testSetDeskPriceInvalidValue() {
		
		testPropertyArea = new PropertyArea();
		testPropertyArea.setDeskPrice(invalidPrice);
	}

	@Test(expected = IllegalArgumentException.class)
	
	public void testSetInvestmentPriceInvalidValue() {
		
		testPropertyArea = new PropertyArea();
		testPropertyArea.setInvestmentPrice(invalidPrice);
	}

	@Test(expected = IllegalArgumentException.class)
	
	public void testSetDeskFeeInvalidValue() {
		
		testPropertyArea = new PropertyArea();
		testPropertyArea.setDeskFee(invalidPrice);
	}

	@Test(expected = IllegalArgumentException.class)
	
	public void testSetFloorFeeInvalidValue() {
		
		testPropertyArea = new PropertyArea();
		testPropertyArea.setFloorFee(invalidPrice);
	}

	@Test(expected = IllegalArgumentException.class)
	
	public void testSetOfficeFeeInvalidValue() {
		
		testPropertyArea = new PropertyArea();
		testPropertyArea.setOfficeFee(invalidPrice);
	}

	@Test(expected = IllegalArgumentException.class)
	
	public void testSetDevelopmentLevelInvalidValue() {
		
		testPropertyArea = new PropertyArea();
		testPropertyArea.setDevelopmentLevel(invalidDevelopmentLevel);
	}

	@Test(expected = IllegalArgumentException.class)
	
	public void testSetOwnerNullPlayer() {
		
		testPropertyArea = new PropertyArea();
		testPropertyArea.setOwner(nullTestPlayer);
	}

	@Test(expected = IllegalArgumentException.class)
	
	public void testDisplayDevelopmentLevel() {
		
		testPropertyArea = new PropertyArea();
		int developmentLevelTest = 0;
		testPropertyArea.setDevelopmentLevel(developmentLevelTest);
		assertEquals("desk", testPropertyArea.displayDevelopmentLevel());
		developmentLevelTest = 1;
		testPropertyArea.setDevelopmentLevel(developmentLevelTest);
		assertEquals("1 floor", testPropertyArea.displayDevelopmentLevel());
		developmentLevelTest = 2;
		testPropertyArea.setDevelopmentLevel(developmentLevelTest);
		assertEquals("2 floors", testPropertyArea.displayDevelopmentLevel());
		developmentLevelTest = 3;
		testPropertyArea.setDevelopmentLevel(developmentLevelTest);
		assertEquals("3 floors", testPropertyArea.displayDevelopmentLevel());
		developmentLevelTest = 4;
		testPropertyArea.setDevelopmentLevel(developmentLevelTest);
		assertEquals("office", testPropertyArea.displayDevelopmentLevel());
		// this value will throw Illegal Argument Exception
		developmentLevelTest = 5;
		testPropertyArea.setDevelopmentLevel(developmentLevelTest);
		assertEquals("Default", testPropertyArea.displayDevelopmentLevel());
		
	}

	@Test
	public void testUpdateCurrentFee() {
		
		int developmentLevelTest = 1;
		testPropertyArea = new PropertyArea();
		testPropertyArea.setFloorFee(validPrice);
		testPropertyArea.setDevelopmentLevel(developmentLevelTest);
		testPropertyArea.updateCurrentFee();
		assertEquals(developmentLevelTest * validPrice, testPropertyArea.getCurrentFee());
		developmentLevelTest = 2;
		testPropertyArea.setDevelopmentLevel(developmentLevelTest);
		testPropertyArea.updateCurrentFee();
		assertEquals(developmentLevelTest * validPrice, testPropertyArea.getCurrentFee());
		developmentLevelTest = 3;
		testPropertyArea.setDevelopmentLevel(developmentLevelTest);
		testPropertyArea.updateCurrentFee();
		assertEquals(developmentLevelTest * validPrice, testPropertyArea.getCurrentFee());
	}
	
} // end of PropertyAreaTest class