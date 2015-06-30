<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String webProject = request.getServerName() + ":"
			+ request.getServerPort() + path;
%>
<script type="text/javascript">
var callcenter = callcenter || {};
	callcenter.basePath = "<%=basePath%>";
	callcenter.webProject =  "<%=webProject%>";
</script>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>状态监控</title>
<script type="text/javascript" src="<%=path%>/js/jquery-1.8.3.min.js"></script>
<link href="<%=path%>/plugIn/css/UtryJsLib.all.css" type="text/css"
	rel="stylesheet">
<link href="<%=path%>/plugIn/jquery-ui/jquery-ui.css" type="text/css"
	rel="stylesheet">
<link href="<%=path%>/css/UtryUI.css" type="text/css" rel="stylesheet">
<link href="<%=path%>/css/style.css" type="text/css" rel="stylesheet">
<script type="text/javascript"
	src="<%=path%>/plugIn/jquery-ui/jquery-ui.all.js"></script>
<script type="text/javascript"
	src="<%=path%>/plugIn/js/UtryJsLib.all.js"></script>
<script type="text/javascript"
	src="<%=path%>/plugIn/js/UtryJsLib.all.min.js"></script>
<script type="text/javascript" src="<%=path%>/plugIn/charts/esl.js"></script>
<script type="text/javascript"
	src="<%=path%>/js/monitor/stateMonitoring.js"></script>
<script type="text/javascript"
	src="<%=path%>/js/monitor/clientDataAction.js"></script>
<style>
.row-fluid [class*="span"] {
	margin-left: 0.727659574468085%;
}

.row-fluid .span10 {
	width: 84.37872340425532%;
	*width: 82.92553191489361%;
}
</style>
</head>

<body>
	<div class="container-fluid layout-box main-box" id="">
		<div class="row-fluid" style="height:100%;">
			<div class="span2 layout-box box-border">
				<div class="row-fluid layout-center overflow_yout">
					<input type="text" class="input input_medium float_l"
						style="margin:10px;" placeholder="请输入姓名或工号" />
					<div class="clear" id="div_tree_box" style="margin:10px"></div>
				</div>
			</div>
			<div class="span10 layout-box">
				<div class="row-fluid layout-top spe_backcolor all_states_con"
					style="height:38px;">
					<div class="all_states tabte_1" id="exhale">
						呼出<span id="exhaleCount">0</span>
					</div>
					<div class="all_states tabte_2" id="dialout">
						外拨<span id="dialoutCount">0</span>
					</div>
					<div class="all_states state_wrong tabte_3" id="axu">
						AXU外拨<span id="axuCount">0</span>
					</div>
					<div class="all_states tabte_4" id="eat">
						就餐<span id="eatCount">0</span>
					</div>
					<div class="all_states tabte_5" id="littlerest">
						小休<span id="littlerestCount">0</span>
					</div>
					<div class="all_states tabte_6" id="afterwards">
						事后<span id="afterwardsCount">0</span>
					</div>
					<div class="all_states tabte_7" id="idle">
						空闲<span id="idleCount">0</span>
					</div>
					<input type="button" value="返回" id="returnButton"
						class="btn btn-primary float_l back_state" />
				</div>
				<div
					class="row-fluid layout-center overflow_yout box-border emp_inf_con">
					<div class="layout-box">
						<div class="row-fluid layout-top"
							style="height:30px; background:#f7f7f7; padding-right:17px; border-bottom:1px solid
                #d7d7d7;">
							<table class="table">
								<thead>
									<tr>
										<th width="10%">姓名</th>
										<th width="8%">工号</th>
										<th width="10%">班次</th>
										<th width="14%">组别</th>
										<th width="15%">技能线</th>
										<th width="15%">分机号</th>
										<th width="8%">状态</th>
										<th width="10%">状态时长</th>
										<th width="10%">操作</th>
									</tr>
								</thead>
							</table>
						</div>
						<div class="row-fluid layout-center scroll_y">
							<div id="ul_box" class="showbox">
								<table class="table ellipsis show_detailed_t">
									<tbody fm-body>
										<tr>
											<td width="10%">{{sName}}</td>
											<td width="8%">{{sno}}</td>
											<td width="10%">{{classNo}}</td>
											<td width="14%">{{groupType}}</td>
											<td width="15%">{{branchCenter}}</td>
											<td width="15%">{{extensionNumber}}</td>
											<td width="8%"><span fm-conver="converageStatus">{{status}}</span></td>
											<td width="10%"><span fm-conver="converageAddRed">{{isRed}},{{timeLong}}</span></td>
											<td width="10%"><input type="button" value="详情"
												id="{{sno}}" class="btn state_infor_btn" onclick="rowOnclick('{{sName}}','{{sno}}','{{classNo}}','{{groupType}}')"/></td>
											<!-- <span fm-conver="converageAddRed">{{status}}</span> -->
										</tr>
									</tbody>
								</table>
							</div>
						</div>
						<div class="row-fluid layout-bottom" style="height:35px;">
							<div fm-pagerbox="" class="tfoot"></div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/template" id="">
    </script>

	<script>
		$(document).ready(function() {
			//计算状态按钮的宽度
			var states_w = $('.all_states_con').width();
			$('.all_states').css("width", (states_w - 172 - 18) / 9);
			$(window).resize(function() {
				var states_w = $('.all_states_con').width();
				$('.all_states').css("width", (states_w - 172 - 18) / 9);
			});

			//状态按钮的单击事件
			$('.all_states').click(function() {
				if ($(this).hasClass('state_checked')) {
					$(this).removeClass('state_checked');
				} else {
					$(this).addClass('state_checked');
				}
			})

			//返回按钮的单击事件
			$('.back_state').click(function() {
				$('.all_states').removeClass('state_checked');
			})
		});
		
	</script>
	<script type="text/template" id="templatedome1">
		<table class="table" style="border:1px solid #d7d7d7; border-bottom:0px;">
		<tr>
		<td class="ltd">姓名: </td>
		<td class="rtd" id="userName"></td>
		<td class="ltd">工号: </td>
		<td class="rtd" id="userSno"></td>
		</tr>
		<tr>
		<td class="ltd">班次: </td>
		<td class="rtd"id="userClassno"></td>
		<td class="ltd">组别: </td>
		<td class="rtd" id="userGroup"></td>
		</tr>
		</table>
		<table class="table mt10" style="border:1px solid #d7d7d7; border-bottom:0px;">
		<thead>
		<tr style="border-bottom:1px solid #d7d7d7;">
		<th></th>
		<th>呼出</th>
		<th>外拨</th>
		<th>AXU外拨</th>
		<th>就餐</th>
		<th>小休</th>
		<th>事后</th>
		<th>空闲</th>
		</tr>
		</thead>
		<tbody>
		<tr>
		<td>时长</td>
		<td id="longtime1"></td>
		<td id="longtime2"></td>
		<td id="longtime3"></td>
		<td id="longtime4"></td>
		<td id="longtime5"></td>
		<td id="longtime6"></td>
		<td id="longtime7"></td> 
		</tr>
		<tr>
		<td>次数</td> 
		<td id="statusNum1"></td>
		<td id="statusNum2"></td>
		<td id="statusNum3"></td>
		<td id="statusNum4"></td>
		<td id="statusNum5"></td>
		<td id="statusNum6"></td>
		<td id="statusNum7"></td> 
		</tr>
		</tbody>
		</table>
	</script>


</body>
</html>
