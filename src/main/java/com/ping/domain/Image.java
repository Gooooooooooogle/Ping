package com.ping.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "t_image")
public class Image implements Serializable {
    private static final long serialVersionUID = 7886311010994984923L;

    private String imageId;

    private String title;
    private String describe;
    private String cataloge;

    private long hot;
    private String location;
    private Timestamp uploadTime;

    private boolean checked;

    private String username;

    private boolean autoDelete;
    private Timestamp autoDeleteTime;

    public Image() {
    }

    @Id
    @Column(name = "image_id")
    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    @Column(name = "image_title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(name = "image_describe")
    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    @Column(name = "image_cataloge")
    public String getCataloge() {
        return cataloge;
    }

    public void setCataloge(String cataloge) {
        this.cataloge = cataloge;
    }

    @Column(name = "image_location")
    public String getLocation() {
        return location;
    }

    public Image setLocation(String location) {
        this.location = location;
        return this;
    }

    @Column(name = "image_uploadTime")
    public Timestamp getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Timestamp uploadTime) {
        this.uploadTime = uploadTime;
    }

    @Column(name = "image_checked")
    public boolean getChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    @Column(name = "image_username")
    public String getUsername() {
        return username;
    }

    public Image setUsername(String username) {
        this.username = username;
        return this;
    }

    @Column(name = "image_autoDelete")
    public boolean getAutoDelete() {
        return autoDelete;
    }

    public void setAutoDelete(boolean autoDelete) {
        this.autoDelete = autoDelete;
    }

    @Column(name = "image_autoDeleteTime")
    public Timestamp getAutoDeleteTime() {
        return autoDeleteTime;
    }

    public void setAutoDeleteTime(Timestamp autoDeleteTime) {
        this.autoDeleteTime = autoDeleteTime;
    }

    @Column(name = "image_hot")
    public long getHot() {
        return hot;
    }

    public void setHot(long hot) {
        this.hot = hot;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((imageId == null) ? 0 : imageId.hashCode());
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
        Image other = (Image) obj;
        if (imageId == null) {
            if (other.imageId != null)
                return false;
        } else if (!imageId.equals(other.imageId))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Image [imageId=" + imageId + ", title=" + title + ", describe="
                + describe + ", cataloge=" + cataloge + ", hot=" + hot
                + ", location=" + location + ", uploadTime=" + uploadTime
                + ", checked=" + checked + ", username=" + username
                + ", autoDelete=" + autoDelete + ", autoDeleteTime="
                + autoDeleteTime + "]";
    }

}
