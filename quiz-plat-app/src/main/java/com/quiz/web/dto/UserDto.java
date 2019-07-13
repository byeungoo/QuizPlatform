package com.quiz.web.dto;

import java.util.Date;

public class UserDto {

	private String user_id;  //사용자ID
	private String nickname; //닉네임
	private String regpe_id; //등록자
	private String pwd;
	private String reg_div_cd;
	private Date   reg_dts;  //등록일
	private Date   mod_dts;  //변경일
	
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
}
