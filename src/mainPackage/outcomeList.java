package mainPackage;

import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import database.outcome;
import java.sql.*;
import java.text.SimpleDateFormat;

public class outcomeList {
	private ResourceBundle rb;
	public TableView<outcomeItems> table;
	private VBox layout;
	private TableColumn<outcomeItems, Integer> idColumn;
	private TableColumn<outcomeItems, String> dateColumn;
	private TableColumn<outcomeItems, String> productNameColumn;
	private TableColumn<outcomeItems, String> whoSpentColumn;
	private TableColumn<outcomeItems, String> amountColumn;
	private ObservableList<outcomeItems> allOutcomes;
	
	public VBox theLayot(int f, int l){
		//resource
		rb = ResourceBundle.getBundle("lang", Locale.getDefault());
		layout = new VBox();
		
		layout.getChildren().addAll(theTable(f, l));
		return layout;
	}
	
	@SuppressWarnings("unchecked")
	private TableView<outcomeItems> theTable(int f, int l){
		idColumn = new TableColumn<>(rb.getString("id"));
		idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
		idColumn.setMinWidth(196);
		
		dateColumn = new TableColumn<>(rb.getString("date"));
		dateColumn.setCellValueFactory(new PropertyValueFactory<>("datex"));
		dateColumn.setMinWidth(196);
		
		productNameColumn = new TableColumn<>(rb.getString("productName"));
		productNameColumn.setCellValueFactory(new PropertyValueFactory<>("productname"));
		productNameColumn.setMinWidth(196);
		
		whoSpentColumn = new TableColumn<>(rb.getString("spenter"));
		whoSpentColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
		whoSpentColumn.setMinWidth(196);
		
		amountColumn = new TableColumn<>(rb.getString("amount"));
		amountColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));
		amountColumn.setMinWidth(196);		
		
		
		table = new TableView<>();
		table.setItems(getOutComes(f, l));
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
	
	public ObservableList<outcomeItems> getOutComes(int f, int l){
		outcome out = new outcome();
		ResultSet result = out.selectOutcomeFromTo(f, l);
		allOutcomes = FXCollections.observableArrayList();
		try{
			while(result.next()){
				Date date=new Date((long)Integer.parseInt(result.getString(2))*1000);
				SimpleDateFormat formatDate = new SimpleDateFormat("dd-MM-yyyy");
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
