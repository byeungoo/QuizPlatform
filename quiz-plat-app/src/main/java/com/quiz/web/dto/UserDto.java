package com.quiz.web.dto;

import java.util.Date;

public class UserDto {

	private String user_id;  //유저아이디
	private String nickname; //닉네임
	private String regpe_id; //등록자
	private String pwd;      //패스워드
	private String reg_div_cd; //등록 구분 코드 :10 회원, 20 비회원
	private Date   reg_dts;  //등록일
	private Date   mod_dts;  //변경일
	private boolean isLogin; //true면 로그인, false면 로그인x
	private boolean isExis;  //존재하면 true 아니면 false
	
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getRegpe_id() {
		return regpe_id;
	}
	public void setRegpe_id(String regpe_id) {
		this.regpe_id = regpe_id;
	}
	public Date getReg_dts() {
		return reg_dts;
	}
	public void setReg_dts(Date reg_dts) {
		this.reg_dts = reg_dts;
	}
	public Date getMod_dts() {
		return mod_dts;
	}
	public void setMod_dts(Date mod_dts) {
		this.mod_dts = mod_dts;
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
