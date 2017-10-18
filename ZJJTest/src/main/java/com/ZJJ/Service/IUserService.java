package com.ZJJ.Service;

import java.util.List;

import com.ZJJ.POJO.Condition;
import com.ZJJ.POJO.User;

public interface IUserService {

	public User getUserById(String user_id) throws Exception;
	
	public List<User> getUserList() throws Exception;
	
	public List<User> getUserListByGroupId(String group_id)throws Exception;
	
	public List<User>getUserListByCondition(Condition conditions)throws Exception;
	
	public int insertUser(User user)throws Exception;
	
	public int updateUserById(User user)throws Exception;
	
	public int deleteUser(String userId)throws Exception;
	
	
}
