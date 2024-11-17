package org.nqm.er.crawler;

import java.util.UUID;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import io.quarkus.logging.Log;
import io.quarkus.scheduler.Scheduled;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ArtProducer {

    @Channel("art")
    Emitter<String> artEmitter;

    @Scheduled(identity = "nypost-sitemap-crawler-job", every = "2s", delayed = "1s")
    void getMonthUrlsFromNypostSitemap() {
        var uuid = "" + UUID.randomUUID();
        Log.infof("sending message %s", uuid);
        artEmitter.send(uuid);
    }
}
