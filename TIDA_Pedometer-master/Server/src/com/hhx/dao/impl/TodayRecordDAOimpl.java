package com.hhx.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.hhx.dao.TodayRecordDAO;
import com.hhx.dao.UserDAO;
import com.hhx.dbUtil.DBManager;
import com.hhx.entity.TodayRecord;

public class TodayRecordDAOimpl implements TodayRecordDAO {
	DBManager dbm = new DBManager();
	
	@Override
	public boolean deleteTodayRecord(TodayRecord tr) {
		// TODO Auto-generated method stub
		String sql = "delete from TodayRecord where record_id = ?";
		return dbm.execUpdate(sql, tr.getRecord_id()) > 0;
	}

	@Override
	public List<Integer> getAllTodayRecord() {
		// TODO Auto-generated method stub
		String sql = "select * from TodayRecord order by steps desc";
		List<Integer> ids = new ArrayList<Integer>();
		ResultSet rs = dbm.execQuery(sql);
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
	public TodayRecord getTodayRecord(int userid) {
		// TODO Auto-generated method stub
		String sql = "select * from TodayRecord where user_id = ?";
		ResultSet rs = dbm.execQuery(sql, userid);
		try {
			if(rs.next()){
				TodayRecord tr = new TodayRecord(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getFloat(4),
						rs.getInt(5), rs.getInt(6), rs.getFloat(7), rs.getInt(8));
				return tr;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public TodayRecord getRecordByID(int record_id) {
		// TODO Auto-generated method stub
		String sql = "select * from TodayRecord where record_id = ?";
		ResultSet rs = dbm.execQuery(sql, record_id);
		try {
			if(rs.next()){
				TodayRecord tr = new TodayRecord(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getFloat(4),
						rs.getInt(5), rs.getInt(6), rs.getFloat(7), rs.getInt(8));
				return tr;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<TodayRecord> getFriendsTodayRecord(int userid) {
		// TODO Auto-generated method stub
		String sql = "select TodayRecord.* from TodayRecord, Friends where Friends.user_id = ? and TodayRecord.user_id"
				+ " = Friends.Use_user_id order by steps desc";
		List<TodayRecord> list = new ArrayList<TodayRecord>();
		ResultSet rs = dbm.execQuery(sql, userid);
		try {
			while(rs.next()){
				TodayRecord tr = new TodayRecord(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getFloat(4),
						rs.getInt(5), rs.getInt(6), rs.getFloat(7), rs.getInt(8));
				list.add(tr);
			}
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean createTodayRecord() {
		// TODO Auto-generated method stub
		UserDAO userdao = new UserDAOimpl();
		List<Integer> ids = userdao.findAllUsersID();
		String sql = "insert into TodayRecord values(?, now(), 0, 0, 10000, 0, 1)";
		for(int i = 0;i < ids.size();i++){
			if(dbm.execUpdate(sql, ids.get(i)) < 0)
				return false;
		}
		return true;
	}

	@Override
	public boolean modifyTodayRecord(int userid, String key, String value) {
		// TODO Auto-generated method stub
		String sql = "update TodayRecord set ? = ? + ? where user_id = ?";
		if("active_minutes".equals(key) || "calories".equals(key)){
			float tmp = Float.parseFloat(value);
			return dbm.execUpdate(sql, key, key, tmp, userid) > 0;
		}else if("steps".equals(key) || "shared".equals(key)){
			int tmp = Integer.parseInt(value);
			return dbm.execUpdate(sql, key, key, tmp, userid) > 0;
		}
		return false;
	}

}
