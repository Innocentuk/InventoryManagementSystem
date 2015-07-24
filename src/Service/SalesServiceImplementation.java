package Service;

import java.util.ArrayList;
import java.util.List;

import DAO.SalesDAO;
import Model.Item;
import Model.Sales;

public class SalesServiceImplementation implements SaleService{

	SalesDAO dao = new SalesDAO();
	
	@Override
	public boolean saveSales(Sales s) throws Exception {
		// TODO Auto-generated method stub
		try {
			
			dao.save(s);
			return true;
		} catch (Exception e) {
			throw new Exception(e.getMessage() , e.getCause());
		}
	}

	@Override
	public boolean updateSale(Sales s) throws Exception {
		try {
			dao.update(s);
			
			return true;
		} catch (Exception e) {
			throw new Exception(e.getMessage() , e.getCause());
		}
	}

	@Override
	public List<Sales> findAllSale() throws Exception {
		try {
			return dao.findAll();
		} catch (Exception e) {
			throw new Exception(e.getMessage() , e.getCause());
		}
	}

	@Override
	public Sales findItemByName(String name) throws Exception {
		try {
			return dao.findByName(name);
		} catch (Exception e) {
			throw new Exception(e.getMessage() , e.getCause());
		}
	}
}
