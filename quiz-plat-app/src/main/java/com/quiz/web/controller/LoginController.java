package com.quiz.web.controller;

import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.WebUtils;

import com.quiz.web.dto.LoginCommand;
import com.quiz.web.dto.UserDto;
import com.quiz.web.service.UserService;

import common.SHA256;

/**
 * 로그인 컨트롤러
 * @ author : GOOHOON
 * @ version 1.0
 */
@Controller
public class LoginController {
	
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private UserService userService;
    
    /*
     ** 닉네임 조회
     */
    @RequestMapping(value = "/getNickname", method = RequestMethod.GET)
    public @ResponseBody String getNickname() throws Exception{
    	
    	String nickname = userService.getNickname();
    	//userService.updateNickname(nickname);
    	
        return nickname;
    }
    
    /*
     ** 회원 가입
     */
    @RequestMapping(value = "/enroll", method = RequestMethod.POST)
    public @ResponseBody UserDto enroll(HttpServletRequest request, UserDto userDto) throws Exception{
    	SHA256 sha256 = new SHA256();
    	
    	String pwd = sha256.getSHA256(userDto.getPwd()); //패스워드 암호화
    	String reg_div_cd = "10"; //등록구분코드10일 경우 회원
    	userDto.setPwd(pwd);
    	userDto.setReg_div_cd(reg_div_cd);
    	
    	//회원가입되있을 경우
    	if(userService.chekUserId(userDto) != 0) {
    		userDto.setLogin(false); //로그인 값 false
    	}

    	userService.insertUser(userDto);
    	
    	//새로 입력한 닉네임인지, default로 뿌려주는 닉네임인지 판단
    	if(userService.chekNickname(userDto.getNickname()) == true) {
    		userService.updateNickname(userDto.getNickname());
    	} else {
    		userService.insertNickname(userDto.getNickname());
    	}
    	
    	userDto.setLogin(true); //로그인 값 true
    	
        return userDto;
    }
    	    
    /*
     ** 로그인 처리
     */
    @CrossOrigin
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public @ResponseBody UserDto login(HttpServletResponse response, HttpServletRequest request, HttpSession session, 
    		             LoginCommand loginCommand) throws Exception{

    	session = request.getSession();
    	
    	SHA256 sha256 = new SHA256();
    	String pwd = sha256.getSHA256(loginCommand.getPwd());
    	loginCommand.setPwd(pwd);
    	UserDto userDto = new UserDto();
    
    	if(userService.chekOurUser(loginCommand) == true) { //로그인 성공 체크
    		  		
    		//로그인 상태 유지
    		if(loginCommand.isRememberId()) {
	            
    			int cookieMaxTime = 28*24*60*60; // 쿠키 만료 시간 지정
    			
	            Cookie cookie = new Cookie("remember", session.getId());
	            cookie.setPath("/"); //모든 경로에서 접근 가능하도록 세팅
	            cookie.setMaxAge(cookieMaxTime);
	            response.addCookie(cookie); 
	            
	            Calendar calendar = Calendar.getInstance();
	            Date now = new Date();
	            calendar.add(Calendar.SECOND, cookieMaxTime);
	            
	            userService.keepLogin(loginCommand.getUser_id(), session.getId(),  calendar.getTime());
    		}
    		
            userDto = userService.getUserDto(loginCommand.getUser_id());
            userDto.setLogin(true);
            session.removeAttribute("login");
            session.setAttribute("login", userDto);	
    	} else { //로그인 실패
    		userDto.setLogin(false);
    	} 
    	
        return userDto;
    }
    
    /*
     ** 로그아웃
     */
    @CrossOrigin
    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public @ResponseBody UserDto logout(HttpServletResponse response, HttpServletRequest request, HttpSession session) throws Exception{
    	
    	Cookie cookie = WebUtils.getCookie(request, "remember");
    	if(cookie!=null) {
    		cookie.setMaxAge(0); // 쿠키만료
	        cookie.setPath("/"); // 모든 경로에서 접근 가능하도록 세팅
	        response.addCookie(cookie);
    	}
        
        //유저정보 획득
    	UserDto userDto = userService.getUesrSettingDto(session, request);
        //session.invalidate(); //세션값 전부 삭제
	    session.removeAttribute("login"); //세션에 login으로 저장된 값 삭제
	    userService.keepLogin(userDto.getUser_id(), "none", new Date());

    	return userDto;
    }
    
    /*
     ** 로그인 유저 정보 조회
     */    
    @CrossOrigin
    @RequestMapping(value = "/getUserInfo", method = RequestMethod.GET)
    public @ResponseBody UserDto getUserInfo(HttpSession session, HttpServletRequest request) throws Exception{
    	
    	//유저정보 획득
    	UserDto userDto = userService.getUesrSettingDto(session, request);
    	
        return userDto;
    }
    
    /*
     ** 아이디 중복 체크
     */    
    @CrossOrigin
    @RequestMapping(value = "/chekUserId", method = RequestMethod.GET)
    public @ResponseBody boolean chekUserId(@RequestParam(value="user_id") String user_id) throws Exception{
    	
    	//유저정보 획득
    	UserDto userDto = userService.getUserDto(user_id);
    	boolean isUserIdRepetit;
    	if(userDto != null) {
    		isUserIdRepetit = true;
    	} else {
    		isUserIdRepetit = false;
    	}
    	
        return isUserIdRepetit;
    }
}