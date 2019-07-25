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

    // ������ session ���� �ʱ�ȭ
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        // session ��
        HttpSession session = request.getSession();
        
        Cookie cookie = WebUtils.getCookie(request, "remember");

        if(cookie!=null) {
        	logger.info("��Ű��: " + cookie.getValue());
        	UserDto userDto = userService.checkLoginBefore(cookie.getValue());

        	if(userDto!=null) {
        		session.setAttribute("login", userDto);
        		logger.info("���ǰ�: " + userDto.toString());
        	}
        }
        
        return true;
    }
}
