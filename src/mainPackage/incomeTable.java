package mainPackage;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Date;
import java.text.SimpleDateFormat;
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
import database.income;
import java.sql.*;

public class incomeTable {
	private ResourceBundle rb;
	private TableView<incomeItems> table;
	private VBox layout;
	private Label incomeTabletitle;
	private TableColumn<incomeItems, Integer> idColumn;
	private TableColumn<incomeItems, String> dateColumn;
	private TableColumn<incomeItems, String> investorColumn;
	private TableColumn<incomeItems, String> amountColumn;
	private TableColumn<incomeItems, String> descColumn;
	private ObservableList<incomeItems> allIncomes;
	
	public VBox theLayot(){
		//resource
		rb = ResourceBundle.getBundle("lang", Locale.getDefault());
		layout = new VBox();
		incomeTabletitle = new Label(rb.getString("incomes"));
		incomeTabletitle.setStyle("-fx-font-size: 16px;");
		incomeTabletitle.setPadding(new Insets(10,5,5,0));
		
		Button getAllList = new Button();
		getAllList.getStyleClass().add("getAllList");
		getAllList.setMinWidth(30);
		getAllList.setMinHeight(30);
		getAllList.setPadding(new Insets(15,0,15,0));
		getAllList.setOnAction(e->{
			loadTable.display(rb.getString("incomes"), "incomes");
		});
		
		Button addListItem = new Button();
		addListItem.getStyleClass().add("addListItem");
		addListItem.setMinWidth(30);
		addListItem.setMinHeight(30);
		addListItem.setPadding(new Insets(15,0,15,0));
		
		addListItem.setOnAction(e ->{
			incomeAdd.display();
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
				incomeItems iI = table.getSelectionModel().getSelectedItem();	
				
				int selectedItemId = iI.getId();
				
				Button actionButton = new Button(rb.getString("delete"));
				actionButton.setOnAction(ev -> {
					income inDB = new income();
					int rm = inDB.removeMember(selectedItemId);
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
		tableTopLayout.getChildren().addAll(incomeTabletitle, getAllList, reloadList, addListItem, removeListItem);
		
		
		layout.getChildren().addAll(tableTopLayout, theTable());
		return layout;
	}
	
	@SuppressWarnings("unchecked")
	private TableView<incomeItems> theTable(){
		
		idColumn = new TableColumn<>(rb.getString("id"));
		idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
		
		dateColumn = new TableColumn<>(rb.getString("date"));
		dateColumn.setCellValueFactory(new PropertyValueFactory<>("datex"));
		
		descColumn = new TableColumn<>(rb.getString("productName"));
		descColumn.setCellValueFactory(new PropertyValueFactory<>("desc"));
				
		investorColumn = new TableColumn<>(rb.getString("investor"));
		investorColumn.setCellValueFactory(new PropertyValueFactory<>("investor"));
				
		amountColumn = new TableColumn<>(rb.getString("amount"));
		amountColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));
		
		table = new TableView<>();
		table.setItems(getIncomes());
		table.getColumns().addAll(idColumn, dateColumn, descColumn, investorColumn, amountColumn);
		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		
		table.setRowFactory( tv -> {
		    TableRow<incomeItems> row = new TableRow<>();
		    row.setOnMouseClicked(event -> {
		        if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
		        	incomeItems rowData = row.getItem();
		        	System.out.println(rowData.getId());
		            mainPackage.editIncome.display(rowData.getId());
		        }
		    });
		    return row ;
		});
		
		return table;
	}
	
	public ObservableList<incomeItems> getIncomes(){
		income in = new income();
		ResultSet result = in.selectIncome();
		allIncomes = FXCollections.observableArrayList();
		
		//long unixTime = System.currentTimeMillis() / 1000L;
		//Date time=new Date((long)unixTime*1000);
		
		try{
			while(result.next()){
				Date date=new Date((long)Integer.parseInt(result.getString(2))*1000);
				SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy");
				allIncomes.add(new incomeItems(	
						result.getInt(1),
						formatDate.format(date),
						result.getString(3) + " " +result.getString(4), 
						result.getString(5) + " " +result.getString(6),
						result.getString(7)
				));
			}
		}catch(Exception e){
			System.out.println("error: " + e);
		}
		
		return allIncomes;
		
	}
}
