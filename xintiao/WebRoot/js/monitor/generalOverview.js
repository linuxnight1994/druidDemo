var dataSaveArray = new Array();
var dataArrayTemp = new Array();
var refreshChartsCount = 0;
var dataObj;
var dataHwl = [ 0 ];// 话务量数组
var dataYuQi = [ 0 ];// 预期值
var dataFwsp = [ 0 ];// 服务水平
var dataSjFwsp = [ 0 ];// 实际服务水平
var option1 = {};

var ws = new WebSocket("ws://" + callcenter.webProject
		+ "/generalOverviewServer?generalOverviewServerWebSocketServer");
$(function() {
	chartInit();
	dingshiqi();
	var iindex = 0;
	ws.onopen = function() {
		// document.getElementById("chatlog").textContent += "websocket
		// connected. \n";
	};
	ws.onmessage = function(message) {
		iindex++;
		var dateGroupAllArray = new Array();
		dateGroupAllArray = openDataPackage(message.data);
		// dataSaveArray.push(dateGroupAllArray);
		foreachArray(dateGroupAllArray);
		// 接收完成 做完成确认
		sendMessageToServer();
	};
	ws.onclose = function() {
		// document.getElementById("chatlog").textContent += "websocket closed.
		// \n";
	}
});
// 遍历数组,展示数据
function foreachArray(dataGroup) {
	var begin = new Date();
	dataGroup.forEach(function(e) {
		dataHwl.length = [ 0 ];
		dataYuQi.length = [ 0 ];
		dataFwsp.length = [ 0 ];
		dataSjFwsp.length = [ 0 ];
		e.forEach(function(eo) {
			if (eo.length == 10) {// 上面半部分数据
				var dataType = eo[1];
				if (dataType == "ensemble") {// 总体
					$("#ensembleJxl").html(eo[2]);
					$("#ensembleJtl").html(eo[3] + "%");
					$("#ensemblefql").html(eo[4] + "%");
					$("#ensemblewaitphone").html(eo[5]);
					$("#ensemblewaittime").html(eo[6] + "s");
					$("#ensemblesetperson").html(eo[7]);
					$("#ensembleputinperson").html(eo[8]);
					$("#ensemblefwsp").html(eo[9] + "%");
				} else if (dataType == "highend") {// 高端
					$("#highendJxl").html(eo[2]);
					$("#highendJtl").html(eo[3]);
					$("#highendfql").html(eo[4]);
					$("#highendwaitphone").html(eo[5]);
					$("#highendwaittime").html(eo[6]);
					$("#highendsetperson").html(eo[7]);
					$("#highendputinperson").html(eo[8]);
					$("#highendfwsp").html(eo[9]);
				} else if (dataType == "platinum") {// 白金
					$("#platinumJxl").html(eo[2]);
					$("#platinumJtl").html(eo[3]);
					$("#platinumfql").html(eo[4]);
					$("#platinumwaitphone").html(eo[5]);
					$("#platinumwaittime").html(eo[6]);
					$("#platinumsetperson").html(eo[7]);
					$("#platinumputinperson").html(eo[8]);
					$("#platinumfwsp").html(eo[9]);
				} else if (dataType == "putin") {// 普金
					$("#putinJxl").html(eo[2]);
					$("#putinJtl").html(eo[3]);
					$("#putinfql").html(eo[4]);
					$("#putinwaitphone").html(eo[5]);
					$("#putinwaittime").html(eo[6]);
					$("#putinsetperson").html(eo[7]);
					$("#putinputinperson").html(eo[8]);
					$("#putinfwsp").html(eo[9]);
				}
			} else if (eo.length == 4) {// 下面图标数据
				dataHwl.push(eo[0]);
				dataFwsp.push(eo[1]);
				dataSjFwsp.push(eo[2]);
				dataYuQi.push(eo[3]);
			}
		})
		// $("#graphic").charts("bar", option1);
		// refreshCharts();
	})
	var end = new Date();
	// alert(end.getTime()-begin.getTime())
}
function sendMessageToServer() {
	// 服务端发送消息客户端接受完做消息确认
	ws.send("dataGetEndOk");
}
function dingshiqi() {
	// 每隔30秒做一次数组清空操作
	window.setInterval("arrayInit()", 5000);
}
function arrayInit() {
	// $("#graphic").charts("bar", option1);
	refreshChartsCount++;
	if (refreshChartsCount > 48) {
		refreshChartsCount = 0;
		dataHwl.length = [ 0 ];
		dataYuQi.length = [ 0 ];
		dataFwsp.length = [ 0 ];
		dataSjFwsp.length = [ 0 ];
		$("#graphic").charts("bar", option1, function(ec) {
			dataObj = ec;
		});
	} else {
		refreshCharts();
	}
}
function refreshCharts() {
	dataObj.setOption({
		series : [ {
			data : dataHwl
		}, {
			data : dataFwsp
		}, {
			data : dataSjFwsp
		}, {
			data : dataYuQi
		} ]
	});
	dataObj.refresh();
}
function chartInit() {
	var data1 = [ 82.0, 24.9, 17.0, 23.2, 5.6, 76.7, 12.0, 42.9, 12.0, 23.2,
			5.6, 11.7, 82.0, 24.9, 17.0, 23.2, 5.6, 76.7, 12.0, 42.9, 12.0,
			23.2, 5.6, 11.7, 82.0, 24.9, 17.0, 23.2, 5.6, 76.7, 12.0, 42.9,
			12.0, 23.2, 5.6, 11.7, 82.0, 24.9, 17.0, 23.2, 5.6, 76.7, 12.0,
			42.9, 12.0, 23.2, 5.6, 11.7 ];
	// 初始化图表
	$(document).ready(function() {
		var con_W = $(".spe_backcolor").width();
		$('.overviews').css('width', (con_W - 16 - 70) / 8);
		$(window).resize(function() {
			var con_W = $(".spe_backcolor").width();
			$('.overviews').css('width', (con_W - 16 - 70) / 8);
		})
	});

	option1 = {
		tooltip : {
			trigger : 'axis'
		},
		toolbox : {
			show : false
		},
		calculable : true,
		legend : {
			data : [ '话务量', '服务水平', '实际服务水平' ]
		},
		xAxis : [ {
			type : 'category',
			data : [ '00:00', '00:30', '01:00', '01:30', '02:00', '02:30',
					'03:00', '03:30', '04:00', '04:30', '05:00', '05:30',
					'06:00', '06:30', '07:00', '07:30', '08:00', '08:30',
					'09:00', '09:30', '10:00', '10:30', '11:00', '11:30',
					'12:00', '12:30', '13:00', '13:30', '14:00', '14:30',
					'15:00', '15:30', '16:00', '16:30', '17:00', '17:30',
					'18:00', '18:30', '19:00', '19:30', '20:00', '20:30',
					'21:00', '21:30', '22:00', '22:30', '23:00', '23:30' ]
		} ],
		yAxis : [ {
			type : 'value',
			name : '话务量',
			axisLabel : {
				formatter : '{value} '
			}
		}, {
			type : 'value',
			name : '服务水平',
			axisLabel : {
				formatter : '{value} '
			}
		} ],
		series : [ {
			name : '话务量',
			type : 'bar',
			data : dataHwl,
		}, {
			name : '服务水平',
			type : 'bar',
			data : dataFwsp, 
		}, {
			name : '实际服务水平',
			type : 'line',
			yAxisIndex : 1,
			data : dataSjFwsp, 
		}, {
			name : '预期值',
			type : 'line',
			style : 'color:#f00',
			yAxisIndex : 1,
			data : dataYuQi, 
		}

		]
	}; 
	$("#graphic").charts("bar", option1, function(ec) {
		dataObj = ec;
	});
}