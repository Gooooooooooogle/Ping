package com.ping.dao;

import com.ping.core.Page;
import com.ping.domain.Image;
import org.springframework.stereotype.Repository;

/**
 * 基于图片的DAO类
 * @author ex
 */
@Repository
public class ImageDao extends BaseDao<Image> {
	private static final String IMAGE_FOR_RANDOM = "from Image i where i.username=?";
	private static final String IMAGE_FOR_LATEST = "from Image i where i.username=? order by i.uploadTime DESC";
	private static final String IMAGE_OF_HOT = "from Image i where i.username=? order by i.hot DESC";
	private static final String PAGE_QUERY = "from Image";
	
	public Page<Image> pageQuery(int pageNo, int pageSize) {
		return super.pagedQuery(PAGE_QUERY, pageNo, pageSize);
	}
	
	public Page<Image> findImageForRandom(int sum, String username) {
		return (Page<Image>) super.pagedQuery(IMAGE_FOR_RANDOM, 1, sum, username);
	}
	
	public Page<Image> findImageForLatest(int sum, String username) {
		return (Page<Image>) super.pagedQuery(IMAGE_FOR_LATEST, 1, sum, username);
	}
	
	public Page<Image> findImageFotHot(int sum, String username) {
		return (Page<Image>) super.pagedQuery(IMAGE_OF_HOT, 1, sum, username);
	}

}
