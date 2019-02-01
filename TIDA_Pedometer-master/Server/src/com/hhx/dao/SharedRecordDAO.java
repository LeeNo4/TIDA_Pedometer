package com.hhx.dao;

import java.util.List;

import com.hhx.entity.SharedRecord;

public interface SharedRecordDAO {
	public SharedRecord queryRecord(int recordid);
	public List<Integer> getSharedCollection(int userid);
	public List<Integer> getAllSharedCollection();
	public List<SharedRecord> getFriendsSharedCollection(int userid);
	public boolean deleteRecord(int recordid);
	public boolean shareRecord(int userid, int steps, float calories, float miutes, String photo, String remark);
}
