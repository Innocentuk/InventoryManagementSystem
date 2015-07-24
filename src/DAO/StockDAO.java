package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;

import Model.Item;
import Model.Price;
import Model.Sales;
import Model.Stock;

public class StockDAO implements GenericDao<Stock>{

	DatabaseManager db = new DatabaseManager();
	private Connection con;
	int currentQty;
	
	public boolean save(Stock s) throws Exception {
		String sql = "INSERT INTO inventory (soldno, damagedno, qty, date,item) VALUES (?,?,?,?,?) " ;
				//SELECT Item_id, Price_id FROM item, price WHERE item = Item_id && price = Price_id) VALUES (?, ?, ?, ?)";
		       // + " values (?, ?, ?, ?, ?)";

				try {
					con = db.getConnect();
			
					 PreparedStatement statement = (PreparedStatement) con.prepareStatement(sql);
					 //statement.setInt(1, s.getId());
					 statement.setInt(1, s.getSoldNo());
					 statement.setInt(2, s.getDamagedNo());
					 statement.setInt(3, s.getQuantity());
					 statement.setString(4, s.getDate());
					 statement.setString(5, s.getItem());
					 statement.execute();
					 
					 return true;
					 
				} catch (Exception e) {
				
					e.printStackTrace();
			
					return false;
				}
	}


	@Override
	public boolean update(Stock s) throws Exception 
	{
		String sql = "update inventory  set soldno = ?, damagedno = ? , qty = ? , date = ? , item = ? where id = "+ s.getId()  ;
		try {
			con = db.getConnect();
	
			 PreparedStatement statement = (PreparedStatement) con.prepareStatement(sql);
			 //statement.setInt(1, s.getId());
			 statement.setInt(1, s.getSoldNo());
			 statement.setInt(2, s.getDamagedNo());
			 statement.setInt(3, s.getQuantity());
			 statement.setString(4, s.getDate());
			 statement.setString(5, s.getItem());
			 statement.executeUpdate();
			 
			 return true;
			 
		} catch (Exception e) {
		
			e.printStackTrace();
	
			return false;
		}
	}

	@Override
	public boolean delete(Stock s) throws Exception {
		String sql = "DELETE FROM inventory where id = ?";
		try{
			con = db.getConnect();
			PreparedStatement st = (PreparedStatement) con.prepareStatement(sql);
			st.setInt(1, s.getId());
			st.setInt(1, s.getSoldNo());
			st.setInt(2, s.getDamagedNo());
			st.setInt(3, s.getQuantity());
			st.setString(4, s.getDate());
			st.setString(5, s.getItem());
			st.executeUpdate();
			
			System.out.println("deleted");
			return true;
				
		}catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}


	@Override
	public List<Stock> findAll() throws Exception {
		String sql = "select * from inventory";
		
		try {
			con = db.getConnect();
			
			PreparedStatement statement = (PreparedStatement) con.prepareStatement(sql);

			//statement.setString(1, item);
			ResultSet result = statement.executeQuery();
			List<Stock> s = new ArrayList<Stock>();
			
			while(result.next())
			{
				Stock ss = new Stock();
				ss.setId(result.getInt("id"));
				ss.setItem(result.getString("item"));
				ss.setDate(result.getString("date"));
				ss.setQuantity(result.getInt("qty"));
				ss.setDamagedNo(result.getInt("damagedno"));
				ss.setSoldNo(result.getInt("soldno"));
				
				s.add(ss);
				
			}
			return s;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
		
	}
	
	 /**
     * Reduces the stock of the item.
     *
     * Reduces the stock by a given amount, unless there is insufficient stock
     * to do so.
     *
     * @param howMany  the amount of stock to remove
     * @return         true if reduction was successful, false if not possible
     */
	public boolean reduceStock(int howMany){
		if(currentQty >= howMany){
			currentQty -= howMany;
			return true;	 
	}else{
			return false;
			}
	}
	
    
    public Stock findByName(String name) throws Exception {

    	String sql = "select * from inventory where item =?";
    	try {
			con = db.getConnect();
			
			PreparedStatement statement = (PreparedStatement) con.prepareStatement(sql);

			statement.setString(1, name);
			
			ResultSet result = statement.executeQuery();
			Stock ss = null;
			while(result.next())
			{
				ss = new Stock();
				ss.setId(result.getInt("id"));
				ss.setItem(result.getString("item"));
				ss.setDate(result.getString("date"));
				ss.setQuantity(result.getInt("qty"));
				ss.setDamagedNo(result.getInt("damagedno"));
				ss.setSoldNo(result.getInt("soldno"));
			
			}
				
			return ss;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void reLoadData(Stock t) throws Exception {
		findAll();
		
	}
}
