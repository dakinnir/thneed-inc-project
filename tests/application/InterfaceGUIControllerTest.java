package application;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class InterfaceGUIControllerTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testLoadFromFileButtonClick() {
		fail("Not yet implemented");
	}

	@Test
	public void testClearFields() {
		InterfaceGUIController controller = new InterfaceGUIController();
		
		String custField = "John Smith";
		String nameField = "John Smith";
		String adField = "666 Hell Road";
		String phoneField = "(800) 555-5555";
		String orderField = "1";

		controller.clearFields();
		
		if (custField != "" || nameField != "" || adField != "" || phoneField != "" || orderField != "") {
			fail("clearFields failed.");
		}
	}

	@Test
	public void testGetOrders() {
		fail("Not yet implemented");
	}

}
