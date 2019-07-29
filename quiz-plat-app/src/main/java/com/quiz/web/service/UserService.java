package com.quiz.web.service;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
    
    /*
     ** 회원객체 조회
     */
    public UserDto getUserDto(String user_id) {
    	return userDao.getUserDto(user_id);
    }
    
    /*
     ** userId 세팅 객체 획득
     */
    public UserDto getUesrSettingDto(HttpSession session, HttpServletRequest request) {
    	
    	Object userDto = session.getAttribute("login");
    	UserDto user = new UserDto();
        if(userDto!=null) {  //로그인 정보 유지 시, 유저 아이디 세팅
        	user = (UserDto) userDto;
        } else {            //로그인 정보가 없을 경우(비회원), 세션 아이디 세팅
        	session = request.getSession();
        	user.setUser_id(session.toString());
        }
    	
    	return user;
    }
}
