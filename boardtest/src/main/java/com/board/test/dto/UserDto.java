package com.board.test.dto;

import java.security.Timestamp;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(exclude = "pwd")
public class UserDto {

	private String userid; // 유저아이디
	private String name; // 유저 이름
	private String pwd; // 유저 password

	// 네이버 로그인 시 사용
	private int sns_idx; // index
	private String sns_name; // 이름
	private String sns_email; // 이메일
	private String sns_id; // 고유 id 값
	private Timestamp date; // 로그인 날짜

	// 네이버 로그인
	public UserDto(String sns_name, String sns_email, String sns_id, Timestamp date) {
		this.sns_name = sns_name;
		this.sns_email = sns_email;
		this.sns_id = sns_id;
		this.date = date;
	}

	// 일반회원
	public UserDto(String userid, String name, String pwd) {
		this.userid = userid;
		this.name = name;
		this.pwd = pwd;
	}

	@Override
	public String toString() {
		return "UserDto [userid=" + userid + ", name=" + name + ", pwd=" + pwd + "]";
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public int getSns_idx() {
		return sns_idx;
	}

	public void setSns_idx(int sns_idx) {
		this.sns_idx = sns_idx;
	}

	public String getSns_name() {
		return sns_name;
	}

	public void setSns_name(String sns_name) {
		this.sns_name = sns_name;
	}

	public String getSns_email() {
		return sns_email;
	}

	public void setSns_email(String sns_email) {
		this.sns_email = sns_email;
	}

	public String getSns_id() {
		return sns_id;
	}

	public void setSns_id(String sns_id) {
		this.sns_id = sns_id;
	}

}
