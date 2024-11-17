package org.nqm.er.processor;

import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.nqm.er.common.NypostSiteMap;
import org.nqm.er.common.constant.MessageConstant;
import io.quarkus.logging.Log;
import io.smallrye.reactive.messaging.annotations.Blocking;
import io.vertx.core.json.JsonObject;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ArtProcessor {

    @Incoming(MessageConstant.CHANNEL_SITEMAP)
    @Blocking
    void getMonthUrlsFromNypostSitemap(JsonObject msg) {
        var monthSitemap = msg.mapTo(NypostSiteMap.class);
        Log.infof("consume %s", monthSitemap);
    }
}
