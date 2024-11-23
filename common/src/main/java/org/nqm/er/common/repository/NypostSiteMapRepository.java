package org.nqm.er.common.repository;

import org.nqm.er.common.entity.NypostSiteMap;
import org.nqm.er.common.entity.NypostSiteMapId;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

@ApplicationScoped
public class NypostSiteMapRepository implements PanacheRepositoryBase<NypostSiteMap, NypostSiteMapId> {

    @Inject
    EntityManager em;

    public NypostSiteMap findRandomUncrawledMonthUrl() {
        var query = em.createNativeQuery(
                "select * from nypost_sitemap where random() < 0.1 limit 1",
                NypostSiteMap.class);
        if (query.getSingleResult() instanceof NypostSiteMap result) {
            return result;
        }
        return null;
    }
}
