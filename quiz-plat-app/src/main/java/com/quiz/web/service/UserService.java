package com.quiz.web.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quiz.web.dao.UserDao;
import com.quiz.web.dto.LoginCommand;
import com.quiz.web.dto.UserDto;

import common.AuthInfo;

@Service
public class UserService {
	
	@Autowired
    private UserDao userDao;
	
    /*
     ** 유저 등록  
     */
    public void insertUser(UserDto userDto) throws Exception{
    	userDao.insertUser(userDto);
    }
    
    /*
     ** 닉네임 획득
     */
    public String getNickname() throws Exception{
    	return userDao.getNickname();
    }
    
    /*
     ** 비회원 유저 아이디 확인
     */
    public int chekUserId(String user_id) throws Exception{
    	return userDao.chekUserId(user_id);
    }
    
    /*
     ** 회원 유저 아이디 확인
     */
    public boolean chekOurUser(LoginCommand loginCommand) throws Exception{
    	return userDao.chekOurUser(loginCommand);
    }
    
    /*
     ** 닉네임 사용여부 'Y' 업데이트 
     */
    public void updateNickname(String nickname) throws Exception{
    	userDao.updateNickname(nickname);
    }
    
    /*
     ** 닉네임 사용여부 'Y' 업데이트 
     */
    public boolean chekNickname(String nickname) throws Exception{
    	return userDao.chekNickname(nickname);
    }
    
    /*
     ** 닉네임 추가 
     */
    public void insertNickname(String nickname) throws Exception{
    	userDao.insertNickname(nickname);
    }
    
    /*
     ** 로그인 유지 
     */
    public void keepLogin(String user_id, String session_id, Date session_limit) {
    	AuthInfo authInfo = new AuthInfo();
    	authInfo.setUser_id(user_id);
    	authInfo.setSession_id(session_id);
    	authInfo.setSession_limit(session_limit);
    	
    	userDao.keepLogin(authInfo);
    }
    
    /*
     ** 계정 중 세션 값 있는지 체크
     */
    public UserDto checkLoginBefore(String session_id) {
    	return userDao.checkLoginBefore(session_id);
    }
    
    public UserDto getUserDto(String user_id) {
    	return userDao.getUserDto(user_id);
    }
    
}
