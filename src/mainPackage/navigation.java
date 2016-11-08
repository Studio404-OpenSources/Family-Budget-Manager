package mainPackage;

import java.util.Locale;
import java.util.ResourceBundle;

import javafx.scene.control.MenuItem;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Menu;

public class navigation {
	
	private ResourceBundle rb;
	private MenuBar mainMenu;
	private Menu[] menuElements;
	
	public MenuBar myNavigation(){
		//resource
		rb = ResourceBundle.getBundle("lang", Locale.getDefault());
		
		mainMenu = new MenuBar();
		menuElements = new Menu[]{
			new Menu(rb.getString("menuFamilyMembers")),
			new Menu(rb.getString("income")),
			new Menu(rb.getString("outcomes")),
			new Menu(rb.getString("options"))
		};
		
		// family members sub menu
		MenuItem addMember = new MenuItem(rb.getString("addMember"));
		MenuItem editMembers = new MenuItem(rb.getString("list"));
		menuElements[0].getItems().addAll(addMember, editMembers);
		
		//incomes sub menu
		MenuItem addIncome = new MenuItem(rb.getString("addIncome"));
		MenuItem editIncome = new MenuItem(rb.getString("list"));
		menuElements[1].getItems().addAll(addIncome, editIncome);
		
		//outcomes sub menu
		MenuItem addOutcome = new MenuItem(rb.getString("addOutcome"));
		MenuItem editOutcome = new MenuItem(rb.getString("list"));
		menuElements[2].getItems().addAll(addOutcome, editOutcome);
		
		
		// options
		MenuItem incomeList = new MenuItem(rb.getString("incomeList"));
		MenuItem outcomeList = new MenuItem(rb.getString("outcomeList"));
		MenuItem exitx = new MenuItem(rb.getString("exits"));
		menuElements[3].getItems().addAll(incomeList, outcomeList, exitx);
		
		// actions
		addMember.setOnAction(e ->{
			FamilyAddMember.display();
		});
		editMembers.setOnAction(e ->{
			loadTable.display(rb.getString("familyMembers"), "familyMembers");
		});
		
		
		addIncome.setOnAction(e ->{
			incomeAdd.display();
		});
		editIncome.setOnAction(e ->{
			loadTable.display(rb.getString("incomes"), "incomes");
		});
		
		addOutcome.setOnAction(e ->{
			outcomeAdd.display();
		});
		editOutcome.setOnAction(e -> {
			loadTable.display(rb.getString("outcomes"), "outcomes");
		});
		
		incomeList.setOnAction(e->{
			productBox.display(rb.getString("incomeList"));			
		});
		
		outcomeList.setOnAction(e ->{
			productBox2.display(rb.getString("outcomeList"));	
		});
		
		exitx.setOnAction(e -> System.exit(0));
		
		for(int i = 0; i < menuElements.length; i++){
			mainMenu.getMenus().add(menuElements[i]);
		}
		return mainMenu;
	}
}
