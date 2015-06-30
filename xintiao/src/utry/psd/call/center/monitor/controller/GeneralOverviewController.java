package utry.psd.call.center.monitor.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/generalOverviewController")
public class GeneralOverviewController {
	@RequestMapping(value = "/generalInit")
	public String generalOverviewInit() {
		return "monitor/generalOverview";
	}

}
