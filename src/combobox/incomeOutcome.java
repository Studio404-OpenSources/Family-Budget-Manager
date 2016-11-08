package combobox;

import java.util.ArrayList;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;

public class incomeOutcome {
	public int INOU = 1;
	private ArrayList<MyClassx> where = new ArrayList<MyClassx>();
	
	public ComboBox<MyClassx> select(int maxWidth){
		
		where.add(new MyClassx(1, "შემოსავალი"));
		where.add(new MyClassx(2, "გასავალი"));
		
		ObservableList<MyClassx> data = FXCollections.observableArrayList(where);
		ComboBox<MyClassx> combobox = new ComboBox<>(data);
		combobox.getSelectionModel().selectFirst(); 
		
		combobox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<MyClassx>() {
			@Override
			public void changed(ObservableValue<? extends MyClassx> arg0, MyClassx arg1, MyClassx arg2) {
                if (arg2 != null) {
                	INOU = arg2.getId();
                }
            }
        });
		combobox.setMinWidth(maxWidth);
		combobox.getStyleClass().add("chartComboBox");		
		return combobox;		
	}
	
	public static class MyClassx {
        private int id;
        private String name;

        @Override
        public String toString() {
            return name;
        }

        public MyClassx(int id, String name) {
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
