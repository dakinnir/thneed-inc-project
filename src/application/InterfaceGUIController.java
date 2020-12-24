package application;

import java.io.IOException;
import java.net.URL;
import java.io.*;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.activation.ActivationDataFlavor;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/** @author Parker Mores, Jason Esquivel, Arjun Mathur
 * 
 *	This is the controller class for our primary interface. The primary function is to populate a list of Customers and Orders. 
 *	We then can view the status of Orders and the Customers they belong to on a second function accessible by clicking the "View Orders" button.
 *
 *  Last updated 10/22/2020
 */

public class InterfaceGUIController implements Initializable {
	@FXML
	private TextField custField;
	@FXML
	private TextField nameField;
	@FXML
	private TextField adField;
	@FXML
	private TextField phoneField;
	@FXML
	private Button custButton;
	@FXML
	private Button saveToFileButton;
	@FXML
	private TextField orderField;
	@FXML
	private Button viewOrderButton;
	@FXML
	private Button addOrderButton;
	@FXML
	private ComboBox<String> custBox;
	
	private Button loadFromFileButton;
	
	// For ordering details
	
	@FXML
	private RadioButton small;
	@FXML
	private RadioButton medium;
	@FXML
	private RadioButton large;
	
	@FXML
	private ToggleGroup size;
	
	@FXML
	private RadioButton orange;
	
	@FXML
	private RadioButton blue;
	
	@FXML
	private RadioButton green;
	
	@FXML
	private ToggleGroup color;
	@FXML
	
	private ArrayList<String> order = new ArrayList<>();
	
	@FXML
	private Spinner quantity;
	
	private Inventory inventory = new Inventory();
	private Item item;

	
	private ArrayList<Order> pendingOrders = new ArrayList<>();
	private ArrayList<Order> completedOrders = new ArrayList<>();
	private ArrayList<Customer> custList = new ArrayList<>();
	


	// Adds a Customer to a list after checking to ensure that the Customer ID is not a duplicate, and that all fields have been filled in. 
	@FXML
	private void addCustomerButtonClick(ActionEvent event) {	
		if (custField.getText().equals("")) {
			new Alert(AlertType.WARNING, "Customer ID Field is empty. Could not create new Customer.").showAndWait();	
			return;
		} else if (nameField.getText().equals("")) {
			new Alert(AlertType.WARNING, "Name Field is empty. Could not create new Customer.").showAndWait();
			return;
		} else if (adField.getText().equals("")) {
			new Alert(AlertType.WARNING, "Address Field is empty. Could not create new Customer.").showAndWait();
			return;
		} else if (!isValid(phoneField.getText()) || phoneField.getText().equals("")) {
			new Alert(AlertType.WARNING, "Phone Field doesn't follow pattern. Could not create new Customer.").showAndWait();
			return;
		} 
		
		Customer newCustomer = new Customer(custField.getText(), nameField.getText(), adField.getText(), phoneField.getText());	
		custList.add(newCustomer);
		
		this.populateCustBox();
		
		for (int i = 0; i < custList.size() - 1; i++) {
			if (custField.getText().equals(custList.get(i).getCustomerID())) {
				new Alert(AlertType.WARNING, "Customer ID is a duplicate. Please enter the data again.").showAndWait();
				custList.remove(i);
				return;
			} 
		}
		
		new Alert(AlertType.INFORMATION, "Customer ID #" + newCustomer.getCustomerID() + " has been created.").showAndWait();
		this.clearFields();
		
	}
	
	/*Creates an order object using a text field for an Order ID number, as well as a Customer ID number and adds it to the Order list, 
	 * as well as displaying it on the second window
	 */
	@FXML
	private void addOrderButtonClick(ActionEvent event) {
		if (orderField.getText().equals("")) {
			new Alert(AlertType.WARNING, "Order ID Field is empty. Could not create new Order.").showAndWait();	
			return;
		}
		int temp = 0;
		temp = Integer.parseInt(orderField.getText());
		if (temp == 0) {
			new Alert(AlertType.WARNING, "Order ID Field must be an integer. Could not create new Order.").showAndWait();	
			return;
		}
		
		if(!isValidOrderNumber(orderField.getText())) {
			new Alert(AlertType.WARNING, "Order ID Field must be an integer.").showAndWait();	
			return;
		}
		
		String value = (String) custBox.getValue();
		int index = -1;
		
		for (int i = 0; i < custList.size(); i++) {
			if (value.equals(custList.get(i).getCustomerID())) {
				index = i;
			}
		}
		
		// For order details
		String orderDetails = "";
		
		// Retrieving the user information entry on selected toggle
		RadioButton sizeSelected = (RadioButton)size.getSelectedToggle();
		RadioButton colorSelected = (RadioButton)color.getSelectedToggle();
		
		// Getting the order entry
		String orderQuantity = quantity.getValue().toString();
		int intQuantity = Integer.parseInt(orderQuantity);
		
		String orderSize = sizeSelected.getText(); 
		String orderColor = colorSelected.getText();
		
		
		System.out.println(intQuantity);
		// Check for no null pointer
		if (sizeSelected == null || colorSelected == null) { 
			new Alert(AlertType.WARNING, "Must enter order details. Please enter the data again.").showAndWait();
			return;
		} 
		
		if (intQuantity > inventory.getInventoryStock(orderColor, orderSize)) {
			System.out.println("it got in");
			new Alert(AlertType.WARNING, "We only have " + inventory.getInventoryStock(orderColor, orderSize) + " in stock!").showAndWait();
			return;
		}
		else {
			orderDetails += "Size: " + orderSize + "\t Color: " + orderColor + "\t Quantity: " + orderQuantity + "\n";
			order.add(orderDetails);
			inventory.removeStock(orderColor, orderSize, intQuantity);
		}
		
		Order newOrder = new Order(Integer.parseInt(orderField.getText()), custList.get(index), order);
		pendingOrders.add(newOrder);
		for (int i = 0; i < pendingOrders.size() - 1; i++) {
			if (orderField.getText().equals(pendingOrders.get(i).getOrderID())) {
				new Alert(AlertType.WARNING, "Order ID is a duplicate. Please enter the data again.").showAndWait();
				pendingOrders.remove(i);
				return;
			}
		}
		
		new Alert(AlertType.INFORMATION, "New Order " + newOrder.getOrderID() + " has been created.").showAndWait();
		this.clearFields();
	}
		
	
	/*
	 * Button that creates a new interface based on the DisplayOrder FXML file and operates based on its controller class. 
	 * Allows us to view all orders that have been placed, including the Order ID number and all of the Customer's personal information. 
	 */
	@FXML
	private void viewOrderButtonClick(ActionEvent event) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/DisplayOrders.fxml"));
		AnchorPane dialogRoot;
		
		if (pendingOrders.size() > 0) {
			try {
				dialogRoot = (AnchorPane) loader.load();
				Scene dialogScene = new Scene(dialogRoot);
				Stage dialogStage = new Stage();
				dialogStage.setScene(dialogScene);
				DisplayOrdersController dialogController = (DisplayOrdersController) loader.getController();
				dialogController.setCallingController(this);
					
				//Code goes here
				dialogController.setOrderList(this.getOrders());
				dialogController.getOrderField().getItems().setAll(this.getOrders());
				if (this.getOrders().size() > 0) { dialogController.setFields(getOrders().get(0));} 
				else { dialogController.clearFields(); }
				//Code goes here
			
				dialogStage.show();
				System.out.println("DisplayOrdersController Loaded");
			}
		 catch(IOException e) {
			e.printStackTrace();
			}
		} else {
			new Alert(AlertType.WARNING, "There are no pending orders.").showAndWait();
		}
	}
	
	// Forcing user entry to be actual numbers and in a certain pattern
	public boolean isValid(String phone) {
		if (phone.matches("\\d{3}[-\\.\\s]\\d{3}[-\\.\\s]\\d{4}")) {
			return true;
		}
		return false;
	}
	
	public boolean isValidOrderNumber(String orderNum) {
		if (orderNum.matches("\\d{1}") 
				|| orderNum.matches("\\d{2}") 
				|| orderNum.matches("\\d{3}")
				|| orderNum.matches("\\d{4}")) {
			return true;
		}
		return false;
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		SpinnerValueFactory<Integer> quanititySpinnerFact = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 50);
		this.quantity.setValueFactory(quanititySpinnerFact);
		this.quantity.setEditable(false);
	}

	
	//Populates the drop down menu with Customer IDs that have already been created or loaded from a file.
	@FXML
	private void populateCustBox() {
		ArrayList<String> custIDs = new ArrayList<>();
		for (int i = 0; i < custList.size(); i++) {
			custIDs.add(custList.get(i).getCustomerID());
		}
		
		
		custBox.getItems().setAll(custIDs);
	}

	// Saves pending and completed Orders, as well as all of the Customers that have been created into three different serialized files.
	@FXML
	private void saveToFileButtonClick(ActionEvent event) {
		try {
			FileOutputStream custFileOut = new FileOutputStream("customer.ser");
			FileOutputStream pendFileOut = new FileOutputStream("pendOrder.ser");
			FileOutputStream compFileOut = new FileOutputStream("compOrder.ser");
			
			ObjectOutputStream custOut = new ObjectOutputStream(custFileOut);
			custOut.writeObject(custList);
			
			ObjectOutputStream pendOut = new ObjectOutputStream(pendFileOut);
			pendOut.writeObject(pendingOrders);
			
			ObjectOutputStream compOut = new ObjectOutputStream(compFileOut);
			compOut.writeObject(completedOrders);
			
			System.out.printf("Saved to customer.ser");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// Loads all of the pending and completed Orders that were saved in a previous iteration of this application, and stores that data into lists
	@FXML
	public void loadFromFileButtonClick() {
		try {
	         FileInputStream custFileIn = new FileInputStream("customer.ser");
	         FileInputStream pendOrderFileIn = new FileInputStream("pendOrder.ser");
	         FileInputStream compOrderFileIn = new FileInputStream("compOrder.ser");
	         ObjectInputStream custIn = new ObjectInputStream(custFileIn);
	         ObjectInputStream pendOrderIn = new ObjectInputStream(pendOrderFileIn);
	         ObjectInputStream compOrderIn = new ObjectInputStream(compOrderFileIn);
	         custList = (ArrayList<Customer>) custIn.readObject();
	         pendingOrders = (ArrayList<Order>) pendOrderIn.readObject();
	         completedOrders = (ArrayList<Order>) compOrderIn.readObject();
	         custIn.close();
	         pendOrderIn.close();
	         compOrderIn.close();
	         custFileIn.close();
	         pendOrderFileIn.close();
	         compOrderFileIn.close();
	      } catch (IOException i) {
	         i.printStackTrace();
	         return;
	      } catch (ClassNotFoundException c) {
	         System.out.println("Employee class not found");
	         c.printStackTrace();
	         return;
	      }
		
		this.populateCustBox();
	}
	
	//Clears all of the TextFields and the ComboBox
	@FXML
	public void clearFields() {
		custField.clear();
		nameField.clear();
		adField.clear();
		phoneField.clear();
		orderField.clear();
		custBox.getSelectionModel().clearSelection();
		custBox.setValue(null);
	}
	
	//Populates a list that we can pass to the second interface's controller in order to display the Orders and the relevant information.
	public ArrayList<Order> getOrders() {
		ArrayList<Order> newOrderList = new ArrayList<>();
		for (int i = 0; i < pendingOrders.size(); i++) {
			newOrderList.add(pendingOrders.get(i));
		}
		for (int k = 0; k < completedOrders.size(); k++) {
			newOrderList.add(completedOrders.get(k));
		}
		return newOrderList;
	}
	
	// Load the inventory window to manage the existing inventory stocks
	public void inventoryButtonClicked(ActionEvent event) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/Inventory.fxml"));
		AnchorPane dialogRoot;
		
		 try {
			 	dialogRoot = (AnchorPane) loader.load();
				Scene dialogScene = new Scene(dialogRoot);
				Stage dialogStage = new Stage();
				dialogStage.setScene(dialogScene);
				InventoryController dialogController = (InventoryController) loader.getController();
				dialogController.setCallingController(this);
				dialogStage.show();		
				
				// List view settings
				dialogController.setItemList(this.getInventory());
				dialogController.getItemField().getItems().setAll(this.getInventory());

		 }
		 catch (Exception e) {
			 e.printStackTrace();
		}
	}
	// A method for returning the array list of items
	public ArrayList<Item> getInventory() {
		return inventory.getItems();
	}

}
