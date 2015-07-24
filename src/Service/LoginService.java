package Service;

import java.util.List;

import Model.Login;


public interface LoginService {

	boolean saveUser(Login l) throws Exception;
	boolean updateUser(Login l)  throws Exception;
	public List<Login >  findAllUser() throws Exception;
	Login findItemByName(String name) throws Exception;
}
