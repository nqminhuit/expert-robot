package org.nqm.er.common.repository;

import java.util.UUID;
import org.nqm.er.common.entity.NypostSiteMap;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

@ApplicationScoped
public class NypostSiteMapRepository implements PanacheRepositoryBase<NypostSiteMap, UUID> {

    @Inject
    EntityManager em;

    public NypostSiteMap findRandomUncrawledMonthUrl() {
        var query = em.createNativeQuery(
                "select * from nypost_sitemap where random() < 0.01 limit 1",
                NypostSiteMap.class);
        return (NypostSiteMap) query.getSingleResult();
    }
}
