package com.ping.service.impl;

import com.ping.dao.EmailCheckDao;
import com.ping.domain.EmailCheck;
import com.ping.service.EmailCheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("emailService")
public class EmailCheckServiceImpl implements EmailCheckService {

	@Autowired
	private EmailCheckDao emailCheckDao;

	public EmailCheck findCheckCode(String checkCode) {
		return emailCheckDao.findCheckCode(checkCode);
	}
	
}
