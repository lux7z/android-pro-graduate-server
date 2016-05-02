package com.crown.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sound.midi.MidiDevice.Info;

import com.crown.bean.Product;
import com.crown.bean.ProductResult;
import com.crown.dao.InfoDao;
import com.crown.dao.impl.InfoDaoImpl;
import com.google.gson.Gson;


@WebServlet("/SearchProductServlet")
public class SearchProductServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		String keyword = request.getParameter("keyword");
		InfoDao infoDao = new InfoDaoImpl();
		List<Product> list = infoDao.searchProductByKeyword(keyword);
		ProductResult productResult = new ProductResult();
		PrintWriter out = response.getWriter();
		if(list != null) {
			productResult.setResultCode(1);
		}
		else {
			productResult.setResultCode(0);
		}
		productResult.setProducts(list);
		String json = new Gson().toJson(productResult);
		out.write(json);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
