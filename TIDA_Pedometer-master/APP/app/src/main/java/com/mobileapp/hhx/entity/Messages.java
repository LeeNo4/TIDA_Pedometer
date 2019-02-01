package com.mobileapp.hhx.entity;

import java.util.List;

public class Messages {
	int message_id;
	int manager_id;
	String content;
	List<String> photo;
	String date;
	
	public Messages(int message_id, int manager_id, String content, List<String> photo, String date) {
		super();
		this.message_id = message_id;
		this.manager_id = manager_id;
		this.content = content;
		this.photo = photo;
		this.date = date;
	}

	public int getMessage_id() {
		return message_id;
	}

	public void setMessage_id(int message_id) {
		this.message_id = message_id;
	}

	public int getManager_id() {
		return manager_id;
	}

	public void setManager_id(int manager_id) {
		this.manager_id = manager_id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public List<String> getPhoto() {
		return photo;
	}

	public void setPhoto(List<String> photo) {
		this.photo = photo;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
}
