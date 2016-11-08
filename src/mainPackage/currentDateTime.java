package mainPackage;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class currentDateTime {
	private ResourceBundle rb;
	private Date date;
	private DateFormat dayFormat;
	private DateFormat monthFormat;
	private DateFormat yearFormat;
	private HBox dateLayout;
	private Label dayLabel;
	private Label monthLabel;
	private Label yearLabel;
	private String[] monthNames;
	
	public currentDateTime(){
		rb = ResourceBundle.getBundle("lang", Locale.getDefault());
		date = new Date();
		dayFormat = new SimpleDateFormat("dd", Locale.US);
		
		monthFormat = new SimpleDateFormat("MM", Locale.US);
		monthFormat.format(date);
		monthNames = new String[]{ 
				rb.getString("jenuary"), 
				rb.getString("february"), 
				rb.getString("march"), 
				rb.getString("april"), 
				rb.getString("may"), 
				rb.getString("june"), 
				rb.getString("july"), 
				rb.getString("august"), 
				rb.getString("september"), 
				rb.getString("octomber"), 
				rb.getString("november"), 
				rb.getString("december") 
		};
		
		yearFormat = new SimpleDateFormat("yyyy", Locale.US);
		
		dateLayout = new HBox();
		dayLabel = new Label();
		monthLabel = new Label();
		yearLabel = new Label();
		
	}
	
	@SuppressWarnings("deprecation")
	public HBox dateBox(){
		dayLabel.setText(dayFormat.format(date));
		monthLabel.setText(monthNames[date.getMonth()]);
		yearLabel.setText(yearFormat.format(date));
		
		dayLabel.setPadding(new Insets(20,20,20,20));
		dayLabel.setStyle("-fx-background-color: #0096c9; -fx-font-size: 22px; -fx-text-fill: white");
		
		monthLabel.setPadding(new Insets(18,5,20,5));
		monthLabel.setStyle("-fx-font-size: 22px; -fx-text-fill: #555555");
		
		yearLabel.setPadding(new Insets(18,5,20,5));
		yearLabel.setStyle("-fx-font-size: 22px; -fx-text-fill: #555555");
		
		dateLayout.getChildren().addAll(dayLabel, monthLabel, yearLabel);
		
		dateLayout.setPadding(new Insets(10,10,10,10));
		return dateLayout;
	}
	
	
}
