package com.ping.core.util;

import com.ping.service.AccountService;
import com.ping.service.ImageService;
import com.ping.service.impl.AccountServiceImpl;
import com.ping.service.impl.ImageServiceImpl;

/**
 * �ڶ��̻߳����£�ͨ�����ؾֲ��������в�ͬ
 * controllerʵ����״̬, ��ʹ������singleton����
 * @author tianym
 * @version 0.1(��spring����ͬ��������)
 */
@Deprecated
public class ThreadLocalUtils {
	
	private static ThreadLocal<AccountService> accountServiceThreadLocal = new ThreadLocal<AccountService>();
	
	private static ThreadLocal<ImageService> imageServiceThreadLocal = new ThreadLocal<ImageService>();
	
	public static AccountService getAccountService() {
		if (accountServiceThreadLocal.get() == null) {
			AccountService accountService = new AccountServiceImpl();
			accountServiceThreadLocal.set(accountService);
			return accountService;
		} else {
			return accountServiceThreadLocal.get();
		}
	}
	
	public static ImageService getImageService() {
		if (imageServiceThreadLocal.get() == null) {
			ImageService imageService = new ImageServiceImpl();
			imageServiceThreadLocal.set(imageService);
			return imageService;
		} else {
			return imageServiceThreadLocal.get();
		}
	}

}
