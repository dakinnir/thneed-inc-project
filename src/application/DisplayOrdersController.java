package application;

import java.util.ArrayList;
import java.util.Date;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.DatePicker;

/** @author Parker Mores, Jason Esquivel, Arjun Mathur
 * 
 *	This is the controller class for the FXML file that handles the window that displays all orders and allows you to set a certain order as fulfilled.
 *	You can click on the orders on the ListView and see the relevant information pop up on the right side of the window after it has been populated by
 *	the primary interface.
 *
 *  Last updated 10/22/2020
 */

public class DisplayOrdersController {
	@FXML
	private ListView<Order> orderField;
	@FXML
	private ChoiceBox<Order> displayChoice;
	@FXML
	private Label nameField;
	@FXML
	private Label orderIDField;
	@FXML
	private Label dateOrderedField;
	@FXML
	private Label dateFilledField;
	@FXML
	private Label orderDetails;
	@FXML
	private DatePicker datePicker;
	@FXML
	private Button completeOrderButton;	
	private ArrayList<Order> orderList;
	private InterfaceGUIController callingController;
	
	private Inventory inventory = new Inventory();
	private Item item;
	
	@FXML 
	private AnchorPane mainPane;
	
	//Marks an order as "complete," where the dateFilled variable of the Order class will be given a new Date variable equal to the time that the button was pushed.
	@FXML
	public void editDateFilledClick(ActionEvent e) {
		dateFilledField.setText((new Date()).toString());
		((Order) orderField.getSelectionModel().getSelectedItem()).completeOrder();
	}
	
	//Sets the Labels to the information relevant currently selected Order object, including the Customer's information and the Order ID.
	@FXML
	public void setFields(Order order) {
		nameField.setText(order.getCustomer().getCustomerName());
		orderIDField.setText(order.orderIDString());
		dateOrderedField.setText(order.getDateOrdered().toString());
		orderDetails.setText(order.getOrderDetails().get(Integer.parseInt(orderIDField.getText())-1));

		
		if (order.getDateFulfilled() == (null)) {
			dateFilledField.setText("---Pending---");
		} else {
			dateFilledField.setText(order.getDateFulfilled().toString());
		}
		
	}
	
	//Clears the Customer information Labels as well as the Order ID Label.
	@FXML
	public void clearFields() {
		nameField.setText("");
		orderIDField.setText("");
		dateOrderedField.setText("");
		dateFilledField.setText("");
	}
	
	//Repopulates the Customer and Order Labels to match the newly selected Order.
	@FXML
	public void switchOrder(MouseEvent e) {
		Order order = (Order) orderField.getSelectionModel().getSelectedItem();
		this.setFields(order);
	}
	
	//Returns the ListView object that holds the Orders.
	public ListView getOrderField() {
		return orderField;
	}
	
	//Sets the list of Order objects that populate the ListView.
	public void setOrderList(ArrayList<Order> orderList) {
		this.orderList = orderList;
	}  
	
	//Returns the list of Order objects that populate the ListView.
	public ArrayList<Order> getOrderList() {
		return orderList;
	}
	
	//Sets the calling controller, InterfaceGUIController, to retrieve information from it. 
	public void setCallingController(InterfaceGUIController callingController) {
		this.callingController = callingController;
	}
	
	//Sets the text value of the nameField Label.
	public void setNameField(String s) {
		nameField.setText(s);
	}
	
	//Sets the text value of the orderIDField Label.
	public void setOrderIDField(String s) {
		orderIDField.setText(s);
	}
	
	//Sets the text value of the dateOrderedField Label.
	public void setDateOrderedField(Date date) {
		dateOrderedField.setText(date.toString());
	}
	
	//Sets the text value of the dateFilledField Label.
	public void setDateFilledField(Date date) {
		dateFilledField.setText(date.toString());
	}

	public Label getDateFilledField() {
		return dateFilledField;
	}
	
	public void returnBackButtonClicked(ActionEvent event) {
		mainPane.getScene().getWindow().hide();
	}

}

