package com.hhx.service;

import java.util.List;

import com.hhx.entity.Message;

public interface MessageService {
	public List<Message> getAllMessages(int userid, int type);
	public boolean sendMessage(int manager_id, String content, List<String> photos);
	public boolean readMessage(int userid, int message_id);
}
