package database;

import database.connectDb;
import java.sql.*;

public class currency {
	public ResultSet selectCur(){
		Connection db = connectDb.db();
		PreparedStatement prepare = null;
		String query = "SELECT currency.id as id, currency.name as name FROM currency ORDER BY currency.id ASC";
		try{
			prepare = db.prepareStatement(query);
			ResultSet result = prepare.executeQuery();
			return result;
		}catch(Exception e){
			System.out.println(e);
			return null;
		}
	}
}
