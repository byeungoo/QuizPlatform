package com.quiz.web.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.quiz.web.dto.LoginCommand;
import com.quiz.web.dto.UserDto;
import com.quiz.web.service.UserService;

import common.SHA256;

@Controller
public class loginController {
	
	 private static final Logger logger = LoggerFactory.getLogger(VsController.class);

	    @Autowired
	    private UserService userService;
	    
	    /*
	     ** 회원가입 페이지 이동
	     */
	    @RequestMapping(value = "/enrollForm", method = RequestMethod.GET)
	    public String enrollForm(Model model) throws Exception{
	    	
	    	String nickname = userService.getNickname();
	    	model.addAttribute("nickname", nickname);
	    	
	        return "member_modal";
	    }
	    
	    /*
	     ** 회원등록
	     */
	    @RequestMapping(value = "/enroll", method = RequestMethod.POST)
	    public String enroll(HttpServletRequest request, Model model) throws Exception{
	    	SHA256 sha256 = new SHA256();
	    	
	    	String user_id = request.getParameter("join1");
	    	String nickname = request.getParameter("join2");
	    	String pwd = sha256.getSHA256(request.getParameter("join3"));
	    	String reg_div_cd = "10";
	    	
	    	UserDto userDto = new UserDto();	
	    	userDto.setUser_id(user_id);
	    	userDto.setNickname(nickname);
	    	userDto.setPwd(pwd);
	    	userDto.setReg_div_cd(reg_div_cd);
	    	userDto.setRegpe_id(user_id);
	    	
	    	//이미 회원가입되 있는 경우
	    	if(userService.chekUserId(user_id) != 0) {
	    		model.addAttribute("enrollFlag", 0);
	    		model.addAttribute("nickname", nickname);
	    		return "member_modal";
	    	}
	    	
	    	userService.insertUser(userDto);
	    	
	    	if(userService.chekNickname(nickname) == true) {
	    		userService.updateNickname(userDto.getNickname());
	    	} else {
	    		userService.insertNickname(nickname);
	    	}
	    	
	        return "redirect:/";
	    }
	    
	    /*
	     ** 로그인 페이지 이동
	     */
	    @RequestMapping(value = "/loginForm", method = RequestMethod.GET)
	    public String loginForm(HttpServletRequest request, Model model) throws Exception{
	    	
	    	Cookie[] getCookie = request.getCookies();
	    	if(getCookie != null){
	    		for(int i=0; i<getCookie.length; i++){
	    			Cookie c = getCookie[i];
		    		String cookieName = c.getName(); // 쿠키 이름 가져오기
		    		String value = c.getValue();     // 쿠키 값 가져오기
		    		if(c.getName().equals("remember-me")) {
		    			model.addAttribute("Cookie", c);
		    		}
	    		}
	    	}
	    	
	        return "member_modal";
	    }
	    
	    /*
	     ** 로그인 처리
	     */
	    @RequestMapping(value = "/login", method = RequestMethod.POST)
	    public String login(HttpServletResponse response, HttpServletRequest request, HttpSession session, 
	    		            Model model, LoginCommand loginCommand) throws Exception{

	    	session = request.getSession();
	    	
	    	SHA256 sha256 = new SHA256();
	    	String pwd = sha256.getSHA256(loginCommand.getPwd());
	    	loginCommand.setPwd(pwd);
	    	
	    	//회원가입 정보 확인
	    	if(userService.chekOurUser(loginCommand) == true) { //로그인 성공
	    		
	    		// 자동로그인 쿠기 셋팅
	            Cookie cookie = new Cookie("remember", session.getId());
	            cookie.setPath("/"); // 모든 경로에서 접근 가능 하도록 설정
	            cookie.setMaxAge(28*24*60*60); //쿠키 만료 시간4주 설정
	            response.addCookie(cookie); 
	            session.setAttribute("login", loginCommand);
	    		
	    	} else { //로그인 실패
	    		return "loginForm";
	    	} 
	    	
	        return "redirect:/";
	    }
	    
	    /*
	     ** 로그아웃 처리
	     */
	    @RequestMapping(value="/logout", method = RequestMethod.GET)
	    public String logout(HttpServletResponse response, HttpSession session) throws Exception{
	    	
	    	Cookie cookie = new Cookie("remember", null);
	        cookie.setMaxAge(0); // 쿠키의 expiration 타임을 0으로 하여 없앤다.
	        cookie.setPath("/"); // 모든 경로에서 삭제 됬음을 알린다.
	        response.addCookie(cookie);
	        session.invalidate(); //세션해제
	        //session.removeAttribute("login"); // 하나씩 하려면 이렇게 해도 됨.
	        
	    	return "redirect:/";
	    }
}
