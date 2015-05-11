package com.ping.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "t_cataloge")
public class Cataloge implements Serializable {
    private static final long serialVersionUID = -7390094903896216971L;

    private String catalogeId;
    private String catalogeName;

    public Cataloge() {
    }

    public Cataloge(String catalogeName) {
        this.catalogeName = catalogeName;
    }

    @Id
    @Column(name = "cataloge_id")
    public String getCatalogeId() {
        return catalogeId;
    }

    public void setCatalogeId(String catalogeId) {
        this.catalogeId = catalogeId;
    }

    @Column(name = "cataloge_name")
    public String getCatalogeName() {
        return catalogeName;
    }

    public void setCatalogeName(String catalogeName) {
        this.catalogeName = catalogeName;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((catalogeId == null) ? 0 : catalogeId.hashCode());
        result = prime * result
                + ((catalogeName == null) ? 0 : catalogeName.hashCode());
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
        Cataloge other = (Cataloge) obj;
        if (catalogeId == null) {
            if (other.catalogeId != null)
                return false;
        } else if (!catalogeId.equals(other.catalogeId))
            return false;
        if (catalogeName == null) {
            if (other.catalogeName != null)
                return false;
        } else if (!catalogeName.equals(other.catalogeName))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Cataloge [catalogeId=" + catalogeId + ", catalogeName="
                + catalogeName + "]";
    }

}

