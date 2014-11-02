package manager;

import mybatis.model.Address;
import mybatis.model.User;

public interface UserManager {

	boolean addAddresssToUser(User user, Address address);
	
	
}
