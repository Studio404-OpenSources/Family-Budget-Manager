package mainPackage;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Date;
import java.text.SimpleDateFormat;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import database.margeIncomeOutcome;

import java.sql.*;

public class incomeListSearchBox {
	private ResourceBundle rb;
	public TableView<incomeItems> table;
	private VBox layout;
	private TableColumn<incomeItems, Integer> idColumn;
	private TableColumn<incomeItems, String> dateColumn;
	private TableColumn<incomeItems, String> investorColumn;
	private TableColumn<incomeItems, String> amountColumn;
	private TableColumn<incomeItems, String> descColumn;
	private ObservableList<incomeItems> allIncomes;
	
	public VBox theLayot(int typex, long t, long t2, int f, int l){
		//resource
		rb = ResourceBundle.getBundle("lang", Locale.getDefault());
		layout = new VBox();
		
		layout.getChildren().addAll(theTable(typex, t, t2, f, l));
		return layout;
	}
	
	@SuppressWarnings("unchecked")
	private TableView<incomeItems> theTable(int typex, long t, long t2, int f, int l){
		
		idColumn = new TableColumn<>(rb.getString("id"));
		idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
		idColumn.setMinWidth(196);
		
		dateColumn = new TableColumn<>(rb.getString("date"));
		dateColumn.setCellValueFactory(new PropertyValueFactory<>("datex"));
		dateColumn.setMinWidth(196);
		
		descColumn = new TableColumn<>(rb.getString("productName"));
		descColumn.setCellValueFactory(new PropertyValueFactory<>("desc"));
		descColumn.setMinWidth(196);
		
		investorColumn = new TableColumn<>(rb.getString("investor"));
		investorColumn.setCellValueFactory(new PropertyValueFactory<>("investor"));
		investorColumn.setMinWidth(196);	
		
		amountColumn = new TableColumn<>(rb.getString("amount"));
		amountColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));
		amountColumn.setMinWidth(196);
		
		table = new TableView<>();
		table.setItems(getIncomes(typex, t, t2, f, l));
		table.getColumns().addAll(idColumn, dateColumn, descColumn, investorColumn, amountColumn);
		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		
		return table;
	}
	
	public ObservableList<incomeItems> getIncomes(int typex, long t, long t2, int f, int l){
		ResultSet result = null;
		margeIncomeOutcome in = new margeIncomeOutcome();
		if(typex==1){
			result = in.selectIncome(t, t2, f, l);
		}else{
			result = in.selectIncome2(t, t2, f, l);
		}
		allIncomes = FXCollections.observableArrayList();
		
		try{
			while(result.next()){
				Date date=new Date((long)Integer.parseInt(result.getString(2))*1000);
				SimpleDateFormat formatDate = new SimpleDateFormat("dd-MM-yyyy");
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
