package com.ping.service.impl;

import javax.transaction.Transactional;

import com.ping.core.Page;
import com.ping.core.util.DateUtils;
import com.ping.dao.CommentDao;
import com.ping.dao.ImageDao;
import com.ping.domain.Comment;
import com.ping.domain.Image;
import com.ping.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("commentService")
public class CommentServiceImpl implements CommentService {
	
	@Autowired
	private CommentDao commentDao;
	@Autowired
	private ImageDao imageDao;
	
	/**
	 * 评论成功，图片hot加1
	 * @param image
	 * @param text
	 */
	public void commentSuccess(Image image, String text, String username) {
		Comment comment = new Comment();
		comment.setCommentDate(DateUtils.getNowByTimestamp());
		comment.setContent(text);
		comment.setImageId(image.getImageId());
		comment.setUsername(username);
		commentDao.save(comment);
		
		image.setHot(image.getHot() + 1);
		imageDao.update(image);
	}
	
	@Transactional
	public Page<Comment> pageQuery(int pageNo, int pageSize, String imageId) {
		return commentDao.pageQuery(pageNo, pageSize, imageId);
	}

}
