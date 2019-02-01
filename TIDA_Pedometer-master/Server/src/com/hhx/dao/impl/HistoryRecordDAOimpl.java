package com.hhx.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.hhx.dao.HistoryRecordDAO;
import com.hhx.dbUtil.DBManager;
import com.hhx.entity.HistoryRecord;
import com.hhx.entity.TodayRecord;

public class HistoryRecordDAOimpl implements HistoryRecordDAO {
	DBManager dbm = new DBManager();
	
	@Override
	public List<Integer> getAllHistoryRecord(int userid) {
		// TODO Auto-generated method stub
		String sql = "select record_id from Record where user_id = ?";
		ResultSet rs = dbm.execQuery(sql, userid);
		List<Integer> ids = new ArrayList<Integer>();
		try {
			while(rs.next()){
				ids.add(rs.getInt(1));
			}
			return ids;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public HistoryRecord getRecordByID(int record_id) {
		// TODO Auto-generated method stub
		String sql = "select * from Record where record_id = ?";
		ResultSet rs = dbm.execQuery(sql, record_id);
		try {
			if(rs.next()){
				HistoryRecord hr = new HistoryRecord(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getFloat(4),
						rs.getFloat(5), rs.getString(6));
				return hr;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Object getTotalData(int userid, String key, String value) {
		// TODO Auto-generated method stub
		String sql = "select sum(?) from Record where user_id = ? and date like ? group by user_id";
		ResultSet rs = dbm.execQuery(sql, key, userid, value + "%");
		try {
			if(rs.next()){
				Object o = rs.getObject(1);
				return o;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Object getMaxData(int userid, String key, String value) {
		// TODO Auto-generated method stub
		String sql = "select max(?) from Record where user_id = ? and date like ? group by user_id";
		ResultSet rs = dbm.execQuery(sql, key, userid, value + "%");
		try {
			if(rs.next()){
				Object o = rs.getObject(1);
				return o;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean addToHistoryRecord(TodayRecord tr) {
		// TODO Auto-generated method stub
		if(tr.getSteps() > 0){
			String sql = "insert into Record values(?, ?, ?, ?, ?)";
			return dbm.execUpdate(sql, tr.getUserid(), tr.getSteps(), tr.getCalories(), tr.getMinutes(), tr.getDate()) > 0;
		}
		return true;
	}

}
