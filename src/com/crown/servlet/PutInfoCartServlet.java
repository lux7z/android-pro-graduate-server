package com.crown.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.crown.bean.JsonResult;
import com.crown.dao.InfoDao;
import com.crown.dao.impl.InfoDaoImpl;
import com.google.gson.Gson;

@WebServlet("/PutInfoCartServlet")
public class PutInfoCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = request.getParameter("userId");
		String productId = request.getParameter("productId");
		System.out.println("UID : " + userId + "  PROID : " + productId);
		
		InfoDao infoDao = new InfoDaoImpl();
		JsonResult jsonR = new JsonResult();
		if (infoDao.putIntoCart(userId, productId) > 0) {
			jsonR.setResultCode(1);
		}
		else {
			jsonR.setResultCode(0);
		}
		response.setContentType("application/json");
		String json = new Gson().toJson(jsonR);
		response.getWriter().write(json);
	}

}
