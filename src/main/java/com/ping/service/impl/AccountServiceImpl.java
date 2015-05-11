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
	 * �����˻��������û�����(����)���м���
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
			
			//������֤�ʼ���ע���û�
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
     * �����û����񣬱����ĵ��û���˿����1�������û����id
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
     * ȡ������ָ���û�,ָ���û���˿����1�������û�ɾ��id
     * @param account ��ǰ�û�
     * @param accountId ָ���û�Id
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
     * �û�������֤�ɹ������û�����
     * @param account
     */
	@Override
	public void checkSuccess(Account account) {
		accountDao.update(account.setLock(false));
	}
	
	/**
	 * ��¼�ɹ����û����ּ�1������¼��־
	 */
	public void loginSuccess(Account currentAccount) {
		if (currentAccount != null) {
			currentAccount.setCredit(currentAccount.getCredit() + 1);
	        accountDao.update(currentAccount);
		}
	}
	
	/**
     * �ղ�ָ��Id��ͼƬ,ͼƬ�ȶȼ�10
     * @param account ��ǰ�û�
     * @param imageId ָ��ͼƬId
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
     * ȡ���ղ�ָ��ͼƬ
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
