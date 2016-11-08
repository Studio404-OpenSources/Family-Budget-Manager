package mainPackage;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

import calendar.customDatePicker;
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

public class editIncome {
	private static ResourceBundle rb;
	
	public static void display(int dbIncomeId){
		income income = new income();
		ResultSet rs = income.selectIncomeById(dbIncomeId);
		
		//resource
		rb = ResourceBundle.getBundle("lang", Locale.getDefault());
		Stage window = new Stage();
		window.setTitle(rb.getString("incomeEdit"));
		window.initModality(Modality.APPLICATION_MODAL);
		window.setMinWidth(350);
		
		Label l = new Label();
		l.getStyleClass().add("addFamilyErrorMessageLabel");
		l.setFocusTraversable(true);
		l.setMaxWidth(330);
		
		Date DBdate = null;
		try {
			DBdate=new Date((long)Integer.parseInt(rs.getString(1))*1000);	
		} catch (NumberFormatException | SQLException e2) {}	
		
		DatePicker dp = customDatePicker.make(330, "dd/MM/YYYY", "textfield", rb.getString("dateFormat"), DBdate);
		
		TextField money = new TextField();
		money.setPromptText(rb.getString("money"));
		money.getStyleClass().add("textfield");
		money.setMaxWidth(330);
		try {
			money.setText(rs.getString(5));
		} catch (SQLException e2) {
			
		}
		
		family family = new family();
		ComboBox<Member> familyCombo;
		int fID = 0;
		String fNAME = "";
		try {
			fID = rs.getInt(2);
			fNAME = rs.getString(3) + " " + rs.getString(4);
		} catch (SQLException e2) {	}
		familyCombo = family.selectByIndex(330, new Member(fID, fNAME));
		familyCombo.getStyleClass().add("combobox");	
		
		
		int cID = 0;
		String cNAME = "";
		try {
			cID = rs.getInt(6);
			cNAME = rs.getString(7);
		} catch (SQLException e2) {	}
		currencyC currencyC = new currencyC();
		ComboBox<MyCurrency> currencyCombo = currencyC.selectByIndex(330, new MyCurrency(cID, cNAME));
		
		
		int pID = 0;
		String pNAME = "";
		try {
			pID = rs.getInt(8);
			pNAME = rs.getString(9);
		} catch (SQLException e2) {	}
		combobox.productsC productsC = new productsC();
		ComboBox<Product> productsCombo = productsC.selectByIndex(330, new Product(pID, pNAME));
		
		TextArea aditionalDesc = new TextArea(); 
		aditionalDesc.setPromptText(rb.getString("additionalDesc"));
		aditionalDesc.getStyleClass().add("textArea");
		aditionalDesc.setMaxWidth(330);
		aditionalDesc.setWrapText(true);
		try {
			aditionalDesc.setText(rs.getString(10));
		} catch (Exception e) {
			
		}
		
		Button a = new Button(rb.getString("edit"));
		
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
				int resultX = database.income.editIncomeToDB(dbIncomeId, newTime, monInt, fmid, curId, proId, addText);
				if(resultX==1){ 
					l.setText(rb.getString("done"));
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
		layout.getChildren().addAll(l, dp, money, familyCombo, currencyCombo, productsCombo, aditionalDesc, buttonLayouts);
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
		
		URL url = editIncome.class.getResource("/image/favicon.png");
		window.getIcons().add(new Image(url.toString()));
		
		window.setScene(scene);
		window.showAndWait();
	}
}
