package Service;

import java.util.List;

import Model.Price;

public interface PriceService {

	Boolean savePrice(Price P) throws Exception;
	boolean updatePrice(Price s)  throws Exception;
	Price findPriceByItem(String item) throws Exception;
	List<Price>  findAllPrice() throws Exception;
}
