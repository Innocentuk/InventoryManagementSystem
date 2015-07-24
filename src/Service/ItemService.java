package Service;

import java.util.List;

import Model.Item;

public interface ItemService 
{
	boolean saveItem(Item t) throws Exception;
	boolean updateItem(Item t)  throws Exception;
	Item findItemByName(String name) throws Exception;
	List<Item>  findAllItem() throws Exception;

}
