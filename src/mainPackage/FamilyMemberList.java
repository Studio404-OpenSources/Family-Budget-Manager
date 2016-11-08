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
import database.familyMembers;
import java.sql.*;

public class FamilyMemberList {
	private ResourceBundle rb;
	public TableView<FamilyMembers> table;
	private VBox layout;
	private TableColumn<FamilyMembers, String> idColumn;
	private TableColumn<FamilyMembers, String> nameColumn;
	private TableColumn<FamilyMembers, String> surnameColumn;
	private TableColumn<FamilyMembers, String> numberColumn;
	private ObservableList<FamilyMembers> FamilyMembers;
	
	public VBox theLayot(int f, int l){
		//resource
		rb = ResourceBundle.getBundle("lang", Locale.getDefault());
		layout = new VBox();
				
		layout.getChildren().addAll(theTable(f, l));
		return layout;
	}
	
	@SuppressWarnings("unchecked")
	private TableView<FamilyMembers> theTable(int f, int l){
		idColumn = new TableColumn<>(rb.getString("id"));
		idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
		idColumn.setMinWidth(245);
		
		nameColumn = new TableColumn<>(rb.getString("name"));
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
		nameColumn.setMinWidth(245);
		
		surnameColumn = new TableColumn<>(rb.getString("surname"));
		surnameColumn.setCellValueFactory(new PropertyValueFactory<>("surname"));
		surnameColumn.setMinWidth(245);
				
		numberColumn = new TableColumn<>(rb.getString("contactNumber"));
		numberColumn.setCellValueFactory(new PropertyValueFactory<>("number"));
		numberColumn.setMinWidth(245);
		
		table = new TableView<>();
		table.setItems(getFamilyMembers(f, l));
		table.getColumns().addAll(idColumn, nameColumn, surnameColumn, numberColumn);
		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		
		table.setRowFactory( tv -> {
		    TableRow<FamilyMembers> row = new TableRow<>();
		    row.setOnMouseClicked(event -> {
		        if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
		        	FamilyMembers rowData = row.getItem();
		            mainPackage.editFamilyMember.display(rowData.getId(), rowData.getName(), rowData.getSurname(), rowData.getNumber());
		        }
		    });
		    return row ;
		});
		
		return table;
	}
	
	public ObservableList<FamilyMembers> getFamilyMembers(int f, int l){
		FamilyMembers = FXCollections.observableArrayList();
		familyMembers fm = new familyMembers();
		ResultSet result = fm.selectMembersFromTo(f, l);
		try{
			while(result.next()){
				FamilyMembers.add(new FamilyMembers(
						result.getInt(1), 
						result.getString(2), 
						result.getString(3), 
						result.getString(4)
				));
			}
		}catch(Exception e){
			System.out.println("error: " + e);
		}
		return FamilyMembers;
		
	}
}
