package mainPackage;

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

import javafx.geometry.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import combobox.currencyC;
import combobox.family;
import combobox.currencyC.MyCurrency;
import combobox.family.Member;
import combobox.productsC;
import combobox.productsC.Product;
import database.income;
import calendar.customDatePicker;

public class incomeAdd {
	private static ResourceBundle rb;

	
	public static void display(){
		//resource
		rb = ResourceBundle.getBundle("lang", Locale.getDefault());
		Stage window = new Stage();
		window.setTitle(rb.getString("incomeAdd"));
		window.initModality(Modality.APPLICATION_MODAL);
		window.setMinWidth(350);
		
		Label l = new Label();
		l.getStyleClass().add("addFamilyErrorMessageLabel");
		l.setFocusTraversable(true);
		l.setMaxWidth(330);
		
		DatePicker dp = customDatePicker.make(330, "dd/MM/YYYY", "textfield", rb.getString("dateFormat"), null);
		
		TextField money = new TextField();
		money.setPromptText(rb.getString("money"));
		money.getStyleClass().add("textfield");
		money.setMaxWidth(330);
		
		family family = new family();
		ComboBox<Member> familyCombo = family.select(330);
		familyCombo.getStyleClass().add("combobox");	
		
		currencyC currencyC = new currencyC();
		ComboBox<MyCurrency> currencyCombo = currencyC.select(330);
		
		combobox.productsC productsC = new productsC();
		ComboBox<Product> productsCombo = productsC.select(330);
		
		TextArea aditionalDesc = new TextArea();
		aditionalDesc.setPromptText(rb.getString("additionalDesc"));
		aditionalDesc.getStyleClass().add("textArea");
		aditionalDesc.setMaxWidth(330);
		aditionalDesc.setWrapText(true);
		
		Button a = new Button(rb.getString("add"));
		
		Button b = new Button(rb.getString("close"));
		b.setOnAction(e -> window.close());
		a.setOnAction(e ->{
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			long time = System.currentTimeMillis() / 1000L;
			Date date2;
			try {
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
				LocalDate datexxx = dp.getValue();
				
				date2 = dateFormat.parse(formatter.format(datexxx));
				time = date2.getTime() / 1000L;
			} catch (Exception e1) {
				System.out.println(e1.toString());
			}
			int newTime = (int) time;
			int fmid = family.familyMemberId;
			double monInt = 0;
			if(!money.getText().isEmpty()){
				monInt = Double.parseDouble(money.getText());
			}
			int curId = currencyC.currencyId;
			int proId = productsC.productId;
			String addText = null;
			if(!aditionalDesc.getText().isEmpty()){
				addText = aditionalDesc.getText();
			}
			
			if(newTime==0 || monInt==0 || fmid==0 || curId==0 || proId==0 || addText==null){
				l.setText(rb.getString("allfieldsrequired"));
			}else{
				int resultX = income.addIncomeToDB(newTime, monInt, fmid, curId, proId, addText);
				if(resultX==1){ 
					l.setText(rb.getString("done"));
					money.setText("");
					familyCombo.getSelectionModel().selectFirst();
					currencyCombo.getSelectionModel().selectFirst();
					productsCombo.getSelectionModel().selectFirst();
					aditionalDesc.setText("");					
				}else{
					l.setText(rb.getString("error"));
				}
			}
		});		
		
		HBox buttonLayouts = new HBox();
		buttonLayouts.getChildren().addAll(a, b);
		HBox.setMargin(a, new Insets(10,0,20,10));
		HBox.setMargin(b, new Insets(10,0,20,10));		
		
		VBox layout = new VBox();
		layout.getChildren().addAll(l, dp, money, currencyCombo, productsCombo, familyCombo, aditionalDesc, buttonLayouts);
		VBox.setMargin(dp, new Insets(10,10,5,10));
		VBox.setMargin(familyCombo, new Insets(5,10,5,10));
		VBox.setMargin(money, new Insets(5,10,10,10));
		VBox.setMargin(currencyCombo, new Insets(5,10,10,10));
		VBox.setMargin(productsCombo, new Insets(5,10,10,10));
		VBox.setMargin(aditionalDesc, new Insets(5,10,10,10));
		layout.setAlignment(Pos.CENTER);
		buttonLayouts.requestFocus();
		
		Scene scene = new Scene(layout);		
		scene.getStylesheets().add("Viper.css");
		
		URL url = incomeAdd.class.getResource("/image/favicon.png");
		window.getIcons().add(new Image(url.toString()));
		
		window.setScene(scene);
		window.showAndWait();
	}

}
