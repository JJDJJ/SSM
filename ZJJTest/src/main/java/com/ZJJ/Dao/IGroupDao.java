package com.ZJJ.Dao;

import java.util.List;

import com.ZJJ.POJO.Group;

public interface IGroupDao {

	int deleteByPrimaryKey(String groupId);

	int insert(Group record);

	int insertSelective(Group record);

	Group selectByPrimaryKey(String groupId);

	List<Group> query();
	
	int updateByPrimaryKeySelective(Group record);

	int updateByPrimaryKey(Group record);
	
	
}
