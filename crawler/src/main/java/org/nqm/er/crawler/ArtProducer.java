package org.nqm.er.crawler;

import java.util.List;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.nqm.er.common.constant.MessageConstant;
import org.nqm.er.common.dto.NypostSiteMapDto;
import org.nqm.er.common.repository.NypostSiteMapRepository;
import org.nqm.er.crawler.service.NypostService;
import io.quarkus.logging.Log;
import io.quarkus.scheduler.Scheduled;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class ArtProducer {

    @Channel(MessageConstant.CHANNEL_SITEMAP)
    Emitter<NypostSiteMapDto> artEmitter;

    @Inject
    NypostService nypostService;

    @Inject
    NypostSiteMapRepository repo;

    @Scheduled(identity = "nypost-sitemap-crawler-job", every = "24h")
    void getMonthUrlsFromNypostSitemap() {
        nypostService.extractSitemapFromUrl("https://nypost.com/sitemap/")
                .orElse(List.of())
                .forEach(artEmitter::send);
    }

    @Scheduled(identity = "nypost-day-urls-crawler-job", every = "1h")
    void getDayUrlsFromRandomNypostMonthUrl() {
        var result = repo.findRandomUncrawledMonthUrl();
        Log.infof("random entity: %s", result);
    }
}
