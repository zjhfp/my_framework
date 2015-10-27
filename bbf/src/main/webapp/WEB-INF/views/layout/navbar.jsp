<%@page contentType="text/html" pageEncoding="UTF-8"%>
<nav class="navbar navbar-fixed-top navbar-default navbar-blue" role="navigation" >
	<div class="navbar-header">
		<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="" aria-expanded="false">
			<span class="sr-only">Toggle navigation</span>
			<span class="icon-bar"></span>
			<span class="icon-bar"></span>
			<span class="icon-bar"></span>
		</button>
		<a class="navbar-brand" href="${ctx}/index"><small>home</small></a>
	</div>
	<ul class="nav navbar-nav navbar-right" style="padding-right:10px;">
		<li>
			<a class="dropdown-toggle avatat" data-toggle="dropdown" href="javascript:void(0)" aria-expended="false">
				头像
				<span class="badge">1</span>
			</a>
			<ul class="dropdown-menu">
				<li class="">
					<a href="#">
						<i class=""></i>
						系统设置
					</a>
				</li>
				<li class="">
					<a href="#">
						<i class=""></i>
						个人资料
					</a>
				</li>
				<li class="">
					<a href="#">
						<i class=""></i>
						退出登录
					</a>
				</li>
			</ul>
		</li>
	</ul>
</nav>