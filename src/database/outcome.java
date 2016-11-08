package database;

import database.connectDb;
import java.sql.*;

public class outcome {
	public ResultSet selectOutcome(){
		Connection db = connectDb.db();
		PreparedStatement prepare = null;
		String query = "SELECT outcome.id as id, outcome.date as date, (SELECT name FROM familyMembers WHERE familyMembers.id=outcome.user_id) as username, (SELECT surname FROM familyMembers WHERE familyMembers.id=outcome.user_id) as usersurname, outcome.money as money, (SELECT name FROM currency WHERE currency.id=outcome.currency) as currency, (SELECT products.productname2 FROM products WHERE products.id=outcome.desc AND products.productname IS NULL AND products.status!=1) as desc FROM outcome ORDER BY date DESC LIMIT 10";
		try{
			prepare = db.prepareStatement(query);
			ResultSet result = prepare.executeQuery();
			return result;
		}catch(Exception e){
			System.out.println(e);
			return null;
		}
	}

	public ResultSet selectIncomeById(int id){
		Connection db = connectDb.db();
		PreparedStatement prepare = null;
		String query = "SELECT outcome.date as date, outcome.money as money, outcome.user_id as user_id, (SELECT name FROM familyMembers WHERE familyMembers.id=outcome.user_id) as username, (SELECT surname FROM familyMembers WHERE familyMembers.id=outcome.user_id) as usersurname, outcome.currency as currencyId, (SELECT name FROM currency WHERE currency.id=outcome.currency) as currency, outcome.desc as descId, (SELECT products.productname2 FROM products WHERE products.id=outcome.desc AND products.productname IS NULL AND products.status!=1) as desc, outcome.additional as addText FROM outcome WHERE id="+id;
		try{
			prepare = db.prepareStatement(query);
			ResultSet result = prepare.executeQuery();
			return result;
		}catch(Exception e){
			System.out.println(e);
			return null;
		}
	}
	
	public ResultSet selectOutcomeFromTo(int from, int limit){
		Connection db = connectDb.db();
		PreparedStatement prepare = null;
		String query = "SELECT outcome.id as id, outcome.date as date, (SELECT name FROM familyMembers WHERE familyMembers.id=outcome.user_id) as username, (SELECT surname FROM familyMembers WHERE familyMembers.id=outcome.user_id) as usersurname, outcome.money as money, (SELECT name FROM currency WHERE currency.id=outcome.currency) as currency, (SELECT products.productname2 FROM products WHERE products.id=outcome.desc AND products.productname IS NULL AND products.status!=1) as desc FROM outcome ORDER BY date DESC LIMIT "+from+","+limit;
		try{
			prepare = db.prepareStatement(query);
			ResultSet result = prepare.executeQuery();
			return result;
		}catch(Exception e){
			System.out.println(e);
			return null;
		}
	}
	
	public int countMembers(){
		int number;
		Connection db = connectDb.db();
		PreparedStatement prepare = null;
		String query = "SELECT COUNT(id) FROM outcome";
		try{
			prepare = db.prepareStatement(query);
			ResultSet result = prepare.executeQuery();
			number = result.getInt(1);
		}catch(Exception e){
			System.out.println(e);
			number = 0;
		}
		return number;
	}
	
	public static int addOutcomeToDB(int time, double money, int familyId, int currencyId, int productId, String description){
		int error = 0;
		Connection db = connectDb.db();
		PreparedStatement prepare = null;
		String query = "INSERT INTO outcome (date, user_id, money, currency, desc, additional) VALUES (?,?,?,?,?,?)";
		try{
			prepare = db.prepareStatement(query);
			prepare.setInt(1, time);
			prepare.setInt(2, familyId);
			prepare.setDouble(3, money);
			prepare.setInt(4, currencyId);
			prepare.setInt(5, productId);
			prepare.setString(6, description);
			prepare.executeUpdate();
			error = 1;
		}catch(Exception e2){
			System.out.println(e2);
		}	
		return error;
	}
	
	public int removeMember(int i){
		Connection db = connectDb.db();
		PreparedStatement prepare = null;
		String query = "DELETE FROM outcome WHERE id="+i;
		try{
			prepare = db.prepareStatement(query);
			prepare.executeUpdate();
			return 1;
		}catch(Exception e){
			System.out.println(e);
			return 0;
		}
		
	}

	public static int editOutcomeToDB(int dbIncomeId, int newTime, double monInt, int fmid, int curId, int proId,
			String addText) {
		int error = 0;
		Connection db = connectDb.db();
		PreparedStatement prepare = null;
		String query = "UPDATE outcome SET date=?, user_id=?, money=?, currency=?, desc=?, additional=? WHERE id=?";
		try{
			prepare = db.prepareStatement(query);
			prepare.setInt(1, newTime);
			prepare.setInt(2, fmid);
			prepare.setDouble(3, monInt);
			prepare.setInt(4, curId);
			prepare.setInt(5, proId);
			prepare.setString(6, addText);
			prepare.setInt(7, dbIncomeId);
			prepare.executeUpdate();
			error = 1;
		}catch(Exception e2){
			System.out.println(e2);
		}	
		return error;
	}
}
