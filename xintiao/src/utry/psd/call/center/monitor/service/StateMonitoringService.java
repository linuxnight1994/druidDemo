package utry.psd.call.center.monitor.service;

import java.util.List;

import utry.psd.call.center.monitor.Bo.StateMonitoringDto;

public interface StateMonitoringService {
	public List<StateMonitoringDto> getStateMonitoringData(String staffno);
}
