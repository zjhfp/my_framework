<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="navbar sidebar" role="navigation">
	<div class="sidebar-nav navbar-default navbar-collapse">
		<ul class="nav nav-list">
			<li>
				<a class="active" href="${ctx}/index">
					Dashboard
				</a>
			</li>
			<li>
				<a href="#schedule_manage" data-toggle="collapse">
					调度管理
				</a>
				<ul id="schedule_manage" class="nav nav-second-level collapse">
					<li>
						<a href="#">调度查询</a>
					</li>
					<li>
						<a href="#">调度添加</a>
					</li>
				</ul>
			</li>
			<li>
				<a href="#user_manage" data-toggle="collapse">
					用户管理
				</a>
				<ul id="user_manage" class="nav nav-second-level collapse">
					<li>
						<a href="${ctx}/user/list">用户列表</a>
					</li>
				</ul>
			</li>
		</ul>
	</div>
</div>