package mainPackage;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.*;
import javafx.application.*;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import charts.bar;
import charts.pie;

public class bootstrap extends Application {
	private ResourceBundle rb;
	private BorderPane root;
	private navigation nav;
	private MenuBar menu;
	private VBox layout;
	private VBox rightSideLayout;
	private VBox centerSideLayout;
	private HBox centerTopLayout;
	private Scene scene;
	private currentDateTime theDateBox;

	public static void main(String[] args){
		launch(args);		
	}

	@Override
	public void start(Stage window) throws Exception {
		//resource
		rb = ResourceBundle.getBundle("lang", Locale.getDefault());
		window.setTitle(rb.getString("projectName"));	
		root = new BorderPane();		
		
		/* Right layout START */
		nav = new navigation();
		menu = nav.myNavigation();
		layout = new VBox();
		layout.getChildren().addAll(menu);		
		FamilyTable Familytable = new FamilyTable();	
		incomeTable incomeTable = new incomeTable();
		outcomeTable outcomeTable = new outcomeTable();		
		rightSideLayout = new VBox();
		rightSideLayout.getChildren().addAll(
				Familytable.theLayot(), 
				incomeTable.theLayot(),
				outcomeTable.theLayot()
		);
		rightSideLayout.setMaxWidth(380);
		rightSideLayout.setPadding(new Insets(0,10,10,10));
		/* Right layout END */
		
		/* Center Layout START */
		centerSideLayout = new VBox();
		centerTopLayout = new HBox();	
		theDateBox = new currentDateTime();
		centerTopLayout.getChildren().add(theDateBox.dateBox());

		HBox chartLayout = new HBox();
		pie pie = new pie();
		PieChart theChart = pie.makeChart(rb.getString("mainCurrency"), 1);

		currencyCombobox curCom = new currencyCombobox();
		ComboBox<String> combobox = curCom.select(350);
		
		bar bar = new bar();
		BarChart<String,Number> barChart = bar.createChart(rb.getString("mainCurrency"), 1);
		VBox vlayout = new VBox();
		VBox.setMargin(combobox, new Insets(15,10,0,10));
		vlayout.getStyleClass().add("chartComboBoxLayout");
		vlayout.setAlignment(Pos.CENTER);
		vlayout.getChildren().addAll(combobox, barChart);
		
		combobox.getSelectionModel().selectedItemProperty().addListener( (v, oldValue, newValue) -> {
			try{
				vlayout.getChildren().remove(1);
				int currencyIndex = combobox.getSelectionModel().getSelectedIndex() + 1; 
				BarChart<String,Number> barChart2 = bar.createChart(newValue, currencyIndex);
				vlayout.getChildren().add(barChart2);
			}catch(Exception exCombo){
				System.out.println(exCombo);
			}
		});
		
		ComboBox<String> combobox2 = curCom.select(350);
		VBox vlayout2 = new VBox();
		VBox.setMargin(combobox2, new Insets(15,10,0,10));
		vlayout2.getStyleClass().add("chartComboBoxLayout");
		vlayout2.setAlignment(Pos.CENTER);
		vlayout2.getChildren().addAll(combobox2, theChart);
		
		combobox2.getSelectionModel().selectedItemProperty().addListener( (v, oldValue, newValue) -> {
			try{
				vlayout2.getChildren().remove(1);
				int currencyIndex = combobox2.getSelectionModel().getSelectedIndex() + 1; 
				PieChart theChart2 = pie.makeChart(newValue, currencyIndex);
				vlayout2.getChildren().add(theChart2);
			}catch(Exception ex){
				
			}
		});
		
		chartLayout.getChildren().addAll(vlayout2, vlayout);
		chartLayout.setSpacing(10);
		chartLayout.setPadding(new Insets(50,0,0,10));
		theChart.getStyleClass().add("piChartLayout");
		barChart.getStyleClass().add("barCartLayout");
		
		
		searchBox searchBox = new searchBox();
		centerTopLayout.getChildren().add(searchBox.getSerchBox());
		centerTopLayout.setPadding(new Insets(20,0,0,0));
		centerTopLayout.setAlignment(Pos.CENTER);
		centerSideLayout.getChildren().add(centerTopLayout);
		centerSideLayout.getChildren().add(chartLayout);
		/* Center Layout END */
		
		/* CopyRight */
		Label Copy = new Label(rb.getString("Copyright"));
		Copy.setPadding(new Insets(10,10,10,10));
		Copy.getStyleClass().add("copyright");
		Copy.setOnMouseClicked(new EventHandler<Event>() {
			@Override
			public void handle(Event event) {
				try {
					Desktop.getDesktop().browse(new URI(rb.getString("CopyrightUrl")));
				} catch (IOException | URISyntaxException e) {
					e.printStackTrace();
				}
			}			
		});
		HBox copyrightLayout = new HBox();
		copyrightLayout.getChildren().add(Copy);
		copyrightLayout.getStyleClass().add("copyrightLabel");		
		// add layouts to main root layout
		root.setTop(layout); 
		root.setRight(rightSideLayout);
		root.setCenter(centerSideLayout);
		root.setBottom(copyrightLayout);
		
		
		//create scene
		scene = new Scene(root);
		scene.getStylesheets().add("Viper.css");	
		URL url = getClass().getResource("/image/favicon.png");
		window.getIcons().add(new Image(url.toString()));
		
		
		window.setMaximized(true);
		window.setResizable(true);
		// add scene to window and show
		window.setScene(scene);
		window.show();
	}
	
}
