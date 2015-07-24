package DAO;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;

import Model.Price;
import Model.Stock;


public class PriceDAO implements GenericDao<Price>{

	DatabaseManager db = new DatabaseManager();
	private Connection con;
	
	private HashMap<Integer, Price> priceData = new HashMap<Integer, Price>();
	
	public PriceDAO() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public boolean save(Price p) throws Exception {
		String sql = "INSERT INTO price (item, sellPrice, costPrice, date) VALUES (?, ?, ?, ?)"; 
		         
				try {
					con = db.getConnect();
					
					 PreparedStatement statement = (PreparedStatement) con.prepareStatement(sql);
					 
					 statement.setString(1, p.getItem());
					 statement.setDouble(2, p.getSellPrice());
					 statement.setDouble(3, p.getCostPrice());
					 statement.setString(4,  p.getDate());
					 statement.execute();
					 return true;
					 
				} catch (Exception e) {
				
					e.printStackTrace();
			
					return false;
				}
	}
	
	///// added hashmap, arraylist, loaddata, reloaddata, findPrice, methods
	 public HashMap<Integer, Price> getPriceData() {
	        return priceData;
	    }
	 ////
	@Override
	public boolean update(Price t) throws Exception {
		String sql = "update price  set item = ?, sellPrice = ?, costPrice = ?, date = ? where Price_id = "+ t.getId();
		try {
			con = db.getConnect();
	
			 PreparedStatement statement = (PreparedStatement) con.prepareStatement(sql);
			 statement.setString(1, t.getItem());
			 statement.setDouble(2, t.getSellPrice());
			 statement.setDouble(3, t.getCostPrice());
			 statement.setString(4, t.getDate());
			 statement.executeUpdate();
			 
			 return true;
			 
		} catch (Exception e) {
		
			e.printStackTrace();
	
			return false;
		}
	}
	@Override
	public boolean delete(Price t) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public List<Price> findAll() throws Exception {
		try {
			con = db.getConnect();
			String sql = "select * from price";
			PreparedStatement statement = (PreparedStatement) con.prepareStatement(sql);

			ResultSet result = statement.executeQuery();
			List<Price> pl = new ArrayList<>();
			
			while(result.next())
			{
				 Price p = new Price();
				p.setId(result.getInt("Price_id"));
				p.setItem(result.getString("item"));
				p.setSellPrice(result.getDouble("sellPrice"));
				p.setCostPrice(result.getDouble("costPrice"));
				p.setDate(result.getString("date"));
				pl.add(p);
				
			}
			return pl;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	
	public Price findByName(String name) throws Exception {

    	String sql = "select * from price where item =?";
    	try {
			con = db.getConnect();
			
			PreparedStatement statement = (PreparedStatement) con.prepareStatement(sql);

			statement.setString(1, name);
			
			ResultSet result = statement.executeQuery();
				
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public void reLoadData(Price t) throws Exception {
		findAll();
		
	}
	 
	 /*@Override
		public ArrayList<Price> loadAll() throws Exception {
		 ArrayList<Price> price = new ArrayList<Price>();
		 price.addAll(priceData.values());
		 return price;
		}
	 ///
	 public Price findPriceByID(int id) throws Exception {
	        for (Price p: loadAll()) {
	            if (p.getId() == id) {
	                return p;
	            }
	        }
			return null;
	 }
	@Override
	public void loadData(Price p) throws Exception {
		try{
			ResultSet prs = con.createStatement().executeQuery("select * from price");
            while(prs.next()){
            	//p.setId(prs.getInt(1));
            	p.setSellPrice(prs.getDouble(1));
            	p.setDate(prs.getString(2));
            	
            	priceData.put(p.getId(), p);
            }
            prs.close();
		}catch (SQLException e){
			e.printStackTrace();
		}
	}*/
	
}
