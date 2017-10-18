package com.ZJJ.Controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ZJJ.POJO.Condition;
import com.ZJJ.POJO.User;
import com.ZJJ.Service.IUserService;
import com.ZJJ.Utility.Utility;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/user")
public class UserController {

	@Resource
	private IUserService userService;

	private static Logger logger = Logger.getLogger(UserController.class);

	@RequestMapping("/Home")
	public String getHome() {
		return "Home";
	}

	/**
	 * 查找用户
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/getUserList")
	public HashMap<String, Object> getUser(HttpServletRequest request) throws Exception {
		Utility utility = new Utility();
		logger.info("getUserList...");
		logger.info("request：" + request.getParameter("cmd"));
		HashMap<String, Object> userRespMap = null;
		UserController userController = new UserController();
		String searchCmd = request.getParameter("cmd");
		// 获取查询命令
		/*
		 * Integer page =Integer.valueOf(request.getParameter("page")); //页码
		 * Integer rows = Integer.valueOf(request.getParameter("rows")); //数据量
		 */ if (searchCmd == null || searchCmd.equals("refresh") || searchCmd.equals("getAll")) {
			logger.info("cmd为" + searchCmd + "，开始查询全部...");
			List<User> userList = userService.getUserList(); // 拿到全部User
			userRespMap = userController.getUserRespMap(userList);
		} else if (searchCmd.equals("getUserByGroup")) { // 根据部门编号查询
			String groupid = request.getParameter("groupid");
			logger.info("查询部门编号为：" + groupid + "的用户");
			List<User> userList = userService.getUserListByGroupId(groupid); // 根据部门ID拿到user
			userRespMap = userController.getUserRespMap(userList);
		} else if (searchCmd.equals("getUserBystartTime")) {
			logger.info("根据开始时间查找");
			String startTime = request.getParameter("startTime");
			Condition conditions = new Condition();
			conditions.setEndTime(null);
			conditions.setKeyWord(null);
			conditions.setStartTime(utility.getReplaceDate(startTime));
			List<User> userListByCondition = userService.getUserListByCondition(conditions);
			userRespMap = userController.getUserRespMap(userListByCondition);
		} else if (searchCmd.equals("getUserByendTime")) {
			logger.info("根据结束时间查找");
			String endTime = request.getParameter("endTime");
			Condition conditions = new Condition();
			conditions.setEndTime(utility.getReplaceDate(endTime));
			conditions.setKeyWord(null);
			conditions.setStartTime(null);
			List<User> userListByCondition = userService.getUserListByCondition(conditions);
			userRespMap = userController.getUserRespMap(userListByCondition);

		} else if (searchCmd.equals("getUserByKey")) {
			logger.info("根据关键字查找");
			String keyWord = request.getParameter("keyWord");
			Condition conditions = new Condition();
			conditions.setEndTime(null);
			conditions.setKeyWord(keyWord);
			conditions.setStartTime(null);
			List<User> userListByCondition = userService.getUserListByCondition(conditions);
			userRespMap = userController.getUserRespMap(userListByCondition);

		} else if (searchCmd.equals("getUserByDateArea")) {
			logger.info("根据时间范围查找");
			String startTime = request.getParameter("startTime");
			String endTime = request.getParameter("endTime");
			Condition conditions = new Condition();
			conditions.setEndTime(utility.getReplaceDate(endTime));
			conditions.setKeyWord(null);
			conditions.setStartTime(utility.getReplaceDate(startTime));
			List<User> userListByCondition = userService.getUserListByCondition(conditions);
			userRespMap = userController.getUserRespMap(userListByCondition);

		} else if (searchCmd.equals("getUserBystartTimeAndKeyWord")) {
			logger.info("根据开始时间和关键字查找");
			String startTime = request.getParameter("startTime");
			String keyWord = request.getParameter("keyWord");
			Condition conditions = new Condition();
			conditions.setEndTime(null);
			conditions.setKeyWord(keyWord);
			conditions.setStartTime(utility.getReplaceDate(startTime));
			List<User> userListByCondition = userService.getUserListByCondition(conditions);
			userRespMap = userController.getUserRespMap(userListByCondition);
		} else if (searchCmd.equals("getUserByendTimeAndKeyWord")) {
			logger.info("根据结束时间和关键字查找");
			String endTime = request.getParameter("endTime");
			String keyWord = request.getParameter("keyWord");
			Condition conditions = new Condition();
			conditions.setEndTime(utility.getReplaceDate(endTime));
			conditions.setKeyWord(keyWord);
			conditions.setStartTime(null);
			List<User> userListByCondition = userService.getUserListByCondition(conditions);
			userRespMap = userController.getUserRespMap(userListByCondition);

		} else if (searchCmd.equals("getUserByAllReq")) {
			String startTime = request.getParameter("startTime");
			String endTime = request.getParameter("endTime");
			String keyWord = request.getParameter("keyWord");
			Condition conditions = new Condition();
			conditions.setEndTime(utility.getReplaceDate(endTime));
			conditions.setKeyWord(keyWord);
			conditions.setStartTime(utility.getReplaceDate(startTime));
			List<User> userListByCondition = userService.getUserListByCondition(conditions);
			userRespMap = userController.getUserRespMap(userListByCondition);
		}
		return userRespMap;
	}

	/**
	 * 添加用户
	 * 
	 * @param reqUser
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/addUser")
	public HashMap<String, String> addUser(@ModelAttribute User reqUser) throws Exception {
		logger.info("addUser start...");
		logger.info("addUser 实际接收到的数据是：" + reqUser.toString());
		User user = new User();
		Utility utility = new Utility();
		user.setUserId(utility.getUUidByYMD());
		user.setDptUserid(reqUser.getDptUserid());
		user.setGroupId(reqUser.getGroupId());
		user.setUserRealname(reqUser.getUserRealname());
		user.setUserXmpy(reqUser.getUserXmpy());
		user.setUserSex(reqUser.getUserSex());
		user.setUserPassword(reqUser.getUserPassword());
		user.setUserFlag(reqUser.getUserFlag());
		user.setUserDuty(reqUser.getUserDuty());
		user.setUserIndex(reqUser.getUserIndex());
		user.setUserSfz(reqUser.getUserSfz());
		user.setUserSj1(reqUser.getUserSj1());
		user.setUserSj2(reqUser.getUserSj2());
		user.setUserSj3(reqUser.getUserSj3());
		user.setUserLb(reqUser.getUserLb());
		user.setUserMrdwsb(reqUser.getUserMrdwsb());
		String mTime = utility.formatDateYYYYMMDDHHmmss(new Date());
		user.setUserModifyDate(mTime);
		if (null == reqUser.getUserRoleNeedchange() || reqUser.getUserRoleNeedchange().equals('0')) {
			user.setUserRoleNeedchange('0');
		} else {
			user.setUserRoleNeedchange('1');
		}
		user.setUserPhoto(reqUser.getUserPhoto());
		int insertUserResult = userService.insertUser(user);
		logger.info("inserUser 返回的状态码是:" + insertUserResult);
		HashMap<String, String> respMap = new HashMap<String, String>();
		if (insertUserResult == 1) {
			respMap.put("status", "200");
			respMap.put("message", "添加用户成功！");
		} else {
			respMap.put("status", "500");
			respMap.put("message", "添加用户失败！");
		}
		return respMap;
	}

	/**
	 * 编辑用户
	 * 
	 * @param userId
	 * @param reqUser
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/editUser/{userId}")
	public HashMap<String, String> editUser(@PathVariable String userId, @ModelAttribute User reqUser)
			throws Exception {
		logger.info("editUser 实际接收到的数据：" + userId);
		reqUser.setUserId(userId);
		Utility utility = new Utility();
		String updateTime = utility.formatDateYYYYMMDDHHmmss(new Date());
		reqUser.setUserModifyDate(updateTime);
		int updateUserResult = userService.updateUserById(reqUser);
		HashMap<String, String> respMap = new HashMap<String, String>();
		logger.info("更新结果：" + updateUserResult);
		if (updateUserResult == 1) {
			respMap.put("status", "200");
			respMap.put("message", "更新用户成功！");
		} else {
			respMap.put("status", "500");
			respMap.put("message", "更新用户失败！");
		}
		return respMap;
	}

	/**
	 * 删除用户
	 * 
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/deleteUser", produces = "application/json;charset=UTF-8")
	public HashMap<String, String> deleteUser(@RequestBody String userId) throws Exception {
		logger.info("deleteUser：" + userId);
		int deleteUserResult = -1;
		try {
			// 批量事务
			deleteUserResult = userService.deleteUser(userId);
			logger.info("deleteUserResult" + deleteUserResult);
		} catch (Exception e) {
			logger.info("deleteUser fail Message:" + e.getMessage());
			logger.error("deletUser" + userId + "fail");
		}
		HashMap<String, String> respMap = new HashMap<String, String>();
		if (deleteUserResult == 1) {
			respMap.put("status", "200");
			respMap.put("message", "删除用户成功！");
		} else {
			respMap.put("status", "500");
			respMap.put("message", "删除用户失败！");
		}
		return respMap;
	}

	/**
	 * 返回userlist
	 * 
	 * @param userList
	 * @return
	 */
	private HashMap<String, Object> getUserRespMap(List<User> userList) {
		List<HashMap<String, Object>> listMap = new ArrayList<HashMap<String, Object>>();
		int total = userList.size();
		for (int i = 0; i < total; i++) {
			HashMap<String, Object> mapData = new HashMap<String, Object>();
			mapData.put("USER_ID", userList.get(i).getUserId());
			mapData.put("GROUP_ID", userList.get(i).getGroupId());
			mapData.put("DPT_USERID", userList.get(i).getDptUserid());
			mapData.put("USER_REALNAME", userList.get(i).getUserRealname());
			mapData.put("USER_XMPY", userList.get(i).getUserXmpy());
			if (userList.get(i).getUserSex().equals('0')) {
				mapData.put("USER_SEX", "男");
			} else {
				mapData.put("USER_SEX", "女");
			}
			mapData.put("USER_PASSWORD", userList.get(i).getUserPassword());
			if (userList.get(i).getUserFlag().equals('0')) {
				mapData.put("USER_FLAG", "禁用");
			} else if (userList.get(i).getUserFlag().equals('1')) {
				mapData.put("USER_FLAG", "启用");
			} else if (userList.get(i).getUserFlag().equals('2')) {
				mapData.put("USER_FLAG", "调离");
			}
			mapData.put("USER_DUTY", userList.get(i).getUserDuty());
			mapData.put("USER_INDEX", userList.get(i).getUserIndex());
			mapData.put("USER_SFZ", userList.get(i).getUserSfz());
			mapData.put("USER_SJ1", userList.get(i).getUserSj1());
			mapData.put("USER_SJ2", userList.get(i).getUserSj2());
			mapData.put("USER_SJ3", userList.get(i).getUserSj3());
			mapData.put("USER_LB", userList.get(i).getUserLb());
			mapData.put("USER_MRDWSB", userList.get(i).getUserMrdwsb());
			mapData.put("USER_MODIFY_DATE", userList.get(i).getUserModifyDate());
			if (userList.get(i).getUserRoleNeedchange().equals('0')) {
				mapData.put("USER_ROLE_NEEDCHANGE", "不需要更新");
			} else {
				mapData.put("USER_ROLE_NEEDCHANGE", "需要更新");
			}
			mapData.put("USER_PHOTO", userList.get(i).getUserPhoto());
			listMap.add(mapData);
		}
		HashMap<String, Object> respMap = new HashMap<String, Object>();
		respMap.put("total", total);
		respMap.put("rows", listMap);
		return respMap;
	}

}
