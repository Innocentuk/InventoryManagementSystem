package Service;

import java.util.ArrayList;
import java.util.List;

import DAO.StockDAO;
import Model.Item;
import Model.Stock;

public class StockServiceImplementation implements StockService{

	StockDAO sdao = new StockDAO();
	
	@Override
	public boolean saveStock(Stock s) throws Exception {
		
		try {
			sdao.save(s);
			
			return true;
		} catch (Exception e) {
			throw new Exception(e.getMessage() , e.getCause());
		}
	}

	@Override
	public boolean updateStock(Stock s) throws Exception {
		// TODO Auto-generated method stub
		try {
			sdao.update(s);
			
			return true;
		} catch (Exception e) {
			throw new Exception(e.getMessage() , e.getCause());
		}
	}

	@Override
	public List<Stock> findAllStock() throws Exception {
		try {
			return sdao.findAll();
		} catch (Exception e) {
			throw new Exception(e.getMessage() , e.getCause());
		}
	}

	@Override
	public Stock findItemByName(String name) throws Exception {
		try {
			return sdao.findByName(name);
		} catch (Exception e) {
			throw new Exception(e.getMessage() , e.getCause());
		}
	}

}
