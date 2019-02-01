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
import com.hhx.entity.FriendApplication;
import com.hhx.entity.User;
import com.hhx.service.UserService;
import com.hhx.service.impl.UserServiceImpl;

public class GetFriendsListServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		
		UserService userservice = new UserServiceImpl();
		Gson gson = new Gson();
		String listgson = "";
		
		if(type == 1){//获取好友列表
			List<User> list = userservice.findFriends(userid);
			listgson = gson.toJson(list);
		}else if(type == 2){//获取全部好友请求
			List<FriendApplication> list = userservice.collectApplication(userid);
			listgson = gson.toJson(list);
		}else if(type == 3){//获取所有不是好友的用户
			List<User> list = userservice.findStrangers(userid);
			listgson = gson.toJson(list);
		}
		out.println(listgson);
	}

}
