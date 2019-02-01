package com.hhx.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.hhx.dao.HistoryRecordDAO;
import com.hhx.dao.impl.HistoryRecordDAOimpl;
import com.hhx.entity.HistoryRecord;
import com.hhx.entity.TodayRecord;
import com.hhx.service.HistoryRecordService;
import com.hhx.service.TodayRecordService;

public class HistoryRecordServiceImpl implements HistoryRecordService {
	HistoryRecordDAO hrdao = new HistoryRecordDAOimpl();
	
	@Override
	public List<HistoryRecord> getAllHistoryRecord(int userid) {
		// TODO Auto-generated method stub
		List<Integer> ids = hrdao.getAllHistoryRecord(userid);
		List<HistoryRecord> list = new ArrayList<HistoryRecord>();
		for(int i = 0;i < ids.size();i++){
			HistoryRecord hr = hrdao.getRecordByID(ids.get(i));
			list.add(hr);
		}
		return list;
	}

	@Override
	public Object getTotalData(int userid, String key, String value) {
		// TODO Auto-generated method stub
		return hrdao.getTotalData(userid, key, value);
	}

	@Override
	public Object getMaxData(int userid, String key, String value) {
		// TODO Auto-generated method stub
		return hrdao.getMaxData(userid, key, value);
	}

	@Override
	public boolean addToHistoryRecord() {
		// TODO Auto-generated method stub
		TodayRecordService trservice = new TodayRecordServiceImpl();
		List<TodayRecord> list = trservice.getAllTodayRecord();
		for(int i = 0;i < list.size();i++){
			TodayRecord tr = list.get(i);
			if(!hrdao.addToHistoryRecord(tr))
				return false;
		}
		return true;
	}

}
