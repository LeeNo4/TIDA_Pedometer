package com.hhx.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hhx.entity.User;
import com.hhx.service.UserService;
import com.hhx.service.impl.UserServiceImpl;

public class CheckNameServlet extends HttpServlet {
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
		
		String username = request.getParameter("username");
		UserService userservice = new UserServiceImpl();
		User user = userservice.findUserByName(username);
		if(user == null)
			out.println("existed");
		else
			out.println("not existed");
	}
}
