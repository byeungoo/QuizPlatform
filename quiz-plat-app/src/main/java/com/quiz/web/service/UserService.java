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
     ** ���� ���  
     */
    public void insertUser(UserDto userDto) throws Exception{
    	userDao.insertUser(userDto);
    }
    
    /*
     ** �г��� ȹ��
     */
    public String getNickname() throws Exception{
    	return userDao.getNickname();
    }
    
    /*
     ** ��ȸ�� ���� ���̵� Ȯ��
     */
    public int chekUserId(String user_id) throws Exception{
    	return userDao.chekUserId(user_id);
    }
    
    /*
     ** ȸ�� ���� ���̵� Ȯ��
     */
    public boolean chekOurUser(LoginCommand loginCommand) throws Exception{
    	return userDao.chekOurUser(loginCommand);
    }
    
    /*
     ** �г��� ��뿩�� 'Y' ������Ʈ 
     */
    public void updateNickname(String nickname) throws Exception{
    	userDao.updateNickname(nickname);
    }
    
    /*
     ** �г��� ��뿩�� 'Y' ������Ʈ 
     */
    public boolean chekNickname(String nickname) throws Exception{
    	return userDao.chekNickname(nickname);
    }
    
    /*
     ** �г��� �߰� 
     */
    public void insertNickname(String nickname) throws Exception{
    	userDao.insertNickname(nickname);
    }
    
    /*
     ** �α��� ���� 
     */
    public void keepLogin(String user_id, String session_id, Date session_limit) {
    	AuthInfo authInfo = new AuthInfo();
    	authInfo.setUser_id(user_id);
    	authInfo.setSession_id(session_id);
    	authInfo.setSession_limit(session_limit);
    	
    	userDao.keepLogin(authInfo);
    }
    
    /*
     ** ���� �� ���� �� �ִ��� üũ
     */
    public UserDto checkLoginBefore(String session_id) {
    	return userDao.checkLoginBefore(session_id);
    }
    
    /*
     ** ȸ����ü ��ȸ
     */
    public UserDto getUserDto(String user_id) {
    	return userDao.getUserDto(user_id);
    }
    
    /*
     ** userId ���� ��ü ȹ��
     */
    public UserDto getUesrSettingDto(HttpSession session, HttpServletRequest request) {
    	
    	Object userDto = session.getAttribute("login");
    	
    	UserDto user = new UserDto();
        if(userDto!=null) {  //�α��� ���� ���� ��, ���� ���̵� ����
        	user = (UserDto) userDto;
        	user.setReg_div_cd("10");
        } else {            //�α��� ������ ���� ���(��ȸ��), ���� ���̵� ����
        	session = request.getSession();
        	user.setUser_id(session.toString());
        	user.setRegpe_id(session.toString());
        	user.setReg_div_cd("20");
        }
    	
    	return user;
    }
}
