package Presenter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import com.mysql.jdbc.PreparedStatement;

import DAO.DatabaseManager;
import DAO.ItemDAO;
import Model.Item;
import Service.ItemServiceImplementation;
import Service.StockServiceImplementation;
import View.AddProduct;
/**
 * Class that handles the communication from the gui and the database when adding a product to stock
 * @author Eucheria
 *
 */
public class GUIControlerAddProduct {
	AddProduct addPro;
	ItemServiceImplementation iserver = new ItemServiceImplementation();
	/**
	 *  Constructor which set up the layer of communication between user and
	 * database.
	 * @param addPro
	 * @throws Exception
	 */
	public GUIControlerAddProduct( AddProduct addPro) throws Exception {
		this.addPro = addPro;
		this.addPro.getAdd().addActionListener(new ListenToAddButton());
		getProductFromDatabase();
	}

	/**
	 * Method which calls the DAO item and get a list of item names
	 * which is added to the GUI.
	 * @throws Exception
	 */
	private void getProductFromDatabase() throws Exception {
		List<Item> listItem = iserver.findAllItem();
		//ItemDAO dao = new ItemDAO();
		//dao.fillCombo();
		for(Item i : listItem){
			addPro.getItemList().insertItemAt(i.getName(), 0);
			//addPro.getItemList()
		}
		addPro.getItemList().setSelectedIndex(0);
		
		/*DatabaseManager db = new DatabaseManager();
		Connection con;
		ResultSet rs;
		String sql = "select * from item ";
    	try {
			con = db.getConnect();
			PreparedStatement statement = (PreparedStatement) con.prepareStatement(sql);
			ResultSet result = statement.executeQuery();
			
			while(result.next())
			{
				String item = result.getString("Name");
				addPro.getItemList().addItem(item);
			}
			addPro.getItemList().setSelectedIndex(0);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}*/
	}
	
	/**
	 * Private class that contains the ActionListeners for adding product in to stock table
	 * GUI
	 * @author Eucheria innocent
	 *
	 */
	private class ListenToAddButton implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			String chosenProduct = (String) addPro.getItemList().getSelectedItem();
			String amount = addPro.getQtyTF().getText(); 
			String numSold = addPro.getNoSoldTF().getText();
			String numDamage = addPro.getNoDamageTF().getText();
			String date = addPro.getDateTF().getText();
			
			JOptionPane.showMessageDialog(null, "you have added "+ 
					""+chosenProduct+ " quantity added "+
					""+ amount+  "amount sold"+
					""+ numSold+ " amount damaged"+
					""+ numDamage+ " on"+
					""+ date + "", null, JOptionPane.PLAIN_MESSAGE);
		}
		
	}

}
