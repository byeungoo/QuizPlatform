package com.quiz.web.dto;

import java.util.Date;

public class CommentDto {
	
	private int    writing_no;         //�Խñ۹�ȣ
	private int    comment_no;         //��۹�ȣ
	private String comment_content;    //��۳���
	private int    recom_no;           //��õ��
	private String regpe_id;           //�ۼ���
	private String modpe_id;           //������
	private Date   reg_dts;            //����Ͻ�
	private Date   mod_dts;            //�����Ͻ�
	private String nickname;           //�г���
	
	public int getWriting_no() {
		return writing_no;
	}
	public void setWriting_no(int writing_no) {
		this.writing_no = writing_no;
	}
	public int getComment_no() {
		return comment_no;
	}
	public void setComment_no(int comment_no) {
		this.comment_no = comment_no;
	}
	public String getComment_content() {
		return comment_content;
	}
	public void setComment_content(String comment_content) {
		this.comment_content = comment_content;
	}
	public int getRecom_no() {
		return recom_no;
	}
	public void setRecom_no(int recom_no) {
		this.recom_no = recom_no;
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
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
}
