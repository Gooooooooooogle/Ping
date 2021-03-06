package com.ping.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.ping.core.Constant;
import com.ping.core.Page;
import com.ping.domain.Account;
import com.ping.domain.Cataloge;
import com.ping.domain.Image;
import com.ping.service.CatalogeService;
import com.ping.service.ImageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * 访问首页的控制器类
 * @author ex
 */
@Controller
public class IndexController {
	
	private ImageService imageService;
	private CatalogeService catalogeService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String toIndex() {
		return "redirect:/index_1";
	}

	@RequestMapping(value = "/index_{pageNo}", method = RequestMethod.GET)
	public String index(@PathVariable int pageNo, Model model, HttpServletRequest request) {
		Account currentAccount = (Account) request.getSession().getAttribute(Constant.CURRENT_ACCOUNT);
		model.addAttribute("currentAccount", currentAccount);

		List<Cataloge> cataloges = catalogeService.findAll();
		model.addAttribute("cataloges", cataloges);

		Page<Image> pageOfImages = imageService.pageQuery(pageNo, Page.DEFAULT_PAGE_SIZE);
		model.addAttribute("currentImages", pageOfImages.getData());

		return "index";
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
