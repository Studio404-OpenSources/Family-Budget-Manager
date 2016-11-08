package calendar;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import javafx.scene.control.DatePicker;
import javafx.util.StringConverter;

public class customDatePicker {
	public static DatePicker make(int maxWidth, String formatString, String styleClass, String promtText, Date setDatex){
		
		DatePicker dp = new DatePicker();
		StringConverter<LocalDate> converter = new StringConverter<LocalDate>() {
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(formatString);
            @Override
            public String toString(LocalDate date) {
                if (date != null) {
                    return dateFormatter.format(date);
                } else {
                    return "";
                }
            }
            @Override
            public LocalDate fromString(String string) {
                if (string != null && !string.isEmpty()) {
                    return LocalDate.parse(string, dateFormatter);
                } else {
                    return null;
                }
            }
        }; 
		dp.setConverter(converter);
		dp.getStyleClass().add(styleClass);
		dp.setMaxWidth(maxWidth);
		dp.setEditable(false);
		dp.setPromptText(promtText);
		
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		if(setDatex==null){
			Date date = new Date();
			dp.setValue(LOCAL_DATE(dateFormat.format(date)));
		}else{
			dp.setValue(LOCAL_DATE(dateFormat.format(setDatex)));
		}
		
		return dp;
	}
	
	public static final LocalDate LOCAL_DATE (String dateString){
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	    LocalDate localDate = LocalDate.parse(dateString, formatter);
	    return localDate;
	}
}
