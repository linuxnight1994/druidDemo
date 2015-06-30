package utry.psd.call.center.monitor.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import utry.psd.call.center.monitor.dao.StateMonitoringDao;
import utry.psd.call.center.monitor.service.XinTiaoService;

@Service("xinTiaoService")
public class XinTiaoServiceImpl implements XinTiaoService {
	@Autowired
	private StateMonitoringDao xintaoDao;

	@Override
	public int xintiaoFunction() {
		// TODO Auto-generated method stub
		int result = 0;
		try {
			result = xintaoDao.xintiaotest();
		} catch (Exception ex) {
			ex.printStackTrace();
			result = 0;
		}
		return result;
	}
}
