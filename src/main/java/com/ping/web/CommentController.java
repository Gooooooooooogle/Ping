package com.ping.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.ping.core.Constant;
import com.ping.core.Page;
import com.ping.domain.Account;
import com.ping.domain.Comment;
import com.ping.domain.Image;
import com.ping.service.CommentService;
import com.ping.service.ImageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 基于评论的控制器类
 * @author ex
 */
@Controller
public class CommentController {
	
	private ImageService imageService;
	private CommentService commentService;

	@RequestMapping("/comment/{imageId}")
	public String comment(@PathVariable String imageId, @RequestParam("text") String text, HttpServletRequest request) {
		Image image = imageService.findImageByImageId(imageId);
		Account account = (Account) request.getAttribute(Constant.CURRENT_ACCOUNT);
		if (account == null) {
			return "redirect:/login";
		}
		commentService.commentSuccess(image, text, account.getUsername());
		return "redirect:/image/detail/" + imageId;
	}
	
	@RequestMapping("/showcomments_{pageNo}") 
	public String showCommentsOfImage(@PathVariable int pageNo, HttpServletRequest request, Model model) {
		Image image = (Image) request.getAttribute("currentImage");
		System.out.println(image == null);
		Page<Comment> commentsOfImage = commentService.pageQuery(pageNo, Page.DEFAULT_PAGE_SIZE, image.getImageId());
		model.addAttribute("currentComments", commentsOfImage.getData());
		return "detail";
	}

	@Resource
	public void setImageService(ImageService imageService) {
		this.imageService = imageService;
	}

	@Resource
	public void setCommentService(CommentService commentService) {
		this.commentService = commentService;
	}
	
}
