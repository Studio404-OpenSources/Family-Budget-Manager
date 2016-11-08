package database;

import database.connectDb;
import java.sql.*;

public class products {
	public ResultSet select(){
		Connection db = connectDb.db();
		PreparedStatement prepare = null;
		String query = "SELECT id, productname FROM products WHERE productname2 IS NULL ORDER BY productname ASC";
		try{
			prepare = db.prepareStatement(query);
			ResultSet result = prepare.executeQuery();
			return result;
		}catch(Exception e){
			System.out.println(e);
			return null;
		}
	}
	
	public ResultSet selectById(int typex, int id){
		Connection db = connectDb.db();
		PreparedStatement prepare = null;
		String query = null;
		if(typex==1){
			query = "SELECT productname FROM products WHERE productname2 IS NULL AND id="+id;
		}else{
			query = "SELECT productname2 FROM products WHERE productname IS NULL AND id="+id;
		}
		try{
			prepare = db.prepareStatement(query);
			ResultSet result = prepare.executeQuery();
			return result;
		}catch(Exception e){
			System.out.println(e);
			return null;
		}
	}
	
	public ResultSet select2(){
		Connection db = connectDb.db();
		PreparedStatement prepare = null;
		String query = "SELECT id, productname2 FROM products WHERE productname IS NULL ORDER BY productname2 ASC";
		try{
			prepare = db.prepareStatement(query);
			ResultSet result = prepare.executeQuery();
			return result;
		}catch(Exception e){
			System.out.println(e);
			return null;
		}
	}

	public ResultSet selectMembersFromTo() {
		Connection db = connectDb.db();
		PreparedStatement prepare = null;
		String query = "SELECT id, productname FROM products WHERE productname2 IS NULL ORDER BY productname ASC";
		try{
			prepare = db.prepareStatement(query);
			ResultSet result = prepare.executeQuery();
			return result;
		}catch(Exception e){
			System.out.println(e);
			return null;
		}
	}
	
	public int addProduct(String ProductName){
		Connection db = connectDb.db();
		PreparedStatement prepare = null;
		String query = "INSERT INTO products (productname) VALUES (?)";
		try{
			prepare = db.prepareStatement(query);
			prepare.setString(1, ProductName);
			prepare.executeUpdate();
			return 1;
		}catch(Exception e){
			System.out.println(e);
			return 0;
		}
	}
	
	public int removeMember(int id){
		Connection db = connectDb.db();
		PreparedStatement prepare = null;
		String query = "DELETE FROM products WHERE id=?";
		try{
			prepare = db.prepareStatement(query);
			prepare.setInt(1, id);
			prepare.executeUpdate();
			return 1;
		}catch(Exception e){
			System.out.println(e);
			return 0;
		}
	}

	public ResultSet selectMembersFromTo2() {
		Connection db = connectDb.db();
		PreparedStatement prepare = null;
		String query = "SELECT id, productname2 FROM products WHERE productname IS NULL ORDER BY productname2 ASC";
		try{
			prepare = db.prepareStatement(query);
			ResultSet result = prepare.executeQuery();
			return result;
		}catch(Exception e){
			System.out.println(e);
			return null;
		}
	}

	public int addProduct2(String ProductName) {
		Connection db = connectDb.db();
		PreparedStatement prepare = null;
		String query = "INSERT INTO products (productname2) VALUES (?)";
		try{
			prepare = db.prepareStatement(query);
			prepare.setString(1, ProductName);
			prepare.executeUpdate();
			return 1;
		}catch(Exception e){
			System.out.println(e);
			return 0;
		}
	}
	
	public int updateProduct(int typex, String ProductName, int id) {
		Connection db = connectDb.db();
		PreparedStatement prepare = null;
		String query = null;
		if(typex==1){
			query = "UPDATE products SET productname=? WHERE id=?";
		}else{
			query = "UPDATE products SET productname2=? WHERE id=?";
		}
		try{
			prepare = db.prepareStatement(query);
			prepare.setString(1, ProductName);
			prepare.setInt(2, id);
			prepare.executeUpdate();
			return 1;
		}catch(Exception e){
			System.out.println(e);
			return 0;
		}
	}
	
	
}
