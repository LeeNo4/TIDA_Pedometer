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
import com.hhx.entity.User;
import com.hhx.service.UserService;
import com.hhx.service.impl.UserServiceImpl;

public class QueryUserServlet extends HttpServlet {
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
		
		String queryStr = request.getParameter("query");
		UserService userservice = new UserServiceImpl();
		List<User> users = userservice.queryUserByName(queryStr);
		
		Gson gson = new Gson();
		String gsonStr = gson.toJson(users);
		out.println(gsonStr);
	}

}
