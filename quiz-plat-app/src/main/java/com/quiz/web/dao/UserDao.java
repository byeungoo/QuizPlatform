package com.quiz.web.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.quiz.web.dto.UserDto;
import com.quiz.web.dto.WritingDtlDto;

@Repository
public class UserDao {

	@Autowired
    private SqlSession sqlSession;
    
    private static final String Namespace = "com.quiz.mapper.userMapper";
    
    public void insertUser(UserDto userDto) throws Exception{
        sqlSession.insert(Namespace+".insertUser", userDto);
    }
    
    public String getNickname() throws Exception{
    	return sqlSession.selectOne(Namespace+".getNickname");
    }
    
    public int chekUserId(String user_id) throws Exception{
    	return sqlSession.selectOne(Namespace+".chekUserId", user_id);
    }
}
