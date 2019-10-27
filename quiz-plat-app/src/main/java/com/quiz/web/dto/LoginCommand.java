package com.quiz.web.dto;

public class LoginCommand extends BaseDto{
    
    private String  pwd;
    private boolean rememberId; //로그인 기억하기 flag
    
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public boolean isRememberId() {
		return rememberId;
	}
	public void setRememberId(boolean rememberId) {
		this.rememberId = rememberId;
	}
    
}