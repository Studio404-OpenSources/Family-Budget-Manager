package charts;

import java.util.Locale;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Side;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;
import java.sql.ResultSet;
import java.sql.SQLException;
import database.incomeOutcome;

public class pie {
	public PieChart makeChart(String currencyName, int currencyId){
		//resource
		ResourceBundle rb = ResourceBundle.getBundle("lang", Locale.getDefault());
		incomeOutcome io = new incomeOutcome();
		ResultSet rs = io.selectIncome(currencyId);
		int incomePer = 0;
		int outcomePer = 0;
		try {
			incomePer = rs.getInt(1);
			outcomePer = rs.getInt(2);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		Data income = new PieChart.Data("შემოსავალი", incomePer);
		Data outcome = new PieChart.Data("გასავალი", outcomePer);
		
		income.nameProperty().bind(Bindings.concat(
				"შემოსავალი ", income.pieValueProperty(), " ", currencyName
        ));
		
		outcome.nameProperty().bind(Bindings.concat(
				"გასავალი ", outcome.pieValueProperty(), " ", currencyName
        ));
		ObservableList<PieChart.Data> details = FXCollections.observableArrayList();
		details.addAll(income,outcome);
		PieChart pieChart = new PieChart();
		pieChart.setData(details);
		pieChart.setTitle(rb.getString("incomeVSOutcome"));
		pieChart.setLegendSide(Side.BOTTOM);
		pieChart.setLabelsVisible(true);
		
		return pieChart;
	}
}
