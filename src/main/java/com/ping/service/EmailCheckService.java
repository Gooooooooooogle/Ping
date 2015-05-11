package com.ping.service;

import com.ping.domain.EmailCheck;

public interface EmailCheckService {

	public EmailCheck findCheckCode(String checkCode);
	
}
