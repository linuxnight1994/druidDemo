package utry.psd.call.center.monitor.Bo;

import java.io.Serializable;
import java.util.Date;

public class StateMonitoringBo implements Serializable {

	private static final long serialVersionUID = 98429492015032401L;
	private int sid;// 序号
	private String sName;// 姓名
	private String sno;// 工号
	private String agentID;// 工号
	private String classNoID;// 班次ID
	private String classNo;// 班次
	private String groupTypeID;// 组别ID
	private String groupType;// 组别
	private String branchCenterID;// 技能线ID
	private String branchCenter;// 技能线
	private String extensionNumber;// 分机号
	private String callInNumber;// 分机号
	private String status;// 状态
	private int index;// 开始时间相同时的序列
	private byte[] descOne; // 执行班表时段1
	private byte[] descTwo; // 执行班表时段2
	private byte[] overtimeTimeDescOne; // 执行班表时段2
	private byte[] overtimeTimeDescTwo; // 执行班表时段2
	private int isNightShift; // 执行班表时段2
	private Date workDate; // 工作日
	private String statusID;// 状态ID
	private Date beginTime;// 同时间的状态序列
	private Date endTIme;// 同时间的状态序列
	private int timeLong;// 时长

	public String getAgentID() {
		return agentID;
	}

	public void setAgentID(String agentID) {
		this.agentID = agentID;
	}

	public String getClassNoID() {
		return classNoID;
	}

	public void setClassNoID(String classNoID) {
		this.classNoID = classNoID;
	}

	public String getGroupTypeID() {
		return groupTypeID;
	}

	public void setGroupTypeID(String groupTypeID) {
		this.groupTypeID = groupTypeID;
	}

	public String getBranchCenterID() {
		return branchCenterID;
	}

	public void setBranchCenterID(String branchCenterID) {
		this.branchCenterID = branchCenterID;
	}

	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

	public String getsName() {
		return sName;
	}

	public void setsName(String sName) {
		this.sName = sName;
	}

	public String getSno() {
		return sno;
	}

	public void setSno(String sno) {
		this.sno = sno;
	}

	public String getClassNo() {
		return classNo;
	}

	public void setClassNo(String classNo) {
		this.classNo = classNo;
	}

	public String getGroupType() {
		return groupType;
	}

	public void setGroupType(String groupType) {
		this.groupType = groupType;
	}

	public String getBranchCenter() {
		return branchCenter;
	}

	public void setBranchCenter(String branchCenter) {
		this.branchCenter = branchCenter;
	}

	public String getExtensionNumber() {
		return extensionNumber;
	}

	public void setExtensionNumber(String extensionNumber) {
		this.extensionNumber = extensionNumber;
	}

	public String getCallInNumber() {
		return callInNumber;
	}

	public void setCallInNumber(String callInNumber) {
		this.callInNumber = callInNumber;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public byte[] getDescOne() {
		return descOne;
	}

	public void setDescOne(byte[] descOne) {
		this.descOne = descOne;
	}

	public byte[] getDescTwo() {
		return descTwo;
	}

	public void setDescTwo(byte[] descTwo) {
		this.descTwo = descTwo;
	}

	public byte[] getOvertimeTimeDescOne() {
		return overtimeTimeDescOne;
	}

	public void setOvertimeTimeDescOne(byte[] overtimeTimeDescOne) {
		this.overtimeTimeDescOne = overtimeTimeDescOne;
	}

	public byte[] getOvertimeTimeDescTwo() {
		return overtimeTimeDescTwo;
	}

	public void setOvertimeTimeDescTwo(byte[] overtimeTimeDescTwo) {
		this.overtimeTimeDescTwo = overtimeTimeDescTwo;
	}

	public int getIsNightShift() {
		return isNightShift;
	}

	public void setIsNightShift(int isNightShift) {
		this.isNightShift = isNightShift;
	}

	public Date getWorkDate() {
		return workDate;
	}

	public void setWorkDate(Date workDate) {
		this.workDate = workDate;
	}

	public String getStatusID() {
		return statusID;
	}

	public void setStatusID(String statusID) {
		this.statusID = statusID;
	}

	public Date getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	public Date getEndTIme() {
		return endTIme;
	}

	public void setEndTIme(Date endTIme) {
		this.endTIme = endTIme;
	}

	public int getTimeLong() {
		return timeLong;
	}

	public void setTimeLong(int timeLong) {
		this.timeLong = timeLong;
	}

	@Override
	public String toString() {
		StringBuffer str = new StringBuffer();
		str.append(sid).append(",").append(sName).append(",").append(sno)
				.append(",").append(classNo).append(",").append(groupType)
				.append(",").append(branchCenter).append(",")
				.append(extensionNumber).append(",").append(status).append(",")
				.append(timeLong);
		return str.toString();
	}

}
