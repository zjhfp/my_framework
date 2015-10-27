<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>用户管理</title>
		<%@ include file="/WEB-INF/views/layout/common.jsp"%>
	</head>
<body class="navbar-fixed">
	<%@ include file="/WEB-INF/views/layout/navbar.jsp"%>
	<%@ include file="/WEB-INF/views/layout/sidebar.jsp"%>
	<div class="page-wrapper">
		<div class="main-container">
			<div>
				<ul class="breadcrumb">
				  <li><a href="${ctx}/index">Home</a></li>
				  <li><a href="javascript:void(0)">用户管理</a></li>
				  <li class="active">用户列表</li>
				</ol>
			</div>
			<a class="btn btn-primary pull-right" href="javascript:void(0)" data-toggle="modal" data-target="#addUser" role="button">添加用户</a>
			<div class="container">
				<table class="table">
					<caption>用户信息</caption>
					<thead>
						<tr>
							<th>用户名</th>
							<th>真实姓名</th>
							<th>邮箱</th>
							<th>电话号码</th>
							<th>最后登录时间</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody id="userList">
					</tbody>
				</table>
			</div>
		</div>
		<!-- 用户添加模态框 -->
		<div class="modal fade" id="addUser" role="dialog">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="model-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        				<h4 class="modal-title">添加用户</h4>
					</div>
					<div class="modal-body">
						<form class="form-horizontal" role="form" id="form_addUser">
							<div class="form-group">
								<label class="col-md-3 control-label">用户名</label>
								<div class="col-md-8">
									<input class="form-control" type="text" name="loginName" placeholder="用户登录名">
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-3 control-label">真实姓名</label>
								<div class="col-md-8">
									<input class="form-control" type="text" name="realName" placeholder="用户真实姓名">
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-3 control-label">邮箱</label>
								<div class="col-md-8">
									<input class="form-control" type="text" name="email" placeholder="用户邮箱">
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-3 control-label">电话号码</label>
								<div class="col-md-8">
									<input class="form-control" type="text" name="phone" placeholder="电话号码">
								</div>
							</div>
							<div class="form-group">
								<label for="" class="col-md-3 control-label">是否为超级用户</label>
								<div class="col-md-8">
									<label class="radio-inline">
										<input class="control-label" type="radio" name="isAdmin" value="0" checked>否
									</label>
									<label class="radio-inline">
										<input type="radio" name="isAdmin" value="1">是
									</label>
								</div>
							</div>
						</form>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
        				<button type="button" class="btn btn-primary" id="add_user_submit">保存</button>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
	
		function renderTable(){
			$tbody = $("#userList");
			$tbody.html('');
			var url = ctx+'/user/list';
			var columns = [{
				'datafield' : 'loginName'
			},{
				'datafield' : 'realName'
			},{
				'datafield' : 'email'
			},{
				'datafield' : 'phone'
			},{
				'datafield' : 'lastLoginDate'
			},{
				'datafield' : 'oper',
				'oper' : '<a href="#" class="btn btn-danger">删除</a><a href="#" class="btn btn-danger">编辑</a>'
			}];
			$.post(url,function(res){
				if(res.status !== '200'){
					alert("获取用户数据失败");
	             }else{
	            	 var data = res.result;
	            	data.map(function(val){
	            		var $tr = $('<tr></tr>');
	            		columns.map(function(c_val){
	            			var $td;
	            			if(c_val['datafield'] === 'oper'){
	            				$td = $('<td>'+c_val['oper']+'</td>');
	            			}else{
		            			var result = val[c_val['datafield']] || '--';
								$td = $('<td>'+result+'</td>');
	            			}
		            		$tr.append($td);
	            		});
	            		$tbody.append($tr);
	            	}); 
	             }
			});
		}
	
		$(document).ready(function(){
			
			
			renderTable();
			
			// 添加用户
			$("#add_user_submit").bind('click',function(){
				var url = ctx+"/user/adduser";
				$.post(url,$("#form_addUser").serializeArray(),function(res){
					if(res.status==='200'){
						alert("用户添加成功");
						$('#addUser').modal('hide');
						renderTable();
		             }else{
		                alert(res.errorMessage);
		             }
				});
			});
		});
	</script>
</body>
</html>