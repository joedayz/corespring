package accounts.web;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class DateInsertingInterceptor
		extends HandlerInterceptorAdapter {

	
	public void postHandle(
			HttpServletRequest request, 
			HttpServletResponse response, 
			Object handler, ModelAndView modelAndView)
			throws Exception {
		
		modelAndView.addObject("date", new Date());
		
	}
}
