package com.hhx.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hhx.service.UserService;
import com.hhx.service.impl.UserServiceImpl;

public class ApplyFriendServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");	
		PrintWriter out = response.getWriter();	   	
		HttpSession session = request.getSession();	
		request.setCharacterEncoding("utf-8");
		
		String useridStr = request.getParameter("userid");
		String friendStr = request.getParameter("friendid");
		String typeStr = request.getParameter("type");
		int userid = Integer.parseInt(useridStr);
		int friendid = Integer.parseInt(friendStr);
		int type = Integer.parseInt(typeStr);
		
		UserService userservice = new UserServiceImpl();
		
		switch(type){
		case 1://发起好友请求
			out.println(userservice.addFriend(userid, friendid));
			break;
		case 2://接受好友请求
			out.println(userservice.acceptApplication(userid, friendid, 2));
			break;
		case 3://取消好友
			out.println(userservice.cancelFriends(userid, friendid));
			break;
		case 4://忽略好友请求
			out.println(userservice.acceptApplication(userid, friendid, 1));
			break;
		}

	}

}
