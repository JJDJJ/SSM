package com.ZJJ.Service.Impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ZJJ.Dao.IUserDao;
import com.ZJJ.POJO.Condition;
import com.ZJJ.POJO.User;
import com.ZJJ.Service.IUserService;

@Service("userService")
public class UserServiceImpl implements IUserService {

	@Resource
	private IUserDao userdao;

	@Override
	public User getUserById(String user_id) throws Exception {
		return this.userdao.selectByPrimaryKey(user_id);
	}

	@Override
	public List<User> getUserList() throws Exception {
		return this.userdao.query();
	}

	@Override
	public List<User> getUserListByGroupId(String group_id) throws Exception {
		return this.userdao.queryById(group_id);
	}

	@Override
	public int insertUser(User user) throws Exception {
		return this.userdao.insert(user);
	}

	@Override
	public int updateUserById(User user) throws Exception {
		return this.userdao.updateByPrimaryKey(user);
	}

	@Override
	public int deleteUser(String user_id) throws Exception {
		return this.userdao.deleteByPrimaryKey(user_id);
	}

	@Override
	public List<User> getUserListByCondition(Condition conditions) throws Exception {
		return this.userdao.queryByCondition(conditions);
	}

}
