package com.ping.service;

import com.ping.core.Page;
import com.ping.domain.Comment;
import com.ping.domain.Image;

public interface CommentService {

	public void commentSuccess(Image image, String text, String username);
	
	public Page<Comment> pageQuery(int pageNo, int pageSize, String imageId);
	
}
