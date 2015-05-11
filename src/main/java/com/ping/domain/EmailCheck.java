package com.ping.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "t_emailcheck")
public class EmailCheck implements Serializable {
    private static final long serialVersionUID = 8433971403184400937L;

    private String checkCodeId;
    private String username;
    private String checkCode;

    private Timestamp generateTime;

    public EmailCheck() {
        super();
    }

    public EmailCheck(String checkCodeId, String username, String checkCode) {
        super();
        this.checkCodeId = checkCodeId;
        this.username = username;
        this.checkCode = checkCode;
    }

    @Id
    @Column(name = "checkCode_id")
    public String getCheckCodeId() {
        return checkCodeId;
    }

    public void setCheckCodeId(String checkCodeId) {
        this.checkCodeId = checkCodeId;
    }

    @Column(name = "checkCode_username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = "checkCode_cc")
    public String getCheckCode() {
        return checkCode;
    }

    public void setCheckCode(String checkCode) {
        this.checkCode = checkCode;
    }

    @Column(name = "checkCode_generateTime")
    public Timestamp getGenerateTime() {
        return generateTime;
    }

    public void setGenerateTime(Timestamp generateTime) {
        this.generateTime = generateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EmailCheck that = (EmailCheck) o;

        if (checkCodeId != null ? !checkCodeId.equals(that.checkCodeId) : that.checkCodeId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return checkCodeId != null ? checkCodeId.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "CheckCode [checkCodeId=" + checkCodeId + ", username="
                + username + ", checkCode=" + checkCode + "]";
    }

}

