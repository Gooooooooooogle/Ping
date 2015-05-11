package com.ping.dao;

import com.ping.core.Page;
import com.ping.domain.Comment;
import org.springframework.stereotype.Repository;

/**
 * �������۵�DAO��
 * @author ppteam
 */
@Repository
public class CommentDao extends BaseDao<Comment> {
	private static final String COMMENT_FOR_IMAGE = "from Comment c where c.imageId=?";
	
	public Page<Comment> pageQuery(int pageNo, int pageSize, String imageId) {
		return super.pagedQuery(COMMENT_FOR_IMAGE, pageNo, pageSize, imageId);
	}
	
}
