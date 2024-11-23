package org.nqm.er.common.entity;

import java.util.UUID;
import org.nqm.er.common.dto.NypostSiteMapDto;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity(name = "nypost_sitemap")
public class NypostSiteMap extends PanacheEntityBase {

    @Id
    @GeneratedValue
    public UUID id;
    public int year;
    public int month;
    public String url;

    public static void persist(NypostSiteMapDto nypostSiteMapDto) {
        nypostSiteMapDto.months().stream()
                .map(dto -> {
                    var entity = new NypostSiteMap();
                    entity.year = nypostSiteMapDto.year();
                    entity.month = dto.month();
                    entity.url = dto.url();
                    return entity;
                })
                .forEach(e -> e.persist());
    }
}
