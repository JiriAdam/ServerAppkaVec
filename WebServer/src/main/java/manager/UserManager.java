package manager;

import org.springframework.stereotype.Service;

@Service
public interface UserManager {

	boolean addAddresssToUser(Long userID, Object address);
	
	
}
