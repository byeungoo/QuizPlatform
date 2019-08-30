package common.search.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.quiz.web.dto.WritingDtlDto;

import common.search.dto.SearchDto;

@Repository
public class SearchDao {

	@Autowired
    private SqlSession sqlSession;
    
    private static final String Namespace = "com.quiz.mapper.writingDtlMapper";
	
	public List<WritingDtlDto> searchTitleWrtingList(SearchDto searchDto) throws Exception{
		 return sqlSession.selectList(Namespace+".searchWrtingList", searchDto);
	}
	
}
