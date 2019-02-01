package com.hhx.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hhx.service.HistoryRecordService;
import com.hhx.service.impl.HistoryRecordServiceImpl;

public class GetHistoryDataServlet extends HttpServlet {
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
		String attribute = request.getParameter("attribute");
		String range = request.getParameter("range");
		int userid = Integer.parseInt(useridStr);
		
		HistoryRecordService hrservice = new HistoryRecordServiceImpl();
		Object o = new Object();
		if("max".equals(typeStr)){
			o = hrservice.getMaxData(userid, attribute, range);
		}else if("sum".equals(typeStr)){
			o = hrservice.getTotalData(userid, attribute, range);
		}
		out.println(o);
	}

}
