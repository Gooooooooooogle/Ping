package com.ping.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ping.core.Constant;
import com.ping.core.util.SolrUtils;
import com.ping.domain.Account;
import com.ping.domain.Image;
import com.ping.service.AccountService;
import com.ping.service.CatalogeService;
import com.ping.service.ImageService;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 基于订阅/收藏等附加服务的控制器
 * @author ex
 */
@Controller
public class AdditionalController {
	
	private AccountService accountService;
	private CatalogeService catalogeService;
	private ImageService imageService;

	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String search(@RequestParam("searchContent") String searchContent, Model model)
			throws IOException, SolrServerException {
		Image image = null;
		List<Image> images = new ArrayList<Image>();
		SolrDocumentList solrDocumentList = SolrUtils.solr(searchContent);

		for (SolrDocument solrDocument : solrDocumentList) {
			image = imageService.findImageByImageId((String) solrDocument.get("image_id"));
			images.add(image);
		}

		model.addAttribute("cataloges", catalogeService.findAll());
		model.addAttribute("searchOfImage", images);

		return "search";
	}
	
	@Resource
	public void setAccountService(AccountService accountService) {
		this.accountService = accountService;
	}

	@Resource
	public void setCatalogeService(CatalogeService catalogeService) {
		this.catalogeService = catalogeService;
	}

	@Resource
	public void setImageService(ImageService imageService) {
		this.imageService = imageService;
	}
	
}
