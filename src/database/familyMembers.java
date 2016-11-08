package database;

import database.connectDb;
import java.sql.*;

public class familyMembers {
	private Connection db;
	
	public ResultSet selectMembers(){
		db = connectDb.db();
		PreparedStatement prepare = null;
		String query = "SELECT * FROM familymembers WHERE status!=1 ORDER BY id ASC LIMIT 10";
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
		PreparedStatement prepare = null;
		int number;
		db = connectDb.db();
		String query = "SELECT COUNT(id) FROM familymembers WHERE status!=1 ORDER BY id ASC";
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
	
	public ResultSet selectMembersFromTo(int from, int limimt){
		PreparedStatement prepare = null;
		db = connectDb.db();
		String query = "SELECT * FROM familymembers WHERE status!=1 ORDER BY id ASC LIMIT "+from+","+limimt;
		try{
			prepare = db.prepareStatement(query);
			ResultSet result = prepare.executeQuery();
			return result;
		}catch(Exception e){
			System.out.println(e);
			return null;
		}
	}
	
	public ResultSet selectMembersIncome(int currency_id){
		PreparedStatement prepare = null;
		db = connectDb.db();
		String query = "SELECT familymembers.name as name, familymembers.surname as surname, (SELECT SUM(income.money) FROM income WHERE familymembers.id=income.user_id AND income.currency="+currency_id+") AS moneySum  FROM familymembers WHERE familymembers.status!=1";
		try{
			prepare = db.prepareStatement(query);
			ResultSet result = prepare.executeQuery();
			return result;
		}catch(Exception e){
			System.out.println(e);
			return null;
		}
	}
	
	public ResultSet selectMembersOutcome(int currency_id){
		PreparedStatement prepare = null;
		db = connectDb.db();
		String query = "SELECT familymembers.name as name, familymembers.surname as surname, (SELECT SUM(outcome.money) FROM outcome WHERE familymembers.id=outcome.user_id AND outcome.currency="+currency_id+") AS moneySum  FROM familymembers WHERE familymembers.status!=1";
		try{
			prepare = db.prepareStatement(query);
			ResultSet result = prepare.executeQuery();
			return result;
		}catch(Exception e){
			System.out.println(e);
			return null;
		}
	}
	
	public int addMember(String n, String s, String c){
		int out = 0;
		db = connectDb.db();
		String query2 = "INSERT INTO familymembers (name, surname, contactnumber, status) VALUES (?,?,?,?)";
		try{
			PreparedStatement prepare2 = db.prepareStatement(query2);
			prepare2.setString(1, n);
			prepare2.setString(2, s);
			prepare2.setString(3, c);
			prepare2.setInt(4, 0);
			out = prepare2.executeUpdate();
			System.out.println(out);
		}catch(Exception e2){
			System.out.println(e2);
		}		
					
		return out;
	}
	
	 
	
	public int removeMember(int i){
		PreparedStatement prepare = null;
		db = connectDb.db();
		String query = "UPDATE familymembers SET status=1 WHERE id="+i;
		try{
			prepare = db.prepareStatement(query);
			prepare.executeUpdate();
			return 1;
		}catch(Exception e){
			System.out.println(e);
			return 0;
		}
		
	}

	public int editMember(int dbFamilyId, String n, String s, String c) {
		int out = 0;
		db = connectDb.db();
		String query2 = "UPDATE familymembers SET name=?, surname=?, contactnumber=? WHERE id=?";
		try{
			PreparedStatement prepare2 = db.prepareStatement(query2);
			prepare2.setString(1, n);
			prepare2.setString(2, s);
			prepare2.setString(3, c);
			prepare2.setInt(4, dbFamilyId);
			out = prepare2.executeUpdate();
			System.out.println(out);
		}catch(Exception e2){
			System.out.println(e2);
		}
		return out;
	}
}
