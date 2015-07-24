package Service;

import java.util.ArrayList;
import java.util.List;

import DAO.DamageDAO;
import Model.DamageItem;
import Model.Item;

public class DamageServiceImplementation implements DamageService{

	DamageDAO ddao = new DamageDAO();

	@Override
	public boolean saveItem(DamageItem d) throws Exception {
		try {
			ddao.save(d);
			return true;
		} catch (Exception e) {
			throw new Exception(e.getMessage() , e.getCause());
		}
	}


	@Override
	public boolean updateDamageList(DamageItem d) throws Exception {
		try {
			ddao.update(d);
			
			return true;
		} catch (Exception e) {
			throw new Exception(e.getMessage() , e.getCause());
		}
	}


	@Override
	public List<DamageItem> findAlldamaged() throws Exception {
		try {
			return ddao.findAll();
		} catch (Exception e) {
			throw new Exception(e.getMessage() , e.getCause());
		}
	}

}
