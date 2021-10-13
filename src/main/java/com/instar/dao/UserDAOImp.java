package com.instar.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.instar.model.User;

@Repository
public class UserDAOImp implements UserDAO {

	@Autowired
	private SqlSession sql;
	
	private String namespace = "UserDAO";
	
	
	public void join(User user) {
		sql.insert(namespace + ".join" , user);
	}


	@Override
	public User login(String username) {
		return sql.selectOne(namespace + ".login" ,username);
	}


	@Override
	public User joinCheck(String username) {
		return sql.selectOne(namespace + ".joinCheck" , username);
	}


	@Override
	public void oauthJoin(User user) {
		sql.insert(namespace + ".oauthJoin" , user);
	}
	

}
