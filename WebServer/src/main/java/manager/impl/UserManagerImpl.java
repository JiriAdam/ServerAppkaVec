package manager.impl;

import org.springframework.beans.factory.annotation.Autowired;

import manager.UserManager;
import mybatis.dao.AddressMapper;
import mybatis.dao.UserMapper;
import mybatis.model.Address;
import mybatis.model.User;



public class UserManagerImpl implements UserManager{

	@Autowired UserMapper userMapper;
	
	@Autowired AddressMapper addressMapper;
		
	
	public boolean addAddresssToUser(Long userID, Object address) {
		
		User user = userMapper.selectByPrimaryKey(userID);
		
		Address address2 = addressMapper.selectByPrimaryKey(user.getIdAddress());

		user.setIdAddress(address2.getId());

		userMapper.updateByPrimaryKey(user);		
		
		return false;
	}

	
	
}
