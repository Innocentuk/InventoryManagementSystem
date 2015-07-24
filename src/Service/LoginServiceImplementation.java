package Service;

import java.util.List;

import DAO.LoginDAO;
import Model.Login;

public class LoginServiceImplementation implements LoginService{

	LoginDAO ldao = new LoginDAO();
	@Override
	public boolean saveUser(Login l) throws Exception {
		try {
			ldao.save(l);
			return true;
		} catch (Exception e) {
			throw new Exception(e.getMessage() , e.getCause());
		}
	}

	@Override
	public boolean updateUser(Login l) throws Exception {
		ldao.update(l);
		return true;
	}

	@Override
	public List<Login> findAllUser() throws Exception {
		
		return ldao.findAll();
	}

	@Override
	public Login findItemByName(String name) throws Exception {
		return ldao.findByName(name);
	}

}
