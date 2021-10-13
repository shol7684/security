package com.instar.model;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class Likes {
	private int id; // 시퀀스
	
	private User user; // id, username, profileImage
	
	private Image image; // 기본 :  image_id
	
	private Timestamp createDate;
	private Timestamp updateDate;
}
