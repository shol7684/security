package com.instar.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data //lombok
public class User {
	private int id; // 시퀀스
	private String username; // 사용자 아이디
	private String password; // 암호화된 패스워드
	private String name; // 사용자 이름
	private String website; // 홈페이지 주소
	private String bio; // 자기 소개
	private String email;
	private String phone;
	private String gender;
	private String profileImage; //프로파일 사진 경로+이름
	
	private String provider; // kakao, google, facebook 
	private String providerId;
	
	private List<Image> images = new ArrayList<>();
	
	private Timestamp createDate;
	private Timestamp updateDate;
	
	private String role;
}
