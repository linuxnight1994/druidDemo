package utry.psd.call.center.monitor.Bo;

import java.io.Serializable;

/**
 * 实时监控数据Vo
 * 
 * date 2015-03-21
 * 
 * @author sharkTang
 * 
 */
public class RumTimeVo implements Serializable {
	private static final long serialVersionUID = 9842949201503212L;
	private String uid;// 序列id号
	private String skillLine;// 技能线Name (白金 ，总体)
	private String resoureLine;// 资源线
	private int skillLineCount;// 进线量
	private float connectRate;// 接通率
	private float backOutCount;// 放弃量
	private int waitPhoneCount;// 等待电话量
	private int waitTime;// 等待时间（秒）
	private int planPersonCount;// 安排人数
	private int checkinPersonCount;// 签入人数
	private float serviceHorizontal;// 服务水平

	public String getResoureLine() {
		return resoureLine;
	}

	public void setResoureLine(String resoureLine) {
		this.resoureLine = resoureLine;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String string) {
		this.uid = string;
	}

	public String getSkillLine() {
		return skillLine;
	}

	public void setSkillLine(String skillLine) {
		this.skillLine = skillLine;
	}

	public int getSkillLineCount() {
		return skillLineCount;
	}

	public void setSkillLineCount(int skillLineCount) {
		this.skillLineCount = skillLineCount;
	}

	public float getConnectRate() {
		return connectRate;
	}

	public void setConnectRate(float connectRate) {
		this.connectRate = connectRate;
	}

	public float getBackOutCount() {
		return backOutCount;
	}

	public void setBackOutCount(float backOutCount) {
		this.backOutCount = backOutCount;
	}

	public int getWaitPhoneCount() {
		return waitPhoneCount;
	}

	public void setWaitPhoneCount(int waitPhoneCount) {
		this.waitPhoneCount = waitPhoneCount;
	}

	public int getWaitTime() {
		return waitTime;
	}

	public void setWaitTime(int waitTime) {
		this.waitTime = waitTime;
	}

	public int getPlanPersonCount() {
		return planPersonCount;
	}

	public void setPlanPersonCount(int planPersonCount) {
		this.planPersonCount = planPersonCount;
	}

	public int getCheckinPersonCount() {
		return checkinPersonCount;
	}

	public void setCheckinPersonCount(int checkinPersonCount) {
		this.checkinPersonCount = checkinPersonCount;
	}

	public float getServiceHorizontal() {
		return serviceHorizontal;
	}

	public void setServiceHorizontal(float serviceHorizontal) {
		this.serviceHorizontal = serviceHorizontal;
	}

	public static RumTimeVo cloneRumTimeVo(RumTimeFromRedis fromRedis) {
		RumTimeVo rv = new RumTimeVo();
		rv.setBackOutCount(fromRedis.getBackOutCount());
		rv.setCheckinPersonCount(fromRedis.getCheckinPersonCount());
		rv.setConnectRate(fromRedis.getConnectRate());
		rv.setPlanPersonCount(fromRedis.getPlanPersonCount());
		rv.setResoureLine(fromRedis.getResoureLine());
		rv.setServiceHorizontal(fromRedis.getServiceHorizontal());
		rv.setSkillLine(fromRedis.getSkillLine());
		rv.setSkillLineCount(fromRedis.getSkillLineCount());
		rv.setUid(fromRedis.getUid());
		rv.setWaitPhoneCount(fromRedis.getWaitPhoneCount());
		rv.setWaitTime(fromRedis.getWaitTime());
		return rv;
	}

	@Override
	public String toString() {
		StringBuffer str = new StringBuffer();
		str.append(uid).append(",").append(skillLine).append(",")
		.append(skillLineCount).append(",").append(connectRate)
		.append(",").append(backOutCount).append(",")
		.append(waitPhoneCount).append(",").append(waitTime).append(",")
		.append(planPersonCount).append(",").append(checkinPersonCount)
		.append(",").append(serviceHorizontal);
		return str.toString();
	}
	//
	// 进线量 = 求和
	// 接通率 = 接起量/进线量
	// 放弃量 = 放弃量/进线量

	// 等待电话=求和
	// 等待时间 =求max（）
	// 安排人数 求和
	// 签入人数 求和
	// 服务水平 20秒内接起量/进线量

}
