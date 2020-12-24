package application;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javafx.event.ActionEvent;

public class DisplayOrdersControllerTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testEditDateFilledClick() {
		ActionEvent event = new ActionEvent();
		DisplayOrdersController controller = new DisplayOrdersController();
		controller.editDateFilledClick(event);
		//String dateFilledField = "";
		//controller.dateFilledField.setText((new Date()).toString());
		//((Order) orderField.getSelectionModel().getSelectedItem()).completeOrder();
	}
		

	@Test
	public void testSetFields() {
		DisplayOrdersController controller = new DisplayOrdersController();
		controller.setFields();
		assertTrue(controller != null);
		fail("Not yet implemented");
	}

	@Test
	public void testClearFields() {
		DisplayOrdersController controller = new DisplayOrdersController();
		
		// 
		String nameField = "John Smith";
		String orderIDField = "1";
		String dateOrderedField = "1/1/2020";
		String dateFilledField = "12/31/2020";
		
		controller.clearFields();
		
		if (nameField != "" || orderIDField != "" || dateOrderedField != "" || dateFilledField != "") {
			fail("clearFields failed.");
		}		
	}


	@Test
	public void testSetDateOrderedField() {
		DisplayOrdersController controller = new DisplayOrdersController();
		Date date = new Date();
		try {
			controller.setDateOrderedField(date);
			assertTrue(controller.dateOrderedField != null);
		} catch(Exception e) {
		fail("Not yet implemented");
		}
	}

	@Test
	public void testSetDateFilledField() {
		DisplayOrdersController controller = new DisplayOrdersController();
		Date date = new Date();
		try {
			controller.setDateOrderedField(date);
			assertTrue(controller.dateOrderedField != null);
		} catch(Exception e) {
		fail("Not yet implemented");
		}
	}

}
