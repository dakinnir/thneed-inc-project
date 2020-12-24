package application;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CustomerTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCustomer() {
		Customer newCustomer = new Customer("0001", "Jason E.", "107 E 4th St.", "(812)-345-3562");
		try {
			assertTrue(newCustomer != null);
		}
		catch(Exception e) {
			fail("Customer constructor failed.");
		}
	}

	@Test
	public void testGetCustomerID() {
		Customer newCustomer = new Customer("0001", "Jason E.", "107 E 4th St.", "(812)-345-3562");
		try {
			assertEquals("0001", newCustomer.getCustomerID());
		}
		catch(Exception e) {
			fail("getCustomerID failed.");
		}
	}
	
	@Test
	public void testGetCustomerName() {
		Customer newCustomer = new Customer("0001", "Jason E.", "107 E 4th St.", "(812)-345-3562");
		try {
			assertEquals("Jason E.", newCustomer.getCustomerName());
		}
		catch(Exception e) {
			fail("getCustomerName method failed.");
		}
	}

	@Test
	public void testGetCustomerAddress() {
		Customer newCustomer = new Customer("0001", "Jason E.", "107 E 4th St.", "(812)-345-3562");
		try {
			assertEquals("107 E 4th St.", newCustomer.getCustomerAddress());
		}
		catch(Exception e) {
			fail("getCustomerAddress failed.");
		}
	}

	@Test
	public void testGetPhone() {
		Customer newCustomer = new Customer("0001", "Jason E.", "107 E 4th St.", "(812)-345-3562");
		try {
			assertEquals("(812)-345-3562", newCustomer.getPhone());
		}
		catch(Exception e) {
			fail("getPhone failed.");
		}
	}

	@Test
	public void testDeleteOrder() {
		ArrayList<Order> orders = new ArrayList<Order>();
		Customer newCustomer = new Customer("0001", "Jason E.", "107 E 4th St.", "(812)-345-3562");
		Order newOrder = new Order(1, newCustomer, null);
		try {
			orders.add(newOrder);
			if(orders.size() != 1) {
				fail("deleteOrder method failed.");
			}
			newCustomer.deleteOrder(0);
			assertEquals(orders.size(),0);
		}
		catch(Exception e) {
			fail("deleteOrder failed.");
		}
	}

	@Test
	public void testGetOrders() {
		Customer newCustomer = new Customer("0001", "Jason E.", "107 E 4th St.", "(812)-345-3562");
		try {
			assertEquals(null, newCustomer.getOrders());
		}
		catch(Exception e) {
			fail("getOrders failed.");
		}
	}

}
