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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.WebUtils;

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
	     ** 회원 가입 페이지 이동
	     */
	    @RequestMapping(value = "/enrollForm", method = RequestMethod.GET)
	    public String enrollForm(Model model) throws Exception{
	    	
	    	String nickname = userService.getNickname();
	    	model.addAttribute("nickname", nickname);
	    	
	        return "member_modal";
	    }
	    
	    /*
	     ** 회원 가입
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
	    	
	    	//�̹� ȸ�����Ե� �ִ� ���
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
	     ** 
	     */
	    @RequestMapping(value = "/loginForm", method = RequestMethod.GET)
	    public String loginForm(HttpServletRequest request, Model model) throws Exception{
	    	
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
	    	
	    	//ȸ������ ���� Ȯ��
	    	if(userService.chekOurUser(loginCommand) == true) { //로그인 성공 체크
	    		  		
	    		if(loginCommand.isRememberId()) {
	    			
	    			// �ڵ��α��� ��� ����
	    			int cookieMaxTime = 28*24*60*60; // 쿠키 만료 시간 지정
	    			
		            Cookie cookie = new Cookie("remember", session.getId());
		            cookie.setPath("/"); // ��� ��ο��� ���� ���� �ϵ��� ����
		            cookie.setMaxAge(cookieMaxTime);
		            response.addCookie(cookie); 
		            
		            Calendar calendar = Calendar.getInstance();
		            Date now = new Date();
		            calendar.add(Calendar.SECOND, cookieMaxTime);
		            
		            //��Ű���� ��� ����
		            userService.keepLogin(loginCommand.getUser_id(), session.getId(),  calendar.getTime());
	    		}
	    		
	            UserDto userDto = userService.getUserDto(loginCommand.getUser_id());
	            
	            session.setAttribute("login", userDto);	
	    	} else { //�α��� ����
	    		return "loginForm";
	    	} 
	    	
	        return "redirect:/";
	    }
	    
	    /*
	     ** 로그아웃
	     */
	    @RequestMapping(value="/logout", method = RequestMethod.GET)
	    public String logout(HttpServletResponse response, HttpServletRequest request, HttpSession session) throws Exception{
	    	
	    	Cookie cookie = WebUtils.getCookie(request, "remember");
	    	if(cookie!=null) {
	    		cookie.setMaxAge(0); // ��Ű�� expiration Ÿ���� 0���� �Ͽ� ���ش�.
		        cookie.setPath("/"); // ��� ��ο��� ���� ������ �˸���.
		        response.addCookie(cookie);
	    	}
	        
	        Object userDto = session.getAttribute("login");
	        if(userDto!=null) {
	        	UserDto user = (UserDto) userDto;
	        	//session.invalidate(); //��ü��������
		        session.removeAttribute("login"); // �ϳ��� �Ϸ��� �̷��� �ص� ��.
		        userService.keepLogin(user.getUser_id(), "none", new Date());
	        }
	        
	    	return "redirect:/";
	    }
}
