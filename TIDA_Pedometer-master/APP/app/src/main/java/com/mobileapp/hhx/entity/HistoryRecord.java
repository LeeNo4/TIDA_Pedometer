package com.mobileapp.hhx.entity;

public class HistoryRecord {
	int record_id;
	int userid;
	int steps;
	float calories;
	float minutes;
	String date;
	
	public HistoryRecord(int record_id, int userid, int steps, float calories, float minutes, String date) {
		super();
		this.record_id = record_id;
		this.userid = userid;
		this.steps = steps;
		this.calories = calories;
		this.minutes = minutes;
		this.date = date;
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

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
}
