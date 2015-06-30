package utry.psd.call.center.monitor.dao;

import java.util.List;


import utry.psd.call.center.monitor.Bo.StateMonitoringDto;


public interface StateMonitoringDao {
	public int xintiaotest();
	public List<StateMonitoringDto> getStateMonitoringData(String staffno);
}
