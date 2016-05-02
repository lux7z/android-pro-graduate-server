package com.crown.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.crown.bean.CartResult;
import com.crown.bean.Order;
import com.crown.dao.InfoDao;
import com.crown.dao.impl.InfoDaoImpl;
import com.google.gson.Gson;

@WebServlet("/GetCartServlet")
public class GetCartServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("userId");
		System.out.println("HHHHUID : " + id);
		
		InfoDao infoDao = new InfoDaoImpl();
		List<Order> orders = infoDao.getCartInfo(id);
		CartResult cr = new CartResult();
		if(orders != null) {
			cr.setResultCode(1);
		}
		else {
			cr.setResultCode(0);
		}
		cr.setOrders(orders);
		response.setContentType("application/json");
		response.getWriter().write(new Gson().toJson(cr));
	}

}
