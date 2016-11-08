package mainPackage;

import java.util.Locale;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import database.products;

import java.sql.*;

public class productIncomeList {
	private ResourceBundle rb;
	public TableView<productsSettterGetter> table;
	private VBox layout;
	private TableColumn<productsSettterGetter, String> idColumn;
	private TableColumn<productsSettterGetter, String> nameColumn;
	private ObservableList<productsSettterGetter> ProductLists;
	
	public VBox theLayot(int type){
		//resource
		rb = ResourceBundle.getBundle("lang", Locale.getDefault());
		layout = new VBox();
				
		layout.getChildren().addAll(theTable(type));
		return layout;
	}
	
	@SuppressWarnings("unchecked")
	private TableView<productsSettterGetter> theTable(int type){
		idColumn = new TableColumn<>(rb.getString("id"));
		idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
		idColumn.setMinWidth(245);
		
		nameColumn = new TableColumn<>(rb.getString("name"));
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
		nameColumn.setMinWidth(245);

		table = new TableView<>();
		table.setItems(getFamilyMembers(type));
		table.getColumns().addAll(idColumn, nameColumn);
		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		
		table.setRowFactory( tv -> {
		    TableRow<productsSettterGetter> row = new TableRow<>();
		    row.setOnMouseClicked(event -> {
		        if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
		        	productsSettterGetter rowData = row.getItem();
		        	String title = null;
		        	if(type==1){
		        		title = rb.getString("incomeListEdit");
		        	}else{
		        		title = rb.getString("outcomeListEdit");
		        	}
		        	editProduct.display(type, title, rowData.getId());
		        }
		    });
		    return row ;
		});
		
		return table;
	}
	
	public ObservableList<productsSettterGetter> getFamilyMembers(int type){
		ResultSet result = null;
		ProductLists = FXCollections.observableArrayList();
		products pr = new products();
		if(type==1){
			result = pr.selectMembersFromTo();
		}else{
			result = pr.selectMembersFromTo2();
		}
		try{
			while(result.next()){
				ProductLists.add(new productsSettterGetter(
						result.getInt(1), 
						result.getString(2)
				));
			}
		}catch(Exception e){
			System.out.println("error: " + e);
		}
		return ProductLists;
		
	}
}
