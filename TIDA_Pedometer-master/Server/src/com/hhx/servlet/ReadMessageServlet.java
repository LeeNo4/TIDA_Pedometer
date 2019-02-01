package com.hhx.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hhx.service.MessageService;
import com.hhx.service.impl.MessageServiceImpl;

public class ReadMessageServlet extends HttpServlet {
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
		String messageidStr = request.getParameter("message_id");
		int userid = Integer.parseInt(useridStr);
		int messageid = Integer.parseInt(messageidStr);
		
		MessageService ms = new MessageServiceImpl();
		out.println(ms.readMessage(userid, messageid));
	}

}
