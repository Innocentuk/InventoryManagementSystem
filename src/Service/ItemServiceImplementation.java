package Service;

import java.util.ArrayList;
import java.util.List;

import DAO.ItemDAO;
import Model.Item;

public class ItemServiceImplementation implements ItemService 
{
	ItemDAO idao = new ItemDAO();
	@Override
	public boolean saveItem(Item t) throws Exception 
	{
		try {
			idao.save(t);
			return true;
		} catch (Exception e) {
			throw new Exception(e.getMessage() , e.getCause());
		}
	}

	@Override
	public boolean updateItem(Item t) throws Exception {
		try {
			idao.update(t);
			
			return true;
		} catch (Exception e) {
			throw new Exception(e.getMessage() , e.getCause());
		}
	}

	@Override
	public List<Item> findAllItem() throws Exception {
		try {
			return idao.findAll();
		} catch (Exception e) {
			throw new Exception(e.getMessage() , e.getCause());
		}
	}

	@Override
	public Item findItemByName(String name) throws Exception {
		try {
			return idao.findByName(name);
		} catch (Exception e) {
			throw new Exception(e.getMessage() , e.getCause());
		}
	}
}
