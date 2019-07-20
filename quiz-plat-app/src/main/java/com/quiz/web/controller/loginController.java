package com.quiz.web.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
	     ** ȸ������ ������ �̵�
	     */
	    @RequestMapping(value = "/enrollForm", method = RequestMethod.GET)
	    public String enrollForm(Model model) throws Exception{
	    	
	    	String nickname = userService.getNickname();
	    	model.addAttribute("nickname", nickname);
	    	
	        return "member_modal";
	    }
	    
	    /*
	     ** ȸ�����
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
	     ** �α��� ������ �̵�
	     */
	    @RequestMapping(value = "/loginForm", method = RequestMethod.GET)
	    public String loginForm(HttpServletRequest request, Model model) throws Exception{
	    	
	    	Cookie[] getCookie = request.getCookies();
	    	if(getCookie != null){
	    		for(int i=0; i<getCookie.length; i++){
	    			Cookie c = getCookie[i];
		    		String cookieName = c.getName(); // ��Ű �̸� ��������
		    		String value = c.getValue();     // ��Ű �� ��������
		    		if(c.getName().equals("remember-me")) {
		    			model.addAttribute("Cookie", c);
		    			logger.info("ã��");
		    		}
	    		}
	    	}
	    	
	        return "testLogin";
	    }
	    
	    /*
	     ** �α��� ó��
	     */
	    @RequestMapping(value = "/login", method = RequestMethod.POST)
	    public String login(HttpServletResponse response, Model model, LoginCommand loginCommand) throws Exception{

	    	SHA256 sha256 = new SHA256();
	    	String pwd = sha256.getSHA256(loginCommand.getPwd());
	    	loginCommand.setPwd(pwd);
	    	
	    	//ȸ������ ���� Ȯ��
	    	if(userService.chekOurUser(loginCommand) == true) { //�α��� ����
	    		
	    		// �ڵ��α��� ��� ����
	            Cookie cookie = new Cookie("remember-me", loginCommand.getUser_id());
	            cookie.setPath("/"); // ��� ��ο��� ���� ���� �ϵ��� ����
	            
	            cookie.setMaxAge(28*24*60*60); //��Ű ���� �ð�4�� ����
	            response.addCookie(cookie); 
	    		
	    	} else { //�α��� ����
	    		return "loginForm";
	    	} 
	    	
	        return "redirect:/";
	    }
	    
	    /*
	     ** �α׾ƿ� ó��
	     */
	    @RequestMapping(value="/logout", method = RequestMethod.GET)
	    public String logout(HttpServletResponse response) throws Exception{
	    	
	    	Cookie myCookie = new Cookie("remember-me", null);
	        myCookie.setMaxAge(0); // ��Ű�� expiration Ÿ���� 0���� �Ͽ� ���ش�.
	        myCookie.setPath("/"); // ��� ��ο��� ���� ������ �˸���.
	    	
	    	return "home";
	    }
	    
	    /*
	     ** �׽�Ʈ������
	     */
	    @RequestMapping(value="/test", method = RequestMethod.GET)
	    public String test(HttpServletRequest request) throws Exception{
	    	return "testPage";
	    }
	 
}
