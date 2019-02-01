package com.hhx.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.hhx.entity.TodayRecord;
import com.hhx.service.TodayRecordService;
import com.hhx.service.impl.TodayRecordServiceImpl;

public class GetAllTodayRecordServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");	
		PrintWriter out = response.getWriter();	   	
		HttpSession session = request.getSession();	
		request.setCharacterEncoding("utf-8");
		
		String useridStr = request.getParameter("userid");
		String typeStr = request.getParameter("type");
		int userid = Integer.parseInt(useridStr);
		int type = Integer.parseInt(typeStr);
		
		TodayRecordService trservice = new TodayRecordServiceImpl();
		Gson gson = new Gson();
		String gsonStr = "";
		if(type == 1){//查询自己今日的记录
			TodayRecord tr = trservice.getTodayRecord(userid);
			gsonStr = gson.toJson(tr);
		}else if(type == 2){//查询好友今日记录排行
			List<TodayRecord> list = trservice.getFriendsTodayRecord(userid);
			gsonStr = gson.toJson(list);
		}else if(type == 3){//查询全国今日记录排行
			List<TodayRecord> list = trservice.getAllTodayRecord();
			gsonStr = gson.toJson(list);
		}
		out.println(gsonStr);
	}

}
