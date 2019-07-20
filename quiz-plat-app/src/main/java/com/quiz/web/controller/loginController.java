package com.quiz.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
	    public String loginForm(Model model) throws Exception{
	    	
	    	String nickname = userService.getNickname();
	    	model.addAttribute("nickname", nickname);
	    	
	        return "testLogin";
	    }
	    
	    /*
	     ** �α��� ó��
	     */
	    @RequestMapping(value = "/login", method = RequestMethod.POST)
	    public String login(HttpServletRequest request, Model model) throws Exception{
	    	SHA256 sha256 = new SHA256();
	    	String user_id = request.getParameter("user_id");
	    	String pwd = sha256.getSHA256(request.getParameter("pwd"));
	    	
	    	UserDto userDto = new UserDto();
	    	userDto.setUser_id(user_id);
	    	userDto.setPwd(pwd);
	    	
	    	//ȸ������ ���� Ȯ��
	    	if(userService.chekOurUser(userDto) == true) {
	    		
	    	} else {
	    		
	    	}
	    	
	        return "home";
	    }
	    
	    /*
	     ** �α׾ƿ� ó��
	     */
	    @RequestMapping(value="/logout", method = RequestMethod.GET)
	    public String logout() throws Exception{
	    	
	    	return "home";
	    }
	 
}
