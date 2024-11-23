package org.nqm.er.common.entity;

import java.io.Serializable;
import jakarta.persistence.Embeddable;

@Embeddable
public class NypostSiteMapId implements Serializable {

    private static final long serialVersionUID = 1L;

    public int year;
    public int month;
    public String url;

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + year;
        result = prime * result + month;
        result = prime * result + ((url == null) ? 0 : url.hashCode());
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
        NypostSiteMapId other = (NypostSiteMapId) obj;
        if (year != other.year)
            return false;
        if (month != other.month)
            return false;
        if (url == null) {
            if (other.url != null)
                return false;
        }
        else if (!url.equals(other.url))
            return false;
        return true;
    }
}
