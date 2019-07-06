package com.quiz.web.dao;

import java.util.List;

import javax.inject.Inject;
 
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
 
import com.quiz.web.dto.MemberDto;
 
@Repository
public class MemberDaoImpl implements MemberDao {
 
    @Inject
    private SqlSession sqlSession;
    
    private static final String Namespace = "com.quiz.mapper.memberMapper";
    
    @Override
    public List<MemberDto> selectMember() throws Exception {
 
        return sqlSession.selectList(Namespace+".selectMember");
    }
 
}