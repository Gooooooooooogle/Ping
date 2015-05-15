package com.ping.dao;

import java.util.List;

import com.ping.domain.Cataloge;
import org.springframework.stereotype.Repository;

/**
 * Õº∆¨∑÷¿‡∆˜DAO¿‡
 * @author ex
 * @version 0.1
 */
@Repository
public class CatalogeDao extends BaseDao<Cataloge> {
	public static final String CATALOGE_ALL = "select c.catalogeName from Cataloge c";
	
	@SuppressWarnings("unchecked")
	public List<String> findAllForCatalogeName() {
		return (List<String>) hibernateTemplate.find(CATALOGE_ALL);
	}

	
}
