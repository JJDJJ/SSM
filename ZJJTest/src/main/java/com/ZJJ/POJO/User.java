package com.ZJJ.POJO;

/**
 * 系统用户表
 * @author JJDJJ
 *
 */
public class User {

	private String userId; // 警员编号
	private String dptUserid; // 大平台用户ID
	private String groupId; // 部门ID
	private String userRealname; // 真实姓名
	private String userXmpy; // 姓名首字母
	private Character userSex; // 性别编号 0.男 1.女
	private String userPassword; // 用户口令
	private Character userFlag; // 1.正常启用 2.禁用 3.调离
	private String userDuty; // 用户职称
	private Integer userIndex; // 显示顺序
	private String userSfz; // 身份证
	private String userSj1; // 手机号1
	private String userSj2; // 手机号2
	private String userSj3; // 手机号3
	private String userLb; // 警员类别
	private String userMrdwsb; // 定位设备编号
	private String userModifyDate; // 用户修改时间
	private Character userRoleNeedchange; // 更新状态
	private String userPhoto; // 用户头像

	 public String getUserId() {
	        return userId;
	    }

	    public void setUserId(String userId) {
	        this.userId = userId == null ? null : userId.trim();
	    }

	    public String getDptUserid() {
	        return dptUserid;
	    }

	    public void setDptUserid(String dptUserid) {
	        this.dptUserid = dptUserid == null ? null : dptUserid.trim();
	    }

	    public String getGroupId() {
	        return groupId;
	    }

	    public void setGroupId(String groupId) {
	        this.groupId = groupId == null ? null : groupId.trim();
	    }

	    public String getUserRealname() {
	        return userRealname;
	    }

	    public void setUserRealname(String userRealname) {
	        this.userRealname = userRealname == null ? null : userRealname.trim();
	    }

	    public String getUserXmpy() {
	        return userXmpy;
	    }

	    public void setUserXmpy(String userXmpy) {
	        this.userXmpy = userXmpy == null ? null : userXmpy.trim();
	    }

	    public Character getUserSex() {
	        return userSex;
	    }

	    public void setUserSex(Character userSex) {
	        this.userSex = userSex == null ? null : userSex;
	    }

	    public String getUserPassword() {
	        return userPassword;
	    }

	    public void setUserPassword(String userPassword) {
	        this.userPassword = userPassword == null ? null : userPassword.trim();
	    }

	    public Character getUserFlag() {
	        return userFlag;
	    }

	    public void setUserFlag(Character userFlag) {
	        this.userFlag = userFlag == null ? null : userFlag;
	    }

	    public String getUserDuty() {
	        return userDuty;
	    }

	    public void setUserDuty(String userDuty) {
	        this.userDuty = userDuty == null ? null : userDuty.trim();
	    }

	    public Integer getUserIndex() {
	        return userIndex;
	    }

	    public void setUserIndex(Integer userIndex) {
	        this.userIndex = userIndex;
	    }

	    public String getUserSfz() {
	        return userSfz;
	    }

	    public void setUserSfz(String userSfz) {
	        this.userSfz = userSfz == null ? null : userSfz.trim();
	    }

	    public String getUserSj1() {
	        return userSj1;
	    }

	    public void setUserSj1(String userSj1) {
	        this.userSj1 = userSj1 == null ? null : userSj1.trim();
	    }

	    public String getUserSj2() {
	        return userSj2;
	    }

	    public void setUserSj2(String userSj2) {
	        this.userSj2 = userSj2 == null ? null : userSj2.trim();
	    }

	    public String getUserSj3() {
	        return userSj3;
	    }

	    public void setUserSj3(String userSj3) {
	        this.userSj3 = userSj3 == null ? null : userSj3.trim();
	    }

	    public String getUserLb() {
	        return userLb;
	    }

	    public void setUserLb(String userLb) {
	        this.userLb = userLb == null ? null : userLb.trim();
	    }

	    public String getUserMrdwsb() {
	        return userMrdwsb;
	    }

	    public void setUserMrdwsb(String userMrdwsb) {
	        this.userMrdwsb = userMrdwsb == null ? null : userMrdwsb.trim();
	    }

	    public String getUserModifyDate() {
	        return userModifyDate;
	    }

	    public void setUserModifyDate(String userModifyDate) {
	        this.userModifyDate = userModifyDate == null ? null : userModifyDate;
	    }

	    public Character getUserRoleNeedchange() {
	        return userRoleNeedchange;
	    }

	    public void setUserRoleNeedchange(Character userRoleNeedchange) {
	        this.userRoleNeedchange = userRoleNeedchange == null ? null : userRoleNeedchange;
	    }

	    public String getUserPhoto() {
	        return userPhoto;
	    }

	    public void setUserPhoto(String userPhoto) {
	        this.userPhoto = userPhoto == null ? null : userPhoto.trim();
	    }

		@Override
		public String toString() {
			return "User [userId=" + userId + ", dptUserid=" + dptUserid + ", groupId=" + groupId + ", userRealname="
					+ userRealname + ", userXmpy=" + userXmpy + ", userSex=" + userSex + ", userPassword="
					+ userPassword + ", userFlag=" + userFlag + ", userDuty=" + userDuty + ", userIndex=" + userIndex
					+ ", userSfz=" + userSfz + ", userSj1=" + userSj1 + ", userSj2=" + userSj2 + ", userSj3=" + userSj3
					+ ", userLb=" + userLb + ", userMrdwsb=" + userMrdwsb + ", userModifyDate=" + userModifyDate
					+ ", userRoleNeedchange=" + userRoleNeedchange + ", userPhoto=" + userPhoto + "]";
		}

}
