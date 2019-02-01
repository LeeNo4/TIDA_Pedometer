package com.hhx.service;

import java.util.List;
import java.util.Map;

import com.hhx.entity.TodayRecord;

public interface TodayRecordService {
	public List<TodayRecord> getAllTodayRecord();
	public boolean deleteTodayRecord(TodayRecord tr);
	public TodayRecord getTodayRecord(int userid);
	public List<TodayRecord> getFriendsTodayRecord(int userid);
	public TodayRecord getRecordByID(int record_id);
	public boolean createTodayRecord();
	public boolean modifyTodayRecord(int userid, Map<String, String>map);
}
