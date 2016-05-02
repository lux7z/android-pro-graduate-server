package com.crown.dao;

import com.crown.bean.User;

public interface UserDao {
	public User login(String email, String password);

	public int register(String username, String email, String password);
}
