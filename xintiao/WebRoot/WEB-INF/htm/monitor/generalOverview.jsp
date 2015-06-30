<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String webProject = request.getServerName() + ":"
			+ request.getServerPort() + path ;
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
<title>总体概况</title>
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
<script type="text/javascript" src="<%=path%>/plugIn/charts/esl.js"></script>
<script type="text/javascript"
	src="<%=path%>/js/monitor/generalOverview.js"></script>
<script type="text/javascript"
	src="<%=path%>/js/monitor/clientDataAction.js"></script>
</head>

<body>
	<div class="container-fluid layout-box main-box">
		<div class="row-fuild layout-top spe_backcolor" style="height:102px;">
			<div class="overviews box-border">
				<div class="overviews_l">
					<p class="p1">进线量</p>
					<p class="p2" id="ensembleJxl">0</p>
				</div>
				<div class="overviews_r">
					<p class="p3">
						高端<span class="ml10" id="highendJxl">0</span>
					</p>
					<p class="p4">
						白金<span class="ml10" id="platinumJxl">0</span>
					</p>
					<p class="p5">
						普金<span class="ml10" id="putinJxl">0</span>
					</p>
				</div>
			</div>

			<div class="overviews box-border ml10">
				<div class="overviews_l">
					<p class="p1">接通率</p>
					<p class="p2" id="ensembleJtl">0</p>
				</div>
				<div class="overviews_r">
					<p class="p3">
						高端<span class="ml10"  id="highendJtl">0</span>%
					</p>
					<p class="p4">
						白金<span class="ml10"  id="platinumJtl">0</span>%
					</p>
					<p class="p5">
						普金<span class="ml10"  id="putinJtl">0</span>%
					</p>
				</div>
			</div>

			<div class="overviews box-border ml10">
				<div class="overviews_l">
					<p class="p1">放弃量</p>
					<p class="p2" id="ensemblefql">0</p>
				</div>
				<div class="overviews_r">
					<p class="p3">
						高端<span class="ml10" id="highendfql">0</span>%
					</p>
					<p class="p4">
						白金<span class="ml10" id="platinumfql">0</span>%
					</p>
					<p class="p5">
						普金<span class="ml10" id="putinfql">0</span>%
					</p>
				</div>
			</div>

			<div class="overviews box-border ml10">
				<div class="overviews_l">
					<p class="p1">等待电话</p>
					<p class="p2 not_normal" id="ensemblewaitphone">0</span>
				</div>
				<div class="overviews_r">
					<p class="p3">
						高端<span class="ml10" id="highendwaitphone">0</span>
					</p>
					<p class="p4">
						白金<span class="ml10" id="platinumwaitphone">0</span>
					</p>
					<p class="p5">
						普金<span class="ml10" id="putinwaitphone">0</span>
					</p>
				</div>
			</div>
			<div class="overviews box-border ml10">
				<div class="overviews_l">
					<p class="p1">等待时间</p>
					<p class="p2 not_normal" id="ensemblewaittime">0s</p>
				</div>
				<div class="overviews_r">
					<p class="p3">
						高端<span class="ml10" id="highendwaittime">0</span>s
					</p>
					<p class="p4">
						白金<span class="ml10" id="platinumwaittime">0</span>s
					</p>
					<p class="p5">
						普金<span class="ml10" id="putinwaittime">0</span>s
					</p>
				</div>
			</div>
			<div class="overviews box-border ml10">
				<div class="overviews_l">
					<p class="p1">安排人数</p>
					<p class="p2" id="ensemblesetperson">0</p>
				</div>
				<div class="overviews_r">
					<p class="p3">
						高端<span class="ml10" id="highendsetperson">0</span>
					</p>
					<p class="p4">
						白金<span class="ml10" id="platinumsetperson">0</span>
					</p>
					<p class="p5">
						普金<span class="ml10" id="putinsetperson">0</span>
					</p>
				</div>
			</div>
			<div class="overviews box-border ml10">
				<div class="overviews_l">
					<p class="p1">签入人数</p>
					<p class="p2" id="ensembleputinperson">0</p>
				</div>
				<div class="overviews_r">
					<p class="p3">
						高端<span class="ml10" id="highendputinperson">0</span>
					</p>
					<p class="p4">
						白金<span class="ml10" id="platinumputinperson">0</span>
					</p>
					<p class="p5">
						普金<span class="ml10" id="putinputinperson">0</span>
					</p>
				</div>
			</div>
			<div class="overviews box-border ml10">
				<div class="overviews_l">
					<p class="p1">服务水平</p>
					<p class="p2" id="ensemblefwsp">0%</p>
				</div>
				<div class="overviews_r">
					<p class="p3">
						高端<span class="ml10" id="highendfwsp">0</span>%
					</p>
					<p class="p4">
						白金<span class="ml10" id="platinumfwsp">0</span>%
					</p>
					<p class="p5">
						普金<span class="ml10" id="putinfwsp">0</span>%
					</p>
				</div>
			</div>
		</div>
		<div class="row-fluid layout-center box-border mt10">
			<div id="graphic" style="height:100%;"></div>
		</div>
	</div>
	<script>
	$.init({
		'echarts' : '<%=path%>/plugIn/charts/echarts',
		'echarts/chart/bar' : '<%=path%>/plugIn/charts/echarts-map',
		'echarts/chart/line' : '<%=path%>/plugIn/charts/echarts-map',
		'echarts/chart/pie' : '<%=path%>/plugIn/charts/echarts-map',
		'echarts/chart/radar' : '<%=path%>/plugIn/charts/echarts-map'
	});
	</script>
</body>
</html>