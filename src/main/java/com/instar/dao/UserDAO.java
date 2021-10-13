package com.instar.dao;

import com.instar.model.User;

public interface UserDAO {

	public void join(User user);

	public User login(String username);

	public User joinCheck(String username);

	public void oauthJoin(User user);

}
