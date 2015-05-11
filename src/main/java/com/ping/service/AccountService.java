package com.ping.service;

import com.ping.domain.Account;

import java.util.List;
import java.util.Set;

public interface AccountService {
	
	public Account findAccountByEmail(String accountId);

	public Account findAccountByUsername(String accountId);
	
	public Account findAccountByAccountId(String accountId);
	
	public Set<String> findRolesByUsername(String username);
	
	public Set<String> findPermissionsByUsername(String username);
	
	public void updateAccount(Account account);

    public void deleteAccount(String accountId);
    
    public List<Account> findAll();
    
    public Account createAccount(Account account);
    
    public void changePassword(String accountId, String newPassword);
    
    public void lockAccount(String username);
    
    public void unockAccount(String username);
    
    
    public void subscribeSuccess(Account account, int accountId);
    
    public void unsubscribeSuccess(Account account, int accountId);
    
    public void checkSuccess(Account account);
    
    public void loginSuccess(Account account);
    
    public void collectSuccess(Account account, long imageId);
    
    public void unCollectSuccess(Account account, long imageId);

}
