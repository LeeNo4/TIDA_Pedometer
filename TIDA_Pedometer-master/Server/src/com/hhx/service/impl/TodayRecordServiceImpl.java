package com.hhx.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.hhx.dao.TodayRecordDAO;
import com.hhx.dao.impl.TodayRecordDAOimpl;
import com.hhx.entity.TodayRecord;
import com.hhx.service.TodayRecordService;

public class TodayRecordServiceImpl implements TodayRecordService {
	TodayRecordDAO trdao = new TodayRecordDAOimpl();
	
	@Override
	public List<TodayRecord> getAllTodayRecord() {
		// TODO Auto-generated method stub
		List<Integer> ids = trdao.getAllTodayRecord();
		List<TodayRecord> list = new ArrayList<TodayRecord>();
		for(int i = 0;i < ids.size();i++){
			TodayRecord tr = trdao.getRecordByID(ids.get(i));
			list.add(tr);
		}
		return list;
	}

	@Override
	public boolean deleteTodayRecord(TodayRecord tr) {
		// TODO Auto-generated method stub
		return trdao.deleteTodayRecord(tr);
	}

	@Override
	public TodayRecord getTodayRecord(int userid) {
		// TODO Auto-generated method stub
		return trdao.getTodayRecord(userid);
	}

	@Override
	public List<TodayRecord> getFriendsTodayRecord(int userid) {
		// TODO Auto-generated method stub
		return trdao.getFriendsTodayRecord(userid);
	}

	@Override
	public TodayRecord getRecordByID(int record_id) {
		// TODO Auto-generated method stub
		return trdao.getRecordByID(record_id);
	}

	@Override
	public boolean modifyTodayRecord(int userid, Map<String, String> map) {
		// TODO Auto-generated method stub
		Iterator<Map.Entry<String, String>>iterator = map.entrySet().iterator();
		while(iterator.hasNext()){
			Map.Entry<String, String>tmp = iterator.next();
			if(!trdao.modifyTodayRecord(userid, tmp.getKey(), tmp.getValue()))
				return false;
		}
		return true;
	}

	@Override
	public boolean createTodayRecord() {
		// TODO Auto-generated method stub
		return trdao.createTodayRecord();
	}

}
