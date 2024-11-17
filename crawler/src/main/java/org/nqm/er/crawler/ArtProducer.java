package org.nqm.er.crawler;

import java.util.List;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.nqm.er.common.NypostSiteMap;
import org.nqm.er.common.constant.MessageConstant;
import org.nqm.er.crawler.service.NypostService;
import io.quarkus.logging.Log;
import io.quarkus.scheduler.Scheduled;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class ArtProducer {

    @Channel(MessageConstant.CHANNEL_SITEMAP)
    Emitter<NypostSiteMap> artEmitter;

    @Inject
    NypostService nypostService;

    @Scheduled(identity = "nypost-sitemap-crawler-job", every = "24h")
    void getMonthUrlsFromNypostSitemap() {
        nypostService.extractSitemapFromUrl("https://nypost.com/sitemap/").orElse(List.of())
                .forEach(x -> {
                    Log.info(x);
                    artEmitter.send(x);
                });
    }
}
