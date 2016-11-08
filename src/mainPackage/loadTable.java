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

public class loadTable {
	public static Button delete;
	
	public static void display(String title, String typex){
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
		
		Button add = new Button(rb.getString("add"));
		add.setMinHeight(20);
		
		Button reloadTable = new Button(rb.getString("reload"));
		reloadTable.setMinHeight(20);

		delete = new Button(rb.getString("delete"));
		delete.setMinHeight(20);
		
		// center elements
		switch(typex){
			case "familyMembers":
				// databse 
				familyMembers familyMembers = new familyMembers();
				int all = familyMembers.countMembers();
				int quentityVal = 20;
				int forEchVal = ((int) Math.ceil(((double) all / quentityVal)));
				if(forEchVal<=0){ forEchVal=1;}

				for(int i = 1; i<=forEchVal; i++){ 
					pageOptions.add(i+"");
				}
				FamilyMemberList centerTable = new FamilyMemberList();
				VBox centerTabelLayout = centerTable.theLayot(0,quentityVal);
				
				add.setOnAction(e ->{
					FamilyAddMember.display();
				});
				
				reloadTable.setOnAction(e ->{
					centerTabelLayout.getChildren().remove(0);
					centerTabelLayout.getChildren().add(0, centerTable.theLayot(0, quentityVal));
					pageCombo.setValue("1");
				});
				
				delete.setOnAction(e -> {
					TableView<FamilyMembers> table = centerTable.table;
					int selectedIndex = table.getSelectionModel().getSelectedIndex();
					if(selectedIndex>=0){						
						FamilyMembers fm = table.getSelectionModel().getSelectedItem();	
						int selectedItemId = fm.getId();						
						Button actionButton = new Button(rb.getString("delete"));
						actionButton.setOnAction(ev -> {
							familyMembers fmDB = new familyMembers();
							int rm = fmDB.removeMember(selectedItemId);
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
				
				centerLayout.getChildren().add(centerTabelLayout);
			break;
			case "incomes":
				// databse 
				income income = new income();
				int all2 = income.countMembers();
				int quentityVal2 = 20;
				int forEchVal2 = ((int) Math.ceil(((double) all2 / quentityVal2)));
				if(forEchVal2<=0){ forEchVal2=1;}
				
				for(int i = 1; i<=forEchVal2; i++){
					pageOptions.add(i+"");
				}
				
				incomeList centerTable2 = new incomeList();
				VBox centerTabelLayout2 = centerTable2.theLayot(0, quentityVal2);
				
				add.setOnAction(e ->{
					incomeAdd.display();
				});
				
				reloadTable.setOnAction(e ->{
					centerTabelLayout2.getChildren().remove(0);
					centerTabelLayout2.getChildren().add(0, centerTable2.theLayot(0, quentityVal2));
					pageCombo.setValue("1");
				});
				
				delete.setOnAction(e -> {
					TableView<incomeItems> table = centerTable2.table;
					int selectedIndex = table.getSelectionModel().getSelectedIndex();
					if(selectedIndex>=0){
						incomeItems fm = table.getSelectionModel().getSelectedItem();
						int selectedItemId = fm.getId();						
						Button actionButton = new Button(rb.getString("delete"));
						actionButton.setOnAction(ev -> {
							income inDB = new income();
							int rm = inDB.removeMember(selectedItemId);
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
				
				centerLayout.getChildren().add(centerTabelLayout2);
			break;
			case "outcomes":
				// databse 
				outcome outcome = new outcome();
				int all3 = outcome.countMembers();
				int quentityVal3 = 20;
				int forEchVal3 = ((int) Math.ceil(((double) all3 / quentityVal3)));
				if(forEchVal3<=0){ forEchVal3=1;}
				
				for(int i = 1; i<=forEchVal3; i++){
					pageOptions.add(i+"");
				}
				
				outcomeList centerTable3 = new outcomeList();
				VBox centerTabelLayout3 = centerTable3.theLayot(0, quentityVal3);
				
				add.setOnAction(e ->{
					outcomeAdd.display();
				});
				
				reloadTable.setOnAction(e ->{
					centerTabelLayout3.getChildren().remove(0);
					centerTabelLayout3.getChildren().add(0, centerTable3.theLayot(0, quentityVal3));
					pageCombo.setValue("1");
				});
				
				delete.setOnAction(e -> {
					TableView<outcomeItems> table = centerTable3.table; 
					int selectedIndex = table.getSelectionModel().getSelectedIndex();
					if(selectedIndex>=0){
						outcomeItems fm = table.getSelectionModel().getSelectedItem();	
						int selectedItemId = fm.getId();						
						Button actionButton = new Button(rb.getString("delete"));
						actionButton.setOnAction(ev -> {
							outcome ouDB = new outcome();
							int rm = ouDB.removeMember(selectedItemId);
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
				
				centerLayout.getChildren().add(centerTabelLayout3);
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
				if(typex=="familyMembers"){
					FamilyMemberList centerTable_ = new FamilyMemberList();
					VBox centerTabelLayout_ = centerTable_.theLayot(page_,quentity_); 
					centerLayout_.getChildren().add(centerTabelLayout_);
					HBox.setMargin(centerTabelLayout_, new Insets(10,10,10,10));
					
					delete.setOnAction(e -> {
						TableView<FamilyMembers> table = centerTable_.table;	
						int selectedIndex = table.getSelectionModel().getSelectedIndex();
						if(selectedIndex >= 0){
							FamilyMembers fm = table.getSelectionModel().getSelectedItem();
							int selectedItemId = fm.getId();							
							Button actionButton = new Button(rb.getString("delete"));
							actionButton.setOnAction(ev -> {
								familyMembers fmDB = new familyMembers();
								int rm = fmDB.removeMember(selectedItemId);
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
				}else if(typex=="incomes"){
					incomeList centerTable2_ = new incomeList();
					VBox centerTabelLayout2_ = centerTable2_.theLayot(page_,quentity_);
					centerLayout_.getChildren().add(centerTabelLayout2_);
					HBox.setMargin(centerTabelLayout2_, new Insets(10,10,10,10));
					
					delete.setOnAction(e -> {
						TableView<incomeItems> table = centerTable2_.table;
						int selectedIndex = table.getSelectionModel().getSelectedIndex();
						if(selectedIndex >= 0){
							incomeItems fm = table.getSelectionModel().getSelectedItem();
							int selectedItemId = fm.getId();							
							Button actionButton = new Button(rb.getString("delete"));
							actionButton.setOnAction(ev -> {
								income inDB = new income();
								int rm = inDB.removeMember(selectedItemId);
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
					
				}else if(typex=="outcomes"){
					outcomeList centerTable3_ = new outcomeList();
					VBox centerTabelLayout3_ = centerTable3_.theLayot(page_,quentity_);
					centerLayout_.getChildren().add(centerTabelLayout3_);
					HBox.setMargin(centerTabelLayout3_, new Insets(10,10,10,10));
					
					delete.setOnAction(e -> {
						TableView<outcomeItems> table = centerTable3_.table; 
							
						int selectedIndex = table.getSelectionModel().getSelectedIndex();
						if(selectedIndex >= 0){
							outcomeItems fm = table.getSelectionModel().getSelectedItem();
							int selectedItemId = fm.getId();							
							Button actionButton = new Button(rb.getString("delete"));
							actionButton.setOnAction(ev -> {
								outcome ouDB = new outcome();
								int rm = ouDB.removeMember(selectedItemId);
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
				}
				root.getChildren().add(centerLayout_);
			}catch(Exception ex){
				System.out.println(ex);
			}
		});
		
		topLayout.getChildren().addAll(page, pageCombo, add, reloadTable, delete);
		
		
		root.getChildren().addAll(topLayout, centerLayout);
		Scene scene = new Scene(root);
		
		scene.getStylesheets().add("Viper.css");
		URL url = loadTable.class.getResource("/image/favicon.png");
		window.getIcons().add(new Image(url.toString()));
		
		window.setScene(scene);
		window.showAndWait();
	}
	
}
