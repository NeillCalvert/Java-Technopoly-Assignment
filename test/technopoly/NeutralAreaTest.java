package technopoly;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * NeutralAreaTest class
 * 
 * @author jamescampbell neillcalvert nialdaly tommills andywilson
 *
 */
public class NeutralAreaTest {

	NeutralArea testNeutralArea;
	String validName, invalidNameEmpty, invalidNameNull;
	int validLocationUpper, invalidLocationLower, invalidLocationUpper;
	Field defaultField;

	@Before
	public void setUp() throws Exception {

		validName = "validName";
		invalidNameEmpty = "";
		invalidNameNull = null;
		validLocationUpper = 12;
		invalidLocationLower = 0;
		invalidLocationUpper = 13;

	}

	/**
	 * test default constructor to ensure it is not NeutralArea object not null
	 */
	@Test
	public void testNeutralAreaDefaultCons() {

		testNeutralArea = new NeutralArea();
		assertNotNull(testNeutralArea);
		testNeutralArea.setField(defaultField);
		testNeutralArea.setName(validName);
		testNeutralArea.setLocation(validLocationUpper);
		assertEquals(defaultField, testNeutralArea.getField());
		assertEquals(validName, testNeutralArea.getName());
		assertEquals(validLocationUpper, testNeutralArea.getLocation());

	}

	/**
	 * test constructor with arguments valid arguments
	 */
	@Test
	public void testNeutralAreaConsWithArgs() {

		testNeutralArea = new NeutralArea(defaultField, validName, validLocationUpper);
		assertEquals(defaultField, testNeutralArea.getField());
		assertEquals(validName, testNeutralArea.getName());
		assertEquals(validLocationUpper, testNeutralArea.getLocation());
	}

	/**
	 * test constructor with arguments invalid name value
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testNeutralAreaConsWithArgsInvalidName() {

		testNeutralArea = new NeutralArea(defaultField, invalidNameEmpty, validLocationUpper);
	}

	/**
	 * test constructor with arguments invalid location value
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testNeutralAreaConsWithArgsInvalidLocation() {

		testNeutralArea = new NeutralArea(defaultField, invalidNameEmpty, invalidLocationLower);

	}

	/**
	 * test invalid name value - null value
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testSetNameInvalidNullValue() {

		testNeutralArea = new NeutralArea();
		testNeutralArea.setName(invalidNameNull);

	}

	/**
	 * test invalid name value - invalid length value
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testSetNameInvalidLengthValue() {

		testNeutralArea = new NeutralArea();
		testNeutralArea.setName(invalidNameEmpty);

	}

	/**
	 * test invalid location value - invalid lower location value
	 */
	@Test(expected = IllegalArgumentException.class)

	public void testSetLocationInvalidLowerValue() {

		testNeutralArea = new NeutralArea();
		testNeutralArea.setLocation(invalidLocationLower);

	}

	/**
	 * test invalid location value - invalid upper location value
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testSetLocationInvalidUpperValue() {

		testNeutralArea = new NeutralArea();
		testNeutralArea.setLocation(invalidLocationUpper);
		
	}
} // end of NeutralAreaTest class
