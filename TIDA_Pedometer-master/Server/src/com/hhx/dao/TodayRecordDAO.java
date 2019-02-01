package com.hhx.dao;

import java.util.List;

import com.hhx.entity.TodayRecord;

public interface TodayRecordDAO {
	public boolean deleteTodayRecord(TodayRecord tr);
	public List<Integer> getAllTodayRecord();
	public TodayRecord getTodayRecord(int userid);
	public List<TodayRecord> getFriendsTodayRecord(int userid);
	public TodayRecord getRecordByID(int record_id);
	public boolean createTodayRecord();
	public boolean modifyTodayRecord(int userid, String key, String value);
}
