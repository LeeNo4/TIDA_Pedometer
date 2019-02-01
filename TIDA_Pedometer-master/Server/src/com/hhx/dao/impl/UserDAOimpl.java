package com.hhx.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.hhx.dao.UserDAO;
import com.hhx.dbUtil.DBManager;
import com.hhx.entity.FriendApplication;
import com.hhx.entity.User;

public class UserDAOimpl implements UserDAO {
	DBManager dbm = new DBManager();
	
	@Override
	public User findUser(String username, String password) {
		// TODO Auto-generated method stub
		String sql = "select * from [User] where user_name = ? and password = ?";
		ResultSet rs = dbm.execQuery(sql, username, password);
		try {
			if(rs.next()){
				User user = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), 
						rs.getInt(6), rs.getInt(7), rs.getFloat(8), rs.getInt(9), rs.getFloat(10), 
						rs.getFloat(11), rs.getInt(12), rs.getString(13));
				return user;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public User findUserByID(int userid) {
		// TODO Auto-generated method stub
		String sql = "select * from [User] where user_id = ?";
		ResultSet rs = dbm.execQuery(sql, userid);
		try {
			if(rs.next()){
				User user = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), 
						rs.getInt(6), rs.getInt(7), rs.getFloat(8), rs.getInt(9), rs.getFloat(10), 
						rs.getFloat(11), rs.getInt(12), rs.getString(13));
				return user;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public User findUserByName(String username) {
		// TODO Auto-generated method stub
		String sql = "select * from [User] where user_name = ?";
		ResultSet rs = dbm.execQuery(sql, username);
		try {
			if(rs.next()){
				User user = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), 
						rs.getInt(6), rs.getInt(7), rs.getFloat(8), rs.getInt(9), rs.getFloat(10), 
						rs.getFloat(11), rs.getInt(12), rs.getString(13));
				return user;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public boolean insertInfo(String username, String password, int gender, int height, float weight) {
		// TODO Auto-generated method stub
		String sql = "insert into [User] values(?, ?, null, null, ?, ?, ?, 0, 0, 0, 0, null)";
		return dbm.execUpdate(sql, username, password, gender, height, weight) > 0;
	}

	@Override
	public boolean addFriendApply(int userid, int friend_id) {
		// TODO Auto-generated method stub
		String sql = "insert into FriendApplication values(?, ?, 3)";
		return dbm.execUpdate(sql, friend_id, userid) > 0;
	}

	@Override
	public List<Integer> findFriendsID(int userid) {
		// TODO Auto-generated method stub
		List<Integer> friendsID = new ArrayList<Integer>();
		String sql = "select Use_user_id from Friends where user_id = ?";
		ResultSet rs = dbm.execQuery(sql, userid);
		try {
			while(rs.next()){
				friendsID.add(rs.getInt(1));
			}
			return friendsID;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Integer> findAllUsersID() {
		// TODO Auto-generated method stub
		List<Integer> ids = new ArrayList<Integer>();
		String sql = "select user_id from [User]";
		ResultSet rs = dbm.execQuery(sql);
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
	public boolean modifyInfo(String attribute, String value, int userid) {
		// TODO Auto-generated method stub
		String sql = "update [User] set ? = ? where user_id = ?";
		return dbm.execUpdate(sql, attribute, value, userid) > 0;
	}

	@Override
	public List<FriendApplication> collectApplication(int userid) {
		// TODO Auto-generated method stub
		String sql = "select F.* from FriendApplication as F,[User] as U where U.user_id = F.user_id and F.user_id = ? order by F.type, F.time";
		List<FriendApplication>apps = new ArrayList<FriendApplication>();
		ResultSet rs = dbm.execQuery(sql, userid);
		try {
			while(rs.next()){
				FriendApplication fa = new FriendApplication(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getString(5), rs.getString(6));
				apps.add(fa);
			}
			return apps;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean acceptApplication(int userid, int friend_id, int type) {
		// TODO Auto-generated method stub
		String sql_1 = "select apply_id from FriendApplication where user_id = ? and applier_id = ? and state = 0";
		ResultSet rs = dbm.execQuery(sql_1, userid, friend_id);
		try {
			if(!rs.next()){
				int apply_id = rs.getInt(1);
				if(type == 2){
					String sql_2 = "insert into Friends values(?, ?)";
					String sql_3 = "update FriendApplication set state = ? where apply_id = ?";
					return (dbm.execUpdate(sql_2, friend_id, userid) > 0) && (dbm.execUpdate(sql_2, userid, friend_id) > 0)
							&& (dbm.execUpdate(sql_3, apply_id) > 0);
				}else if(type == 1){
					String sql_3 = "update FriendApplication set state = ? where apply_id = ?";
					return dbm.execUpdate(sql_3, apply_id) > 0;
				}
			}else
				return false;
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean cancelFriends(int userid, int friend_id) {
		// TODO Auto-generated method stub
		String sql = "delete from Friends where user_id = ? and friend_id = ?";
		return (dbm.execUpdate(sql, userid, friend_id) > 0) && (dbm.execUpdate(sql, friend_id, userid) > 0);
	}

	@Override
	public List<User> queryUserByName(String query) {
		// TODO Auto-generated method stub
		String sql = "select * from [User] where user_name like ?";
		ResultSet rs = dbm.execQuery(sql, "&" + query + "%");
		List<User> users = new ArrayList<User>();
		try {
			while(rs.next()){
				User user = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), 
						rs.getInt(6), rs.getInt(7), rs.getFloat(8), rs.getInt(9), rs.getFloat(10), 
						rs.getFloat(11), rs.getInt(12), rs.getString(13));
				users.add(user);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return users;
	}

}
