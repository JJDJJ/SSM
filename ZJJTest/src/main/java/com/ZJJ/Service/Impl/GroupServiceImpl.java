package com.ZJJ.Service.Impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ZJJ.Dao.IGroupDao;
import com.ZJJ.POJO.Group;
import com.ZJJ.Service.IGroupService;

@Service("groupService")
public class GroupServiceImpl implements IGroupService {

	@Resource
	private IGroupDao groupdao;

	@Override
	public List<Group> getGroupList() throws Exception {
		return this.groupdao.query();
	}

	@Override
	public Group getGroupById(String groupId) throws Exception {
		return groupdao.selectByPrimaryKey(groupId);
	}

}
