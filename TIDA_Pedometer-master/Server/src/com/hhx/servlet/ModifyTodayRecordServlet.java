package com.hhx.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hhx.entity.TodayRecord;
import com.hhx.service.TodayRecordService;
import com.hhx.service.impl.TodayRecordServiceImpl;

public class ModifyTodayRecordServlet extends HttpServlet {
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
		int userid = Integer.parseInt(useridStr);
		TodayRecordService trservice = new TodayRecordServiceImpl();
		
		Map<String, String>map = new HashMap<String, String>();
		String[]keys = {"steps", "active_minutes", "calories", "shared"};
		for(int i = 0;i < keys.length;i++){
			if(!"".equals(request.getParameter(keys[i]))){
				map.put(keys[i], request.getParameter(keys[i]));
			}
		}
		out.println(trservice.modifyTodayRecord(userid, map));
	}

}
