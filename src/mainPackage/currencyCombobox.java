package mainPackage;

import java.sql.ResultSet;
import java.sql.SQLException;
import database.currency;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;

public class currencyCombobox {
	private ObservableList<String> options;
	private currency cur;
	private ResultSet rs;
	private String current;
	private ComboBox<String> comboBox;
	
	public ComboBox<String> select(int maxWidth){
		options = FXCollections.observableArrayList();
		cur = new currency();
		rs = cur.selectCur();
		try {
			while (rs.next()) {
				current = rs.getString("name");
			    options.add(current);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		comboBox = new ComboBox<String>(options);
		comboBox.setMinWidth(maxWidth);
		comboBox.getStyleClass().add("chartComboBox");
		comboBox.getSelectionModel().selectFirst();
		
		return comboBox;
	}
	
}