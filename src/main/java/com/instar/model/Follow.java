package com.instar.model;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class Follow {
	private int id;
	
	// 중간 테이블 생성됨.
	// fromUser가 toUser를 following 함.
	// toUser를 fromUser가 follower 함.
	
	private User fromUser;
	
	private User toUser;
	
	private boolean followState;
	
	private Timestamp createDate;
	private Timestamp updateDate;
}
