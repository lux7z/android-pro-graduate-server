package com.crown.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.crown.bean.HotProduct;
import com.crown.bean.Order;
import com.crown.bean.Product;
import com.crown.bean.Shop;
import com.crown.dao.InfoDao;
import com.crown.util.JDBCUtil;


public class InfoDaoImpl implements InfoDao {

	public static Connection conn = null;
	public static Statement stmt = null;
	public static PreparedStatement pstmt = null;
	public static ResultSet rs = null;
	
	@Override
	public List<Product> searchProductByKeyword(String keyword) {
		List<Product> list = new ArrayList<Product>();
		conn = JDBCUtil.getConnection();
		try {
			String sql = "select * from product p, shop s where p.product_name " + "like '%" + keyword + "%'" + " and p.product_shop = s.shop_id";			
			sql = sql + " order by product_id";
	
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				Product product = new Product();
				product.setpId(rs.getInt("product_id"));
				product.setpTypeId(rs.getInt("product_type"));
				product.setpName(rs.getString("product_name"));
				product.setpPrice(rs.getDouble("product_price"));
				product.setpDiscount(rs.getDouble("product_discount"));
				product.setpImgName(rs.getString("product_img_name"));
				product.setpShopId(rs.getInt("product_shop"));
				product.setpDiscription(rs.getString("product_description"));
				product.setpArea(rs.getString("shop_area"));
				list.add(product);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, stmt, pstmt, rs);
		}
		return list;
	}

	@Override
	public List<Product> searchShopByKeyword(String keyword) {
		List<Product> list = new ArrayList<Product>();
		conn = JDBCUtil.getConnection();
		try {
			String sql = "select * from shop where shop_name " + "like '%" + keyword + "%'";			
			sql = sql + " order by info_id";
	
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				Product product = new Product();
				//TODO: 从结果集中拿出数据
				
				list.add(product);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, stmt, pstmt, rs);
		}
		return list;
	}

	@Override
	public int putIntoCart(String userId, String productId) {
		conn = JDBCUtil.getConnection();
		int row = 0;
		try {                                                      
			pstmt = conn.prepareStatement("insert into cart_order values(0, ?, ?, 1, 1)");
			pstmt.setInt(1, Integer.parseInt(productId));
			pstmt.setInt(2, Integer.parseInt(userId));
			row = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, stmt, pstmt, rs);
		}
		return row;
	}

	@Override
	public List<Order> getCartInfo(String id) {
		List<Order> list = new ArrayList<Order>();
		boolean flag = false;
		conn = JDBCUtil.getConnection();
		try {
			String sql = "select * from cart_order a, product b, shop c where a.order_user = " + id 
					+ " and order_product = product_id"
					+ " and shop_id = product_shop"
					+ " and order_state != 0";	//order_state = 0  表示订单已删除		
			sql = sql + " order by order_id";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				flag = true;
				Order order = new Order();
				order.setOrderId(rs.getInt("order_id"));
				order.setOrderState(rs.getInt("order_state"));
				order.setProductCount(rs.getInt("order_product_cnt"));
				order.setProductDescription(rs.getString("product_description"));
				order.setProductId(rs.getInt("product_id"));
				order.setProductImgName(rs.getString("product_img_name"));
				order.setProductPrice(rs.getDouble("product_price"));
				order.setShopName(rs.getString("shop_name"));
				order.setUserId(Integer.parseInt(id));
				list.add(order);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, stmt, pstmt, rs);
		}
		if(flag)
			return list;
		return null;
	}

	@Override
	public List<HotProduct> getHotProducts() {
		List<HotProduct> list = new ArrayList<HotProduct>();
		boolean flag = false;
		conn = JDBCUtil.getConnection();
		try {
			String sql = "select * from hot_product a, product b, shop c where a.hot_product = b.product_id and c.shop_id = b.product_shop";	
			sql = sql + " order by hot_id";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				flag = true;
				HotProduct hot = new HotProduct();
				Product product = new Product();
				Shop shop = new Shop();
				
				hot.setHotId(rs.getInt("hot_id"));
				hot.setProComment(rs.getString("user_comment"));
				
				shop.setShopId(rs.getInt("shop_id"));
				shop.setShopName(rs.getString("shop_name"));
				shop.setShopOwner(rs.getInt("shop_owner"));
				shop.setShopArea(rs.getString("shop_area"));
				
				product.setpId(rs.getInt("product_id"));
				product.setpTypeId(rs.getInt("product_type"));
				product.setpName(rs.getString("product_name"));
				product.setpPrice(rs.getDouble("product_price"));
				product.setpDiscount(rs.getDouble("product_discount"));
				product.setpImgName(rs.getString("product_img_name"));
				product.setpShopId(rs.getInt("product_shop"));
				product.setpDiscription(rs.getString("product_description"));
				product.setpArea(rs.getString("shop_area"));
				
				hot.setProduct(product);
				hot.setShop(shop);
				list.add(hot);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, stmt, pstmt, rs);
		}
		if(flag)
			return list;
		return null;
	}

}
