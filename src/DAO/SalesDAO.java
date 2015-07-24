package DAO;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;

import Model.Item;
import Model.Price;
import Model.Sales;
import Model.Stock;

public class SalesDAO implements GenericDao<Sales>{

	DatabaseManager db = new DatabaseManager();
	private Connection con;
	
	public SalesDAO() {}
	
	@Override
	public boolean save(Sales t) throws Exception {
		String sql = "INSERT INTO sale (item, totalPrice, qty, status, staffStamp, date) VALUES (?, ?, ?, ?, ?, ?)";

				try {
					con = db.getConnect();
					
					 PreparedStatement statement = (PreparedStatement) con.prepareStatement(sql);
					 //statement.setInt(1, t.getId());
					 statement.setString(1, t.getItem());
					 statement.setDouble(2, t.getTotalprice());
					 statement.setInt(3, t.getQuantity());
					 statement.setString(4, t.getStatus());
					 statement.setString(5, t.getStaffStamp());
					 statement.setString(6, t.getDate());
					 
					 //statement.setDouble(4, p.getSellPrice());
					 statement.execute();
					 
					 return true;
					 
				} catch (Exception e) {
				
					e.printStackTrace();
			
					return false;
				}
	}

	@Override
	public boolean update(Sales s) throws Exception {
		String sql = "update sale  set item = ?, totalPrice = ? , qty = ? , status = ? , staffStamp = ? , date = ? where id = "+ s.getId()  ;
		try{
			con = db.getConnect();
			
			 PreparedStatement statement = (PreparedStatement) con.prepareStatement(sql);
			 statement.setString(1, s.getItem());
			 statement.setDouble(2, s.getTotalprice());
			 statement.setInt(3, s.getQuantity());
			 statement.setString(4, s.getDate());
			 statement.setString(5, s.getStatus());
			 statement.setString(6, s.getStaffStamp());
			 statement.executeUpdate();
			 
			 return true;
		}catch (Exception e){
			e.printStackTrace();
			return false;
		}
		
	}

	@Override
	public boolean delete(Sales t) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Sales> findAll() throws Exception {
		try {
			con = db.getConnect();
			String sql = "select * from sale";
			PreparedStatement statement = (PreparedStatement) con.prepareStatement(sql);

			ResultSet result = statement.executeQuery();
			List<Sales> s = new ArrayList<Sales>();
			
			while(result.next())
			{
				Sales sa = new Sales();
				sa.setId(result.getInt("sale_id"));
				sa.setItem(result.getString("item"));
				sa.setDate(result.getString("date"));
				sa.setQuantity(result.getInt("qty"));
				sa.setTotalprice(result.getDouble("totalPrice"));
				sa.setStatus(result.getString("status"));
				sa.setStaffStamp(result.getString("staffStamp"));
				
				s.add(sa);
			}
			return s;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	
	public Sales findByName(String name) throws Exception {
			String sql = "select * from sale  where item =?";
	    	try {
				con = db.getConnect();
				PreparedStatement statement = (PreparedStatement) con.prepareStatement(sql);
				statement.setString(1, name);
				
				ResultSet result = statement.executeQuery();
				Sales sa = null; 
				while(result.next())
				{
					sa = new Sales();
					sa.setId(result.getInt("sale_id"));
					sa.setItem(result.getString("item"));
					sa.setDate(result.getString("date"));
					sa.setQuantity(result.getInt("qty"));
					sa.setTotalprice(result.getDouble("totalPrice"));
					sa.setStatus(result.getString("status"));
					sa.setStaffStamp(result.getString("staffStamp"));
				}
				return sa;
		} catch (Exception e) {
		  throw new Exception();
		}
	    	//return null;
	}

	@Override
	public void reLoadData(Sales t) throws Exception {
		findAll();
		
	}

}
