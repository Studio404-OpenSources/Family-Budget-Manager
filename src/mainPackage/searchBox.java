package mainPackage;

import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import calendar.customDatePicker;
import combobox.incomeOutcome;
import combobox.incomeOutcome.MyClassx;
import database.margeIncomeOutcome;

public class searchBox {
	private VBox vb;
	private ResourceBundle rb;
	private Label label;
	DatePicker cdP;
	DatePicker cdP2;
	ComboBox<MyClassx> combo;
	private Button btn;
	private incomeOutcome incomeOutcome;
	
	public searchBox(){
		vb = new VBox();
		rb = ResourceBundle.getBundle("lang", Locale.getDefault());
		label = new Label();
		cdP = customDatePicker.make(500, "dd/MM/YYYY", "serachBoxDatepicker", "", null); 
		cdP2 = customDatePicker.make(500, "dd/MM/YYYY", "serachBoxDatepicker", "", null); 
		incomeOutcome = new incomeOutcome();
		combo = incomeOutcome.select(500);
		btn = new Button();
	}
	
	public VBox getSerchBox(){
		label.setText(rb.getString("typeDate"));
		label.setPadding(new Insets(10,0,5,0));
		
		btn.setText(rb.getString("searchBtn"));
		
		btn.setOnAction(e -> {
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			long time = System.currentTimeMillis() / 1000L;
			long time2 = System.currentTimeMillis() / 1000L;
			Date date;
			Date date2;
			try {
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
				LocalDate datexxx = cdP.getValue();
				LocalDate datexxx2 = cdP2.getValue();
				
				date = dateFormat.parse(formatter.format(datexxx));
				date2 = dateFormat.parse(formatter.format(datexxx2));
				time = date.getTime() / 1000L;
				time2 = date2.getTime() / 1000L;
			} catch (Exception e1) {
				System.out.println(e1.toString());
			}
			
			//System.out.println("From: "+time+"; TO: "+time2+" ComboBoxId: "+incomeOutcome.INOU); 
			if(time>time2){
				alertBox.display(rb.getString("message"), rb.getString("searchByDateError"));
			}else{
				margeIncomeOutcome margeIncomeOutcome = new margeIncomeOutcome();
				if(incomeOutcome.INOU==1){
					int size = 0;
					ResultSet rs = margeIncomeOutcome.selectIncome(time, time2, 0, 20);
					try {
						while(rs.next()){
							size++;
						}
					} catch (Exception e1) {
						alertBox.display(rb.getString("message"), rb.getString("error"));
					}
					
					if(size<=0){
						alertBox.display(rb.getString("message"), rb.getString("noResult"));
					}else{
						loadTableSearchBox.display("income list", "incomes", time, time2);
					}
				}else{
					int size = 0;
					ResultSet rs = margeIncomeOutcome.selectIncome2(time, time2, 0, 20);
					try {
						while(rs.next()){
							size++;
						}
					} catch (Exception e1) {
						alertBox.display(rb.getString("message"), rb.getString("error"));
					}
					
					if(size<=0){
						alertBox.display(rb.getString("message"), rb.getString("noResult"));
					}else{
						loadTableSearchBox.display("income list", "outcomes", time, time2);
					}
				}
			}
		});
		
		vb.getChildren().addAll(label, cdP, cdP2, combo, btn);
		VBox.setMargin(cdP, new Insets(0,0,5,0));
		VBox.setMargin(cdP2, new Insets(0,0,5,0));
		VBox.setMargin(combo, new Insets(0,0,5,0));
		
		vb.setPadding(new Insets(0,10,0,10));
		vb.setMinWidth(500);
		return vb;
	}
}
