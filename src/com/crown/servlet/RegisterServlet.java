package com.crown.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.crown.bean.RegisterResult;
import com.crown.bean.User;
import com.crown.dao.UserDao;
import com.crown.dao.impl.UserDaoImpl;
import com.google.gson.Gson;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
       
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		UserDao userDao = new UserDaoImpl();
		int row = userDao.register(username, email, password);
		response.setContentType("application/json");
		RegisterResult rr = null;
		//User user = null;
		String gson = null;
		PrintWriter out = response.getWriter();
		if(row > 0) {
			rr = new RegisterResult();
			rr.setResultCode(1);
			gson = new Gson().toJson(rr);
			out.write(gson);
		}
		else {
			rr = new RegisterResult();
			rr.setResultCode(0);
			gson = new Gson().toJson(rr);
			out.write(gson);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
