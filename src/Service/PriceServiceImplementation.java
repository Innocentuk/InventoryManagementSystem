package Service;

import java.util.ArrayList;
import java.util.List;

import DAO.PriceDAO;
import Model.Price;

public class PriceServiceImplementation implements PriceService{

	PriceDAO pdao = new PriceDAO();
	protected ArrayList<Price> sessionFactory;

	public Boolean savePrice(Price p) throws Exception 
	{
		try {
			pdao.save(p);
			return true;
		} catch (Exception e) {
			throw new Exception(e.getMessage() , e.getCause());
		}
	}

	@Override
	public boolean updatePrice(Price p) throws Exception {
		try {
			pdao.update(p);
			return true;
		} catch (Exception e) {
			throw new Exception(e.getMessage() , e.getCause());
		}
	}

	@Override
	public List<Price> findAllPrice() throws Exception {
		try {
			return pdao.findAll();
		} catch (Exception e) {
			throw new Exception(e.getMessage() , e.getCause());
		}
	}

	@Override
	public Price findPriceByItem(String item) throws Exception {
		try{
			return pdao.findByName(item);
		}catch(Exception e){
			e.printStackTrace();
			throw new Exception(e.getMessage(), e.getCause());
		}
		
	}
}
