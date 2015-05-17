package com.ping.service.impl;

import java.util.List;

import com.ping.dao.CatalogeDao;
import com.ping.domain.Cataloge;
import com.ping.service.CatalogeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ͼƬ���������
 * @author ex
 */
@Service("catalogeService")
public class CatalogeServiceImpl implements CatalogeService {
	
	@Autowired
	private CatalogeDao catalogeDao;

	public List<Cataloge> findAll() {
		return catalogeDao.loadAll();
	}
	
}
