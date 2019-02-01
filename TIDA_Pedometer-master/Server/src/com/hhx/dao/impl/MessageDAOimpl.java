package com.hhx.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.hhx.dao.MessageDAO;
import com.hhx.dao.UserDAO;
import com.hhx.dbUtil.DBManager;
import com.hhx.entity.Message;

public class MessageDAOimpl implements MessageDAO {
	DBManager dbm = new DBManager();
	
	@Override
	public Message getMessageByID(int message_id) {
		// TODO Auto-generated method stub
		String sql = "select * from Message where message_id = ?";
		ResultSet rs = dbm.execQuery(sql, message_id);
		try {
			if(rs.next()){
				String tmp = rs.getString(4);
				String[] photos = tmp.split(";");
				Message message = new Message(rs.getInt(1), rs.getInt(2), rs.getString(3), Arrays.asList(photos), rs.getString(5));
				return message;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Integer> getAllMessageID(int userid, int type) {
		// TODO Auto-generated method stub
		String sql = "select Message.message_id from Message, Receive where Message.message_id = Receive.message_id"
				+ " and Receive.user_id = ? and Receive.state = ? order by Message.time desc";
		ResultSet rs = dbm.execQuery(sql, userid, type);
		List<Integer> ids = new ArrayList<Integer>();
		try {
			while(rs.next()){
				ids.add(rs.getInt(1));
			}
			return ids;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean sendMessage(int manager_id, String content, List<String> photos) {
		// TODO Auto-generated method stub
		String tmp = "";
		if(photos.size() > 0){
		for(int i = 0;i < photos.size()-1;i++){
				tmp = tmp + photos.get(i) + ";";
			}
			tmp = tmp + photos.get(photos.size()-1);
		}
		String sql_1 = "insert into Message values(?, ?, ?, now())";
		String sql_2 = "select * from Message order by time desc";
		String sql_3 = "insert into Receive values(?, ?, 0)";
		int message_id = 0, n = 0;
		if(dbm.execUpdate(sql_1, manager_id, content, tmp) > 0){
			ResultSet rs = dbm.execQuery(sql_2);
			try {
				if(rs.next())
					message_id = rs.getInt(1);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			UserDAO userdao = new UserDAOimpl();
			List<Integer> list = userdao.findAllUsersID();
			for(int i = 0;i < list.size();i++){
				if(dbm.execUpdate(sql_3, message_id, list.get(i)) < 0)
					n = 1;
			}
			if(n == 0) return true;
		}
		return false;
	}
	
	@Override
	public boolean readMessage(int userid, int message_id) {
		// TODO Auto-generated method stub
		String sql = "update Receive set state = 1 where user_id = ? and message_id = ?";
		return dbm.execUpdate(sql, userid, message_id) > 0;
	}

}
