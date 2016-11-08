package combobox;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import database.products;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;

public class productsC {
	public int productId = 0;
	private products pr;
	private ResultSet rs;
	private ArrayList<Product> where = new ArrayList<Product>();
	
	public ComboBox<Product> select(int maxWidth){
		pr = new products();
		rs = pr.select();
		try {
			while (rs.next()) {
				//productNames = rs.getString("productname");
				if(productId==0){ productId = rs.getInt("id"); }
				where.add(new Product(rs.getInt("id"), rs.getString("productname")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}	


		ObservableList<Product> data = FXCollections.observableArrayList(where);
		ComboBox<Product> combobox = new ComboBox<>(data);
		combobox.getSelectionModel().selectFirst(); 
		
		combobox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Product>() {
			@Override
			public void changed(ObservableValue<? extends Product> arg0, Product arg1, Product arg2) {
                if (arg2 != null) {
                    //System.out.println("Selected employee: " + arg2.getName()+" "+arg2.getId());
                	productId = arg2.getId();
                }
            }
        });
		combobox.setMinWidth(maxWidth);
		combobox.getStyleClass().add("chartComboBox");
		
		return combobox;
	}
	
	public ComboBox<Product> selectByIndex(int maxWidth, Product index){
		pr = new products();
		rs = pr.select();
		productId = index.id;
		try {
			while (rs.next()) {
				if(productId==0){ productId = rs.getInt("id"); }
				where.add(new Product(rs.getInt("id"), rs.getString("productname")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}	


		ObservableList<Product> data = FXCollections.observableArrayList(where);
		ComboBox<Product> combobox = new ComboBox<>(data);
		combobox.getSelectionModel().select(index);
		
		combobox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Product>() {
			@Override
			public void changed(ObservableValue<? extends Product> arg0, Product arg1, Product arg2) {
                if (arg2 != null) {
                    //System.out.println("Selected employee: " + arg2.getName()+" "+arg2.getId());
                	productId = arg2.getId();
                }
            }
        });
		combobox.setMinWidth(maxWidth);
		combobox.getStyleClass().add("chartComboBox");
		
		return combobox;
	}
	
	public ComboBox<Product> select2(int maxWidth){
		pr = new products();
		rs = pr.select2();
		try {
			while (rs.next()) {
				if(productId==0){ productId = rs.getInt("id"); }
				where.add(new Product(rs.getInt("id"), rs.getString("productname2")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}	


		ObservableList<Product> data = FXCollections.observableArrayList(where);
		ComboBox<Product> combobox = new ComboBox<>(data);
		combobox.getSelectionModel().selectFirst(); 
		
		combobox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Product>() {
			@Override
			public void changed(ObservableValue<? extends Product> arg0, Product arg1, Product arg2) {
                if (arg2 != null) {
                    productId = arg2.getId();
                }
            }
        });
		combobox.setMinWidth(maxWidth);
		combobox.getStyleClass().add("chartComboBox");
		
		return combobox;
	}
	
	public ComboBox<Product> selectByIndex2(int maxWidth, Product index){
		pr = new products();
		rs = pr.select2();
		productId = index.id;
		try {
			while (rs.next()) {
				if(productId==0){ productId = rs.getInt("id"); }
				where.add(new Product(rs.getInt("id"), rs.getString("productname2")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}	


		ObservableList<Product> data = FXCollections.observableArrayList(where);
		ComboBox<Product> combobox = new ComboBox<>(data);
		combobox.getSelectionModel().select(index);
		
		combobox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Product>() {
			@Override
			public void changed(ObservableValue<? extends Product> arg0, Product arg1, Product arg2) {
                if (arg2 != null) {
                    //System.out.println("Selected employee: " + arg2.getName()+" "+arg2.getId());
                	productId = arg2.getId();
                }
            }
        });
		combobox.setMinWidth(maxWidth);
		combobox.getStyleClass().add("chartComboBox");
		
		return combobox;
	}
	
	public static class Product {
        private int id;
        private String name;

        @Override
        public String toString() {
            return name;
        }

        public Product(int id, String name) {
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
