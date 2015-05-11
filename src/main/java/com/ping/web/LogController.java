package com.ping.web;

import javax.annotation.Resource;

import com.ping.core.Constant;
import com.ping.domain.Account;
import com.ping.service.AccountService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 基于登录/注销的控制器类
 * @author tianym
 * @version 0.2
 */
@Controller
public class LogController {
	
	private AccountService accountService;
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
    public String showLoginForm() {
		return "/login";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@RequestParam("id") String accountId, @RequestParam String password, Model model) {
		UsernamePasswordToken token = new UsernamePasswordToken(accountId, password);
		token.setRememberMe(true);//默认记住用户
		
		Subject currentAccount = SecurityUtils.getSubject();
		
		try {
		    currentAccount.login(token);
		} catch (UnknownAccountException uae) {
			model.addAttribute(Constant.ERROR_MSG, "账户错误");
			return "redirect:/login";
		} catch (IncorrectCredentialsException ice) { 
			model.addAttribute(Constant.ERROR_MSG, "密码不正确");
			return "redirect:/login";
		} catch (LockedAccountException lae) { 
			model.addAttribute(Constant.ERROR_MSG, "账户被锁定");
			return "redirect:/login";
		} catch (ExcessiveAttemptsException eae) {
			return "redirect:/login";
		}
		
		accountService.loginSuccess((Account) currentAccount.getSession().getAttribute(Constant.CURRENT_ACCOUNT));
		
		return "redirect:/";
	}
	
	@RequestMapping("/logout")
	public void logout() {
		Subject currentAccount = SecurityUtils.getSubject();
		currentAccount.logout();
	}

	@Resource
	public void setAccountService(AccountService accountService) {
		this.accountService = accountService;
	}
	
}
