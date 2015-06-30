package utry.psd.call.center.monitor.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import utry.psd.call.center.monitor.Bo.StateMonitoringDto;
import utry.psd.call.center.monitor.service.StateMonitoringService;

@Controller
@RequestMapping(value = "/stateMonitoringController")
public class StateMonitoringController {
	@Autowired
	private StateMonitoringService monitoringService;

	@RequestMapping(value = "/stateMonitorinit")
	public String generalOverviewInit() {
		return "monitor/stateMonitoring";
	}

	@RequestMapping(value = "/showDetailById")
	public @ResponseBody
	Map<String, Object> showDetailById(@RequestBody Map<String, Object> params) {
		String paramsSno = (String) params.get("snoc");
		String sidName = (String) params.get("sidName");
		String classNo = (String) params.get("sidClassnoc");
		String sidGroupId = (String) params.get("sidGroupId");
		Map<String, Object> map = new HashMap<String, Object>();
		List<StateMonitoringDto> stateMonitoringDtos = monitoringService
				.getStateMonitoringData("1234");
		map.put("paramsSno", paramsSno);
		map.put("sidName", sidName);
		map.put("classNo", classNo);
		map.put("sidGroupId", sidGroupId);
		map.put("stateMonitoringDtos", stateMonitoringDtos);
		return map;
	}
}
