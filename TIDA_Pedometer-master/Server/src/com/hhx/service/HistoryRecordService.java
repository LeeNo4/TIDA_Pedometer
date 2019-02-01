package com.hhx.service;

import java.util.List;

import com.hhx.entity.HistoryRecord;

public interface HistoryRecordService {
	public List<HistoryRecord> getAllHistoryRecord(int userid);
	public boolean addToHistoryRecord();
	public Object getTotalData(int userid, String key, String value);
	public Object getMaxData(int userid, String key, String value);
}
