package com.hhx.dao;

import java.util.List;

import com.hhx.entity.HistoryRecord;
import com.hhx.entity.TodayRecord;

public interface HistoryRecordDAO {
	public List<Integer> getAllHistoryRecord(int userid);
	public HistoryRecord getRecordByID(int record_id);
	public Object getTotalData(int userid, String key, String value);
	public Object getMaxData(int userid, String key, String value);
	public boolean addToHistoryRecord(TodayRecord tr);
}
