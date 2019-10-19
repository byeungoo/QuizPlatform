package com.quiz.web.controller;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.quiz.web.dto.UserDto;
import com.quiz.web.dto.WritingDtlDto;
import com.quiz.web.service.FileUploadService;
import com.quiz.web.service.UserService;
import com.quiz.web.service.WritingDtlService;

import common.paging.dto.PagingDto;

/**
 * 게시글 작성 컨트롤러
 * @ author : GOOHOON
 * @ version 1.0
 */
@Controller
public class PostController {
	
    private static final Logger logger = LoggerFactory.getLogger(PostController.class);

    @Autowired
    private UserService userService;
    
    @Autowired
    private WritingDtlService writingDtlService;
    
	@Autowired
	FileUploadService fileUploadService;
	
	@Autowired 
	private ServletContext servletContext;
	
    /*
     ** 글작성 페이지 이동
     */
    @RequestMapping(value = "/write", method = RequestMethod.GET)
    public String main(HttpSession session, HttpServletRequest request) throws Exception{
    	
        return "/write";
    }
    
    /*
     ** 글작성
     */
    @CrossOrigin
    @RequestMapping(value="/writePost", method = RequestMethod.POST)
    public @ResponseBody WritingDtlDto insertWrite(HttpServletRequest request, WritingDtlDto writingDtlDto, @RequestParam("title_img_file") MultipartFile title_img_file
    		, @RequestParam("content_file") MultipartFile content_file, @RequestParam("summary_file") MultipartFile summary_file) throws Exception{
    	
    	HttpSession   session = request.getSession();
    	logger.debug(servletContext.getRealPath("/"));
    	
    	//유저정보 획득
    	UserDto userDto = userService.getUesrSettingDto(session, request);
    		
    	String ques_type_div_cd  = "10"; //텍스트 게시글 구분 코드 세팅
    	
    	writingDtlDto.setQues_type_div_cd(ques_type_div_cd);
    	writingDtlDto.setUser_id(userDto.getUser_id());
    	
    	//요약, 본문 데이터 세팅
    	String content = new String(content_file.getBytes());
    	String summary = new String(summary_file.getBytes());
    	writingDtlDto.setContent(content);
    	writingDtlDto.setSummary(summary);
    	
    	//서버에 파일 업로드
    	String title_img_path = fileUploadService.restore(title_img_file);
    	
    	//이미지 경로 세팅
    	writingDtlDto.setTitle_img_path(title_img_path);
    	
    	writingDtlDto = writingDtlService.insertWritingDtl(writingDtlDto);
    	
    	return writingDtlDto;
    }
    
    /*
     ** 글작성 페이지 이동
     */
    @RequestMapping(value = "/kakaoShare", method = RequestMethod.GET)
    public String kakaoShare(HttpSession session, HttpServletRequest request) throws Exception{
    	
        return "kakaoShare";
    }
    
}
