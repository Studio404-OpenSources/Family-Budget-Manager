package mainPackage;

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
import database.familyMembers;
import java.sql.*;

public class FamilyTable {
	private ResourceBundle rb;
	private TableView<FamilyMembers> table;
	private VBox layout;
	private Label FamilyTabletitle;
	private TableColumn<FamilyMembers, String> idColumn;
	private TableColumn<FamilyMembers, String> nameColumn;
	private TableColumn<FamilyMembers, String> surnameColumn;
	private TableColumn<FamilyMembers, String> numberColumn;
	private ObservableList<FamilyMembers> FamilyMembers;
	
	public VBox theLayot(){
		//resource
		rb = ResourceBundle.getBundle("lang", Locale.getDefault());
		layout = new VBox();
		FamilyTabletitle = new Label(rb.getString("familyMembers"));
		FamilyTabletitle.setStyle("-fx-font-size: 16px;");
		FamilyTabletitle.setPadding(new Insets(10,5,5,0));
		
		Button getAllList = new Button();
		getAllList.getStyleClass().add("getAllList");
		getAllList.setMinWidth(30);
		getAllList.setMinHeight(30);
		getAllList.setPadding(new Insets(15,0,15,0));
		getAllList.setOnAction(e -> {
			loadTable.display(rb.getString("familyMembers"), "familyMembers");
		});
		
		
		Button addListItem = new Button();
		addListItem.getStyleClass().add("addListItem");
		addListItem.setMinWidth(30);
		addListItem.setMinHeight(30);
		addListItem.setPadding(new Insets(15,0,15,0));
		
		addListItem.setOnAction(e ->{
			FamilyAddMember.display();
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
				FamilyMembers fm = table.getSelectionModel().getSelectedItem();	
				int selectedItemId = fm.getId();
				
				Button actionButton = new Button(rb.getString("delete"));
				actionButton.setOnAction(ev -> {
					familyMembers fmDB = new familyMembers();
					int rm = fmDB.removeMember(selectedItemId);
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
		tableTopLayout.getChildren().addAll(FamilyTabletitle, getAllList, reloadList, addListItem, removeListItem);
		
		
		
		layout.getChildren().addAll(tableTopLayout, theTable());
		return layout;
	}
	
	@SuppressWarnings("unchecked")
	private TableView<FamilyMembers> theTable(){
		idColumn = new TableColumn<>(rb.getString("id"));
		idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
		
		nameColumn = new TableColumn<>(rb.getString("name"));
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
				
		surnameColumn = new TableColumn<>(rb.getString("surname"));
		surnameColumn.setCellValueFactory(new PropertyValueFactory<>("surname"));
				
		numberColumn = new TableColumn<>(rb.getString("contactNumber"));
		numberColumn.setCellValueFactory(new PropertyValueFactory<>("number"));
		
		table = new TableView<>();
		table.setItems(getFamilyMembers());
		
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
	
	public ObservableList<FamilyMembers> getFamilyMembers(){
		FamilyMembers = FXCollections.observableArrayList();
		familyMembers fm = new familyMembers();
		ResultSet result = fm.selectMembers();
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
