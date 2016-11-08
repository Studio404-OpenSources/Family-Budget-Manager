package mainPackage;

import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import database.*;

public class loadTableSearchBox {
	
	public static void display(String title, String typex, long fromTime, long toTime){
		//resource
		ResourceBundle rb = ResourceBundle.getBundle("lang", Locale.getDefault());
		ObservableList<String> pageOptions = FXCollections.observableArrayList();
		ComboBox<String> pageCombo = new ComboBox<String>();
		Stage window = new Stage();
		window.setTitle(title);
		window.initModality(Modality.APPLICATION_MODAL);
		window.setMinWidth(1000);
		window.setMinHeight(550);
		
		//root layout
		VBox root = new VBox();
		// top layout
		HBox topLayout = new HBox();
		topLayout.setSpacing(10);
		topLayout.setPadding(new Insets(10,10,10,10));
		// center layout
		HBox centerLayout = new HBox();
		centerLayout.setSpacing(10);
		centerLayout.setPadding(new Insets(10,10,10,10));
		
		// top elements
		Label page = new Label(rb.getString("page")+":");		
		page.setMinHeight(20);
		
		// center elements
		switch(typex){
			case "incomes":
				// databse 
				margeIncomeOutcome margeIncomeOutcome = new margeIncomeOutcome();
				int all2 = margeIncomeOutcome.countMembers(fromTime, toTime);
				int quentityVal2 = 20;
				int forEchVal2 = ((int) Math.ceil(((double) all2 / quentityVal2)));
				if(forEchVal2<=0){ forEchVal2=1;}
				
				for(int i = 1; i<=forEchVal2; i++){
					pageOptions.add(i+"");
				}
				
				incomeListSearchBox incomeListSearchBox = new incomeListSearchBox();
				VBox centerTabelLayout = incomeListSearchBox.theLayot(1, fromTime, toTime, 0, quentityVal2);
				
				centerLayout.getChildren().add(centerTabelLayout);
			break;
			case "outcomes":
				// databse 
				margeIncomeOutcome margeIncomeOutcome2 = new margeIncomeOutcome();
				int all3 = margeIncomeOutcome2.countMembers2(fromTime, toTime);
				int quentityVal3 = 20;
				int forEchVal3 = ((int) Math.ceil(((double) all3 / quentityVal3)));
				if(forEchVal3<=0){ forEchVal3=1;}
				
				for(int i = 1; i<=forEchVal3; i++){
					pageOptions.add(i+"");
				}
				
				incomeListSearchBox incomeListSearchBox2 = new incomeListSearchBox();
				VBox centerTabelLayout2 = incomeListSearchBox2.theLayot(2, fromTime, toTime, 0, quentityVal3);			

				centerLayout.getChildren().add(centerTabelLayout2);
			break;
		}
	
		pageCombo.setItems(pageOptions);
		pageCombo.setValue("1");
		
		/*
		 * combo listeners
		 * */
		pageCombo.getSelectionModel().selectedItemProperty().addListener( (v, oldValue, newValue) -> {
			try{
				root.getChildren().remove(1);
				int quentity_ = 20;
				int page_ = pageCombo.getSelectionModel().getSelectedIndex() * quentity_; 
				
				HBox centerLayout_ = new HBox();
				if(typex=="incomes"){
					incomeListSearchBox centerTable2_ = new incomeListSearchBox();
					VBox centerTabelLayout2_ = centerTable2_.theLayot(1, fromTime, toTime, page_,quentity_);
					centerLayout_.getChildren().add(centerTabelLayout2_);
					HBox.setMargin(centerTabelLayout2_, new Insets(10,10,10,10));
				}else if(typex=="outcomes"){
					incomeListSearchBox centerTable3_ = new incomeListSearchBox();
					VBox centerTabelLayout3_ = centerTable3_.theLayot(2, fromTime, toTime, page_,quentity_);
					centerLayout_.getChildren().add(centerTabelLayout3_);
					HBox.setMargin(centerTabelLayout3_, new Insets(10,10,10,10));
				}
				root.getChildren().add(centerLayout_);
			}catch(Exception ex){
				System.out.println(ex);
			}
		});
		
		topLayout.getChildren().addAll(page, pageCombo);
		
		
		root.getChildren().addAll(topLayout, centerLayout);
		Scene scene = new Scene(root);
		
		scene.getStylesheets().add("Viper.css");
		URL url = loadTableSearchBox.class.getResource("/image/favicon.png");
		window.getIcons().add(new Image(url.toString()));
		
		window.setScene(scene);
		window.showAndWait();
	}
}
