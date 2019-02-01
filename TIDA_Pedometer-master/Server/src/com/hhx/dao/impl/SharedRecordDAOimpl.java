package com.hhx.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.hhx.dao.SharedRecordDAO;
import com.hhx.dbUtil.DBManager;
import com.hhx.entity.SharedRecord;

public class SharedRecordDAOimpl implements SharedRecordDAO {
	DBManager dbm = new DBManager();
	
	@Override
	public SharedRecord queryRecord(int recordid) {
		// TODO Auto-generated method stub
		String sql = "select * from SharedRecord where record_id = ?";
		ResultSet rs = dbm.execQuery(sql, recordid);
		try {
			if(rs.next()){
				SharedRecord sr = new SharedRecord(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getFloat(4),
						rs.getFloat(5), rs.getString(6), rs.getString(7), rs.getDate(8).toString());
				return sr;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Integer> getSharedCollection(int userid) {
		// TODO Auto-generated method stub
		String sql = "select record_id from SharedRecord where userid = ? order by time desc";
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
	public boolean deleteRecord(int recordid) {
		// TODO Auto-generated method stub
		String sql = "delete from SharedRecord where record_id = ?";
		return dbm.execUpdate(sql, recordid) > 0;
	}

	@Override
	public List<Integer> getAllSharedCollection() {
		// TODO Auto-generated method stub
		String sql = "select record_id from SharedRecord order by time desc";
		ResultSet rs = dbm.execQuery(sql);
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
	public boolean shareRecord(int userid, int steps, float calories, float minutes, String photo, String remark) {
		// TODO Auto-generated method stub
		String sql = "insert into SharedRecord values(?, ?, ?, ?, ?, ?, now())";
		return dbm.execUpdate(sql, userid, steps, calories, minutes, photo, remark) > 0;
	}

	@Override
	public List<SharedRecord> getFriendsSharedCollection(int userid) {
		// TODO Auto-generated method stub
		String sql = "select SharedRecord.* from SharedRecord, Friends where userid = ? and Friends.Use_user_id = "
				+ "SharedRecord.user_id order by time desc";
		ResultSet rs = dbm.execQuery(sql, userid);
		List<SharedRecord> list = new ArrayList<SharedRecord>();
		try {
			while(rs.next()){
				SharedRecord sr = new SharedRecord(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getFloat(4),
						rs.getFloat(5), rs.getString(6), rs.getString(7), rs.getDate(8).toString());
				list.add(sr);
			}
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
