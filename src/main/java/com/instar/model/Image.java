package com.instar.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class Image{
	
	private int id; // 시퀀스
	private String location; //사진 찍은 위치 (로마)
	private String caption; // 사진 설명
	private String postImage; //포스팅 사진 경로+이름
	
	private User user;
	
//	private List<Likes> likes = new ArrayList<>();
//	private List<Tag> tags = new ArrayList<>();
	
	private int likeCount;
	
	private boolean heart;
	
	private Timestamp createDate;
	private Timestamp updateDate;

	
}







