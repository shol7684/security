package com.instar.model;

import java.sql.Timestamp;

import lombok.Data;

// 태그 검색은 안함.
@Data
public class Tag {

	private int id;
	private String name;
	
	private Timestamp createDate;
}
