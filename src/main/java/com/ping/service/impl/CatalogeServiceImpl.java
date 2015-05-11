package com.ping.service.impl;

import java.util.List;

import com.ping.dao.CatalogeDao;
import com.ping.service.CatalogeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ͼƬ���������
 * @author tianym
 */
@Service("catalogeService")
public class CatalogeServiceImpl implements CatalogeService {
	
	@Autowired
	private CatalogeDao catalogeDao;
	
	/**
	 * ���������Ѵ����ķ�����
	 * @return
	 */
	@Override
	public List<String> findAll() {
		return catalogeDao.findAllForCatalogeName();
	}
	
}
