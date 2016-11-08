package mainPackage;

import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.geometry.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import database.products;

public class productAddBox {
	public static void display(int type, String title){
		//resource
		ResourceBundle rb = ResourceBundle.getBundle("lang", Locale.getDefault());
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
		
		Button add = new Button(rb.getString("add"));
		
		add.setOnAction(e -> {
			if(productName.getText().isEmpty()){
				l.setText(rb.getString("allfieldsrequired"));
			}else{
				int r = 0;
				products p = new products();
				if(type==1){
					r = p.addProduct(productName.getText());
				}else{
					r = p.addProduct2(productName.getText());
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
		buttonLayouts.getChildren().addAll(add, close);
		buttonLayouts.setSpacing(10);
		HBox.setMargin(add, new Insets(0,0,10,10));
		HBox.setMargin(close, new Insets(0,10,10,0));
		
		
		layout.getChildren().addAll(l, productName, buttonLayouts);
		VBox.setMargin(productName, new Insets(0,10,10,10));
		
		
		Scene scene = new Scene(layout);
		
		scene.getStylesheets().add("Viper.css");
		URL url = productAddBox.class.getResource("/image/favicon.png");
		window.getIcons().add(new Image(url.toString()));
		
		window.setScene(scene);
		window.showAndWait();
	}
}
