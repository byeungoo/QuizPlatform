package com.quiz.web.dao;

import java.util.List;
 
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
 
import com.quiz.web.dto.MemberDto;
import com.quiz.web.dto.WritingDtlDto;
 
@Repository
public class MemberDaoImpl implements MemberDao {
 
    @Autowired
    private SqlSession sqlSession;
    
    private static final String Namespace = "com.quiz.mapper.memberMapper";
    
    @Override
    public List<MemberDto> selectMember() throws Exception {
 
        return sqlSession.selectList(Namespace+".selectMember");
    }
    
    @Override
    public List<WritingDtlDto> getTextWritingList() throws Exception {
        return sqlSession.selectList(Namespace+".getTextWritingList");
    }
 
}