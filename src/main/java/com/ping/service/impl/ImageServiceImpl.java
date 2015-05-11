package com.ping.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.ping.core.Page;
import com.ping.core.util.DateUtils;
import com.ping.core.util.NumberUtils;
import com.ping.dao.ImageDao;
import com.ping.domain.Account;
import com.ping.domain.Image;
import com.ping.service.AccountService;
import com.ping.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 实现与图片相关的功能的服务类
 * @author ppteam
 */
@Service("imageService")
public class ImageServiceImpl implements ImageService {
	
	@Autowired
	private ImageDao imageDao;
	
	private AccountService accountService;
	
	@Override
	public void createImage(Image image) {
		imageDao.save(image);
	}
	
	@Override
	public int find(String hql, Object... params) {
		return imageDao.find(hql, params).size();
	}
	
	@Override
	public Image findImageByImageId(String imageId) {
		return imageDao.get(imageId);
	}
	
	/**
	 * 分页查询图片
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@Override
	public Page<Image> pageQuery(int pageNo, int pageSize) {
		return imageDao.pageQuery(pageNo, pageSize);
	}
	
	@Override
	public Page<Image> pagedQuery(String hql, int pageNo, int pageSize) {
		return imageDao.pagedQuery(hql, pageNo, pageSize);
	}
	
	/**
	 * 根据用户名任意取出指定数量的图片
	 * @param sum
	 * @param username
	 * @return
	 */
	@Override
	public List<Image> findImageForRandom(int sum, String username) {
		return imageDao.findImageForRandom(sum, username).getData();
	}
	
	/**
	 * 按时间顺序取出指定数量的图片
	 * @param sum
	 * @param username
	 * @return
	 */
	@Override
	public List<Image> findImageForLatest(int sum, String username) {
		return imageDao.findImageForLatest(sum, username).getData();
	}
	
	/**
	 * 按热度递减的顺序取出指定数量的图片
	 * @param sum
	 * @param username
	 * @return
	 */
	@Override
	public List<Image> findImageFotHot(int sum, String username) {
		return imageDao.findImageFotHot(sum, username).getData();
	}
	
	/**
	 * 下载图片成功，图片用户积分加3，图片热度加3
	 * @param image
	 */
	@Override
	public void downloadImageSuccess(Image image) {
		image.setHot(image.getHot() + 1);
		imageDao.update(image);
		
		Account account = accountService.findAccountByUsername(image.getUsername());
		account.setCredit(account.getCredit() + 1);
		accountService.updateAccount(account);
	}
	
	/**
	 * 上传图片成功，保存图片Id到用户列表，用户积分加1
	 * @param location
	 * @param account
	 */
	@Override
	public void uploadImageSuccess(String location, Account account, Image image) {
		image.setLocation(location);
		image.setUploadTime(DateUtils.getNowByTimestamp());
		image.setImageId(NumberUtils.generateUUID());
		createImage(image);
		
		account.setCredit(account.getCredit() + 1);
		accountService.updateAccount(account);
	}

	public void checkSearch() {

	}

	@Resource
	public void setAccountService(AccountService accountService) {
		this.accountService = accountService;
	}
	
	
}
