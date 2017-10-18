package com.ZJJ.Service;

import java.util.List;

import com.ZJJ.POJO.Group;

public interface IGroupService {

	public List<Group> getGroupList() throws Exception;

	public Group getGroupById(String groupId) throws Exception;
}
