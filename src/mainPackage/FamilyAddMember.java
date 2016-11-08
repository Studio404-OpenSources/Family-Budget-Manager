package mainPackage;

import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;
import database.familyMembers;
import javafx.geometry.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

public class FamilyAddMember {
	private static ResourceBundle rb;
	
	public static void display(){
		//resource
		rb = ResourceBundle.getBundle("lang", Locale.getDefault());
		Stage window = new Stage();
		window.setTitle(rb.getString("FamilyAddMember"));
		window.initModality(Modality.APPLICATION_MODAL);
		window.setMinWidth(350);
		
		Label l = new Label();
		l.getStyleClass().add("addFamilyErrorMessageLabel");
		l.setFocusTraversable(true);
		
		TextField name = new TextField();
		name.setPromptText(rb.getString("name"));
		
		TextField surname = new TextField();
		surname.setPromptText(rb.getString("surname"));
		
		TextField contactNumber = new TextField();
		contactNumber.setPromptText(rb.getString("contactNumber"));
		
		Button a = new Button(rb.getString("add"));
		a.setOnAction(e -> {
			if(name.getText().isEmpty() || surname.getText().isEmpty() || contactNumber.getText().isEmpty()){
				l.setText(rb.getString("allfieldsrequired"));
			}else{
				familyMembers fmad = new familyMembers();
				int backResult = fmad.addMember(name.getText(), surname.getText(), contactNumber.getText());
				if(backResult==1){
					name.setText("");
					surname.setText("");
					contactNumber.setText("");
					l.setText(rb.getString("done"));
				}else{
					l.setText(rb.getString("error"));
				}
			}
		});
		
		Button b = new Button(rb.getString("close"));
		b.setOnAction(e -> window.close());
		
		HBox buttonLayouts = new HBox();
		buttonLayouts.getChildren().addAll(a, b);
		HBox.setMargin(a, new Insets(10,5,20,10));
		HBox.setMargin(b, new Insets(10,0,20,5));
		
		VBox layout = new VBox();
		layout.getChildren().addAll(l, name, surname, contactNumber, buttonLayouts);
		VBox.setMargin(name, new Insets(10,10,5,10));
		VBox.setMargin(surname, new Insets(5,10,5,10));
		VBox.setMargin(contactNumber, new Insets(5,10,10,10));
		layout.setAlignment(Pos.CENTER);
		buttonLayouts.requestFocus();
		
		Scene scene = new Scene(layout);		
		scene.getStylesheets().add("Viper.css");
		
		URL url = FamilyAddMember.class.getResource("/image/favicon.png");
		window.getIcons().add(new Image(url.toString()));
		
		window.setScene(scene);
		window.showAndWait();
	}
}
