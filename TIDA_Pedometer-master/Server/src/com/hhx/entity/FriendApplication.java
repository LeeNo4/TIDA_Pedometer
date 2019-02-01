package com.hhx.entity;

public class FriendApplication {
	int applyid;
	int userid;
	int applier_id;
	int state;
	String remark;
	String time;
	
	public FriendApplication(int applyid, int userid, int applier_id, int state, String remark, String time) {
		super();
		this.applyid = applyid;
		this.userid = userid;
		this.applier_id = applier_id;
		this.state = state;
		this.remark = remark;
		this.time = time;
	}

	public int getApplyid() {
		return applyid;
	}

	public void setApplyid(int applyid) {
		this.applyid = applyid;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public int getApplier_id() {
		return applier_id;
	}

	public void setApplier_id(int applier_id) {
		this.applier_id = applier_id;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
}
