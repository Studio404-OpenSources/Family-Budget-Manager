package combobox;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import database.familyMembers;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;

public class family {
	public int familyMemberId = 0;
	private familyMembers fm;
	private ResultSet rs;
	private ArrayList<Member> where = new ArrayList<Member>();
	
	public ComboBox<Member> select(int maxWidth){
		fm = new familyMembers();
		rs = fm.selectMembers();
		try {
			while (rs.next()) {
				if(familyMemberId==0){ familyMemberId = rs.getInt("id"); }
				String familyName = rs.getString("name") + " " + rs.getString("surname");
				where.add(new Member(rs.getInt("id"), familyName));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		ObservableList<Member> data = FXCollections.observableArrayList(where);
		ComboBox<Member> combobox = new ComboBox<>(data);
		combobox.getSelectionModel().selectFirst(); 
		combobox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Member>() {
			@Override
			public void changed(ObservableValue<? extends Member> arg0, Member arg1, Member arg2) {
                if (arg2 != null) {
                    //System.out.println("Selected employee: " + arg2.getName()+" "+arg2.getId());
                    familyMemberId = arg2.getId();
                }
            }
        });
		combobox.setMinWidth(maxWidth);
		combobox.getStyleClass().add("chartComboBox");
		
		return combobox;
	}
	
	public ComboBox<Member> selectByIndex(int maxWidth, Member index){
		fm = new familyMembers();
		rs = fm.selectMembers();
		familyMemberId = index.id;
		try {
			while (rs.next()) {
				if(familyMemberId==0){ familyMemberId = rs.getInt("id"); }
				String familyName = rs.getString("name") + " " + rs.getString("surname");
				where.add(new Member(rs.getInt("id"), familyName));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		ObservableList<Member> data = FXCollections.observableArrayList(where);
		ComboBox<Member> combobox = new ComboBox<>(data);
		
		combobox.getSelectionModel().select(index); 
		
		combobox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Member>() {
			@Override
			public void changed(ObservableValue<? extends Member> arg0, Member arg1, Member arg2) {
                if (arg2 != null) {
                    //System.out.println("Selected employee: " + arg2.getName()+" "+arg2.getId());
                    familyMemberId = arg2.getId();
                }
            }
        });
		combobox.setMinWidth(maxWidth);
		combobox.getStyleClass().add("chartComboBox");
		
		return combobox;
	}
	
	public static class Member {
        private int id;
        private String name;

        @Override
        public String toString() {
            return name;
        }

        public Member(int id, String name) {
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
