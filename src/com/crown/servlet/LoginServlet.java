package com.crown.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.crown.bean.LoginResult;
import com.crown.bean.User;
import com.crown.dao.UserDao;
import com.crown.dao.impl.UserDaoImpl;
import com.google.gson.Gson;
import com.sun.org.apache.bcel.internal.generic.NEW;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		System.out.println("Email : " + email + " Password : " + password);
		UserDao userDao = new UserDaoImpl();
		User user = userDao.login(email, password);
		PrintWriter mWriter = response.getWriter();
		LoginResult lr = new LoginResult();
		if(user != null) {
			lr.setResultCode(1); // resultCode = 1 表示登录成功
			
		}
		else {
			lr.setResultCode(0);
		}
		lr.setUser(user);
		mWriter.write(new Gson().toJson(lr));
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
