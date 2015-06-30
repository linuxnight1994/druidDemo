package utry.psd.call.center.monitor.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/complianceController")
public class ComplianceController {
	@RequestMapping(value = "/compliance")
	public String generalOverviewInit() {
		
		return "monitor/compliance";
	}

}
