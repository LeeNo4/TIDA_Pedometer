package com.hhx.service;

import java.util.List;

import com.hhx.entity.SharedRecord;

public interface SharedRecordService {
	public SharedRecord queryRecord(int recordid);
	public List<SharedRecord> getSharedCollection(int userid);
	public List<SharedRecord> getAllSharedCollection();
	public List<SharedRecord> getFriendsSharedCollection(int userid);
	public boolean deleteRecord(int recordid);
	public boolean shareRecord(int userid, int steps, float calories, float minutes, String photo, String remark);
}