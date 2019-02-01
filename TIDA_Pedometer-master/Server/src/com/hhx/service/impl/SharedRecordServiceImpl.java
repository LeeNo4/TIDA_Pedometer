package com.hhx.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.hhx.dao.SharedRecordDAO;
import com.hhx.dao.impl.SharedRecordDAOimpl;
import com.hhx.entity.SharedRecord;
import com.hhx.service.SharedRecordService;

public class SharedRecordServiceImpl implements SharedRecordService {
	SharedRecordDAO srdao = new SharedRecordDAOimpl();
	
	@Override
	public SharedRecord queryRecord(int recordid) {
		// TODO Auto-generated method stub
		return srdao.queryRecord(recordid);
	}

	@Override
	public List<SharedRecord> getSharedCollection(int userid) {
		// TODO Auto-generated method stub
		List<Integer> ids = srdao.getSharedCollection(userid);
		List<SharedRecord> list = new ArrayList<SharedRecord>();
		for(int i = 0;i < ids.size();i++){
			SharedRecord sr = srdao.queryRecord(ids.get(i));
			list.add(sr);
		}
		return list;
	}

	@Override
	public List<SharedRecord> getAllSharedCollection() {
		// TODO Auto-generated method stub
		List<Integer> ids = srdao.getAllSharedCollection();
		List<SharedRecord> list = new ArrayList<SharedRecord>();
		for(int i = 0;i < ids.size();i++){
			SharedRecord sr = srdao.queryRecord(ids.get(i));
			list.add(sr);
		}
		return list;
	}

	@Override
	public boolean deleteRecord(int recordid) {
		// TODO Auto-generated method stub
		return srdao.deleteRecord(recordid);
	}

	@Override
	public boolean shareRecord(int userid, int steps, float calories, float minutes, String photo, String remark) {
		// TODO Auto-generated method stub
		return srdao.shareRecord(userid, steps, calories, minutes, photo, remark);
	}

	@Override
	public List<SharedRecord> getFriendsSharedCollection(int userid) {
		// TODO Auto-generated method stub
		return srdao.getFriendsSharedCollection(userid);
	}

}
