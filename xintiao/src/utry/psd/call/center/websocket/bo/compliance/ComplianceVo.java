package utry.psd.call.center.websocket.bo.compliance;

import java.io.Serializable;
import java.util.Date;
import java.util.Random;

/**
 * 遵时度数据推送VO
 * 
 * date 2015-03-26
 * 
 * @author sharkTang
 * 
 */
public class ComplianceVo implements Serializable {
	private static final long serialVersionUID = 98429492015032601L;
	private String sno;// 工号
	private String status;// 状态
	private Date beginTime;// 开始时间
	private int longTime;// 时长

	@Override
	public String toString() {
		Date date = new Date();
		date.setHours(0);
		date.setMinutes(0);
		date.setSeconds(0);
		long b = beginTime.getTime();
		long e = date.getTime();
		long d = b - e;
		long miao = d / 1000;
		long minuten = 0;
		long miaoqumo = miao % 60;
		if (miaoqumo == 0) {
			minuten = miao / 60;
		} else {
			minuten = (miao - miaoqumo) / 60 + (miaoqumo / 60);
		}
		//System.out.println(minuten);
		StringBuffer str = new StringBuffer();
		str.append(sno).append(",").append(status).append(",").append(longTime+new Random().nextInt(20))
				.append(",").append(minuten);
		return str.toString();
	}

	public String getSno() {
		return sno;
	}

	public void setSno(String sno) {
		this.sno = sno;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	public int getLongTime() {
		return longTime;
	}

	public void setLongTime(int longTime) {
		this.longTime = longTime;
	}

}
