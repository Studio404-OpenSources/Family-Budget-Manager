package mainPackage;

import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import database.outcome;
import java.sql.*;
import java.text.SimpleDateFormat;

public class outcomeTable {
	private ResourceBundle rb;
	private TableView<outcomeItems> table;
	private VBox layout;
	private Label outcomeTabletitle;
	private TableColumn<outcomeItems, Integer> idColumn;
	private TableColumn<outcomeItems, String> dateColumn;
	private TableColumn<outcomeItems, String> productNameColumn;
	private TableColumn<outcomeItems, String> whoSpentColumn;
	private TableColumn<outcomeItems, String> amountColumn;
	private ObservableList<outcomeItems> allOutcomes;
	
	public VBox theLayot(){
		//resource
		rb = ResourceBundle.getBundle("lang", Locale.getDefault());
		layout = new VBox();
		outcomeTabletitle = new Label(rb.getString("outcomes"));
		outcomeTabletitle.setStyle("-fx-font-size: 16px;");
		outcomeTabletitle.setPadding(new Insets(10,5,5,0));
		
		Button getAllList = new Button();
		getAllList.getStyleClass().add("getAllList");
		getAllList.setMinWidth(30);
		getAllList.setMinHeight(30);
		getAllList.setPadding(new Insets(15,0,15,0));
		getAllList.setOnAction(e -> {
			loadTable.display(rb.getString("outcomes"), "outcomes");
		});
		
		Button addListItem = new Button();
		addListItem.getStyleClass().add("addListItem");
		addListItem.setMinWidth(30);
		addListItem.setMinHeight(30);
		addListItem.setPadding(new Insets(15,0,15,0));
		
		addListItem.setOnAction(e ->{
			outcomeAdd.display();
		});
		
		Button removeListItem = new Button();
		removeListItem.getStyleClass().add("removeListItem");
		removeListItem.setMinWidth(30);
		removeListItem.setMinHeight(30);
		removeListItem.setPadding(new Insets(15,0,15,0));
		
		Button reloadList = new Button();
		reloadList.getStyleClass().add("reloadList");
		reloadList.setMinWidth(30);
		reloadList.setMinHeight(30);
		reloadList.setPadding(new Insets(15,0,15,0));
		
		reloadList.setOnAction(e ->{
			layout.getChildren().remove(1);
			layout.getChildren().add(1, theTable());
		});
		
		removeListItem.setOnAction(e -> {
			int selectedIndex = table.getSelectionModel().getSelectedIndex();
			if(selectedIndex>=0){
				outcomeItems oI = table.getSelectionModel().getSelectedItem();	
				int selectedItemId = oI.getId();				
				Button actionButton = new Button(rb.getString("delete"));
				actionButton.setOnAction(ev -> {
					outcome otDB = new outcome();
					int rm = otDB.removeMember(selectedItemId);
					if(rm == 1){
						alertBox.display(rb.getString("message"), rb.getString("successDone"));
						table.getItems().remove(selectedIndex);
					}else{
						alertBox.display(rb.getString("message"), rb.getString("errorHappend"));
					}
					Stage stage = (Stage) actionButton.getScene().getWindow();
				    stage.close();			    
				});
				comfirmBox.display(rb.getString("message"), rb.getString("WULTdelete"), actionButton);
			}else{
				alertBox.display(rb.getString("message"), rb.getString("pleaseSelectOneElement"));
			}
		});
		
		HBox tableTopLayout = new HBox();	
		tableTopLayout.setSpacing(5);
		tableTopLayout.getChildren().addAll(outcomeTabletitle, getAllList, reloadList, addListItem, removeListItem);
		
		layout.getChildren().addAll(tableTopLayout, theTable());
		return layout;
	}
	
	@SuppressWarnings("unchecked")
	private TableView<outcomeItems> theTable(){
		idColumn = new TableColumn<>(rb.getString("id"));
		idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
		
		dateColumn = new TableColumn<>(rb.getString("date"));
		dateColumn.setCellValueFactory(new PropertyValueFactory<>("datex"));
		
		productNameColumn = new TableColumn<>(rb.getString("productName"));
		productNameColumn.setCellValueFactory(new PropertyValueFactory<>("productname"));
		
		whoSpentColumn = new TableColumn<>(rb.getString("spenter"));
		whoSpentColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
				
		amountColumn = new TableColumn<>(rb.getString("amount"));
		amountColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));
				
		
		
		table = new TableView<>();
		table.setItems(getOutComes());
		table.getColumns().addAll(idColumn, dateColumn, productNameColumn, whoSpentColumn, amountColumn);
		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		
		table.setRowFactory( tv -> {
		    TableRow<outcomeItems> row = new TableRow<>();
		    row.setOnMouseClicked(event -> {
		        if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
		        	outcomeItems rowData = row.getItem();
		            mainPackage.editOutcome.display(rowData.getId());
		        }
		    });
		    return row ;
		});
		
		return table;
	}
	
	public ObservableList<outcomeItems> getOutComes(){
		outcome out = new outcome();
		ResultSet result = out.selectOutcome();
		allOutcomes = FXCollections.observableArrayList();
		try{
			while(result.next()){
				Date date=new Date((long)Integer.parseInt(result.getString(2))*1000);
				SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy");
				allOutcomes.add(new outcomeItems(
						result.getInt(1),
						formatDate.format(date),
						result.getString(7),
						result.getString(3) + " " +result.getString(4), 
						result.getString(5) + " " +result.getString(6)						
				));
			}
		}catch(Exception e){
			System.out.println("error: " + e);
		}
		return allOutcomes;
		
	}
}
