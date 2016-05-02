package com.crown.dao;

import java.util.List;

import com.crown.bean.HotProduct;
import com.crown.bean.Order;
import com.crown.bean.Product;

public interface InfoDao {
	public List<Product> searchProductByKeyword(String keyword);
	
	public List<Product> searchShopByKeyword(String keyword);

	public int putIntoCart(String userId, String productId);

	public List<Order> getCartInfo(String id);

	public List<HotProduct> getHotProducts();
}
