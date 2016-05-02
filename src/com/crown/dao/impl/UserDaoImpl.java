package com.crown.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.crown.bean.User;
import com.crown.dao.UserDao;
import com.crown.util.JDBCUtil;

public class UserDaoImpl implements UserDao{

	public static Connection conn = null;
	public static Statement stmt = null;
	public static PreparedStatement pstmt = null;
	public static ResultSet rs = null;
	
	@Override
	public User login(String email, String password) {
		conn = JDBCUtil.getConnection();
		User user = new User();
		boolean success = false;
		try {
			pstmt = conn.prepareStatement("select * from user where email = ? and password = ?");
			pstmt.setString(1, email);
			pstmt.setString(2, password);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				success = true;
				user.setUserId(rs.getInt("uid"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setEmail(rs.getString("email"));
				user.setRank(rs.getInt("rank"));
				user.setUserType(rs.getInt("user_type"));
				user.setUserState(rs.getInt("state"));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, stmt, pstmt, rs);
		}
		if(success) {
			return user;
		} else {
			return null;
		}
	}

	@Override
	public int register(String username, String email, String password) {
		conn = JDBCUtil.getConnection();
		int row = 0;
		try {
			pstmt = conn.prepareStatement("insert into user values(0, ?, ?, ?, 0, 1, 1)");
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			pstmt.setString(3, email);
			row = pstmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, stmt, pstmt, rs);
		}
		return row;
	}

}
