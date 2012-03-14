package accounts.web;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * This interceptor adds the current Date to the ModelAndView
 * in in the {@link #postHandle(HttpServletRequest, HttpServletResponse, Object, ModelAndView)}
 * method.
 */
public class DateInsertingInterceptor extends HandlerInterceptorAdapter {
	
	/**
	 * The postHandle method is called after the 
	 * controller has handled the request. The postHandle method
	 * allows you to modify the ModelAndView for example.
	 */
	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		modelAndView.addObject("date", new Date());
	}

}
