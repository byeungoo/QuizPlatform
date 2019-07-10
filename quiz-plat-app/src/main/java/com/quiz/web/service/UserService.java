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
     ** À¯Àú µî·Ï  
     */
    public void insertUser(UserDto userDto) throws Exception{
    	userDao.insertUser(userDto);
    }
    
    /*
     ** ´Ð³×ÀÓ È¹µæ
     */
    public String getNickname() throws Exception{
    	return userDao.getNickname();
    }
	
}
