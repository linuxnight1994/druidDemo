package utry.psd.call.center.websocket.bo.compliance;

import java.io.Serializable;
import java.util.Date;

/**
 * 当前时间推送VO
 * 
 * date 2015-03-26
 * 
 * @author sharkTang
 * 
 */
public class ComplianceCurrentTimeVo implements Serializable {
	private static final long serialVersionUID = 98429492015032602L;
	private Date currentTime;// 当前时间

	public Date getCurrentTime() {
		return currentTime;
	}

	public void setCurrentTime(Date currentTime) {
		this.currentTime = currentTime;
	}

	@Override
	public String toString() { 
		return currentTime.getTime()+"";
	}

}
