package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;

import Model.Item;
import Model.Login;

public class LoginDAO implements GenericDao<Login>{

	DatabaseManager db = new DatabaseManager();
	private Connection con;
	ResultSet rs;
	@Override
	public boolean save(Login l) throws Exception {
		String sql = "INSERT INTO login (userName, password, role) VALUES (?, ?, ?)";
		try {
			con = db.getConnect();
	
			 PreparedStatement statement = (PreparedStatement) con.prepareStatement(sql);
			 
			 statement.setString(1, l.getUsername());
			 statement.setString(2, l.getPassword());
			 statement.setString(3, l.getRole());
			 statement.execute();
			 
			 return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean update(Login l) throws Exception {
		String sql = "update login  set userName = ?, password = ?, role = ? where Item_id = "+ l.getId()  ;
		try {
			con = db.getConnect();
	
			 PreparedStatement statement = (PreparedStatement) con.prepareStatement(sql);
			 statement.setString(1, l.getUsername());
			 statement.setString(2, l.getPassword());
			 statement.setString(2, l.getRole());
			 statement.executeUpdate();
			 
			 return true;
			 
		} catch (Exception e) {
		
			e.printStackTrace();
	
			return false;
		}
	}

	@Override
	public void reLoadData(Login t) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean delete(Login l) throws Exception {
		String sql = "DELETE FROM login where id = ?";
		try{
			con = db.getConnect();
			PreparedStatement st = (PreparedStatement) con.prepareStatement(sql);
			st.setString(1,l.getUsername());
			st.setString(2, l.getPassword());
			st.setString(2, l.getRole());
			
			st.executeUpdate();
			
			System.out.println("deleted");
			return true;
				
		}catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Login findByName(String name) throws Exception {
		String sql = "select * from login where userName =?";
    	try {
			con = db.getConnect();
			PreparedStatement statement = (PreparedStatement) con.prepareStatement(sql);
			statement.setString(1, name);
			
			ResultSet result = statement.executeQuery();
			while(result.next())
			{
				Login log = new Login();
				log.setId(result.getInt("log_id"));
				log.setUsername(result.getString("userName"));
				log.setPassword(result.getString("password"));
				log.setRole(result.getString("role"));
			}
				
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Login> findAll() throws Exception {
		try {
			con = db.getConnect();
			String sql = "select * from login";
			PreparedStatement statement = (PreparedStatement) con.prepareStatement(sql);

			ResultSet result = statement.executeQuery();
			List<Login> l = new ArrayList<>();
			
			while(result.next())
			{
				Login log = new Login();
				log.setId(result.getInt("log_id"));
				log.setUsername(result.getString("userName"));
				log.setPassword(result.getString("password"));
				log.setRole(result.getString("role"));
				
				l.add(log);
			}
			return l;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}

}
