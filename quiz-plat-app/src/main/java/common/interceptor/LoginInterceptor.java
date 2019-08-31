package common.interceptor;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.WebUtils;

import com.quiz.web.dto.UserDto;
import com.quiz.web.service.UserService;

public class LoginInterceptor extends HandlerInterceptorAdapter {

	static final Logger logger = LoggerFactory.getLogger(LoggerFactory.class);
    
    @Autowired
    UserService userService;

    //로그인 인터셉터
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // session값 세팅
        HttpSession session = request.getSession();
        
        Cookie cookie = WebUtils.getCookie(request, "remember");
        UserDto userDto = new UserDto();
        UserDto normalUserDto = userService.getUesrSettingDto(session, request);
        UserDto nonUserDto = new UserDto();
        
        //쿠키값에 있는 값과 서버에 저장된 값 비교, 만약 2개가 같다면 session에 로그인 값 세팅
        if(cookie!=null) {
        	userDto = userService.checkLoginBefore(cookie.getValue());  //로그인 기억하기 체크
        	nonUserDto =  userService.getUserDto(session.toString());   //비회원 정보 조회
        	
        	if(userDto!=null) { //자동 로그인 상태로 로그인 되어있는 경우
        		userDto.setLogin(true);
        		session.setAttribute("login", userDto);
        	} else if(normalUserDto!=null && normalUserDto.isLogin() == true) { //기억하기 없이 로그인되어있는 상태일 경우
        		session.setAttribute("login", normalUserDto);
        	} else if(nonUserDto != null) {  //현재 세션 연결중(비회원 로그인상태로 간주)
        		nonUserDto.setLogin(false);
         		session.setAttribute("login", nonUserDto); 
         	} else { //비회원 회원가입 및 비회원 로그인 정보 세팅
         		nonUserDto = new UserDto();
         		
         		//서버로부터 닉네임 받아오고, 사용여부 'N' 업데이트
         		String nickname = userService.getNickname();
         		userService.updateNickname(nickname);
         		
         		//세션이 처음 연결될 경우 비회원 유저 정보 생성
         		nonUserDto.setUser_id(session.toString());
         		nonUserDto.setReg_div_cd("20");
         		nonUserDto.setNickname(nickname);
         		
         		nonUserDto.setLogin(false);
         		
         		//비회원 정보 저장
         		userService.insertUser(nonUserDto);
         		session.setAttribute("login", nonUserDto);
         	}
        } else { //쿠키값이 없을 경우 세션 연결 상태 확인
        	nonUserDto =  userService.getUserDto(session.toString());   
        	
        	if(normalUserDto!=null && normalUserDto.isLogin() == true) { //기억하기 없이 로그인되어있는 상태일 경우
        		session.setAttribute("login", normalUserDto);
        	} else if(nonUserDto != null) {  //현재 세션 연결중(비회원 로그인상태로 간주)
        		nonUserDto.setLogin(false);
         		session.setAttribute("login", nonUserDto); 
         	} else { //비회원 회원가입 및 비회원 로그인 정보 세팅
         		
         		nonUserDto = new UserDto();
         		
         		//서버로부터 닉네임 받아오고, 사용여부 'N' 업데이트
         		String nickname = userService.getNickname();
         		//userService.updateNickname(nickname);
         		
         		//세션이 처음 연결될 경우 비회원 유저 정보 생성
         		nonUserDto.setUser_id(session.toString());
         		nonUserDto.setReg_div_cd("20");
         		nonUserDto.setNickname(nickname);
         		
         		nonUserDto.setLogin(false);
         		
         		//비회원 정보 저장
         		userService.insertUser(nonUserDto);
         		session.setAttribute("login", nonUserDto);
         	}
        }
        
        return true;
    }
}
