package com.crown.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.crown.bean.HotProduct;
import com.crown.bean.HotProductResult;
import com.crown.dao.InfoDao;
import com.crown.dao.impl.InfoDaoImpl;
import com.google.gson.Gson;

@WebServlet("/GetHotProductServlet")
public class GetHotProductServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		InfoDao infoDao = new InfoDaoImpl();
		List<HotProduct> hotProducts = infoDao.getHotProducts();
		HotProductResult hpr = new HotProductResult();
		if(hotProducts != null)
			hpr.setResultCode(1);
		else 
			hpr.setResultCode(0);
		hpr.setHotProducts(hotProducts);
		response.setContentType("application/json");
		response.getWriter().write(new Gson().toJson(hpr));
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
