package com.hhx.entity;

public class TodayRecord {
	int record_id;
	int userid;
	String date;
	float minutes;
	int steps;
	int plan_steps;
	float calories;
	int shared;
	
	public TodayRecord(int record_id, int userid, String date, float minutes, int steps, int plan_steps, float calories, int shared) {
		super();
		this.record_id = record_id;
		this.userid = userid;
		this.date = date;
		this.minutes = minutes;
		this.steps = steps;
		this.plan_steps = plan_steps;
		this.calories = calories;
		this.shared = shared;
	}

	public int getRecord_id() {
		return record_id;
	}

	public void setRecord_id(int record_id) {
		this.record_id = record_id;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public float getMinutes() {
		return minutes;
	}

	public void setMinutes(float minutes) {
		this.minutes = minutes;
	}

	public int getSteps() {
		return steps;
	}

	public void setSteps(int steps) {
		this.steps = steps;
	}

	public int getPlan_steps() {
		return plan_steps;
	}

	public void setPlan_steps(int plan_steps) {
		this.plan_steps = plan_steps;
	}

	public float getCalories() {
		return calories;
	}

	public void setCalories(float calories) {
		this.calories = calories;
	}

	public int getShared() {
		return shared;
	}

	public void setShared(int shared) {
		this.shared = shared;
	}
}
