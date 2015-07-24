package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;

import Model.Item;
import Model.Price;
import Model.Stock;
import View.AddProduct;

public class ItemDAO implements GenericDao<Item> {

	DatabaseManager db = new DatabaseManager();
	private Connection con;
	ResultSet rs;
	
	
	public ItemDAO() {
	}
	
	@Override
	public boolean save(Item t) throws Exception 
	{
		String sql = "INSERT INTO item (Name, Description) VALUES (?, ?)";
		//String sql = "INSERT INTO item (Name, Description, costPrice, price) SELECT Price_id FROM price WHERE price=Price_id) VALUES (?, ?, ?, ?)";

		try {
			con = db.getConnect();
	
			 PreparedStatement statement = (PreparedStatement) con.prepareStatement(sql);
			 
			 statement.setString(1, t.getName());
			 statement.setString(2, t.getDescription());
			 statement.execute();
			 
			 return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean update(Item t) throws Exception {
		String sql = "update item  set Name = ?, Description = ? where Item_id = "+ t.getId()  ;
		try {
			con = db.getConnect();
	
			 PreparedStatement statement = (PreparedStatement) con.prepareStatement(sql);
			 statement.setString(1, t.getName());
			 statement.setString(2, t.getDescription());
			 statement.executeUpdate();
			 
			 return true;
			 
		} catch (Exception e) {
		
			e.printStackTrace();
	
			return false;
		}
	}

	@Override
	public boolean delete(Item t) throws Exception {
		String sql = "DELETE FROM item where id = ?";
		try{
			con = db.getConnect();
			PreparedStatement st = (PreparedStatement) con.prepareStatement(sql);
			st.setString(1, t.getName());
			st.setString(2, t.getDescription());
			
			st.executeUpdate();
			
			System.out.println("deleted");
			return true;
				
		}catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<Item> findAll() throws Exception {
		try {
			con = db.getConnect();
			String sql = "select * from Item";
			PreparedStatement statement = (PreparedStatement) con.prepareStatement(sql);

			ResultSet result = statement.executeQuery();
			List<Item> t = new ArrayList<>();
			
			while(result.next())
			{
				Item it = new Item();
				it.setId(result.getInt("Item_id"));
				it.setName(result.getString("Name"));
				it.setDescription(result.getString("Description"));
				
				t.add(it);
				
			}
			return t;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	public Item findByName(String name) throws Exception {

    	String sql = "select * from item where name =?";
    	try {
			con = db.getConnect();
			PreparedStatement statement = (PreparedStatement) con.prepareStatement(sql);
			statement.setString(1, name);
			
			ResultSet result = statement.executeQuery();
			while(result.next())
			{
				Item it = new Item();
				it.setId(result.getInt("Item_id"));
				it.setName(result.getString("Name"));
				it.setDescription(result.getString("Description"));
			}
				
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
    	public void fillCombo() throws Exception {

    		AddProduct add = new AddProduct();
        	String sql = "select * from item where name =?";
        	try {
    			con = db.getConnect();
    			PreparedStatement statement = (PreparedStatement) con.prepareStatement(sql);
    			ResultSet result = statement.executeQuery();
    			
    			while(result.next())
    			{
    				String item = result.getString("Name");
    				add.getItemList().addItem(item);
    			}
    			add.getItemList().setSelectedIndex(0);
    		} catch (Exception e) {
    			// TODO: handle exception
    			e.printStackTrace();
    		}
	}

	@Override
	public void reLoadData(Item t) throws Exception {
		findAll();
		
	}

}
