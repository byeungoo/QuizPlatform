package com.quiz.web.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.quiz.web.dto.UserDto;

@Repository
public class UserDao {

	@Autowired
    private SqlSession sqlSession;
    
    private static final String Namespace = "com.quiz.mapper.userMapper";
    
    public UserDto insertUser(UserDto userDto) throws Exception{
        return sqlSession.selectOne(Namespace+".insertUser", userDto);
    }
	
    public String getNickname() throws Exception{
    	return sqlSession.selectOne(Namespace+".getNickname");
    }
}
