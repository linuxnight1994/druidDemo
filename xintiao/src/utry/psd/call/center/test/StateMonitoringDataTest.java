package utry.psd.call.center.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.quartz.simpl.RAMJobStore;

import utry.common.JedisService;
import utry.psd.call.center.monitor.Bo.StateMonitoringBo;
import utry.psd.call.center.monitor.Bo.StateMonitoringBoVo;
import utry.psd.call.center.util.UUIDUtils;

public class StateMonitoringDataTest {
	private static JedisService jedisFactoryAction;

	public static List<Object> makeStateMonitoringDate() {
		List<Object> stateMonitorings = new ArrayList<>();
		int size = new Random().nextInt(30);
		for (int i = 1; i <= size; i++) {
			StateMonitoringBo monitoringBo = new StateMonitoringBo();
			monitoringBo.setSid(i);
			monitoringBo.setsName("Name" + i + new Random().nextInt(20));
			monitoringBo.setSno(UUIDUtils.getUUID().substring(1, 5));
			monitoringBo.setClassNo("classNo" + i + new Random().nextInt(20));
			monitoringBo.setGroupType("groupType" + i
					+ new Random().nextInt(20));
			monitoringBo.setBranchCenter("branchCenter" + i
					+ new Random().nextInt(20));
			monitoringBo.setExtensionNumber("extensionNumber" + i
					+ new Random().nextInt(20));
			monitoringBo.setBeginTime(new Date());
			if (i % 7 == 0) {
				monitoringBo.setStatus("exhale");
			} else if (i % 7 == 1) {
				monitoringBo.setStatus("dialout");
			} else if (i % 7 == 2) {
				monitoringBo.setStatus("axu");
			} else if (i % 7 == 3) {
				monitoringBo.setStatus("eat");
			} else if (i % 7 == 4) {
				monitoringBo.setStatus("littlerest");
			} else if (i % 7 == 5) {
				monitoringBo.setStatus("afterwards");
			} else if (i % 7 == 6) {
				monitoringBo.setStatus("idle");
			}
			stateMonitorings.add(monitoringBo);
		}
		return stateMonitorings;
	}

	public static List<Object> makeStateMonitoringBoVoList(List<Object> objs) {
		List<Object> stateMonitoringVoList = new ArrayList<>();
		if (null != objs && objs.size() > 0) {
			for (Object obj : objs) {
				StateMonitoringBoVo stateMonitoringVo = new StateMonitoringBoVo();
				StateMonitoringBo monitoringBo = (StateMonitoringBo) obj;
				stateMonitoringVo.setSid(monitoringBo.getSid());
				stateMonitoringVo.setSno(monitoringBo.getSno());
				stateMonitoringVo.setBranchCenter(monitoringBo
						.getBranchCenter());
				stateMonitoringVo.setClassNo(monitoringBo.getClassNo());
				stateMonitoringVo.setExtensionNumber(monitoringBo
						.getExtensionNumber());
				stateMonitoringVo.setGroupType(monitoringBo.getGroupType());
				stateMonitoringVo.setsName(monitoringBo.getsName());
				stateMonitoringVo.setStatus(monitoringBo.getStatus());
				Date datetime = monitoringBo.getBeginTime();
				long timelong = new Random().nextInt(3600000);
				// System.out.println(timelong);
				long datimelong = (datetime.getTime()) + timelong;
				long dataLong = (datimelong - datetime.getTime()) / 1000;// /
																			// 1000
				String str = "";
				if (dataLong / 3600 > 0) {
					int hqumo = (int) (dataLong % 3600);
					int hours = (int) (dataLong - hqumo) / 3600;
					if (hours < 10) {
						str += "0" + hours;
					} else {
						str += hours;
					}
					int minqumo = (hqumo % 60);
					int minutes = (hqumo - minqumo) / 60;
					if (minutes < 10) {
						str += ":" + "0" + hours;
					} else {
						str += ":" + hours;
					}
				}
				str = str.length() <= 0 ? dataLong + "" : str;
				stateMonitoringVo.setTimeLong(str);
				int isRed = new Random().nextInt(50);
				if (isRed > 25) {
					stateMonitoringVo.setIsRed(1);
				}else{
					stateMonitoringVo.setIsRed(0);
				}
				// System.out.println(stateMonitoringVo.toString());
				stateMonitoringVoList.add(stateMonitoringVo);
			}
		}
		return stateMonitoringVoList;
	}

	public static void main(String[] args) {
		List<Object> stateMonitoringVoList = makeStateMonitoringBoVoList(makeStateMonitoringDate());
		// System.out.println(stateMonitoringVoList.size());
	}
}
