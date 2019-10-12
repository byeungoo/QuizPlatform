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
    
    public void insertUser(UserDto userDto){
        sqlSession.insert(Namespace+".insertUser", userDto);
    }
    
    public String getNickname(){
    	return sqlSession.selectOne(Namespace+".getNickname");
    }
    
    public int chekUserId(UserDto userDto){
    	return sqlSession.selectOne(Namespace+".chekUserId", userDto);
    }
    
    public boolean chekOurUser(LoginCommand loginCommand){
    	return sqlSession.selectOne(Namespace+".chekOurUser", loginCommand);
    }
    
    public void updateNickname(String nickname){
    	sqlSession.update(Namespace+".updateNickname", nickname);
    }
    
    public boolean chekNickname(String nickname){
    	return sqlSession.selectOne(Namespace+".chekNickname", nickname);
    }
    
    public void insertNickname(String nickname){
    	sqlSession.insert(Namespace+".insertNickname", nickname);
    }
    
    public void keepLogin(AuthInfo authInfo){
    	sqlSession.update(Namespace+".keepLogin", authInfo);
    }
    
    public UserDto checkLoginBefore(String session_id){
    	return sqlSession.selectOne(Namespace+".checkLoginBefore", session_id);
    }
    
    public UserDto getUserDto(String user_id){
    	return sqlSession.selectOne(Namespace+".getUserDto", user_id);
    }
    
}
