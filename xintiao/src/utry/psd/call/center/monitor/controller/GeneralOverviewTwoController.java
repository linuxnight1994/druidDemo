package utry.psd.call.center.monitor.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/generalOverviewControllerTwo")
public class GeneralOverviewTwoController {
	@RequestMapping(value = "/generalInit")
	public String generalOverviewInit() {
		return "monitor/generalOverviewTwo";
	}

}
