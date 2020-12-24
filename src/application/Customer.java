package application;

import java.util.ArrayList;
import java.util.Date;


/** @author Parker Mores, Jason Esquivel, Arjun Mathur
 * 
 *	A class that contains the relevant information for a particular customer,
 *	including a unique Customer ID #, name, address, phone number and list of orders
 *	associated with this customer.
 *
 *  Last updated 10/22/2020
 */

public class Customer implements java.io.Serializable {
	private String customerID;
	private String customerName;
	private String address;
	private String phone;
	private ArrayList<Order> orders;
	
	public Customer(String customerID, String customerName, String address, String phone) {
		this.customerID = customerID;
		this.customerName = customerName;
		this.address = address;
		this.phone = phone;
	}
	
	// Returns unique customer ID
	public String getCustomerID() {
		return customerID;
	}
	
	// Returns customers name
	public String getCustomerName() {
		return customerName;
	}
	
	//Returns Customer address
	public String getCustomerAddress() {
		return address;
	}
	
	// Returns Customer Phone Number
	public String getPhone() {
		return phone;
	}
	
	// Removes An Order at the Specified Index of Customers' Orders
	public void deleteOrder(int index) {
		orders.remove(index);
	}
	
	// Returns Customer Orders
	public ArrayList<Order> getOrders() {
		return orders;
	}
}
