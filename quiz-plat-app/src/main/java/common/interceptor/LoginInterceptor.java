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
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        // session값 세팅
        HttpSession session = request.getSession();
        
        Cookie cookie = WebUtils.getCookie(request, "remember");

        if(cookie!=null) {
        	UserDto userDto = userService.checkLoginBefore(cookie.getValue());  //로그인 기억하기 체크

        	if(userDto!=null) {
        		session.setAttribute("login", userDto);
        	}
        }
        
        return true;
    }
}
