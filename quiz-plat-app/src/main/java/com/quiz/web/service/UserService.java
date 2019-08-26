package com.quiz.web.service;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quiz.web.controller.VsController;
import com.quiz.web.dao.UserDao;
import com.quiz.web.dto.LoginCommand;
import com.quiz.web.dto.UserDto;

import common.AuthInfo;

@Service
public class UserService {
	
	@Autowired
    private UserDao userDao;
	
	private static final Logger logger = LoggerFactory.getLogger(VsController.class);
	
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
     ** 가입된 회원인지 체크
     */
    public int chekUserId(String user_id) throws Exception{
    	return userDao.chekUserId(user_id);
    }
    
    /*
     ** 유저 로그인 성공시 true, 실패시 false 반환
     */
    public boolean chekOurUser(LoginCommand loginCommand) throws Exception{
    	return userDao.chekOurUser(loginCommand);
    }
    
    /*
     ** 닉네임 사용여부 업데이트
     */
    public void updateNickname(String nickname) throws Exception{
    	userDao.updateNickname(nickname);
    }
    
    /*
     ** 닉네임 사용여부 체크
     */
    public boolean chekNickname(String nickname) throws Exception{
    	return userDao.chekNickname(nickname);
    }
    
    /*
     ** 닉네임 삽입
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
     ** 세션키를 이용하여 닉네임, 유저 아이디 반환
     */
    public UserDto checkLoginBefore(String session_id) {
    	return userDao.checkLoginBefore(session_id);
    }
    
    /*
     ** 유저 정보 조회
     */
    public UserDto getUserDto(String user_id) {
    	return userDao.getUserDto(user_id);
    }
    
    /*
     ** userId 로그인 정보 획득
     */
    public UserDto getUesrSettingDto(HttpSession session, HttpServletRequest request) {
    	
    	Object userDto = session.getAttribute("login");
    	
    	UserDto user = new UserDto();
        if(userDto!=null) {  //로그인 되있음
        	user = (UserDto) userDto;
        	user.setRegpe_id(user.getUser_id());
        	user.setLogin(true);
        	user.setReg_div_cd("10");
        } else {            //비회원 정보 세팅
        	session = request.getSession();
        	user.setUser_id(session.toString());
        	user.setRegpe_id(session.toString());
        	user.setLogin(false);
        	user.setReg_div_cd("20");
        }
    	
    	return user;
    }
}
