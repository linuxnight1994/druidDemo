package utry.psd.call.center.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import utry.psd.call.center.util.MakeDataUtil;
import utry.psd.call.center.websocket.bo.compliance.ComplianceCurrentTimeVo;
import utry.psd.call.center.websocket.bo.compliance.CompliancePlanVo;
import utry.psd.call.center.websocket.bo.compliance.ComplianceVo;

public class ComplianceDateCreate {
	// 模拟一个每次获取当前时间的数据
	public static List<Object> makeComplianceCurrentTimeVo() {
		List<Object> cctimes = new ArrayList<>();
		ComplianceCurrentTimeVo cctime1 = new ComplianceCurrentTimeVo();
		cctime1.setCurrentTime(new Date());
		ComplianceCurrentTimeVo cctime2 = new ComplianceCurrentTimeVo();
		cctime2.setCurrentTime(new Date());
		cctimes.add(cctime1);
		cctimes.add(cctime2);
		return cctimes;
	}

	// 模拟一个安排的数据BO
	public static List<Object> makeCompliancePlanVo() {
		List<Object> cctimes = new ArrayList<>();
		for (int i = 0; i < 8; i++) {
			CompliancePlanVo clct = new CompliancePlanVo();
			int ri = new Random().nextInt(10);
			clct.setSno("1001" + i);
			clct.setsName("name" + i);
			clct.setClassNo("cNo" + i);
			clct.setExcptionInfo("error" + i);
			String descOne = getMapString(null);// 安排话务
			String overtimeTimeDescOne = getMapString(null);// 安排加班
			String overEateDescOne = getMapString(null);// 安排就餐
			clct.setDescOne(descOne);
			clct.setOverEateDescOne(overEateDescOne);
			clct.setOvertimeTimeDescOne(overtimeTimeDescOne);
			cctimes.add(clct);
		}
		return cctimes;
	}

	// 模拟一个安排的数据BO
	public static List<Object> makeComplianceVoData(List<Object> list) {
		List<Object> cctimes = new ArrayList<>();
		if (null == list) {
			int statusCount = 8;
			for (int j = 0; j < statusCount; j++) {
				ComplianceVo clct = new ComplianceVo();
				clct.setSno("1001" + j);
				clct.setBeginTime(new Date());
				if (j == 2) {
					clct.setStatus("status" + "1");
				}else{
					clct.setStatus("status" + j);
				}
				clct.setLongTime(new Random().nextInt(10));
				cctimes.add(clct);
			}
		} else {
			for (int i = 0; i < list.size(); i++) {
				int statusCount = new Random().nextInt(4);
				CompliancePlanVo cvo = (CompliancePlanVo) list.get(i);
				for (int j = 0; j < statusCount; j++) {
					ComplianceVo clct = new ComplianceVo();
					clct.setSno(cvo.getSno());
					clct.setBeginTime(new Date());
					clct.setStatus("status" + j);
					clct.setLongTime(new Random().nextInt(10));
					cctimes.add(clct);
				}
			}
		}

		return cctimes;
	}

	public static String getMapString(byte[] byteArray) {
		if (null == byteArray || byteArray.length == 0) {
			byteArray = new byte[15];
			for (int i = 0; i < 7; i++) {
				int ran = new Random().nextInt(20);
				if (ran > 10) {
					byteArray[i] = 0;
				} else {
					byteArray[i] = 1;
				}
			}
		}
		String byteArrayString = "";
		/*
		 * 011110100011011 1:5 6:7 10:12 13:15
		 */
		Map<Integer, Integer> timeMap = new HashMap<Integer, Integer>();
		int beginIndex = 0;
		int dataLong = 0;
		int lastByte = 0;
		for (int k = 0; k < byteArray.length; k++) {
			byteArrayString += byteArray[k];
			int currentByte = byteArray[k];
			if (lastByte != byteArray[k]) {
				if (currentByte == 1) {
					beginIndex = k;
				} else if (currentByte == 0) {
					dataLong = k - beginIndex;
					timeMap.put(beginIndex, dataLong);
				}
			}
			lastByte = currentByte;
		}
		if (lastByte == 1) {
			dataLong = byteArray.length - beginIndex;
			timeMap.put(beginIndex, dataLong);
		}

		// System.out.println(byteArrayString);
		String returnStr = "";
		for (Entry<Integer, Integer> entry : timeMap.entrySet()) {
			returnStr += entry.getKey() + ":" + entry.getValue() + "*";
		}
		if (returnStr.length() > 0) {
			returnStr = returnStr.substring(0, returnStr.length() - 1);
		}
		// System.out.println(returnStr);
		return returnStr;
	}

	public static String getMapString2(byte[] byteArray) {
		if (null == byteArray || byteArray.length == 0) {
			byteArray = new byte[15];
			for (int i = 0; i < 15; i++) {
				int ran = new Random().nextInt(20);
				if (ran > 10) {
					byteArray[i] = 0;
				} else {
					byteArray[i] = 1;
				}
			}
		}
		System.out.println(byteArray.toString());
		String byteArrayString = "";
		/*
		 * 011110100011011 1:5 6:7 10:12 13:15
		 */
		Map<Integer, Integer> timeMap = new HashMap<>();
		int beginIndex = 9999;// 记录下标
		int dateKey = 10000;
		for (int k = 0; k < byteArray.length; k++) {
			if (byteArray[k] == 0 && dateKey == 10000) {// 空白处不做处理

			} else if (byteArray[k] == 0) {
				if (beginIndex != 9999 && dateKey != 10000) {
					// 一个数据组结束，保存下标
					timeMap.put(beginIndex, k);
					// 保存完成，还原开始下标
					beginIndex = 9999;
					dateKey = 10000;
				}
			} else if (byteArray[k] == 1) {
				// 判断是不是最后一条，如果是最后一条还没结束，那么立刻结束或者最后是1
				if (k == byteArray.length - 1) {
					int flag = 0;
					if (beginIndex != 9999) {
						flag++;
						timeMap.put(beginIndex, k + 1);
					}
					if (flag == 0) {
						timeMap.put(k, k + 1);
					}
				}
				// 如果是第一次,保存开始下标k
				if (beginIndex == 9999) {
					beginIndex = k;
					dateKey++;
				}
			}
			byteArrayString += byteArray[k];
		}
		System.out.println(byteArrayString);
		Map<String, Integer> timeMapData = new HashMap<>();
		List<Object> mapListObj = new ArrayList<>();
		for (Entry<Integer, Integer> entry : timeMap.entrySet()) {
			Integer mkey = entry.getKey();
			Integer mValue = entry.getValue();
			System.out.println("mkey" + mkey + "  mValue" + mValue);
			int timeMin = (mkey + 1) * 15;
			String keyString = "";
			int houresInt = timeMin / 60;
			String houres = "";
			int minsInt = timeMin % 60;
			if (houresInt == 0) {
				houres = "00";
			} else if (houresInt < 10) {
				houres = "0" + houresInt;
			} else {
				houres = houresInt + "";
			}
			String mins = "";
			if (minsInt == 0) {
				mins = "00";
			} else if (minsInt < 10) {
				mins = "0" + minsInt;
			} else {
				mins = minsInt + "";
			}
			keyString = houres + "" + ":" + mins;
			Integer pxSize = (mValue - mkey) * 15;
			// System.out.println("pxSize"+pxSize);
			timeMapData.put(keyString, pxSize);
			mapListObj.add(timeMapData);
		}
		for (int i = 0; i < mapListObj.size(); i++) {
			Map<String, Integer> timeMapDataFor = (Map<String, Integer>) mapListObj
					.get(i);
			for (Entry<String, Integer> entry : timeMapDataFor.entrySet()) {
				System.out.println(entry.getKey() + "     " + entry.getValue());
			}
		}

		return "";
	}

	public static void main(String[] args) {
		/*
		 * List<List<Object>> objs = new ArrayList<List<Object>>(); List<Object>
		 * list1 = ComplianceDateCreate.makeCompliancePlanVo(); List<Object>
		 * list2 = ComplianceDateCreate.makeComplianceVoData(list1);
		 * List<Object> list3 =
		 * ComplianceDateCreate.makeComplianceCurrentTimeVo(); objs.add(list1);
		 * objs.add(list2); objs.add(list3); String dataSend =
		 * MakeDataUtil.makeDataListObj(objs); System.out.println(dataSend);
		 */
	}
}
