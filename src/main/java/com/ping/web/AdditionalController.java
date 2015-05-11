package com.ping.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.ping.core.Constant;
import com.ping.domain.Account;
import com.ping.service.AccountService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * ���ڶ���/�ղصȸ��ӷ���Ŀ�����
 * @author ppteam
 */
@Controller
public class AdditionalController {
	
	private AccountService accountService;
	
	@RequestMapping(value = "/collect/{imageId}", method = RequestMethod.GET)
	@ResponseBody
	public String collect(@PathVariable long imageId, HttpServletRequest request) {
		Account account = (Account) request.getAttribute(Constant.CURRENT_ACCOUNT);
		accountService.collectSuccess(account, imageId);
		return "���ĳɹ���";
	}
	
	@RequestMapping(value = "/uncollect/{imageId}", method = RequestMethod.GET)
	@ResponseBody
	public String unCollect(@PathVariable long imageId, HttpServletRequest request) {
		Account account = (Account) request.getAttribute(Constant.CURRENT_ACCOUNT);
		accountService.unCollectSuccess(account, imageId);
		return "��ȡ�����ģ�";
	}

	@Resource
	public void setAccountService(AccountService accountService) {
		this.accountService = accountService;
	}
	
	
}
