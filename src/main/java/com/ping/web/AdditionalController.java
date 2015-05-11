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
 * 基于订阅/收藏等附加服务的控制器
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
		return "订阅成功！";
	}
	
	@RequestMapping(value = "/uncollect/{imageId}", method = RequestMethod.GET)
	@ResponseBody
	public String unCollect(@PathVariable long imageId, HttpServletRequest request) {
		Account account = (Account) request.getAttribute(Constant.CURRENT_ACCOUNT);
		accountService.unCollectSuccess(account, imageId);
		return "已取消订阅！";
	}

	@Resource
	public void setAccountService(AccountService accountService) {
		this.accountService = accountService;
	}
	
	
}
