package com.ping.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 记录访问用户主页的实体类
 * @author ex
 */
@Entity
@Table(name = "t_guest")
public class Guest implements Serializable {
    private static final long serialVersionUID = -2482254577660374966L;

    private String guestId;

    private String username;
    private String thumb;
    private Timestamp lastVisitedTime;

    @Id
    @Column(name = "guest_id")
    public String getGuestId() {
        return guestId;
    }

    public void setGuestId(String guestId) {
        this.guestId = guestId;
    }

    @Column(name = "guest_username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = "guest_thumb")
    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    @Column(name = "guest_lastVisitedTime")
    public Timestamp getLastVisitedTime() {
        return lastVisitedTime;
    }

    public void setLastVisitedTime(Timestamp lastVisitedTime) {
        this.lastVisitedTime = lastVisitedTime;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((guestId == null) ? 0 : guestId.hashCode());
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
        Guest other = (Guest) obj;
        if (guestId == null) {
            if (other.guestId != null)
                return false;
        } else if (!guestId.equals(other.guestId))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Guest [guestId=" + guestId + ", username=" + username
                + ", thumb=" + thumb + ", lastVisitedTime=" + lastVisitedTime
                + "]";
    }

}
