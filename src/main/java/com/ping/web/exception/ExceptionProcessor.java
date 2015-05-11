package com.ping.web.exception;

import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.servlet.ModelAndView;

/**
 * ����web���쳣������, ������web������쳣�Ĵ�����
 * @author tianym
 */
public class ExceptionProcessor {
	
	@ExceptionHandler({UnauthorizedException.class})
	//@ResponseStatus(HttpStatus.UNAUTHORIZED)
	public ModelAndView processUnauthenticatedException(NativeWebRequest request, UnauthorizedException e) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("exception", e);
		modelAndView.setViewName("unauthorized");
		return modelAndView;
	}
	
}
