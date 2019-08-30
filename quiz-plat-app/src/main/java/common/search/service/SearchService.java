package common.search.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quiz.web.dto.WritingDtlDto;

import common.search.dao.SearchDao;
import common.search.dto.SearchDto;

@Service
public class SearchService {
	
	@Autowired
    private SearchDao searchDao;
	
	    /*
	     ** 검색 후 데이터 반환
	     */
		public List<WritingDtlDto> searchWrtingList(SearchDto searchDto) throws Exception{
		
		List<WritingDtlDto> searchWritingDtlList = new ArrayList<WritingDtlDto>();
		
		//가지고올 리스트 개수 지정
    	int start = (searchDto.getPage_num()-1)*searchDto.getPage_size();
    	int end = searchDto.getPage_num()*searchDto.getPage_size();
    	
    	searchDto.setStart(start);
    	searchDto.setEnd(end);
    	
    	String searchType = searchDto.getSrch_type_div_cd();//검색 유형
    	
		try {
			if(searchType.equals("0")) { //제목을 이용한 검색
				
				String searchTitle = searchDto.getSrch_word();
		    	String srchSplitWord[] = searchTitle.split(" ");
		    	
		    	searchWritingDtlList.addAll(searchDao.searchTitleWrtingList(searchDto));
			}
			
		} catch(Exception e) {
    		System.err.println(e.getMessage());
    	}
		
		return searchWritingDtlList;
	}
	
	
}
