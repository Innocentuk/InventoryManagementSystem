package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;

import Model.DamageItem;
import Model.Stock;

public class DamageDAO implements GenericDao<DamageItem>{

	DatabaseManager db = new DatabaseManager();
	private Connection con;
	
	public DamageDAO() {
	}
	
	public boolean save(DamageItem d) throws Exception {
		String sql = "insert into damaged (item, qty, reason, date, staffStamp) values (?, ?, ?, ?, ?)";

				try {
					con = db.getConnect();
					 PreparedStatement statement = (PreparedStatement) con.prepareStatement(sql);
					 //statement.setInt(1, d.getId());
					 statement.setString(1, d.getItem());
					 statement.setInt(2, d.getQty());
					 statement.setString(3, d.getReason());
					 statement.setString(4, d.getDate());
					 statement.setString(5, d.getStaffstamp());
					 statement.execute();
					 
					 return true;
					 
				} catch (Exception e) {
				
					e.printStackTrace();
			
					return false;
				}
	}

	public DamageItem findByName(String name) throws Exception {
		try {
			
		} catch (Exception e) {
		  throw new Exception();
		}
		return null;
	}

	@Override
	public boolean update(DamageItem d) throws Exception {
		String sql = "update damaged  set item = ?, qty = ?, reason = ? , date = ? , staffStamp = ? where id = "+ d.getId()  ;
		try {
			con = db.getConnect();
	
			 PreparedStatement statement = (PreparedStatement) con.prepareStatement(sql);
			 statement.setString(1, d.getItem());
			 statement.setInt(2, d.getQty());
			 statement.setString(3, d.getReason());
			 statement.setString(4, d.getDate());
			 statement.setString(5, d.getStaffstamp());
			 statement.executeUpdate();
			 
			 return true;
			 
		} catch (Exception e) {
		
			e.printStackTrace();
	
			return false;
		}
	}

	@Override
	public boolean delete(DamageItem t) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<DamageItem> findAll() throws Exception {
		try {
			con = db.getConnect();
			String sql = "select * from damaged";
			PreparedStatement statement = (PreparedStatement) con.prepareStatement(sql);

			ResultSet result = statement.executeQuery();
			List<DamageItem> dl = new ArrayList<>();
			
			while(result.next())
			{
				DamageItem di = new DamageItem();
				di.setId(result.getInt("id"));
				di.setItem(result.getString("item"));
				di.setQty(result.getInt("qty"));
				di.setReason(result.getString("reason"));
				di.setDate(result.getString("date"));
				di.setStaffstamp(result.getString("staffStamp"));
				
				dl.add(di);
			}
			return dl;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void reLoadData(DamageItem t) throws Exception {
		findAll();
	}

}
