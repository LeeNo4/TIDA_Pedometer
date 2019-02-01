package com.hhx.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.hhx.dao.MessageDAO;
import com.hhx.dao.impl.MessageDAOimpl;
import com.hhx.entity.Message;
import com.hhx.service.MessageService;

public class MessageServiceImpl implements MessageService {
	MessageDAO messagedao = new MessageDAOimpl();
	
	@Override
	public List<Message> getAllMessages(int userid, int type) {
		// TODO Auto-generated method stub
		List<Integer> ids = messagedao.getAllMessageID(userid, type);
		List<Message> messages = new ArrayList<Message>();
		for(int i =0;i < ids.size();i++){
			Message message = messagedao.getMessageByID(ids.get(i));
			messages.add(message);
		}
		return messages;
	}

	@Override
	public boolean sendMessage(int manager_id, String content, List<String> photos) {
		// TODO Auto-generated method stub
		return messagedao.sendMessage(manager_id, content, photos);
	}

	@Override
	public boolean readMessage(int userid, int message_id) {
		// TODO Auto-generated method stub
		return messagedao.readMessage(userid, message_id);
	}

}
