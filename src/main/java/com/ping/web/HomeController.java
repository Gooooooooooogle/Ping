package com.ping.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.ping.core.Constant;
import com.ping.domain.Account;
import com.ping.domain.Image;
import com.ping.service.AccountService;
import com.ping.service.CatalogeService;
import com.ping.service.ImageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 基于访问用户个人主页相关的控制器类
 * @author tianym
 */
@Controller
public class HomeController {
	
	private static final String IMAGE_OF_CATALOGE = "from Image i where i.cataloge = ? and i.username = ?";
	
	private AccountService accountService;
	private ImageService imageService;
	private CatalogeService catalogeService;

	@RequestMapping("/home/{accountId}")
	public String home(@PathVariable String accountId, HttpServletRequest request, Model model) {
		Account homeAccount = (Account) request.getAttribute(Constant.CURRENT_ACCOUNT);
		if (accountId == null || homeAccount == null) {
			//未登录
			return "login";
		}
		model.addAttribute("currentAccount", homeAccount);
		
		if (homeAccount.getAccountId() != accountId) {
			//此次请求访问其他用户主页
			homeAccount = accountService.findAccountByAccountId(accountId);
			model.addAttribute("homeAccount", homeAccount);
		}
		
		List<Image> imageForRandom = imageService.findImageForRandom(15, homeAccount.getUsername());
		model.addAttribute("imageForRandom", imageForRandom);
		
		List<Image> imageForLatest = imageService.findImageForLatest(9, homeAccount.getUsername());
		model.addAttribute("imageForLatest", imageForLatest);
		
		List<Image> imageForHot = imageService.findImageFotHot(9, homeAccount.getUsername());
		model.addAttribute("imageForHot", imageForHot);
		
		Map<String, Integer> catalogeNameAndCount = new HashMap<String, Integer>();
		for (String catalogeName : catalogeService.findAll()) {
			catalogeNameAndCount.put(catalogeName, 
					imageService.find(IMAGE_OF_CATALOGE, catalogeName, homeAccount.getUsername()));
		}
		model.addAttribute("catalogeNameAndCount", catalogeNameAndCount);	
		
		return "home";
	}
	
	@RequestMapping("/homeImage/{imageId}")
	public String home(@PathVariable String imageId, Model model) {
		Image image = imageService.findImageByImageId(imageId);
		String username = image.getUsername();
		Account homeAccount = accountService.findAccountByUsername(username);
		model.addAttribute("homeAccount", homeAccount);
		return "home";
	}
	
	@RequestMapping("/tohome")
	public String totot() {
		return "home";
	}

	@Resource
	public void setAccountService(AccountService accountService) {
		this.accountService = accountService;
	}

	@Resource
	public void setImageService(ImageService imageService) {
		this.imageService = imageService;
	}

	@Resource
	public void setCatalogeService(CatalogeService catalogeService) {
		this.catalogeService = catalogeService;
	}
	
}
