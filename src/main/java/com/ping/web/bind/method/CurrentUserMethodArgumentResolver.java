package com.ping.web.bind.method;

import com.ping.web.bind.annotation.CurrentAccount;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * 用于绑定@FormModel的方法参数解析器
 * @author tianym
 * @version : 0.1
 */
@Deprecated
public class CurrentUserMethodArgumentResolver implements HandlerMethodArgumentResolver {

    public CurrentUserMethodArgumentResolver() {
    }

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) 
			throws Exception {
		 CurrentAccount currentAccountAnnotation = parameter.getParameterAnnotation(CurrentAccount.class);
		 return webRequest.getAttribute(currentAccountAnnotation.value(), NativeWebRequest.SCOPE_REQUEST);
	}

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		if (parameter.hasParameterAnnotation(CurrentAccount.class)) {
            return true;
        }
        return false;
	}
}
