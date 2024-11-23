package org.nqm.er.common.entity;

import org.nqm.er.common.dto.NypostSiteMapDto;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;

@Entity(name = "nypost_sitemap")
public class NypostSiteMap extends PanacheEntityBase {

    @EmbeddedId
    public NypostSiteMapId pk;

    public boolean crawled;

    public static void persist(NypostSiteMapDto nypostSiteMapDto) {
        nypostSiteMapDto.months().stream()
                .map(dto -> {
                    var entity = new NypostSiteMap();
                    entity.setYear(nypostSiteMapDto.year());
                    entity.setMonth(dto.month());
                    entity.setUrl(dto.url());
                    return entity;
                })
                .filter(entity -> {
                    return findById(entity.pk) == null;
                })
                .forEach(e -> e.persist());
    }

    public void setYear(int year) {
        if (this.pk == null) {
            this.pk = new NypostSiteMapId();
        }
        this.pk.year = year;
    }

    public void setMonth(int month) {
        if (this.pk == null) {
            this.pk = new NypostSiteMapId();
        }
        this.pk.month = month;
    }

    public void setUrl(String url) {
        if (this.pk == null) {
            this.pk = new NypostSiteMapId();
        }
        this.pk.url = url;
    }

    @Override
    public String toString() {
        return "year=%d month=%d url=%s crawled=%b"
                .formatted(pk.year, pk.month, pk.url, crawled);
    }
}
