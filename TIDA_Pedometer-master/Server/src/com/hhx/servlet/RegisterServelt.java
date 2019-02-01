package com.hhx.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.hhx.entity.User;
import com.hhx.service.UserService;
import com.hhx.service.impl.UserServiceImpl;

public class RegisterServelt extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");	
		PrintWriter out = response.getWriter();	   	
		HttpSession session = request.getSession();	
		request.setCharacterEncoding("utf-8");
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String genderStr = request.getParameter("gender");
		String heightStr = request.getParameter("height");
		String weightStr = request.getParameter("weight");
		int gender = Integer.parseInt(genderStr);
		int height = Integer.parseInt(heightStr);
		float weight = Float.parseFloat(weightStr);
		
		UserService userservice = new UserServiceImpl();
		User user = userservice.register(username, password, gender, height, weight);
		
		Gson gson = new Gson();
		String usergson = gson.toJson(user);
		out.println(usergson);
	}

}
