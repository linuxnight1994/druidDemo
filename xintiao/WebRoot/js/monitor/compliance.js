var currentDate = "";
var jk = 0;
var jkk = 0;
var parmVlaue1 = 15;
var parmVlaue2 = 1;
var diseCount1 = 0;
var dateJss = 0;
var dataAllArray = new Array();// 遵时数据不清空
var initStatusArray = new Array();
var initDateAarrayAll = new Array();
var clanHuaWuArray = new Array();
var clanJiaBanArray = new Array();
var clanJiuCanWuArray = new Array();
var newDataServerArray = [];
var ws = new WebSocket("ws://" + callcenter.webProject
		+ "/complinanceServerServer?complinanceServerWebSocketServer");
$(function() {
	dingshiqi();
	var iindex = 0;
	ws.onopen = function() {
	};
	ws.onmessage = function(message) {
		iindex++;
		var dateGroupAllArray = new Array();
		dateGroupAllArray.length = 0;
		dateGroupAllArray = openDataPackage(message.data);
		// dataSaveArray.push(dateGroupAllArray);
		foreachArray(dateGroupAllArray);
		// 接收完成 做完成确认
		sendMessageToServer();
	};
	ws.onclose = function() {
	}
});
// 遍历数组,展示数据
function foreachArray(dataGroup) {
	// 状态数据
	initStatusArray.length = 0;
	dataGroup.forEach(function(e) {
		e.forEach(function(eo) {
			if (eo.length == 2) {// 时间线
				var dateJs = eo[0];
				/* alert(dateJs.getMonth()); */
				dateJss = new Date(parseInt(dateJs));
				var hh = dateJss.getHours();
				var mm = dateJss.getMinutes(); // 获取当前分钟数(0-59)
				var ss = dateJss.getSeconds();
				var GetLeft = $('.modal_scroll').scrollLeft();
				var now_hh = ((hh * 3600 + mm * 60 + ss) - GetLeft * 60) / 60;
				if (now_hh < 0) {
					$('.nowTime_line').hide();
				} else {
					$('.nowTime_line').show();
					$('.nowTime_line').css("left", now_hh);
				}
			} else if (eo.length == 7) {
				// 安排加班，话务，就餐等数据,只加载一次
				var thisObj = new Object();
				thisObj.sno = eo[0];
				thisObj.sname = eo[1];
				thisObj.classNo = eo[2];
				thisObj.excptionInfo = eo[3];
				thisObj.descOne = eo[4];// 安排话务
				thisObj.overtimeTimeDescOne = eo[5];// 安排加班
				thisObj.overEateDescOne = eo[6];// 安排就餐
				newDataServerArray.push(thisObj);
			} else {
				// 状态数据
				initStatusArray.push(eo);
			}
		})
	})
	// 方法执行完成，筛选数据
	initDateAarrayAll = new Array();
	for (var ei = 0; ei < newDataServerArray.length; ei++) {
		var eiObj = newDataServerArray[ei];
		var eiObjSno = eiObj.sno;
		var eiObjclassNo = eiObj.classNo;
		var eiObjdescOne = eiObj.descOne;
		var eiObjexcptionInfo = eiObj.excptionInfo;
		var eiObjoverEateDescOne = eiObj.overEateDescOne;
		var eiObjovertimeTimeDescOne = eiObj.overtimeTimeDescOne;
		var eiObjsname = eiObj.sname;
		for (var ej = 0; ej < initStatusArray.length; ej++) {
			var ejObjVal = initStatusArray[ej];
			var ejObjValSno = ejObjVal[0];
			var ejObjValstatus = ejObjVal[1];
			var ejObjValtimelong = ejObjVal[2];
			var ejObjValbeginDate = ejObjVal[3];
			// 如果基础信息sno和状态关联sno相等
			if (eiObjSno == ejObjValSno) {
				var thisFinalObj = new Object();
				thisFinalObj.eiObjSno = eiObjSno;
				thisFinalObj.eiObjclassNo = eiObjclassNo;
				thisFinalObj.eiObjdescOne = eiObjdescOne;
				thisFinalObj.eiObjexcptionInfo = eiObjexcptionInfo;
				thisFinalObj.eiObjoverEateDescOne = eiObjoverEateDescOne;
				thisFinalObj.eiObjovertimeTimeDescOne = eiObjovertimeTimeDescOne;
				thisFinalObj.eiObjsname = eiObjsname;
				thisFinalObj.ejObjValstatus = ejObjValstatus;
				thisFinalObj.ejObjValtimelong = ejObjValtimelong;
				thisFinalObj.ejObjValbeginDate = ejObjValbeginDate;
				initDateAarrayAll.push(thisFinalObj);
			}
		}
	}

	if (diseCount1 < 1) {
		$("#ul_box").Grid({
			data : newDataServerArray
		});
		setInitData();// 循环结束初始化背景色数据
		initStatus();
	} else {
		// 更新浮动层
		setDataFunction();
	}
	diseCount1++;
}
function setDataFunction() {
	var dataAllArrayIndex=0;
	dataAllArray
			.forEach(function(dxa) {
				var alleiObjSno = dxa.eiObjSno;
				var alleiObjclassNo = dxa.eiObjclassNo;
				var allejObjValbeginDate = dxa.ejObjValbeginDate;
				var allejObjValstatus = dxa.ejObjValstatus;
				var allejObjValtimelong = dxa.ejObjValtimelong;
				if (null != initStatusArray && initStatusArray.length > 0) {
					for (var ai = 0; ai < initStatusArray.length; ai++) {
						var statusObj = initStatusArray[ai];
						var statuseiObjSno = statusObj[0];
						if (alleiObjSno == statuseiObjSno) {
							var statusCurry = statusObj[1];
							var statustimelong = statusObj[2];
							var statusBeginDate = statusObj[3];
							if (allejObjValstatus == statusCurry) {// 状态不改变
								// alert("ccc");
								var tr = $(".tableClass").find(
										"#" + statuseiObjSno);
								var div = tr.find('.ar_color_con');
								var divtwo = div.find('.ac_service_con');
								// 状态未改变，找到指定行设置新的数据

								if (alleiObjSno == "10011") {
									var ffff = 11;
								}
								if (alleiObjSno == "10012") {
									var ffff = 22;
								}
								var classStr = $(divtwo.children().last())
										.attr('class');
								if (null != classStr && classStr.length > 0) {
									var classStrArray = classStr.split(" ");
									var acseatingtang = classStrArray[(classStrArray.length - 1)];
									divtwo.children().last().css('left',
											statusBeginDate);
									divtwo.children().last().css('width',
											statustimelong);
								}
							} else {
								// 状态改变
								if ("10012" == statuseiObjSno) {
									var tr = $(".tableClass").find(
											"#" + statuseiObjSno);
									var div = tr.find('.ar_color_con');
									var divtwo = div.find('.ac_service_con');
									var myserviseVal = "";
									if (statusCurry == "status1") {// 如果是状态1ac_service_tang3
										myserviseVal = "ac_service_tang" + jk;
									} else {
										myserviseVal = "ac_eatting_time_tang"
												+ jk;
									}
									divtwo.append('<div class="ac_service '
											+ myserviseVal + '"></div>');
									divtwo.find('.ac_service_tang' + jk).css(
											'left', statusBeginDate);
									divtwo.find('.ac_service_tang' + jk).css(
											'width', statustimelong);
									jk++;
									var dxaTemp=dxa;
									dxaTemp.ejObjValstatus=statusCurry;
									dataAllArray.splice(dataAllArrayIndex,1);
									dataAllArrayIndex++;
									var cc = 11;
								}
							}
						}
					}
				}
			})
}
function initStatus() {
	for (var ai = 0; ai < initDateAarrayAll.length; ai++) {
		var statusObj = initDateAarrayAll[ai];
		var statusSno = statusObj.eiObjSno;
		var statusBeginDateInt = statusObj.ejObjValbeginDate;
		var statusCurry = statusObj.ejObjValstatus;
		var statustimelong = statusObj.ejObjValtimelong;
		var statusBeginDate = new Date(parseInt(statusBeginDateInt));
		var timebegin = statusBeginDateInt;
		var timeend = statustimelong * parmVlaue1 * parmVlaue2;
		var tr = $(".tableClass").find("#" + statusSno);
		var div = tr.find('.ar_color_con');
		var divtwo = div.find('.ac_service_con');

		if ("status0" == statusCurry) {
			var myserviseVal = "ac_service_tang" + jk;
			divtwo
					.append('<div class="ac_service ' + myserviseVal
							+ '"></div>');
			divtwo.find('.ac_service_tang' + jk).css('left', timebegin);
			divtwo.find('.ac_service_tang' + jk).css('width', timeend);
			jk++;
		} else if ("status1" == statusCurry || "status2" == statusCurry) {
			var myserviseVal = "ac_eatting_time_tang" + jkk;
			divtwo.append('<div class="ac_eatting_time ' + myserviseVal
					+ '"></div>');
			divtwo.find('.ac_eatting_time_tang' + jkk).css('left', timebegin);
			divtwo.find('.ac_eatting_time_tang' + jkk).css('width', timeend);
			/*
			 * var tr = $(".tableClass").find("#" + statusSno); var div =
			 * tr.find('.ar_color_con'); var divtwo =
			 * div.find('.ac_eatting_time_con'); var myserviseVal =
			 * "ac_eatting_time_tang" + jkk; divtwo.append('<div
			 * class="ac_eatting_time ' + myserviseVal + '"></div>');
			 * divtwo.find('.ac_eatting_time_tang' + jkk).css('left',
			 * timebegin); divtwo.find('.ac_eatting_time_tang' +
			 * jkk).css('width', timeend); jkk++;
			 */}
	}
	// 渲染后把数据追加到dataAllArray
	initDateAarrayAll.forEach(function(dxa) {
		dataAllArray.push(dxa);
	})
	// alert(dataAllArray.length);
}

function sendMessageToServer() {
	// 服务端发送消息客户端接受完做消息确认
	ws.send("dataGetEndOk");
}
function dingshiqi() {
	// 每隔30秒做一次date操作
	// window.setInterval("dateInit()", 10000);
}

function setInitData() {
	if (null != newDataServerArray && undefined != newDataServerArray
			&& "" != newDataServerArray && newDataServerArray.length > 0) {
		for (var kli = 0; kli < newDataServerArray.length; kli++) {
			var eiObjSnoString = newDataServerArray[kli].sno;
			var clanHuaWuArrayString = newDataServerArray[kli].descOne;
			var clanJiaBanArrayString = newDataServerArray[kli].overtimeTimeDescOne;
			var clanJiuCanWuArrayString = newDataServerArray[kli].overEateDescOne;
			var ik = 0;
			var ikk = 0;
			var ikkk = 0;
			clanHuaWuArray = clanHuaWuArrayString.split("*");
			clanHuaWuArray.forEach(function(ekx) {
				var valArrayNew = ekx.split(":");
				var indexone = valArrayNew[0];// 开始时间下标
				var beginIndex = indexone * parmVlaue1 * parmVlaue2;
				var indextwo = valArrayNew[1];
				var endIndex = indextwo * parmVlaue1 * parmVlaue2;
				var myserviseVal = "myservise" + ik;
				var tr = $(".tableClass").find("#" + eiObjSnoString);
				var div = tr.find('.bg_color_con');
				var divtwo = div.find('.service_con');
				divtwo.append('<div class="service ' + myserviseVal
						+ '"></div>');
				divtwo.find('.myservise' + ik).css('left', beginIndex);
				divtwo.find('.myservise' + ik).css('width', endIndex);
				ik++;
			})
			clanJiaBanArray = clanJiaBanArrayString.split("*");
			clanJiaBanArray.forEach(function(ekx) {
				var valArrayNew = ekx.split(":");
				var indexone = valArrayNew[0];// 开始时间下标
				var beginIndex = indexone * parmVlaue1 * parmVlaue2;
				var indextwo = valArrayNew[1];
				var endIndex = indextwo * parmVlaue1 * parmVlaue2;
				var myovertimeVal = "myovertime" + ikk;
				var tr = $(".tableClass").find("#" + eiObjSnoString);
				var div = tr.find('.bg_color_con');
				var divtwo = div.find('.overtime_con');
				divtwo.append('<div class="overtime ' + myovertimeVal
						+ '"></div>');
				divtwo.find('.myovertime' + ikk).css('left', beginIndex);
				divtwo.find('.myovertime' + ikk).css('width', endIndex);
				ikk++;
			})
			clanJiuCanWuArray = clanJiuCanWuArrayString.split("*");
			clanJiuCanWuArray.forEach(function(ekx) {
				var valArrayNew = ekx.split(":");
				var indexone = valArrayNew[0];// 开始时间下标
				var beginIndex = indexone * parmVlaue1 * parmVlaue2;
				var indextwo = valArrayNew[1];
				var endIndex = indextwo * parmVlaue1 * parmVlaue2;
				var myeattingtimeVal = "myeatting_time" + ikkk;
				var tr = $(".tableClass").find("#" + eiObjSnoString);
				var div = tr.find('.bg_color_con');
				var divtwo = div.find('.eatting_time_con');
				divtwo.append('<div class="eatting_time ' + myeattingtimeVal
						+ '"></div>');
				divtwo.find('.myeatting_time' + ikkk).css('left', beginIndex);
				divtwo.find('.myeatting_time' + ikkk).css('width', endIndex);
				ikkk++;
			})
		}
	}
}
