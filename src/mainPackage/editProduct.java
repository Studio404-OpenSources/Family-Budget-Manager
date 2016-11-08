package mainPackage;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.geometry.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import database.products;

public class editProduct {
	public static void display(int type, String title, int prID){
		//resource
		ResourceBundle rb = ResourceBundle.getBundle("lang", Locale.getDefault());
		products p = new products();
		VBox layout = new VBox();
		Stage window = new Stage();
		window.setTitle(title);
		window.initModality(Modality.APPLICATION_MODAL);
		window.setMinWidth(350);
		
		Label l = new Label();
		l.getStyleClass().add("addFamilyErrorMessageLabel");
		l.setPadding(new Insets(5,10,0,10));
		l.setFocusTraversable(true);
		
		TextField productName = new TextField();
		productName.setPromptText(rb.getString("productName"));
		ResultSet rs = p.selectById(type, prID);	
		try {
			productName.setText(rs.getString(1).toString());
		} catch (SQLException e1) { System.out.println(e1);	}
		
		Button edit = new Button(rb.getString("edit"));
		
		edit.setOnAction(e -> {
			if(productName.getText().isEmpty()){
				l.setText(rb.getString("allfieldsrequired"));
			}else{
				int r = 0;
				
				if(type==1){
					r = p.updateProduct(1, productName.getText(), prID);
				}else{
					r = p.updateProduct(2, productName.getText(), prID);
				}
				if(r==1){
					l.setText(rb.getString("done"));
				}else{
					l.setText(rb.getString("error"));
				}
			}
		});
		
		Button close = new Button(rb.getString("close"));
		close.setOnAction(e -> window.close());
		
		HBox buttonLayouts = new HBox();
		buttonLayouts.getChildren().addAll(edit, close);
		buttonLayouts.setSpacing(10);
		HBox.setMargin(edit, new Insets(0,0,10,10));
		HBox.setMargin(close, new Insets(0,10,10,0));
		
		
		layout.getChildren().addAll(l, productName, buttonLayouts);
		VBox.setMargin(productName, new Insets(0,10,10,10));
		
		
		Scene scene = new Scene(layout);
		
		scene.getStylesheets().add("Viper.css");
		URL url = editProduct.class.getResource("/image/favicon.png");
		window.getIcons().add(new Image(url.toString()));
		
		window.setScene(scene);
		window.showAndWait();
	}
}
