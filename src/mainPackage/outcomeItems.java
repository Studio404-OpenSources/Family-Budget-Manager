package mainPackage;

public class outcomeItems {
	private int id;
	private String datex;
	private String productName;
	private String name;
	private String amount;
	
	public outcomeItems(){
		this.id = 0;
		this.datex = "";
		this.name = "";
		this.productName = "";
		this.amount = "";
	}
	
	public outcomeItems(int i, String d, String p, String n, String s){
		this.id = i;
		this.datex = d;
		this.name = n;
		this.productName = p;
		this.amount = s;
	}
	
	public int getId(){
		return id;
	}
	
	public void setId(int i){
		this.id = i;
	}
	
	public String getDatex(){
		return datex;
	}
	
	public void setDatex(String d){
		this.datex = d;
	}
	
	public String getProductname(){
		return productName;
	}
	
	public void setProductname(String p){
		this.productName = p;
	}
	
	public String getName(){
		return name;
	}
	
	public void setName(String n){
		this.name = n;
	}
	
	public String getAmount(){
		return amount;
	}
	
	public void setAmount(String a){
		this.amount = a;
	}
}
