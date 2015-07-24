package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager

{
	
	 private Connection connect = null;
	public DatabaseManager()
	{

		 try {
			Class.forName("com.mysql.jdbc.Driver");
			connect = DriverManager
			          .getConnection("jdbc:mysql://localhost/inventorysystem","root","12345");

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public Connection getConnect() {
		return connect;
	}
	public void setConnect(Connection connect) {
		this.connect = connect;
	}

}
