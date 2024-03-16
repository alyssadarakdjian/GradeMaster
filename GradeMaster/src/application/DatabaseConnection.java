package application;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
	public Connection databaseLink;
	
	public Connection getConnection() {
		// was "loginbase"
		String url = "grademaster-mysql.mysql.database.azure.com";
		// was "root"
		String databaseUser = "GradeMaster";
		// was "gradedatabase"
		String databasePassword = "Justice_League";
		// was "jdbc:mysql://localhost/" + databaseName
		//String url = "jdbc:mysql://localhost/" + databaseName;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			databaseLink = DriverManager.getConnection(url, databaseUser, databasePassword);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return databaseLink;
	}
}
