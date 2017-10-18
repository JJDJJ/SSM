package com.ZJJ.POJO;

/**
 * 部门表
 * 
 * @author JJDJJ
 *
 */
public class Group {

	private String groupId; // 部门编号
	private String groupName; // 部门名称
	private String groupBmpy; // 部门名称首字母
	private String groupParentId; // 上级部门编码
	private Short groupLevel; // 部门级别，0
	private String groupComment; // 部门职责说明
	private Integer groupIndex; // 排序字段
	private String groupShow; // 部门简称
	private String groupBm; // 部门编码
	private String groupDh; // 部门电话

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId == null ? null : groupId.trim();
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName == null ? null : groupName.trim();
	}

	public String getGroupBmpy() {
		return groupBmpy;
	}

	public void setGroupBmpy(String groupBmpy) {
		this.groupBmpy = groupBmpy == null ? null : groupBmpy.trim();
	}

	public String getGroupParentId() {
		return groupParentId;
	}

	public void setGroupParentId(String groupParentId) {
		this.groupParentId = groupParentId == null ? null : groupParentId.trim();
	}

	public Short getGroupLevel() {
		return groupLevel;
	}

	public void setGroupLevel(Short groupLevel) {
		this.groupLevel = groupLevel;
	}

	public String getGroupComment() {
		return groupComment;
	}

	public void setGroupComment(String groupComment) {
		this.groupComment = groupComment == null ? null : groupComment.trim();
	}

	public Integer getGroupIndex() {
		return groupIndex;
	}

	public void setGroupIndex(Integer groupIndex) {
		this.groupIndex = groupIndex;
	}

	public String getGroupShow() {
		return groupShow;
	}

	public void setGroupShow(String groupShow) {
		this.groupShow = groupShow == null ? null : groupShow.trim();
	}

	public String getGroupBm() {
		return groupBm;
	}

	public void setGroupBm(String groupBm) {
		this.groupBm = groupBm == null ? null : groupBm.trim();
	}

	public String getGroupDh() {
		return groupDh;
	}

	public void setGroupDh(String groupDh) {
		this.groupDh = groupDh == null ? null : groupDh.trim();
	}

}
