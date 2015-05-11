package com.ping.web.shiro;

import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import com.ping.core.Constant;
import com.ping.core.util.AESUtils;
import com.ping.domain.Account;
import com.ping.service.AccountService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;

public class AccountRealm extends AuthorizingRealm {

	private AccountService accountService;
	
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
		String accountId = (String) super.getAvailablePrincipal(principalCollection);
		Account currentAccount = findAccount(accountId);
		if (currentAccount == null) {
			return null;
		}
		
		Set<String> roleList = accountService.findRolesByUsername(currentAccount.getUsername());
		Set<String> permissionList = accountService.findPermissionsByUsername(currentAccount.getUsername());
		
		SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
		simpleAuthorizationInfo.addRoles(roleList);
		simpleAuthorizationInfo.addStringPermissions(permissionList);
		return simpleAuthorizationInfo;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) 
			throws AuthenticationException {
		UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
		String accountId = (String) token.getUsername();
		String password = new String(token.getPassword());
		Account currentAccount = findAccount(accountId);
		
		if (currentAccount == null) {
			return null;
		}
		
		String passwordForAccount = null;
		try {
        	byte[] _password = AESUtils.parseHexStr2Byte(currentAccount.getPassword());
        	passwordForAccount = new String(AESUtils.decrypt(_password, Constant.KEY));
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (!password.equals(passwordForAccount)) {
			return null;
		} 
		
		AuthenticationInfo authcInfo = new SimpleAuthenticationInfo(
				currentAccount.getUsername(),
				passwordForAccount,
				this.getName());  
		this.setSession(Constant.CURRENT_ACCOUNT, currentAccount);
		return authcInfo;
	}
	
	private Account findAccount(String accountId) {
		if (accountId.trim().equals("") || accountId == null) {
    		return null;
    	}
    	
    	accountId = accountId.toLowerCase();
		String emailRegex = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
		Pattern pattern = Pattern.compile(emailRegex);
		Matcher matcher = pattern.matcher(accountId);
		
		Account currentAccount = null;
		if (matcher.find()) {
			currentAccount = accountService.findAccountByEmail(accountId);
		} else {
			currentAccount = accountService.findAccountByUsername(accountId);
		}
		
		return currentAccount;
	}
	
	/**
	 * 将一些数据放到ShiroSession中,以便于其它地方使用
	 * @param key
	 * @param value
	 */
	private void setSession(Object key, Object value) {
		Subject currentAccount = SecurityUtils.getSubject();
		if (currentAccount != null) {
			Session session = currentAccount.getSession();
			
			if (session != null) {
				session.setAttribute(key, value);
			}
		}
	}

	@Resource
	public void setAccountService(AccountService accountService) {
		this.accountService = accountService;
	}
	

}
