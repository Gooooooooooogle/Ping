package com.ping.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;

import javax.annotation.Resource;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ping.core.Constant;
import com.ping.core.Page;
import com.ping.core.util.HttpUtils;
import com.ping.domain.Account;
import com.ping.domain.Comment;
import com.ping.domain.Image;
import com.ping.service.CommentService;
import com.ping.service.ImageService;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * 基于图片相关的控制器类
 * @author tianym
 * @version 0.2
 */
@Controller
@RequestMapping("/image")
public class ImageController {
	
	private ImageService imageService;
	private CommentService commentService;
	private JmsTemplate jmsTemplate;

	@RequestMapping("/{imageId}/detail")
	public String detail(@PathVariable String imageId, Model model, HttpServletRequest request) {
		Account currentAccount = (Account) request.getSession().getAttribute(Constant.CURRENT_ACCOUNT);
		model.addAttribute("currentAccount", currentAccount);
		
		Image currentImage = imageService.findImageByImageId(imageId);
		model.addAttribute("currentImage", currentImage);
		
		Page<Comment> commentsOfImage = commentService.pageQuery(1, Page.DEFAULT_PAGE_SIZE, currentImage.getImageId());
		model.addAttribute("currentComments", commentsOfImage.getData());
		
		return "detail";
	}

	@RequestMapping(value = "/{imageId}/download", method = RequestMethod.GET)
	@ResponseBody
	public String download(@PathVariable String imageId, HttpServletResponse response, Model model, HttpServletRequest req) {
		try {
			Image image = imageService.findImageByImageId(imageId);

			String basePath = HttpUtils.getWebProjectPath();
			String imagePath = basePath + image.getLocation();
			System.out.println(imagePath);
			File file = new File(imagePath);
			if (!file.exists()) {
				return "this image is remove！";
			}

			response.setContentType("application/x-msdownload");
			response.setHeader("Content-disposition", "attachment; filename=" + new String(imagePath.getBytes("utf-8"), "ISO8859-1"));

			FileInputStream in = new FileInputStream(file);
			OutputStream out = response.getOutputStream();
			byte[] buf = new byte[1024];
			int len = 0;
			while ((len = in.read(buf)) > 0) {
				out.write(buf, 0, len);
			}
			out.flush();
			out.close();
			in.close();

			imageService.downloadImageSuccess(image);
		} catch (IOException e) {
			return "出错了";
		}
		return "下载成功！";
	}

	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	@ResponseBody
	public String upload(@RequestParam("imageFile") MultipartFile imageFile, Image image,
						 HttpServletRequest request) {
		if (imageFile.isEmpty()) {
			return "请选择上传的图片！";
		}

		Account account = (Account) request.getSession().getAttribute(Constant.CURRENT_ACCOUNT);
		String location = Constant.UPLOAD_IMAGE_LOCATION + "/" + imageFile.getOriginalFilename();

		jmsTemplate.send("pp.uploadImage.queue", new MessageCreator() {
			@Override
			public Message createMessage(Session session) throws JMSException {
				return session.createObjectMessage((Serializable) imageFile);
			}
		});
		imageService.uploadImageSuccess(location, account, image);

		return "上传成功！";
	}

	@Resource
	public void setImageService(ImageService imageService) {
		this.imageService = imageService;
	}

	@Resource
	public void setCommentService(CommentService commentService) {
		this.commentService = commentService;
	}

	@Resource
	public void setJmsTemplate(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}
	
}
