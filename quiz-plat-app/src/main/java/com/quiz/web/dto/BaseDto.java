package com.quiz.web.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class BaseDto {
	
	public BaseDto() {
		this.page_size = 5;
	}
	
	private String user_id;       //유저아이디
	private int page_num;         //가지고올 페이지 번호
	private int page_size;        //페이지 사이즈
	private int start;            //가지고올 번호 시작 다음
	private int end;              //가지고올 번호 마지막
	private String regpe_id;      //등록자
	private String modpe_id;      //변경자
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date   reg_dts;       //등록일
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date   mod_dts;       //변경일
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public int getPage_num() {
		return page_num;
	}
	public void setPage_num(int page_num) {
		this.page_num = page_num;
	}
	public int getPage_size() {
		return page_size;
	}
	public void setPage_size(int page_size) {
		this.page_size = page_size;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getEnd() {
		return end;
	}
	public void setEnd(int end) {
		this.end = end;
	}
	public String getRegpe_id() {
		return regpe_id;
	}
	public void setRegpe_id(String regpe_id) {
		this.regpe_id = regpe_id;
	}
	public String getModpe_id() {
		return modpe_id;
	}
	public void setModpe_id(String modpe_id) {
		this.modpe_id = modpe_id;
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
	
}
