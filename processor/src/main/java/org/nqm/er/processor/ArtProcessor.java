package org.nqm.er.processor;

import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.nqm.er.common.constant.MessageConstant;
import org.nqm.er.common.dto.NypostSiteMapDto;
import org.nqm.er.common.entity.NypostSiteMap;
import io.smallrye.reactive.messaging.annotations.Blocking;
import io.vertx.core.json.JsonObject;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class ArtProcessor {

    @Incoming(MessageConstant.CHANNEL_SITEMAP)
    @Blocking
    @Transactional
    void getMonthUrlsFromNypostSitemap(JsonObject msg) {
        var monthSitemap = msg.mapTo(NypostSiteMapDto.class);
        NypostSiteMap.persist(monthSitemap);
    }
}
