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
     ** 占쏙옙占쏙옙 占쏙옙占�  
     */
    public void insertUser(UserDto userDto) throws Exception{
    	userDao.insertUser(userDto);
    }
    
    /*
     ** 占싻놂옙占쏙옙 획占쏙옙
     */
    public String getNickname() throws Exception{
    	return userDao.getNickname();
    }
    
    /*
     ** 占쏙옙회占쏙옙 占쏙옙占쏙옙 占쏙옙占싱듸옙 확占쏙옙
     */
    public int chekUserId(String user_id) throws Exception{
    	return userDao.chekUserId(user_id);
    }
    
    /*
     ** 회占쏙옙 占쏙옙占쏙옙 占쏙옙占싱듸옙 확占쏙옙
     */
    public boolean chekOurUser(LoginCommand loginCommand) throws Exception{
    	return userDao.chekOurUser(loginCommand);
    }
    
    /*
     ** 占싻놂옙占쏙옙 占쏙옙肉⑼옙占� 'Y' 占쏙옙占쏙옙占쏙옙트 
     */
    public void updateNickname(String nickname) throws Exception{
    	userDao.updateNickname(nickname);
    }
    
    /*
     ** 占싻놂옙占쏙옙 占쏙옙肉⑼옙占� 'Y' 占쏙옙占쏙옙占쏙옙트 
     */
    public boolean chekNickname(String nickname) throws Exception{
    	return userDao.chekNickname(nickname);
    }
    
    /*
     ** 占싻놂옙占쏙옙 占쌩곤옙 
     */
    public void insertNickname(String nickname) throws Exception{
    	userDao.insertNickname(nickname);
    }
    
    /*
     ** 占싸깍옙占쏙옙 占쏙옙占쏙옙 
     */
    public void keepLogin(String user_id, String session_id, Date session_limit) {
    	AuthInfo authInfo = new AuthInfo();
    	authInfo.setUser_id(user_id);
    	authInfo.setSession_id(session_id);
    	authInfo.setSession_limit(session_limit);
    	
    	userDao.keepLogin(authInfo);
    }
    
    /*
     ** 占쏙옙占쏙옙 占쏙옙 占쏙옙占쏙옙 占쏙옙 占쌍댐옙占쏙옙 체크
     */
    public UserDto checkLoginBefore(String session_id) {
    	return userDao.checkLoginBefore(session_id);
    }
    
    /*
     ** 회占쏙옙占쏙옙체 占쏙옙회
     */
    public UserDto getUserDto(String user_id) {
    	return userDao.getUserDto(user_id);
    }
    
    /*
     ** userId 占쏙옙占쏙옙 占쏙옙체 획占쏙옙
     */
    public UserDto getUesrSettingDto(HttpSession session, HttpServletRequest request) {
    	
    	Object userDto = session.getAttribute("login");
    	
    	UserDto user = new UserDto();
        if(userDto!=null) {  //占싸깍옙占쏙옙 占쏙옙占쏙옙 占쏙옙占쏙옙 占쏙옙, 占쏙옙占쏙옙 占쏙옙占싱듸옙 占쏙옙占쏙옙
        	user = (UserDto) userDto;
        	user.setRegpe_id(user.getUser_id());
        	user.setReg_div_cd("10");
        } else {            //占싸깍옙占쏙옙 占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙 占쏙옙占�(占쏙옙회占쏙옙), 占쏙옙占쏙옙 占쏙옙占싱듸옙 占쏙옙占쏙옙
        	session = request.getSession();
        	user.setUser_id(session.toString());
        	user.setRegpe_id(session.toString());
        	user.setReg_div_cd("20");
        }
    	
    	return user;
    }
}
