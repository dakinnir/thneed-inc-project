package application;

import java.util.ArrayList;
import java.util.Date;

/** @author Parker Mores, Jason Esquivel, Arjun Mathur
 * 
 *	This class is designed to keep track of Orders from a specific Customer, and records the relevant information necessary to 
 *	keep track and fulfill the Order.
 *
 *  Last updated 10/22/2020
 */

public class Order implements java.io.Serializable {
	private int orderID;
	private Customer customer;
	private ArrayList<String> orderDetails = new ArrayList<>();
	private Date dateOrdered;
	private Date dateFilled;
	
	public Order(int orderID, Customer customer, ArrayList<String> orderDetails) {
		this.orderID = orderID;
		this.customer = customer;
		this.orderDetails = orderDetails;
		dateOrdered = new Date();
		dateFilled = null;
	}

	// Returns the unique ID number generated in the GUI controller.
	public int getOrderID() {
		return orderID;
	}
	
	public String orderIDString() {
		return String.valueOf(orderID);
	}
	
	// Returns the Customer who placed the Order.
	public Customer getCustomer() {
		return customer;
	}
	
	// Returns the Date that the Order was placed.
	public Date getDateOrdered() {
		return dateOrdered;
	}
	
	// Returns the Date that the Order was filled. 
	public Date getDateFulfilled() {
		return dateFilled;
	}
	
	//get method for orderDetails ArrayList using orderNum
	public ArrayList<String> getOrderDetails() {
		return orderDetails;
	}
	
	// Sets the Date fulfilled as the current Date.
	public void completeOrder() {
		this.dateFilled = new Date();
	}
	
	@Override
	public String toString() {
		return "Order ID: #" + this.orderID;
	}
}
