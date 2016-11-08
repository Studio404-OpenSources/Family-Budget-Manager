package database;

import database.connectDb;
import java.sql.*;

public class margeIncomeOutcome {
	public ResultSet selectIncome(long fromTime, long toTime, int from, int to){
		Connection db = connectDb.db();
		PreparedStatement prepare = null;
		String query = "SELECT income.id as id, income.date as date, (SELECT name FROM familyMembers WHERE familyMembers.id=income.user_id) as username, (SELECT surname FROM familyMembers WHERE familyMembers.id=income.user_id) as usersurname, income.money as money, (SELECT name FROM currency WHERE currency.id=income.currency) as currency, (SELECT products.productname FROM products WHERE products.id=income.desc AND products.productname2 IS NULL AND products.status!=1) as desc FROM income WHERE date>="+fromTime+" AND date<=" + toTime + " ORDER BY date DESC LIMIT " + from + "," + to;
		try{
			prepare = db.prepareStatement(query);
			ResultSet result = prepare.executeQuery();
			return result;
		}catch(Exception e){
			System.out.println(e);
			return null;
		}
	}
	
	public ResultSet selectIncome2(long fromTime, long toTime, int from, int to){
		Connection db = connectDb.db();
		PreparedStatement prepare = null;
		String query = "SELECT outcome.id as id, outcome.date as date, (SELECT name FROM familyMembers WHERE familyMembers.id=outcome.user_id) as username, (SELECT surname FROM familyMembers WHERE familyMembers.id=outcome.user_id) as usersurname, outcome.money as money, (SELECT name FROM currency WHERE currency.id=outcome.currency) as currency, (SELECT products.productname2 FROM products WHERE products.id=outcome.desc AND products.productname IS NULL AND products.status!=1) as desc FROM outcome WHERE date>="+fromTime+" AND date<=" + toTime + " ORDER BY date DESC LIMIT " + from + "," + to;
		try{
			prepare = db.prepareStatement(query);
			ResultSet result = prepare.executeQuery();
			return result;
		}catch(Exception e){
			System.out.println(e);
			return null;
		}
	}
	
	public int countMembers(long fromTime, long toTime){
		int number;
		Connection db = connectDb.db();
		PreparedStatement prepare = null;
		String query = "SELECT COUNT(id) FROM income WHERE date>="+fromTime+" AND date<=" + toTime;
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
	
	public int countMembers2(long fromTime, long toTime){
		int number;
		Connection db = connectDb.db();
		PreparedStatement prepare = null;
		String query = "SELECT COUNT(id) FROM outcome WHERE date>="+fromTime+" AND date<=" + toTime;
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
}
