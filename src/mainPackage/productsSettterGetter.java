package mainPackage;

public class productsSettterGetter {
	private int id;
	private String name;
	
	public productsSettterGetter(){
		this.id = 0;
		this.name = "";
	}
	
	public productsSettterGetter(int id, String name){
		this.id = id;
		this.name = name;
	}
	
	public int getId(){
		return id;
	}
	
	public void setId(int id){
		this.id = id;
	}
	
	public String getName(){
		return name;
	}
	
	public void setName(String name){
		this.name = name;
	}
}
