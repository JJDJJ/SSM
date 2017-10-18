package com.ZJJ.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ZJJ.POJO.Group;
import com.ZJJ.POJO.TreeNode;
import com.ZJJ.Service.IGroupService;


@Controller
@RequestMapping("/group")
public class GroupController {

	private static Logger logger = Logger.getLogger(GroupController.class);

	@Resource
	private IGroupService groupService;

	/**
	 * 获取部门名称
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping( value="/selectGroup/{treeGroupId}")
	public List<HashMap<String, String>> getGroupNameList(@PathVariable String treeGroupId) throws Exception {
		logger.info("getGroup...");
		logger.info("treeGroupId:" + treeGroupId);
		List<Group> groupNameLits = groupService.getGroupList();	
		List<HashMap<String, String>> respJsonList = new ArrayList<HashMap<String, String>>();
		if(treeGroupId.equals("-1")){   //没有选择部门，输出所有部门
			for (Integer i = 0; i < groupNameLits.size(); i++) {
				HashMap<String, String> respJsonMap = new HashMap<String, String>();
				respJsonMap.put("groupId",groupNameLits.get(i).getGroupId());
				respJsonMap.put("groupName",groupNameLits.get(i).getGroupName());
				respJsonList.add(respJsonMap);
			}
			
		}else{
			logger.info("树选择的部门编号：" +treeGroupId );
//			Group groupById = groupService.getGroupById(treeGroupId);
			for (Integer i = 0; i < groupNameLits.size(); i++) {
				HashMap<String, String> respJsonMap = new HashMap<String, String>();
				respJsonMap.put("groupId",groupNameLits.get(i).getGroupId());
				respJsonMap.put("groupName",groupNameLits.get(i).getGroupName());
				if(groupNameLits.get(i).getGroupId().equals(treeGroupId)){
					respJsonMap.put("selected","true");
				}
				respJsonList.add(respJsonMap);
			}
//			HashMap<String, String> respJsonMap = new HashMap<String, String>();
//			respJsonMap.put("groupId",groupById.getGroupId());
//			respJsonMap.put("groupName",groupById.getGroupName());
//			respJsonMap.put("selected","true");
//			respJsonList.add(respJsonMap);
		}
		return respJsonList;
	}
	
	/**
	 * 初始化部门树
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/initTree")
	 public List<TreeNode> getTree()throws Exception{
		logger.info("initTree...");
		List<Group> groupList = groupService.getGroupList();
		List<TreeNode> childrens = new ArrayList<TreeNode>();
		List<TreeNode> respTreeNodes = new ArrayList<TreeNode>();
		for(int i = 0;i<groupList.size();i++){
			TreeNode childern = new TreeNode();
			childern.setId(Integer.valueOf(groupList.get(i).getGroupId()));
			childern.setText(groupList.get(i).getGroupName());
			childrens.add(childern);
		}
		 TreeNode treeNode = new TreeNode();	
		 treeNode.setId(100);
		 treeNode.setText("部门");
		 treeNode.setChildren(childrens);
	
		 respTreeNodes.add(treeNode);
		 return respTreeNodes;
	 }
	
}
