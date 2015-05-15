package com.ping.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.ping.core.Constant;
import com.ping.core.util.DateUtils;
import com.ping.domain.Account;
import com.ping.domain.EmailCheck;
import com.ping.service.AccountService;
import com.ping.service.EmailCheckService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * ʵ��ע�Ṧ�ܵĿ�������
 * @author ex
 */
@Controller
public class RegisterController {
	
	private AccountService accountService;
	private EmailCheckService emailCheckService;
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String showRegisterForm() {
		return "register";
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String register(Account account, HttpServletRequest request) {
		System.out.println(account.getUsername());
		
		account = accountService.createAccount(account); 
		
		String toUrl = null;
		if (toUrl == null) {
			toUrl = "redirect:/login";
		}
		return toUrl;
	}
	
	@RequestMapping("/register/{checkcode}")
	public String checkForEmail(@PathVariable("checkcode") String checkCode, 
			Model model, HttpServletRequest request) {
		if (checkCode.length() != 32 || checkCode == null) {
			model.addAttribute(Constant.ERROR_MSG, "��Ч����֤�룡");
		} 
		EmailCheck checkCodeOfAccount = emailCheckService.findCheckCode(checkCode);
		if (checkCodeOfAccount == null) {
			model.addAttribute(Constant.ERROR_MSG, "��Ч����֤�룡");
		}
		
		boolean available = DateUtils.compareTimeForAvailableToCheck(
				checkCodeOfAccount.getGenerateTime()); 
		if (!available) {
			model.addAttribute(Constant.ERROR_MSG, "��֤���ѹ��ڣ���������֤");
		}
		
		Account currentAccount = accountService.findAccountByUsername(
				checkCodeOfAccount.getUsername());
		accountService.checkSuccess(currentAccount);
		
		model.addAttribute("currentAccount", currentAccount);
		
		return "checkForEmail";
	}

	@Resource
	public void setAccountService(AccountService accountService) {
		this.accountService = accountService;
	}

	@Resource
	public void setEmailCheckService(EmailCheckService emailCheckService) {
		this.emailCheckService = emailCheckService;
	}
}
