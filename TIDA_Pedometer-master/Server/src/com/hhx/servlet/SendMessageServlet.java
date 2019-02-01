package com.hhx.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hhx.service.MessageService;
import com.hhx.service.impl.MessageServiceImpl;

public class SendMessageServlet extends HttpServlet {
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
		
		String manageridStr = request.getParameter("manager_id");
		String content = request.getParameter("content");
		String photosStr = request.getParameter("photos");
		int manager_id = Integer.parseInt(manageridStr);
		String[] tmp = photosStr.split("SPLIT");
		List<String> photos = Arrays.asList(tmp);
		
		MessageService ms = new MessageServiceImpl();
		out.println(ms.sendMessage(manager_id, content, photos));
	}

}
