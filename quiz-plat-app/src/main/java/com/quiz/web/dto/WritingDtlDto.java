package com.quiz.web.dto;

import java.util.Date;
import java.util.List;

public class WritingDtlDto {
	
private int    writing_no;        //寃뚯떆湲�踰덊샇
	private String ques_type_div_cd;  //吏덈Ц�쑀�삎援щ텇肄붾뱶, 10: �뀓�뒪�듃, 20: �씠誘몄�
	private String content;           //蹂몃Ц
	private String fir_writ_content;  //鍮꾧탳 吏덈Ц 泥ル쾲吏� �옉�꽦�궡�슜 
	private String sec_writ_content;  //鍮꾧탳 吏덈Ц �몢踰덉㎏ �옉�꽦�궡�슜
	private String fir_writ_img_path; //鍮꾧탳 吏덈Ц 泥ル쾲吏� �옉�꽦 �씠誘몄� 寃쎈줈
	private String sec_writ_img_path; //鍮꾧탳 吏덈Ц �몢踰덉㎏ �옉�꽦 �씠誘몄� 寃쎈줈
	private int    fir_vote_no;       //泥ル쾲吏� �닾�몴 �닔
	private int    sec_vote_no;       //�몢踰덉㎏ �닾�몴 �닔
	private String regpe_id;          //�옉�꽦�옄
	private String modpe_id;          //�닔�젙�옄
	private Date   reg_dts;           //�벑濡앹씪�떆
	private Date   mod_dts;           //�닔�젙�씪�떆
	private int    hits;              //議고쉶�닔
	private int    sum_vote;          //珥� �닾�몴 �닔
	private int    sum_comment;       //珥� �뙎湲� �닔
	private double popularity;        //�씤湲곕룄
	private int    fir_vote_perc;     //泥ル쾲吏� �닾�몴 鍮꾩쑉
	private int    sec_vote_perc;     //�몢踰덉㎏ �닾�몴 鍮꾩쑉
	private int    vote_diff;
	private Integer vote;              // 위 컨텐츠 투표시 1, 아래 컨텐츠 투표 시 2, 투표값 없으면 null
	private List<CommentDto> detailCommentList; //�뙎湲�由ъ뒪�듃

	public int getWriting_no() {
		return writing_no;
	}
	public void setWriting_no(int writing_no) {
		this.writing_no = writing_no;
	}
	public String getQues_type_div_cd() {
		return ques_type_div_cd;
	}
	public void setQues_type_div_cd(String ques_type_div_cd) {
		this.ques_type_div_cd = ques_type_div_cd;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getFir_writ_content() {
		return fir_writ_content;
	}
	public void setFir_writ_content(String fir_writ_content) {
		this.fir_writ_content = fir_writ_content;
	}
	public String getSec_writ_content() {
		return sec_writ_content;
	}
	public void setSec_writ_content(String sec_writ_content) {
		this.sec_writ_content = sec_writ_content;
	}
	public String getFir_writ_img_path() {
		return fir_writ_img_path;
	}
	public void setFir_writ_img_path(String fir_writ_img_path) {
		this.fir_writ_img_path = fir_writ_img_path;
	}
	public String getSec_writ_img_path() {
		return sec_writ_img_path;
	}
	public void setSec_writ_img_path(String sec_writ_img_path) {
		this.sec_writ_img_path = sec_writ_img_path;
	}
	public int getFir_vote_no() {
		return fir_vote_no;
	}
	public void setFir_vote_no(int fir_vote_no) {
		this.fir_vote_no = fir_vote_no;
	}
	public int getSec_vote_no() {
		return sec_vote_no;
	}
	public void setSec_vote_no(int sec_vote_no) {
		this.sec_vote_no = sec_vote_no;
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
	public int getHits() {
		return hits;
	}
	public void setHits(int hits) {
		this.hits = hits;
	}
	public int getSum_vote() {
		return sum_vote;
	}
	public void setSum_vote(int sum_vote) {
		this.sum_vote = sum_vote;
	}
	public int getSum_comment() {
		return sum_comment;
	}
	public void setSum_comment(int sum_comment) {
		this.sum_comment = sum_comment;
	}
	public int getFir_vote_perc() {
		return fir_vote_perc;
	}
	public void setFir_vote_perc(int fir_vote_perc) {
		this.fir_vote_perc = fir_vote_perc;
	}
	public int getSec_vote_perc() {
		return sec_vote_perc;
	}
	public void setSec_vote_perc(int sec_vote_perc) {
		this.sec_vote_perc = sec_vote_perc;
	}
	public double getPopularity() {
		return popularity;
	}
	public void setPopularity(double popularity) {
		this.popularity = popularity;
	}
	public List<CommentDto> getDetailCommentList() {
		return detailCommentList;
	}

	public void setDetailCommentList(List<CommentDto> detailCommentList) {
		this.detailCommentList = detailCommentList;
	}
	public int getVote_diff() {
		return vote_diff;
	}
	public void setVote_diff(int vote_diff) {
		this.vote_diff = vote_diff;
	}
	public Integer getVote() {
		return vote;
	}
	public void setVote(Integer vote) {
		this.vote = vote;
	}
}
