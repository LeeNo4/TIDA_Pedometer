package com.hhx.dbUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBManager {
	private Connection conn;
	private PreparedStatement pstmt;
	
	public DBManager(){
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	private void openConnection(){  
		try {
			this.conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=Pedometer", "sa", "hhx");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public int execUpdate(String sql, Object... params){
		this.openConnection();
		try {
			this.pstmt = this.conn.prepareStatement(sql);
			
			for(int i=0; i< params.length; i++){
				this.pstmt.setObject(i+1, params[i]);
			}
			
			return this.pstmt.executeUpdate();	
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		} finally{
			this.closeConnection();
		}
		
	}
	
	public ResultSet execQuery(String sql, Object... params){
		this.openConnection();
		try {
			this.pstmt = this.conn.prepareStatement(sql);
			
			for(int i=0; i< params.length; i++){
				this.pstmt.setObject(i+1, params[i]);
			}

			return this.pstmt.executeQuery();		
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}	
	}

	public void closeConnection(){	
		try {
			if(this.pstmt!=null){
				this.pstmt.close(); 
			}	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			if(this.conn!=null){
				this.conn.close(); 
			}	
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}
}
