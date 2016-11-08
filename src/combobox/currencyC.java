package combobox;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import database.currency;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;

public class currencyC {
	public int currencyId = 0;
	private currency cur;
	private ResultSet rs;
	private ArrayList<MyCurrency> where = new ArrayList<MyCurrency>();
	
	public ComboBox<MyCurrency> select(int maxWidth){
		cur = new currency();
		rs = cur.selectCur();
		try {
			while (rs.next()) {
				//current = rs.getString("name");
				if(currencyId==0){ currencyId = rs.getInt("id"); }
				where.add(new MyCurrency(rs.getInt("id"), rs.getString("name")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		ObservableList<MyCurrency> data = FXCollections.observableArrayList(where);
		ComboBox<MyCurrency> combobox = new ComboBox<>(data);
		combobox.getSelectionModel().selectFirst(); 
		
		combobox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<MyCurrency>() {
			@Override
			public void changed(ObservableValue<? extends MyCurrency> arg0, MyCurrency arg1, MyCurrency arg2) {
                if (arg2 != null) {
                    currencyId = arg2.getId();
                }
            }
        });
		combobox.setMinWidth(maxWidth);
		combobox.getStyleClass().add("chartComboBox");
		
		return combobox;
	}
	
	public ComboBox<MyCurrency> selectByIndex(int maxWidth, MyCurrency myCurrency) {
		cur = new currency();
		rs = cur.selectCur();
		currencyId = myCurrency.id;
		try {
			while (rs.next()) {
				//current = rs.getString("name");
				if(currencyId==0){ currencyId = rs.getInt("id"); }
				where.add(new MyCurrency(rs.getInt("id"), rs.getString("name")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		ObservableList<MyCurrency> data = FXCollections.observableArrayList(where);
		ComboBox<MyCurrency> combobox = new ComboBox<>(data);
		combobox.getSelectionModel().select(myCurrency);
		
		combobox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<MyCurrency>() {
			@Override
			public void changed(ObservableValue<? extends MyCurrency> arg0, MyCurrency arg1, MyCurrency arg2) {
                if (arg2 != null) {
                    currencyId = arg2.getId();
                }
            }
        });
		combobox.setMinWidth(maxWidth);
		combobox.getStyleClass().add("chartComboBox");
		
		return combobox;
	}
	
	public static class MyCurrency {
        private int id;
        private String name;

        @Override
        public String toString() {
            return name;
        }

        public MyCurrency(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

	
	}
}
