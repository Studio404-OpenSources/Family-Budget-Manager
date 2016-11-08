package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import database.connectDb;

public class incomeOutcome {
	public ResultSet selectIncome(int currencyId){
		Connection db = connectDb.db();
		PreparedStatement prepare = null;
		String query = "SELECT SUM(income.money) as totalincome, (SELECT SUM(outcome.money) FROM outcome WHERE currency="+currencyId+") as totaloutcome FROM income WHERE currency="+currencyId;
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
