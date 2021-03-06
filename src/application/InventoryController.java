package application;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.input.MouseEvent;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;

import javafx.scene.control.ListView;

import javafx.scene.control.Label;

import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.layout.AnchorPane;

public class InventoryController  implements Initializable {
	@FXML
	private ListView listView;
	@FXML
	private Spinner<Integer> addStockSpinner;
	@FXML
	private Label inventoryColor;
	@FXML
	private Label inventorySize;
	@FXML
	private Label itemID;
	@FXML
	private Label inventoryStock;
	@FXML 
	private AnchorPane mainPane;
	
	@FXML
	private ListView<Item> itemField;
	
	private ArrayList<Item> itemsList; 
	
	private Inventory inventory;
	
	private InterfaceGUIController callingController;


	// Event Listener on Button.onAction
	@FXML
	public void addStockButtonClicked(ActionEvent event) {
		// TODO Autogenerated
		try {
			Item item = (Item) itemField.getSelectionModel().getSelectedItem();		
			item.addStock(addStockSpinner.getValue().intValue());
			this.getItemField().getItems().setAll(callingController.getInventory());
			setLabelField(item);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void setCallingController(InterfaceGUIController interfaceGUIController) {
		this.callingController = interfaceGUIController;
	}
	@FXML
	public void switchItem(MouseEvent e) {
		Item item = (Item) itemField.getSelectionModel().getSelectedItem();
		this.setLabelField(item);
	}
	
	public void setItemList(ArrayList<Item> items) {
		this.itemsList = items;
	}  
	
	public ListView getItemField() {
		return itemField;
	}
	
	public ArrayList<Item> getItems() {
		return itemsList;
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		SpinnerValueFactory<Integer> quanititySpinnerFact = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 50);
		this.addStockSpinner.setValueFactory(quanititySpinnerFact);
		this.addStockSpinner.setEditable(false);
	}
	
	@FXML
	public void returnBack() {
		mainPane.getScene().getWindow().hide();
	}
	
	@FXML
	public void setLabelField(Item item) {
		itemID.setText(item.itemIDString());
		inventoryColor.setText(item.getItemColor());
		inventorySize.setText(item.getItemSize());
		inventoryStock.setText(item.itemStockString());
	}
	
	@FXML
	public void clearFields() {
		itemID.setText("");
		inventoryColor.setText("");
		inventorySize.setText("");
		inventoryStock.setText("");
	}
}
