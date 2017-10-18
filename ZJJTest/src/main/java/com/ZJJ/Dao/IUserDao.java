package com.ZJJ.Dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ZJJ.POJO.Condition;
import com.ZJJ.POJO.User;

public interface IUserDao {

	int deleteByPrimaryKey(String userId);

	int insert(User record);

	int insertSelective(User record);

	User selectByPrimaryKey(String userId);

	List<User> query();
	
	List<User> queryById(@Param("groupId") String Id);
	
	List<User>queryByCondition(Condition conditions);
	
	int updateByPrimaryKeySelective(User record);

	int updateByPrimaryKey(User record);
}
