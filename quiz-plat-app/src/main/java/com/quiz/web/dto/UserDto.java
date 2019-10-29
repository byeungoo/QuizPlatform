package com.quiz.web.dto;

import java.util.Date;

public class UserDto extends BaseDto{

	private String nickname; //닉네임
	private String pwd;      //패스워드
	private String reg_div_cd; //등록 구분 코드 :10 회원, 20 비회원
	private boolean isLogin; //true면 로그인, false면 로그인x
	private boolean isExis;  //존재하면 true 아니면 false
	
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getReg_div_cd() {
		return reg_div_cd;
	}
	public void setReg_div_cd(String reg_div_cd) {
		this.reg_div_cd = reg_div_cd;
	}
	public boolean isLogin() {
		return isLogin;
	}
	public void setLogin(boolean isLogin) {
		this.isLogin = isLogin;
	}
	public boolean isExis() {
		return isExis;
	}
	public void setExis(boolean isExis) {
		this.isExis = isExis;
	}
}
