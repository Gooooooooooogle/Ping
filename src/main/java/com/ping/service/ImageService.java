package com.ping.service;

import com.ping.core.Page;
import com.ping.domain.Account;
import com.ping.domain.Image;

import java.util.List;

public interface ImageService {
	
	public void createImage(Image image);
	
	public int find(String hql, Object... params);
	
	public Image findImageByImageId(String imageId);
	
	public Page<Image> pageQuery(int pageNo, int pageSize);
	
	public Page<Image> pagedQuery(String hql, int pageNo, int pageSize);
	
	public List<Image> findImageForRandom(int sum, String username);
	
	public List<Image> findImageForLatest(int sum, String username);
	
	public List<Image> findImageFotHot(int sum, String username);
	
	public void downloadImageSuccess(Image image);
	
	public void uploadImageSuccess(String location, Account account, Image image);
	
}
