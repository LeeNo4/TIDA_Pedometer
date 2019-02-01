package com.hhx.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.hhx.dao.UserDAO;
import com.hhx.dao.impl.UserDAOimpl;
import com.hhx.entity.FriendApplication;
import com.hhx.entity.User;
import com.hhx.service.UserService;

public class UserServiceImpl implements UserService {
	UserDAO userdao = new UserDAOimpl();
	
	@Override
	public User findUser(String username, String password) {
		// TODO Auto-generated method stub
		return userdao.findUser(username, password);
	}

	@Override
	public User findUserByID(int userid) {
		// TODO Auto-generated method stub
		return userdao.findUserByID(userid);
	}

	@Override
	public User findUserByName(String username) {
		// TODO Auto-generated method stub
		return userdao.findUserByName(username);
	}

	@Override
	public User register(String username, String password, int gender, int height, float weight) {
		// TODO Auto-generated method stub	
		if(userdao.insertInfo(username, password, gender, height, weight))
			return userdao.findUserByName(username);
		return null;
	}

	@Override
	public boolean addFriend(int userid, int friend_id) {
		// TODO Auto-generated method stub
		return userdao.addFriendApply(userid, friend_id);
	}

	@Override
	public List<User> findFriends(int userid) {
		// TODO Auto-generated method stub
		List<Integer> ids = userdao.findFriendsID(userid);
		List<User> friends = new ArrayList<User>();
		for(int i = 0;i < ids.size();i++){
			User user = userdao.findUserByID(ids.get(i));
			friends.add(user);
		}
		return friends;
	}

	@Override
	public User modifyInfo(Map<String, String> map, int userid) {
		// TODO Auto-generated method stub
		int i = 0;
		Iterator<Map.Entry<String, String>>iterator = map.entrySet().iterator();
		while(iterator.hasNext()){
			Map.Entry<String, String>tmp = iterator.next();
			if(!userdao.modifyInfo(tmp.getKey(), tmp.getValue(), userid)){
				i = 1;
			}
		}
		if(i == 0) {
			User user = userdao.findUserByID(userid);
			return user;
		}
		return null;
	}

	@Override
	public List<FriendApplication> collectApplication(int userid) {
		// TODO Auto-generated method stub
		return userdao.collectApplication(userid);
	}

	@Override
	public boolean acceptApplication(int userid, int friend_id, int type) {
		// TODO Auto-generated method stub
		return userdao.acceptApplication(userid, friend_id, type);
	}

	@Override
	public boolean cancelFriends(int userid, int friend_id) {
		// TODO Auto-generated method stub
		return userdao.cancelFriends(userid, friend_id);
	}

	@Override
	public List<User> findStrangers(int userid) {
		// TODO Auto-generated method stub
		List<Integer> ids = userdao.findAllUsersID();
		List<Integer> friends = userdao.findFriendsID(userid);
		List<User> strangers = new ArrayList<User>();
		for(int i = 0;i < ids.size();i++){
			int tmp = ids.get(i);
			if(tmp != userid && !friends.contains(tmp)){
				User user = userdao.findUserByID(tmp);
				strangers.add(user);
			}
		}
		return strangers;
	}

	@Override
	public List<User> queryUserByName(String query) {
		return userdao.queryUserByName(query);
	}
}
