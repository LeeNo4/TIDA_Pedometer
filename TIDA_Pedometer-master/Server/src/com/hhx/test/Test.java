package com.hhx.test;

import com.hhx.dao.impl.UserDAOimpl;

public class Test {
	public static void main(String[]avgs){
		UserDAOimpl udi = new UserDAOimpl();
		System.out.println(udi.findUser("11", "111"));
		
	}
}
