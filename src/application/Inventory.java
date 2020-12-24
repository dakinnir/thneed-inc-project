package application;

import java.util.ArrayList;

// Orange = 10
// Blue = 20
// Green = 30

// Small = 01
// Medium = 02
// Large = 03

//		Orange						Blue						Green
// 1001, 1002, 1003				2001, 2002, 2003			3001, 3002, 3003

public class Inventory implements java.io.Serializable {
	public ArrayList<Item> items = new ArrayList<Item>();
	
	
	public Inventory() {
		this.items.add(new Item(1001, "Orange", "Small", 50));
		this.items.add(new Item(1002, "Orange", "Medium", 50));
		this.items.add(new Item(1003, "Orange", "Large", 50));
		this.items.add(new Item(2001, "Blue", "Small", 50));
		this.items.add(new Item(2002, "Blue", "Medium", 50));
		this.items.add(new Item(2003, "Blue", "Large", 50));
		this.items.add(new Item(3001, "Green", "Small", 50));
		this.items.add(new Item(3002, "Green", "Medium", 50));
		this.items.add(new Item(3003, "Green", "Large", 50));
	}
	
	public ArrayList<Item> getItems() {
		return items;
	}
	public int getInventoryStock(String color, String size) {
		for (Item i: items) {
			if (color.equals(i.getItemColor()) && size.equals(i.getItemSize())) {
				return i.getItemStock();
			}
		}
		return 0;
	}

	public void addStock(int itemId, int quantity) {
		// loop through the arraylist of items
		for (Item i: items) {
			if (itemId == i.getItemId()) {
				i.addStock(quantity);
			}
		}
	}
	

	
	public void removeStock(String itemColor, String itemSize, int quantity) {
		for (Item i: items) {
			if (itemColor.equals(i.getItemColor()) && itemSize.equals(i.getItemSize())) {
				i.removeStock(quantity);
			}
		}
	}

}

