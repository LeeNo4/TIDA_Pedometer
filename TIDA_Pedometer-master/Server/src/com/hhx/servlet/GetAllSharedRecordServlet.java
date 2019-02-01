package com.hhx.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.hhx.entity.SharedRecord;
import com.hhx.service.SharedRecordService;
import com.hhx.service.impl.SharedRecordServiceImpl;

public class GetAllSharedRecordServlet extends HttpServlet {
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
		
		SharedRecordService srservice = new SharedRecordServiceImpl();
		List<SharedRecord> list = new ArrayList<SharedRecord>();
		String useridStr = request.getParameter("userid");
		String typeStr = request.getParameter("type");
		if("".equals(useridStr)){
			list = srservice.getAllSharedCollection();
		}else if("1".equals(typeStr)){
			int userid = Integer.parseInt(useridStr);
			list = srservice.getSharedCollection(userid);
		}else if("2".equals(typeStr)){
			int userid = Integer.parseInt(useridStr);
			list = srservice.getFriendsSharedCollection(userid);
		}
		Gson gson = new Gson();
		String gsonStr = gson.toJson(list);
		out.println(gsonStr);
	}
}
