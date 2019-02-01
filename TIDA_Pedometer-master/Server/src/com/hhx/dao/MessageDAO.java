package com.hhx.dao;

import java.util.List;

import com.hhx.entity.Message;

public interface MessageDAO {
	public Message getMessageByID(int message_id);
	public List<Integer> getAllMessageID(int userid, int type);
	public boolean sendMessage(int manager_id, String content, List<String> photos);
	public boolean readMessage(int userid, int message_id);
}
