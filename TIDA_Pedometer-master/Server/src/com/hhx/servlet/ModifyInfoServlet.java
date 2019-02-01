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

import com.google.gson.Gson;
import com.hhx.entity.User;
import com.hhx.service.UserService;
import com.hhx.service.impl.UserServiceImpl;

public class ModifyInfoServlet extends HttpServlet {
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
		int userid = Integer.parseInt(useridStr);
		
		Map<String, String>map = new HashMap<String, String>();
		String[]keys = {"user_name", "password", "phone_no", "photo", "height", "weight", "remark"};
		for(int i = 0;i < keys.length;i++){
			if(!"".equals(request.getParameter(keys[i]))){
				map.put(keys[i], request.getParameter(keys[i]));
			}
		}
		
		UserService userservice = new UserServiceImpl();
		User user = userservice.modifyInfo(map, userid);
		Gson gson = new Gson();
		String gsonStr = gson.toJson(user);
		out.println(gsonStr);
	}

}
