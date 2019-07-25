package com.quiz.web.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.quiz.web.dto.LoginCommand;
import com.quiz.web.dto.UserDto;
import com.quiz.web.dto.WritingDtlDto;

import common.AuthInfo;

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
    
    public boolean chekOurUser(LoginCommand loginCommand) throws Exception{
    	return sqlSession.selectOne(Namespace+".chekOurUser", loginCommand);
    }
    
    public void updateNickname(String nickname) throws Exception{
    	sqlSession.update(Namespace+".updateNickname", nickname);
    }
    
    public boolean chekNickname(String nickname) throws Exception{
    	return sqlSession.selectOne(Namespace+".chekNickname", nickname);
    }
    
    public void insertNickname(String nickname) throws Exception{
    	sqlSession.insert(Namespace+".insertNickname", nickname);
    }
    
    public void keepLogin(AuthInfo authInfo) {
    	sqlSession.update(Namespace+".keepLogin", authInfo);
    }
    
    public UserDto checkLoginBefore(String session_id) {
    	return sqlSession.selectOne(Namespace+".checkLoginBefore", session_id);
    }
    
    public UserDto getUserDto(String user_id) {
    	return sqlSession.selectOne(Namespace+".getUserDto", user_id);
    }
    
}
