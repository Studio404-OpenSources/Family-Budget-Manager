package charts;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.input.MouseEvent;
import mainPackage.alertBox;
import database.*;

public class bar {    
	
	@SuppressWarnings("unchecked")
	public BarChart<String,Number> createChart(String currencyName, int currencyId) {
		//resource
		ResourceBundle rb = ResourceBundle.getBundle("lang", Locale.getDefault());
		
		final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        final BarChart<String,Number> bc = new BarChart<String,Number>(xAxis,yAxis);
        bc.setTitle(rb.getString("incomeVSOutcome"));
 
        @SuppressWarnings("rawtypes")
		XYChart.Series series1 = new XYChart.Series();
        series1.setName("შემოსავალი ( "+currencyName+" )"); 
        
        familyMembers fm = new familyMembers();
        ResultSet rs = fm.selectMembersIncome(currencyId);
        ResultSet rs2 = fm.selectMembersOutcome(currencyId);

        try {
			while (rs.next()) {
				String namelname = rs.getString(1) + " " +rs.getString(2);
				if(rs.getString(3)!=null){
					series1.getData().add(new XYChart.Data<String, Double>(namelname, Double.parseDouble(rs.getString(3))));
				}else{
					series1.getData().add(new XYChart.Data<String, Double>(namelname, 0.0));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
        
        @SuppressWarnings("rawtypes")
		XYChart.Series series4 = new XYChart.Series();
        series4.setName("გასავალი ( "+currencyName+" )"); 

        try {
			while (rs2.next()) {
				String namelname = rs2.getString(1) + " " +rs2.getString(2);
				if(rs2.getString(3)!=null){
					series4.getData().add(new XYChart.Data<String, Double>(namelname, Double.parseDouble(rs2.getString(3))));
				}else{
					series4.getData().add(new XYChart.Data<String, Double>(namelname, 0.0));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
        
        bc.getStyleClass().add("barCartSeries");
        bc.setVerticalGridLinesVisible(true);
        bc.setHorizontalGridLinesVisible(true);
        
        bc.getData().addAll(series1, series4);
        
        //now you can get the nodes.
        for (Series<String, Number> serie: bc.getData()){
        	for (XYChart.Data<String, Number> item: serie.getData()){
                item.getNode().setOnMousePressed((MouseEvent event) -> {
                	String message = item.getXValue() + " " + item.getYValue().toString();
                	
					alertBox.display(rb.getString("message"), message);
                });
            }
        }
        
        return bc;
    }
}
