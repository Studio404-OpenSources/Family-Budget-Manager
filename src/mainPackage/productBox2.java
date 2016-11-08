package mainPackage;

import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;
import database.products;
import javafx.geometry.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

public class productBox2 {
	public static void display(String title){
		//resource
		ResourceBundle rb = ResourceBundle.getBundle("lang", Locale.getDefault());
		VBox layout = new VBox();
		Stage window = new Stage();
		window.setTitle(title);
		window.initModality(Modality.APPLICATION_MODAL);
		window.setMinWidth(1000);	
		productIncomeList productIncomeList = new productIncomeList();
		VBox tableLayer = productIncomeList.theLayot(2);
		
		Button add = new Button(rb.getString("add"));
		add.setOnAction(e -> {
			productAddBox.display(2, rb.getString("outcomeListAdd"));
		});
		
		Button reload = new Button(rb.getString("reload"));
		reload.setOnAction(e -> {
			layout.getChildren().remove(1);
			VBox prList = productIncomeList.theLayot(2);
			layout.getChildren().add(1, prList);
			VBox.setMargin(prList, new Insets(0,10,0,10));
		});
		
		Button delete = new Button(rb.getString("delete"));
		delete.setOnAction(e -> {
			TableView<productsSettterGetter> table = productIncomeList.table;
			int selectedIndex = table.getSelectionModel().getSelectedIndex();
			if(selectedIndex >= 0){
				productsSettterGetter prSetGet = table.getSelectionModel().getSelectedItem();
				int selectedItemId = prSetGet.getId();
				Button actionButton = new Button(rb.getString("delete"));
				actionButton.setOnAction(ev -> {
					products proDB = new products();
					int rm = proDB.removeMember(selectedItemId);
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
		
		HBox buttonLayouts = new HBox();
		buttonLayouts.getChildren().addAll(add, reload, delete);
		buttonLayouts.setPadding(new Insets(10,10,10,10));
		buttonLayouts.setSpacing(10);
		
		
		layout.getChildren().addAll(buttonLayouts, tableLayer);
		VBox.setMargin(tableLayer, new Insets(0,10,10,10));
		
		Scene scene = new Scene(layout);
		
		scene.getStylesheets().add("Viper.css");
		URL url = productBox2.class.getResource("/image/favicon.png");
		window.getIcons().add(new Image(url.toString()));
		
		window.setScene(scene);
		window.showAndWait();
	}
}
