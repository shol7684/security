package com.instar.service;

import com.instar.model.User;

public interface UserService {

	void join(User user);

	User joinCheck(String username);

	void oauthJoin(User user);

}
