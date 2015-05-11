package com.ping.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "t_account")
public class Account implements Serializable {
    private static final long serialVersionUID = -1718018743979275416L;

    private String accountId;

    private String username;
    private String email;
    private String password;

    private int age;
    private String sex;
    private String introduction;
    private String thumb;

    private Timestamp registerDate;
    private Timestamp lastLoginDate;
    private boolean lock;
    private long credit;
    private long fans;

    private String subscribeImageIds;
    private String collectImageIds;

    private String subscribeIds;
    private String publishIds;

    private String roleIds;

    public Account() {
    }

    public Account(String nickName) {
        this.username = nickName;
    }

    @Id
    @Column(name = "account_id")
    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    @Column(name = "account_username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = "account_email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "account_password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "account_age")
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Column(name = "account_sex")
    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Column(name = "account_introduction")
    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    @Column(name = "account_thumb")
    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    @Column(name = "account_registerDate")
    public Timestamp getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Timestamp registerDate) {
        this.registerDate = registerDate;
    }

    @Column(name = "account_lastLoginDate")
    public Timestamp getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(Timestamp lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    @Column(name = "account_lock")
    public boolean getLock() {
        return lock;
    }

    public Account setLock(boolean lock) {
        this.lock = lock;
        return this;
    }

    @Column(name = "account_credit")
    public long getCredit() {
        return credit;
    }

    public void setCredit(long credit) {
        this.credit = credit;
    }

    @Column(name = "account_fans")
    public long getFans() {
        return fans;
    }

    public void setFans(long fans) {
        this.fans = fans;
    }

    @Column(name = "account_subscribeImageIds")
    public String getSubscribeImageIds() {
        return subscribeImageIds;
    }

    public void setSubscribeImageIds(String subscribeImageIds) {
        this.subscribeImageIds = subscribeImageIds;
    }

    @Column(name = "account_collectImageIds")
    public String getCollectImageIds() {
        return collectImageIds;
    }

    public void setCollectImageIds(String collectImageIds) {
        this.collectImageIds = collectImageIds;
    }

    @Column(name = "account_subscribeIds")
    public String getSubscribeIds() {
        return subscribeIds;
    }

    public void setSubscribeIds(String subscribeIds) {
        this.subscribeIds = subscribeIds;
    }

    @Column(name = "account_publishIds")
    public String getPublishIds() {
        return publishIds;
    }

    public void setPublishIds(String publishIds) {
        this.publishIds = publishIds;
    }

    @Column(name = "account_roleIds")
    public String getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(String roleIds) {
        this.roleIds = roleIds;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((accountId == null) ? 0 : accountId.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Account other = (Account) obj;
        if (accountId == null) {
            if (other.accountId != null)
                return false;
        } else if (!accountId.equals(other.accountId))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Account [accountId=" + accountId + ", username=" + username
                + ", email=" + email + ", password=" + password + ", age="
                + age + ", sex=" + sex + ", introduction=" + introduction
                + ", thumb=" + thumb + ", registerDate=" + registerDate
                + ", lastLoginDate=" + lastLoginDate + ", lock=" + lock
                + ", credit=" + credit + ", fans=" + fans
                + ", subscribeImageIds=" + subscribeImageIds
                + ", collectImageIds=" + collectImageIds + ", subscribeIds="
                + subscribeIds + ", publishIds=" + publishIds + ", "
                + ", roleIds=" + roleIds + "]";
    }


}

