package com.mobileapp.hhx.entity;

public class User {
	private int userid;
	private String username;
	private String password;
	private String phone;
	private String photo;
	private int gender;
	private int height;
	private float weight;
	private int activeDays;
	private float activeHours;
	private float calories;
	private int steps;
	private String remark;
	
	
	public User(int userid, String username, String password, String phone, String photo, int gender, int height, float weight,
			int activeDays, float activeHours, float calories, int steps, String remark) {
		super();
		this.userid = userid;
		this.username = username;
		this.password = password;
		this.phone = phone;
		this.photo = photo;
		this.gender = gender;
		this.height = height;
		this.weight = weight;
		this.activeDays = activeDays;
		this.activeHours = activeHours;
		this.calories = calories;
		this.steps = steps;
		this.remark = remark;
	}

	public int getUserid() {
		return userid;
	}
	
	public void setUserid(int userid) {
		this.userid = userid;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getPhoto() {
		return photo;
	}
	
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	
	public int getGender() {
		return gender;
	}
	
	public void setGender(int gender) {
		this.gender = gender;
	}
	
	public int getHeight() {
		return height;
	}
	
	public void setHeight(int height) {
		this.height = height;
	}
	
	public float getWeight() {
		return weight;
	}
	
	public void setWeight(float weight) {
		this.weight = weight;
	}
	
	public int getActiveDays() {
		return activeDays;
	}
	
	public void setActiveDays(int activeDays) {
		this.activeDays = activeDays;
	}
	
	public float getActiveHours() {
		return activeHours;
	}
	
	public void setActiveHours(float activeHours) {
		this.activeHours = activeHours;
	}
	
	public float getCalories() {
		return calories;
	}

	public void setCalories(float calories) {
		this.calories = calories;
	}

	public int getSteps() {
		return steps;
	}
	
	public void setSteps(int steps) {
		this.steps = steps;
	}
	
	public String getRemark() {
		return remark;
	}
	
	public void setRemark(String remark) {
		this.remark = remark;
	}
}
