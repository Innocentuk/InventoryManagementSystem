package Service;

import java.util.List;

import Model.DamageItem;

public interface DamageService {

	boolean saveItem(DamageItem d) throws Exception;
	boolean updateDamageList(DamageItem d)  throws Exception;
	List<DamageItem> findAlldamaged() throws Exception;
}
