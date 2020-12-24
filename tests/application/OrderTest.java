package application;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

public class OrderTest {

	@Test
	public void testOrder() {
		Customer newCustomer = new Customer("0001", "Jason E.", "107 E 4th St.", "(812)-345-3562");
		Order newOrder = new Order(1, newCustomer, null);
		try {
			assertTrue(newOrder != null);
		}
		catch(Exception e) {
			fail("Order constructor failed.");
		}
	}

	@Test
	public void testCompleteOrder() {
		Customer newCustomer = new Customer("0001", "Jason E.", "107 E 4th St.", "(812)-345-3562");
		Order newOrder = new Order(1, newCustomer, null);
		try {
			Date newDate = new Date();
			newOrder.completeOrder();
			assertEquals(newOrder.getDateFulfilled(),newDate);
		}
		catch(Exception e) {
			fail("completeOrder failed.");	
		}
	}

	@Test
	public void testToString() {
		Customer newCustomer = new Customer("0001", "Jason E.", "107 E 4th St.", "(812)-345-3562");
		Order newOrder = new Order(1, newCustomer, null);
		try {
			String expectedString = "Order ID: #1";
			assertEquals(newOrder.toString(), expectedString);
		}
		catch(Exception e) {
			fail("toString failed.");
		}
	}

}
