/*var dataSaveArray = new Array();
var dataArrayTemp = new Array();*/
//var finalDataArray = new Array();
var flowGridApp = null;
var tree;
var getTreeSelectArray = new Array();
var exhaleCount = 0;
var dialoutCount = 0;
var axuCount = 0;
var eatCount = 0;
var littlerestCount = 0;
var afterwardsCount = 0;
var idleCount = 0;
var buttunClickArray = new Array();
var dataServerArray = [];
var sitr = 0;
var ws = new WebSocket("ws://" + callcenter.webProject
		+ "/stateMonitoringServer?stateMonitoringServerWebSocketServer");
$(function() {
	tiaojianClick();
	initTree();
	ws.onopen = function() {
		sitr++;
		var aa = 0;
	};
	ws.onmessage = function(message) {
		dataServerArray.length = 0;
		var messagedata = message.data;
		var dataServerArrayPackage = openDataPackage(message.data);
		dataServerArrayPackage.forEach(function(e) {
			exhaleCount = 0;
			dialoutCount = 0;
			axuCount = 0;
			eatCount = 0;
			littlerestCount = 0;
			afterwardsCount = 0;
			idleCount = 0;
			e.forEach(function(ex) {
				var str = "{" + ex + "}";
				// 获取数组中不同状态的数量
				var statusName = ex[7];
				var isRed = ex[8];
				if (statusName.indexOf("exhale") > -1) {
					exhaleCount++;
				} else if (statusName.indexOf("dialout") > -1) {
					dialoutCount++;
				} else if (statusName.indexOf("axu") > -1) {
					axuCount++;
				} else if (statusName.indexOf("eat") > -1) {
					eatCount++;
				} else if (statusName.indexOf("littlerest") > -1) {
					littlerestCount++;
				} else if (statusName.indexOf("afterwards") > -1) {
					afterwardsCount++;
				} else if (statusName.indexOf("idle") > -1) {
					idleCount++;
				}
				var jsonText = eval('(' + str + ')');
				dataServerArray.push(jsonText);
			})

			$('.time_state').addClass('state_timewrong');
			$("#exhaleCount").html(exhaleCount);
			$("#dialoutCount").html(dialoutCount);
			$("#axuCount").html(axuCount);
			$("#eatCount").html(eatCount);
			$("#littlerestCount").html(littlerestCount);
			$("#afterwardsCount").html(afterwardsCount);
			$("#idleCount").html(idleCount);
			getNewData(buttunClickArray, dataServerArray, getTreeSelectArray);
		})
	};
	ws.onclose = function() {
	}
});
/**
 * dataServerArray:后端推出数据 buttunClickArray:按钮data数组保存
 */
function getNewData(buttunClickArray, dataServerArray, getTreeSelectArray) {
	var newDataServerArray = new Array();
	var newDataServerArrayTemp = new Array();
	if (buttunClickArray.length == 0 && dataServerArray.length > 0) {
		// 再根据人员筛选
		if (getTreeSelectArray.length > 0) {
			dataServerArray.forEach(function(ex) {
				var statusVar = ex.sno;
				for (var ik = 0; ik < getTreeSelectArray.length; ik++) {
					if (statusVar == getTreeSelectArray[ik].key) {
						newDataServerArrayTemp.push(ex);
					}
				}
			})
			loadData(newDataServerArrayTemp);
		} else {
			loadData(dataServerArray);
		}
	} else {
		var newDataServerArrayElseTemp = new Array();
		dataServerArray.forEach(function(ex) {
			var statusVar = ex.status;
			for (var ik = 0; ik <= buttunClickArray.length; ik++) {
				if (statusVar == buttunClickArray[ik]) {
					newDataServerArray.push(ex);
				}
			}
		})
		if (getTreeSelectArray.length > 0) {
			newDataServerArray.forEach(function(ex) {
				var statusVar = ex.sno;
				for (var ik = 0; ik < getTreeSelectArray.length; ik++) {
					if (statusVar == getTreeSelectArray[ik].key) {
						newDataServerArrayElseTemp.push(ex);
					}
				}
			})
			loadData(newDataServerArrayElseTemp);
		} else {
			loadData(newDataServerArray);
		}
	}
}

function rowOnclick(idName, idsnoc, idClassnoc, idGroupId) {
	// 行的单击事件
	var snoc = idsnoc;
	var sidName = idName;
	var sidClassnoc = idClassnoc;
	var sidGroupId = idGroupId;
	var params = {};
	params.snoc = snoc;
	params.sidName = sidName;
	params.sidClassnoc = sidClassnoc;
	params.sidGroupId = sidGroupId;
	var url = "showDetailById.htm";
	$.ajax({
		type : "POST",
		url : url,
		contentType : "application/json;charset=UTF-8", // 发送信息至服务器时内容编码类型。
		data : JSON.stringify(params),
		success : function(data) {
			var paramsSno = data.paramsSno;
			var sidName = data.sidName;
			var classNo = data.classNo;
			var sidGroupId = data.sidGroupId;
			var stateMonitoringDtosArray = data.stateMonitoringDtos;
			$("#templatedome1").modal({
				title : "详细展示",
				buttons : [ {
					text : "确定",
					click : function() {
						$(this).close()
					}
				}, {
					text : "关闭",
					click : function() {
						$(this).close()
					}
				} ]
			});
			$("#userName").html(sidName);
			$("#userSno").html(paramsSno);
			$("#userClassno").html(classNo);
			$("#userGroup").html(sidGroupId);
			stateMonitoringDtosArray.forEach(function(px) {
				var statusVal = px.actStatus;
				var totalDurationVal = px.totalDuration;// 时长
				var statusNumVal = px.statusNum;// 次数
				if ("1" == statusVal) {
					$("#longtime" + "1").html(totalDurationVal);
					$("#statusNum" + "1").html(statusNumVal);
				} else if ("2" == statusVal) {
					$("#longtime" + "2").html(totalDurationVal);
					$("#statusNum" + "2").html(statusNumVal);
				} else if ("3" == statusVal) {
					$("#longtime" + "3").html(totalDurationVal);
					$("#statusNum" + "3").html(statusNumVal);
				} else if ("4" == statusVal) {
					$("#longtime" + "4").html(totalDurationVal);
					$("#statusNum" + "4").html(statusNumVal);
				} else if ("5" == statusVal) {
					$("#longtime" + "5").html(totalDurationVal);
					$("#statusNum" + "5").html(statusNumVal);
				} else if ("6" == statusVal) {
					$("#longtime" + "6").html(totalDurationVal);
					$("#statusNum" + "6").html(statusNumVal);
				} else if ("7" == statusVal) {
					$("#longtime" + "7").html(totalDurationVal);
					$("#statusNum" + "7").html(statusNumVal);
				}

			})
		}
	});
}

function loadData(finalDataArray) {
	flowGridApp = $("#ul_box").Grid({
		data : finalDataArray
	});
}
// 状态时长超过阈值红色显示
function converageAddRed(isredStr) {
	var ss = isredStr.split(",");
	if (ss[0] == 1) {
		return "<span class='state_timewrong'>" + ss[1] + "</span>";
	} else {
		return "<span>" + ss[1] + "</span>";
	}
}
function converageStatus(statusToChina) {
	if ("exhale" == statusToChina) {
		statusToChina = "呼出";
	} else if ("dialout" == statusToChina) {
		statusToChina = "外拨";
	} else if ("axu" == statusToChina) {
		statusToChina = "AXU外拨";
	} else if ("eat" == statusToChina) {
		statusToChina = "就餐";
	} else if ("littlerest" == statusToChina) {
		statusToChina = "小休";
	} else if ("afterwards" == statusToChina) {
		statusToChina = "事后";
	} else if ("idle" == statusToChina) {
		statusToChina = "空闲";
	}
	return statusToChina;
}
function sendMessageToServer() {
	// 服务端发送消息客户端接受完做消息确认
	ws.send("dataGetEndOk");
}
function buttunClickArrayadd(actionStr) {
	buttunClickArray.push(actionStr);
}
function buttunClickArrayRemove(actionStr) {
	var index = buttunClickArray.indexOf(actionStr);
	if (index > -1) {
		buttunClickArray.splice(index, 1);
	}
}

function tiaojianClick() {
	// 人员树点击
	$('#div_tree_box').click(function() {
		getTreeSelectArray = tree.getSelected();
		// alert(getSelectVar);
	})
	$('#returnButton').click(function() {
		buttunClickArray.length = 0;
	})
	$('#exhale').click(function() {
		var action = "exhale";
		if ($(this).hasClass('state_checked')) {// 删除
			buttunClickArrayRemove(action);
		} else {
			buttunClickArrayadd(action);// 添加
		}
	})
	$('#dialout').click(function() {
		var action = "dialout";
		if ($(this).hasClass('state_checked')) {// 删除
			buttunClickArrayRemove(action);
		} else {
			buttunClickArrayadd(action);// 添加
		}
	})
	$('#axu').click(function() {
		var action = "axu";
		if ($(this).hasClass('state_checked')) {// 删除
			buttunClickArrayRemove(action);
		} else {
			buttunClickArrayadd(action);// 添加
		}
	})
	$('#eat').click(function() {
		var action = "eat";
		if ($(this).hasClass('state_checked')) {// 删除
			buttunClickArrayRemove(action);
		} else {
			buttunClickArrayadd(action);// 添加
		}
	})
	$('#littlerest').click(function() {
		var action = "littlerest";
		if ($(this).hasClass('state_checked')) {// 删除
			buttunClickArrayRemove(action);
		} else {
			buttunClickArrayadd(action);// 添加
		}
	})
	$('#afterwards').click(function() {
		var action = "afterwards";
		if ($(this).hasClass('state_checked')) {// 删除
			buttunClickArrayRemove(action);
		} else {
			buttunClickArrayadd(action);// 添加
		}
	})
	$('#idle').click(function() {
		var action = "idle";
		if ($(this).hasClass('state_checked')) {// 删除
			buttunClickArrayRemove(action);
		} else {
			buttunClickArrayadd(action);// 添加
		}
	})
}
function initTree() {
	tree = $("#div_tree_box").Tree({
		url : callcenter.basePath + "personTree/treedata.htm",
		expand : true,
		child : "child",
		multiple : true,
		treeModel : "checkbox",
		key : "id",
		text : "name",
		clazz : 1
	});
	return tree;
}

function openClent() {
	// ws.send("reFreshSession");
}