package application;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ItemTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testInventory() {
		Item newItem = new Item(1, "blue","large", 1);
		try {
			assertTrue(newItem != null);
		} catch(Exception e) {
			fail("Inventory constructor failed");
		}
	}

	@Test
	public void testGetItemId() {
		Item newItem = new Item(1, "blue","large", 100);
		try {
			assertEquals(newItem.getItemId(), 1);
		} catch(Exception e) {
			fail("getItemId failed");
		}
	}

	@Test
	public void testGetItemStock() {
		Item newItem = new Item(1, "blue","large", 100);
		try {
			assertEquals(newItem.getItemStock(), 100);
		} catch(Exception e) {
			fail("getItemStock failed");
		}
	}

	@Test
	public void testGetItemColor() {
		Item newItem = new Item(1, "blue","large", 100);
		try {
			assertEquals(newItem.getItemColor(), "blue");
		} catch(Exception e) {
			fail("getItemId failed");
		}
	}
	
	@Test
	public void testGetItemSize() {
		Item newItem = new Item(1, "blue","large", 100);
		try {
			assertEquals(newItem.getItemSize(), "large");
		} catch(Exception e) {
			fail("getItemId failed");
		}
	}
	
	@Test
	public void testAddStock() {
		Item newItem = new Item(1, "blue", "large", 100);
		newItem.addStock(10);
		try {
			assertEquals(newItem.getItemStock(), 110);
		} catch(Exception e) {
			fail("addStock failed");
		}
	}

	@Test
	public void testRemoveStock() {
		Item newItemPassed = new Item(1, "blue", "large", 100);
		Item newItemFailed = new Item(2, "red", "small", 100);
		newItemPassed.removeStock(1);
		newItemFailed.removeStock(101);
		try {
			assertEquals(newItemPassed.getItemStock(), 99);
			assertEquals(newItemFailed.getItemStock(), 100);
		} catch(Exception e) {
			fail("removeStock failed");
		}
	}

}
