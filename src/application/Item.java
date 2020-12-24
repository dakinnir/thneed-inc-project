package application;


//Orange = 10
//Blue = 20
//Green = 30

//Small = 01
//Medium = 02
//Large = 03

//		Orange						Blue						Green
//1001, 1002, 1003				2001, 2002, 2003			3001, 3002, 3003

public class Item {
	private int itemId = 0;
	private String itemColor = "";
	private String itemSize = "";
	private int itemStock = 0;
	
	
	public Item(int itemId, String itemColor, String itemSize, int itemStock) {
		this.itemId = itemId;
		this.itemColor = itemColor;
		this.itemSize = itemSize;
		this.itemStock = itemStock;
	}
	
	// Returns itemId
	public int getItemId() {
		return itemId;
	}
	
	// Returns the current amount in stock
	public int getItemStock() {
		return itemStock;
	}
	
	public void updateItemStock(int itemStock) {
		this.itemStock += itemStock;
	}
	
	
	// Returns the item Color
	public String getItemColor() {
		return itemColor;
	}
	
	//Returns the item Size
	public String getItemSize() {
		return itemSize;
	}
	
	// Update when stock has been added
	public void addStock(int quantity) {
		int newStock = itemStock + quantity;
		this.itemStock = newStock;
	}

	public void removeStock(int quantity) {
		if ((itemStock - quantity) >= 0) {
			int newStock = itemStock - quantity;
			this.itemStock = newStock;
		} else {
			System.out.println("We only have " + getItemStock() + " in stock!");
		}
	}
	// Returning a string of itemID for setting label purposes
	public String itemIDString() {
		return String.valueOf(itemId);
	}
	
	// Returning a string of itemStock for setting label purposes
	public String itemStockString() {
		return String.valueOf(itemStock);
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return itemId + ", " + itemColor + ", " + itemSize + ", " + itemStock;
	}
}
