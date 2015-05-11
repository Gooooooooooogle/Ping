package com.ping.dao;

import com.ping.domain.EmailCheck;
import org.springframework.stereotype.Repository;

@Repository
public class EmailCheckDao extends BaseDao<EmailCheck> {
	private static final String CHECK_CODE = "from CheckCode c where c.checkCode=?";
	
	public EmailCheck findCheckCode(String checkCode) {
		return (EmailCheck) hibernateTemplate.find(CHECK_CODE, checkCode).get(0);
	}
}
