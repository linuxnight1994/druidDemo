package utry.psd.call.center.monitor.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import utry.psd.call.center.monitor.Bo.StateMonitoringDto;
import utry.psd.call.center.monitor.dao.StateMonitoringDao;
import utry.psd.call.center.monitor.service.StateMonitoringService;

@Service
@Transactional
public class StateMonitoringServiceImpl implements StateMonitoringService {
	@Autowired
	private StateMonitoringDao stateMonitoringDao;

	@Override
	public List<StateMonitoringDto> getStateMonitoringData(String staffno) {
		// TODO Auto-generated method stub
		return stateMonitoringDao.getStateMonitoringData(staffno);
	}

}
