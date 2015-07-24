package Service;

import java.util.List;

import Model.Sales;
import Model.Stock;

public interface SaleService {

	boolean saveSales(Sales s) throws Exception;
	boolean updateSale(Sales s)  throws Exception;
	List<Sales >  findAllSale() throws Exception;
	Sales findItemByName(String name) throws Exception;
}
