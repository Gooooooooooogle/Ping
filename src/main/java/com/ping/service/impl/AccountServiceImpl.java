package com.ping.service.impl;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import com.ping.core.Constant;
import com.ping.core.util.AESUtils;
import com.ping.core.util.DateUtils;
import com.ping.core.util.NumberUtils;
import com.ping.dao.AccountDao;
import com.ping.dao.EmailCheckDao;
import com.ping.dao.ImageDao;
import com.ping.domain.Account;
import com.ping.domain.EmailCheck;
import com.ping.domain.Image;
import com.ping.service.AccountService;
import com.ping.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service("accountService")
public class AccountServiceImpl implements AccountService {
	
	private static final String FIND_ACCOUNT_BY_EMAIL = "from Account a where a.email=?";
	private static final String FIND_ACCOUNT_BY_USERNAME = "from Account a where a.username=?";
	
	@Autowired
	private ImageDao imageDao;
	@Autowired
	private AccountDao accountDao;
	@Autowired
	private EmailCheckDao emailCheckDao;
	@Autowired
	private SimpleMailMessage completeRegisterCheck;
	@Autowired
	private MailSender mailSender;
	
	private RoleService roleService;
	
	@Override
	public Account findAccountByEmail(String accountId) {
		List<Account> accounts = accountDao.find(FIND_ACCOUNT_BY_EMAIL, accountId);
		if (accounts.size() <= 0) {
			return null;
		}
		return accounts.get(0);
	}
	@Override
	public Account findAccountByUsername(String accountId) {
		List<Account> accounts = accountDao.find(FIND_ACCOUNT_BY_USERNAME, accountId);
		if (accounts.size() <= 0) {
			return null;
		}
		return accounts.get(0);
	}
	@Override
	public Account findAccountByAccountId(String accountId) {
		return accountDao.get(accountId);
	}
	@Override
	public Set<String> findRolesByUsername(String username) {
		Account account = findAccountByUsername(username);
        if (account == null) {
            return Collections.emptySet();
        }
        
        return roleService.findRolesByRoleIds(account.getRoleIds());
	}
	
	@Override
	public Set<String> findPermissionsByUsername(String username) {
		Account account = findAccountByUsername(username);
		if (account == null) {
			return Collections.emptySet();
		}
		return roleService.findPermissionsByRoleIds(account.getRoleIds());
	}
	
	@Override
	public void updateAccount(Account account) {
		accountDao.update(account);
	}
	
	@Override
	public void deleteAccount(String accountId) {
		accountDao.delete(findAccountByAccountId(accountId));
	}
	
	@Override
	public List<Account> findAll() {
		return accountDao.loadAll();
	}
	
	/**
	 * 创建账户，并将用户资料(密码)进行加密
	 * @param account
	 * @return
	 */
	@Override
	public Account createAccount(Account account) {
		try {
			account.setRegisterDate(new Timestamp(System.currentTimeMillis()));
			account.setLock(true);
			account.setAccountId(NumberUtils.generateUUID());
			
			String encryptPassword = AESUtils.parseByte2HexStr(
					AESUtils.encrypt(account.getPassword().getBytes(), Constant.KEY));
			account.setPassword(encryptPassword);
			
			accountDao.save(account);
			
			//发送验证邮件给注册用户
			String[] uuid = NumberUtils.generateUUID(2);
			EmailCheck checkCodeForAccount = new EmailCheck(uuid[0], account.getUsername(), uuid[1]);
			checkCodeForAccount.setGenerateTime(DateUtils.getNowByTimestamp());
			emailCheckDao.save(checkCodeForAccount);
			
			SimpleMailMessage message = new SimpleMailMessage(completeRegisterCheck);
			message.setText(String.format(completeRegisterCheck.getText(), uuid[1], 
					DateUtils.getNowByTimestamp(), DateUtils.convertToTimeText(Constant.CHECKCODE_AVAILABLE)));
			message.setTo(account.getEmail());
			mailSender.send(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return account;
	}
	
	@Override
	public void changePassword(String accountId, String newPassword) {
		Account account = findAccountByAccountId(accountId);
        account.setPassword(newPassword);
        accountDao.update(account);
	}
	
	@Override
	public void lockAccount(String username) {
		Account account = this.findAccountByUsername(username);
        account.setLock(true);
        accountDao.update(account);
	}
	
	@Override
	public void unockAccount(String username) {
		Account account = this.findAccountByUsername(username);
	    account.setLock(false);
	    accountDao.update(account);
	}
	
	/**
     * 订阅用户服务，被订阅的用户粉丝数加1，订阅用户添加id
     * @param account
     * @param accountId
     */
	@Override
	public void subscribeSuccess(Account account, int accountId) {
		account.getSubscribeIds().concat("," + accountId);
        accountDao.update(account);

        Account _account = accountDao.get(accountId);
        _account.setFans(_account.getFans() + 1);
        _account.getPublishIds().concat("," + account.getAccountId());
        _account.setCredit(_account.getCredit() + 15);
        accountDao.update(_account);
	}
	
	/**
     * 取消订阅指定用户,指定用户粉丝数减1，订阅用户删除id
     * @param account 当前用户
     * @param accountId 指定用户Id
     */
	@Override
	public void unsubscribeSuccess(Account account, int accountId) {
		account.getSubscribeIds().replace("," + accountId, "");
        accountDao.update(account);

        Account _account = accountDao.get(accountId);
        _account.setFans(_account.getFans() - 1);
        _account.getPublishIds().concat("," + account.getAccountId());
        _account.setCredit(_account.getCredit() - 15);
        accountDao.update(_account);
	}
	
	/**
     * 用户邮箱验证成功，将用户解锁
     * @param account
     */
	@Override
	public void checkSuccess(Account account) {
		accountDao.update(account.setLock(false));
	}
	
	/**
	 * 登录成功，用户积分加1，并记录日志
	 */
	public void loginSuccess(Account currentAccount) {
		if (currentAccount != null) {
			currentAccount.setCredit(currentAccount.getCredit() + 1);
	        accountDao.update(currentAccount);
		}
	}
	
	/**
     * 收藏指定Id的图片,图片热度加10
     * @param account 当前用户
     * @param imageId 指定图片Id
     */
	@Override
	public void collectSuccess(Account account, long imageId) {
		account.getCollectImageIds().concat("," + imageId);
        accountDao.update(account);

        Image image = imageDao.get(imageId);
        image.setHot(image.getHot() + 10);
        imageDao.update(image);
	}
	
	/**
     * 取消收藏指定图片
     * @param account
     * @param imageId
     */
	@Override
	public void unCollectSuccess(Account account, long imageId) {
		account.getCollectImageIds().replace("," + imageId, "");
        accountDao.update(account);

        Image image = imageDao.get(imageId);
        image.setHot(image.getHot() - 10);
        imageDao.update(image);
	}
	
	@Resource
	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}
	
}
