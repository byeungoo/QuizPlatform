package common.search.dto;

import common.paging.dto.PagingDto;

public class SearchDto extends PagingDto{

	public String srch_word; //검색어
	public String srch_type_div_cd; //검색타입구분코드, 0: 제목 검색
	
	public String getSrch_word() {
		return srch_word;
	}
	public void setSrch_word(String srch_word) {
		this.srch_word = srch_word;
	}
	public String getSrch_type_div_cd() {
		return srch_type_div_cd;
	}
	public void setSrch_type_div_cd(String srch_type_div_cd) {
		this.srch_type_div_cd = srch_type_div_cd;
	}
}
