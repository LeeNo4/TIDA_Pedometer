package com.hhx.dao;

import java.util.List;

import com.hhx.entity.FriendApplication;
import com.hhx.entity.User;

public interface UserDAO {
	public User findUser(String username, String password);
	public User findUserByID(int userid);
	public User findUserByName(String username);
	public List<User> queryUserByName(String query);
	
	public List<Integer> findFriendsID(int userid);
	public List<Integer> findAllUsersID();
	public boolean addFriendApply(int userid, int friend_id);
	public boolean cancelFriends(int userid, int friend_id);
	
	public List<FriendApplication> collectApplication(int userid);
	public boolean acceptApplication(int userid, int friend_id, int type);
	
	public boolean insertInfo(String username, String password, int gender, int height, float weight);
	public boolean modifyInfo(String attribute, String value, int userid);

	
}
