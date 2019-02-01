package com.hhx.service;

import java.util.List;
import java.util.Map;

import com.hhx.entity.FriendApplication;
import com.hhx.entity.User;

public interface UserService {
	public User findUser(String username, String password);
	public User findUserByID(int userid);
	public User findUserByName(String username);
	public List<User> queryUserByName(String query);
	public User register(String username, String password, int gender, int height, float weight);
	public boolean addFriend(int userid, int friend_id);
	public List<User> findFriends(int userid);
	public List<User> findStrangers(int userid);
	public User modifyInfo(Map<String, String>map, int userid);
	public List<FriendApplication> collectApplication(int userid);
	public boolean acceptApplication(int userid, int friend_id, int type);
	public boolean cancelFriends(int userid, int friend_id);
}
