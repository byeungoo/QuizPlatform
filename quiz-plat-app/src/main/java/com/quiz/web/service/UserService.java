package com.quiz.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quiz.web.dao.UserDao;
import com.quiz.web.dto.UserDto;

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
    public int chekOurUser(UserDto userDto) throws Exception{
    	return userDao.chekOurUser(userDto);
    }
    
    /*
     ** 닉네임 사용여부 'Y' 업데이트 
     */
    public void updateNickname(String nickname) throws Exception{
    	userDao.updateNickname(nickname);
    }
}
