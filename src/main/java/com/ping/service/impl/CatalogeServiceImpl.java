package com.ping.service.impl;

import java.util.List;

import com.ping.dao.CatalogeDao;
import com.ping.service.CatalogeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 图片分类服务器
 * @author tianym
 */
@Service("catalogeService")
public class CatalogeServiceImpl implements CatalogeService {
	
	@Autowired
	private CatalogeDao catalogeDao;
	
	/**
	 * 返回所有已创建的分类名
	 * @return
	 */
	@Override
	public List<String> findAll() {
		return catalogeDao.findAllForCatalogeName();
	}
	
}
