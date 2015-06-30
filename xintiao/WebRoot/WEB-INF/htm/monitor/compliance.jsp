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
<title>遵时监控</title>
<link href="<%=path%>/plugIn/css/UtryJsLib.all.css" type="text/css"
	rel="stylesheet">
<link href="<%=path%>/plugIn/jquery-ui/jquery-ui.css" type="text/css"
	rel="stylesheet">
<link href="<%=path%>/css/UtryUI.css" type="text/css" rel="stylesheet">
<link href="<%=path%>/css/style.css" type="text/css" rel="stylesheet">
<script type="text/javascript" src="<%=path%>/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript"
	src="<%=path%>/plugIn/jquery-ui/jquery-ui.all.js"></script>
<script type="text/javascript"
	src="<%=path%>/plugIn/js/UtryJsLib.all.js"></script>
<script type="text/javascript"
	src="<%=path%>/plugIn/js/UtryJsLib.all.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/monitor/compliance.js"></script>
</head>
<script type="text/javascript"
	src="<%=path%>/js/monitor/clientDataAction.js"></script>
</head>


<body>
	<div class="container-fluid layout-box main-box">
		<div class="row-fluid" style="height:100%;">
			<div class="span2 layout-box box-border">
				<div class="row-fluid layout-center overflow_yout">
					<input type="text" class="input input_medium float_l"
						style="margin:10px;" placeholder="请输入姓名或工号" />
					<div class="clear" id="div_tree_box" style="margin:10px"></div>
				</div>
			</div>
			<div class="span10 layout-box">
				<div class="row-fluid layout-top spe_backcolor" style="height:38px;">
					<input type="button" value="查询" class="btn btn-primary float_r" />
					<input type="text" class="calendar calendar_medium float_r mr10"
						id="defaultTime" name="defaultTime" placeholder="请选择日期"
						readonly="readonly" />
				</div>
				<div class="row-fluid layout-center box-border">
					<div class="layout-box">
						<div class="row-fluid layout-top"
							style="height:30px; padding-right:17px; background:#f7f7f7; border-bottom:1px solid #d7d7d7;">
							<table class="table">
								<thead>
									<tr>
										<th width="5%">工号</th>
										<th>姓名</th>
										<th width="5%">班次</th>
										<th width="5%">异常</th>
										<th class="chart_th"
											style="overflow:visible; position:relative;">
											<div class="th_div_con">
												<div class="time_div_con" style="margin-left:-480px;">
													<div class="th_clock">00:00</div>
													<div class="th_clock">01:00</div>
													<div class="th_clock">02:00</div>
													<div class="th_clock">03:00</div>
													<div class="th_clock">04:00</div>
													<div class="th_clock">05:00</div>
													<div class="th_clock">06:00</div>
													<div class="th_clock">07:00</div>
													<div class="th_clock">08:00</div>
													<div class="th_clock">09:00</div>
													<div class="th_clock">10:00</div>
													<div class="th_clock">11:00</div>
													<div class="th_clock">12:00</div>
													<div class="th_clock">13:00</div>
													<div class="th_clock">14:00</div>
													<div class="th_clock">15:00</div>
													<div class="th_clock">16:00</div>
													<div class="th_clock">17:00</div>
													<div class="th_clock">18:00</div>
													<div class="th_clock">19:00</div>
													<div class="th_clock">20:00</div>
													<div class="th_clock">21:00</div>
													<div class="th_clock">22:00</div>
													<div class="th_clock">23:00</div>
												</div>
											</div>
										</th>
										<th class="chart_th"></th>
										<th class="chart_th"></th>
										<th class="chart_th"></th>
										<th class="chart_th"></th>
										<th class="chart_th"></th>
										<th class="chart_th"></th>
										<th class="chart_th"></th>
										<th class="chart_th"></th>
										<th class="chart_th"></th>
										<th class="chart_th"></th>
										<th class="chart_th"></th>
										<th class="chart_th"></th>
										<th class="chart_th"></th>
									</tr>
								</thead>
							</table>
						</div>
						<div class="row-fluid layout-center scroll_y spe_height_td">
							<div class="modal_scroll">
								<div class="modal_div"></div>
							</div>
							<div id="ul_box" class="showbox">
								<table class="table tableClass">
									<tbody fm-body>
										<tr id='{{sno}}'>
											<td width="5%">{{sno}}</td>
											<td>{{sname}}</td>
											<td width="5%">{{classNo}}</td>
											<td width="5%">{{excptionInfo}}</td>
											<td class="chart_td"
												style="overflow:visible; position:relative;">
												<div class="tr_div_con">
													<div class="bg_color_con" style="margin-left:-480px;">
														<div class="overtime_con"></div>
														<div class="service_con"></div>
														<!-- <div class="overtime"></div> -->
														<!-- <div class="service"></div> -->
														<div class="eatting_time_con">
															<!-- 	<div class="eatting_time" style="left:50px;"></div> -->
														</div>
													</div>
													<div class="ar_color_con" style="margin-left:-480px;">
														<div class="ac_service_con"></div>
													</div>
												</div>
												<div class="nowTime_line"></div>
											</td>
											<td class="chart_td"></td>
											<td class="chart_td"></td>
											<td class="chart_td"></td>
											<td class="chart_td"></td>
											<td class="chart_td"></td>
											<td class="chart_td"></td>
											<td class="chart_td"></td>
											<td class="chart_td"></td>
											<td class="chart_td"></td>
											<td class="chart_td"></td>
											<td class="chart_td"></td>
											<td class="chart_td"></td>
											<td class="chart_td"></td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
						<div class="row-fluid layout-bottom" style="height:35px;">
							<div fm-pagerbox class="tfoot"></div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<script type="text/template" id="">
    </script>

	<script>
		var dateTime = new Date();
		var hh = dateTime.getHours();//小时
		if (hh < 10) {
			hh = "0" + hh;
		}
		var mm = dateTime.getMinutes();//分钟
		if (mm < 10) {
			mm = "0" + mm;
		}
		var ss = dateTime.getSeconds();//秒钟
		var yy = dateTime.getFullYear();//年份
		var MM = dateTime.getMonth() + 1; //月份-因为1月这个方法返回为0，所以加1
		if (MM < 10) {
			MM = "0" + MM;
		}
		var dd = dateTime.getDate();//日数
		if (dd < 10) {
			dd = "0" + MM;
		}
		var now_time = yy + "-" + MM + "-" + dd;//当前日期
		var now_mm = hh + ":" + mm;//当前钟点
		$('.nowTime_line').attr("title", now_mm);

		//时间转成距离并赋值给线

		$(document)
				.ready(
						function() {
							//给钟点线赋值高度
							var layoutCenterH = $('.spe_height_td').height();
							$('.nowTime_line').height(layoutCenterH);

							$('#defaultTime').datepicker({
								onClose : function(param) {
									if (now_time == param) {
										$('.nowTime_line').show();
									} else {
										$('.nowTime_line').hide();
									}
								}
							});

							//左边人的树
							var data = [ {
								Id : 1,
								Icon : "",
								Name : "张三",
								Child : null
							}, {
								Id : 2,
								Name : "李思",
								Child : [ {
									Id : 21,
									Name : "张三",
									Child : null
								}, {
									Id : 22,
									Name : "李思",
									Child : [ {
										Id : 221,
										Name : "王五",
										Child : null
									} ]
								}, {
									Id : 2200,
									Name : "李思",
									Child : [ {
										Id : 22100,
										Name : "王五",
										Child : null
									} ]
								}, {
									Id : 3,
									Name : "王五",
									Child : null
								} ]
							}, {
								Id : 31,
								Name : "王五",
								Child : [ {
									Id : 311,
									Name : "张三",
									Child : null
								}, {
									Id : 312,
									Name : "李思",
									Child : null
								}, {
									Id : 313,
									Name : "王五",
									Icon : "",
									Child : [ {
										Id : 3131,
										Name : "王五",
										Child : null
									}, {
										Id : 3131,
										Name : "王五1",
										Child : null
									} ]
								} ]
							} ];
							var app = $("#div_tree_box").Tree(
									{
										data : data,
										expand : true,
										key : "Id",
										text : "Name",
										selected : function(data) {
											$("#div_show").html(
													"id:" + data.key
															+ " 父节点id:"
															+ data.pid);
										}//选中节点事件
									});
							$('.modal_scroll').scrollLeft(10);

							//模拟滚动条的位置
							$('.modal_scroll')
									.scroll(
											function() {
												var GetLeft = $('.modal_scroll')
														.scrollLeft();
												//alert(GetLeft);
												$('.time_div_con')
														.css('margin-left',
																-GetLeft);
												$('.bg_color_con')
														.css('margin-left',
																-GetLeft);
												$('.ar_color_con')
														.css('margin-left',
																-GetLeft);
												//hh= 1;
												var now_hh = ((hh * 3600 + mm
														* 60 + ss) - GetLeft * 60) / 60;
												var dateJss1 = dateJss;
												if (dateJss1 > 0) {
													var hhJs = dateJss
															.getHours();
													var mmJs = dateJss
															.getMinutes(); //获取当前分钟数(0-59)
													var ssJs = dateJss
															.getSeconds();
													now_hh = ((hhJs * 3600
															+ mmJs * 60 + ssJs) - GetLeft * 60) / 60;
												}

												if (now_hh < 0) {
													$('.nowTime_line').hide();
												} else {
													$('.nowTime_line').show();
													$('.nowTime_line').css(
															"left", now_hh);
												}
											})
						});
	</script>
</body>
</html>
