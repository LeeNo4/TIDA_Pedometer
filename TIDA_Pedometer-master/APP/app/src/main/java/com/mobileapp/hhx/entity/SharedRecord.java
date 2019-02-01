package com.mobileapp.hhx.entity;

public class SharedRecord {
	int recordid;
	int userid;
	int steps;
	float calories;
	float minutes;
	String photo;
	String remark;
	String time;
	
	public SharedRecord(int recordid, int userid, int steps, float calories, float minutes, String photo, String remark,
			String time) {
		super();
		this.recordid = recordid;
		this.userid = userid;
		this.steps = steps;
		this.calories = calories;
		this.minutes = minutes;
		this.photo = photo;
		this.remark = remark;
		this.time = time;
	}

	public int getRecordid() {
		return recordid;
	}
	
	public void setRecordid(int recordid) {
		this.recordid = recordid;
	}
	
	public int getUserid() {
		return userid;
	}
	
	public void setUserid(int userid) {
		this.userid = userid;
	}
	
	public int getSteps() {
		return steps;
	}
	
	public void setSteps(int steps) {
		this.steps = steps;
	}
	
	public float getCalories() {
		return calories;
	}
	
	public void setCalories(float calories) {
		this.calories = calories;
	}
	
	public float getMinutes() {
		return minutes;
	}
	
	public void setMinutes(float minutes) {
		this.minutes = minutes;
	}
	
	public String getPhoto() {
		return photo;
	}
	
	public void setPhoto(String photo) {
		this.photo = photo;
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
