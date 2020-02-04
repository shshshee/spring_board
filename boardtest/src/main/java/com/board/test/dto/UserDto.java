package com.board.test.dto;

import lombok.Data;

@Data
public class UserDto {
	String userid;
	String name;
	String pwd;

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

}
