<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>Home</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link rel="shortcut icon" href="images/favcion.ico" />
<%@include file="Header.jsp"%>
<style type="text/css">
	.pagination-page-list{
		font-size:12px !important;
	}
	.pagination-num{
		font-size:12px !important;
	}
	.textbox combo{width:100% !important;}
	.col-sm-8 input{padding-left: 4px;font-size: 13px}
</style>
<script type="text/javascript">
var treeGroupId = -1;

	$(function() {
			selectGroupFun();
	})
	
	function selectGroupFun() {
		$('#selectGroup').combobox({
				url : 'group/selectGroup/' + treeGroupId,
				editable : false,
				cache : false,
				valueField : 'groupId',
				textField : 'groupName',
				value : '请选择部门',
				panelHeight : "auto"
			})
	}

	//加载树
	$(function() {	
		$("#GroupTree").tree({
			url : 'group/initTree',
			animate : true,
			lines : true,
			onClick : function(node) {
				if(node.id == 100){			
					getAllGroupUser();
				}else{
					treeGroupId = node.id;
					selectGroupFun();
					getUserByGroup(node.id);	
				}		
			}
		})
	})
	
	//加载datagrid
	$(function(){
		$("#dg").datagrid({
			url:'user/getUserList',
			title:'用户管理',
			method:'POST',
			toolbar:'#tb',
			rownumbers:'true',
			columns : [ [
				 //这边列表后期用json返回
                  {field : 'USER_ID', title : '用户编号',width : 120, align:'center',  halign: 'center'  },
                  {field : 'DPT_USERID',title : '大平台用户ID', width : 80, align:'center',halign: 'center' },
                  {field : 'GROUP_ID',title : '所属部门编号', width : 80, align:'center',halign: 'center' },
                  {field : 'USER_REALNAME', title : '用户真实姓名', width : 120,align:'center',halign: 'center' },   
                  {field : 'USER_XMPY', title : '用户首字母', width : 120,align:'center',halign: 'center' },
                  {field : 'USER_SEX', title : '用户性别编号', width : 80,align:'center',halign: 'center' },
                  {field : 'USER_PASSWORD', title : '用户口令', width : 120,align:'center',halign: 'center' },
                  {field : 'USER_FLAG', title : '用户数据标志', width : 80,align:'center',halign: 'center' },
                  {field : 'USER_DUTY', title : '用户职称', width : 80,align:'center',halign: 'center' },
                  {field : 'USER_INDEX', title : '用户显示', width : 120,align:'center',halign: 'center' },
                  {field : 'USER_SFZ', title : '用户身份证号', width : 150,align:'center',halign: 'center' },
                  {field : 'USER_SJ1', title : '用户手机1', width : 120,align:'center',halign: 'center' },
                  {field : 'USER_SJ2', title : '用户手机号2', width : 120,align:'center',halign: 'center' },
                  {field : 'USER_SJ3', title : '用户手机3', width : 120,align:'center',halign: 'center' },
                  {field : 'USER_LB', title : '警员类别', width : 80,align:'center',halign: 'center' },
                  {field : 'USER_MRDWSB', title : '民警默认设备编号', width : 180,align:'center',halign: 'center' },
                  {field : 'USER_MODIFY_DATE', title : '用户修改时间', width :160,align:'center',halign: 'center' },
                  {field : 'USER_ROLE_NEEDCHANGE', title : '是否更新权限', width : 120,align:'center',halign: 'center' },
                  {field : 'USER_PHOTO', title : '用户头像地址', width : 150,align:'center',halign: 'center' },      			 
               ] ],
             /*    pagination: true,  
                pageSize:10,  
                pageList: [10, 15, 20, 25] */
		})
	})




	//刷新所有
	function reFresh() {
		var cmd = "refresh";
		$('#dg').datagrid('load', {
			cmd : cmd
		});
	}

	//获取部门所有成员
	function getAllGroupUser() {
		var cmd = "getAll";
		$('#dg').datagrid('load', {
			cmd : cmd
		});
	}

	//根据条件查询用户
	function Search() {
		var startTimeInput = $("#startTimeInput").val();
		var endTimeInput = $("#endTimeInput").val();
		var keySearchInput = $("#keySearchInput").val();
		if ("" == startTimeInput.trim() && "" == endTimeInput && "" == keySearchInput) {
			alert("请输入范围或关键字！");
		} else {
			if ("" != startTimeInput.trim() && "" == endTimeInput.trim() && "" == keySearchInput.trim()) {
				var cmd = "getUserBystartTime"; //根据开始时间查找
				$('#dg').datagrid('load', {
					startTime : startTimeInput.trim(),
					cmd : cmd
				});
			} else if ("" != endTimeInput.trim() && "" == startTimeInput.trim() && "" == keySearchInput.trim()) {
				var cmd = "getUserByendTime"; //根据结束时间查找
				$('#dg').datagrid('load', {
					endTime : endTimeInput.trim(),
					cmd : cmd
				});
			} else if ("" != keySearchInput.trim() && "" == startTimeInput.trim() && "" == endTimeInput.trim()) {
				var cmd = "getUserByKey"; //根据关键字查找
				$('#dg').datagrid('load', {
					keyWord : keySearchInput.trim(),
					cmd : cmd
				});
			} else if ("" != startTimeInput.trim() && "" != endTimeInput.trim() && "" == keySearchInput.trim()) {
				var cmd = "getUserByDateArea";  //根据时间范围查找
				$('#dg').datagrid('load', {
					startTime : startTimeInput.trim(),
					endTime : endTimeInput.trim(),
					cmd : cmd
				});
			} else if ("" != startTimeInput.trim() && "" != keySearchInput.trim() && "" == endTimeInput.trim()) {
				var cmd = "getUserBystartTimeAndKeyWord";   //根据开始时间和关键字查找
				$('#dg').datagrid('load', {
					startTime : startTimeInput.trim(),
					endTime : endTimeInput.trim(),
					cmd : cmd
				});
			} else if ("" != endTimeInput.trim() && "" != keySearchInput.trim() && "" == startTimeInput.trim()) {
				var cmd = "getUserByendTimeAndKeyWord";  //根据结束时间和关键字查找
				$('#dg').datagrid('load', {
					endTime : endTimeInput.trim(),
					keyWord : keySearchInput.trim(),
					cmd : cmd
				});
			} else if ("" != startTimeInput.trim() && "" != endTimeInput.trim() && "" != keySearchInput.trim()) {
				var cmd = "getUserByAllReq";   //所有条件查询
				$('#dg').datagrid('load', {
					startTime : startTimeInput.trim(),
					endTime : endTimeInput.trim(),
					keyWord : keySearchInput.trim(),
					cmd : cmd
				});
			}
		}
	}

	//根据groupid获取user
	function getUserByGroup(groupId) {
		var cmd = "getUserByGroup"
		$('#dg').datagrid('load', {
			groupid : groupId,
			cmd : cmd
		});
	}

	//新建用户dialog
	function newUser(){
		$('#userDlg').dialog('open').dialog('setTitle','新建用户');
    	/*  $('#userForm').form('clear');  */
    	
	}

	//update用户
	function saveUser() {
		var validateFlag = 0;
		var userId = $('input[name="userId"]').val();
		if (null != userId && "" != userId.trim() && undefined != userId) {
			var validateResult = validate(validateFlag);
			if (validateResult == 1) {
				$('#userForm').form('submit', {
					url : 'user/editUser/' + userId,
					success : function(data) {
						var result = eval('(' + data + ')');
						if (result.status == "200") {
							$.messager.show({
								title : '操作成功',
								msg : result.message
							});
							$('#userDlg').dialog('close');
							$('#dg').datagrid('reload');
						} else {
							$.messager.show({
								title : '操作失败',
								msg : result.message
							});
						}
					},
					error : function(XMLHttpRequest, textStatus, errotThrown) {
						$.messager.show({
							title : '操作失败',
							msg : XMLHttpRequest + "，" + textStatus
						});
					}
				})
			}
		} else {
			
			var validateResult = validate(validateFlag);
			if (validateResult == 1) {
				$('#userForm').form('submit', {
					url : 'user/addUser',
					success : function(data) {
						var result = eval('(' + data + ')');
						if (result.status == "200") {
							$.messager.show({
								title : '操作成功',
								msg : result.message
							});
							$('#userDlg').dialog('close');
							$('#dg').datagrid('reload');
						} else {
							$.messager.show({
								title : '操作失败',
								msg : result.message
							});
						}
					},
					error : function(XMLHttpRequest, textStatus, errotThrown) {
						$.messager.show({
							title : '操作失败',
							msg : XMLHttpRequest + "，" + textStatus
						});
					}
				})
			}
		}
	}

	//表单简单验证
	function validate(validateFlag){
			var validateFlags = validateFlag;
		    var groupId = $('input[name="groupId"]').val();
			var userRealName = $('input[name="userRealname"]').val();
			var userSexSelect = $("#userSexSelect").combobox('getValue');
			var userPassword = $('input[name="userPassword"]').val();
			var userFlagSelect =$("#userFlagSelect").combobox('getValue');
			var userIndex = $('input[name="userIndex"]').val();
			var userLb = $('input[name="userLb"]').val();
			if (null == groupId || "" == groupId.trim() || undefined == groupId) {
				alert("部门编号不能为空！");
			} else if (null == userRealName || "" == userRealName.trim() || undefined == userRealName) {
				alert("用户真实姓名不能为空！");
			} else if (null == userSexSelect || "" == userSexSelect.trim() || undefined == userSexSelect) {
				alert("请选择用户性别！");
			} else if (null == userPassword || "" == userPassword.trim() || undefined == userPassword) {
				alert("用户口令不能为空！");
			} else if (null == userFlagSelect || "" == userFlagSelect.trim() || undefined == userFlagSelect) {
				alert("请选择用户数据标志！");
			} else if (null == userIndex || "" == userIndex.trim() || undefined == userIndex) {
				alert("用户显示顺序不能为空！");
			} else if (null == userLb || "" == userLb.trim() || undefined == userLb) {
				alert("警员类别不能为空！");
			} else if (userIndex < 0) {
				alert("请输入正确的用户显示顺序！");
			}else{
				validateFlags = 1;
			}
			return validateFlags;
	}

	//编辑用户dialog表单绑定
	function editUser() {
		var rows = $('#dg').datagrid('getSelections');
		if (rows.length != 1 && rows.length != 0) {
			alert("请选择一条数据进行编辑！");	
		}else if(rows.length  == 0){
			alert("请选择要编辑的数据！");
		} else if (rows.length == 1) {
				var sex = 0;
				var flag =0;
				var change =0;
				if(rows[0].USER_SEX == "男"){
					sex = 0;
				}else if(rows[0].USER_SEX == "女"){
					sex = 1;
				}
				if(rows[0].USER_FLAG == "禁用"){
					flag = 0;
				}else if(rows[0].USER_FLAG == "启用"){
				 	flag = 1;
				}else if(rows[0].USER_FLAG == "调离"){
					flag = 2;
				}
				if(rows[0].USER_ROLE_NEEDCHANGE == "需要更新"){
					change = 1;
				}else if (rows[0].USER_ROLE_NEEDCHANGE == "不需要更新"){
					change = 0;
				}
				$('#userDlg') .dialog('open').dialog('setTitle', '编辑用户');
				//$('#userForm').form('load', rows);
				//url = "user/getUser" + rows[0].USER_ID; //mvc接收不到
				$("#userForm").form("load", {
					userId : rows[0].USER_ID,
					dptUserid : rows[0].DPT_USERID,
					groupId : rows[0].GROUP_ID,
					userRealname : rows[0].USER_REALNAME,
					userXmpy : rows[0].USER_XMPY,
					userSex : sex,
					userPassword : rows[0].USER_PASSWORD,
					userFlag :flag,
					userDuty : rows[0].USER_DUTY,
					userIndex : rows[0].USER_INDEX,
					userSfz : rows[0].USER_SFZ,
					userSj1 : rows[0].USER_SJ1,
					userSj2 : rows[0].USER_SJ2,
					userSj3 : rows[0].USER_SJ3,
					userLb : rows[0].USER_LB,
					userMrdwsb : rows[0].USER_MRDWSB,
					userModifyDate : rows[0].USER_MODIFY_DATE,
					userRoleNeedchange :change
				});
			}
			else {
			alert("选择数据出错！");
		}
	}

	//删除用户
	function deleteUser() {
		var rows = $('#dg').datagrid('getSelections');
		if (rows.length != 1 && rows.length != 0) {
			alert("请选择一条数据删除！");
		} else if (rows.length == 0) {
			alert("请选择要删除的数据！");
		} else if (rows.length == 1) {
			$.messager.confirm('确认', '你确定要删除这个用户吗？', function(checkResult) {
				if (checkResult) {
					$.ajax({
						url : "user/deleteUser",
						type : "POST",
						data : rows[0].USER_ID,
						dataType : "json",
						contentType : 'application/json;charset=UTF-8',
						success : function(deleteUserresult) {
							if (deleteUserresult.status == "200") {
								$.messager.show({
									title : '操作成功',
									msg : deleteUserresult.message
								});
								$('#dg').datagrid('reload');
							} else {
								$.messager.show({
									title : '操作失败',
									msg : deleteUserresult.message
								});
							}
						},error:function(XMLHttpRequest,textStatus,errotThrown){
						$.messager.show({
								title : '操作失败',
								msg : XMLHttpRequest + "，" + textStatus
						});
					}
					})
				} 
			})
		} else {
			alert("选择数据出错！");
		}
	}

	
</script>
</head>

<body class="easyui-layout">
	
	<div class="easyui-layout" style="width:100%;height:700px;">
	
		<!-- tree -->
		<div region="west" split="true" title="列表" style="width:150px;">
			<ul id="GroupTree" class="easyui-tree"></ul>	
		</div>

		<!-- center -->
		<div id="content" region="center" title="面板" style="padding:5px;">
		<!-- 工具栏 -->
			<div id="tb" style="padding:5px;height:auto">
				<div style="margin-bottom:5px">
					<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newUser()">添加</a> 		
					<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editUser()">编辑</a> 		
					<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-reload" plain="true" onclick="reFresh()">刷新</a>			
					<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="deleteUser()">删除</a>		
				</div>
				<div>
					从: <input class="easyui-datebox" style="width:120px" id="startTimeInput">
					至: <input class="easyui-datebox" style="width:120px" id="endTimeInput">
					关键字（用户编号）:  <input type="text" class="easyui-textbox"  id="keySearchInput" >
					<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" onclick="Search()">查询</a>
				</div>
			</div>
			<!-- 工具栏 END -->
			
			<!--datagrid -->
			 <table id="dg" style="width:98%;height:98%"></table>

			<!-- userDlg -->
			<div id="userDlg" class="easyui-dialog" style="width:400px;height:280px;padding:10px 20px" closed="true" buttons="#userBtn">
				<form class="form-horizontal" id="userForm">	
					<div class="form-group">
						<label for="USER_ID" class="col-sm-4 control-label">用户编号</label>
						<div class="col-sm-8">
							<input type="text" class="form-control"  name="userId"  disabled="disabled" >			
						</div>
					</div>
				
					<div class="form-group">
						<label for="DPT_USERID" class="col-sm-4 control-label">大平台用户编号</label>
						<div class="col-sm-8">
							<input type="text" class="form-control"  name="dptUserid"  maxlength="50"  >			
						</div>
					</div>
					
					<div class="form-group">
						<label for="GROUP_ID" class="col-sm-4 control-label">所属部门</label>
						<div class="col-sm-8">
						<!-- <input type="text" class="form-control"  name="groupId"  maxlength="20" > -->
						<!-- 	<select class="easyui-Combobox" name="groupList" id="selectGroup" style="width:100%">
								<option value="部门编号" >部门名称</option>
							</select> -->
							<input id="selectGroup" name="groupId" class="easyui-combobox"  style="width:100%"> 	 
						</div>
					</div>
					
					<div class="form-group">
						<label for="USER_REALNAME" class="col-sm-4 control-label">用户真实姓名</label>
						<div class="col-sm-8">
							<input type="text" class="form-control"  name="userRealname" maxlength="15" >			
						</div>
					</div>
					
					<div class="form-group">
						<label for="USER_XMPY" class="col-sm-4 control-label">用户姓名首字母</label>
						<div class="col-sm-8">
							<input type="text" class="form-control"  name="userXmpy" maxlength="15" >			
						</div>
					</div>
					
					<div class="form-group">
						<label for="USER_SEX" class="col-sm-4 control-label">用户性别</label>
						<div class="col-sm-8">
							<select  class="easyui-combobox" name="userSex" id="userSexSelect" panelHeight="auto" style="width:100%!important;" >
							  <option value="0" >男</option>
							  <option value="1">女</option>		 
							</select> 
						</div>
					</div>
					
					<div class="form-group">
						<label for="USER_PASSWORD" class="col-sm-4 control-label">用户口令</label>
						<div class="col-sm-8">
							<input type="text" class="form-control"  name="userPassword"  maxlength="30">			
						</div>
					</div>
					
					<div class="form-group">
						<label for="USER_FLAG" class="col-sm-4 control-label">用户数据标志</label>
						<div class="col-sm-8">
							<!-- <input type="text" class="form-control"  name="userFlag" > -->	
							<select class="easyui-combobox"  name=userFlag id="userFlagSelect" panelHeight="auto"  style="width:100%!important;">
							  <option value="0" >禁用</option>
							  <option value="1">启用</option>	
							  <option value="2">调离</option>		 
							</select> 
							
							<!-- <input id="userFlagSelect" name="userFlag" class="easyui-combobox"  
							style="width:100%" value="请选择用户数据标志"  panelHeight="auto"> -->
						</div>
					</div>
					
					<div class="form-group">
						<label for="USER_DUTY" class="col-sm-4 control-label">用户职称</label>
						<div class="col-sm-8">
							<input type="text" class="form-control"  name="userDuty"  maxlength="50">			
						</div>
					</div>
					
					<div class="form-group">
						<label for="USER_INDEX" class="col-sm-4 control-label">用户显示顺序</label>
						<div class="col-sm-8">
							<input type="number" class="form-control"  name="userIndex"   min="0" max="9999" oninput="if(value.length>4)value=value.slice(0,4)" id="inputuserIndex">			
						</div>
					</div>
					
					<div class="form-group">
						<label for="USER_SFZ" class="col-sm-4 control-label">用户身份证号</label>
						<div class="col-sm-8">
							<input type="text" class="form-control"  name="userSfz"  maxlength="18">			
						</div>
					</div>
					
					<div class="form-group">
						<label for="USER_SJ1" class="col-sm-4 control-label">用户手机1</label>
						<div class="col-sm-8">
							<input type="text" class="form-control"  name="userSj1"  maxlength="12">			
						</div>
					</div>
					
					<div class="form-group">
						<label for="USER_SJ2" class="col-sm-4 control-label">用户手机号2</label>
						<div class="col-sm-8">
							<input type="text" class="form-control"  name="userSj2"  maxlength="12">			
						</div>
					</div>
					
					<div class="form-group">
						<label for="USER_SJ3" class="col-sm-4 control-label">用户手机3</label>
						<div class="col-sm-8">
							<input type="text" class="form-control"  name="userSj3"  maxlength="12">			
						</div>
					</div>
					
					<div class="form-group">
						<label for="USER_LB" class="col-sm-4 control-label">警员类别</label>
						<div class="col-sm-8">
							<input type="text" class="form-control"  name="userLb"  maxlength="2" >			
						</div>
					</div>
					
					<div class="form-group">
						<label for="USER_MRDWSB" class="col-sm-4 control-label">民警设备编号</label>
						<div class="col-sm-8">
							<input type="text" class="form-control"  name="userMrdwsb" maxlength="50" >			
						</div>
					</div>
			
					<div class="form-group">
						<label for="USER_ROLE_NEEDCHANGE" class="col-sm-4 control-label">否需要更新权限</label>
						<div class="col-sm-8">
						<!-- 	<input type="text" class="form-control" name="userRoleNeedchange" > -->		
					<select  class="easyui-combobox" name="userRoleNeedchange" id="userRoleNeedchange" panelHeight="auto"  style="width:100%!important;">
							  <option value="0" >否</option>
							  <option value="1">是</option>		 
							</select> 
						<!-- <input id="userRoleNeedchange" name="userRoleNeedchange" class="easyui-combobox"  
						style="width:100%" value="请选择是否需要更新权限"  panelHeight="auto"> -->
						</div>
					</div>
					
				</form>
				<div id="userBtn">
					<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveUser()">保存</a>
   					<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#userDlg').dialog('close')"  iconcls="icon-cancel">取消</a>      
				</div>
			</div>	
			
			<!-- userDlg  END  -->
			
			
		</div>
	</div>
</body>